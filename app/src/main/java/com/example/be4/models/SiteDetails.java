package com.example.be4.models;

import java.io.Serializable;
import java.util.ArrayList;

public class SiteDetails implements Serializable {
    String id;
    String siteName;
    String supEmail;
    String supName;
    ArrayList<Item> siteItems;

    public SiteDetails(String id, String siteName, String supEmail, String supName, ArrayList<Item> siteItems) {
        this.id = id;
        this.siteName = siteName;
        this.supEmail = supEmail;
        this.supName = supName;
        this.siteItems = siteItems;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SiteDetails() {
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getSupEmail() {
        return supEmail;
    }

    public void setSupEmail(String supEmail) {
        this.supEmail = supEmail;
    }

    public String getSupName() {
        return supName;
    }

    public void setSupName(String supName) {
        this.supName = supName;
    }

    public ArrayList<Item> getSiteItems() {
        return siteItems;
    }

    public void setSiteItems(ArrayList<Item> siteItems) {
        this.siteItems = siteItems;
    }
}
