package se.lu.bosmp.controller.typehandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.lu.bosmp.event.GameEvent;
import se.lu.bosmp.processor.RowData;

import javax.persistence.EntityManager;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2014-12-10
 * Time: 22:36
 * To change this template use File | Settings | File Templates.
 */
public interface HandleRowData<T extends RowData> {

    static final Logger log = LoggerFactory.getLogger(HandleRowData.class);

    void handle(T rd);

    static void log(RowData rd) {
        log.info(rd.toString());
    }
}
