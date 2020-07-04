package com.refknowledgebase.refknowledgebase.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Home_Content_entities_created_at {

    @SerializedName("date")
    private Date date;

    @SerializedName("timezone_type")
    private int timezone_type;

    @SerializedName("timezone")
    private String timezone;

    public Date getDate() {
        return date;
    }

    public int getTimezone_type() {
        return timezone_type;
    }

    public String getTimezone() {
        return timezone;
    }
}
