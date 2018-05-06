package com.example.ben.joblist;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddJobActivity extends AppCompatActivity   implements AdapterView.OnItemSelectedListener{
    private  ArrayList<Company> companys;
    private List<String> sCompanys = new ArrayList<String>() ;
    public final String TAG = "addjob";
    String[] comName ;
    Spinner spinner;
    Spinner spinner2;
    TextView addressFrom,phoneFrom,addressTo,phoneTo;
    EditText dateEt,timeEt;
    public String selFromAddress;
    public String selToAddress;

    private ArrayList<Job> jobs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        addressFrom = (TextView)findViewById(R.id.fromaddresstv);
        phoneFrom = (TextView)findViewById(R.id.fromphonetv);
        addressTo = (TextView)findViewById(R.id.toaddresstv);
        phoneTo = (TextView)findViewById(R.id.tophonetv);
        dateEt = (EditText)findViewById(R.id.orderdateet);
        timeEt =(EditText)findViewById(R.id.ordertimeet);
        setSupportActionBar(toolbar);
        toolbar.setTitle("New Job");
        setupData();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }



        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        for(int i = 0;i < companys.size();i++) {
            Company c = companys.get(i);
          sCompanys.add(c.getComName());
        }


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat dfd = new SimpleDateFormat("dd-MMM-yyyy");
        SimpleDateFormat dft = new SimpleDateFormat("HH:mm:ss");
        String formattedDate = dfd.format(c);
        String formattedTime = dft.format(c);



        dateEt.setText(formattedDate);
        timeEt.setText(formattedTime);
        spinner = (Spinner) findViewById(R.id.spinner3);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,sCompanys);
        spinner.setAdapter(dataAdapter);

        spinner2 = (Spinner) findViewById(R.id.spinner5);
        spinner2.setOnItemSelectedListener(this);
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,sCompanys);
        spinner2.setAdapter(dataAdapter2);
try{



    Log.d(TAG,"spinner"+companys);






}
       catch (Exception e)
       {Log.d(TAG,"spinner"+spinner);
           Log.d(TAG,"spinner"+sCompanys);}
        try{
        SQLiteDatabase db = SQLiteDatabase.openDatabase("/data/data/com.example.ben.joblist/JobDB", null, SQLiteDatabase.CREATE_IF_NECESSARY);}


        catch (Exception e){

        }
// //Create an ArrayAdapter using the string array and a default spinner layout
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                companys, android.R.layout.simple_spinner_item);
// //Specify the layout to use when the list of choices appears
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        /* Apply the adapter to the sp */

    }



    private void insert() {
        SQLiteDatabase db = SQLiteDatabase.openDatabase("/data/data/com.example.ben.joblist/JobDB", null, SQLiteDatabase.OPEN_READWRITE);
        Job j =getLate();
        Company c2 =  companys.get(companys.size()-1);
        ContentValues pairValue = new ContentValues();
        int no = j.getJobno()+1;
        pairValue.put("jobNo", no);

        Company comNO = matchNameToNo(selFromAddress);
        pairValue.put("comFrom", comNO.getComNo());
        Company comNO2 = matchNameToNo(selToAddress);
        pairValue.put("comTo", comNO2.getComNo());
        pairValue.put("status", "Ordered");
        pairValue.put("orderDateTime", "1");
        pairValue.put("pickupDateTime", "1");
        pairValue.put("deliveryDateTime", "1");
        int rowPosition = (int) db.insert("Job", null, pairValue);
        Toast.makeText(this, "Row " + rowPosition + " is added.",
                Toast.LENGTH_LONG).show();
        Log.d(TAG,"insertComNo"+no);
        Log.d(TAG,"insert"+comNO2);
        Log.d(TAG,"insertNoFrom"+comNO.getComNo());
        Log.d(TAG,"insertNoTO"+comNO2.getComNo());
    }


    public void onItemSelected(AdapterView parent, View v , int position , long id){
        Spinner spinner =(Spinner)parent;
        if(spinner.getId() == R.id.spinner3){
        Company c = companys.get(position);
        addressFrom.setText(c.getComAddress());
       phoneFrom.setText(Integer.toString(c.getComPhone()));
       selFromAddress = c.getComName();

        }
       else if(spinner.getId() == R.id.spinner5){
            Company c = companys.get(position);
            addressTo.setText(c.getComAddress());
            phoneTo.setText(Integer.toString(c.getComPhone()));
            selToAddress = c.getComName();

        }
    }
    public void onNothingSelected(AdapterView parent){

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.addjobmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(getApplicationContext(),"save",Toast.LENGTH_LONG).show();

        insert();
        Intent intent = new Intent(this,JobList.class);
        startActivity(intent);


        return super.onOptionsItemSelected(item);
    }



    public void setupData() {

           // sCompanys = new ArrayList<String>();
                companys= new ArrayList<Company>();
                try {

            SQLiteDatabase db = SQLiteDatabase.openDatabase("/data/data/com.example.ben.joblist/JobDB", null, SQLiteDatabase.CREATE_IF_NECESSARY);
            db.execSQL("DROP TABLE IF EXISTS Company;");
           db.execSQL("CREATE TABLE Company (comNo int PRIMARY KEY,comName text, comAddress text, comPhone int);");
            db.execSQL("INSERT INTO  COMPANY(comNO,comName,comAddress,comPhone)values"+
                    "(2001,'IVE/Chai Wan','30 Shing Tai Road Chai Wan, Hong Kong','25958333')");
            db.execSQL("INSERT INTO  COMPANY(comNO,comName,comAddress,comPhone)values"+
                    "(2002,'IVE/Haking Wong','702 Lai Chi Kok Road, Cheung Sha Wan, Kowloon','23615161')");
            db.execSQL("INSERT INTO  COMPANY(comNO,comName,comAddress,comPhone)values"+
                    "(2003,'IVE/Kwai Chung','20 Hing Shing Road, Kwai Chung, New Territories','24246221')");
            db.execSQL("INSERT INTO  COMPANY(comNO,comName,comAddress,comPhone)values"+
                    "(2004,'IVE/Kwun Tong','25 Hiu Ming Street, Kwun Tong, Kowloon','27274331')");
            db.execSQL("INSERT INTO  COMPANY(comNO,comName,comAddress,comPhone)values"+
                    "(2005,'IVE/Lee Wai Lee','3 King Ling Road, Tseung Kwan O, New Territories','39282000')");
            db.execSQL("INSERT INTO  COMPANY(comNO,comName,comAddress,comPhone)values"+
                    "(2006,'IVE/Morrison Hill','6 Oi Kwan Road, Wan Chai, Hong Kong','25745321')");
            db.execSQL("INSERT INTO  COMPANY(comNO,comName,comAddress,comPhone)values"+
                    "(2007,'IVE/Sha Tin','21 Yuen Wo Road, Sha Tin, New Territories','26066227')");
            db.execSQL("INSERT INTO  COMPANY(comNO,comName,comAddress,comPhone)values"+
                    "(2008,'IVE/Tsing Yi','20 Tsing Yi Road, Tsing Yi Island, New Territories','24368333')");
            db.execSQL("INSERT INTO  COMPANY(comNO,comName,comAddress,comPhone)values"+
                    "(2009,'IVE/Tuen Mun','18 Tsing Wun Road, Tuen Mun, New Territories','24630066')");

            Cursor cursor = db.rawQuery("SELECT * FROM Company", null);



            while (cursor.moveToNext()) {
                companys.add(new Company(cursor.getInt(cursor.getColumnIndex("comNo")),
                        cursor.getString(cursor.getColumnIndex("comName")),
                        cursor.getString(cursor.getColumnIndex("comAddress")),
                        cursor.getInt(cursor.getColumnIndex("comPhone")))

                       // cursor.getString(cursor.getColumnIndex("comname"))

                );
            }

            db.close();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


    public Company matchNameToNo(String companyName){
        String address = null;
        Company c =new Company();
        try{
            SQLiteDatabase db = SQLiteDatabase.openDatabase("/data/data/com.example.ben.joblist/JobDB", null, SQLiteDatabase.OPEN_READWRITE);
//            db.execSQL("DROP TABLE IF EXISTS Company;");
//            db.execSQL("CREATE TABLE Company (comNo int PRIMARY KEY,comName text, comAddress text, comPhone int);");
//            db.execSQL("INSERT INTO  COMPANY(comNO,comName,comAddress,comPhone)values"+
//                    "(2001,'IVE/Chai Wan','30 Shing Tai Road Chai Wan, Hong Kong','25958333')");
//            db.execSQL("INSERT INTO  COMPANY(comNO,comName,comAddress,comPhone)values"+
//                    "(2002,'IVE/Haking Wong','702 Lai Chi Kok Road, Cheung Sha Wan, Kowloon','23615161')");
//            db.execSQL("INSERT INTO  COMPANY(comNO,comName,comAddress,comPhone)values"+
//                    "(2003,'IVE/Kwai Chung','20 Hing Shing Road, Kwai Chung, New Territories','24246221')");
//            db.execSQL("INSERT INTO  COMPANY(comNO,comName,comAddress,comPhone)values"+
//                    "(2004,'IVE/Kwun Tong','25 Hiu Ming Street, Kwun Tong, Kowloon','27274331')");
//            db.execSQL("INSERT INTO  COMPANY(comNO,comName,comAddress,comPhone)values"+
//                    "(2005,'IVE/Lee Wai Lee','3 King Ling Road, Tseung Kwan O, New Territories','39282000')");
//            db.execSQL("INSERT INTO  COMPANY(comNO,comName,comAddress,comPhone)values"+
//                    "(2006,'IVE/Morrison Hill','6 Oi Kwan Road, Wan Chai, Hong Kong','25745321')");
//            db.execSQL("INSERT INTO  COMPANY(comNO,comName,comAddress,comPhone)values"+
//                    "(2007,'IVE/Sha Tin','21 Yuen Wo Road, Sha Tin, New Territories','26066227')");
//            db.execSQL("INSERT INTO  COMPANY(comNO,comName,comAddress,comPhone)values"+
//                    "(2008,'IVE/Tsing Yi','20 Tsing Yi Road, Tsing Yi Island, New Territories','24368333')");
//            db.execSQL("INSERT INTO  COMPANY(comNO,comName,comAddress,comPhone)values"+
//                    "(2009,'IVE/Tuen Mun','18 Tsing Wun Road, Tuen Mun, New Territories','24630066')");
            // String [] arg = {Integer.toString(companyID)};
            Log.d(TAG,"companyID"+companyName);
            // String a[] = {Integer.toString(companyID)};
            String a[] = {companyName};
            Log.d(TAG,"companyID2"+a);
            Cursor cursor = db.rawQuery("SELECT * FROM Company WHERE comName = ?",a );
            //addresss = "a";

            Log.d(TAG,"cursor2"+cursor);

            // address = cursor.getString(cursor.getColumnIndex("comAddress"));
            cursor.moveToLast();
            c= new Company(
                    cursor.getInt(cursor.getColumnIndex("comNo")),
                    cursor.getString(cursor.getColumnIndex("comName")),
                    cursor.getString(cursor.getColumnIndex("comAddress")),
                    cursor.getInt(cursor.getColumnIndex("comPhone")));



            Log.d(TAG,"addressMethod"+address);

            db.close();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        return c;
    }

    public Job getLate() {

        // sCompanys = new ArrayList<String>();
        jobs= new ArrayList<Job>();
        Job j = new Job();
        try {

            SQLiteDatabase db = SQLiteDatabase.openDatabase("/data/data/com.example.ben.joblist/JobDB", null, SQLiteDatabase.OPEN_READWRITE);


            Cursor cursor = db.rawQuery("SELECT * FROM Job", null);



           cursor.moveToLast();
                j = new Job(cursor.getInt(cursor.getColumnIndex("jobno")),
                        cursor.getInt(cursor.getColumnIndex("comfrom")),
                        cursor.getInt(cursor.getColumnIndex("comto")),
                        cursor.getString(cursor.getColumnIndex("status")),
                        cursor.getString(cursor.getColumnIndex("orderdatetime")),
                        cursor.getString(cursor.getColumnIndex("pickupdatetime")),
                        cursor.getString(cursor.getColumnIndex("deliverydatetime"))

                        // cursor.getString(cursor.getColumnIndex("comname"))

                );


            db.close();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return j;
    }
}
