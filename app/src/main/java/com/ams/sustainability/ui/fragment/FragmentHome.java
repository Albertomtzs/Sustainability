package com.ams.myapplication.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import com.ams.myapplication.ChartBuilder2;
import com.ams.myapplication.R;
import com.ams.myapplication.data.Resultados;
import com.ams.myapplication.data.ResultadosDAO;
import com.ams.myapplication.data.ResultadosListener;
import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import java.util.LinkedHashMap;
import java.util.List;

public class FragmentHome extends Fragment implements ResultadosListener {

    private TextView label;
    private TextView top;
    private TextView ukAvgText;
    private TextView ukAvgFig;
    private TextView goalText;
    private TextView goalFig;

    private String seccionesTotal;
    private double emissiomResult;
    private BarChart barChart;
    private PieChart pieChart;
    private ResultadosDAO resultadosDAO;
    private Context context;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_results_page, container, false);

        context = view.getContext();
        resultadosDAO = new ResultadosDAO(context);
        barChart = view.findViewById(R.id.barChart);
        pieChart = view.findViewById(R.id.pieChart);

        resultadosDAO.getLastRecord(this);

        return view;
    }

    @Override
    public void onLastRecordLoaded(Resultados lastRecord) {
        float hogar = lastRecord.getHogar().floatValue();
        float ropa = lastRecord.getRopa().floatValue();
        float alimentacion = lastRecord.getAlimentacion().floatValue();
        float tecnologia = lastRecord.getTecnologia().floatValue();
        float transporte = lastRecord.getTransporte().floatValue();

        LinkedHashMap<String, Float> emissiontable = new LinkedHashMap<>();
        emissiontable.put("Vivienda", hogar);
        emissiontable.put("Comida", alimentacion);
        emissiontable.put("Transporte", transporte);
        emissiontable.put("ropa", ropa);
        emissiontable.put("Tecnología", tecnologia);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenheight = displayMetrics.heightPixels;
        int screenwidth = displayMetrics.widthPixels;



        ChartBuilder2.buildBarChart7(barChart, getContext(), screenwidth, screenheight, emissiontable);
        ChartBuilder2.buildPieChart2(pieChart, getContext(), screenwidth, screenheight, emissiontable);
    }

    @Override
    public void onError(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }

}

        /*Context context = view.getContext();

        ResultadosDAO resultadosDAO = new ResultadosDAO(context);

        BarChart barChart = view.findViewById(R.id.barChart);
        PieChart pieChart = view.findViewById(R.id.pieChart);

        resultadosDAO.findLastRecord(new AsyncCallback<List<Resultados>>() {
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
                    emissiontable.put("Comida", alimentacion);
                    emissiontable.put("Transporte", transporte);
                    emissiontable.put("ropa", ropa);
                    emissiontable.put("Tecnología", tecnologia);

                    DisplayMetrics displayMetrics = new DisplayMetrics();
                    getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                    int screenheight = displayMetrics.heightPixels;
                    int screenwidth = displayMetrics.widthPixels;

                    ChartBuilder2.buildBarChart7(barChart, getContext(), screenwidth, screenheight, emissiontable);
                    ChartBuilder2.buildPieChart2(pieChart, getContext(), screenwidth, screenheight, emissiontable);

                    //addTotal(emissiomResult);

                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }

            private void addTotal(double emissiomResult) {
                top.setText("Your annual C02 Emissions:");
                label.setText(emissiomResult + "t");
            }

        });

        return view;
    }
}*/













        /*Context context = view.getContext();

        ResultadosDAO resultadosDAO = new ResultadosDAO(context);

        BarChart barChart = view.findViewById(R.id.barChart);
        PieChart pieChart = view.findViewById(R.id.pieChart);

        LinkedHashMap<String, Float> emissiontable = resultadosDAO.obtenerTablaEmisionesRecientes();

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenheight = displayMetrics.heightPixels;
        int screenwidth = displayMetrics.widthPixels;

        ChartBuilder2.buildBarChart7(barChart, getContext(), screenwidth, screenheight, emissiontable);
        ChartBuilder2.buildPieChart2(pieChart, getContext(), screenwidth, screenheight, emissiontable);

        //addTotal(emissiomResult);

        return view;*/














        /*Context context = view.getContext();

        ResultadosDAO resultadosDAO = new ResultadosDAO(context);

        top = view.findViewById(R.id.textResult);
        label = view.findViewById(R.id.textTotal);
        ukAvgText = view.findViewById(R.id.textAverage);
        ukAvgFig = view.findViewById(R.id.textAverageFigure);
        goalText = view.findViewById(R.id.textGoal);
        goalFig = view.findViewById(R.id.textGoalFigure);

        BarChart barChart = view.findViewById(R.id.barChart);
        PieChart pieChart = view.findViewById(R.id.pieChart);

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
                    emissiontable.put("Comida", alimentacion);
                    emissiontable.put("Transporte", transporte);
                    emissiontable.put("ropa", ropa);
                    emissiontable.put("Tecnología", tecnologia);


                    DisplayMetrics displayMetrics = new DisplayMetrics();
                    getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                    int screenheight = displayMetrics.heightPixels;
                    int screenwidth = displayMetrics.widthPixels;

                    ChartBuilder2.buildBarChart7(barChart, getContext(), screenwidth, screenheight, emissiontable);
                    ChartBuilder2.buildPieChart2(pieChart, getContext(), screenwidth, screenheight, emissiontable);

                    emissiomResult = 7.1;

                    addTotal(emissiomResult);

                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }

            private void addTotal(double emissiomResult) {
                top.setText("Your annual C02 Emissions:");
                label.setText(emissiomResult + "t");
            }
        });

        return view;
    }*/
