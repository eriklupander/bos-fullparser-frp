package se.lu.bosmp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import se.lu.bosmp.processor.RowData;
import se.lu.bosmp.processor.data.AType3RowData;
import se.lu.bosmp.processor.data.BaseHitRowData;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2014-12-10
 * Time: 22:27
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class MissionParseProcessorBean implements MissionParseManager {

    private static final Logger log = LoggerFactory.getLogger(MissionParseProcessorBean.class);

    @Autowired
    RowDataHandlerRouterBean router;

    @Override
    public void process(List<RowData> rowData) {
        rowData.stream().forEach(router);
    }

    @Override
    public void onCompleted() {
        log.info("ENTER - onCompleted");
    }

    @Override
    public void onError(Throwable throwable) {
        log.info("ENTER - onError: " + throwable.getMessage());
        throwable.printStackTrace();
    }

    @Override
    public void onNext(List<RowData> rowData) {
        log.info("ENTER onNext with " + rowData.size() + " data rows.");
        rowData.stream().forEach(rd -> log.info("ORDER: " + rd.getTypeCode() + " :: " + rd.getIndex()));
        // Try to find kills with no attacker and associate to a missiongameobject
        mapUnmappedKills(rowData);

        rowData.stream().forEach(router);
    }

    private void mapUnmappedKills(List<RowData> rowData) {
        List<? super RowData> unmappedKills =   getAllUnmappedKills(rowData);
        List<BaseHitRowData> hits =            getAllMappedHits(rowData);

        // Iterate over each unmapped kill
        unmappedKills.parallelStream().forEach(rd -> {
            AType3RowData rd3 = (AType3RowData) rd;

            // Find all hits on this object and group them into a KEY -> COUNT(HIT)
            Map<Object, Long> collect = hits.parallelStream()
                    .filter(hit -> rd3.getTargetGameObjectId().equals(hit.getTargetGameObjectId()))
                    .collect(Collectors.groupingBy(BaseHitRowData::getAttackerGameObjectId, Collectors.counting()));

            // Finally push this through a new stream that applies a MAX that evaluates on the COUNT.
            Optional<Map.Entry<Object,Long>> max = collect.entrySet().stream()
                    .max((o1, o2) -> o1.getValue().compareTo(o2.getValue()));

            if(max.isPresent()) {
                Map.Entry<Object, Long> attackerObj = max.get();
                if(attackerObj != null) {
                    rd3.setAttackerGameObjectId((Integer) attackerObj.getKey());
                }
            }
        });
    }

    private List<? super RowData> getAllUnmappedKills(List<RowData> rowData) {
        return (List<? super RowData>) rowData.stream()
                .filter(rd -> rd.getTypeCode() == 3 && ((AType3RowData) rd).getAttackerGameObjectId() == -1)
                .collect(Collectors.toList());
    }

    private List<BaseHitRowData> getAllMappedHits(List<RowData> rowData) {

        List<RowData> l = rowData.stream().filter(
                rd -> (rd.getTypeCode() == 1 || rd.getTypeCode() == 2) && ((BaseHitRowData) rd).getAttackerGameObjectId() != -1).collect(Collectors.toList());
        List<BaseHitRowData> hitList = new ArrayList<>();
        l.stream().forEach(rd -> hitList.add((BaseHitRowData) rd));
        return hitList;
    }




}
