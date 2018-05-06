package com.example.ben.joblist;

public class Job {
    private int jobno;
    private int comfrom;
    private int comto;
    private String status;
    private String orderdatetime;
    private String pickupdatetime;
    private String deliverydatetime;

    public Job(int jobno, int comfrom, int comto, String status, String orderdatetime, String pickupdatetime, String deliverydatetime) {
        this.jobno = jobno;
        this.comfrom = comfrom;
        this.comto = comto;
        this.status = status;
        this.orderdatetime = orderdatetime;
        this.pickupdatetime = pickupdatetime;
        this.deliverydatetime = deliverydatetime;
    }

    public Job() {
    }

    public int getJobno() {
        return jobno;
    }

    public void setJobno(int jobno) {
        this.jobno = jobno;
    }

    public int getComfrom() {
        return comfrom;
    }

    public void setComfrom(int comfrom) {
        this.comfrom = comfrom;
    }

    public int getComto() {
        return comto;
    }

    public void setComto(int comto) {
        this.comto = comto;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderdatetime() {
        return orderdatetime;
    }

    public void setOrderdatetime(String orderdatetime) {
        this.orderdatetime = orderdatetime;
    }

    public String getPickupdatetime() {
        return pickupdatetime;
    }

    public void setPickupdatetime(String pickupdatetime) {
        this.pickupdatetime = pickupdatetime;
    }

    public String getDeliverydatetime() {
        return deliverydatetime;
    }

    public void setDeliverydatetime(String deliverydatetime) {
        this.deliverydatetime = deliverydatetime;
    }
}
