package com.ams.sustainability.model.graph;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.ams.sustainability.R;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.Description;
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
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.DefaultValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.renderer.PieChartRenderer;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ChartBuilder {

    public static void buildBarChart(@NonNull BarChart barChart, Context context, int screenWidth, int screenHeight, @NonNull LinkedHashMap<String, Float> data) {

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

        barDataSet.setValueFormatter(new DefaultValueFormatter(1) {
            @Override
            public String getFormattedValue(float value) {
                return super.getFormattedValue(value).replace(".", ",")+" t";
            }
        });

        Legend legend = barChart.getLegend();
        legend.setCustom(entries);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setTypeface((Typeface.create("@font/poppins_semibold", Typeface.BOLD))); // Cambiar la fuente
        legend.setFormSize(18f); // Establecer el tamaño de la leyenda
        legend.setTextSize(14f);
        legend.setXOffset(-1f);

        barDataSet.setValueTextColor(Color.WHITE);
        barDataSet.setValueTextSize(16f);
        barDataSet.setBarBorderColor(Color.WHITE);

        BarData barData = new BarData(barDataSet);

        barChart.setDrawBorders(true);
        barChart.setBorderColor(Color.WHITE);


        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("");
        barChart.animateY(4000);
        barChart.getXAxis().setDrawLabels(false);
        barChart.getXAxis().setDrawAxisLine(false);
        barChart.getXAxis().setDrawGridLines(false);
        barChart.getAxisLeft().setTextSize(18f);
        barChart.getAxisLeft().setTextColor(ContextCompat.getColor(context, R.color.white));
        barChart.getAxisLeft().setDrawAxisLine(false);
        barChart.getAxisLeft().setDrawGridLines(false);
        barChart.getAxisLeft().setAxisMinimum(0f);
        barChart.getAxisRight().setDrawLabels(false);
        barChart.getAxisRight().setDrawAxisLine(false);
        barChart.getAxisRight().setDrawGridLines(false);

        barChart.getAxisRight().setAxisLineColor(Color.WHITE);
        barChart.getAxisRight().setGridColor(Color.WHITE);
        barChart.getLegend().setTextColor((ContextCompat.getColor(context, R.color.white)));
        barChart.setExtraOffsets(20.f, 20.f, 20.f, 20.f);

        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.setValueFormatter(new IntegerValueFormatter());

        barChart.setDragDecelerationFrictionCoef(0.95f);
        barChart.getLegend().setFormSize(11f); // Establecer el tamaño de la leyenda

        barChart.invalidate();

        // Redimensionar el tamaño del gráfico para ajustarse a las dimensiones de la pantalla
        ViewGroup.LayoutParams params = barChart.getLayoutParams();
        params.width = (int) (screenWidth);
        params.height = (int) (screenHeight) / 2;
        barChart.setLayoutParams(params);
    }

    public static void buildPieChart(@NonNull PieChart pieChart, Context context, int screenWidth, int screenHeight, @NonNull LinkedHashMap<String, Float> data, String goal) {

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

        dataSet.setValueFormatter(new DefaultValueFormatter(1) {
            @Override
            public String getFormattedValue(float value) {
                return super.getFormattedValue(value).replace(".", ",")+" t";
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
        pieChart.setCenterText(generateCenterText(goal));

        pieChart.animateY(5500, Easing.EaseInOutQuad);
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

        pieChart.invalidate();

        // Redimensionar el tamaño del gráfico para ajustarse a las dimensiones de la pantalla
        ViewGroup.LayoutParams params = pieChart.getLayoutParams();
        params.width = screenWidth;
        params.height = screenHeight / 2;
        pieChart.setLayoutParams(params);

    }

    public static void buildLineChart(@NonNull LineChart lineChart, Context context, int screenWidth, int screenHeight, @NonNull ArrayList<DateValueEntry> entries) {

        ArrayList<Entry> chartEntries = new ArrayList<>();
        for (int i = 0; i < entries.size(); i++) {
            chartEntries.add(new Entry(i, entries.get(i).getValue()));
        }

        LineDataSet lineDataSet = new LineDataSet(chartEntries, "");
        lineDataSet.setColor(Color.BLUE);
        lineDataSet.setLineWidth(2f);
        lineDataSet.setDrawValues(false);
        lineDataSet.setValueTextColor(Color.BLACK);
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

        int visibleRange = 10;

        lineChart.setVisibleXRangeMaximum(visibleRange);
        lineChart.setVisibleXRangeMinimum(visibleRange);
        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(false);

        lineChart.setOnChartGestureListener(new OnChartGestureListener() {
            @Override
            public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {}

            @Override
            public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {}

            @Override
            public void onChartLongPressed(MotionEvent me) {}

            @Override
            public void onChartDoubleTapped(MotionEvent me) {}

            @Override
            public void onChartSingleTapped(MotionEvent me) {}

            @Override
            public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {}

            @Override
            public void onChartScale(MotionEvent me, float scaleX, float scaleY) {}

            @Override
            public void onChartTranslate(MotionEvent me, float dX, float dY) {
                // Actualizar los límites de la vista cuando el usuario desplaza el gráfico
                float maxVisibleRange = lineChart.getXChartMax();
                float minVisibleRange = lineChart.getLowestVisibleX();
                if (maxVisibleRange - minVisibleRange < visibleRange) {
                    float newMin = Math.max(0, maxVisibleRange - visibleRange);
                    lineChart.setVisibleXRangeMaximum(visibleRange);
                    lineChart.setVisibleXRangeMinimum(visibleRange);
                    lineChart.moveViewToX(newMin);
                }
            }
        });

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);
        xAxis.setLabelRotationAngle(-90);
        xAxis.setLabelCount(entries.size());
        xAxis.setTextColor(Color.WHITE);
        xAxis.setTextSize(12f);
        xAxis.setYOffset(15f);

        // Añadir la línea de tendencia
        LimitLine trendlineNacional = new LimitLine(5.5f, "Media Nacional: 5,5 t CO2/año");
        trendlineNacional.setTypeface((Typeface.create("@font/poppins_semibold", Typeface.BOLD)));
        trendlineNacional.setTextColor(Color.WHITE);
        trendlineNacional.setLineColor(Color.rgb(135, 206, 235));
        trendlineNacional.setLineWidth(2f);
        trendlineNacional.enableDashedLine(10f, 10f, 0f); // Si quieres una línea discontinua
        trendlineNacional.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        trendlineNacional.setTextSize(12f);


        // Añadir la línea de tendencia
        LimitLine trendlineEuropea = new LimitLine(6.8f, "Media Unión Europea: 6,8 t CO2/año");
        trendlineEuropea.setTypeface((Typeface.create("@font/poppins_semibold", Typeface.BOLD)));
        trendlineEuropea.setTextColor(Color.WHITE);
        trendlineEuropea.setLineColor(Color.rgb(244, 164, 96));
        trendlineEuropea.setLineWidth(2f);
        trendlineEuropea.enableDashedLine(10f, 10f, 0f); // Si quieres una línea discontinua
        trendlineEuropea.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        trendlineEuropea.setTextSize(12f);


        // Añadir la línea de tendencia al eje izquierdo del gráfico
        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.addLimitLine(trendlineNacional);
        leftAxis.addLimitLine(trendlineEuropea);
        leftAxis.setTextSize(18f);
        leftAxis.setTextColor(ContextCompat.getColor(context, R.color.white));
        leftAxis.setAxisLineColor(Color.WHITE);
        leftAxis.setGridColor(Color.WHITE);
       //leftAxis.setAxisMinimum(0f);
        leftAxis.setDrawLabels(true);
        leftAxis.setDrawAxisLine(false);
        leftAxis.setDrawGridLines(false);

        //leftAxis.setAxisMaximum(Math.max(trendlineNacional.getLimit(), trendlineEuropea.getLimit()) * 1.1f);

        lineChart.getLegend().setEnabled(false);

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
        lineChart.animateY(2000);
        lineChart.getXAxis().setDrawAxisLine(true);
        lineChart.getXAxis().setAxisLineColor(Color.WHITE);
        lineChart.getXAxis().setTextColor(Color.WHITE);
        lineChart.getXAxis().setGridColor(Color.WHITE);
        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);

        lineChart.getAxisRight().setDrawAxisLine(false);
        lineChart.getAxisRight().setDrawLabels(false);
        lineChart.getAxisRight().setDrawGridLines(false);
        lineChart.getAxisRight().setAxisLineColor(Color.WHITE);
        lineChart.getAxisRight().setGridColor(Color.WHITE);
        lineChart.getLegend().setTextColor((ContextCompat.getColor(context, R.color.white)));
        lineChart.setExtraOffsets(5, 0, 5, 5);
        lineChart.setExtraOffsets(20f, 0f, 30f, 0f);
        lineChart.setDrawBorders(true);
        lineChart.setBorderColor(Color.WHITE);

        MarketViewLineRadar mv = new MarketViewLineRadar(lineChart.getContext(), R.layout.marker_view);
        mv.setChartView(lineChart); // For bounds control
        lineChart.setMarker(mv); // Set the marker to the chart

        lineChart.setDragDecelerationFrictionCoef(0.95f);
        lineChart.getLegend().setFormSize(11f); // Establecer el tamaño de la leyenda

        lineChart.invalidate();

        // Redimensionar el tamaño del gráfico para ajustarse a las dimensiones de la pantalla

        ViewGroup.LayoutParams params = lineChart.getLayoutParams();
        params.width = (int) (screenWidth);
        params.height = (int) (screenHeight) / 2;
        lineChart.setLayoutParams(params);
    }

    public static void BuildRadarChart(@NonNull RadarChart radarChart, int screenWidth, int screenHeight, @NonNull LinkedHashMap<String, Float> emissionTable) {

        Context context = radarChart.getContext();
        // Define la leyenda del gráfico

        Description description = new Description();
        description.setText("");
        radarChart.setDescription(description);

        radarChart.setWebLineWidth(1f);
        radarChart.setWebColor(Color.WHITE);
        radarChart.setWebLineWidthInner(1f);
        radarChart.setWebColorInner(Color.WHITE);
        radarChart.setWebAlpha(100);

        radarChart.animateXY(2400, 3400, Easing.EaseInOutQuad);

        // Define el eje X del gráfico
        XAxis xAxis = radarChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(emissionTable.keySet().toArray(new String[0])));
        xAxis.setTextSize(14f);
        xAxis.setTypeface((Typeface.create("@font/poppins_semibold", Typeface.BOLD)));
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawLabels(true);
        xAxis.setAxisLineColor(Color.WHITE);
        xAxis.setDrawGridLines(false);
        xAxis.setTextColor(Color.WHITE);

        // Define el eje Y del gráfico
        YAxis yAxis = radarChart.getYAxis();
        yAxis.setAxisMinimum(0f);
        yAxis.setDrawLabels(false);
        yAxis.setGridColor(Color.WHITE);
        yAxis.setDrawAxisLine(false);
        yAxis.setDrawGridLines(false);


        // Crea una lista de valores para el último registro y para la media en España
        List<RadarEntry> lastRecordValues = new ArrayList<>();
        lastRecordValues.add(new RadarEntry(emissionTable.get("Vivienda")));
        lastRecordValues.add(new RadarEntry(emissionTable.get("Comida")));
        lastRecordValues.add(new RadarEntry(emissionTable.get("Transporte")));
        lastRecordValues.add(new RadarEntry(emissionTable.get("ropa")));
        lastRecordValues.add(new RadarEntry(emissionTable.get("Tecnología")));

        List<RadarEntry> spainAverageValues = new ArrayList<>();
        spainAverageValues.add(new RadarEntry(0.4f));
        spainAverageValues.add(new RadarEntry(2.3f));
        spainAverageValues.add(new RadarEntry(1.9f));
        spainAverageValues.add(new RadarEntry(0.5f));
        spainAverageValues.add(new RadarEntry(0.4f));

        RadarDataSet setAvergareSpain = new RadarDataSet(spainAverageValues, "Media nacional");
        setAvergareSpain.setColor(Color.rgb(244, 164, 96));
        setAvergareSpain.setFillColor(Color.rgb(244, 164, 96));
        setAvergareSpain.setDrawFilled(true);
        setAvergareSpain.setFillAlpha(50);
        setAvergareSpain.setLineWidth(2f);
        setAvergareSpain.setDrawHighlightCircleEnabled(true);
        setAvergareSpain.setDrawHighlightIndicators(false);

        // Define los datasets del gráfico
        RadarDataSet lastRecordDataSet = new RadarDataSet(lastRecordValues, "Tú huella");
        lastRecordDataSet.setColor(Color.rgb(135, 206, 235));
        lastRecordDataSet.setFillColor(Color.rgb(135, 206, 235));
        lastRecordDataSet.setDrawFilled(true);
        lastRecordDataSet.setFillAlpha(50);
        lastRecordDataSet.setLineWidth(2f);
        lastRecordDataSet.setDrawHighlightCircleEnabled(true);
        lastRecordDataSet.setDrawHighlightIndicators(false);

        // Define el conjunto de datos del gráfico
        List<IRadarDataSet> dataSets = new ArrayList<>();
        dataSets.add(setAvergareSpain);
        dataSets.add(lastRecordDataSet);

        // Crea el objeto de datos del gráfico
        RadarData radarData = new RadarData(dataSets);
        radarData.setDrawValues(false);

        Legend legend = radarChart.getLegend();
        legend.setEnabled(true);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(true);
        legend.setTypeface((Typeface.create("@font/poppins_semibold", Typeface.BOLD)));
        legend.setTextColor(Color.WHITE);
        legend.setTextSize(14f);
        legend.setStackSpace(10f);
        //legend.setYOffset(15f);

        MarketViewLineRadar mv = new MarketViewLineRadar(radarChart.getContext(), R.layout.marker_view);
        mv.setChartView(radarChart); // For bounds control
        radarChart.setMarker(mv); // Set the marker to the chart

        // Actualiza el gráfico con los nuevos datos
        radarChart.setData(radarData);
        radarChart.invalidate();

        ViewGroup.LayoutParams params = radarChart.getLayoutParams();
        params.width = (int) (screenWidth);
        params.height = (int) (screenHeight) / 2;
        radarChart.setLayoutParams(params);

    }

    public static void buildStackedBarChart(@NonNull BarChart barChart, Context context, int screenWidth, int screenHeight, @NonNull ArrayList<DateValueEntry> entries, ArrayList<BarEntry> entries2) {

        //TextView

        ArrayList<BarEntry> chartEntries = new ArrayList<>();
        for (int i = 0; i < entries.size(); i++) {
            chartEntries.add(new BarEntry(i, new float[]{entries.get(i).getValue()}));
        }


        BarDataSet barDataSet2 = new BarDataSet(entries2, "");
        barDataSet2.setStackLabels(new String[]{"Hogar", "Comida", "Ropa", "Transporte", "Tecnología"});
        barDataSet2.setColors(ColorTemplate.VORDIPLOM_COLORS);
        barDataSet2.setBarBorderColor(Color.WHITE);
        barDataSet2.setDrawValues(false);


        barChart.setDrawBorders(true);
        barChart.setBorderColor(Color.WHITE);

        barDataSet2.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return value + " t";
            }
        });

        BarData barData = new BarData(barDataSet2);
        barData.setBarWidth(0.3f);

        MarketViewStacked mv = new MarketViewStacked(barChart.getContext(), R.layout.marker_view);
        mv.setChartView(barChart); // For bounds control
        barChart.setMarker(mv); // Set the marker to the chart

        XAxis xAxis = barChart.getXAxis();
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
        xAxis.setLabelRotationAngle(-90);
        xAxis.setLabelCount(entries.size());
        xAxis.setTextColor(Color.WHITE);
        xAxis.setTextSize(12f);
        xAxis.setYOffset(15f);

        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.setTextSize(18f);
        leftAxis.setTextColor(ContextCompat.getColor(context, R.color.white));
        leftAxis.setAxisLineColor(Color.WHITE);
        leftAxis.setGridColor(Color.WHITE);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setDrawLabels(true);
        leftAxis.setDrawAxisLine(false);
        leftAxis.setDrawGridLines(false);

        Legend legend = barChart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
        legend.setFormToTextSpace(4f);
        legend.setXEntrySpace(10f);
        legend.setTypeface((Typeface.create("@font/poppins_semibold", Typeface.BOLD)));
        legend.setFormSize(11f); // Establecer el tamaño de la leyenda
        legend.setTextSize(14f);
        legend.setXOffset(-12f);

        barDataSet2.setValueTextColor(Color.WHITE);
        barDataSet2.setValueTextSize(11f);


        barChart.setData(barData);
        barChart.getDescription().setText("");
        barChart.animateY(6000);
        barChart.getXAxis().setDrawAxisLine(true);
        barChart.getXAxis().setAxisLineColor(Color.WHITE);
        barChart.getXAxis().setTextColor(Color.WHITE);
        barChart.getXAxis().setGridColor(Color.WHITE);
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);

        barChart.getAxisRight().setEnabled(false);
        barChart.getLegend().setTextColor((ContextCompat.getColor(context, R.color.white)));
        barChart.setExtraOffsets(5, 10, 5, 5);
        barChart.setExtraOffsets(20f, 0f, 30f, 15f);
        barChart.setDrawBorders(true);

        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);
        xAxis.setLabelRotationAngle(-90);
        xAxis.setLabelCount(entries.size());
        xAxis.setTextSize(14f);
        xAxis.setYOffset(15f);

        leftAxis.setTextSize(18f);
        leftAxis.setTextColor(ContextCompat.getColor(context, R.color.white));
        leftAxis.setAxisLineColor(Color.WHITE);
        leftAxis.setGridColor(Color.WHITE);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setDrawLabels(true);
        leftAxis.setDrawAxisLine(false);
        leftAxis.setDrawGridLines(false);
        leftAxis.setValueFormatter(new IntegerValueFormatter());

        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
        legend.setFormToTextSpace(4f);
        legend.setXEntrySpace(6f);
        legend.setTypeface((Typeface.create("@font/poppins_semibold", Typeface.BOLD))); // Cambiar la fuente
        legend.setFormSize(18f); // Establecer el tamaño de la leyenda
        legend.setTextSize(14f);

        barChart.setData(barData);

        barChart.getDescription().setText("");
        barChart.animateY(6000);
        barChart.getXAxis().setDrawAxisLine(true);
        barChart.getXAxis().setAxisLineColor(Color.WHITE);
        barChart.getXAxis().setTextColor(Color.WHITE);
        barChart.getXAxis().setGridColor(Color.WHITE);

        barChart.setDragEnabled(true); // habilitar el arrastre del gráfico
        barChart.setVisibleXRangeMaximum(10); // establecer el número máximo de valores visibles en el eje X
        barChart.moveViewToX(entries.size() - 10); // establecer el desplazamiento inicial del gráfico

        // Actualizar la gráfica
        barChart.invalidate();

        ViewGroup.LayoutParams params = barChart.getLayoutParams();
        params.width = (int) (screenWidth);
        params.height = (int) (screenHeight) / 2;
        barChart.setLayoutParams(params);

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

    public static class IntegerValueFormatter extends ValueFormatter {
        @Override
        public String getFormattedValue(float value) {
            return String.valueOf(Math.round(value));
        }
    }

    @NonNull
    private static SpannableString generateCenterText(String total) {
        SpannableString s = new SpannableString(total.replace(".",",") + " t" + "\nCO2/año");
        s.setSpan(new RelativeSizeSpan(2.7f), 0, 5, 0);
        s.setSpan(new StyleSpan(Typeface.BOLD), 0, 5, 0);
        ;
        s.setSpan(new ForegroundColorSpan(ColorTemplate.rgb("#FFFFFF")), s.length() - 13, s.length(), 0);
        s.setSpan(new StyleSpan(Typeface.NORMAL), 5, 13, 0);
        ;
        return s;
    }


}




