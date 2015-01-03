package se.lu.bosmp.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import rx.Observer;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2014-12-14
 * Time: 19:58
 * To change this template use File | Settings | File Templates.
 */
@Component
public class EventSender implements Observer<GameEvent> {

    private static final Logger log = LoggerFactory.getLogger(EventSender.class);

    private SimpMessagingTemplate template;

    @Autowired
    public EventSender(SimpMessagingTemplate template) {
        this.template = template;
    }


    @Override
    public void onCompleted() {
        log.info("ENTER - onCompleted");
    }

    @Override
    public void onError(Throwable throwable) {
        log.info("ENTER - onError: " + throwable.getClass().getSimpleName() + " " + throwable.getMessage());
    }

    @Override
    public void onNext(GameEvent gameEvent) {
        log.info("ENTER - onNext");
        this.template.convertAndSend("/topic/gameevents", gameEvent.getJson());
    }
}
