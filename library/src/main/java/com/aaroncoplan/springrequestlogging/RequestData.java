package com.aaroncoplan.springrequestlogging;

public class RequestData {

    private final int httpResponseCode;
    private final long executionTimeMS;
    private final String requestPattern, requestURL, requestMethod;
    private final boolean hasException;

    RequestData(int httpResponseCode, long executionTimeMS, String requestPattern, String requestURL, String requestMethod, boolean hasException) {
        this.httpResponseCode = httpResponseCode;
        this.executionTimeMS = executionTimeMS;
        this.requestPattern = requestPattern;
        this.requestURL = requestURL;
        this.requestMethod = requestMethod;
        this.hasException = hasException;
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

    public boolean getHasException() {
        return hasException;
    }

    @Override
    public String toString() {
        return "RequestData{" +
                "requestMethod='" + requestMethod + '\'' +
                ", requestURL='" + requestURL + '\'' +
                ", requestPattern='" + requestPattern + '\'' +
                ", httpResponseCode=" + httpResponseCode +
                ", executionTimeMS=" + executionTimeMS +
                ", hasException=" + hasException +
                '}';
    }
}
