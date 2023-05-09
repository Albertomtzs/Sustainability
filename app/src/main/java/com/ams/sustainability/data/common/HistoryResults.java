package com.ams.sustainability.data;

import com.ams.sustainability.model.entities.Resultados;
import com.backendless.exceptions.BackendlessFault;

import java.util.List;

public interface HistoryResults {
    void onHistoryRecords(List<Resultados> resultados);
    void onError(BackendlessFault fault);
}
