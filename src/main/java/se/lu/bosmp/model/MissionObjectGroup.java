package se.lu.bosmp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2014-12-09
 * Time: 22:36
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class MissionObjectGroup extends BaseEntity {

    private String name;
    private Integer gameObjectGroupId;

    private MissionGameObject leaderGameObject;
    private List<MissionGameObject> groupMembers = new ArrayList<>();
    private MissionInstance missionInstance;

    @ManyToOne
    public MissionGameObject getLeaderGameObject() {
        return leaderGameObject;
    }

    public void setLeaderGameObject(MissionGameObject leaderGameObject) {
        this.leaderGameObject = leaderGameObject;
    }

    @OneToMany(mappedBy = "missionObjectGroup")
    public List<MissionGameObject> getGroupMembers() {
        return groupMembers;
    }

    public void setGroupMembers(List<MissionGameObject> groupMembers) {
        this.groupMembers = groupMembers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGameObjectGroupId() {
        return gameObjectGroupId;
    }

    public void setGameObjectGroupId(Integer gameObjectGroupId) {
        this.gameObjectGroupId = gameObjectGroupId;
    }

    @JsonIgnore
    @ManyToOne
    public MissionInstance getMissionInstance() {
        return missionInstance;
    }

    public void setMissionInstance(MissionInstance missionInstance) {
        this.missionInstance = missionInstance;
    }
}
