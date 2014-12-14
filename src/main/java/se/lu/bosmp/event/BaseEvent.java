package se.lu.bosmp.event;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2014-12-14
 * Time: 18:52
 * To change this template use File | Settings | File Templates.
 */
public class BaseEvent implements GameEvent {

    private Integer gameTime;
    private String json;
    private EventType eventType;

    @Override
    public Integer getGameTime() {
        return gameTime;
    }

    public void setGameTime(Integer gameTime) {
        this.gameTime = gameTime;
    }

    @Override
    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    @Override
    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }
}
