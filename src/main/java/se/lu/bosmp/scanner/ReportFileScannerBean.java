package se.lu.bosmp.scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import rx.Observable;
import rx.Observer;
import rx.functions.Action1;
import se.lu.bosmp.controller.MissionParseManager;
import se.lu.bosmp.processor.*;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2014-12-01
 * Time: 20:51
 * To change this template use File | Settings | File Templates.
 */
@Component
public class ReportFileScannerBean implements ReportFileScanner {

    private static final Logger log = LoggerFactory.getLogger(ReportFileScannerBean.class);

    public static final String DEFAULT_SCAN_FOLDER = "c:\\java\\bos-logs";

    private final BasicReportFileFilter fileFilter = new BasicReportFileFilter();

    @Autowired
    MissionParseManager missionParseManager;

    @Autowired
    private Environment env;
    private String reportsFolder;

    @PostConstruct
    public void init() {
        this.reportsFolder = env.getProperty("reports.directory", DEFAULT_SCAN_FOLDER);
        log.info("Set directory '" + this.reportsFolder + "' as reports folder");
    }


    public int scan() {
        log.info("Start scheduled read of reports directory");

        int scanCount = 0;

        List<File> files = Arrays.asList(new File(reportsFolder).listFiles(fileFilter));
        if(files == null ||files.size() == 0) {
            log.warn("Specified report directory '" + reportsFolder + "' is empty or does not exist.");
            return 0;
        }

        final SingleFileProcessor sfp = new SingleFileProcessor();
        final StringToRows stringToRows = new StringToRows();
        final LogProcessor lp = new LogProcessor();

        // RxJava approach
        final long start = System.currentTimeMillis();
        Observable<List<RowData>> rowDataObservable = Observable.from(files)
                .toSortedList((File f1, File f2)
                                -> new Long(f1.lastModified()).compareTo(f2.lastModified()))

                .flatMap(list   -> Observable.from(list))           // From List<List<File>> to List<File>
                .map(f          -> sfp.process(f))                  // Turns each File into a plain string
                .map(s          -> stringToRows.process(s))         // Turns each String into a List of rows
                .map(list       -> lp.process(list))                // Turns each List of row string into a List of row data
                .filter(l       -> l.size() > 0)                    // Discard all empty lists
                .flatMap(list   -> Observable.from(list))           // Map the List<List<RowData>> into a new stream of List<RowData>
                .toSortedList((RowData r1, RowData r2)              // Sort by index so we can process.
                                -> r1.getIndex().compareTo(r2.getIndex()));

        // Important note: Due to the requirement that we process all events in the current "batch" in order,
        // we first flatmap the list of lists and then sort them by their index. A much more effective solution
        // would be to skip the flatmap and sort, then the missionParseManager would not have to wait for all items to be
        // made ready, it could asynchronously persist batch by batch.

        rowDataObservable.subscribe(missionParseManager);





        // JAVA 8 approach.
//        long start = System.currentTimeMillis();
//        List<RowData> collectedRowData = files.stream()                                                   // Create basic stream
//                .sorted((File f1, File f2) -> new Long(f1.lastModified()).compareTo(f2.lastModified()))   // Sort files by last update
//                .map(f -> sfp.process(f))                                                                 // Turn each File into a plain String
//                .map(s -> stringToRows.process(s))                                                        // Turns each String into a List of rows
//                .map(list -> lp.process(list))                                                            // Turns each List of row string into a List of row data
//                .flatMap(list -> list.parallelStream())                                                   // Actually, what we had was List<List<RowData>>. Use flatmap to get List<RowData>
//                .sorted((RowData r1, RowData r2) -> r1.getIndex().compareTo(r2.getIndex()))               // Now we sort the final list by index so events are processed in the correct order.
//                .collect(Collectors.toList());                                                            // Collect!
//        log.info("Remapping from Files to List of RowData took " + (System.currentTimeMillis() - start));
//
//
//        missionParseManager.process(collectedRowData);

        return scanCount;
    }

    public String parseRootName(File f) {
        return f.getName().substring(0, f.getName().length() - 7);

    }

    private String clean(String rootName) {
        return rootName.replaceAll("[^a-zA-Z0-9\\s]", "");
    }
}
