package se.lu.bosmp.model;

import javax.persistence.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2014-12-09
 * Time: 21:26
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Ammunition extends BaseEntity {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
