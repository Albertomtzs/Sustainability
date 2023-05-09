package com.ams.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

import com.ams.myapplication.data.ResultadosDAO;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;

import java.util.LinkedHashMap;

public class ResultsPage extends AppCompatActivity {

    private TextView label;
    private TextView top;
    private TextView ukAvgText;
    private TextView ukAvgFig;
    private TextView goalText;
    private TextView goalFig;

    private String seccionesTotal;
    private double emissiomResult;

    private float hogarf, comidaf, transportef, ropaf, tecnologiaf, otrosTransportef;
    static private LinkedHashMap<String, Float> emissiontable;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_page);

        label = findViewById(R.id.textTotal);
        top = findViewById(R.id.textResult);
        ukAvgFig = findViewById(R.id.textAverageFigure);
        ukAvgText = findViewById(R.id.textAverage);
        goalText = findViewById(R.id.textGoal);
        goalFig = findViewById(R.id.textGoalFigure);

        emissiomResult = Double.parseDouble(getIntent().getStringExtra("RESULT_ALL"));
        hogarf = Float.parseFloat(getIntent().getStringExtra("RESULT_HOUSE"));
        comidaf = Float.parseFloat(getIntent().getStringExtra("RESULT_FOOD"));
        transportef = Float.parseFloat(getIntent().getStringExtra("RESULT_TRANSPORT"));
        otrosTransportef = Float.parseFloat(getIntent().getStringExtra("RESULT_TRANSPORT_OTHER"));
        ropaf = Float.parseFloat(getIntent().getStringExtra("RESULT_CLOTHES"));
        tecnologiaf = Float.parseFloat(getIntent().getStringExtra("RESULT_Technology"));

        ResultadosDAO resultadosDAO = new ResultadosDAO(this);
        resultadosDAO.insertRecordCarbon(hogarf,comidaf,ropaf,tecnologiaf,(Math.round((transportef + otrosTransportef) * 10.0) / 10.0));

        BarChart barChart = findViewById(R.id.barChart);
        PieChart pieChart = findViewById(R.id.pieChart);

        emissiontable = new LinkedHashMap<>();

        emissiontable.put("Vivienda", hogarf);
        emissiontable.put("Comida",comidaf);
        emissiontable.put("Transporte", (float) (Math.round((transportef + otrosTransportef) * 10.0) / 10.0));
        emissiontable.put("ropa",ropaf);
        emissiontable.put("Tecnología",tecnologiaf);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenheight = displayMetrics.heightPixels;
        int screenwidth = displayMetrics.widthPixels;

        ChartBuilder2.buildBarChart7(barChart,this,screenwidth,screenheight, emissiontable);
        ChartBuilder2.buildPieChart2(pieChart,this,screenwidth,screenheight, emissiontable);

        addTotal(emissiomResult);
    }

    public void backToStart(View view) {
        Intent back = new Intent(this, MainActivity.class);
        startActivity(back);
        finish();
    }

    public void backTips (View view) {
        Intent tips = new Intent(this, Education.class);
        startActivity(tips);
    }

    private void addTotal(double emissiomResult) {

        top.setText("Your annual C02 Emissions:");
        label.setText(emissiomResult + "t");
    }

    public void sendResults(View view){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "My total weekly CO2 emissions is " + emissiomResult + "kg. Try the CARBN app to find out yours!");
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);
    }

    public void weeklyResults (View v){
        emissiomResult = Double.parseDouble(seccionesTotal)/52;

        top.setText("Your Weekly C02 Emissions:");
        label.setText(emissiomResult + " t");

        ukAvgText.setText("UK average weekly CO2 emissions:");
        ukAvgFig.setText("190kg");

        goalText.setText("Your weekly CO2 emissions goal should be:") ;
        goalFig.setText("50kg");
    }

    public void monthlyResults (View v){
        double m = emissiomResult / 4;
        top.setText("Your Monthly C02 Emissions:");
        label.setText(m + " t");

        ukAvgText.setText("UK average monthly CO2 emissions:");
        ukAvgFig.setText("825kg");

        goalText.setText("Your monthly CO2 emissions goal should be:") ;
        goalFig.setText("600kg");
    }

    public void annualResults (View v){
        emissiomResult = Double.parseDouble(seccionesTotal);

        double o = emissiomResult;
        top.setText("Your Annual C02 Emissions:");
        label.setText(o + " t");

        ukAvgText.setText("UK average yearly CO2 emissions:");
        ukAvgFig.setText("9,880kg");

        goalText.setText("Your yearly CO2 emissions goal should be:") ;
        goalFig.setText("7,000kg");
    }

}

