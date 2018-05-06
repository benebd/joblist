package com.example.ben.joblist;

public class Staff {
    private int staffid;
    private String staffpswd;
    private String staffname;


    public Staff(int staffid,String staffname ,String staffpswd)  {
        this.staffid = staffid;
        this.staffpswd = staffpswd;
        this.staffname = staffname;
    }

    public int getStaffid() {
        return staffid;
    }

    public void setStaffid(int staffid) {
        this.staffid = staffid;
    }

    public String getStaffpswd() {
        return staffpswd;
    }

    public void setStaffpswd(String staffpswd) {
        this.staffpswd = staffpswd;
    }

    public String getStaffname() {
        return staffname;
    }

    public void setStaffname(String staffname) {
        this.staffname = staffname;
    }
}
