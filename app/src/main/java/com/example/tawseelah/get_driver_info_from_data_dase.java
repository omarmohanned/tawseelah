package com.example.tawseelah;

public class get_driver_info_from_data_dase {
    private String carcolor;
    private String firstname;
    private String secondname;
    private String cartype;
    private String phonenumber;
    private String SumAllbill;
    private String cashdesired;
    private String uid_driver;

    public get_driver_info_from_data_dase() {
    }

    public get_driver_info_from_data_dase(String carcolor, String firstname, String secondname, String cartype, String phonenumber, String sumAllbill, String cashdesired, String uid_driver) {
        this.carcolor = carcolor;
        this.firstname = firstname;
        this.secondname = secondname;
        this.cartype = cartype;
        this.phonenumber = phonenumber;
        SumAllbill = sumAllbill;
        this.cashdesired = cashdesired;
        this.uid_driver = uid_driver;
    }

    public String getCarcolor() {
        return carcolor;
    }

    public void setCarcolor(String carcolor) {
        this.carcolor = carcolor;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public String getCartype() {
        return cartype;
    }

    public void setCartype(String cartype) {
        this.cartype = cartype;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getSumAllbill() {
        return SumAllbill;
    }

    public void setSumAllbill(String sumAllbill) {
        SumAllbill = sumAllbill;
    }

    public String getCashdesired() {
        return cashdesired;
    }

    public void setCashdesired(String cashdesired) {
        this.cashdesired = cashdesired;
    }

    public String getUid_driver() {
        return uid_driver;
    }

    public void setUid_driver(String uid_driver) {
        this.uid_driver = uid_driver;
    }
}
