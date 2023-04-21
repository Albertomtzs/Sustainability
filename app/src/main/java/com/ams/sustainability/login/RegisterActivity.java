
package com.ams.sustainability.login;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.ams.sustainability.MainActivity;
import com.ams.sustainability.R;
import com.ams.sustainability.form.PostLogin;
import com.ams.sustainability.ui.NavigationActivity;
import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class RegisterActivity extends Activity {
	private final static java.text.SimpleDateFormat SIMPLE_DATE_FORMAT = new java.text.SimpleDateFormat("yyyy/MM/dd");

	private TextView screenLogin;
	private EditText nameField, emailField, passwordField;

	private Button registerButton;

	private String name, email, password;

	private BackendlessUser user;

	public static int SPLASH_TIMER = 2000;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		initUI();
		initUIBehaviour();

	}

	private void initUI() {

		screenLogin = (TextView) findViewById(R.id.screenLogin);
		nameField = (EditText) findViewById(R.id.nameField);
		emailField = (EditText) findViewById(R.id.emailField);
		passwordField = (EditText) findViewById(R.id.passwordField);
		registerButton = (Button) findViewById(R.id.registerButton);

		registerButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				onRegisterButtonClicked();

			}
		});
	}

	private void initUIBehaviour() {
		screenLogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				onLoginButtonClicked();
			}
		});

	}

	private void onLoginButtonClicked() {
		startActivity(new Intent(RegisterActivity.this, MainLogin.class));
	}

	private void onRegisterButtonClicked() {
		String nameText = nameField.getText().toString().trim();
		String emailText = emailField.getText().toString().trim();
		String passwordText = passwordField.getText().toString().trim();

		if (emailText.isEmpty()) {
			Toast.makeText(this, "El campo email no puede estar vacio.", Toast.LENGTH_SHORT).show();
			return;
		} else
			email = emailText;

		if (passwordText.isEmpty()) {
			Toast.makeText(this, "El campo contraseña no puede estar vacio.", Toast.LENGTH_SHORT).show();
			return;
		}
		else
			password = passwordText;

		if (nameText.isEmpty()) {
			Toast.makeText(this, "El campo Nombre no puede estar vacio.", Toast.LENGTH_SHORT).show();
			return;

		}
		else
			name = nameText;

		BackendlessUser user = new BackendlessUser();

		if (email != null) {
			user.setEmail(email);
		}

		if (password != null) {
			user.setPassword(password);
		}

		if (name != null) {
			user.setProperty("name", name);
		}

		Backendless.UserService.register(user, new AsyncCallback<BackendlessUser>() {
			@Override
			public void handleResponse(BackendlessUser response) {

				Resources resources = getResources();
				String message = String.format(resources.getString(R.string.registration_success_message), resources.getString(R.string.app_name));

				AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
				builder.setMessage(message).setTitle(R.string.registration_success);
				AlertDialog dialog = builder.create();
				dialog.show();

				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						Intent intent = new Intent(RegisterActivity.this, LoginResult.class);
						startActivity(intent);
						finish();
					}
				}, SPLASH_TIMER);

			}

			@Override
			public void handleFault(BackendlessFault fault) {
				AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
				builder.setMessage(fault.getMessage()).setTitle(R.string.registration_error);
				AlertDialog dialog = builder.create();
				dialog.show();
			}
		});
	}
}

