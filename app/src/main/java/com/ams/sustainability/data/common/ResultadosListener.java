package com.ams.sustainability.data.common;

import com.ams.sustainability.model.entities.Results;

public interface ResultadosListener {
    void onLastRecordLoaded(Results lastRecord);

    void onError(String errorMessage);
}
