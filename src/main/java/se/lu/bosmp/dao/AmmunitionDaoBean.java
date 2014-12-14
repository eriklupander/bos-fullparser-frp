package se.lu.bosmp.dao;

import org.springframework.stereotype.Repository;
import se.lu.bosmp.model.Ammunition;
import se.lu.bosmp.model.Player;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2014-12-11
 * Time: 23:03
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class AmmunitionDaoBean implements AmmunitionDao {

    @PersistenceContext
    EntityManager em;

    @Override
    public Ammunition getOrCreate(Ammunition ammunition) {
        try {
            Ammunition god = em.createQuery("SELECT a FROM Ammunition a WHERE a.name = :name", Ammunition.class)
                    .setParameter("name", ammunition.getName().trim()).getSingleResult();
            return god;
        } catch (NoResultException e) {
            return em.merge(ammunition);
        }
    }

}
