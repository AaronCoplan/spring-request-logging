package com.aaroncoplan;

import com.aaroncoplan.springrequestlogging.BatchedRequestLogger;
import com.aaroncoplan.springrequestlogging.RequestData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class BenchmarkBatchedRequestLogger implements BatchedRequestLogger {

    private final Logger logger = LoggerFactory.getLogger(BenchmarkBatchedRequestLogger.class);

    @Override
    public int getBatchSize() {
        return 10;
    }

    @Override
    public void processRequestBatch(List<RequestData> batch) {
        String batchInfo = batch.toString();
        // logger.info(batch.toString()); skipping logger to avoid performance hit
    }
}
