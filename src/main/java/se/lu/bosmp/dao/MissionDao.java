package se.lu.bosmp.dao;

import se.lu.bosmp.model.*;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2014-12-10
 * Time: 23:20
 * To change this template use File | Settings | File Templates.
 */
public interface MissionDao {

    Mission getOrCreate(Mission mission);

    MissionInstance getOrCreate(MissionInstance mission);

  //  MissionInstance getMissionInstance(Long missionInstanceId);

    MissionGameObject getOrCreate(MissionGameObject mgo);

    MissionGameObject getMissionGameObjectByGameObjectId(Integer planeGameObjectId, Long missionInstanceId);

    MissionGameObject updateMissionGameObject(MissionGameObject missionGameObject);

    MissionParticipation createMissionParticipation(MissionParticipation missionParticipation);

    Hit createOrUpdateHit(Hit hit);

    Hit findHit(Long missionInstanceId, Long attackerId, Long targetId, Integer time);

    Kill createKill(Kill kill);

    MissionInstance getMissionInstanceByIdHash(Integer fileNameHash);


    MissionInstance getMissionInstance(Long id);
}
