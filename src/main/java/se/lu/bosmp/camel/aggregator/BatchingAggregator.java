package se.lu.bosmp.camel.aggregator;

import java.util.ArrayList;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.springframework.stereotype.Component;

import se.lu.bosmp.scanner.FileRowData;

/**
 * Created by eriklupander on 2015-05-19.
 */
@Component
public class BatchingAggregator implements AggregationStrategy {


    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {


        FileRowData newBody = (FileRowData) newExchange.getIn().getBody();
        ArrayList<FileRowData> list = null;
        if (oldExchange == null) {
            list = new ArrayList<>();
            list.add(newBody);
            newExchange.getIn().setBody(list);
            return newExchange;
        } else {
            list = oldExchange.getIn().getBody(ArrayList.class);
            list.add(newBody);
            return oldExchange;
        }
    }
}
