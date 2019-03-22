package com.aaroncoplan.springrequestlogging;

import java.util.List;

public interface BatchedRequestLogger {

    int getBatchSize();
    void processRequestBatch(List<RequestData> batch);
    // long getMaxBatchAgeMS(); // avoid having batch sit there forever during periods of inactivity
}
