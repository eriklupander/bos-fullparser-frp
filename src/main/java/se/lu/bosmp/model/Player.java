package se.lu.bosmp.model;

import javax.persistence.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2014-12-09
 * Time: 19:43
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Player extends BaseEntity {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
