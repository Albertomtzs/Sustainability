
package com.ams.sustainability.view;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.ams.sustainability.R;
import com.ams.sustainability.data.common.Defaults;
import com.ams.sustainability.model.usecases.RegisterActivity;
import com.ams.sustainability.model.usecases.RestorePasswordActivity;
import com.ams.sustainability.data.common.DefaultCallback;
import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MainLogin extends Activity {

    private CheckBox rememberLoginBox;

    // backendless
    private TextView registerLink, restoreLink;
    private EditText identityField, passwordField;
    private Button login;
    private ImageButton loginGooglePlusButton;

    // google
    private final int RC_SIGN_IN = 112233; // arbitrary number
    private GoogleApiClient mGoogleApiClient;
    private String gpAccessToken = null;
    private boolean isLoggedInGoogle = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        Backendless.initApp(this, Defaults.APPLICATION_ID, Defaults.API_KEY);
        Backendless.setUrl(Defaults.SERVER_URL);

        initUI();
        initUIBehaviour();

        Backendless.UserService.isValidLogin(new DefaultCallback<Boolean>(this) {
            @Override
            public void handleResponse(Boolean isValidLogin) {
                super.handleResponse(null);
                if (isValidLogin && Backendless.UserService.CurrentUser() == null) {
                    String currentUserId = Backendless.UserService.loggedInUser();

                    if (!currentUserId.equals("")) {
                        Backendless.UserService.findById(currentUserId, new DefaultCallback<BackendlessUser>(MainLogin.this, "Logging in...") {
                            @Override
                            public void handleResponse(BackendlessUser currentUser) {
                                super.handleResponse(currentUser);
                                Backendless.UserService.setCurrentUser(currentUser);
                                startLoginResult(currentUser);
                            }
                        });
                    }
                }
                super.handleResponse(isValidLogin);
            }
        });
    }

    private void initUI() {

        rememberLoginBox = (CheckBox) findViewById(R.id.rememberLoginBox);

        // backendless
        registerLink = (TextView) findViewById(R.id.registerLink);
        restoreLink = (TextView) findViewById(R.id.restoreLink);
        identityField = (EditText) findViewById(R.id.identityField);
        passwordField = (EditText) findViewById(R.id.passwordField);
        login = (Button) findViewById(R.id.btnLogin);

        // google
        loginGooglePlusButton = (ImageButton) findViewById(R.id.button_googlePlusLogin);

    }

    private void initUIBehaviour() {

        // backendless
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLoginWithBackendlessButtonClicked();
            }
        });
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRegisterLinkClicked();
            }
        });
        restoreLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRestoreLinkClicked();
            }
        });

        // google
        configureGooglePlusSDK();
        if (mGoogleApiClient.isConnected()) {
            OptionalPendingResult pendingResult = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
            if (pendingResult.isDone())
                handleSignInResult((GoogleSignInResult) pendingResult.get());
        }

    }

    private void startLoginResult(BackendlessUser user) {
        String msg = "ObjectId: " + user.getObjectId() + "\n"
                + "UserId: " + user.getUserId() + "\n"
                + "Email: " + user.getEmail() + "\n"
                + "Properties: " + "\n";

        for (Map.Entry<String, Object> entry : user.getProperties().entrySet())
            msg += entry.getKey() + " : " + entry.getValue() + "\n";

        Intent i = new Intent(this, GetStartedCalculator.class);
        startActivity(i);

    }

    private void startLoginResult(String msg, boolean logoutButtonState) {

        if (logoutButtonState) {
            Intent i = new Intent(this, GetStartedCalculator.class);
            startActivity(i);
        }

    }


    private void onLoginWithBackendlessButtonClicked() {

        BackendlessUser currentUser = Backendless.UserService.CurrentUser();

        String identity = identityField.getText().toString();
        String password = passwordField.getText().toString();
        boolean rememberLogin = rememberLoginBox.isChecked();

        if (identity.equals("") || password.equals("")) {
            Toast.makeText(this, "Por favor, introduce tu email y contraseña", Toast.LENGTH_SHORT).show();
            return;
        }

        Backendless.UserService.login(identity, password, new DefaultCallback<BackendlessUser>(MainLogin.this) {
            public void handleResponse(BackendlessUser backendlessUser) {
                super.handleResponse(backendlessUser);
                startLoginResult(backendlessUser);

            }

            @Override
            public void handleFault(BackendlessFault fault) {
                super.handleFault(fault);
                startLoginResult(fault.toString(), false);
            }

        }, rememberLogin);
    }

    private void onRegisterLinkClicked() {
        startActivity(new Intent(this, RegisterActivity.class));

    }

    private void onRestoreLinkClicked() {
        startActivity(new Intent(this, RestorePasswordActivity.class));
    }

    // ------------------------------ google ------------------------------ //

    private void loginToBackendlessWithGoogle() {
        boolean rememberLogin = rememberLoginBox.isChecked();
        Backendless.UserService.loginWithGooglePlusSdk(gpAccessToken, new AsyncCallback<BackendlessUser>() {
            @Override
            public void handleResponse(BackendlessUser backendlessUser) {
                startLoginResult(backendlessUser);
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                startLoginResult(fault.toString(), false);
            }
        }, rememberLogin);
    }

    private void configureGooglePlusSDK() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestScopes(new Scope(Scopes.PROFILE), new Scope(Scopes.PLUS_ME))
                .requestId()
                .requestIdToken(Defaults.gp_WebApp_ClientId)
                .requestServerAuthCode(Defaults.gp_WebApp_ClientId, false)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.CREDENTIALS_API)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        loginGooglePlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });
    }

    private void handleSignInResult(GoogleSignInResult result) {

        if (result.isSuccess()) {
            isLoggedInGoogle = true;

            final String gpAuthToken = result.getSignInAccount().getServerAuthCode();
            if (gpAuthToken == null) {
                Toast.makeText(MainLogin.this, "Google no devolvió AuthToken.", Toast.LENGTH_LONG).show();
                return;
            }
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    exchangeAuthTokenOnAccessToken(gpAuthToken);
                    loginToBackendlessWithGoogle();
                }
            });
            t.setDaemon(true);
            t.start();

        } else {

            gpAccessToken = null;
            isLoggedInGoogle = false;
            String msg = "Inicio de sesión fallido en Google.\nMensaje de estado:\n" + result.getStatus().getStatusMessage();
            Toast.makeText(MainLogin.this, msg, Toast.LENGTH_LONG).show();
        }
    }

    private String exchangeAuthTokenOnAccessToken(String gpAuthToken) {
        GoogleTokenResponse tokenResponse = null;
        try {
            tokenResponse = new GoogleAuthorizationCodeTokenRequest(
                    new NetHttpTransport(),
                    JacksonFactory.getDefaultInstance(),
                    "https://www.googleapis.com/oauth2/v4/token",
                    Defaults.gp_WebApp_ClientId,
                    Defaults.gp_WebApp_ClientSecret,
                    gpAuthToken,
                    "")

                    .execute();
        } catch (Exception e) {
            Log.e("LoginWithGooglePlus", e.getMessage(), e);
            Toast.makeText(MainLogin.this, "Google no intercambió AuthToken en AccessToken.\n" + e.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }

        gpAccessToken = tokenResponse.getAccessToken();
        return gpAccessToken;
    }

    private void logoutFromGoogle() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                mGoogleApiClient.blockingConnect(10, TimeUnit.SECONDS);
                if (!mGoogleApiClient.isConnected()) {
                    Toast.makeText(MainLogin.this, "No se puede cerrar sesión en Google plus. Sin conexión. Intenta más tarde.", Toast.LENGTH_LONG).show();
                    return;
                }

                Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                        new ResultCallback<Status>() {
                            @Override
                            public void onResult(@NonNull Status status) {
                                if (!status.isSuccess())
                                    return;

                                isLoggedInGoogle = false;
                                gpAccessToken = null;
                            }
                        });
            }
        });
    }

    // ------------------------------ end google ------------------------------ //

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
            return;
        }

    }
}
    