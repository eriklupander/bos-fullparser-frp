package se.lu.bosmp.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2014-12-09
 * Time: 19:50
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class MissionGameObject extends BaseEntity {

    private Integer gameObjectId;

    private Integer spawnedGameTime;
    private Integer destroyedGameTime;

    private MissionInstance missionInstance;
    private GameObjectDefinition gameObjectDefinition;
    private Player player;
    private Country country;

    public Integer getGameObjectId() {
        return gameObjectId;
    }

    public void setGameObjectId(Integer gameObjectId) {
        this.gameObjectId = gameObjectId;
    }

    public Integer getSpawnedGameTime() {
        return spawnedGameTime;
    }

    public void setSpawnedGameTime(Integer spawnedGameTime) {
        this.spawnedGameTime = spawnedGameTime;
    }

    public Integer getDestroyedGameTime() {
        return destroyedGameTime;
    }

    public void setDestroyedGameTime(Integer destroyedGameTime) {
        this.destroyedGameTime = destroyedGameTime;
    }

    @ManyToOne
    public MissionInstance getMissionInstance() {
        return missionInstance;
    }

    public void setMissionInstance(MissionInstance missionInstance) {
        this.missionInstance = missionInstance;
    }

    @ManyToOne
    public GameObjectDefinition getGameObjectDefinition() {
        return gameObjectDefinition;
    }

    public void setGameObjectDefinition(GameObjectDefinition gameObjectDefinition) {
        this.gameObjectDefinition = gameObjectDefinition;
    }

    @ManyToOne(optional = true)
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @ManyToOne(optional = false)
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MissionGameObject that = (MissionGameObject) o;

        if (!gameObjectId.equals(that.gameObjectId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return gameObjectId.hashCode();
    }
}
