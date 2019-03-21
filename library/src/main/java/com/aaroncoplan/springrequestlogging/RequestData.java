package com.aaroncoplan.springrequestlogging;

public class RequestData {

    private final int httpResponseCode;
    private final long executionTimeMS;
    private final String requestPattern, requestURL, requestMethod;

    public RequestData(int httpResponseCode, long executionTimeMS, String requestPattern, String requestURL, String requestMethod) {
        this.httpResponseCode = httpResponseCode;
        this.executionTimeMS = executionTimeMS;
        this.requestPattern = requestPattern;
        this.requestURL = requestURL;
        this.requestMethod = requestMethod;
    }

    public int getHttpResponseCode() {
        return httpResponseCode;
    }

    public long getExecutionTimeMS() {
        return executionTimeMS;
    }

    public String getRequestPattern() {
        return requestPattern;
    }

    public String getRequestURL() {
        return requestURL;
    }

    public String getRequestMethod() {
        return requestMethod;
    }
}
