package se.lu.bosmp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2014-12-09
 * Time: 21:23
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Hit extends BaseEntity {

    private Integer hitGameTime;
    private MissionGameObject attacker;
    private MissionGameObject target;
    private Ammunition ammunition;
    private Float damage;
    private MissionInstance missionInstance;

    public Integer getHitGameTime() {
        return hitGameTime;
    }

    public void setHitGameTime(Integer hitGameTime) {
        this.hitGameTime = hitGameTime;
    }

    @ManyToOne
    public Ammunition getAmmunition() {
        return ammunition;
    }

    public void setAmmunition(Ammunition ammunition) {
        this.ammunition = ammunition;
    }

    public Float getDamage() {
        return damage;
    }

    public void setDamage(Float damage) {
        this.damage = damage;
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

    @Override
    public String toString() {
        return "Hit{" +
                "hitGameTime=" + hitGameTime +
                ", attacker=" + attacker +
                ", target=" + target +
                ", ammunition=" + ammunition +
                ", damage=" + damage +
                ", missionInstance=" + missionInstance +
                '}';
    }
}
