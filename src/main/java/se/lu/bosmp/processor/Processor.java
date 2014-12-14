package se.lu.bosmp.processor;

import java.io.File;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2014-12-10
 * Time: 22:14
 * To change this template use File | Settings | File Templates.
 */
public interface Processor<T, U> {

    List<T> process(List<U> list);


}
