package se.lu.bosmp.scanner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2014-12-15
 * Time: 09:39
 * To change this template use File | Settings | File Templates.
 */
public class FileRowData {
    private Integer fileNameHash;
    private List<String> rows = new ArrayList<>();

    public Integer getFileNameHash() {
        return fileNameHash;
    }

    public void setFileNameHash(Integer fileNameHash) {
        this.fileNameHash = fileNameHash;
    }

    public List<String> getRows() {
        return rows;
    }

    public void setRows(List<String> rows) {
        this.rows = rows;
    }
}
