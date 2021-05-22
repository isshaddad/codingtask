package com.example.listviewwithhashmap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Activity_SingleUser extends AppCompatActivity{
    TextView newtext ;//= (TextView) findViewById(R.id.textView3);
   // String sessionId = getIntent().getStringExtra("EXTRA_SESSION_ID");
    //Intent mIntent=getIntent();
    //int position=mIntent.getIntExtra("Index",0);
    //int position=getIntent().getExtras().getInt("Index");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__single_user);
        newtext=findViewById(R.id.textView3);
        newtext.setText("hjgfdsa");
    }
}