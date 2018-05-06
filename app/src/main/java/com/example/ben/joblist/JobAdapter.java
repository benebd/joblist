package com.example.ben.joblist;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by edwar on 2017-02-28.
 */

public class JobAdapter extends BaseAdapter {

    private LayoutInflater contextView;
    private ArrayList<Job> jobs;
    private final String TAG = "jobAdapter";

    private class ViewHolder {
        TextView tvID;
        TextView tvName;
        TextView tvComFrom;
        TextView tvComTo;
        public ViewHolder(TextView tvID, TextView tvName,TextView tvComFrom,TextView tvComTo) {
            this.tvID = tvID;
            this.tvName = tvName;
            this.tvComFrom = tvComFrom;
            this.tvComTo = tvComTo;
        }
    }

    public JobAdapter(Context context, ArrayList<Job> jobs) {
        contextView = LayoutInflater.from(context);
        this.jobs = jobs;
    }

    @Override
    public int getCount() {
        return jobs.size();
    }

    @Override
    public Object getItem(int i) {
        return jobs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = contextView.inflate(R.layout.member_info, null);
            holder = new ViewHolder((TextView) view.findViewById(R.id.tvID),
                    (TextView) view.findViewById(R.id.tvName),
                    (TextView) view.findViewById(R.id.tvComFrom),
                    (TextView) view.findViewById(R.id.tvComTo));
            view.setTag(holder);
        } else
            holder = (ViewHolder) view.getTag();

        Job job = jobs.get(i);
        holder.tvID.setText(String.valueOf(job.getJobno()));
        holder.tvName.setText(job.getStatus());
        holder.tvComFrom.setText(setUpDate(job.getComfrom()));
        holder.tvComTo.setText(setUpDate(job.getComto()));
        return view;
    }
public String setUpDate(int addressNO) {
        String address = null;
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
            String[] arg = {Integer.toString(addressNO)};

            Cursor cursor = db.rawQuery("Select * from Company where comNo = ?", arg);
            cursor.moveToLast();
            address = cursor.getString(cursor.getColumnIndex("comName"));
           // comto = cursor.getString(cursor.getColumnIndex("comTo"));

        }catch (Exception e){
            Log.d(TAG,"setUpDate"+e.getMessage());
        }
        return  address;
}

}
