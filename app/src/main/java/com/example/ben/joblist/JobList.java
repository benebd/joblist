package com.example.ben.joblist;

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
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class JobList extends AppCompatActivity {
    private ArrayList<Job> members;
    private ArrayList<Company>companys;
    private ListView lvMember;


    private Company com ;
    private Company com2 ;

    private final String TAG = "jobList";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_list);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle( "a");
        Intent intent1 = getIntent();
        String name = intent1.getStringExtra("userName");
        Toast.makeText(getApplicationContext(),"name"+name,Toast.LENGTH_LONG).show();
        toolbar.setTitle( name);


        setupData();

        lvMember = (ListView) findViewById(R.id.lvMember);
        lvMember.setAdapter(new JobAdapter(this, members));
        lvMember.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Job m = members.get(i);

                Log.d(TAG,"addresso"+com);
                //String address = com.getComAddress();
               // Log.d(TAG,"address"+address);
                Intent intent = new Intent(JobList.this,JobDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("jobno",m.getJobno());
                com =matchAddree(m.getComfrom());
                bundle.putString("comNameF",com.getComName());
                bundle.putString("comfrom",com.getComAddress());
                bundle.putInt("phonefrom",com.getComPhone());
                com =matchAddree(m.getComto());
                bundle.putString("comNameT",com.getComName());
                bundle.putString("comto",com.getComAddress());
                bundle.putInt("phoneto",com.getComPhone());
                bundle.putInt("conto",m.getComto());
                bundle.putString("status",m.getStatus());
                bundle.putString("timeOrder",m.getOrderdatetime());
                bundle.putString("timePick",m.getPickupdatetime());
                bundle.putString("timeDel",m.getDeliverydatetime());

                Log.d(TAG,"phoneJoblist"+com.getComPhone());
                //bundle.putString();
                intent.putExtras(bundle);
                startActivity(intent);

                //intent.getStringExtra("userName");

                Toast.makeText(getApplicationContext(),"name"+intent.getStringExtra("userName"),Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(),
                        "Job " + m.getJobno() + ", " + m.getComfrom() + ", " +
                                m.getComto() + ", " + m.getStatus()+m.getOrderdatetime(), Toast.LENGTH_LONG).show();
            }
        });
       // matchAddree(companyID);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.layout, menu);
        return super.onCreateOptionsMenu(menu);
    }
@Override
public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
        case R.id.action_addjob:
            // User chose the "Settings" item, show the app settings UI...
            Intent intent = new Intent(this,AddJobActivity.class);
            startActivity(intent);
            return true;

        case R.id.action_signout:
            // User chose the "Favorite" action, mark the current item
            // as a favorite...

            onDestroy();
            finish();


            return true;

        default:
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            return super.onOptionsItemSelected(item);

    }
}

    @Override
    protected void onStart() {
        super.onStart();
        setupData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupData();

    }

    public void setupData() {
        members = new ArrayList<Job>();
        companys = new ArrayList<Company>();
        try {
            SQLiteDatabase db = SQLiteDatabase.openDatabase("/data/data/com.example.ben.joblist/JobDB", null, SQLiteDatabase.OPEN_READWRITE);
//            db.execSQL("DROP TABLE IF EXISTS Job;");
//            db.execSQL("CREATE TABLE JOB (jobno int PRIMARY KEY,comfrom int, comto int, status text,orderdatetime text,pickupdatetime text, deliverydatetime text);");
//            db.execSQL("INSERT INTO JOB(jobno ,comfrom ,comto, status ,orderdatetime ,pickupdatetime ,deliverydatetime) values "
//                    + "(9001,2009,2004,'Picked','2018-01-31 15:05:32','2018-02-01 09:23:09',NULL); ");
//            db.execSQL("INSERT INTO JOB(jobno ,comfrom ,comto, status ,orderdatetime ,pickupdatetime ,deliverydatetime) values"
//                    + "(9002,2001,2006,'Completed','2018-02-01 10:24:23','2018-02-01 13:26:12','2018-02-01 16:53:45'); ");
//            db.execSQL("INSERT INTO JOB(jobno ,comfrom ,comto, status ,orderdatetime ,pickupdatetime ,deliverydatetime) values"
//                    + "(9003,2005,2007,'Completed','2018-02-01 11:11:35','2018-02-01 12:39:54','2018-02-01 15:22:48'); ");
//            db.execSQL("INSERT INTO JOB(jobno ,comfrom ,comto, status ,orderdatetime ,pickupdatetime ,deliverydatetime) values"
//                    + "(9004,2009,2001,'Ordered','2018-02-01 15:21:24',NULL,NULL); ");
//            db.execSQL("INSERT INTO JOB(jobno ,comfrom ,comto, status ,orderdatetime ,pickupdatetime ,deliverydatetime) values"
//                    + "(9005,2005,2004,'Picked','2018-02-02 11:52:12','2018-02-02 15:20:53',NULL); ");
//            db.execSQL("INSERT INTO JOB(jobno ,comfrom ,comto, status ,orderdatetime ,pickupdatetime ,deliverydatetime) values"
//                    + "(9006,2003,2002,'Picked','2018-02-02 12:36:55','2018-02-02 16:14:21',NULL); ");
//            db.execSQL("INSERT INTO JOB(jobno ,comfrom ,comto, status ,orderdatetime ,pickupdatetime ,deliverydatetime) values"
//                    + "(9007,2001,2002,'Ordered','2018-02-02 17:12:43',NULL,NULL); ");

//            db.execSQL("INSERT INTO JOB(jobno ,comfrom ,comto, status ,orderdatetime ,pickupdatetime ,deliverydatetime) values"
//                    + "(2010,2001,2002,'Ordered','2018-02-02 17:12:43',NULL,NULL); ");


                    db.execSQL("DROP TABLE IF EXISTS Company;");
            db.execSQL("CREATE TABLE Company (comNo int PRIMARY KEY,comName text, comAddress text, comPhone int);");
//            db.execSQL("INSERT INTO  COMPANY(comNO,comName,comAddress,comPhone)values"+
//            "(2001,'IVE/Chai Wan','30 Shing Tai Road Chai Wan, Hong Kong','2595 8333')");
//            db.execSQL("INSERT INTO  COMPANY(comNO,comName,comAddress,comPhone)values"+
//                    "(2002,'IVE/Haking Wong','702 Lai Chi Kok Road, Cheung Sha Wan, Kowloon','2361 5161')");
//            db.execSQL("INSERT INTO  COMPANY(comNO,comName,comAddress,comPhone)values"+
//                    "(2003,'IVE/Kwai Chung','20 Hing Shing Road, Kwai Chung, New Territories','2424 6221')");
//            db.execSQL("INSERT INTO  COMPANY(comNO,comName,comAddress,comPhone)values"+
//                    "(2004,'IVE/Kwun Tong','25 Hiu Ming Street, Kwun Tong, Kowloon','2727 4331')");
//            db.execSQL("INSERT INTO  COMPANY(comNO,comName,comAddress,comPhone)values"+
//                    "(2005,'IVE/Lee Wai Lee','3 King Ling Road, Tseung Kwan O, New Territories','3928 2000')");
//            db.execSQL("INSERT INTO  COMPANY(comNO,comName,comAddress,comPhone)values"+
//                    "(2006,'IVE/Morrison Hill','6 Oi Kwan Road, Wan Chai, Hong Kong','2574 5321')");
//            db.execSQL("INSERT INTO  COMPANY(comNO,comName,comAddress,comPhone)values"+
//                    "(2007,'IVE/Sha Tin','21 Yuen Wo Road, Sha Tin, New Territories','2606 6227')");
//            db.execSQL("INSERT INTO  COMPANY(comNO,comName,comAddress,comPhone)values"+
//                    "(2008,'IVE/Tsing Yi','20 Tsing Yi Road, Tsing Yi Island, New Territories','2436 8333')");
//            db.execSQL("INSERT INTO  COMPANY(comNO,comName,comAddress,comPhone)values"+
//                    "(2009,'IVE/Tuen Mun','18 Tsing Wun Road, Tuen Mun, New Territories','2463 0066')");

            Cursor cursor = db.rawQuery("SELECT * FROM Job", null);
            Cursor cursor2 = db.rawQuery("SELECT * FROM Company",null );


            while (cursor.moveToNext()) {
                members.add(new Job(
                        cursor.getInt(cursor.getColumnIndex("jobno")),
                        cursor.getInt(cursor.getColumnIndex("comfrom")),
                        cursor.getInt(cursor.getColumnIndex("comto")),
                        cursor.getString(cursor.getColumnIndex("status")),
                        cursor.getString(cursor.getColumnIndex("orderdatetime")),
                        cursor.getString(cursor.getColumnIndex("pickupdatetime")),
                        cursor.getString(cursor.getColumnIndex("deliverydatetime"))
                        // cursor.getColumnIndex("comfrom")
                ));
            }

            db.close();
    } catch (SQLiteException e) {
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
    }
    }


    public Company matchAddree(int companyID){
        String address = null;
        Company c =new Company();
        try{
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
       // String [] arg = {Integer.toString(companyID)};
        Log.d(TAG,"companyID"+Integer.toString(companyID));
       // String a[] = {Integer.toString(companyID)};
        String a[] = {Integer.toString(companyID)};
        Log.d(TAG,"companyID2"+a);
        Cursor cursor = db.rawQuery("SELECT * FROM Company WHERE comNO = ?",a );
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

    public Company matchAddreeTo(int companyID){
        String address = null;
        Company c =new Company();
        try{
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
            // String [] arg = {Integer.toString(companyID)};
            Log.d(TAG,"companyID"+Integer.toString(companyID));
            // String a[] = {Integer.toString(companyID)};
            String a[] = {Integer.toString(companyID)};
            Log.d(TAG,"companyID2"+a);
            Cursor cursor = db.rawQuery("SELECT * FROM Company WHERE comNO = ?",a );
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

}
