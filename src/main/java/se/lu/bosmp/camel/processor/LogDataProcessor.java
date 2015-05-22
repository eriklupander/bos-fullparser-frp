package se.lu.bosmp.camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rx.Observable;
import rx.functions.Func1;
import rx.functions.Func2;
import se.lu.bosmp.controller.MissionParseManager;
import se.lu.bosmp.processor.LogProcessor;
import se.lu.bosmp.processor.RowData;
import se.lu.bosmp.scanner.FileRowData;
import se.lu.bosmp.util.NaturalOrderComparator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by eriklupander on 2015-05-19.
 */
@Component
public class LogDataProcessor implements Processor {

    private static final Logger log = LoggerFactory.getLogger(LogDataProcessor.class);

    @Autowired
    LogProcessor lp;

    @Autowired
    MissionParseManager missionParseManager;

    Func2<FileRowData, FileRowData, Integer> sort = (FileRowData s1, FileRowData s2) ->
        new NaturalOrderComparator().compare(s1.getFileName(), s2.getFileName());


    @Override
    public void process(Exchange exchange) throws Exception {
        if ( !(exchange.getIn().getBody() instanceof ArrayList)) {
            log.error("Could not parse message body, not an instance of FileRowData");
             return;
        }

        List<FileRowData> fileRowData = (List<FileRowData>) exchange.getIn().getBody();
        Observable<List<RowData>> rowDataObservable = Observable.from(fileRowData)
                .toSortedList(sort)
                .flatMap(frd -> Observable.from(frd))
                .flatMap(frd -> Observable.from(lp.process(frd)))
                .toSortedList((RowData r1, RowData r2)                          // Sort by index so we can process.
                        -> r1.getTime().compareTo(r2.getTime()));

        //Observable<List<RowData>> rowDataObservable = Observable.from(lp.process(fileRowData))
        //        .toSortedList((RowData r1, RowData r2)                          // Sort by index so we can process.
        //                -> r1.getIndex().compareTo(r2.getIndex()));

        rowDataObservable.subscribe(missionParseManager);


//        List<RowData> collectedRowData = lp.process(fileRowData).stream()                                // Actually, what we had was List<List<RowData>>. Use flatmap to get List<RowData>
//                .sorted((RowData r1, RowData r2) -> r1.getIndex().compareTo(r2.getIndex()))               // Now we sort the final list by index so events are processed in the correct order.
//                .collect(Collectors.toList());
//
//        missionParseManager.process(collectedRowData);
        log.info("Successfully processed " + fileRowData.size() + " aggregated files "
                + exchange.getIn().getHeader("filename"));
    }
}
