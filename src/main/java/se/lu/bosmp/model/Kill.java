package se.lu.bosmp.model;

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

    private Integer hitGameTime;
    private MissionGameObject attacker;
    private MissionGameObject target;
    private MissionInstance missionInstance;

    public Integer getHitGameTime() {
        return hitGameTime;
    }

    public void setHitGameTime(Integer hitGameTime) {
        this.hitGameTime = hitGameTime;
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

    @ManyToOne
    public MissionInstance getMissionInstance() {
        return missionInstance;
    }

    public void setMissionInstance(MissionInstance missionInstance) {
        this.missionInstance = missionInstance;
    }
}
