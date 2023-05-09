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

public class MarketViewStacked extends MarkerView {

    private final TextView tvContent;

    public MarketViewStacked(Context context, int layoutResource) {
        super(context, layoutResource);

        tvContent = findViewById(R.id.tvContent);
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {

       if (e instanceof BarEntry) {

            BarEntry be = (BarEntry) e;

            float value = be.getYVals()[highlight.getStackIndex()];

            tvContent.setText(Utils.formatNumber(value, 1, true)+" t");
        }

        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }
}

