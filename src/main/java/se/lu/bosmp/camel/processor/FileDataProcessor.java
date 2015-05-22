package se.lu.bosmp.camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import se.lu.bosmp.processor.FileDataToRowMapper;
import se.lu.bosmp.scanner.FileData;
import se.lu.bosmp.scanner.FileRowData;

/**
 * Created by eriklupander on 2015-05-18.
 */
@Component(value="fileDataProcessor")
public class FileDataProcessor implements Processor {

    private static final Logger logger = LoggerFactory.getLogger(FileDataProcessor.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        logger.info("ENTER - FileDataProcessor, will transform from plain string into row data for file " + exchange.getIn().getHeader("filename"));
        FileRowData processedData = new FileDataToRowMapper().process((FileData) exchange.getIn().getBody());
        exchange.getOut().setBody(processedData);
        logger.info("EXIT - FileDataProcessor. Body processed into line data for file " + exchange.getIn().getHeader("filename"));
    }
}
