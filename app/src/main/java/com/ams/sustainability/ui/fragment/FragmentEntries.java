package com.ams.sustainability.ui.fragment;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.ams.sustainability.R;
import com.ams.sustainability.data.repository.BackendLessDAO;
import com.ams.sustainability.model.graph.entities.Results;
import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class FragmentEntries extends Fragment {

    private FragmentHistorial fragmentHistorial;
    private BackendLessDAO resultadosDAO;
    private BackendlessUser currentUser;
    LinearLayout linearLayoutRecords;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        List<Map<String, Object>> data = new ArrayList<>();

        // Inflar el diseño del fragment
        View view = inflater.inflate(R.layout.fragment_entries, container, false);

        linearLayoutRecords = view.findViewById(R.id.ll_entries);

        resultadosDAO = new BackendLessDAO(this.getContext());

        readRecords(view);

        ImageView deleteAllBtn = view.findViewById(R.id.delete_all_btn);

        deleteAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog deleteDialog = new AlertDialog.Builder(getContext(), R.style.MyAlertDialogTheme)
                        .setTitle("Importante")
                        .setMessage("¿Desea eliminar todos los registros?")
                        .setPositiveButton("Borrrar todos", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                resultadosDAO.remove();
                                Toast.makeText(getContext(), "Fueron borrados todos los registros", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                linearLayoutRecords.removeAllViews();

                                //readRecords(view);

                            }
                        })
                        .setNegativeButton("Cancelar", null)
                        .create();

                deleteDialog.show();
            }
        });

        return view;
    }


    private void readRecords(View view) {

        linearLayoutRecords.removeAllViews();

        currentUser = Backendless.UserService.CurrentUser();

        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setWhereClause("ownerId = '" + currentUser.getObjectId() + "'");
        queryBuilder.setPageSize(100);
        queryBuilder.setSortBy("created DESC");


        Backendless.Data.of(Results.class).find(queryBuilder, new AsyncCallback<List<Results>>() {
            @Override
            public void handleResponse(List<Results> response) {
                List<Map<String, Object>> data = new ArrayList<>();

                for (int i = 0; i < response.size(); i++) {
                    String inputDate = String.valueOf(response.get(i).getCreated());
                    String outputDate = convertStringFormat(inputDate);
                    double huella = response.get(i).getCarbon_footprint();
                    String id = response.get(i).getObjectId();

                    Log.e("****MainActivity", "Fecha entrada: " + inputDate + " Valores: " + huella);
                    Log.e("****MainActivity", "Fecha salida: " + outputDate + " Valores: " + huella);

                    Map<String, Object> itemData = new LinkedHashMap<>();
                    itemData.put("date", outputDate);
                    itemData.put("value", huella);
                    itemData.put("id", id);
                    data.add(itemData);

                    TextView dateText = new TextView(getContext());
                    dateText.setPadding(120, 50, 0, 5);
                    dateText.setText(outputDate);
                    dateText.setTextColor(Color.WHITE);
                    dateText.setTextSize(12);

                    linearLayoutRecords.addView(dateText);

                    TextView lbCO2Text = new TextView(getContext());
                    lbCO2Text.setPadding(100, 0, 0, 10);
                    lbCO2Text.setText(String.valueOf(huella).replace(".", ",") + " toneladas de CO2 producida");
                    lbCO2Text.setTextColor(Color.WHITE);
                    lbCO2Text.setTextSize(18);

                    linearLayoutRecords.addView(lbCO2Text);

                    Button delete = new Button(getContext());

                    delete.setPadding(140, 0, 0, 0);
                    delete.setText("BORRAR");
                    delete.setTextColor(Color.WHITE);
                    delete.setBackground(null);
                    delete.setGravity(Gravity.NO_GRAVITY);

                    linearLayoutRecords.addView(delete);

                    try {
                    delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Backendless.Data.of(Results.class).findById(id, new AsyncCallback<Results>() {

                                @Override
                                public void handleResponse(Results response) {
                                    if (response != null) {
                                        Backendless.Data.of(Results.class).remove(response, new AsyncCallback<Long>() {
                                            @Override
                                            public void handleResponse(Long response) {
                                                Toast.makeText(getContext(), "Registro borrado", Toast.LENGTH_LONG).show();
                                                Log.i("******Backendless", "Registro eliminado con éxito");

                                                linearLayoutRecords.removeAllViews();

                                                readRecords(view);

                                            }

                                            @Override
                                            public void handleFault(BackendlessFault fault) {
                                                Toast.makeText(getContext(), "No fue posible borrar el registro", Toast.LENGTH_LONG).show();

                                            }
                                        });
                                    }
                                }

                                @Override
                                public void handleFault(BackendlessFault fault) {

                                }

                            });
                        }
                    });
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }}

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });
    }


    public static String convertStringFormat(String dateString) {
        try {
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

}














