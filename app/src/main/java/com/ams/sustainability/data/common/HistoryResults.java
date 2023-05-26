package com.ams.sustainability.data.common;

import com.ams.sustainability.model.entities.Results;
import com.backendless.exceptions.BackendlessFault;

import java.util.List;

public interface HistoryResults {
    void onHistoryRecords(List<Results> resultados);

    void onError(BackendlessFault fault);
}
