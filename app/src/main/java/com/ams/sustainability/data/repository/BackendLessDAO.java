package com.ams.sustainability.data.repository;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.ams.sustainability.data.common.Defaults;
import com.ams.sustainability.data.common.HistoryResults;
import com.ams.sustainability.data.common.ResultadosListener;
import com.ams.sustainability.model.entities.Resultados;
import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class BackendLessDAO {

    private static final String TABLE_NAME = "Resultados";
    private static final String TAG = BackendLessDAO.class.getSimpleName();

    private BackendlessUser currentUser;

    @SuppressLint("StaticFieldLeak")
    private Context context;

    private static DataQueryBuilder queryBuilder = DataQueryBuilder.create();

    static private LinkedHashMap<String, Float> emissiontable = new LinkedHashMap<>();

    private Resultados resultados;

    //Bundle bundle;

    public BackendLessDAO(Context context) {
        this.context = context;
        initUI();
    }

    public void insertRecordCarbon(double hogar, double alimentacion, double ropa, double tecnologia, double transporte) {

        currentUser = Backendless.UserService.CurrentUser();

        resultados = new Resultados();
        resultados.setHogar(hogar);
        resultados.setAlimentacion(alimentacion);
        resultados.setRopa(ropa);
        resultados.setTecnologia(tecnologia);
        resultados.setTransporte(transporte);
        resultados.setOwnerId(currentUser.getObjectId());

        resultados.saveAsync(new AsyncCallback<Resultados>() {
            @Override
            public void handleResponse(Resultados savedResultados) {

                resultados = savedResultados;
                String objetcId = resultados.getObjectId();
                //bundle = new Bundle();
                //bundle.putString("objetc_Id", objetcId);
                Log.i("***********objetc_Id: ", objetcId);

                //FragmentHome fragmentHome = new FragmentHome();
                //fragmentHome.setArguments(bundle);

                new RetrieveRecordTask().execute(objetcId);

            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(context, fault.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });


    }

    public void getLastRecord(ResultadosListener listener) {

        currentUser = Backendless.UserService.CurrentUser();

        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setWhereClause("ownerId = '" + currentUser.getObjectId() + "'");
        queryBuilder.setPageSize(100);
        Backendless.Data.of(Resultados.class).find(queryBuilder,new AsyncCallback<List<Resultados>>() {
            @Override
            public void handleResponse(List<Resultados> response) {
                Resultados lastRecord = null;
                for (Resultados record : response) {
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

    public void findLastRecord() {
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setPageSize(100);
        Backendless.Data.of(Resultados.class).find(queryBuilder,new AsyncCallback<List<Resultados>>() {
            @Override
            public void handleResponse(List<Resultados> response) {
                Resultados lastRecord = null;
                for (Resultados record : response) {
                    if (lastRecord == null || record.getCreated().getTime() > lastRecord.getCreated().getTime()) {
                        lastRecord = record;
                    }
                }
                if (lastRecord != null) {

                    float hogar = lastRecord.getHogar().floatValue();
                    float ropa = lastRecord.getRopa().floatValue();
                    float alimentacion = lastRecord.getAlimentacion().floatValue();
                    float tecnologia = lastRecord.getTecnologia().floatValue();
                    float transporte = lastRecord.getTransporte().floatValue();

                } else {
                }
            } @Override
            public void handleFault(BackendlessFault fault) {
                Log.e(TAG, "Error al cargar el último registro: " + fault.getMessage());
            }
        });

    }

    public void getHistoryResults(HistoryResults listener) {
        currentUser = Backendless.UserService.CurrentUser();

        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setWhereClause("ownerId = '" + currentUser.getObjectId() + "'");
        queryBuilder.setPageSize(100);
        queryBuilder.setSortBy("created ASC");

        Backendless.Data.of(Resultados.class).find(queryBuilder, new AsyncCallback<List<Resultados>>() {
            @Override
            public void handleResponse(List<Resultados> response) {
                listener.onHistoryRecords(response);
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                listener.onError(fault);
            }
        });
    }


    /*public void remove() {

        currentUser = Backendless.UserService.CurrentUser();

        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setWhereClause("ownerId = '" + currentUser.getObjectId() + "'");

        Backendless.Data.of(Resultados.class).find(queryBuilder, new AsyncCallback<List<Resultados>>() {
            @Override
            public void handleResponse(List<Resultados> response) {
                if (response != null && response.size() > 0) {
                    Backendless.Data.of(Resultados.class).remove("objectId != 'non-existent-objectId'", new AsyncCallback<Integer>() {
                        @Override
                        public void handleResponse(Integer response) {
                            // Éxito: se han eliminado todos los registros de la tabla
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            // Error: no se pudieron eliminar todos los registros de la tabla
                        }
                    });
                } else {
                    // No hay registros que coincidan con la condición de eliminación
                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                // Error: no se pudo realizar la consulta
            }
        });
    }*/

    public void remove() {

        Resultados resultados = new Resultados();

        currentUser = Backendless.UserService.CurrentUser();
        String whereClause = "ownerId = '"+currentUser.getObjectId()+"'"; // condición para eliminar todo

        Log.e("********whereClause: ", whereClause);
        Backendless.Data.of(Resultados.class).remove(whereClause, new AsyncCallback<Integer>() {
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

    class RetrieveRecordTask extends AsyncTask<String, Void, Resultados> {

        @Override
        protected Resultados doInBackground(String... params) {
            String objectId = params[0];

            // Realiza la operación de recuperación de datos en un hilo de fondo
            final Resultados[] result = new Resultados[1];
            Backendless.Data.of(Resultados.class).findById(objectId, new AsyncCallback<Resultados>() {
                @Override
                public void handleResponse(Resultados response) {
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
        protected void onPostExecute(Resultados response) {
            // Utiliza los valores obtenidos de la base de datos como sea necesario
            if (response != null) {
                float hogar = response.getHogar().floatValue();
                float ropa = response.getRopa().floatValue();
                float alimentacion = response.getAlimentacion().floatValue();
                float tecnologia = response.getTecnologia().floatValue();
                float transporte = response.getTransporte().floatValue();

                addRecordBackendless(hogar, alimentacion, transporte, ropa, tecnologia);
                // Actualiza la tabla de emisiones en el hilo de la interfaz de usuario
                /*emissiontable.put("Vivienda", hogar);
                emissiontable.put("Comida", ropa);
                emissiontable.put("Transporte", alimentacion);
                emissiontable.put("ropa", ropa);
                emissiontable.put("Tecnología", tecnologia);*/

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
        Backendless.Data.mapTableToClass(TABLE_NAME, Resultados.class);

    }

    public static LinkedHashMap<String, Float> loadLastRecordSync() {
        final CountDownLatch latch = new CountDownLatch(1);

        Backendless.Data.of(Resultados.class).find(new AsyncCallback<List<Resultados>>() {
            @Override
            public void handleResponse(List<Resultados> response) {
                Resultados lastRecord = null;
                for (Resultados record : response) {
                    if (lastRecord == null || record.getCreated().getTime() > lastRecord.getCreated().getTime()) {
                        lastRecord = record;
                    }
                }
                if (lastRecord != null) {
                    float hogar = lastRecord.getHogar().floatValue();
                    float ropa = lastRecord.getRopa().floatValue();
                    float alimentacion = lastRecord.getAlimentacion().floatValue();
                    float tecnologia = lastRecord.getTecnologia().floatValue();
                    float transporte = lastRecord.getTransporte().floatValue();

                    LinkedHashMap<String, Float> emissiontable = new LinkedHashMap<>();
                    emissiontable.put("Vivienda", hogar);
                    emissiontable.put("Comida", ropa);
                    emissiontable.put("Transporte", alimentacion);
                    emissiontable.put("ropa", ropa);
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
        emissiontable.put("ropa", ropaf);
        emissiontable.put("Tecnología", tecnologiaf);

    }

}