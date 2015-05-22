package se.lu.bosmp.camel;

import javax.annotation.PostConstruct;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.language.ConstantExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import se.lu.bosmp.camel.aggregator.BatchingAggregator;
import se.lu.bosmp.camel.processor.LogDataProcessor;

/**
 * Created by eriklupander on 2015-05-18.
 */
@Component
public class FileRouter extends RouteBuilder {

    public static final String DEFAULT_SCAN_FOLDER = "/Users/eriklupander/privat/bos-fullparser-frp/testdata";

    private static final Logger logger = LoggerFactory.getLogger(FileRouter.class);

    @Autowired
    private Environment env;

    @Autowired
    LogDataProcessor logDataProcessor;

    @Autowired
    BatchingAggregator batchingAggregator;

    private String reportsFolder;

    @PostConstruct
    public void init() {
        this.reportsFolder = DEFAULT_SCAN_FOLDER;//env.getProperty("reports.directory", DEFAULT_SCAN_FOLDER);
        log.info("Set directory '" + this.reportsFolder + "' as reports folder");
    }

    @Override
    public void configure() throws Exception {

        // Always copy files from source dir once complete into the current.
        from("file://" + reportsFolder + "?sortBy=file:modified&readLock=markerFile").to("file://" + reportsFolder + "/current");

        // Then start the actual processing from the /current folder.
        //from("file://" + reportsFolder + "/current?sortBy=file:modified&idempotent=true&idempotentRepository=#fileStore&move=../done/${file:name}")
        from("file://" + reportsFolder + "/current?sortBy=file:modified&move=../done/${file:name}")
                .to("bean:loggingProcessor")
            .to("bean:fileToStringDataTransformer")
            .filter(exchange -> {
                if (((String) exchange.getIn().getHeader("filename")).startsWith("missionReport")) {
                    return true;
                } else {
                    logger.info("Rejecting message, filename not supported.");
                    return false;
                }
            })
            .to("bean:fileDataProcessor")
            .aggregate(new ConstantExpression("TEST"), batchingAggregator).completionInterval(5000L)
            .process(logDataProcessor);


    }
}
