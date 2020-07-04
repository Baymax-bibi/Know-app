package com.refknowledgebase.refknowledgebase.model;

import com.google.gson.annotations.SerializedName;

public class Phone_Model {
    @SerializedName("id")
    private int id;
    @SerializedName("contact_form_id")
    private int contact_form_id;
    @SerializedName("phone")
    private String phone;
    @SerializedName("country_code")
    private int country_code;
    @SerializedName("area_code")
    private int area_code;

    public int getId() {
        return id;
    }

    public int getContact_form_id() {
        return contact_form_id;
    }

    public String getPhone() {
        return phone;
    }

    public int getCountry_code() {
        return country_code;
    }

    public int getArea_code() {
        return area_code;
    }
}
