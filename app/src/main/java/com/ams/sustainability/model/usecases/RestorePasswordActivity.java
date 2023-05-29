
package com.ams.sustainability.model.usecases;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ams.sustainability.R;
import com.ams.sustainability.data.common.DefaultCallback;
import com.ams.sustainability.view.MainLogin;
import com.backendless.Backendless;

public class RestorePasswordActivity extends Activity {

    public static int SPLASH_TIMER = 5000;
    private Button btnRestorePassword;
    private EditText loginField;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restore_password);

        initUI();
    }

    private void initUI() {
        btnRestorePassword = (Button) findViewById(R.id.restorePassword);
        loginField = (EditText) findViewById(R.id.loginField);

        btnRestorePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRestorePasswordButtonClicked();
            }
        });
    }

    public void onRestorePasswordButtonClicked() {
        String login = loginField.getText().toString();
        Backendless.UserService.restorePassword(login, new DefaultCallback<Void>(this) {
            @Override
            public void handleResponse(Void response) {
                super.handleResponse(response);

                AlertDialog.Builder builder = new AlertDialog.Builder(RestorePasswordActivity.this, R.style.MyAlertDialogTheme);
                builder.setMessage(R.string.password_recovered_message).setTitle(R.string.password_recovery_page_name).setPositiveButton("Cerrar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(RestorePasswordActivity.this, MainLogin.class);
                        startActivity(intent);
                        finish();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });
    }
}

