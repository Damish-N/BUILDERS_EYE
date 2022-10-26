package com.example.be4.models;

public class Site {
    String siteName;
    String supervisorName;

    public Site() {
    }

    public Site(String siteName, String supervisorName) {
        this.siteName = siteName;
        this.supervisorName = supervisorName;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }
}
