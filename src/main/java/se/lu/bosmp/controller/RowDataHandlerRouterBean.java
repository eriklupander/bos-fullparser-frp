package se.lu.bosmp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import se.lu.bosmp.controller.typehandler.RowDataHandler;
import se.lu.bosmp.processor.RowData;

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
    RowDataHandler rowDataHandler0;

    @Autowired
    @Qualifier(value = "Type1Handler")
    RowDataHandler rowDataHandler1;

    @Autowired
    @Qualifier(value = "Type2Handler")
    RowDataHandler rowDataHandler2;

    @Autowired
    @Qualifier(value = "Type3Handler")
    RowDataHandler rowDataHandler3;

    @Autowired
    @Qualifier(value = "Type10Handler")
    RowDataHandler rowDataHandler10;

    @Autowired
    @Qualifier(value = "Type12Handler")
    RowDataHandler rowDataHandler12;

    @Override
    public void accept(RowData rd) {
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
