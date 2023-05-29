package com.ams.sustainability.data.common;

import com.ams.sustainability.model.graph.entities.Results;

public interface ResultadosListener {
    void onLastRecordLoaded(Results lastRecord);

    void onError(String errorMessage);
}
