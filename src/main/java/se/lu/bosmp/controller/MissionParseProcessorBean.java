package se.lu.bosmp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import se.lu.bosmp.processor.RowData;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2014-12-10
 * Time: 22:27
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class MissionParseProcessorBean implements MissionParseManager {

    private static final Logger log = LoggerFactory.getLogger(MissionParseProcessorBean.class);

    @Autowired
    RowDataHandlerRouterBean router;

    @Override
    public void process(List<RowData> rowData) {
        rowData.stream().forEach(router);
    }

    @Override
    public void onCompleted() {
        log.info("ENTER - onCompleted");
    }

    @Override
    public void onError(Throwable throwable) {
        log.info("ENTER - onError: " + throwable.getMessage());
        throwable.printStackTrace();
    }

    @Override
    public void onNext(List<RowData> rowData) {
        rowData.stream().forEach(router);
    }
}
