package com.aaroncoplan.springrequestlogging;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class RequestUtils {

    private final static String TIMING_ATTRIBUTE = "REQUEST_START_TIME_MILLIS";

    static void applyTimingStart(HttpServletRequest request) {
        request.setAttribute(TIMING_ATTRIBUTE, System.currentTimeMillis());
    }

    static RequestData extractRequestData(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex, RequestLogger requestLogger) {
        final long completionTime = System.currentTimeMillis();
        final long startTime = (long)request.getAttribute(TIMING_ATTRIBUTE);
        return new RequestData(
                response.getStatus(),
                completionTime - startTime,
                requestLogger.extractRequestPattern(handler),
                request.getRequestURI(),
                request.getMethod(),
                ex != null
        );
    }
}
