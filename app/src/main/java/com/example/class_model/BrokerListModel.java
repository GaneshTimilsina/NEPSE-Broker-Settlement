package com.example.class_model;

import android.widget.ImageView;

public class BrokerListModel {
    String broker_no, broker_Name, address, ph_no, website, branch, lat, longi;
    int phImage;
    public BrokerListModel(String broker_no){
        this.broker_no = broker_no;
    }

    public BrokerListModel(String broker_no, String broker_Name, int phImage, String address, String ph_no, String branches, String website, String lat, String longi) {
        this.broker_no = broker_no;
        this.broker_Name = broker_Name;
        this.address = address;
        this.ph_no = ph_no;
        this.website = website;
        this.phImage = phImage;
        this.branch = branches;
        this.lat = lat;
        this.longi = longi;
    }

    public String getBroker_no() {
        return broker_no;
    }

    public void setBroker_no(String broker_no) {
        this.broker_no = broker_no;
    }

    public String getBroker_Name() {
        return broker_Name;
    }

    public void setBroker_Name(String broker_Name) {
        this.broker_Name = broker_Name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPh_no() {
        return ph_no;
    }

    public void setPh_no(String ph_no) {
        this.ph_no = ph_no;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public int getPhImage() {
        return phImage;
    }

    public void setPhImage(int phImage) {
        this.phImage = phImage;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLongi() {
        return longi;
    }

    public void setLongi(String longi) {
        this.longi = longi;
    }
}
