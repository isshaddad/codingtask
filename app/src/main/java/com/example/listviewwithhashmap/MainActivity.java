package com.example.listviewwithhashmap;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
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
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity{
    private ListView lv;
    String name,companyname;
    JSONObject company;
    private static String JSON_URL="https://jsonplaceholder.typicode.com/users";

    ArrayList<HashMap<String, String>> usersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        usersList = new ArrayList<>();
        lv = findViewById(R.id.listview);

        GetData getData = new GetData();
        getData.execute();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, Activity_SingleUser.class);
                intent.putExtra("Index", position);

                startActivity(intent);

            }


        });


    }

    public class GetData extends AsyncTask<String, String, String>{

        String current="";


        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url;
                HttpURLConnection urlConnection=null;
                try {
                    url=new URL (JSON_URL);
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
                for (int i=0;i<jsonarray.length();i++)
                {
                    JSONObject jsonObject1=(JSONObject)jsonarray.get(i);
                    name=jsonObject1.getString("name");
                    company = (JSONObject) jsonObject1.get("company");
                    companyname="Company: " +company.getString("name");



                    HashMap <String,String> users=new HashMap<>();

                    users.put("name",name);
                    users.put("companyname", companyname);
                    usersList.add(users);

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            ListAdapter adapter= new SimpleAdapter(MainActivity.this, usersList, R.layout.row_layout, new String[] {"name","companyname"}, new int[] {R.id.textView,R.id.textView2});

            lv.setAdapter(adapter);

        }
    }


}

