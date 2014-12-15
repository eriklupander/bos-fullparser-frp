package se.lu.bosmp.scanner;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2014-12-15
 * Time: 09:14
 * To change this template use File | Settings | File Templates.
 */
public class FileData {

    private String tentantId;
    private String fileName;

    private String data;
    private Integer fileNameHash;

    public String getTentantId() {
        return tentantId;
    }

    public void setTentantId(String tentantId) {
        this.tentantId = tentantId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setFileNameHash(Integer fileNameHash) {
        this.fileNameHash = fileNameHash;
    }

    public Integer getFileNameHash() {
        return fileNameHash;
    }
}
