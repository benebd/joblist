package com.example.ben.joblist;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class JobDetailActivity extends AppCompatActivity {
    public TextView  address;
    private final String TAG = "jobDetail";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextView  fromaddresstv;
        TextView  fromphonetv;
        TextView toAddressTv;
        TextView toPhoneTv;
        EditText orderDateEt,orderTimeEt;
        EditText pickDateEt,pickTimeEt;
        EditText delDateEt,delTimeEt;
      // TextView
        //from = (Spinner)findViewById(R.id.spinner3);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Bundle extras = getIntent().getExtras();
        int jobno = extras.getInt("jobno");
        String status = extras.getString("status");

       // String addressfrom = extras.getString("comNameF");
        String addressfrom = extras.getString("comfrom");
         final int phonefrom = extras.getInt("phonefrom");

        //String address = extras.getString("comNameT");
         String addressto = extras.getString("comto");
        final int phoneto = extras.getInt("phoneto");


        //get time
        String timeOrder = extras.getString("timeOrder");
        String timePick = extras.getString("timePick");
        String timeDel = extras.getString("timeDel");

        toolbar.setTitle("Job No:"+jobno +" - "+status);
        //toolbar.setTitle("address:"+address );
        //Log.d(TAG,"intphont"+phone);
        Log.d(TAG,"addressfrom"+addressfrom);
        Log.d(TAG,"phonefrom"+phonefrom);
        Log.d(TAG,"addressto"+addressto);
        Log.d(TAG,"phoneto"+phoneto);


        toAddressTv =(TextView)findViewById(R.id.toaddresstv);
        toPhoneTv = (TextView)findViewById(R.id.tophonetv);

        fromaddresstv = (TextView)findViewById(R.id.fromaddresstv);
        fromphonetv = (TextView)findViewById(R.id.fromphonetv);

        orderDateEt =(EditText)findViewById(R.id.orderdateet);
       pickDateEt =(EditText)findViewById(R.id.pickdateet);
        delDateEt  =(EditText)findViewById(R.id.dedateet);

//        EditText orderDateEt,orderTimeEt;
//        EditText pickDateEt,pickTimeEt;
//        EditText delDateEt,delTimeEt;

        orderDateEt.setText(timeOrder);
        pickDateEt.setText(timePick);
        delDateEt.setText(timeDel);

        orderDateEt.setKeyListener(null);
        pickDateEt.setKeyListener(null);
        delDateEt.setKeyListener(null);



       toAddressTv.setText(addressto);



        toPhoneTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phoneto));
                startActivity(intent);
            }
        });
        toPhoneTv.setText(Integer.toString(phoneto));



       fromaddresstv.setText(addressfrom);

        fromphonetv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phonefrom));
                startActivity(intent);
            }
        });
   fromphonetv.setText(Integer.toString(phonefrom));


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }

}
