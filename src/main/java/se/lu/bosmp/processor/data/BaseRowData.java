package se.lu.bosmp.processor.data;

import se.lu.bosmp.processor.RowData;
import se.lu.bosmp.processor.RowElementParser;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2014-12-09
 * Time: 22:10
 * To change this template use File | Settings | File Templates.
 */
public class BaseRowData implements RowData {

    private Integer time;
    private Integer typeCode;
    private Long index;

    protected BaseRowData(String row, Long index) {

        this.time = RowElementParser.parseTime(row);
        this.typeCode = RowElementParser.parseTypeCode(row);
        this.index = index;
    }

    public Integer getTime() {
        return time;
    }

    @Override
    public Integer getTypeCode() {
        return typeCode;
    }

    public Long getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return "BaseRowData{" +
                "typeCode=" + typeCode +
                ", time=" + time +
                '}';
    }
}
