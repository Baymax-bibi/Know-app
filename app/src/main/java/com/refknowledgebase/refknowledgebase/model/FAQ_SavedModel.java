package com.refknowledgebase.refknowledgebase.model;

import com.google.gson.annotations.SerializedName;

public class FAQ_SavedModel {

    @SerializedName("id")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
