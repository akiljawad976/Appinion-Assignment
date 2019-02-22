package com.example.interndcr;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static Context context;
    public static Spinner  spProductGroup,spLiterature,spPhysicianSample,spGift;
    private EditText etAccompaniedWith,etRemarks,etLiterature,etPhysicianSample,etGift;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        MainActivity.context = getApplicationContext();
        spProductGroup = findViewById(R.id.sp1);
        spLiterature = findViewById(R.id.sp2);
        spPhysicianSample = findViewById(R.id.sp3);
        spGift = findViewById(R.id.sp4);
        etAccompaniedWith = findViewById(R.id.et4);
        etRemarks = findViewById(R.id.et5);
        etLiterature = findViewById(R.id.et1);
        etPhysicianSample = findViewById(R.id.et2);
        etGift = findViewById(R.id.et3);
        btnSubmit = findViewById(R.id.btn1);
        fetchJSONdata process = new fetchJSONdata(MainActivity.this);
        process.execute();

        //String text_ProductGroup = spProductGroup.getSelectedItem().toString();
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getAppContext(),"DONE",Toast.LENGTH_LONG);
                toast.show();
            }
        });

    }

    public static Context getAppContext() {
        return MainActivity.context;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
