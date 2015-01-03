package se.lu.bosmp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2014-12-12
 * Time: 20:18
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="missioninstance_kill")
public class Kill extends BaseEntity {

    private Integer killedGameTime;
    private MissionGameObject attacker;
    private MissionGameObject target;
    private MissionInstance missionInstance;

    public Integer getKilledGameTime() {
        return killedGameTime;
    }

    public void setKilledGameTime(Integer killedGameTime) {
        this.killedGameTime = killedGameTime;
    }

    @ManyToOne
    public MissionGameObject getAttacker() {
        return attacker;
    }

    public void setAttacker(MissionGameObject attacker) {
        this.attacker = attacker;
    }

    @ManyToOne
    public MissionGameObject getTarget() {
        return target;
    }

    public void setTarget(MissionGameObject target) {
        this.target = target;
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
