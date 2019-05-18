package com.krasnov.abby.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemsAPI {

    @SerializedName("items")
    private List<News> items;

    public List<News> getItems() {
        return items;
    }

    public void setItems(List<News> items) {
        this.items = items;
    }
}
