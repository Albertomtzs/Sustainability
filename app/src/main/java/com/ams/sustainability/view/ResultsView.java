package com.ams.sustainability.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ams.sustainability.R;
import com.ams.sustainability.data.repository.BackendLessDAO;
import com.ams.sustainability.model.graph.ChartBuilder;
import com.ams.sustainability.ui.navigation.NavigationFragment;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;

import java.util.LinkedHashMap;

public class ResultsView extends AppCompatActivity {

    private TextView top, label, avgText, avgFig, goalText, goalFig;
    private double emissionResult;
    private int screenHeight, screenWidth;
    private BarChart barChart;
    private PieChart pieChart;
    private LinkedHashMap<String, Float> emissionTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_view);

        initializeViews();
        retrieveIntentData();
        insertEmissionData();
        updateLabel(String.valueOf(emissionResult), " t");
        setupCharts();
    }

    private void initializeViews() {
        label = findViewById(R.id.textTotal);
        top = findViewById(R.id.textResult);
        avgFig = findViewById(R.id.textAverageFigure);
        avgText = findViewById(R.id.textAverage);
        goalText = findViewById(R.id.textGoal);
        goalFig = findViewById(R.id.textGoalFigure);
        barChart = findViewById(R.id.barChart);
        pieChart = findViewById(R.id.pieChart);
    }

    private void retrieveIntentData() {
        Intent intent = getIntent();
        emissionResult = Double.parseDouble(intent.getStringExtra("RESULT_ALL"));
        float[] values = {
                Float.parseFloat(intent.getStringExtra("RESULT_HOUSE")),
                Float.parseFloat(intent.getStringExtra("RESULT_FOOD")),
                Float.parseFloat(intent.getStringExtra("RESULT_CLOTHES")),
                Float.parseFloat(intent.getStringExtra("RESULT_TECHNOLOGY")),
                Math.round((Float.parseFloat(intent.getStringExtra("RESULT_TRANSPORT")) + Float.parseFloat(intent.getStringExtra("RESULT_TRANSPORT_OTHER"))) * 10.0f) / 10.0f
        };
        String[] categories = {
                "Vivienda", "Comida", "Ropa", "Tecnología", "Transporte"
        };
        emissionTable = new LinkedHashMap<>();
        for (int i = 0; i < values.length; i++) {
            emissionTable.put(categories[i], values[i]);
        }
    }

    private void insertEmissionData() {
        BackendLessDAO resultadosDAO = new BackendLessDAO(this);
        resultadosDAO.insertRecordCarbon(
                emissionTable.get("Vivienda"),
                emissionTable.get("Comida"),
                emissionTable.get("Ropa"),
                emissionTable.get("Tecnología"),
                emissionTable.get("Transporte")
        );
    }

    private void setupCharts() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenHeight = displayMetrics.heightPixels;
        screenWidth = displayMetrics.widthPixels;

        ChartBuilder.buildBarChart(barChart, this, screenWidth, screenHeight, emissionTable);
        ChartBuilder.buildPieChart(pieChart, this, screenWidth, screenHeight, emissionTable, String.valueOf(emissionResult));
    }

    public void onBacktoHome(View view) {
        Intent back = new Intent(this, NavigationFragment.class);
        startActivity(back);
        finish();
    }

    private void updateLabel(String text, String unit) {
        label.setText(text.replace(".", ",") + unit);
    }

    private void updateTop(String text) {
        top.setText(text);
    }

    public void weeklyResults(View v) {
        double weeklyEmission = Math.round((emissionResult / 52) * 10000) * 10d / 100d;
        updateTop("Emisiones semanales de CO2:");
        updateLabel(String.valueOf(weeklyEmission), " kg");
        avgText.setText("El promedio de emisiones semanales de CO2 en España son:");
        avgFig.setText("106,2 kg");
        goalText.setText("Tu objetivo semanal sostenible de emisiones de CO2 debería ser:");
        goalFig.setText("40,4 kg");

        //updateCharts(convertToMontlyWeekly(52));
    }

    public void monthlyResults(View v) {
        double monthlyEmission = Math.round((emissionResult / 12) * 10d) / 10d;
        updateTop("Emisiones mensuales de CO2:");
        updateLabel(String.valueOf(monthlyEmission), " t");
        avgText.setText("El promedio de emisiones mensuales de CO2 en España son:");
        avgFig.setText("0,46 t");
        goalText.setText("Tu objetivo mensual sostenible de emisiones de CO2 debería ser:");
        goalFig.setText("0,18 t");

        //updateCharts(convertToMontlyWeekly(12));
    }

    public void annualResults(View v) {
        updateTop("Emisiones anuales de CO2:");
        updateLabel(String.valueOf(emissionResult), " t");
        avgText.setText("El promedio de emisiones anuales de CO2 en España son:");
        avgFig.setText("5,5 t");
        goalText.setText("Tu objetivo anual sostenible de emisiones de CO2 debería ser:");
        goalFig.setText("2,1 t");

        //updateCharts(emissionTable);
    }

    private void updateCharts(LinkedHashMap<String, Float> data) {
        ChartBuilder.buildBarChart(barChart, this, screenWidth, screenHeight, data);
        ChartBuilder.buildPieChart(pieChart, this, screenWidth, screenHeight, data, String.valueOf(emissionResult));
    }


}


