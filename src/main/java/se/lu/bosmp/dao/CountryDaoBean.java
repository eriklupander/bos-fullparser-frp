package se.lu.bosmp.dao;

import org.springframework.stereotype.Repository;
import se.lu.bosmp.model.Ammunition;
import se.lu.bosmp.model.Country;

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
public class CountryDaoBean implements CountryDao {

    @PersistenceContext
    EntityManager em;

    @Override
    public Country getOrCreate(Country country) {
        try {
            Country dbCountry = em.createQuery("SELECT a FROM Country a WHERE a.code = :code", Country.class)
                    .setParameter("code", country.getCode()).getSingleResult();
            return dbCountry;
        } catch (NoResultException e) {
            return em.merge(country);
        }
    }

}
