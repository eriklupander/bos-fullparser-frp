package se.lu.bosmp.event;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2014-12-14
 * Time: 18:49
 * To change this template use File | Settings | File Templates.
 */
public interface GameEvent {
    Integer getGameTime();
    String getJson();
    EventType getEventType();
}
