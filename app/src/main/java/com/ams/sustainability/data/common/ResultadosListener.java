package com.ams.sustainability.data;

import com.ams.sustainability.model.entities.Resultados;

public interface ResultadosListener {
    void onLastRecordLoaded(Resultados lastRecord);
    void onError(String errorMessage);
}
