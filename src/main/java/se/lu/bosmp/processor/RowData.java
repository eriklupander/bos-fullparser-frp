package se.lu.bosmp.processor;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2014-12-09
 * Time: 22:05
 * To change this template use File | Settings | File Templates.
 */
public interface RowData {
    Integer getTime();
    Integer getTypeCode();
    Long getIndex();

    Long getTenantId();
    Integer getFileNameHash();
}
