package com.ams.sustainability.data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import com.ams.sustainability.data.common.Defaults;
import com.ams.sustainability.model.entities.Resultados;
import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class BackendLessDAO {

    private static final String TABLE_NAME = "Resultados";
    private static final String TAG = BackendLessDAO.class.getSimpleName();

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

        resultados = new Resultados();

        resultados.setHogar(hogar);
        resultados.setAlimentacion(alimentacion);
        resultados.setRopa(ropa);
        resultados.setTecnologia(tecnologia);
        resultados.setTransporte(transporte);

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

    public void findLastRecord(AsyncCallback<List<Resultados>> callback) {
        Backendless.Data.of(Resultados.class).find(callback);
    }

    public void getLastRecord(ResultadosListener listener) {
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

    public void findAll(AsyncCallback<List<Resultados>> callback) {
        Backendless.Data.of(Resultados.class).find(callback);
    }

    public void getHistoryResults(HistoryResults listener) {
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
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

    /**public LinkedHashMap<String, Float> obtenerTablaEmisionesRecientes () {
     Resultados ultimoRegistro = obtenerUltimo();
     if (ultimoRegistro != null) {
     float hogar = ultimoRegistro.getHogar().floatValue();
     float ropa = ultimoRegistro.getRopa().floatValue();
     float alimentacion = ultimoRegistro.getAlimentacion().floatValue();
     float tecnologia = ultimoRegistro.getTecnologia().floatValue();
     float transporte = ultimoRegistro.getTransporte().floatValue();

     LinkedHashMap<String, Float> emissiontable = new LinkedHashMap<>();
     emissiontable.put("Vivienda", hogar);
     emissiontable.put("Comida", alimentacion);
     emissiontable.put("Transporte", transporte);
     emissiontable.put("ropa", ropa);
     emissiontable.put("Tecnología", tecnologia);

     return emissiontable;
     } else {
     return null;
     }
     }**/

    /*@Override
    protected Resultados doInBackground(String... strings) {
        return null;
    }*/

    public void retrieveRecord (String objectId){

        initUI();

        Backendless.Data.of(Resultados.class).findById(objectId, new AsyncCallback<Resultados>() {
            @Override
            public void handleResponse(Resultados response) {
                if (response != null) {
                    // Los datos de hogar, ropa, alimentación, tecnología y transporte están almacenados
                    // en los campos correspondientes del objeto Resultados
                    double hogar = response.getHogar();
                    double ropa = response.getRopa();
                    double alimentacion = response.getAlimentacion();
                    double tecnologia = response.getTecnologia();
                    double transporte = response.getTransporte();

                    // Utiliza los valores obtenidos de la base de datos como sea necesario
                    Log.i("*****retrieveRecord", "Hogar: " + hogar + " | Ropa: " + ropa + " | Alimentación: " + alimentacion +
                            " | Tecnología: " + tecnologia + " | Transporte: " + transporte);
                } else {
                    Log.e("retrieveRecord", "No se encontró el registro con ID " + objectId);
                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.e("*****retrieveRecord", "Error al recuperar el registro: " + fault.getMessage());
            }
        });
    }

    public void retrieveLastRecordByIdUser () {

        initUI();
        String whereClause = "id_user = '1'";

        // Construir una consulta para buscar el último registro creado por el id_user
        queryBuilder.setWhereClause(whereClause);
        queryBuilder.setSortBy("created DESC");
        queryBuilder.setPageSize(1);

        // Ejecutar la consulta
        Backendless.Data.of(Resultados.class).find(queryBuilder, new AsyncCallback<List<Resultados>>() {
            @Override
            public void handleResponse(List<Resultados> response) {

                for (int i = 0; i < response.size(); i++) {
                    System.out.println("************" + response.get(i).getHogar());
                    System.out.println("************" + response.get(i).getAlimentacion());
                    System.out.println("************" + response.get(i).getRopa());
                    System.out.println("************" + response.get(i).getTecnologia());
                    System.out.println("************" + response.get(i).getTransporte());
                }
                /*if (response.size() > 0) {
                    // Devolver el primer (y único) resultado de la lista
                    System.out.println("************encontraron resultados");

                    //callback.handleResponse(response.get(0));
                } else {
                    System.out.println("************No se encontraron resultados");
                    // Si no se encuentra ningún resultado, devolver null
                    callback.handleResponse(null);
                }*/
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(context, fault.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    /*public void getRecordsByUserId() {

        String userId = Backendless.UserService.CurrentUser().getObjectId();
        String whereClause = "id_user = '" + userId + "'";
        queryBuilder.setWhereClause(whereClause);

        Resultados.find(queryBuilder, new AsyncCallback<List<Resultados>>() {
            @Override
            public void handleResponse(List<Resultados> response) {
                LinkedHashMap<String, Float> data = new LinkedHashMap<>();
                for (Resultados resultado : response) {
                    data.put("Hogar", resultado.getHogar().floatValue());
                    data.put("Ropa", resultado.getRopa().floatValue());
                    data.put("Alimentacion", resultado.getAlimentacion().floatValue());
                    data.put("Transporte", resultado.getTransporte().floatValue());
                    data.put("Tecnologia", resultado.getTecnologia().floatValue());
                }

                // Pasar los datos al gráfico
                ChartBuilder2.buildBarChart7(barChart, context, screenWidth, screenHeight, data);
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(context, fault.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getRecordsByUserId2() {

        String userId = Backendless.UserService.CurrentUser().getUserId();

        Resultados.find(userId, new AsyncCallback<List<Resultados>>() {

            @Override
            public void handleResponse(List<Resultados> response) {
                LinkedHashMap<String, Float> data = new LinkedHashMap<>();
                for (Resultados resultado : response) {
                    data.put("Hogar", resultado.getHogar().floatValue());
                    data.put("Ropa", resultado.getRopa().floatValue());
                    data.put("Alimentacion", resultado.getAlimentacion().floatValue());
                    data.put("Transporte", resultado.getTransporte().floatValue());
                    data.put("Tecnologia", resultado.getTecnologia().floatValue());
                }

                // Pasar los datos al gráfico
                ChartBuilder2.buildBarChart7(barChart, context, screenWidth, screenHeight, data);
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(context, fault.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }*/

    /*public void getRecordsByUserId3(String userId) {

        queryBuilder.setWhereClause("id_user = '" + userId + "'");
        queryBuilder.setSortBy("created DESC");
        queryBuilder.setPageSize(1);

        for (Resultados resultado : Resultados.find(queryBuilder)) {
            LinkedHashMap<String, Float> data = new LinkedHashMap<>();

            data.put("Hogar", resultado.getHogar().floatValue());
            data.put("Ropa", resultado.getRopa().floatValue());
            data.put("Alimentacion", resultado.getAlimentacion().floatValue());
            data.put("Transporte", resultado.getTransporte().floatValue());
            data.put("Tecnologia", resultado.getTecnologia().floatValue());
        }

    }

    public LinkedHashMap<String, Float> getRecordsByUserIdNuevo(int userId) {

        LinkedHashMap<String, Float> data = new LinkedHashMap<>();

        queryBuilder = DataQueryBuilder.create();
        queryBuilder.setWhereClause("id_user = '" + userId + "'");
        queryBuilder.setSortBy("created DESC");
        queryBuilder.setPageSize(1);

        Resultados.find(queryBuilder).forEach(resultado -> {
            data.put("Hogar", resultado.getHogar().floatValue());
            data.put("Ropa", resultado.getRopa().floatValue());
            data.put("Alimentacion", resultado.getAlimentacion().floatValue());
            data.put("Transporte", resultado.getTransporte().floatValue());
            data.put("Tecnologia", resultado.getTecnologia().floatValue());
        });

        return data;
    }

    public interface RecordsByUserIdCallback {
        void onRecordsByUserIdLoaded(LinkedHashMap<String, Float> data);
    }

    public void getRecordsByUserId2(int userId, RecordsByUserIdCallback callback) {
        queryBuilder = DataQueryBuilder.create();
        queryBuilder.setWhereClause("id_user = '" + userId + "'");
        queryBuilder.setSortBy("created DESC");
        queryBuilder.setPageSize(1);

        Resultados.find2(queryBuilder, new AsyncCallback<List<Resultados>>() {
            @Override
            public void handleResponse(List<Resultados> response) {
                LinkedHashMap<String, Float> data = new LinkedHashMap<>();

                // Si se encontró un registro, agregar los datos al mapa
                if (!response.isEmpty()) {
                    Resultados resultado = response.get(0);
                    data.put("Hogar", resultado.getHogar().floatValue());
                    data.put("Ropa", resultado.getRopa().floatValue());
                    data.put("Alimentacion", resultado.getAlimentacion().floatValue());
                    data.put("Transporte", resultado.getTransporte().floatValue());
                    data.put("Tecnologia", resultado.getTecnologia().floatValue());
                }

                callback.onRecordsByUserIdLoaded(data);
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(context, fault.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }*/
/*
    @NonNull
    public static LinkedHashMap<String, Float> getRecordsByUserId(int userId) {

        LinkedHashMap<String, Float> data = new LinkedHashMap<>();

        //DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setWhereClause("id_user = '" + userId + "'");
        queryBuilder.setSortBy("created DESC");
        queryBuilder.setPageSize(1);

        Resultados.find2(queryBuilder, new AsyncCallback<List<Resultados>>() {
            @Override
            public void handleResponse(List<Resultados> response) {

                if (!response.isEmpty()) {
                    Resultados resultado = response.get(0);
                    data.put("Hogar", resultado.getHogar().floatValue());
                    data.put("Ropa", resultado.getRopa().floatValue());
                    data.put("Alimentacion", resultado.getAlimentacion().floatValue());
                    data.put("Transporte", resultado.getTransporte().floatValue());
                    data.put("Tecnologia", resultado.getTecnologia().floatValue());
                }

            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(context, fault.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return data;
    }

    public void getSortedResultadosRetrievalCode() {

        QueryOptions queryOptions = new QueryOptions();
                List<String> sortByProperties = new ArrayList<String>();
                sortByProperties.add("created DESC");
                queryOptions.setSortBy(sortByProperties);
                BackendlessDataQuery query = new BackendlessDataQuery();
                query.setQueryOptions( queryOptions );
                Resultados.findAsync(query, new AsyncCallback<List<Resultados>>()
                {
                @Override
                public void handleResponse( List<Resultados> response )
                {
                Resultados firstSortedResultados = response.get( 0 );
                }
                @Override
                public void handleFault( BackendlessFault fault )
                {
                Toast.makeText(context, fault.getMessage(), Toast.LENGTH_SHORT ).show();
                }
                } );

    }*/

    /*public class RetrieveRecordTask extends AsyncTask<String, Void, Resultados> {

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
                double hogar = response.getHogar();
                double ropa = response.getRopa();
                double alimentacion = response.getAlimentacion();
                double tecnologia = response.getTecnologia();
                double transporte = response.getTransporte();

                Log.i("retrieveRecord", "Hogar: " + hogar + " | Ropa: " + ropa + " | Alimentación: " + alimentacion +
                        " | Tecnología: " + tecnologia + " | Transporte: " + transporte);
            } else {
                Log.e("retrieveRecord", "No se encontró el registro con ID D093994F-4CAE-4E28-BA4A-62B5269ABEB0 ");
            }
        }
    }*/

    /*class RetrieveRecordTask extends AsyncTask<String, Void, Resultados> {

        @Override
        protected Resultados doInBackground(String ... params) {
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

                //Thread.sleep(7000);
                emissiontable.put("Vivienda", hogar);
                emissiontable.put("Comida", ropa);
                emissiontable.put("Transporte", alimentacion);
                emissiontable.put("ropa", ropa);
                emissiontable.put("Tecnología", tecnologia);

                Log.i("retrieveRecord", "Hogar: " + 0 + " | Ropa: " + 0 + " | Alimentación: " + 0 +
                        " | Tecnología: " + 0 + " | Transporte: " + 0);
            } else {
                Log.e("retrieveRecord", "No se encontró el registro con ID D093994F-4CAE-4E28-BA4A-62B5269ABEB0 ");
            }

        }
    }*/

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


    private void initUI () {

        Backendless.setUrl(Defaults.SERVER_URL);
        Backendless.initApp(context, Defaults.APPLICATION_ID, Defaults.API_KEY);
        Backendless.Data.mapTableToClass(TABLE_NAME, Resultados.class);

    }

    /*public static LinkedHashMap<String, Float> loadLastRecord() {
        // Configurar una consulta para ordenar por fecha en orden descendente y limitar a 1 resultado
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setSortBy("created DESC");
        queryBuilder.setPageSize(1);

        // Ejecutar la consulta
        Backendless.Data.of(Resultados.class).find(queryBuilder, new AsyncCallback<List<Resultados>>() {
            @Override
            public void handleResponse(List<Resultados> response) {
                if (!response.isEmpty()) {
                    // El primer (y único) resultado es el último registro creado
                    Resultados lastRecord = response.get(0);
                    float hogar = lastRecord.getHogar().floatValue();
                    float ropa = lastRecord.getRopa().floatValue();
                    float alimentacion = lastRecord.getAlimentacion().floatValue();
                    float tecnologia = lastRecord.getTecnologia().floatValue();
                    float transporte = lastRecord.getTransporte().floatValue();

                    addRecordBackendless(hogar, alimentacion, transporte, ropa, tecnologia);;
                    // Utiliza los valores del registro como sea necesario
                    Log.e("****loadLastRecord", "Se encontró registro:" + lastRecord.getObjectId());
                    // ...
                } else {
                    Log.e("****loadLastRecord", "No se encontró ningún registro");
                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.e("loadLastRecord", "Error al cargar el último registro: " + fault.getMessage());
            }
        });
        return emissiontable;
    }*/

    public static LinkedHashMap<String, Float> loadLastRecordSync () {
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


    public static void addRecordBackendless (Float hogarf, Float comidaf, Float
            transportef, Float ropaf, Float tecnologiaf){
        emissiontable.put("Vivienda", hogarf);
        emissiontable.put("Comida", comidaf);
        emissiontable.put("Transporte", transportef);
        emissiontable.put("ropa", ropaf);
        emissiontable.put("Tecnología", tecnologiaf);

    }

    public static LinkedHashMap getEmissionTable () {
        return emissiontable;
    }


}