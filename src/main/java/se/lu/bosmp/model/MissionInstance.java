package se.lu.bosmp.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2014-12-09
 * Time: 19:44
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class MissionInstance extends BaseEntity {

    private Calendar started;
    private Calendar ended;

    private Mission mission;

    private Set<MissionParticipation> missionParticipation = new HashSet<>();
    private Set<MissionGameObject> missionGameObjects = new HashSet<>();

    private Set<Kill> missionGameObjectKills = new HashSet<>();

    @Temporal(TemporalType.TIMESTAMP)
    public Calendar getStarted() {
        return started;
    }

    public void setStarted(Calendar started) {
        this.started = started;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Calendar getEnded() {
        return ended;
    }

    public void setEnded(Calendar ended) {
        this.ended = ended;
    }

    @ManyToOne(cascade = CascadeType.MERGE)
    public Mission getMission() {
        return mission;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }

    @OneToMany
    public Set<MissionParticipation> getMissionParticipation() {
        return missionParticipation;
    }

    public void setMissionParticipation(Set<MissionParticipation> missionParticipation) {
        this.missionParticipation = missionParticipation;
    }

    @OneToMany
    @JoinTable
    public Set<MissionGameObject> getMissionGameObjects() {
        return missionGameObjects;
    }

    public void setMissionGameObjects(Set<MissionGameObject> missionGameObjects) {
        this.missionGameObjects = missionGameObjects;
    }

    @OneToMany
    @JoinTable
    public Set<Kill> getMissionGameObjectKills() {
        return missionGameObjectKills;
    }

    public void setMissionGameObjectKills(Set<Kill> missionGameObjectKills) {
        this.missionGameObjectKills = missionGameObjectKills;
    }
}
