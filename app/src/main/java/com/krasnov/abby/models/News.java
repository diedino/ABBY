package com.krasnov.abby.models;

import com.google.gson.annotations.SerializedName;

public class News {

    @SerializedName("title")
    private String title;
    @SerializedName("creation_date")
    private String creation_date;


    public News(String title, String creation_date) {
        this.title = title;
        this.creation_date = creation_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }

}
