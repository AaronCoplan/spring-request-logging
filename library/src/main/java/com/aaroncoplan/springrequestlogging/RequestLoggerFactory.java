package com.aaroncoplan.springrequestlogging;

public class RequestLoggerFactory {

    public static SingleRequestInterceptor buildSingleRequestLogger(SingleRequestLogger singleRequestLogger) {
        return new SingleRequestInterceptor(singleRequestLogger);
    }

    public static BatchedRequestInterceptor buildBatchedRequestLogger(BatchedRequestLogger batchedRequestLogger) {
        return new BatchedRequestInterceptor();
    }
}
