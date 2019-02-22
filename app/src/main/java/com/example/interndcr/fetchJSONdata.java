package com.example.interndcr;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class fetchJSONdata extends AsyncTask<Void,Void,Void> {
    String singleParsed = "";
    ArrayList<String> product_List;
    ArrayList<String> literature_list;
    ArrayList<String> physician_list;
    ArrayList<String> gift_list;

    private ProgressDialog pd;

    public fetchJSONdata(MainActivity activity) {
        pd = new ProgressDialog(activity);
    }



    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd.setTitle("JSON Parsing");
        pd.setMessage("Downloading JSON...");
        pd.show();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("https://raw.githubusercontent.com/appinion-dev/intern-dcr-data/master/data.json");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer line = new StringBuffer();
            product_List = new ArrayList<>();
            literature_list = new ArrayList<>();
            physician_list = new ArrayList<>();
            gift_list = new ArrayList<>();

            product_List.add("Choose");
            literature_list.add("Choose");
            physician_list.add("Choose");
            gift_list.add("Choose");
            String inputLine;
            while((inputLine = bufferedReader.readLine())!=null){
                line.append(inputLine);

            }bufferedReader.close();

            JSONObject obj_parent = new JSONObject(line.toString());
            JSONArray obj_product_group_list = obj_parent.getJSONArray("product_group_list");
            JSONArray obj_literature_list = obj_parent.getJSONArray("literature_list");
            JSONArray obj_physician_sample_list = obj_parent.getJSONArray("physician_sample_list");
            JSONArray obj_gift_list = obj_parent.getJSONArray("gift_list");

            for(int i=0;i<obj_product_group_list.length();i++){
                JSONObject obj_child = obj_product_group_list.getJSONObject(i);
                singleParsed = obj_child.getString("id")+"."+obj_child.getString("product_group");
                product_List.add(singleParsed);
            }
            singleParsed = "";
            for(int i=0;i<obj_literature_list.length();i++){
                JSONObject obj_child = obj_literature_list.getJSONObject(i);
                singleParsed = obj_child.getString("id")+"."+obj_child.getString("literature");
                literature_list.add(singleParsed);
            }

            singleParsed = "";
            for(int i=0;i<obj_physician_sample_list.length();i++){
                JSONObject obj_child = obj_physician_sample_list.getJSONObject(i);
                singleParsed = obj_child.getString("id")+"."+obj_child.getString("sample");
                physician_list.add(singleParsed);
            }

            singleParsed = "";
            for(int i=0;i<obj_gift_list.length();i++){
                JSONObject obj_child = obj_gift_list.getJSONObject(i);
                singleParsed = obj_child.getString("id")+"."+obj_child.getString("gift");
                gift_list.add(singleParsed);
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        MainActivity.spProductGroup.setAdapter(new ArrayAdapter<String>(MainActivity.getAppContext(),R.layout.custom_spinner,product_List));
        MainActivity.spLiterature.setAdapter(new ArrayAdapter<String>(MainActivity.getAppContext(),R.layout.custom_spinner,literature_list));
        MainActivity.spPhysicianSample.setAdapter(new ArrayAdapter<String>(MainActivity.getAppContext(),R.layout.custom_spinner,physician_list));
        MainActivity.spGift.setAdapter(new ArrayAdapter<String>(MainActivity.getAppContext(),R.layout.custom_spinner,gift_list));

        if(pd.isShowing()){
            try {
                Thread.sleep(4000);
                pd.dismiss();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
