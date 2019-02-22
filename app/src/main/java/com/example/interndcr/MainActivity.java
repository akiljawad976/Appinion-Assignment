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
    String text_ProductGroup = "";
    String text_Literature = "";
    String text_PhysicianSample = "";
    String text_Gift = "";
    String text_AccompaniedWith = "";
    String text_Remarks = "";
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

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spProductGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if(position>0){
                             text_ProductGroup = spProductGroup.getSelectedItem().toString();
                        }else{
                             text_ProductGroup = "None";
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        spProductGroup.setSelection(0);
                        text_ProductGroup = "None";

                    }
                });
                spLiterature.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if(position>0){
                            text_Literature = spLiterature.getSelectedItem().toString();
                        }else{
                            text_Literature = "None";
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        spLiterature.setSelection(0);
                        text_Literature = "None";
                    }
                });
                spPhysicianSample.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if(position>0){
                            text_PhysicianSample = spPhysicianSample.getSelectedItem().toString();
                        }else{
                            text_PhysicianSample = "None";
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        spPhysicianSample.setSelection(0);
                        text_PhysicianSample = "None";

                    }
                });
                spGift.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if(position>0){
                            text_Gift = spGift.getSelectedItem().toString();
                        }else{
                            text_Gift = "None";
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        spGift.setSelection(0);
                        text_Gift = "None";

                    }
                });

                text_AccompaniedWith = etAccompaniedWith.getText().toString();
                text_Remarks = etRemarks.getText().toString();
//                Toast toast = Toast.makeText(getAppContext(),"ProductGroup :"+ text_ProductGroup +"\nLiterature :"+
//                        text_Literature+"\nPhysicianSample :"+text_PhysicianSample+"\nGift :"+text_Gift+"\nAccompanied With :"+
//                        text_AccompaniedWith+"\nRemarks :"+text_Remarks,Toast.LENGTH_LONG);
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
