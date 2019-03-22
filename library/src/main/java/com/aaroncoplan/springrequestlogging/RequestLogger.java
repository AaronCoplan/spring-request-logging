package com.aaroncoplan.springrequestlogging;

public interface RequestLogger {

    default String extractRequestPattern(Object handler) {
        return null;
    }
}
