package com.hikaku.coordination;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class OptionsActivity extends Activity {

     private EditText ServerAddressOption;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        ServerAddressOption = (EditText) findViewById(R.id.ServerAddressOption);
        String serverAddress = getApplicationContext().getSharedPreferences(Constants.PREFERENCES_NAME, MODE_PRIVATE).getString(Constants.SERVER_ADDRESS, "Type your server address here");
        if(serverAddress != null && !serverAddress.isEmpty())
            {
            ServerAddressOption.setText(serverAddress);
            }
        else
            ServerAddressOption.setHint("Type your server address here");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_options, menu);
        return true;
    }
    public void btnOptionsSave_OnClick(View view) {
        String tempServerAddress = ServerAddressOption.getText().toString();
        //ServerAddressOption.setHint(tempServerAddress);
        if (tempServerAddress != null && !tempServerAddress.isEmpty())
            {
            getApplicationContext().getSharedPreferences(Constants.PREFERENCES_NAME,MODE_PRIVATE).edit().putString(Constants.SERVER_ADDRESS,tempServerAddress).commit();
            finish();
            }
        else
            {

            AlertDialog.Builder builder = new AlertDialog.Builder(
                    view.getContext());
            builder.setMessage("Server Address is EMPTY");
            AlertDialog dialog = builder.create();
            dialog.show();
            return;
            }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
