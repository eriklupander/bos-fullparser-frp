package se.lu.bosmp.model;

import javax.persistence.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2014-12-09
 * Time: 19:41
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Mission extends BaseEntity {

    private String name;

    private Integer nameHash;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNameHash() {
        return nameHash;
    }

    public void setNameHash(Integer nameHash) {
        this.nameHash = nameHash;
    }
}
