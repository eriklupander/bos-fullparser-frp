package se.lu.bosmp.model;

import javax.persistence.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2014-12-14
 * Time: 17:40
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Country extends BaseEntity {

    private Integer code;
    private String name;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
