package com.aaroncoplan.springrequestlogging;

public class BatchedRequestInterceptor {

    private final BatchedRequestLogger batchedRequestLogger;

    public BatchedRequestInterceptor(BatchedRequestLogger batchedRequestLogger) {
        this.batchedRequestLogger = batchedRequestLogger;
    }


}
