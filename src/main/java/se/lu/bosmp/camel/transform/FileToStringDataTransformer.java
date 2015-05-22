package se.lu.bosmp.camel.transform;

import org.apache.camel.Message;
import org.apache.camel.component.file.GenericFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import se.lu.bosmp.processor.SingleFileProcessor;

import java.io.File;

/**
 * Created by eriklupander on 2015-05-18.
 */
@Component(value="fileToStringDataTransformer")
public class FileToStringDataTransformer {

    private static final Logger logger = LoggerFactory.getLogger(FileToStringDataTransformer.class);

    public void process(Message message) throws Exception {
        logger.info("Transforming " + message.getBody().toString() + "...");
        if(message.getBody() instanceof GenericFile) {
            GenericFile gf = (GenericFile) message.getBody();
            message.setHeader("filename", gf.getFileName());
            message.setBody(new SingleFileProcessor().process(new File(gf.getAbsoluteFilePath())));
        }

    }
}
