package com.ams.sustainability.model.graph;


import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.TextView;

import com.ams.sustainability.R;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;

public class MarketViewLineRadar extends MarkerView {

    private final TextView tvContent;

    public MarketViewLineRadar(Context context, int layoutResource) {
        super(context, layoutResource);

        tvContent = findViewById(R.id.tvContent);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void refreshContent(Entry e, Highlight highlight) {

        if (e instanceof CandleEntry) {

            CandleEntry ce = (CandleEntry) e;

            tvContent.setText(Utils.formatNumber(ce.getHigh(), 1, true) + " t");


        } else {

            tvContent.setText(Utils.formatNumber(e.getY(), 1, true) + " t");
        }

        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }


}
