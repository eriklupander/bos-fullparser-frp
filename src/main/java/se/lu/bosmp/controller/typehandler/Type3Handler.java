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
import se.lu.bosmp.model.Kill;
import se.lu.bosmp.model.MissionGameObject;
import se.lu.bosmp.model.MissionInstance;
import se.lu.bosmp.processor.data.AType3RowData;

/**
 * Handles kill events
 */
@Component(value = "Type3Handler")
public class Type3Handler implements RowDataHandler<AType3RowData> {

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
    public void handle(AType3RowData rd) {
        MissionInstance missionInstance = missionDao.getMissionInstanceByIdHash(rd.getFileNameHash());

        Kill kill = new Kill();
        if(rd.getAttackerGameObjectId() != null && rd.getAttackerGameObjectId() != -1) {
            kill.setAttacker(missionDao.getMissionGameObjectByGameObjectId(rd.getAttackerGameObjectId(), missionInstance.getId()));
        }
        MissionGameObject target = missionDao.getMissionGameObjectByGameObjectId(rd.getTargetGameObjectId(), missionInstance.getId());
        kill.setTarget(target);
        kill.setKilledGameTime(rd.getTime());
        kill.setMissionInstance(missionInstance);


        kill = missionDao.createKill(kill);

        target.setDestroyedGameTime(rd.getTime());
        missionDao.updateMissionGameObject(target);

    }
}
