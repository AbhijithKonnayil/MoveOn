package com.example.abhi.moveon;

public class Model {
    public String mId, mTitle, mDesc;
    public String bikeName,company;
    public int rpd,model,cc,milage;

/*
    public Model(String id, String title, String desc) {

    }
*/

    public Model(String mId,String bikeName, String company, int  rpd, int model, int milage,int cc) {
        this.mId = mId;
        this.bikeName = bikeName;
        this.company = company;
        this.rpd = rpd;
        this.model = model;
        this.cc = cc;
        this.milage=milage;
    }


    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmDesc() {
        return mDesc;
    }

    public void setmDesc(String mDesc) {
        this.mDesc = mDesc;
    }

    public String getBikeName() {
        return bikeName;
    }

    public void setBikeName(String bikeName) {
        this.bikeName = bikeName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getRpd() {
        return String.valueOf(rpd);
    }

    public void setRpd(int rpd) {
        this.rpd = rpd;
    }

    public String getModel() {
        return String.valueOf(model);
    }

    public void setModel(int model) {
        this.model = model;
    }

    public String getCc() {
        return String.valueOf(cc);
    }

    public void setCc(int cc) {
        this.cc = cc;
    }

    public String getMilage() {
        return String.valueOf(milage);
    }

    public void setMilage(int milage) {
        this.milage = milage;
    }
}