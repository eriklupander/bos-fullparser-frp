package se.lu.bosmp.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import se.lu.bosmp.processor.data.*;
import se.lu.bosmp.scanner.FileRowData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2014-12-09
 * Time: 21:41
 * To change this template use File | Settings | File Templates.
 */
@Component(value="logProcessor")
public class LogProcessor  {

    private static final Logger log = LoggerFactory.getLogger(LogProcessor.class);

    private long index = 0;

    public List<RowData> process(FileRowData fileRowData) {
        log.info("Processing " + fileRowData.getRows().size() + " log rows");
        List<RowData> rowData = new ArrayList<>();
        for(String row : fileRowData.getRows()) {
            Integer typeCode = RowElementParser.parseTypeCode(row);
            RowData rd = null;
            switch(typeCode) {
                case 12:
                    rd = new AType12RowData(row, fileRowData.getFileNameHash(), index++);
                    break;
                case 10:
                    rd = new AType10RowData(row, fileRowData.getFileNameHash(), index++);
                    break;
                case 0:
                    rd= new AType0RowData(row, fileRowData.getFileNameHash(), index++);
                    break;
                case 1:
                    rd = new AType1RowData(row, fileRowData.getFileNameHash(), index++);
                    break;
                case 2:
                    rd = new AType2RowData(row, fileRowData.getFileNameHash(), index++);
                    break;
                case 3:
                    rd = new AType3RowData(row, fileRowData.getFileNameHash(), index++);
                    break;
            }
            if( rd != null ) {
                rowData.add(rd);
            }
        }

        return rowData;
    }
}
