package com.hikaku.coordination;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class DetailsActivity extends Activity {
    private TextView jobNameField;
    private TextView jobDescriptionField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Bundle extras = getIntent().getExtras();
        Job job=null;
        if (extras != null && extras.get("job") != null) {
            job =(Job) extras.get("job");
        }
        if (job!=null){
        jobNameField = (TextView) findViewById(R.id.jobNameField);
        jobNameField.setText(job.jobName);
        jobDescriptionField = (TextView)findViewById(R.id.jobDescriptionField);
        jobDescriptionField.setText(job.description);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return true;
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
