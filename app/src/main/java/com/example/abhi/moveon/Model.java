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
        return "Rate : "+String.valueOf(rpd);
    }

    public void setRpd(int rpd) {
        this.rpd = rpd;
    }

    public String getModel() {
        return "Model : " + String.valueOf(model);
    }

    public void setModel(int model) {
        this.model = model;
    }

    public String getCc() {
        return "Engine CC :"+ String.valueOf(cc);
    }

    public void setCc(int cc) {
        this.cc = cc;
    }

    public String getMilage() {
        return "Milage : "+ String.valueOf(milage);
    }

    public void setMilage(int milage) {
        this.milage = milage;
    }
}