package com.ams.sustainability.view;

import android.annotation.SuppressLint;
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

    private TextView top, label, avgText, avgFig, goalText;
    private TextView goalFig;
    private double emissiomResult;

    private int screenheight, screenwidth;

    private BarChart barChart;
    private PieChart pieChart;

    private float hogarf, comidaf, transportef, ropaf, tecnologiaf, otrosTransportef;
    static private LinkedHashMap<String, Float> emissiontable;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_view);

        label = findViewById(R.id.textTotal);
        top = findViewById(R.id.textResult);
        avgFig = findViewById(R.id.textAverageFigure);
        avgText = findViewById(R.id.textAverage);
        goalText = findViewById(R.id.textGoal);
        goalFig = findViewById(R.id.textGoalFigure);

        barChart = findViewById(R.id.barChart);
        pieChart = findViewById(R.id.pieChart);

        /*emissiomResult = 6;
        hogarf = 1f;
        comidaf = 1f;
        transportef = 1f;
        ropaf = 1f;
        tecnologiaf = 1f;
        otrosTransportef = 1f;*/


         emissiomResult = Double.parseDouble(getIntent().getStringExtra("RESULT_ALL"));
         hogarf = Float.parseFloat(getIntent().getStringExtra("RESULT_HOUSE"));
         comidaf = Float.parseFloat(getIntent().getStringExtra("RESULT_FOOD"));
         transportef = Float.parseFloat(getIntent().getStringExtra("RESULT_TRANSPORT"));
         otrosTransportef = Float.parseFloat(getIntent().getStringExtra("RESULT_TRANSPORT_OTHER"));
         ropaf = Float.parseFloat(getIntent().getStringExtra("RESULT_CLOTHES"));
         tecnologiaf = Float.parseFloat(getIntent().getStringExtra("RESULT_Technology"));

        addTotal(emissiomResult);

        BackendLessDAO resultadosDAO = new BackendLessDAO(this);
        resultadosDAO.insertRecordCarbon(hogarf, comidaf, ropaf, tecnologiaf, (Math.round((transportef + otrosTransportef) * 10.0) / 10.0));

        emissiontable = new LinkedHashMap<>();

        emissiontable.put("Vivienda", hogarf);
        emissiontable.put("Comida", comidaf);
        emissiontable.put("Transporte", (float) (Math.round((transportef + otrosTransportef) * 10.0) / 10.0));
        emissiontable.put("ropa", ropaf);
        emissiontable.put("Tecnología", tecnologiaf);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenheight = displayMetrics.heightPixels;
        screenwidth = displayMetrics.widthPixels;

        ChartBuilder.buildBarChart(barChart, this, screenwidth, screenheight, emissiontable);
        ChartBuilder.buildPieChart(pieChart, this, screenwidth, screenheight, emissiontable, String.valueOf(emissiomResult));

    }


    public void onBacktoHome(View view) {
        Intent back = new Intent(this, NavigationFragment.class);
        startActivity(back);
        finish();
    }

    @SuppressLint("SetTextI18n")
    private void addTotal(double emissiomResult) {

        top.setText("Emisiones anuales de CO2:");
        label.setText(String.valueOf(emissiomResult).replace(".",",") + " t");
    }

    @SuppressLint("SetTextI18n")
    public void weeklyResults(View v) {
        double m = Math.round((emissiomResult / 52) * 10000) * 10d / 100d;

        top.setText("Emisiones semanales de CO2:");
        label.setText(String.valueOf(m).replace(".",",") + " Kg");

        avgText.setText("El promedio de emisiones semanal de CO2 en España son:");
        avgFig.setText("106,2 kg");

        goalText.setText("Tu objetivo semanal sostenible de emisiones de CO2 debería ser:");
        goalFig.setText("40,4 kg");

        ChartBuilder.buildBarChart(barChart, this, screenwidth, screenheight, emissiontable);
        ChartBuilder.buildPieChart(pieChart, this, screenwidth, screenheight, emissiontable, String.valueOf(emissiomResult));
    }

    @SuppressLint("SetTextI18n")
    public void monthlyResults(View v) {
        double m = Math.round((emissiomResult / 12) * 10000) * 10d / 100d;
        top.setText("Emisiones mensuales de CO2:");
        label.setText(String.valueOf(m).replace(".",",") + " t");

        avgText.setText("El promedio de emisiones mensual de CO2 en España son:");
        avgFig.setText("0,46 t");

        goalText.setText("Tu objetivo mensual sostenible de CO2 sostenible debería ser:");
        goalFig.setText("0,18 t");

        ChartBuilder.buildBarChart(barChart, this, screenwidth, screenheight, emissiontable);
        ChartBuilder.buildPieChart(pieChart, this, screenwidth, screenheight, emissiontable, String.valueOf(emissiomResult));
    }

    @SuppressLint("SetTextI18n")
    public void annualResults(View v) {

        double m = emissiomResult;
        top.setText("Emisiones anuales de CO2:");
        label.setText(String.valueOf(m).replace(".",","));

        avgText.setText("El promedio de emisiones anual de CO2 en España son:");
        avgFig.setText("5,5 t");

        goalText.setText("Tu objetivo anual sostenible de CO2 sostenible debería ser:");
        goalFig.setText("2,1 t");

        ChartBuilder.buildBarChart(barChart, this, screenwidth, screenheight, emissiontable);
        ChartBuilder.buildPieChart(pieChart, this, screenwidth, screenheight, emissiontable, String.valueOf(emissiomResult));
    }

}

