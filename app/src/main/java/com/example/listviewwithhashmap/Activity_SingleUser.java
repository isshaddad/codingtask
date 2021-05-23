package com.example.listviewwithhashmap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class Activity_SingleUser extends AppCompatActivity{
    TextView newtext3,newtext4,newtext5,newtext6;
    TextView newtext8;
    String name, companyname, email,fulladdress,coordinates;
    private static String JSON_URL="https://jsonplaceholder.typicode.com/users";
    JSONObject company,address,geo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__single_user);



        FetchData fetchData = new FetchData();
        fetchData.execute();
        /*button=findViewById(R.id.location);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent browserIntent=new Intent (Intent.ACTION_VIEW, Uri.parse(""))
            }
        });*/


    }

    public class FetchData extends AsyncTask<String, String, String>{

        String current="";

        int position=getIntent().getExtras().getInt("Index");
        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url;
                HttpURLConnection urlConnection=null;
                try {
                    url=new URL(JSON_URL);
                    urlConnection=(HttpURLConnection) url.openConnection();


                    InputStream in=urlConnection.getInputStream();
                    InputStreamReader isr= new InputStreamReader(in);

                    int data=isr.read();
                    while (data!=-1){
                        current+=(char)data;
                        data=isr.read();
                    }
                    return current;







                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finally {
                    if (urlConnection!=null){
                        urlConnection.disconnect();
                    }

                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }






            return current;
        }


        @Override
        protected void onPostExecute(String s) {
            try{
                // JSONObject jsonObject=new JSONObject(s);
                JSONArray jsonarray = new JSONArray(current);
                //for (int i=0;i<jsonarray.length();i++)
                {
                    JSONObject jsonObject1=(JSONObject)jsonarray.get(position);
                    name="Name: "+jsonObject1.getString("name");
                    newtext3=findViewById(R.id.textView3);
                    newtext3.setText(name);

                    company = (JSONObject) jsonObject1.get("company");
                    companyname="Company: " +company.getString("name");
                    newtext4=findViewById(R.id.textView4);
                    newtext4.setText(companyname);
                    email="Email: "+jsonObject1.getString("email");
                    newtext5=findViewById(R.id.textView5);
                    newtext5.setText(email);


                    address = (JSONObject) jsonObject1.get("address");
                    fulladdress="Address: " +address.getString("street")+", "+address.getString("suite")+", "+address.getString("city")+", "+address.getString("zipcode");
                    newtext6=findViewById(R.id.textView6);
                    newtext6.setText(fulladdress);
                   // newtext8=findViewById(R.id.textView8);
                    //newtext8.setText("hi");
                    geo = (JSONObject) address.get("geo");

                    coordinates="Location on a map: \n"+"latitude: "+geo.getString("lat")+"\n "+"longitude: "+geo.getString("lng");
                    newtext8=findViewById(R.id.textView8);
                    newtext8.setText(coordinates);


                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }


}