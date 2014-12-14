package se.lu.bosmp.dao;

import se.lu.bosmp.model.Country;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2014-12-14
 * Time: 17:42
 * To change this template use File | Settings | File Templates.
 */
public interface CountryDao {
    Country getOrCreate(Country country);
}
