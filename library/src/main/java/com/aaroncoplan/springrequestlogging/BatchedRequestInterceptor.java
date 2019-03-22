package com.aaroncoplan.springrequestlogging;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class BatchedRequestInterceptor extends HandlerInterceptorAdapter {

    private final BatchedRequestLogger batchedRequestLogger;
    private final Object lock;
    private RequestData[] batchData;
    private int index;

    BatchedRequestInterceptor(BatchedRequestLogger batchedRequestLogger) {
        this.batchedRequestLogger = batchedRequestLogger;
        this.batchData = new RequestData[batchedRequestLogger.getBatchSize()];
        this.lock = new Object();
        this.index = 0;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        RequestUtils.applyTimingStart(request);
        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        RequestData requestData = RequestUtils.extractRequestData(request, response, handler, ex, batchedRequestLogger);

        List<RequestData> batchToProcess = null;
        synchronized (lock) {
            batchData[index] = requestData;
            ++index;
            if(index == batchedRequestLogger.getBatchSize()) {
                // process the batch and reinit the array
                // this allows us to exit the CS faster
                // we absolutely don't want to process the batch in the CS
                batchToProcess = Arrays.asList(batchData);
                batchData = new RequestData[batchedRequestLogger.getBatchSize()];
                index = 0;
            }
        }

        // check if there is a batch to process
        // if there is, call the process function
        if(batchToProcess != null) {
            batchedRequestLogger.processRequestBatch(batchToProcess);
        }
        super.afterCompletion(request, response, handler, ex);
    }
}
