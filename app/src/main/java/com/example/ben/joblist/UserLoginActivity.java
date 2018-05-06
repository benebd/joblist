package com.example.ben.joblist;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Canvas;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class UserLoginActivity extends AppCompatActivity {
    private final String TAG = "UserLogin";

    private ArrayList<Staff> staff;



    private EditText inname;
    private EditText inpassword;
    private Button loginbt;

    public String dname;
    public String dpwd;
    private String userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       // setupData();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        setupData();
        inname = (EditText) findViewById(R.id.nameet);
        inpassword=(EditText)findViewById(R.id.pwdet);
        loginbt =(Button)findViewById(R.id.loginbn);

        //String iname = inname.getText().toString();
        Log.d(TAG,"iname"+inname.getText().toString());
//        if (iname != null) {
//            //int intInName = Integer.parseInt(iname);
//        }

         final String ipwd = inpassword.getText().toString();
//        for(int i = 0;i < staff.size();i++){
//            Staff s = staff.get(i);
//            if(iname == s.getStaffid() && ipwd == s.getStaffpswd()){
//                Toast.makeText(getApplicationContext(),"LOGIN",Toast.LENGTH_LONG).show();
//
//            }
//        }

        loginbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int intInName = 0;
                String iname = inname.getText().toString();
                String ipwd = inpassword.getText().toString();
                Log.d(TAG, "iname" + iname);
                Toast.makeText(getApplicationContext(), "name:"+iname, Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "password:"+ipwd, Toast.LENGTH_LONG).show();
                if (iname != null) {
                    try {
                         intInName = Integer.parseInt(iname);
                        Log.d(TAG, "iname" + intInName);
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Please Insert User id)", Toast.LENGTH_LONG).show();
                    }
                   if(check(intInName,ipwd)) {
                       Intent intent = new Intent(UserLoginActivity.this,JobList.class);
                       intent.putExtra("userName",userName);
                       startActivity(intent);

                       Toast.makeText(getApplicationContext(), "successful", Toast.LENGTH_LONG).show();


                   }
                    Log.d(TAG, "intInName" + intInName);
                    Log.d(TAG, "ipwd" + ipwd);
                }
            }
        });

    }

    public void setupData() {
       staff = new ArrayList<Staff>();
        try {
            SQLiteDatabase db = SQLiteDatabase.openDatabase("/data/data/com.example.ben.joblist/JobDB", null, SQLiteDatabase.CREATE_IF_NECESSARY);
            db.execSQL("DROP TABLE IF EXISTS STAFF;");
            db.execSQL("CREATE TABLE STAFF (staffid int PRIMARY KEY,staffpswd text,staffname text);");
            db.execSQL("INSERT INTO STAFF(staffid ,staffpswd ,staffname) values "
                    + "(1001,'pwd1001','John Chan'); ");
            db.execSQL("INSERT INTO STAFF(staffid ,staffpswd ,staffname) values "
                    + "(1002,'pwd1002','Jacky Au'); ");
            db.execSQL("INSERT INTO STAFF(staffid ,staffpswd ,staffname) values "
                   + "(1003,'pwd1003','Tom Wong'); ");
            db.execSQL("INSERT INTO STAFF(staffid ,staffpswd ,staffname) values "
                    + "(1004,'pwd1004','Calvin Yu'); ");

            db.execSQL("DROP TABLE IF EXISTS Job;");
            db.execSQL("CREATE TABLE JOB (jobno int PRIMARY KEY,comfrom int, comto int, status text,orderdatetime text,pickupdatetime text, deliverydatetime text);");
            db.execSQL("INSERT INTO JOB(jobno ,comfrom ,comto, status ,orderdatetime ,pickupdatetime ,deliverydatetime) values "
                    + "(9001,2009,2004,'Picked','2018-01-31 15:05:32','2018-02-01 09:23:09',NULL); ");
            db.execSQL("INSERT INTO JOB(jobno ,comfrom ,comto, status ,orderdatetime ,pickupdatetime ,deliverydatetime) values"
                    + "(9002,2001,2006,'Completed','2018-02-01 10:24:23','2018-02-01 13:26:12','2018-02-01 16:53:45'); ");
            db.execSQL("INSERT INTO JOB(jobno ,comfrom ,comto, status ,orderdatetime ,pickupdatetime ,deliverydatetime) values"
                    + "(9003,2005,2007,'Completed','2018-02-01 11:11:35','2018-02-01 12:39:54','2018-02-01 15:22:48'); ");
            db.execSQL("INSERT INTO JOB(jobno ,comfrom ,comto, status ,orderdatetime ,pickupdatetime ,deliverydatetime) values"
                    + "(9004,2009,2001,'Ordered','2018-02-01 15:21:24',NULL,NULL); ");
            db.execSQL("INSERT INTO JOB(jobno ,comfrom ,comto, status ,orderdatetime ,pickupdatetime ,deliverydatetime) values"
                    + "(9005,2005,2004,'Picked','2018-02-02 11:52:12','2018-02-02 15:20:53',NULL); ");
            db.execSQL("INSERT INTO JOB(jobno ,comfrom ,comto, status ,orderdatetime ,pickupdatetime ,deliverydatetime) values"
                    + "(9006,2003,2002,'Picked','2018-02-02 12:36:55','2018-02-02 16:14:21',NULL); ");
            db.execSQL("INSERT INTO JOB(jobno ,comfrom ,comto, status ,orderdatetime ,pickupdatetime ,deliverydatetime) values"
                    + "(9007,2001,2002,'Ordered','2018-02-02 17:12:43',NULL,NULL); ");




            Cursor cursor = db.rawQuery("SELECT * FROM Staff", null);



            while (cursor.moveToNext()) {
                staff.add(new Staff(
                        cursor.getInt(cursor.getColumnIndex("staffid")),
                        cursor.getString(cursor.getColumnIndex("staffname")),
                        cursor.getString(cursor.getColumnIndex("staffpswd"))
                ));
            }

            db.close();
        } catch (SQLiteException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public boolean check(int name,String pwd){
        boolean Login = false;

        for(int i = 0;i < staff.size();i++){
            Staff s = staff.get(i);
            Toast.makeText(getApplicationContext(),"id"+s.getStaffid(),Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(),"pwd"+s.getStaffpswd(),Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(),"idnamr"+name,Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(),"pwddd"+pwd,Toast.LENGTH_LONG).show();
            if(name == s.getStaffid() && pwd.equals(s.getStaffpswd()) ){
                Toast.makeText(getApplicationContext(),"LOGIN",Toast.LENGTH_LONG).show();
                setUserName(s.getStaffname());
                Login = true;
                break;



            }else {
                Toast.makeText(getApplicationContext(),"Wrong name or password",Toast.LENGTH_LONG).show();
                       // Login = false;
            }
        }

        return  Login;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
}
