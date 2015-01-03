package se.lu.bosmp.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lu.bosmp.model.*;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2014-12-10
 * Time: 23:20
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class MissionDaoBean implements MissionDao {

    private static Logger log = LoggerFactory.getLogger(MissionDaoBean.class);

    @PersistenceContext
    EntityManager em;

    @Override
    public Mission getOrCreate(Mission mission) {
        try {
            Mission dbMission = em.createQuery("SELECT mt FROM Mission mt WHERE mt.nameHash =:nameHash", Mission.class)
                    .setParameter("nameHash", mission.getNameHash()).getSingleResult();
            return dbMission;
        } catch (NoResultException e) {
            return em.merge(mission);
        }
    }

    @Override
    public MissionInstance getOrCreate(MissionInstance missionInstance) {
        try {
            MissionInstance dbMissionInstance = em.createQuery("SELECT mi FROM MissionInstance mi WHERE mi.missionIdHash =:missionIdHash", MissionInstance.class)
                    .setParameter("missionIdHash", missionInstance.getMissionIdHash()).getSingleResult();
            return dbMissionInstance;
        } catch (NoResultException e) {
            return em.merge(missionInstance);
        }
    }

    // TODO - probably its best to create a dummy instance to update later if possible.
    @Override
    public MissionInstance getMissionInstanceByIdHash(Integer fileNameHash) {
        try {
            MissionInstance dbMissionInstance = em.createQuery("SELECT mi FROM MissionInstance mi WHERE mi.missionIdHash =:missionIdHash", MissionInstance.class)
                    .setParameter("missionIdHash", fileNameHash).getSingleResult();
            return dbMissionInstance;
        } catch (NoResultException e) {
            throw new IllegalArgumentException("Could not find a mission instance identified by " + fileNameHash);
        }
    }

    @Override
    @Transactional
    public MissionInstance getMissionInstance(Long missionInstanceId) {

        MissionInstance mi = em.find(MissionInstance.class, missionInstanceId);

        // Lazy-loading the old-school way..
        for(MissionGameObject mgo : mi.getMissionGameObjects() ) {
            loadChildren(mgo);
        }
        mi.getMissionParticipation().size();
        mi.getMissionGameObjectKills().size();
        mi.getMissionObjectGroups().size();

        return mi;
    }

    private void loadChildren(MissionGameObject mgo) {
        if(mgo.getChildren().size() > 0) {
            for(MissionGameObject child : mgo.getChildren()) {
                loadChildren(child);
            }
        }
    }

    @Override
    public MissionGameObject getOrCreate(MissionGameObject mgo) {
        try {
            MissionGameObject dbMgo = em.createQuery("SELECT mgo FROM MissionGameObject mgo WHERE mgo.missionInstance.id = :missionInstanceId AND mgo.gameObjectId = :gameObjectId", MissionGameObject.class)
                    .setParameter("missionInstanceId", mgo.getMissionInstance().getId())
                    .setParameter("gameObjectId", mgo.getGameObjectId())
                    .getSingleResult();
            return dbMgo;
        } catch (NoResultException e) {
            return em.merge(mgo);
        }
    }

    @Override
    public MissionGameObject getMissionGameObjectByGameObjectId(Integer planeGameObjectId, Long missionInstanceId) {
        try {
            MissionGameObject dbMgo = em.createQuery("SELECT mgo FROM MissionGameObject mgo WHERE mgo.missionInstance.id = :missionInstanceId AND mgo.gameObjectId = :gameObjectId", MissionGameObject.class)
                    .setParameter("missionInstanceId", missionInstanceId)
                    .setParameter("gameObjectId", planeGameObjectId)
                    .getSingleResult();
            return dbMgo;
        } catch (NoResultException e) {
            return null; // This can happen if someone sends -1 in..
        }
    }

    @Override
    public MissionGameObject updateMissionGameObject(MissionGameObject missionGameObject) {
        return em.merge(missionGameObject);
    }

    @Override
    public MissionParticipation createMissionParticipation(MissionParticipation missionParticipation) {
        return em.merge(missionParticipation);
    }

    @Override
    public Hit createOrUpdateHit(Hit hit) {
        return em.merge(hit);
    }

    @Override
    public Hit findHit(Long missionInstanceId, Long attackerId, Long targetId, Integer time) {

        List<Hit> hits = em.createQuery("SELECT h FROM Hit h WHERE " +
                "h.missionInstance.id = :missionInstanceId " +
                "AND h.attacker.id = :attackerId " +
                "AND h.target.id = :targetId " +
                "AND h.hitGameTime = :time", Hit.class)
                .setParameter("missionInstanceId", missionInstanceId)
                .setParameter("attackerId", attackerId)
                .setParameter("targetId", targetId)
                .setParameter("time", time)
                .getResultList();
        if(hits.size() == 0) {
            return null;
        } else if(hits.size() == 1) {
            return hits.get(0);
        } else {
            log.warn("findHit query returned " + hits.size() + " matches. Returning last of them: " + hits.get(hits.size() - 1).toString());
            return hits.get(hits.size() - 1);
        }
    }

    @Override
    public Kill createKill(Kill kill) {
        return em.merge(kill);
    }



}
