package com.aaroncoplan.springrequestlogging;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestUtils {

    private final static String TIMING_ATTRIBUTE = "REQUEST_START_TIME_MILLIS";

    public static void applyTimingStart(HttpServletRequest request) {
        request.setAttribute(TIMING_ATTRIBUTE, System.currentTimeMillis());
    }

    public static RequestData extractRequestData(HttpServletRequest request, HttpServletResponse response) {
        final long completionTime = System.currentTimeMillis();
        final long startTime = (long)request.getAttribute(TIMING_ATTRIBUTE);
        return new RequestData(
                response.getStatus(),
                completionTime - startTime,
                null,
                request.getRequestURI(),
                request.getMethod()
        );
    }
}
