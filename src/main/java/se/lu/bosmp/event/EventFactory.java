package se.lu.bosmp.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import se.lu.bosmp.model.Hit;
import se.lu.bosmp.model.MissionGameObject;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2014-12-14
 * Time: 18:55
 * To change this template use File | Settings | File Templates.
 */
public class EventFactory {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static GameEvent buildHitEvent(Hit h) {
        BaseEvent event = new BaseEvent();
        event.setEventType(EventType.HIT);
        event.setGameTime(h.getHitGameTime());
        try {
            event.setJson(objectMapper.writeValueAsString(h));
            return event;
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Could not serialize hit into JSON, aborting...");
        }
    }
}
