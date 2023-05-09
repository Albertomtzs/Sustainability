package com.ams.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.ams.myapplication.ui.social.FragmentSocial;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public class ChartBuilder2 {


    static private LinkedHashMap<String, Float> emissiontable;

    public static void buildBarChart(BarChart barChart) {

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(R.color.pastelPink); // Color para la primera barra (Vivienda)
        colors.add(R.color.pastelBlue); // Color para la segunda barra (Alimentación)
        colors.add(R.color.pastelOrange); // Color para la tercera barra (Transporte)

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, 0.3f, "Vivienda"));
        entries.add(new BarEntry(1, 0.4f, "Alimentación"));
        entries.add(new BarEntry(2, 2.4f, "Transporte"));

        BarDataSet dataSet = new BarDataSet(entries, "Gastos");
        dataSet.setColors(colors);

        dataSet.setValueTextSize(12f);
        dataSet.setValueTextColor(Color.WHITE);
        dataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return ((int) (value * 100)) + " %";
            }
        });

        BarData data = new BarData(dataSet);


        barChart.setData(data);
        barChart.animateY(1000);
        barChart.getDescription().setEnabled(false);

        /*Legend legend = barChart.getLegend();
        legend.setForm(Legend.LegendForm.SQUARE);
        legend.setTextSize(14f);
        legend.setTextColor(Color.BLACK);
        legend.setXEntrySpace(20f);
        legend.setYEntrySpace(20f);*/

        XAxis xAxis = barChart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setCenterAxisLabels(true);
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //xAxis.setValueFormatter(new IndexAxisValueFormatter(new String[]{"Vivienda", "Alimentación", "Transporte"}));

        YAxis yAxisLeft = barChart.getAxisLeft();
        yAxisLeft.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return ((int) (value * 100)) + " %";
            }
        });

        YAxis yAxisRight = barChart.getAxisRight();
        yAxisRight.setEnabled(false);

        barChart.invalidate();

    }

    public static void buildBarChart2(@NonNull BarChart barChart, Context context) {

        ArrayList<BarEntry> carbonFootprint = new ArrayList<>();
        carbonFootprint.add(new BarEntry(0, 0, "Hogar"));
        carbonFootprint.add(new BarEntry(1, 1, "Alimentación"));
        carbonFootprint.add(new BarEntry(2, 2, "Transporte"));
        carbonFootprint.add(new BarEntry(3, 3, "Otros transportes"));
        carbonFootprint.add(new BarEntry(4, 4, "ropa"));
        carbonFootprint.add(new BarEntry(5, 5, "Tecnología"));

        BarDataSet barDataSet = new BarDataSet(carbonFootprint, "Carbon Footprint");

        // Asignar colores personalizados a cada barra
        int[] colors = {ContextCompat.getColor(context, R.color.pastelPink),
                ContextCompat.getColor(context, R.color.pastelPeach),
                ContextCompat.getColor(context, R.color.pastelBlue),
                ContextCompat.getColor(context, R.color.pastelTeal),
                ContextCompat.getColor(context, R.color.pastelTurquoise),
                ContextCompat.getColor(context, R.color.pastelYellow)};
        barDataSet.setColors(colors);

        // Crear leyenda para cada color
        List<LegendEntry> entries = new ArrayList<>();
        String[] labels = {"Hogar", "Alimentación", "Transporte", "Otros transportes", "ropa", "Tecnología"};
        for (int i = 0; i < labels.length; i++) {
            LegendEntry entry = new LegendEntry();
            entry.label = labels[i];
            entry.formColor = colors[i];
            entries.add(entry);
        }
        barChart.getLegend().setCustom(entries);
        Legend legend = barChart.getLegend();
        legend.setCustom(entries);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);


        barDataSet.setValueTextColor(Color.WHITE);
        barDataSet.setValueTextSize(18f);
        barDataSet.setBarBorderColor(Color.WHITE);

        BarData barData = new BarData(barDataSet);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Bar Chart Example");
        barChart.animateY(6000);
        barChart.getXAxis().setDrawLabels(false);
        barChart.getAxisLeft().setTextSize(18f);
        barChart.getAxisLeft().setTextColor(ContextCompat.getColor(context, R.color.white));
        barChart.getAxisRight().setDrawLabels(false);
        barChart.getLegend().setTextColor((ContextCompat.getColor(context, R.color.white)));
    }


    public static void buildBarChart3(@NonNull BarChart barChart, Context context) {

        ArrayList<BarEntry> carbonFootprint = new ArrayList<>();
        carbonFootprint.add(new BarEntry(0, 0, "Hogar"));
        carbonFootprint.add(new BarEntry(1, 1, "Alimentación"));
        carbonFootprint.add(new BarEntry(2, 2, "Transporte"));
        carbonFootprint.add(new BarEntry(3, 3, "Otros transportes"));
        carbonFootprint.add(new BarEntry(4, 4, "ropa"));
        carbonFootprint.add(new BarEntry(5, 5, "Tecnología"));

        BarDataSet barDataSet = new BarDataSet(carbonFootprint, "Carbon Footprint");

        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        barDataSet.setValueTextColor(Color.WHITE);
        barDataSet.setValueTextSize(18f);
        barDataSet.setBarBorderColor(Color.WHITE);

        BarData barData = new BarData(barDataSet);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Bar Chart Example");
        barChart.animateY(6000);
        barChart.getXAxis().setDrawLabels(false);
        barChart.getAxisLeft().setTextSize(18f);
        barChart.getAxisLeft().setTextColor(ContextCompat.getColor(context, R.color.white));
        barChart.getAxisRight().setDrawLabels(false);
        barChart.getLegend().setTextColor((ContextCompat.getColor(context, R.color.white)));

    }

    public static void buildBarChart4(@NonNull BarChart barChart, Context context, int screenWidth, int screenHeight) {

        ArrayList<BarEntry> carbonFootprint = new ArrayList<>();
        carbonFootprint.add(new BarEntry(0, 0, "Hogar"));
        carbonFootprint.add(new BarEntry(1, 1, "Alimentación"));
        carbonFootprint.add(new BarEntry(2, 2, "Transporte"));
        //carbonFootprint.add(new BarEntry(3, 3, "Otros transportes"));
        carbonFootprint.add(new BarEntry(4, 4, "ropa"));
        carbonFootprint.add(new BarEntry(5, 5, "Tecnología"));

        BarDataSet barDataSet = new BarDataSet(carbonFootprint, "Carbon Footprint");

        // Asignar colores personalizados a cada barra
        int[] colors = {ContextCompat.getColor(context, R.color.pastelPink),
                ContextCompat.getColor(context, R.color.pastelPeach),
                ContextCompat.getColor(context, R.color.pastelBlue),
                ContextCompat.getColor(context, R.color.pastelTeal),
                ContextCompat.getColor(context, R.color.pastelTurquoise),
                ContextCompat.getColor(context, R.color.pastelYellow)};
        barDataSet.setColors(colors);

        // Crear leyenda para cada color
        List<LegendEntry> entries = new ArrayList<>();
        String[] labels = {"Hogar", "Alimentación", "Transporte", "ropa", "Tecnología"};
        for (int i = 0; i < labels.length; i++) {
            LegendEntry entry = new LegendEntry();
            entry.label = labels[i];
            entry.formColor = colors[i];
            entries.add(entry);
        }
        Legend legend = barChart.getLegend();
        legend.setCustom(entries);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);

        barDataSet.setValueTextColor(Color.WHITE);
        barDataSet.setValueTextSize(18f);
        barDataSet.setBarBorderColor(Color.WHITE);

        BarData barData = new BarData(barDataSet);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Bar Chart Example");
        barChart.animateY(6000);
        barChart.getXAxis().setDrawLabels(false);
        barChart.getAxisLeft().setTextSize(18f);
        barChart.getAxisLeft().setTextColor(ContextCompat.getColor(context, R.color.white));
        barChart.getAxisRight().setDrawLabels(false);
        barChart.getLegend().setTextColor((ContextCompat.getColor(context, R.color.white)));

        // Redimensionar el tamaño del gráfico para ajustarse a las dimensiones de la pantalla
        ViewGroup.LayoutParams params = barChart.getLayoutParams();
        params.width = (int) (screenWidth * 0.8);
        params.height = (int) (screenHeight * 0.5);
        barChart.setLayoutParams(params);
    }

    public static void buildBarChart5(@NonNull BarChart barChart, Context context, int screenWidth, int screenHeight) {

        ArrayList<BarEntry> carbonFootprint = new ArrayList<>();
        carbonFootprint.add(new BarEntry(0, 0, "Hogar"));
        carbonFootprint.add(new BarEntry(1, 1, "Alimentación"));
        carbonFootprint.add(new BarEntry(2, 2, "Transporte"));
        //carbonFootprint.add(new BarEntry(3, 3, "Otros transportes"));
        carbonFootprint.add(new BarEntry(3, 4, "ropa"));
        carbonFootprint.add(new BarEntry(4, 5, "Tecnología"));

        BarDataSet barDataSet = new BarDataSet(carbonFootprint, "Carbon Footprint");

        // Asignar colores personalizados a cada barra
        int[] colors = {ContextCompat.getColor(context, R.color.pastelPink),
                ContextCompat.getColor(context, R.color.pastelPeach),
                ContextCompat.getColor(context, R.color.pastelBlue),
                ContextCompat.getColor(context, R.color.pastelTeal),
                ContextCompat.getColor(context, R.color.pastelTurquoise),
                ContextCompat.getColor(context, R.color.pastelYellow)};
        barDataSet.setColors(colors);

        // Crear leyenda para cada color
        List<LegendEntry> entries = new ArrayList<>();
        String[] labels = {"Hogar", "Alimentación", "Transporte", "ropa", "Tecnología"};
        for (int i = 0; i < labels.length; i++) {
            LegendEntry entry = new LegendEntry();
            entry.label = labels[i];
            entry.formColor = colors[i];
            entries.add(entry);
        }
        Legend legend = barChart.getLegend();
        legend.setCustom(entries);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setTypeface((Typeface.create("@font/poppins_semibold", Typeface.BOLD))); // Cambiar la fuente


        barDataSet.setValueTextColor(Color.WHITE);
        barDataSet.setValueTextSize(18f);
        barDataSet.setBarBorderColor(Color.WHITE);

        BarData barData = new BarData(barDataSet);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Bar Chart Example");
        barChart.animateY(6000);
        barChart.getXAxis().setDrawLabels(false);
        barChart.getXAxis().setAxisLineColor(Color.WHITE);
        barChart.getXAxis().setTextColor(Color.WHITE);
        barChart.getAxisLeft().setTextSize(18f);
        barChart.getAxisLeft().setTextColor(ContextCompat.getColor(context, R.color.white));
        barChart.getAxisLeft().setAxisLineColor(Color.WHITE);
        barChart.getAxisRight().setDrawLabels(false);
        barChart.getAxisRight().setAxisLineColor(Color.WHITE);
        barChart.getLegend().setTextColor((ContextCompat.getColor(context, R.color.white)));
        barChart.getLegend().setFormSize(18f); // Establecer el tamaño de la leyenda

        // Redimensionar el tamaño del gráfico para ajustarse a las dimensiones de la pantalla
        ViewGroup.LayoutParams params = barChart.getLayoutParams();
        params.width = (int) (screenWidth * 0.9);
        params.height = (int) (screenHeight * 0.4);
        barChart.setLayoutParams(params);
    }

    public static void buildBarChart6(@NonNull BarChart barChart, Context context, int screenWidth, int screenHeight, LinkedHashMap<String, Float> data) {

        ArrayList<BarEntry> carbonFootprint = new ArrayList<>();
        int i = 0;
        for (Map.Entry<String, Float> entry : data.entrySet()) {
            carbonFootprint.add(new BarEntry(i, entry.getValue(), entry.getKey()));
            i++;
        }

        BarDataSet barDataSet = new BarDataSet(carbonFootprint, "Carbon Footprint");

        // Asignar colores personalizados a cada barra
        int[] colors = {ContextCompat.getColor(context, R.color.pastelPink),
                ContextCompat.getColor(context, R.color.pastelPeach),
                ContextCompat.getColor(context, R.color.pastelBlue),
                ContextCompat.getColor(context, R.color.pastelTeal),
                ContextCompat.getColor(context, R.color.pastelTurquoise),
                ContextCompat.getColor(context, R.color.pastelYellow)};
        barDataSet.setColors(colors);

        // Crear leyenda para cada color
        List<LegendEntry> entries = new ArrayList<>();
        String[] labels = data.keySet().toArray(new String[0]);
        for (int j = 0; j < labels.length; j++) {
            LegendEntry entry = new LegendEntry();
            entry.label = labels[j];
            entry.formColor = colors[j];
            entries.add(entry);
        }
        Legend legend = barChart.getLegend();
        legend.setCustom(entries);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setTypeface((Typeface.create("@font/poppins_semibold", Typeface.BOLD))); // Cambiar la fuente


        barDataSet.setValueTextColor(Color.WHITE);
        barDataSet.setValueTextSize(16f);
        barDataSet.setBarBorderColor(Color.WHITE);

        BarData barData = new BarData(barDataSet);

        barChart.getAxisLeft().setGridColor(Color.WHITE);
        barChart.getXAxis().setGridColor(Color.WHITE);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Bar Chart Example");
        barChart.animateY(6000);
        barChart.getXAxis().setDrawLabels(false);
        barChart.getXAxis().setAxisLineColor(Color.WHITE);
        barChart.getXAxis().setTextColor(Color.WHITE);
        barChart.getAxisLeft().setTextSize(18f);
        barChart.getAxisLeft().setTextColor(ContextCompat.getColor(context, R.color.white));
        barChart.getAxisLeft().setAxisLineColor(Color.WHITE);
        barChart.getAxisRight().setDrawLabels(false);
        barChart.getAxisRight().setAxisLineColor(Color.WHITE);
        barChart.getLegend().setTextColor((ContextCompat.getColor(context, R.color.white)));
        barChart.getLegend().setFormSize(12f); // Establecer el tamaño de la leyenda

        // Redimensionar el tamaño del gráfico para ajustarse a las dimensiones de la pantalla
        ViewGroup.LayoutParams params = barChart.getLayoutParams();
        params.width = (int) (screenWidth * 1);
        params.height = (int) (screenHeight * 0.4);
        barChart.setLayoutParams(params);
    }

    public static void buildBarChart7(@NonNull BarChart barChart, Context context, int screenWidth, int screenHeight, LinkedHashMap<String, Float> data) {

        ArrayList<BarEntry> carbonFootprint = new ArrayList<>();

        int index = 0;
        for (Map.Entry<String, Float> entry : data.entrySet()) {
            carbonFootprint.add(new BarEntry(index, entry.getValue(), entry.getKey()));
            index++;
        }

        BarDataSet barDataSet = new BarDataSet(carbonFootprint, "Carbon Footprint");

        // Asignar colores personalizados a cada barra
        int[] colors = {ContextCompat.getColor(context, R.color.pastelPeach),
                ContextCompat.getColor(context, android.R.color.holo_purple),
                ContextCompat.getColor(context, android.R.color.holo_blue_light),
                ContextCompat.getColor(context, android.R.color.holo_orange_light),
                ContextCompat.getColor(context, R.color.teal_700)};
        barDataSet.setColors(colors);

        // Crear leyenda para cada color
        List<LegendEntry> entries = new ArrayList<>();
        String[] labels = data.keySet().toArray(new String[0]);
        for (int i = 0; i < labels.length; i++) {
            LegendEntry entry = new LegendEntry();
            entry.label = labels[i];
            entry.formColor = colors[i];
            entries.add(entry);
        }
        barDataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return value + " t";
            }
        });

        Legend legend = barChart.getLegend();
        legend.setCustom(entries);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setTypeface((Typeface.create("@font/poppins_semibold", Typeface.BOLD))); // Cambiar la fuente
        legend.setFormSize(18f); // Establecer el tamaño de la leyenda
        legend.setTextSize(14f);

        barDataSet.setValueTextColor(Color.WHITE);
        barDataSet.setValueTextSize(16f);
        barDataSet.setBarBorderColor(Color.WHITE);
        //barDataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

        //barDataSet.setValueLinePart1Length(0.2f);
        //barDataSet.setValueLinePart2Length(0.4f);



        BarData barData = new BarData(barDataSet);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("");
        barChart.animateY(6000);
        barChart.getXAxis().setDrawLabels(false);
        barChart.getXAxis().setDrawAxisLine(true);
        barChart.getXAxis().setAxisLineColor(Color.WHITE);
        barChart.getXAxis().setTextColor(Color.WHITE);
        barChart.getXAxis().setGridColor(Color.WHITE);
        barChart.getAxisLeft().setTextSize(18f);
        barChart.getAxisLeft().setTextColor(ContextCompat.getColor(context, R.color.white));
        barChart.getAxisLeft().setAxisLineColor(Color.WHITE);
        barChart.getAxisLeft().setGridColor(Color.WHITE);
        barChart.getAxisRight().setDrawLabels(false);
        barChart.getAxisRight().setAxisLineColor(Color.WHITE);
        barChart.getAxisRight().setGridColor(Color.WHITE);
        barChart.getLegend().setTextColor((ContextCompat.getColor(context, R.color.white)));
        barChart.setExtraOffsets(5, 10, 5, 5);
        barChart.setExtraOffsets(20.f, 0.f, 20.f, 0.f);


        barChart.setDragDecelerationFrictionCoef(0.95f);
        barChart.getLegend().setFormSize(11f); // Establecer el tamaño de la leyenda

        // Redimensionar el tamaño del gráfico para ajustarse a las dimensiones de la pantalla
        ViewGroup.LayoutParams params = barChart.getLayoutParams();
        params.width = (int) (screenWidth);
        params.height = (int) (screenHeight) / 2;
        barChart.setLayoutParams(params);
    }

    public static void buildBarChart8(@NonNull BarChart barChart, Context context, int screenWidth, int screenHeight, LinkedHashMap<String, Float> data) {

        ArrayList<BarEntry> carbonFootprint = new ArrayList<>();

        int index = 0;
        for (Map.Entry<String, Float> entry : data.entrySet()) {
            carbonFootprint.add(new BarEntry(index++, entry.getValue(), entry.getKey()));
        }

        BarDataSet barDataSet = new BarDataSet(carbonFootprint, "Carbon Footprint");

        // Asignar colores personalizados a cada barra
        int[] colors = {ContextCompat.getColor(context, R.color.pastelPink),
                ContextCompat.getColor(context, R.color.pastelPeach),
                ContextCompat.getColor(context, R.color.pastelBlue),
                ContextCompat.getColor(context, R.color.pastelTeal),
                ContextCompat.getColor(context, R.color.pastelTurquoise),
                ContextCompat.getColor(context, R.color.pastelYellow)};
        barDataSet.setColors(colors);

        // Crear leyenda para cada color
        List<LegendEntry> entries = new ArrayList<>();
        String[] labels = data.keySet().toArray(new String[0]);
        for (int i = 0; i < labels.length; i++) {
            LegendEntry entry = new LegendEntry();
            entry.label = labels[i];
            entry.formColor = colors[i];
            entries.add(entry);
        }
        barDataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return value + " t";
            }
        });

        Legend legend = barChart.getLegend();
        legend.setCustom(entries);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setTypeface((Typeface.create("@font/poppins_semibold", Typeface.BOLD))); // Cambiar la fuente
        legend.setFormSize(18f); // Establecer el tamaño de la leyenda
        legend.setTextSize(14f);

        barDataSet.setValueTextColor(Color.WHITE);
        barDataSet.setValueTextSize(16f);
        barDataSet.setBarBorderColor(Color.WHITE);

        BarData barData = new BarData(barDataSet);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("");
        barChart.animateY(6000);
        barChart.getXAxis().setDrawLabels(false);
        barChart.getXAxis().setDrawAxisLine(true);
        barChart.getXAxis().setAxisLineColor(Color.WHITE);
        barChart.getXAxis().setTextColor(Color.WHITE);
        barChart.getXAxis().setGridColor(Color.WHITE);
        barChart.getAxisLeft().setTextSize(18f);
        barChart.getAxisLeft().setTextColor(ContextCompat.getColor(context, R.color.white));
        barChart.getAxisLeft().setAxisLineColor(Color.WHITE);
        barChart.getAxisLeft().setGridColor(Color.WHITE);
        barChart.getAxisRight().setDrawLabels(false);
        barChart.getAxisRight().setAxisLineColor(Color.WHITE);
        barChart.getAxisRight().setGridColor(Color.WHITE);
        barChart.getLegend().setTextColor((ContextCompat.getColor(context, R.color.white)));
        barChart.getLegend().setFormSize(11f); // Establecer el tamaño de la leyenda

        // Redimensionar el tamaño del gráfico para ajustarse a las dimensiones de la pantalla
        ViewGroup.LayoutParams params = barChart.getLayoutParams();
        params.width = (int) (screenWidth);
        params.height = (int) (screenHeight) / 2;
        barChart.setLayoutParams(params);
    }

    public static void buildPieChart(@NonNull PieChart pieChart, Context context, int screenWidth, int screenHeight, @NonNull LinkedHashMap<String, Float> data) {

        ArrayList<PieEntry> entries = new ArrayList<>();

        for (Map.Entry<String, Float> entry : data.entrySet()) {
            entries.add(new PieEntry(entry.getValue(), entry.getKey()));
        }

        PieDataSet dataSet = new PieDataSet(entries, "");

        dataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return value + " t";
            }
        });

        dataSet.setValueTextColor(Color.WHITE);
        dataSet.setValueTextSize(16f);

        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        PieData pieData = new PieData(dataSet);

        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setDrawEntryLabels(false);
        pieChart.setUsePercentValues(true);
        pieChart.setHoleRadius(50f);
        pieChart.setTransparentCircleRadius(55f);

        pieChart.animateY(6000, Easing.EaseInOutQuad);
        pieChart.setRotationAngle(0);
        pieChart.setRotationEnabled(true);

        pieChart.getLegend().setEnabled(true);
        pieChart.getLegend().setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        pieChart.getLegend().setTypeface((Typeface.create("@font/poppins_semibold", Typeface.BOLD)));
        pieChart.getLegend().setTextColor((ContextCompat.getColor(context, R.color.white)));
        pieChart.getLegend().setFormSize(11f);

        // Redimensionar el tamaño del gráfico para ajustarse a las dimensiones de la pantalla
        ViewGroup.LayoutParams params = pieChart.getLayoutParams();
        params.width = (int) (screenWidth);
        params.height = (int) (screenHeight)/2;
        pieChart.setLayoutParams(params);
    }

    public static void buildPieChart3(@NonNull PieChart pieChart, Context context, @NonNull LinkedHashMap<String, Float> data) {

        ArrayList<PieEntry> entries = new ArrayList<>();

        for (Map.Entry<String, Float> entry : data.entrySet()) {
            entries.add(new PieEntry(entry.getValue(), entry.getKey()));
        }

        PieDataSet dataSet = new PieDataSet(entries, "");

        dataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return value + " t";
            }
        });

        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setValueTextSize(16f);
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setValueTypeface(Typeface.create("@font/poppins_semibold", Typeface.BOLD));
        dataSet.setValueLineColor(Color.BLACK);
        dataSet.setValueLineWidth(21.5f);
        dataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);

        PieData pieData = new PieData(dataSet);

        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setDrawEntryLabels(false);
        pieChart.setUsePercentValues(false);
        pieChart.setHoleRadius(50f);
        pieChart.setTransparentCircleRadius(55f);
        pieChart.setHoleColor(Color.TRANSPARENT);
        pieChart.setCenterText(generateCenterText("5.5"));


        pieChart.animateY(11500, Easing.EaseInOutQuad);
        pieChart.setRotationAngle(0);
        pieChart.setRotationEnabled(true);

        Legend legend = pieChart.getLegend();
        legend.setEnabled(true);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setTypeface(Typeface.create("@font/poppins_semibold", Typeface.BOLD));
        legend.setTextColor(Color.WHITE);
        legend.setTextSize(14f);
        legend.setFormSize(11f);
        legend.setXEntrySpace(7f);
        legend.setYEntrySpace(10f);

        // Redimensionar el tamaño del gráfico para ajustarse a las dimensiones de la pantalla
        /*ViewGroup.LayoutParams params = pieChart.getLayoutParams();
        pieChart.setLayoutParams(params);*/

    }

    private static SpannableString generateCenterText(String total) {
        SpannableString s = new SpannableString(total+" t"+"\nCO2/año");
        s.setSpan(new RelativeSizeSpan(2.7f), 0, 5, 0);
        s.setSpan(new StyleSpan(Typeface.BOLD), 0, 5, 0);;
        s.setSpan(new ForegroundColorSpan(ColorTemplate.rgb("#FFFFFF")), s.length() - 13, s.length(), 0);
        s.setSpan(new StyleSpan(Typeface.NORMAL), 5, 13, 0);;
        return s;
    }

    public static void buildPieChart2(@NonNull PieChart pieChart, Context context, int screenWidth, int screenHeight, @NonNull LinkedHashMap<String, Float> data) {

        ArrayList<PieEntry> entries = new ArrayList<>();

        for (Map.Entry<String, Float> entry : data.entrySet()) {
            entries.add(new PieEntry(entry.getValue(), entry.getKey()));
        }

        PieDataSet dataSet = new PieDataSet(entries, "");

        dataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return value + " t";
            }
        });

        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setValueTextSize(16f);
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setValueTypeface(Typeface.create("@font/poppins_semibold", Typeface.BOLD));
        dataSet.setValueLineColor(Color.BLACK);
        dataSet.setValueLineWidth(21.5f);
        dataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);

        PieData pieData = new PieData(dataSet);

        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setDrawEntryLabels(false);
        pieChart.setUsePercentValues(false);
        pieChart.setHoleRadius(50f);
        pieChart.setTransparentCircleRadius(55f);
        pieChart.setHoleColor(Color.TRANSPARENT);
        pieChart.setCenterText(generateCenterText("5.5"));


        pieChart.animateY(11500, Easing.EaseInOutQuad);
        pieChart.setRotationAngle(0);
        pieChart.setRotationEnabled(true);

        Legend legend = pieChart.getLegend();
        legend.setEnabled(true);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setTypeface(Typeface.create("@font/poppins_semibold", Typeface.BOLD));
        legend.setTextColor(Color.WHITE);
        legend.setTextSize(14f);
        legend.setFormSize(11f);
        legend.setXEntrySpace(7f);
        legend.setYEntrySpace(10f);

        // Redimensionar el tamaño del gráfico para ajustarse a las dimensiones de la pantalla
        ViewGroup.LayoutParams params = pieChart.getLayoutParams();
        params.width = screenWidth;
        params.height = screenHeight / 2;
        pieChart.setLayoutParams(params);

    }

    public static void buildHistoricalLineChart(@NonNull LineChart lineChart, Context context, int screenWidth, int screenHeight, @NonNull List<Entry> entries) {

        LineDataSet dataSet = new LineDataSet(entries, "");
        dataSet.setDrawCircles(false);
        dataSet.setDrawValues(false);
        dataSet.setLineWidth(2f);
        dataSet.setColor(ContextCompat.getColor(context, R.color.pastelBlue));

        LineData lineData = new LineData(dataSet);
        lineChart.setData(lineData);

        lineChart.getDescription().setEnabled(false);
        lineChart.getLegend().setEnabled(false);
        lineChart.setTouchEnabled(false);
        lineChart.setScaleEnabled(false);
        lineChart.setDragEnabled(false);
        lineChart.setPinchZoom(false);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setDrawLabels(true);
        xAxis.setDrawAxisLine(false);
        xAxis.setTextSize(10f);
        xAxis.setTextColor(ContextCompat.getColor(context, R.color.pastelBlue));

        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setDrawAxisLine(false);
        leftAxis.setDrawLabels(false);
        leftAxis.setAxisMinimum(0f);

        YAxis rightAxis = lineChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setDrawAxisLine(false);
        rightAxis.setDrawLabels(false);
        rightAxis.setAxisMinimum(0f);

        // Redimensionar el tamaño del gráfico para ajustarse a las dimensiones de la pantalla
        ViewGroup.LayoutParams params = lineChart.getLayoutParams();
        params.width = (int) (screenWidth);
        params.height = (int) (screenHeight) / 2;
        lineChart.setLayoutParams(params);
    }

    public static void buildHistoricalLineChart2(@NonNull LineChart lineChart, Context context, int screenWidth, int screenHeight, @NonNull List<Entry> entries) {

        LineDataSet dataSet = new LineDataSet(entries, "");

        dataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return value + " t";
            }
        });

        //dataSet.setDrawCircles(false);
        //dataSet.setDrawValues(false);
        dataSet.setLineWidth(2f);
        dataSet.setColor(ContextCompat.getColor(context, R.color.pastelBlue));
        dataSet.setValueTextSize(16f);
        dataSet.setValueTextColor(Color.WHITE);
        dataSet.setCircleHoleColor(Color.BLUE);
        dataSet.setCircleRadius(5f);

        LineData lineData = new LineData(dataSet);
        lineChart.setData(lineData);

        lineChart.getDescription().setEnabled(false);
        lineChart.getLegend().setEnabled(false);
        lineChart.setTouchEnabled(false);
        lineChart.setScaleEnabled(false);
        lineChart.setDragEnabled(false);
        lineChart.setPinchZoom(false);
        lineChart.animateX(1500);

        lineChart.setMaxVisibleValueCount(20);


        XAxis xAxis = lineChart.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setDrawLabels(true);
        xAxis.setDrawAxisLine(false);
        xAxis.setTextSize(12f);
        xAxis.setTextColor(ContextCompat.getColor(context, R.color.pastelBlue));
        xAxis.setTypeface(Typeface.create("@font/poppins_semibold", Typeface.BOLD));

        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setDrawAxisLine(false);
        leftAxis.setDrawLabels(false);
        leftAxis.setAxisMinimum(0f);

        YAxis rightAxis = lineChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setDrawAxisLine(false);
        rightAxis.setDrawLabels(false);
        rightAxis.setAxisMinimum(0f);



        // Actualizar la gráfica
        lineChart.invalidate();

        // Redimensionar el tamaño del gráfico para ajustarse a las dimensiones de la pantalla
        ViewGroup.LayoutParams params = lineChart.getLayoutParams();
        params.width = (int) (screenWidth);
        params.height = (int) (screenHeight) / 2;
        lineChart.setLayoutParams(params);

        // Añadir animación de entrada
        lineChart.animateX(1000, Easing.EaseInOutQuad);
    }

    public static void buildHistoricalLineChart4(@NonNull LineChart lineChart, Context context, int screenWidth, int screenHeight, @NonNull LinkedHashMap<String, Float> data) {

        // Crear lista de objetos Entry a partir de los datos de la base de datos
        /*List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            Date fecha = convertDateStringToDate(data.get(i).get("fecha_creacion"));
            float valor = Float.parseFloat(data.get(i).get("valor"));
            entries.add(new Entry(fecha.getTime(), valor));
        }*/

        List<Entry> entries = new ArrayList<>();

        int index = 0;
        for (Map.Entry<String, Float> entry : data.entrySet()) {
            entries.add(new BarEntry(index++, entry.getValue(), entry.getKey()));
        }

        LineDataSet dataSet = new LineDataSet(entries, "");

        dataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return value + " t";
            }
        });

        //dataSet.setDrawCircles(false);
        //dataSet.setDrawValues(false);
        dataSet.setLineWidth(2f);
        dataSet.setColor(ContextCompat.getColor(context, R.color.pastelBlue));
        dataSet.setValueTextSize(16f);
        dataSet.setValueTextColor(Color.WHITE);
        dataSet.setCircleHoleColor(Color.BLUE);
        dataSet.setCircleRadius(5f);

        LineData lineData = new LineData(dataSet);
        lineChart.setData(lineData);

        lineChart.getDescription().setEnabled(false);
        lineChart.getLegend().setEnabled(false);
        lineChart.setTouchEnabled(false);
        lineChart.setScaleEnabled(false);
        lineChart.setDragEnabled(false);
        lineChart.setPinchZoom(false);
        lineChart.animateX(1500);

        lineChart.setMaxVisibleValueCount(3);


        XAxis xAxis = lineChart.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setDrawLabels(true);
        xAxis.setDrawAxisLine(false);
        xAxis.setTextSize(12f);
        xAxis.setTextColor(ContextCompat.getColor(context, R.color.pastelBlue));
        xAxis.setTypeface(Typeface.create("@font/poppins_semibold", Typeface.BOLD));

        // Etiquetar el eje x con el formato de fecha deseado
        xAxis.setValueFormatter(new IndexAxisValueFormatter(data.keySet()));
        /**xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                Date fecha = new Date((long) value);
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                return sdf.format(fecha);
            }
        });**/

        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setDrawAxisLine(false);
        leftAxis.setDrawLabels(false);
        leftAxis.setAxisMinimum(0f);

        YAxis rightAxis = lineChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setDrawAxisLine(false);
        rightAxis.setDrawLabels(false);
        rightAxis.setAxisMinimum(0f);

        // Actualizar la gráfica
        lineChart.invalidate();

        // Redimensionar el tamaño del gráfico para ajustarse a las dimensiones de la pantalla
        ViewGroup.LayoutParams params = lineChart.getLayoutParams();
        params.width = (int) (screenWidth);
        params.height = (int) (screenHeight) / 2;
        lineChart.setLayoutParams(params);

        // Añadir animación de entrada
        lineChart.animateX(1000, Easing.EaseInOutQuad);


    }

    public static void buildHistoricalLineChart5(@NonNull LineChart lineChart, Context context, int screenWidth, int screenHeight, @NonNull LinkedHashMap<String, Float> data) {

        List<Entry> entries = new ArrayList<>();
        int index = 0;
        for (Map.Entry<String, Float> entry : data.entrySet()) {
            entries.add(new BarEntry(index++, entry.getValue(), entry.getKey()));
        }

        LineDataSet dataSet = new LineDataSet(entries, "");

        dataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return value + " t";
            }
        });

        dataSet.setLineWidth(1.75f);
        dataSet.setCircleRadius(5f);
        dataSet.setCircleHoleRadius(2.5f);
        dataSet.setColor(Color.WHITE);
        dataSet.setCircleColor(Color.WHITE);
        dataSet.setHighLightColor(Color.WHITE);
        dataSet.setDrawValues(true);

        dataSet.setValueTextColor(Color.WHITE);
        dataSet.setValueTextSize(16f);
        dataSet.enableDashedLine(30f, 30f, 0f);

        // draw points as solid circles
        dataSet.setDrawCircleHole(true);

        // customize legend entry
        dataSet.setFormLineWidth(1f);
        dataSet.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
        dataSet.setFormSize(15.f);





        LineData lineData = new LineData(dataSet);
        lineChart.setData(lineData);

        lineChart.getDescription().setEnabled(false);
        lineChart.getLegend().setEnabled(false);
        lineChart.setTouchEnabled(false);
        lineChart.setScaleEnabled(false);
        lineChart.setDragEnabled(false);
        lineChart.setPinchZoom(false);
        lineChart.animateX(1500);



        //lineChart.setMaxVisibleValueCount(3);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGridColor(Color.WHITE);
        xAxis.setGranularityEnabled(true);
        xAxis.setGranularity(1f);
        xAxis.setDrawLabels(true);
        xAxis.setDrawAxisLine(false);
        xAxis.setTextSize(12f);
        xAxis.setAxisLineWidth(0f);
        xAxis.setAxisMinimum(-0.3f);
        xAxis.setAxisMaximum(+2.3f);
        xAxis.setTextColor(ContextCompat.getColor(context, R.color.white));
        xAxis.setTypeface(Typeface.create("@font/poppins_semibold", Typeface.BOLD));
        xAxis.setValueFormatter(new IndexAxisValueFormatter(data.keySet()));

        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.setDrawGridLines(true);
        leftAxis.setDrawAxisLine(true);
        leftAxis.setDrawLabels(true);
        leftAxis.setTextSize(16f);
        leftAxis.setTypeface(Typeface.create("@font/poppins_semibold", Typeface.BOLD));
        leftAxis.setAxisLineColor(Color.WHITE);
        leftAxis.setTextColor(Color.WHITE);
        leftAxis.setAxisLineColor(Color.WHITE);
        leftAxis.setAxisMaximum(24f);
        leftAxis.setAxisMinimum(0f);

        YAxis rightAxis = lineChart.getAxisRight();
        rightAxis.setDrawGridLines(true);
        rightAxis.setDrawAxisLine(true);
        rightAxis.setDrawLabels(false);
        rightAxis.setAxisLineColor(Color.WHITE);
        rightAxis.setTextColor(Color.WHITE);
        rightAxis.setAxisLineColor(Color.WHITE);
        rightAxis.setAxisMinimum(0f);

        lineChart.invalidate();

        ViewGroup.LayoutParams params = lineChart.getLayoutParams();
        params.width = screenWidth;
        params.height = screenHeight / 2;
        lineChart.setLayoutParams(params);

        lineChart.animateX(1000, Easing.EaseInOutQuad);
    }


    public static Date convertDateStringToDate(String dateString) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            return sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static void buildHistoricalLineChart3(@NonNull LineChart lineChart, Context context, int screenWidth, int screenHeight, @NonNull List<Entry> entries) {

        for (int i = 0; i < entries.size(); i++) {
        float val1 = entries.get(i).getY();
        float val2 = entries.get(i + 1).getY();
        float val3 = entries.get(i + 2).getY();

        entries.add(new BarEntry(
                i,
                new float[]{val1, val2, val3}));
    }
    }

    public static void buildTableLayout(@NonNull TableLayout tableLayout, Context context, @NonNull LinkedHashMap<String, Float> data) {

        // Crear una fila para las cabeceras de la tabla
        TableRow headerRow = new TableRow(context);

        // Crear las celdas de las cabeceras y agregarlas a la fila
        TextView headerTitle = new TextView(context);
        headerTitle.setText("Título");
        headerTitle.setPadding(20, 20, 20, 20);
        headerTitle.setTextColor(Color.BLACK);
        headerTitle.setTypeface(Typeface.DEFAULT_BOLD);
        headerRow.addView(headerTitle);

        TextView headerValue = new TextView(context);
        headerValue.setText("Valor");
        headerValue.setPadding(20, 20, 20, 20);
        headerValue.setTextColor(Color.BLACK);
        headerValue.setTypeface(Typeface.DEFAULT_BOLD);
        headerRow.addView(headerValue);

        // Agregar la fila de cabecera a la tabla
        tableLayout.addView(headerRow);

        // Agregar una fila para cada entrada en el mapa de datos
        for (Map.Entry<String, Float> entry : data.entrySet()) {
            TableRow row = new TableRow(context);

            // Crear las celdas de título y valor y agregarlas a la fila
            TextView title = new TextView(context);
            title.setText(entry.getKey());
            title.setPadding(20, 20, 20, 20);
            title.setTextColor(Color.BLACK);
            row.addView(title);

            TextView value = new TextView(context);
            value.setText(entry.getValue() + " t");
            value.setPadding(20, 20, 20, 20);
            value.setTextColor(Color.BLACK);
            row.addView(value);

            // Agregar la fila a la tabla
            tableLayout.addView(row);
        }
    }

    public static void buildTableLayout2(@NonNull TableLayout tableLayout, Context context, @NonNull LinkedHashMap<String, Float> data) {

        // Crear una fila para las cabeceras de la tabla
        TableRow headerRow = new TableRow(context);

        // Crear las celdas de las cabeceras y agregarlas a la fila
        TextView headerTitle = new TextView(context);
        headerTitle.setText("Título");
        headerTitle.setPadding(dpToPx(context, 16), dpToPx(context, 16), dpToPx(context, 16), dpToPx(context, 16));
        headerTitle.setTextColor(Color.WHITE);
        headerTitle.setTypeface(Typeface.DEFAULT_BOLD);
        headerTitle.setBackgroundResource(R.drawable.small_container);
        headerRow.addView(headerTitle);

        TextView headerValue = new TextView(context);
        headerValue.setText("Valor");
        headerValue.setPadding(dpToPx(context, 16), dpToPx(context, 16), dpToPx(context, 16), dpToPx(context, 16));
        headerValue.setTextColor(Color.WHITE);
        headerValue.setTypeface(Typeface.DEFAULT_BOLD);
        headerValue.setBackgroundResource(R.drawable.small_container);
        headerRow.addView(headerValue);

        // Agregar la fila de cabecera a la tabla
        tableLayout.addView(headerRow);

        // Agregar una fila para cada entrada en el mapa de datos
        for (Map.Entry<String, Float> entry : data.entrySet()) {
            TableRow row = new TableRow(context);

            // Crear las celdas de título y valor y agregarlas a la fila
            TextView title = new TextView(context);
            title.setText(entry.getKey());
            title.setPadding(dpToPx(context, 16), dpToPx(context, 16), dpToPx(context, 16), dpToPx(context, 16));
            title.setTextColor(context.getResources().getColor(R.color.pastelBlue));
            title.setBackgroundResource(R.drawable.small_container);
            row.addView(title);

            TextView value = new TextView(context);
            value.setText(String.format("%.2f t", entry.getValue()));
            value.setPadding(dpToPx(context, 16), dpToPx(context, 16), dpToPx(context, 16), dpToPx(context, 16));
            value.setTextColor(context.getResources().getColor(R.color.pastelBlue));
            value.setBackgroundResource(R.drawable.small_container);
            row.addView(value);

            // Agregar la fila a la tabla
            tableLayout.addView(row);
        }
    }

    public static void buildStackedBarChart(@NonNull BarChart barChart, Context context, int screenWidth, int screenHeight,ArrayList<String> labels, ArrayList<BarEntry> entries) {
        // Crear el conjunto de datos para la gráfica
        BarDataSet barDataSet = new BarDataSet(entries, "");
        barDataSet.setDrawValues(true);
        barDataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
        barChart.getDescription().setText("");


        // Configurar detalles del conjunto de datos
        barDataSet.setDrawValues(false);
        barDataSet.setStackLabels(new String[]{"Hogar", "Comida", "Ropa", "Transporte", "Tecnología"});

        barDataSet.setValueTextColor(Color.WHITE);
        barDataSet.setValueTextSize(16f);
        barDataSet.setBarBorderColor(Color.WHITE);

        // Agregar el conjunto de datos a la gráfica
        BarData barData = new BarData(barDataSet);

        // Configurar detalles de la gráfica
        barChart.setData(barData);
        barChart.setFitBars(true);
        barChart.getDescription().setText("");
        barChart.animateY(6000);
        barChart.getXAxis().setDrawLabels(true);
        barChart.getXAxis().setDrawAxisLine(true);
        barChart.getXAxis().setAxisLineColor(Color.WHITE);
        barChart.getXAxis().setTextColor(Color.WHITE);
        barChart.getXAxis().setGridColor(Color.WHITE);
        barChart.getAxisLeft().setTextSize(18f);
        barChart.getAxisLeft().setTextColor(ContextCompat.getColor(context, R.color.white));
        barChart.getAxisLeft().setAxisLineColor(Color.WHITE);
        barChart.getAxisLeft().setGridColor(Color.WHITE);
        barChart.getAxisRight().setDrawLabels(false);
        barChart.getAxisRight().setAxisLineColor(Color.WHITE);
        barChart.getAxisRight().setGridColor(Color.WHITE);
        barChart.getLegend().setTextColor((ContextCompat.getColor(context, R.color.white)));
        barChart.setExtraOffsets(5, 10, 5, 5);
        barChart.setExtraOffsets(20.f, 0.f, 20.f, 0.f);

        barChart.setDragDecelerationFrictionCoef(0.95f);
        barChart.getLegend().setFormSize(11f); // Establecer el tamaño de la leyend

        // Configurar detalles del eje X
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);

        // Configurar detalles del eje Y
        /*YAxis yAxis = barChart.getAxisLeft();
        yAxis.setDrawGridLines(false);
        yAxis.setDrawAxisLine(true);
        yAxis.setDrawLabels(true);*/

        YAxis yAxis = barChart.getAxisLeft();
        yAxis.setDrawGridLines(false);
        yAxis.setDrawAxisLine(true);
        yAxis.setDrawLabels(true);
        //yAxis.setValueFormatter(new CustomValueFormatter(labels));

        Legend legend = barChart.getLegend();
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setTypeface((Typeface.create("@font/poppins_semibold", Typeface.BOLD))); // Cambiar la fuente
        legend.setFormSize(18f); // Establecer el tamaño de la leyenda
        legend.setTextSize(14f);

        // Actualizar la gráfica
        barChart.invalidate();


        /*ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(screenWidth, screenHeight / 2);

        barChart.setLayoutParams(params);*/

        ViewGroup.LayoutParams params = barChart.getLayoutParams();
        params.width = (int) (screenWidth);
        params.height = (int) (screenHeight) / 2;
        barChart.setLayoutParams(params);

    }

    public static void buildStackedBarChart2(@NonNull BarChart barChart, Context context, int screenWidth, int screenHeight, ArrayList<String> labels, ArrayList<BarEntry> entries) {

        BarDataSet barDataSet = new BarDataSet(entries, "");
        barDataSet.setStackLabels(new String[]{"Hogar", "Comida", "Ropa", "Transporte", "Tecnología"});

        barDataSet.setDrawValues(true);
        barDataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
        barChart.getDescription().setText("");
        barChart.setDrawValueAboveBar(false);

        barDataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return value + " t"+entries.get(1).getY();
            }
        });

        Legend legend = barChart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
        legend.setFormToTextSpace(4f);
        legend.setXEntrySpace(6f);
        legend.setTypeface((Typeface.create("@font/poppins_semibold", Typeface.BOLD))); // Cambiar la fuente
        legend.setFormSize(18f); // Establecer el tamaño de la leyenda
        legend.setTextSize(14f);

        barDataSet.setValueTextColor(Color.WHITE);
        barDataSet.setValueTextSize(16f);
        barDataSet.setBarBorderColor(Color.WHITE);

        barChart.getXAxis().setCenterAxisLabels(true); // Establecer la posición de las etiquetas debajo de cada barra

        BarData barData = new BarData(barDataSet);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("");
        barChart.animateY(6000);
        barChart.getXAxis().setDrawAxisLine(true);
        barChart.getXAxis().setAxisLineColor(Color.WHITE);
        barChart.getXAxis().setTextColor(Color.WHITE);
        barChart.getXAxis().setGridColor(Color.WHITE);
        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.getAxisLeft().setTextSize(18f);
        barChart.getAxisLeft().setTextColor(ContextCompat.getColor(context, R.color.white));
        barChart.getAxisLeft().setAxisLineColor(Color.WHITE);
        barChart.getAxisLeft().setGridColor(Color.WHITE);
        barChart.getAxisRight().setDrawLabels(false);
        barChart.getAxisRight().setAxisLineColor(Color.WHITE);
        barChart.getAxisRight().setGridColor(Color.WHITE);
        barChart.getLegend().setTextColor((ContextCompat.getColor(context, R.color.white)));
        barChart.setExtraOffsets(5, 10, 5, 5);
        barChart.setExtraOffsets(20.f, 0.f, 20.f, 0.f);

        barChart.setDragDecelerationFrictionCoef(0.95f);
        barChart.getLegend().setFormSize(11f); // Establecer el tamaño de la leyenda

        // Redimensionar el tamaño del gráfico para ajustarse a las dimensiones de la pantalla
        ViewGroup.LayoutParams params = barChart.getLayoutParams();
        params.width = (int) (screenWidth);
        params.height = (int) (screenHeight) / 2;
        barChart.setLayoutParams(params);
    }

    public static void buildLineChart10(@NonNull LineChart lineChart, Context context, int screenWidth, int screenHeight, @NonNull ArrayList<DateValueEntry> entries) {


        ArrayList<Entry> chartEntries = new ArrayList<>();
        for (int i = 0; i < entries.size(); i++) {
            chartEntries.add(new Entry(i, entries.get(i).getValue()));
        }

        LineDataSet lineDataSet = new LineDataSet(chartEntries, "Label");

        lineDataSet.setColor(Color.BLUE);
        lineDataSet.setLineWidth(2f);
        lineDataSet.setDrawValues(true);
        lineDataSet.setValueTextColor(Color.BLACK);
        lineDataSet.setValueFormatter(new ValueFormatter() {

                @Override
                public String getFormattedValue(float value) {
                    return value + " t";
                }
            });
           // private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");

            /*@Override
            public String getFormattedValue(float value) {
                int index = (int) value;
                if (index >= 0 && index < entries.size()) {
                    Date date = entries.get(index).getDate();
                    float pointValue = entries.get(index).getValue();
                    return pointValue + " t";

                } else {
                    return "";
                }}});*/

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setValueFormatter(new ValueFormatter() {
            private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");

            @Override
            public String getFormattedValue(float value) {
                int index = (int) value;
                if (index >= 0 && index < entries.size()) {
                    Date date = entries.get(index).getDate();
                    return dateFormat.format(date);
                } else {
                    return "";
                }
            }
        });
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);
        xAxis.setLabelRotationAngle(0);
        xAxis.setLabelCount(entries.size());
        xAxis.setTextColor(Color.WHITE);

        // Añadir la línea de tendencia
        LimitLine trendlineNacional = new LimitLine(5.52f, "Media Nacional: 5.52 CO2/año");
        trendlineNacional.setLineColor(Color.RED);
        trendlineNacional.setLineWidth(2f);
        trendlineNacional.enableDashedLine(10f, 10f, 0f); // Si quieres una línea discontinua
        trendlineNacional.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        trendlineNacional.setTextSize(10f);

        // Añadir la línea de tendencia
        LimitLine trendlineEuropea = new LimitLine(6.8f, "Media Unión Europea: 6.8 CO2/año");
        trendlineEuropea.setLineColor(Color.YELLOW);
        trendlineEuropea.setLineWidth(2f);
        trendlineEuropea.enableDashedLine(10f, 10f, 0f); // Si quieres una línea discontinua
        trendlineEuropea.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        trendlineEuropea.setTextSize(10f);

// Añadir la línea de tendencia al eje izquierdo del gráfico
        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.addLimitLine(trendlineNacional);
        leftAxis.addLimitLine(trendlineEuropea);


        /*LineDataSet lineDataSet = new LineDataSet(entries, "Label");

        lineDataSet.setColor(Color.BLUE);
        lineDataSet.setLineWidth(2f);
        lineDataSet.setDrawValues(true);
        lineDataSet.setValueTextColor(Color.BLACK);



        lineDataSet.setValueFormatter(new ValueFormatter( ) {
            @Override
            public String getFormattedValue(float value) {
                return value + " t";
            }
        });*/

        Legend legend = lineChart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
        legend.setFormToTextSpace(4f);
        legend.setXEntrySpace(6f);
        legend.setTypeface((Typeface.create("@font/poppins_semibold", Typeface.BOLD))); // Cambiar la fuente
        legend.setFormSize(18f); // Establecer el tamaño de la leyenda
        legend.setTextSize(14f);

        lineDataSet.setValueTextColor(Color.WHITE);
        lineDataSet.setValueTextSize(16f);

        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);

        lineChart.getDescription().setText("");
        lineChart.animateY(6000);
        /*lineChart.getXAxis().setDrawAxisLine(true);
        lineChart.getXAxis().setAxisLineColor(Color.WHITE);
        lineChart.getXAxis().setTextColor(Color.WHITE);
        lineChart.getXAxis().setGridColor(Color.WHITE);
        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);*/
        lineChart.getAxisLeft().setTextSize(18f);
        lineChart.getAxisLeft().setTextColor(ContextCompat.getColor(context, R.color.white));
        lineChart.getAxisLeft().setAxisLineColor(Color.WHITE);
        lineChart.getAxisLeft().setGridColor(Color.WHITE);
        lineChart.getAxisRight().setDrawLabels(false);
        lineChart.getAxisRight().setAxisLineColor(Color.WHITE);
        lineChart.getAxisRight().setGridColor(Color.WHITE);
        lineChart.getLegend().setTextColor((ContextCompat.getColor(context, R.color.white)));
        lineChart.setExtraOffsets(5, 10, 5, 5);
        lineChart.setExtraOffsets(20f, 0f, 20f, 0f);

        lineChart.setDragDecelerationFrictionCoef(0.95f);
        lineChart.getLegend().setFormSize(11f); // Establecer el tamaño de la leyenda

        // Redimensionar el tamaño del gráfico para ajustarse a las dimensiones de la pantalla
        ViewGroup.LayoutParams params = lineChart.getLayoutParams();
        params.width = (int) (screenWidth);
        params.height = (int) (screenHeight) / 2;
        lineChart.setLayoutParams(params);
    }

    public static void buildLineChart11(@NonNull LineChart lineChart, Context context, int screenWidth, int screenHeight, @NonNull ArrayList<DateValueEntry> entries) {

        ArrayList<Entry> chartEntries = new ArrayList<>();
        for (int i = 0; i < entries.size(); i++) {
            chartEntries.add(new Entry(i, entries.get(i).getValue()));
        }

        LineDataSet lineDataSet = new LineDataSet(chartEntries, "Label");

        lineDataSet.setColor(ContextCompat.getColor(context, R.color.blue_button));
        lineDataSet.setLineWidth(2f);
        lineDataSet.setDrawValues(true);
        lineDataSet.setValueTextColor(ContextCompat.getColor(context, R.color.white));
        lineDataSet.setValueTextSize(12f);
        lineDataSet.setValueFormatter(new ValueFormatter() {

            @Override
            public String getFormattedValue(float value) {
                return value + " t";
            }
        });

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setValueFormatter(new ValueFormatter() {
            private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");

            @Override
            public String getFormattedValue(float value) {
                int index = (int) value;
                if (index >= 0 && index < entries.size()) {
                    Date date = entries.get(index).getDate();
                    return dateFormat.format(date);
                } else {
                    return "";
                }
            }
        });
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);
        xAxis.setLabelRotationAngle(0);
        xAxis.setLabelCount(entries.size());
        xAxis.setTextColor(ContextCompat.getColor(context, R.color.white));
        xAxis.setTextSize(12f);

        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);

        // Añadir la línea de tendencia
        LimitLine trendlineNacional = new LimitLine(5.52f, "Media Nacional: 5.52 CO2/año");
        trendlineNacional.setLineColor(ContextCompat.getColor(context, R.color.pastelRed));
        trendlineNacional.setLineWidth(2f);
        trendlineNacional.enableDashedLine(10f, 10f, 0f); // Si quieres una línea discontinua
        trendlineNacional.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        trendlineNacional.setTextSize(10f);
        trendlineNacional.setTypeface(Typeface.create("@font/poppins_medium", Typeface.NORMAL));

        // Añadir la línea de tendencia
        LimitLine trendlineEuropea = new LimitLine(6.8f, "Media Unión Europea: 6.8 CO2/año");
        trendlineEuropea.setLineColor(ContextCompat.getColor(context, R.color.pastelYellow));
        trendlineEuropea.setLineWidth(2f);
        trendlineEuropea.enableDashedLine(10f, 10f, 0f); // Si quieres una línea discontinua
        trendlineEuropea.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        trendlineEuropea.setTextSize(10f);
        trendlineEuropea.setTypeface(Typeface.create("@font/poppins_medium", Typeface.NORMAL));

        // Añadir la línea de tendencia al eje izquierdo del gráfico
        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.addLimitLine(trendlineNacional);
        leftAxis.addLimitLine(trendlineEuropea);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setDrawGridLines(true);
        leftAxis.setGridColor(ContextCompat.getColor(context, R.color.white));
        leftAxis.setDrawAxisLine(false);

        // Redimensionar el tamaño del gráfico para ajustarse a las dimensiones de la pantalla
        ViewGroup.LayoutParams params = lineChart.getLayoutParams();
        params.width = (int) (screenWidth);
        params.height = (int) (screenHeight) / 2;
        lineChart.setLayoutParams(params);

    }

    public static void buildLineChart12(@NonNull LineChart lineChart, Context context, int screenWidth, int screenHeight, @NonNull ArrayList<DateValueEntry> entries) {

        // Convertir las entradas a objetos Entry
        ArrayList<Entry> chartEntries = new ArrayList<>();
        for (int i = 0; i < entries.size(); i++) {
            chartEntries.add(new Entry(i, entries.get(i).getValue()));
        }

        // Configurar la serie de datos de línea
        LineDataSet lineDataSet = new LineDataSet(chartEntries, "Label");
        lineDataSet.setColor(Color.BLUE);
        lineDataSet.setLineWidth(3f);
        lineDataSet.setDrawCircles(false);
        lineDataSet.setDrawValues(false);
        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);

        // Configurar el eje x
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(entries.size());
        xAxis.setTextColor(Color.BLACK);
        xAxis.setTextSize(14f);
        xAxis.setLabelRotationAngle(-45f); // Rotar las etiquetas del eje x

        // Configurar el eje y izquierdo
        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.setTextSize(14f);
        leftAxis.setTextColor(Color.BLACK);
        leftAxis.setAxisLineColor(Color.BLACK);
        leftAxis.setGridColor(Color.LTGRAY);

        // Configurar la leyenda
        Legend legend = lineChart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
        legend.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
        legend.setFormSize(12f);
        legend.setTextSize(14f);

        // Configurar la línea de datos
        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);

        // Configurar el gráfico en sí
        lineChart.getDescription().setEnabled(false);
        lineChart.animateY(1000, Easing.EaseInOutQuart);
        lineChart.setDrawGridBackground(false);
        lineChart.setTouchEnabled(true);
        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(true);
        lineChart.setPinchZoom(true);
        lineChart.setBackgroundColor(Color.WHITE);
        lineChart.setExtraOffsets(16f, 0f, 16f, 24f);

        // Redimensionar el tamaño del gráfico para ajustarse a las dimensiones de la pantalla
        ViewGroup.LayoutParams params = lineChart.getLayoutParams();
        params.width = screenWidth;
        params.height = screenHeight / 2;
        lineChart.setLayoutParams(params);
    }

    public static void buildLineChart13(@NonNull LineChart lineChart, Context context, int screenWidth, int screenHeight, @NonNull ArrayList<DateValueEntry> entries) {

        ArrayList<Entry> chartEntries = new ArrayList<>();
        for (int i = 0; i < entries.size(); i++) {
            chartEntries.add(new Entry(i, entries.get(i).getValue()));
        }

        LineDataSet lineDataSet = new LineDataSet(chartEntries, "Label");
        lineDataSet.setColor(ContextCompat.getColor(context, R.color.chart_color));
        lineDataSet.setLineWidth(2.5f);
        lineDataSet.setDrawValues(false);
        lineDataSet.setCircleColor(ContextCompat.getColor(context, R.color.chart_color));
        lineDataSet.setCircleRadius(5f);
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setDrawFilled(true);
        lineDataSet.setFillDrawable(ContextCompat.getDrawable(context, R.drawable.gradient));
        lineDataSet.setHighlightEnabled(false);
        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);

        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);

        lineChart.getDescription().setEnabled(false);
        lineChart.setDrawGridBackground(false);
        lineChart.setDrawBorders(false);
        lineChart.setTouchEnabled(false);
        lineChart.setScaleEnabled(false);
        lineChart.getLegend().setEnabled(false);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(ContextCompat.getColor(context, R.color.chart_color));
        xAxis.setTextSize(12f);
        xAxis.setGranularityEnabled(true);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new ValueFormatter() {
            private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");

            @Override
            public String getFormattedValue(float value) {
                int index = (int) value;
                if (index >= 0 && index < entries.size()) {
                    Date date = entries.get(index).getDate();
                    return dateFormat.format(date);
                } else {
                    return "";
                }
            }
        });

        YAxis yAxis = lineChart.getAxisLeft();
        yAxis.setDrawGridLines(true);
        yAxis.setGridColor(ContextCompat.getColor(context, R.color.chart_grid));
        yAxis.setAxisLineColor(ContextCompat.getColor(context, R.color.chart_color));
        yAxis.setTextColor(ContextCompat.getColor(context, R.color.chart_color));
        yAxis.setTextSize(12f);
        yAxis.setGranularityEnabled(true);
        yAxis.setGranularity(1f);

        lineChart.getAxisRight().setEnabled(false);

        // Redimensionar el tamaño del gráfico para ajustarse a las dimensiones de la pantalla
        ViewGroup.LayoutParams params = lineChart.getLayoutParams();
        params.width = (int) (screenWidth * 0.9);
        params.height = (int) (screenHeight * 0.4);
        lineChart.setLayoutParams(params);

        lineChart.animateX(1500);
    }



    public static class DateValueEntry {

        private Date date;
        private float value;

        public DateValueEntry(Date date, float value) {
            this.date = date;
            this.value = value;
        }

        public Date getDate() {
            return date;
        }

        public float getValue() {
            return value;
        }
    }



    public static class CustomValueFormatter extends ValueFormatter {
        private final ArrayList<String> labels;

        public CustomValueFormatter(ArrayList<String> labels) {
            this.labels = labels;
        }

        @Override
        public String getFormattedValue(float value) {
            int index = (int) value;
            if (index >= 0 && index < labels.size()) {
                return labels.get(index);
            } else {
                return "";
            }
        }
    }





    // Convierte un valor DP a píxeles
    private static int dpToPx(@NonNull Context context, int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }



}



