package se.lu.bosmp.model;

import javax.persistence.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2014-12-09
 * Time: 19:44
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class GameObjectDefinition extends BaseEntity {

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
