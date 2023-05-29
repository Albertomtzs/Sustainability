package com.ams.sustainability.data.common;

import com.ams.sustainability.model.graph.entities.Results;
import com.backendless.exceptions.BackendlessFault;

import java.util.List;

public interface HistoryResults {
    void onHistoryRecords(List<Results> resultados);

    void onError(BackendlessFault fault);
}
