package com.refknowledgebase.refknowledgebase.model;

import com.google.gson.annotations.SerializedName;

public class Working_Hour_Model {
    @SerializedName("id")
    private int id;
    @SerializedName("day")
    private String day;
    @SerializedName("from")
    private String from;
    @SerializedName("to")
    private String to;

    public int getId() {
        return id;
    }

    public String getDay() {
        return day;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}
