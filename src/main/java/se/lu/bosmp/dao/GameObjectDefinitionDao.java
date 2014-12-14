package se.lu.bosmp.dao;

import se.lu.bosmp.model.GameObjectDefinition;
import se.lu.bosmp.model.Player;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2014-12-11
 * Time: 21:38
 * To change this template use File | Settings | File Templates.
 */
public interface GameObjectDefinitionDao {
    GameObjectDefinition getOrCreateGameObjectDefinition(GameObjectDefinition gameObjectDefinition);
}
