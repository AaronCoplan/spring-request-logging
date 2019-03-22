package com.aaroncoplan.springrequestlogging;

public interface SingleRequestLogger extends RequestLogger {

    void processSingleRequest(RequestData requestData);
}
