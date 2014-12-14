package se.lu.bosmp.simulator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import rx.Observable;
import se.lu.bosmp.event.*;
import se.lu.bosmp.model.Hit;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2014-12-14
 * Time: 20:02
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class EventSimulator {

    private Logger log = LoggerFactory.getLogger(EventSimulator.class);

    @Autowired
    EventSender eventSender;


   // @Scheduled(fixedDelay = 1000)
    public void send() {
        BaseEvent baseEvent = new BaseEvent();
        baseEvent.setGameTime((int) (System.currentTimeMillis() / 100000L));
        baseEvent.setEventType(EventType.TEST);
        baseEvent.setJson("{\"TEST\":\"TRUE\"}");
       // eventSender.sendEvent(baseEvent);
        Hit hit = new Hit();
        hit.setDamage(1f);
        hit.setHitGameTime(1234);
        hit.setId(12121212L);
        Observable<GameEvent> observable = Observable.just(hit)
                .map(h -> EventFactory.buildHitEvent(h));
        observable.subscribe(eventSender);
    }
}
