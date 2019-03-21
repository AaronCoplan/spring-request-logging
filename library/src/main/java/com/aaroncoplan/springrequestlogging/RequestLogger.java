package com.aaroncoplan.springrequestlogging;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestLogger extends HandlerInterceptorAdapter {

    private final String TIMING_ATTRIBUTE = "REQUEST_START_TIME_MILLIS";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute(TIMING_ATTRIBUTE, System.currentTimeMillis());
        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        final long completionTime = System.currentTimeMillis();
        final long startTime = (long)request.getAttribute(TIMING_ATTRIBUTE);
        final String requestURL = request.getRequestURI();
        System.out.println(completionTime-startTime);
        System.out.println(requestURL);
        super.afterCompletion(request, response, handler, ex);
    }
}