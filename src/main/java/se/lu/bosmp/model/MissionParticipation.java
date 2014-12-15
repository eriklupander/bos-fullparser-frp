package se.lu.bosmp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2014-12-09
 * Time: 19:46
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class MissionParticipation extends BaseEntity {

    private Player player;
    private MissionInstance missionInstance;
    private MissionGameObject missionGameObject;

    @ManyToOne
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @JsonIgnore
    @ManyToOne
    public MissionInstance getMissionInstance() {
        return missionInstance;
    }

    public void setMissionInstance(MissionInstance missionInstance) {
        this.missionInstance = missionInstance;
    }

    @ManyToOne
    public MissionGameObject getMissionGameObject() {
        return missionGameObject;
    }

    public void setMissionGameObject(MissionGameObject missionGameObject) {
        this.missionGameObject = missionGameObject;
    }
}
