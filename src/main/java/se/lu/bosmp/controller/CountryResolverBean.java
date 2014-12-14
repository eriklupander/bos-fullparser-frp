package se.lu.bosmp.controller;

import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2014-12-14
 * Time: 17:44
 * To change this template use File | Settings | File Templates.
 */
@Component
public class CountryResolverBean implements CountryResolver {
    @Override
    public String getCountryNameByCode(Integer country) {
        switch(country) {
            case 101:
                return "Soviet Union";
            case 201:
                return "Germany";
            default:
                return "Unspecified";
        }
    }
}
