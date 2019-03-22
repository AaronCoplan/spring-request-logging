package com.aaroncoplan.springrequestlogging;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SingleRequestInterceptor extends HandlerInterceptorAdapter {

    private final SingleRequestLogger singleRequestLogger;

    public SingleRequestInterceptor(SingleRequestLogger singleRequestLogger) {
        this.singleRequestLogger = singleRequestLogger;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        RequestUtils.applyTimingStart(request);
        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        RequestData requestData = RequestUtils.extractRequestData(request, response, handler, singleRequestLogger);
        singleRequestLogger.processSingleRequest(requestData);
        super.afterCompletion(request, response, handler, ex);
    }
}
