package se.lu.bosmp.dao;

import se.lu.bosmp.model.Ammunition;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2014-12-11
 * Time: 23:03
 * To change this template use File | Settings | File Templates.
 */
public interface AmmunitionDao {

    Ammunition getOrCreate(Ammunition ammunition);
}
