package com.ams.sustainability.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.ams.sustainability.R;
import com.ams.sustainability.data.common.ResultadosListener;
import com.ams.sustainability.data.repository.BackendLessDAO;
import com.ams.sustainability.model.graph.entities.Results;
import com.ams.sustainability.model.graph.ChartBuilder;
import com.ams.sustainability.model.usecases.CarbonFootprintCalculator;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.charts.RadarChart;

import java.util.LinkedHashMap;

public class FragmentHome extends Fragment implements ResultadosListener {

    private BarChart barChart;
    private PieChart pieChart;
    private RadarChart radarChart;
    private BackendLessDAO backendLessDAO;
    private Context context;
    private Button btnReCalculate;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        context = view.getContext();
        backendLessDAO = new BackendLessDAO(context);
        barChart = view.findViewById(R.id.barChart);
        pieChart = view.findViewById(R.id.pieChart);
        radarChart = view.findViewById(R.id.radarChart);
        backendLessDAO.getLastRecord(this);
        btnReCalculate = view.findViewById(R.id.btnNewCalculate);

        btnReCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // crear un Intent y empezar la actividad deseada
                Intent intent = new Intent(getActivity(), CarbonFootprintCalculator.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onLastRecordLoaded(Results lastRecord) {

        try {

            String huellaTotal = String.valueOf(lastRecord.getCarbon_footprint());
            float hogar = lastRecord.getHouse().floatValue();
            float ropa = lastRecord.getClothes().floatValue();
            float alimentacion = lastRecord.getFood().floatValue();
            float tecnologia = lastRecord.getTechonology().floatValue();
            float transporte = lastRecord.getTransport().floatValue();

            LinkedHashMap<String, Float> emissiontable = new LinkedHashMap<>();
            emissiontable.put("Vivienda", hogar);
            emissiontable.put("Comida", alimentacion);
            emissiontable.put("Transporte", transporte);
            emissiontable.put("Ropa", ropa);
            emissiontable.put("Tecnología", tecnologia);


            DisplayMetrics displayMetrics = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int screenheight = displayMetrics.heightPixels;
            int screenwidth = displayMetrics.widthPixels;

            ChartBuilder.buildBarChart(barChart, getContext(), screenwidth, screenheight, emissiontable,"t");
            ChartBuilder.buildPieChart(pieChart, getContext(), screenwidth, screenheight, emissiontable, huellaTotal, "t","año");
            ChartBuilder.BuildRadarChart(radarChart, screenwidth, screenheight, emissiontable);

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "No fue posible cargar los registros", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onError(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }

}



