package se.lu.bosmp.processor;

import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2014-12-14
 * Time: 22:36
 * To change this template use File | Settings | File Templates.
 */
public class StringToRows {

    public List<String> process(String body) {
        return Arrays.asList(body.split("\r\n"));
    }
}
