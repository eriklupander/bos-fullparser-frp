package se.lu.bosmp.controller.typehandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import rx.Observable;
import se.lu.bosmp.controller.ParserContext;
import se.lu.bosmp.dao.AmmunitionDao;
import se.lu.bosmp.dao.GameObjectDefinitionDao;
import se.lu.bosmp.dao.MissionDao;
import se.lu.bosmp.dao.PlayerDao;
import se.lu.bosmp.event.EventFactory;
import se.lu.bosmp.event.EventSender;
import se.lu.bosmp.event.GameEvent;
import se.lu.bosmp.model.Hit;
import se.lu.bosmp.model.MissionGameObject;
import se.lu.bosmp.model.MissionInstance;
import se.lu.bosmp.processor.data.AType2RowData;

import javax.annotation.PostConstruct;

/**
 * Handles hit events
 */
@Component(value = "Type2Handler")
public class Type2Handler implements HandleRowData<AType2RowData>{

    static final Logger log = LoggerFactory.getLogger(HandleRowData.class);

    @Autowired
    MissionDao missionDao;

    @Autowired
    private GameObjectDefinitionDao gameObjectDefinitionDao;

    @Autowired
    private PlayerDao playerDao;

    @Autowired
    private AmmunitionDao ammunitionDao;

    @Autowired
    EventSender eventSender;

    @Transactional
    @Override
    public void handle(AType2RowData rd) {
        if(rd.getAttackerGameObjectId() == -1) {
            return;
        }
        MissionInstance missionInstance = missionDao.getMissionInstance(ParserContext.missionInstanceId);

        MissionGameObject attacker = missionDao.getMissionGameObjectByGameObjectId(rd.getAttackerGameObjectId(), ParserContext.missionInstanceId);
        MissionGameObject target = missionDao.getMissionGameObjectByGameObjectId(rd.getTargetGameObjectId(), ParserContext.missionInstanceId);


        // Try to find a hit in same mission, same att/tgt and same timestamp
        try {
            Hit hit = missionDao.findHit(ParserContext.missionInstanceId, attacker.getId(), target.getId(), rd.getTime());
            if(hit != null) {
                hit.setDamage(rd.getDamage());
                missionDao.createOrUpdateHit(hit);
            }

            // Try to RxJava stuff to send the event
            Observable<GameEvent> observable = Observable.just(hit).map( h -> EventFactory.buildHitEvent(h)).doOnNext(ge -> log.info("I was triggered!"));
            observable.subscribe(eventSender);
        } catch (NullPointerException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
