package se.lu.bosmp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import se.lu.bosmp.controller.typehandler.HandleRowData;
import se.lu.bosmp.controller.typehandler.Type0Handler;
import se.lu.bosmp.processor.RowData;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.function.Consumer;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2014-12-10
 * Time: 22:56
 * To change this template use File | Settings | File Templates.
 */
@Component
public class RowDataHandlerRouterBean implements Consumer<RowData> {

    private static final Logger log = LoggerFactory.getLogger(RowDataHandlerRouterBean.class);

    @Autowired
    @Qualifier(value = "Type0Handler")
    HandleRowData rowDataHandler0;

    @Autowired
    @Qualifier(value = "Type1Handler")
    HandleRowData rowDataHandler1;

    @Autowired
    @Qualifier(value = "Type2Handler")
    HandleRowData rowDataHandler2;

    @Autowired
    @Qualifier(value = "Type3Handler")
    HandleRowData rowDataHandler3;


    @Autowired
    @Qualifier(value = "Type10Handler")
    HandleRowData rowDataHandler10;

    @Autowired
    @Qualifier(value = "Type12Handler")
    HandleRowData rowDataHandler12;

    @Override
    public void accept(RowData rd) {
        log.info("Handling index: " + rd.getIndex());
        switch(rd.getTypeCode()) {
            case 12:
                rowDataHandler12.handle(rd);
                break;
            case 10:
                rowDataHandler10.handle(rd);
                break;
            case 0:
                rowDataHandler0.handle(rd);
                break;
            case 1:
                rowDataHandler1.handle(rd);
                break;
            case 2:
                rowDataHandler2.handle(rd);
                break;
            case 3:
                rowDataHandler3.handle(rd);
                break;
        }
    }
}
