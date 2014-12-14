package se.lu.bosmp.controller.typehandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import se.lu.bosmp.controller.ParserContext;
import se.lu.bosmp.dao.AmmunitionDao;
import se.lu.bosmp.dao.GameObjectDefinitionDao;
import se.lu.bosmp.dao.MissionDao;
import se.lu.bosmp.dao.PlayerDao;
import se.lu.bosmp.model.Kill;
import se.lu.bosmp.model.MissionInstance;
import se.lu.bosmp.processor.data.AType3RowData;

/**
 * Handles kill events
 */
@Component(value = "Type3Handler")
public class Type3Handler implements HandleRowData<AType3RowData>{

    static final Logger log = LoggerFactory.getLogger(HandleRowData.class);

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
        MissionInstance missionInstance = missionDao.getMissionInstance(ParserContext.missionInstanceId);

        Kill kill = new Kill();
        if(rd.getAttackerGameObjectId() != null && rd.getAttackerGameObjectId() != -1) {
            kill.setAttacker(missionDao.getMissionGameObjectByGameObjectId(rd.getAttackerGameObjectId(), missionInstance.getId()));
        }

        kill.setTarget(missionDao.getMissionGameObjectByGameObjectId(rd.getTargetGameObjectId(), missionInstance.getId()));
        kill.setHitGameTime(rd.getTime());
        kill.setMissionInstance(missionInstance);

        kill = missionDao.createKill(kill);

    }
}
