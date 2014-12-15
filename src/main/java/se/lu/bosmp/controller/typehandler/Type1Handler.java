package se.lu.bosmp.controller.typehandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import se.lu.bosmp.dao.AmmunitionDao;
import se.lu.bosmp.dao.GameObjectDefinitionDao;
import se.lu.bosmp.dao.MissionDao;
import se.lu.bosmp.dao.PlayerDao;
import se.lu.bosmp.model.Ammunition;
import se.lu.bosmp.model.Hit;
import se.lu.bosmp.model.MissionGameObject;
import se.lu.bosmp.model.MissionInstance;
import se.lu.bosmp.processor.data.AType1RowData;

/**
 * Handles hit events
 */
@Component(value = "Type1Handler")
public class Type1Handler implements RowDataHandler<AType1RowData> {

    static final Logger log = LoggerFactory.getLogger(RowDataHandler.class);

    @Autowired
    MissionDao missionDao;

    @Autowired
    private GameObjectDefinitionDao gameObjectDefinitionDao;

    @Autowired
    private PlayerDao playerDao;

    @Autowired
    private AmmunitionDao ammunitionDao;

    @Transactional
    @Override
    public void handle(AType1RowData rd) {

        MissionInstance missionInstance = missionDao.getMissionInstanceByIdHash(rd.getFileNameHash());

        MissionGameObject attacker = missionDao.getMissionGameObjectByGameObjectId(rd.getAttackerGameObjectId(), missionInstance.getId());
        MissionGameObject target = missionDao.getMissionGameObjectByGameObjectId(rd.getTargetGameObjectId(), missionInstance.getId());
        Ammunition  ammunition = new Ammunition();
        ammunition.setName(rd.getAmmunition());
        ammunition = ammunitionDao.getOrCreate(ammunition);

        Hit hit = new Hit();
        hit.setAttacker(attacker);
        hit.setTarget(target);
        hit.setAmmunition(ammunition);
        hit.setHitGameTime(rd.getTime());
        hit.setMissionInstance(missionInstance);

        missionDao.createOrUpdateHit(hit);

        // Possibly: Set hit ID to ParserContext so parser can associate relevant damage to this hit.
    }
}
