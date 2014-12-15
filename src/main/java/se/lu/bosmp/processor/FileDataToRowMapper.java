package se.lu.bosmp.processor;

import se.lu.bosmp.scanner.FileData;
import se.lu.bosmp.scanner.FileRowData;

import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2014-12-14
 * Time: 22:36
 * To change this template use File | Settings | File Templates.
 */
public class FileDataToRowMapper {

    public FileRowData process(FileData fileData) {
        FileRowData fileRowData = new FileRowData();
        fileRowData.setRows(Arrays.asList(fileData.getData().split("\r\n")));
        fileRowData.setFileNameHash(fileData.getFileNameHash());
        return fileRowData;
    }
}
