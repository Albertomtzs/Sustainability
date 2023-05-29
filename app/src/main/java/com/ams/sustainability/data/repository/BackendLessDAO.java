package com.ams.sustainability.data.repository;

import static android.app.PendingIntent.getActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.ams.sustainability.data.common.Defaults;
import com.ams.sustainability.data.common.HistoryResults;
import com.ams.sustainability.data.common.ResultadosListener;
import com.ams.sustainability.model.graph.entities.Results;
import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessException;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class BackendLessDAO {

    private static final String TABLE_NAME = "Results";
    private static final String TAG = BackendLessDAO.class.getSimpleName();
    private BackendlessUser currentUser;
    @SuppressLint("StaticFieldLeak")
    private Context context;

    private static DataQueryBuilder queryBuilder = DataQueryBuilder.create();

    static private LinkedHashMap<String, Float> emissiontable = new LinkedHashMap<>();

    private Results results;

    public BackendLessDAO(Context context) {
        this.context = context;
        initUI();
    }

    public void insertRecordCarbon(double hogar, double alimentacion, double ropa, double tecnologia, double transporte) {

        try {

            currentUser = Backendless.UserService.CurrentUser();

            results = new Results();
            results.setHouse(hogar);
            results.setFood(alimentacion);
            results.setClothes(ropa);
            results.setTechonology(tecnologia);
            results.setTransport(transporte);
            if (currentUser != null) {
                results.setOwnerId(currentUser.getObjectId());
            }

            results.saveAsync(new AsyncCallback<Results>() {
                @Override
                public void handleResponse(Results savedResultados) {

                    results = savedResultados;
                    String objetcId = results.getObjectId();

                    Log.i("***********objetc_Id: ", objetcId);

                    new RetrieveRecordTask().execute(objetcId);

                }

                @Override
                public void handleFault(BackendlessFault fault) {
                    Toast.makeText(context, fault.getMessage(), Toast.LENGTH_SHORT).show();
                }

            });
        } catch (BackendlessException e) {
            Log.e(TAG, "Error al insertar registro: " + e.getMessage());
        }

    }

    public void getLastRecord(ResultadosListener listener) {

        currentUser = Backendless.UserService.CurrentUser();

        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setWhereClause("ownerId = '" + currentUser.getObjectId() + "'");
        queryBuilder.setPageSize(100);
        Backendless.Data.of(Results.class).find(queryBuilder, new AsyncCallback<List<Results>>() {
            @Override
            public void handleResponse(List<Results> response) {
                Results lastRecord = null;
                for (Results record : response) {
                    if (lastRecord == null || record.getCreated().getTime() > lastRecord.getCreated().getTime()) {
                        lastRecord = record;
                    }
                }
                if (lastRecord != null) {
                    listener.onLastRecordLoaded(lastRecord);
                } else {
                    listener.onError("No se encontró ningún registro.");
                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.e(TAG, "Error al cargar el último registro: " + fault.getMessage());
                listener.onError("Error al cargar el último registro.");
            }
        });
    }

    /*public void findLastRecord() {
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setPageSize(100);
        Backendless.Data.of(Results.class).find(queryBuilder, new AsyncCallback<List<Results>>() {
            @Override
            public void handleResponse(List<Results> response) {
                Results lastRecord = null;
                for (Results record : response) {
                    if (lastRecord == null || record.getCreated().getTime() > lastRecord.getCreated().getTime()) {
                        lastRecord = record;
                    }
                }
                if (lastRecord != null) {

                    float hogar = lastRecord.getHouse().floatValue();
                    float ropa = lastRecord.getClothes().floatValue();
                    float alimentacion = lastRecord.getFood().floatValue();
                    float tecnologia = lastRecord.getTechonology().floatValue();
                    float transporte = lastRecord.getTransport().floatValue();

                } else {
                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.e(TAG, "Error al cargar el último registro: " + fault.getMessage());
            }
        });

    }*/

    public void getHistoryResults(HistoryResults listener) {
        currentUser = Backendless.UserService.CurrentUser();

        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setWhereClause("ownerId = '" + currentUser.getObjectId() + "'");
        queryBuilder.setPageSize(100);
        queryBuilder.setSortBy("created ASC");

        Backendless.Data.of(Results.class).find(queryBuilder, new AsyncCallback<List<Results>>() {
            @Override
            public void handleResponse(List<Results> response) {
                listener.onHistoryRecords(response);
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                listener.onError(fault);
            }
        });
    }

    public void remove() {

        Results resultados = new Results();

        currentUser = Backendless.UserService.CurrentUser();
        String whereClause = "ownerId = '" + currentUser.getObjectId() + "'"; // condición para eliminar todo

        Log.e("********whereClause: ", whereClause);
        Backendless.Data.of(Results.class).remove(whereClause, new AsyncCallback<Integer>() {
            @Override
            public void handleResponse(Integer response) {
                // Éxito: se han eliminado todos los registros de la tabla
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                // Error: no se pudieron eliminar todos los registros de la tabla
            }
        });
    }

    class RetrieveRecordTask extends AsyncTask<String, Void, Results> {

        @Override
        protected Results doInBackground(String... params) {
            String objectId = params[0];

            // Realiza la operación de recuperación de datos en un hilo de fondo
            final Results[] result = new Results[1];
            Backendless.Data.of(Results.class).findById(objectId, new AsyncCallback<Results>() {
                @Override
                public void handleResponse(Results response) {
                    result[0] = response;
                }

                @Override
                public void handleFault(BackendlessFault fault) {
                    Log.e("retrieveRecord", "Error al recuperar el registro: " + fault.getMessage());
                }
            });

            // Espera a que se complete la operación de red y devuelve el resultado
            while (result[0] == null) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return result[0];
        }

        @Override
        protected void onPostExecute(Results response) {
            // Utiliza los valores obtenidos de la base de datos como sea necesario
            if (response != null) {
                float hogar = response.getHouse().floatValue();
                float ropa = response.getClothes().floatValue();
                float alimentacion = response.getFood().floatValue();
                float tecnologia = response.getTechonology().floatValue();
                float transporte = response.getTransport().floatValue();

                addRecordBackendless(hogar, alimentacion, transporte, ropa, tecnologia);

                Log.i("retrieveRecord", "Hogar: " + hogar + " | Ropa: " + ropa + " | Alimentación: " + alimentacion +
                        " | Tecnología: " + tecnologia + " | Transporte: " + transporte);
            } else {
                Log.e("retrieveRecord", "No se encontró el registro con ID D093994F-4CAE-4E28-BA4A-62B5269ABEB0 ");
            }
        }
    }

    private void initUI() {

        Backendless.setUrl(Defaults.SERVER_URL);
        Backendless.initApp(context, Defaults.APPLICATION_ID, Defaults.API_KEY);
        Backendless.Data.mapTableToClass(TABLE_NAME, Results.class);

    }

    public static LinkedHashMap<String, Float> loadLastRecordSync() {
        final CountDownLatch latch = new CountDownLatch(1);

        Backendless.Data.of(Results.class).find(new AsyncCallback<List<Results>>() {
            @Override
            public void handleResponse(List<Results> response) {
                Results lastRecord = null;
                for (Results record : response) {
                    if (lastRecord == null || record.getCreated().getTime() > lastRecord.getCreated().getTime()) {
                        lastRecord = record;
                    }
                }
                if (lastRecord != null) {
                    float hogar = lastRecord.getHouse().floatValue();
                    float ropa = lastRecord.getClothes().floatValue();
                    float alimentacion = lastRecord.getFood().floatValue();
                    float tecnologia = lastRecord.getTechonology().floatValue();
                    float transporte = lastRecord.getTransport().floatValue();

                    LinkedHashMap<String, Float> emissiontable = new LinkedHashMap<>();
                    emissiontable.put("Vivienda", hogar);
                    emissiontable.put("Comida", ropa);
                    emissiontable.put("Transporte", alimentacion);
                    emissiontable.put("Ropa", ropa);
                    emissiontable.put("Tecnología", tecnologia);

                    addRecordBackendless(hogar, alimentacion, transporte, ropa, tecnologia);
                }

                latch.countDown();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.e("loadLastRecordSync", "Error al cargar el último registro: " + fault.getMessage());
                latch.countDown();
            }
        });

        try {
            latch.await();
        } catch (InterruptedException e) {
            Log.e("loadLastRecordSync", "El hilo fue interrumpido mientras esperaba por el recuento de cierre: " + e.getMessage());
        }

        return emissiontable;
    }

    public static void addRecordBackendless(Float hogarf, Float comidaf, Float transportef, Float ropaf, Float tecnologiaf) {
        emissiontable.put("Vivienda", hogarf);
        emissiontable.put("Comida", comidaf);
        emissiontable.put("Transporte", transportef);
        emissiontable.put("Ropa", ropaf);
        emissiontable.put("Tecnología", tecnologiaf);

    }

}