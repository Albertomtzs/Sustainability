package com.ams.sustainability.ui.fragment;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.ams.sustainability.R;
import com.ams.sustainability.data.common.HistoryResults;
import com.ams.sustainability.data.repository.BackendLessDAO;
import com.ams.sustainability.model.entities.Resultados;
import com.ams.sustainability.model.graph.ChartBuilder;
import com.backendless.exceptions.BackendlessFault;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarEntry;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;


public class FragmentHistorial extends Fragment implements HistoryResults {

    private BarChart barChart;
    private LineChart lineChart;
    private BackendLessDAO backendLessDAO;
    private Context context;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_historial, container, false);

        context = view.getContext();
        backendLessDAO = new BackendLessDAO(context);
        backendLessDAO.getHistoryResults(this);
        barChart = view.findViewById(R.id.barChart);
        lineChart = view.findViewById(R.id.lineChart);

        return view;
    }

    @Override
    public void onHistoryRecords(List<Resultados> resultados) {

        try {


            LinkedHashMap<String, Float> emissiontable = new LinkedHashMap<>();
            ArrayList<BarEntry> barEntries = new ArrayList<>();
            ArrayList<String> labels = new ArrayList<>();

            DisplayMetrics displayMetrics = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int screenHeight = displayMetrics.heightPixels;
            int screenWidth = displayMetrics.widthPixels;

            for (int i = 0; i < resultados.size(); i++) {
                String inputDate = String.valueOf(resultados.get(i).getCreated());
                String outputDate = convertStringFormat(inputDate);
                double hogar = resultados.get(i).getHogar();
                double alimentacion = resultados.get(i).getAlimentacion();
                double ropa = resultados.get(i).getRopa();
                double transporte = resultados.get(i).getTransporte();
                double tecnologia = resultados.get(i).getTecnologia();

                Log.e("****MainActivity", "Fecha entrada: " + inputDate + " Valores: " + hogar + ", " + alimentacion + ", " + ropa + ", " + transporte + ", " + tecnologia);
                Log.e("****MainActivity", "Fecha salida: " + outputDate + " Valores: " + hogar + ", " + alimentacion + ", " + ropa + ", " + transporte + ", " + tecnologia);

                BarEntry entry = new BarEntry(i, new float[]{(float) hogar, (float) alimentacion, (float) ropa, (float) transporte, (float) tecnologia});
                barEntries.add(entry);
                labels.add(outputDate);

            }

            ArrayList<ChartBuilder.DateValueEntry> entries = new ArrayList<>();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");

            for (int j = 0; j < resultados.size(); j++) {
                String inDate = String.valueOf(resultados.get(j).getCreated());
                Date outDate;
                try {
                    outDate = dateFormat.parse(convertStringFormat(inDate));
                } catch (ParseException e) {
                    Log.e(TAG, "Error parsing date", e);
                    continue;
                }
                float valor = resultados.get(j).getHuella().floatValue();
                entries.add(new ChartBuilder.DateValueEntry(outDate, valor));
            }

            ChartBuilder.buildStackedBarChart(barChart, getContext(), screenWidth, screenHeight, entries, barEntries);
            ChartBuilder.buildLineChart(lineChart, getContext(), screenWidth, screenHeight, entries);
        } catch (Exception e) {
            e.printStackTrace();
            if (getContext() != null) {
                Toast.makeText(getContext(), "No fue posible cargar los registros", Toast.LENGTH_SHORT).show();
            }

        }
    }

    private static String convertStringFormat(String dateString) {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
            Date date = inputFormat.parse(dateString);
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yy", Locale.getDefault());
            String outputDate = outputFormat.format(date);

            return outputDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateString;
    }

    @Override
    public void onError(BackendlessFault fault) {

    }
}


