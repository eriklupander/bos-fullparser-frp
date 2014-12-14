package se.lu.bosmp.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lu.bosmp.model.GameObjectDefinition;
import se.lu.bosmp.model.Mission;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2014-12-11
 * Time: 21:39
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class GameObjectDefinitionDaoBean implements GameObjectDefinitionDao {

    @PersistenceContext
    EntityManager em;

    @Override
    public GameObjectDefinition getOrCreateGameObjectDefinition(GameObjectDefinition gameObjectDefinition) {
        try {
            GameObjectDefinition god = em.createQuery("SELECT god FROM GameObjectDefinition god WHERE god.type =:type", GameObjectDefinition.class)
                    .setParameter("type", gameObjectDefinition.getType().trim()).getSingleResult();
            return god;
        } catch (NoResultException e) {
            return em.merge(gameObjectDefinition);
        }
    }
}
