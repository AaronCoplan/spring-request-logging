package com.aaroncoplan;

import com.aaroncoplan.springrequestlogging.RequestData;
import com.aaroncoplan.springrequestlogging.SingleRequestLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BenchmarkSingleRequestLogger implements SingleRequestLogger {

    private final Logger logger = LoggerFactory.getLogger(BenchmarkSingleRequestLogger.class);

    @Override
    public void processSingleRequest(RequestData requestData) {
        String requestInfo = requestData.toString();
        // logger.info(requestData.toString()); skipping logger to avoid performance hit
    }
}
