package com.ams.sustainability.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ams.sustainability.R;
import com.ams.sustainability.model.repository.tips;

import java.util.ArrayList;
import java.util.List;

public class AdapterRecyclerViewTips extends RecyclerView.Adapter<AdapterRecyclerViewTips.ViewHolder> {
    private ArrayList<tips.TipsItem> data;

    public AdapterRecyclerViewTips(ArrayList<tips.TipsItem> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tiprow, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        tips.TipsItem item = data.get(position);

        // Configurar el ícono
        holder.imageView.setImageResource(item.getIconResource());

        // Configurar el texto
        holder.textView.setText(item.getTip());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageTips);
            textView = itemView.findViewById(R.id.tipText);
        }
    }
}
