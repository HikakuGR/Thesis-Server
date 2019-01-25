package com.hikaku.coordination;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends Activity {

	private EditText txtUsername, txtPassword, txtEmail;
	private Button btnRegister, btnBack;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODOAuto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		txtUsername = (EditText) findViewById(R.id.txtUsername);
		txtPassword = (EditText) findViewById(R.id.txtPassword);
		txtEmail = (EditText) findViewById(R.id.txtEmail);
		btnRegister = (Button) findViewById(R.id.btnRegister);
		btnBack = (Button) findViewById(R.id.btnBack);

	}

	public void btnBack_OnClick(View view) {
		this.finish();
	}

	public void btnRegister_OnClick(View view) {
		String userName = txtUsername.getText().toString();
		String password = txtPassword.getText().toString();
		String mail = txtEmail.getText().toString();
		String serverAddress = Constants.getServerAddress(getApplicationContext().getSharedPreferences(Constants.PREFERENCES_NAME, MODE_PRIVATE));
		WebServiceHandler webservice = new WebServiceHandler(serverAddress);

		try {
			int result = webservice.Register(userName, password, mail);
			AlertDialog.Builder builder = new AlertDialog.Builder(
					view.getContext());
			if (result == -1) {

				builder.setMessage("User already exists");

			} else if (result == -2)

			{
				builder.setMessage("ERROR");

			} else {

				builder.setMessage("COMPLETE");

			}
			AlertDialog dialog = builder.create();
			dialog.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
