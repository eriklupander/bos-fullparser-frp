package se.lu.bosmp.dao;

import org.springframework.stereotype.Repository;
import se.lu.bosmp.model.Player;

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
public class PlayerDaoBean implements PlayerDao {

    @PersistenceContext
    EntityManager em;

    @Override
    public Player getOrCreate(Player player) {
        try {
            Player god = em.createQuery("SELECT p FROM Player p WHERE p.name = :name", Player.class)
                    .setParameter("name", player.getName().trim()).getSingleResult();
            return god;
        } catch (NoResultException e) {
            return em.merge(player);
        }
    }
}
