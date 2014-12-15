package se.lu.bosmp.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import rx.Observable;
import rx.Subscription;
import rx.schedulers.Schedulers;
import se.lu.bosmp.dao.MissionDao;
import se.lu.bosmp.model.MissionInstance;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2014-12-09
 * Time: 21:46
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping("/rest/view")
public class DataServiceBean {

    private static final Logger log = LoggerFactory.getLogger(DataServiceBean.class);

    @Autowired
    MissionDao missionDao;

    @Autowired
    @Qualifier("dbThreadPoolExecutor")
    private TaskExecutor dbThreadPoolExecutor;

    @RequestMapping(method = RequestMethod.GET, value = "/missioninstance/{id}", produces = "application/json")
    public DeferredResult<MissionInstance> getMissionInstance(@PathVariable Long id) {

        DeferredResult<MissionInstance> deferredResult = new DeferredResult<>();
        Subscription subscription =
                Observable.just(id)
                        .subscribeOn(Schedulers.from(dbThreadPoolExecutor))
                        .map(myId -> missionDao.getMissionInstance(myId))
                        .subscribe(v -> deferredResult.setResult(v));

        deferredResult.onCompletion(subscription::unsubscribe);
        return deferredResult;
    }

}
