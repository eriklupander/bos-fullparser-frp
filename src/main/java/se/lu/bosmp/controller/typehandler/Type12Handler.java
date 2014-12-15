package se.lu.bosmp.controller.typehandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import se.lu.bosmp.controller.CountryResolver;
import se.lu.bosmp.dao.CountryDao;
import se.lu.bosmp.dao.GameObjectDefinitionDao;
import se.lu.bosmp.dao.MissionDao;
import se.lu.bosmp.model.Country;
import se.lu.bosmp.model.GameObjectDefinition;
import se.lu.bosmp.model.MissionGameObject;
import se.lu.bosmp.model.MissionInstance;
import se.lu.bosmp.processor.data.AType12RowData;

/**
 * AType12  (some game object spawned)
 */
@Component(value = "Type12Handler")
public class Type12Handler implements RowDataHandler<AType12RowData> {

    static final Logger log = LoggerFactory.getLogger(RowDataHandler.class);

    @Autowired
    MissionDao missionDao;

    @Autowired
    private GameObjectDefinitionDao gameObjectDefinitionDao;

    @Autowired
    CountryResolver countryResolver;

    @Autowired
    CountryDao countryDao;

    @Override
    @Transactional
    public void handle(AType12RowData rd) {

        MissionInstance missionInstance = missionDao.getMissionInstanceByIdHash(rd.getFileNameHash());

        GameObjectDefinition gameObjectDefinition = new GameObjectDefinition();
        gameObjectDefinition.setType(rd.getType().trim());
        gameObjectDefinition = gameObjectDefinitionDao.getOrCreateGameObjectDefinition(gameObjectDefinition);

        Country country = new Country();
        country.setCode(rd.getCountry());
        country.setName(countryResolver.getCountryNameByCode(rd.getCountry()));
        country = countryDao.getOrCreate(country);

        MissionGameObject mgo = new MissionGameObject();
        mgo.setSpawnedGameTime(rd.getTime());
        mgo.setGameObjectId(rd.getGameObjectId());
        mgo.setGameObjectDefinition(gameObjectDefinition);
        mgo.setMissionInstance(missionInstance);
        mgo.setCountry(country);

        mgo = missionDao.getOrCreate(mgo);

    }
}
