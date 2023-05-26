package com.ams.sustainability.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ams.sustainability.R;
import com.ams.sustainability.data.repository.BackendLessDAO;
import com.ams.sustainability.model.entities.Results;
import com.ams.sustainability.model.repository.tips;
import com.ams.sustainability.ui.adapters.AdapterRecyclerViewTips;
import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;

import java.util.List;

public class FragmentTips extends Fragment {

    private ListView listView;
    private BackendlessUser currentUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tips, container, false);

        BackendLessDAO backendLessDAO = new BackendLessDAO(getContext());

        tips tips = new tips();

        RecyclerView recyclerView = view.findViewById(R.id.ListadoLibros);

        currentUser = Backendless.UserService.CurrentUser();

        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setWhereClause("ownerId = '" + currentUser.getObjectId() + "'");

        queryBuilder.setPageSize(100);
        Backendless.Data.of(Results.class).find(queryBuilder, new AsyncCallback<List<Results>>() {
            @Override
            public void handleResponse(List<Results> response) {
                Results lastRecord = null;
                for (Results record : response) {
                    if (lastRecord == null || record.getCreated().getTime() > lastRecord.getCreated().getTime()) {
                        lastRecord = record;
                    }
                }
                if (lastRecord != null) {

                    float home = lastRecord.getHouse().floatValue();
                    float clothes = lastRecord.getClothes().floatValue();
                    float food = lastRecord.getFood().floatValue();
                    float technology = lastRecord.getTechonology().floatValue();
                    float transport = lastRecord.getTransport().floatValue();

                    if (home > 0.4 && clothes > 0.5 && food > 2.3 && technology > 0.4 && transport > 1.9) {
                        AdapterRecyclerViewTips adapter = new AdapterRecyclerViewTips(tips.getTipsAll());
                        recyclerView.setAdapter(adapter);
                    }
                    else if (home > 0.4 && clothes > 0.5 && food > 2.3 && technology > 0.4) {
                        AdapterRecyclerViewTips adapter = new AdapterRecyclerViewTips(tips.getTipsHCFT());
                        recyclerView.setAdapter(adapter);
                    }
                    else if (home > 0.4 && clothes > 0.5 && food > 2.3 && transport > 1.9) {
                        AdapterRecyclerViewTips adapter = new AdapterRecyclerViewTips(tips.getTipsHCFT());
                        recyclerView.setAdapter(adapter);
                    }
                    else if (home > 0.4 && clothes > 0.5 && technology > 0.4 && transport > 1.9) {
                        AdapterRecyclerViewTips adapter = new AdapterRecyclerViewTips(tips.getTipsHCTTr());
                        recyclerView.setAdapter(adapter);
                    }
                    else if (home > 0.4 && food > 2.3 && technology > 0.4 && transport > 1.9) {
                        AdapterRecyclerViewTips adapter = new AdapterRecyclerViewTips(tips.getTipsHFTTr());
                        recyclerView.setAdapter(adapter);
                    }
                    else if (clothes > 0.5 && food > 2.3 && technology > 0.4 && transport > 1.9) {
                        AdapterRecyclerViewTips adapter = new AdapterRecyclerViewTips(tips.getTipsCFTTr());
                        recyclerView.setAdapter(adapter);
                    }
                    else if (home > 0.4 && clothes > 0.5 && food > 2.3) {
                        AdapterRecyclerViewTips adapter = new AdapterRecyclerViewTips(tips.getTipsHFC());
                        recyclerView.setAdapter(adapter);
                    }
                    else if (home > 0.4 && clothes > 0.5 && technology > 0.4) {
                        AdapterRecyclerViewTips adapterHCT = new AdapterRecyclerViewTips(tips.getTipsHCT());
                        recyclerView.setAdapter(adapterHCT);
                    }
                    else if (home > 0.4 && clothes > 0.5 && transport > 1.9) {
                        AdapterRecyclerViewTips adapter = new AdapterRecyclerViewTips(tips.getTipsHCTr());
                        recyclerView.setAdapter(adapter);
                    }
                    else if (home > 0.4 && food > 2.3 && technology > 0.4) {
                        AdapterRecyclerViewTips adapter = new AdapterRecyclerViewTips(tips.getTipsHFT());
                        recyclerView.setAdapter(adapter);
                    }
                    else if (home > 0.4 && food > 2.3 && transport > 1.9) {
                        AdapterRecyclerViewTips adapter = new AdapterRecyclerViewTips(tips.getTipsHFTr());
                        recyclerView.setAdapter(adapter);
                    }
                    else if (clothes > 0.5 && food > 2.3 && technology > 0.4) {
                        AdapterRecyclerViewTips adapter = new AdapterRecyclerViewTips(tips.getTipsCFT());
                        recyclerView.setAdapter(adapter);
                    }
                    else if (clothes > 0.5 && food > 2.3 && transport > 1.9) {
                        AdapterRecyclerViewTips adapter = new AdapterRecyclerViewTips(tips.getTipsCFTr());
                        recyclerView.setAdapter(adapter);
                    }
                    else if (technology > 0.4 && food > 2.3 && transport > 1.9) {
                        AdapterRecyclerViewTips adapter = new AdapterRecyclerViewTips(tips.getTipsTFTr());
                        recyclerView.setAdapter(adapter);
                    }
                    else if (home > 0.4 && clothes > 0.5) {
                        AdapterRecyclerViewTips adapter = new AdapterRecyclerViewTips(tips.getTipsHC());
                        recyclerView.setAdapter(adapter);
                    }
                    else if (home > 0.4 && food > 2.3) {
                        AdapterRecyclerViewTips adapter = new AdapterRecyclerViewTips(tips.getTipsHF());
                        recyclerView.setAdapter(adapter);
                    }
                    else if (home > 0.4 && technology > 0.4) {
                        AdapterRecyclerViewTips adapter = new AdapterRecyclerViewTips(tips.getTipsHT());
                        recyclerView.setAdapter(adapter);
                    }
                    else if (home > 0.4 && transport > 1.9) {
                        AdapterRecyclerViewTips adapter = new AdapterRecyclerViewTips(tips.getTipsHTr());
                        recyclerView.setAdapter(adapter);
                    }
                    else if (clothes > 0.5 && food > 2.3) {
                        AdapterRecyclerViewTips adapter = new AdapterRecyclerViewTips(tips.getTipsCF());
                        recyclerView.setAdapter(adapter);
                    }
                    else if (clothes > 0.5 && technology > 0.4) {
                        AdapterRecyclerViewTips adapter = new AdapterRecyclerViewTips(tips.getTipsCT());
                        recyclerView.setAdapter(adapter);
                    }
                    else if (clothes > 0.5 && transport > 1.9) {
                        AdapterRecyclerViewTips adapter = new AdapterRecyclerViewTips(tips.getTipsCTr());
                        recyclerView.setAdapter(adapter);
                    }
                    else if (food > 2.3 && technology > 0.4) {
                        AdapterRecyclerViewTips adapter = new AdapterRecyclerViewTips(tips.getTipsFT());
                        recyclerView.setAdapter(adapter);
                    }
                    else if (food > 2.3 && transport > 1.9) {
                        AdapterRecyclerViewTips adapter = new AdapterRecyclerViewTips(tips.getTipsFTr());
                        recyclerView.setAdapter(adapter);
                    }
                    else if (technology > 0.4 && transport > 1.9) {
                        AdapterRecyclerViewTips adapter = new AdapterRecyclerViewTips(tips.getTipsTTr());
                        recyclerView.setAdapter(adapter);
                    }
                    else if (home > 0.4) {
                        AdapterRecyclerViewTips adapter = new AdapterRecyclerViewTips(tips.getTipsHome());
                        recyclerView.setAdapter(adapter);
                    }
                    else if (clothes > 0.5) {
                        AdapterRecyclerViewTips adapter = new AdapterRecyclerViewTips(tips.getTipsClothes());
                        recyclerView.setAdapter(adapter);
                    }
                    else if (food > 2.3) {
                        AdapterRecyclerViewTips adapter = new AdapterRecyclerViewTips(tips.getTipsFood());
                        recyclerView.setAdapter(adapter);
                    }
                    else if (technology > 0.4) {
                        AdapterRecyclerViewTips adapter = new AdapterRecyclerViewTips(tips.getTipsTechnology());
                        recyclerView.setAdapter(adapter);
                    }
                    else if (transport > 1.9) {
                        AdapterRecyclerViewTips adapter = new AdapterRecyclerViewTips(tips.getTipsTransport());
                        recyclerView.setAdapter(adapter);
                    }

                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                } else {
                    Toast.makeText(getContext(), "No hay registros", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void handleFault(BackendlessFault fault) {
            }
        });

        return view;

    }

}