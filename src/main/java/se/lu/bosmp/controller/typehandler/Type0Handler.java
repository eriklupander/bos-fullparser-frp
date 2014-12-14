package se.lu.bosmp.controller.typehandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import se.lu.bosmp.controller.ParserContext;
import se.lu.bosmp.dao.MissionDao;
import se.lu.bosmp.event.GameEvent;
import se.lu.bosmp.model.Mission;
import se.lu.bosmp.model.MissionInstance;
import se.lu.bosmp.processor.data.AType0RowData;

import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2014-12-10
 * Time: 22:37
 * To change this template use File | Settings | File Templates.
 */
@Component(value = "Type0Handler")
public class Type0Handler implements HandleRowData<AType0RowData> {

    static final Logger log = LoggerFactory.getLogger(HandleRowData.class);

    @Autowired
    MissionDao missionDao;

    @Override
    @Transactional
    public void handle(AType0RowData rd) {
        HandleRowData.log(rd);
        Mission mission = new Mission();
        mission.setName(rd.getMissionFile());
        mission.setNameHash(rd.getMissionFile().hashCode());

        mission = missionDao.getOrCreate(mission);
        ParserContext.missionId = mission.getId();

        MissionInstance missionInstance = new MissionInstance();
        missionInstance.setMission(mission);
        missionInstance.setStarted(Calendar.getInstance());


        missionInstance = missionDao.getOrCreate(missionInstance);

        ParserContext.missionInstanceId = missionInstance.getId();
    }
}
