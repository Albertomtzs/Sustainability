package com.ams.sustainability.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.ams.sustainability.R;
import com.ams.sustainability.view.MainLogin;
import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class FragmentAccount extends Fragment {

    private TextView user;
    private Button bkndlsLogoutButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_account, container, false);

        user = view.findViewById(R.id.userinfo_name);

        BackendlessUser currentUser = Backendless.UserService.CurrentUser();

        if (currentUser != null) {
            String userId = currentUser.getObjectId();
            String email = currentUser.getEmail();
            user.setText((String) currentUser.getProperty("name"));
            Log.e("****MainActivity", "*********User ID: " + userId);
            Log.e("****MainActivity", "*********Email: " + email);

        } else {
            Log.e("****MainActivity", "*****No user is currently logged in.");
        }

        bkndlsLogoutButton = view.findViewById(R.id.button_bkndlsBackendlessLogout);

        bkndlsLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Backendless.UserService.logout(new AsyncCallback<Void>() {
                    @Override
                    public void handleResponse(Void response) {
                        Intent intent = new Intent(getActivity(), MainLogin.class);
                        startActivity(intent);
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        // Se ha producido un error al cerrar la sesión
                    }
                });
            }
        });

        return view;
    }
}