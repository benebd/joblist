package com.example.ben.joblist;

public class Company {
    private int comNo;
    private String comName;
    private String comAddress;
    private int comPhone;

    public Company() {
    }

    public Company(int comNo, String comName, String comAddress, int comPhone) {
        this.comNo = comNo;
        this.comName = comName;
        this.comAddress = comAddress;
        this.comPhone = comPhone;
    }

    public int getComNo() {
        return comNo;
    }

    public void setComNo(int comNo) {
        this.comNo = comNo;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public String getComAddress() {
        return comAddress;
    }

    public void setComAddress(String comAddress) {
        this.comAddress = comAddress;
    }

    public int getComPhone() {
        return comPhone;
    }

    public void setComPhone(int comPhone) {
        this.comPhone = comPhone;
    }
}
