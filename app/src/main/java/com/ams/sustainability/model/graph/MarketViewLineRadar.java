package com.ams.sustainability.model.graph;


import android.content.Context;
import android.widget.TextView;

import com.ams.sustainability.R;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;

public class MarketViewValue extends MarkerView {

    private final TextView tvContent;

    public MarketViewValue(Context context, int layoutResource) {
        super(context, layoutResource);

        tvContent = findViewById(R.id.tvContent);
    }

    // runs every time the MarkerView is redrawn, can be used to update the
    // content (user-interface)
    @Override
    public void refreshContent(Entry e, Highlight highlight) {

        if (e instanceof CandleEntry) {

            CandleEntry ce = (CandleEntry) e;

            tvContent.setText(Utils.formatNumber(ce.getHigh(), 1, true));
        } else if (e instanceof BarEntry) {

            BarEntry be = (BarEntry) e;

            // Obtener el valor correspondiente al índice del segmento seleccionado
            float value = be.getYVals()[highlight.getStackIndex()];

            tvContent.setText(Utils.formatNumber(value, 1, true));
        }

        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }
}

/*

import android.content.Context;
import android.widget.TextView;

import com.ams.sustainability.R;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;

/*public class MarketViewValue extends MarkerView {

    private final TextView tvContent;

    public MarketViewValue(Context context, int layoutResource) {
        super(context, layoutResource);

        tvContent = findViewById(R.id.tvContent);
    }

    // runs every time the MarkerView is redrawn, can be used to update the
    // content (user-interface)
    @Override
    public void refreshContent(Entry e, Highlight highlight) {

        /*if (e instanceof CandleEntry) {

            CandleEntry ce = (CandleEntry) e;

            tvContent.setText(Utils.formatNumber(ce.getHigh(), 1, true));
        }
        if (e instanceof BarEntry) {
            BarEntry entry = (BarEntry) e;
            float value = entry.getYVals()[highlight.getStackIndex()];

            tvContent.setText(Utils.formatNumber(value, 1, true));
        } else {

            tvContent.setText(Utils.formatNumber(e.getY(), 1, true));
        }

        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }


}*/
