package com.example.todoit;

public class myPlan {

    String titleplan;
    String descplan;
    String dateplan;
    String key;


    public myPlan() {

    }

    public myPlan(String titleplan, String descplan, String dateplan, String key) {
        this.titleplan = titleplan;
        this.descplan = descplan;
        this.dateplan = dateplan;
        this.key = key;
    }

    public String getkey() {
        return key;
    }

    public void setkey(String key) {
        this.key = key;
    }

    public String getTitleplan() {
        return titleplan;
    }

    public void setTitleplan(String titleplan) {
        this.titleplan = titleplan;
    }

    public String getDescplan() {
        return descplan;
    }

    public void setDescplan(String descplan) {
        this.descplan = descplan;
    }

    public String getDateplan() {
        return dateplan;
    }

    public void setDateplan(String dateplan) {
        this.dateplan = dateplan;
    }
}
