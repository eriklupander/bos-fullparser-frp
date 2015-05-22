package se.lu.bosmp.camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.file.GenericFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by eriklupander on 2015-05-18.
 */
@Component(value="loggingProcessor")
public class LoggingProcessor implements Processor {

    private static final Logger logger = LoggerFactory.getLogger(LoggingProcessor.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        if (exchange.getIn().getBody() instanceof GenericFile) {
            GenericFile gf = (GenericFile) exchange.getIn().getBody();
            logger.info("New file discovered: " + gf.getFileName() + ". Moving to /current for processing.");
        }
    }

}
