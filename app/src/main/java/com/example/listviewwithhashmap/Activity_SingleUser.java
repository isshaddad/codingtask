package com.example.listviewwithhashmap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
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
    String name, companyname, email,fulladdress;
    private static String JSON_URL="https://jsonplaceholder.typicode.com/users";
    JSONObject company,address;

    //Bundle extras = getIntent().getExtras();
   // int temp = extras.getInt("id");
    //= (TextView) findViewById(R.id.textView3);
   // String sessionId = getIntent().getStringExtra("EXTRA_SESSION_ID");
    //Intent mIntent=getIntent();
    //int position=mIntent.getIntExtra("Index",0);
   // int position=getIntent().getExtras().getInt("Index");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__single_user);

       // int position=getIntent().getExtras().getInt("Index");
        //newtext=findViewById(R.id.textView3);
        //newtext.setText("temp");

        FetchData fetchData = new FetchData();
        fetchData.execute();


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



                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            //ListAdapter adapter= new SimpleAdapter(MainActivity.this, usersList, R.layout.row_layout, new String[] {"name","companyname"}, new int[] {R.id.textView,R.id.textView2});

            //lv.setAdapter(adapter);

        }
    }


}