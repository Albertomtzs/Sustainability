package com.ams.myapplication.ui.social;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.ams.myapplication.ChartBuilder2;
import com.ams.myapplication.R;
import com.ams.myapplication.adapter.ChartAdapter;
import com.ams.myapplication.data.HistoryResults;
import com.ams.myapplication.data.Resultados;
import com.ams.myapplication.data.ResultadosDAO;
import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.security.PrivateKey;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;



public class FragmentSocial extends Fragment implements HistoryResults {

    private BarChart barChart;
    private LineChart lineChart;
    private ResultadosDAO resultadosDAO;
    private Context context;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_results_page2, container, false);

        context = view.getContext();
        resultadosDAO = new ResultadosDAO(context);
        resultadosDAO.getHistoryResults(this);
        barChart = view.findViewById(R.id.barChart);
        lineChart = view.findViewById(R.id.lineChart);

        return view;
    }

    @Override
    public void onHistoryRecords(List<Resultados> resultados) {

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
        ChartBuilder2.buildStackedBarChart2(barChart,getContext(),screenWidth,screenHeight,labels,barEntries);


        ArrayList<ChartBuilder2.DateValueEntry> entries = new ArrayList<>();
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
            entries.add(new ChartBuilder2.DateValueEntry(outDate, valor));
        }

        ChartBuilder2.buildLineChart13(lineChart, getContext(), screenWidth, screenHeight, entries);


        /*ArrayList<Entry> entries = new ArrayList<>();
        for (int j = 0; j < resultados.size(); j++) {
            String inDate = String.valueOf(resultados.get(j).getCreated());
            String outDate = convertStringFormat(inDate);
            float valor = resultados.get(j).getHuella().floatValue();

            Log.e("****MainActivity", "Fecha entrada: " + inDate + " Valor: " + valor);
            Log.e("****MainActivity", "Fecha salida: " + outDate + " Valor: " + valor);

            entries.add(new Entry(j, valor, outDate));

            //emissiontable.put(outDate, valor);
        }*/


    }

    public class DateValueEntry {

        private String date;
        private float value;

        public DateValueEntry(String date, float value) {
            this.date = date;
            this.value = value;
        }

        public String getDate() {
            return date;
        }

        public float getValue() {
            return value;
        }
    }


    public static String convertStringFormat(String dateString) {
        try{
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










       // ESTE CODIGO ES FUNCIONAL


  /*      public class FragmentSocial extends Fragment implements HistoryResults {

    private ListView listView;
    private LineChart lineChart;
    private BarChart barChart;
    private PieChart pieChart;
    private ResultadosDAO resultadosDAO;
    private Context context;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_results_page2, container, false);

        context = view.getContext();
        resultadosDAO = new ResultadosDAO(context);
        resultadosDAO.getHistoryResults(this);
        lineChart = view.findViewById(R.id.lineChart);

        return view;
    }

    @Override
    public void onHistoryRecords(List<Resultados> resultados) {

        LinkedHashMap<String, Float> emissiontable = new LinkedHashMap<>();

        for (int i = 0; i < resultados.size(); i++) {
            String inputDate = String.valueOf(resultados.get(i).getCreated());
            String outputDate = convertStringFormat(inputDate);
            double valor = resultados.get(i).getHuella();

            Log.e("****MainActivity", "Fecha entrada: " + inputDate + " Valor: " + valor);
            Log.e("****MainActivity", "Fecha salida: " + outputDate + " Valor: " + valor);
            emissiontable.put(outputDate, (float) valor);
        }

        /*List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < resultados.size(); i++) {
            Date fecha = resultados.get(i).getCreated();
            Resultados resultado = resultados.get(i);
            float total = resultado.getHogar().floatValue() + resultado.getRopa().floatValue() +
                    resultado.getAlimentacion().floatValue() + resultado.getTecnologia().floatValue() +
                    resultado.getTransporte().floatValue();

            entries.add(new Entry(fecha.getTime(), total));
        }


        LineDataSet dataSet = new LineDataSet((List<Entry>) emissiontable, "Total");
        dataSet.setColor(Color.BLUE);
        dataSet.setCircleColor(Color.RED);

        LineData lineData = new LineData(dataSet);
        lineChart.setData(lineData);
        lineChart.invalidate();*/

       /* DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenheight = displayMetrics.heightPixels;
        int screenwidth = displayMetrics.widthPixels;

        //ChartBuilder2.buildBarChart7(barChart, getContext(),screenwidth, screenheight, emissiontable);
        ChartBuilder2.buildHistoricalLineChart5(lineChart, getContext(),screenwidth, screenheight, emissiontable);

    }*/

   /* public static String convertStringFormat(String dateString) {
        try{
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
*/