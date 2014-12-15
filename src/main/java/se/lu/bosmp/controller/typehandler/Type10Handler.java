package se.lu.bosmp.controller.typehandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import se.lu.bosmp.dao.GameObjectDefinitionDao;
import se.lu.bosmp.dao.MissionDao;
import se.lu.bosmp.dao.PlayerDao;
import se.lu.bosmp.model.*;
import se.lu.bosmp.processor.data.AType10RowData;

/**
 * AType10  (Player joined mission)
 */
@Component(value = "Type10Handler")
public class Type10Handler implements RowDataHandler<AType10RowData> {

    static final Logger log = LoggerFactory.getLogger(RowDataHandler.class);

    @Autowired
    MissionDao missionDao;

    @Autowired
    private GameObjectDefinitionDao gameObjectDefinitionDao;

    @Autowired
    private PlayerDao playerDao;

    @Override
    @Transactional
    public void handle(AType10RowData rd) {

        MissionInstance missionInstance = missionDao.getMissionInstanceByIdHash(rd.getFileNameHash());

//        GameObjectDefinition gameObjectDefinition = new GameObjectDefinition();
//        gameObjectDefinition.setType(rd.getType().trim());
//        gameObjectDefinition = gameObjectDefinitionDao.getOrCreateGameObjectDefinition(gameObjectDefinition);

        Player player = new Player();
        player.setName(rd.getPilotName());
        player = playerDao.getOrCreate(player);

        MissionParticipation mp = new MissionParticipation();
        mp.setMissionInstance(missionInstance);
        mp.setPlayer(player);

        // Now a litle tricky one. We need to find the MissionGameObject this pilot has taken control over.
        // Consider these three lines:
        // T:5 AType:12 ID:1865727 TYPE:Bf 109 G-2 COUNTRY:201 NAME:Rabe PID:-1
        // T:5 AType:12 ID:1866751 TYPE:BotPilot_Bf109 COUNTRY:201 NAME:BotPilot_Bf109 PID:1865727
        // T:5 AType:10 PLID:1865727 PID:1866751

        // The PLID should map to the airplane, while the PID maps the player to the Bot object. I think we can ignore the bot object for now. maybe even remove it?
        MissionGameObject parentVehicle = missionDao.getMissionGameObjectByGameObjectId(rd.getPlaneGameObjectId(), missionInstance.getId());
        mp.setMissionGameObject(parentVehicle);
        parentVehicle.setPlayer(player);

        parentVehicle = missionDao.updateMissionGameObject(parentVehicle);

        mp = missionDao.createMissionParticipation(mp);
    }
}
