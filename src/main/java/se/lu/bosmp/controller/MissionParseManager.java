package se.lu.bosmp.controller;

import rx.Observer;
import se.lu.bosmp.processor.RowData;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2014-12-10
 * Time: 22:25
 * To change this template use File | Settings | File Templates.
 */
public interface MissionParseManager extends Observer<List<RowData>> {

    void process(List<RowData> rowData);
}
