package com.refknowledgebase.refknowledgebase.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Contact_form_entities_Model extends Contact_form_BaseModel{

    @SerializedName("id")
    private int id;
    @SerializedName("directory_id")
    private String directory_id;
    @SerializedName("contact_label")
    private String contact_label;
    @SerializedName("address_line_one")
    private String address_line_one;
    @SerializedName("address_line_two")
    private String address_line_two;
    @SerializedName("country")
    private String country;
    @SerializedName("city")
    private String city;
    @SerializedName("building_name")
    private String building_name;
    @SerializedName("email")
    private String email;
    @SerializedName("website")
    private String website;
    @SerializedName("landmark")
    private String landmark;
    @SerializedName("metro")
    private String metro;
    @SerializedName("pluscode")
    private String pluscode;
    @SerializedName("phones")
    private List<Phone_Model> phones;
    @SerializedName("working_hours")
    private List<Working_Hour_Model> working_hours;
    @SerializedName("lat")
    private String lat;
    @SerializedName("lng")
    private String lng;


    public String getDirectory_id() {
        return directory_id;
    }

    public String getContact_label() {
        return contact_label;
    }

    public String getAddress_line_one() {
        return address_line_one;
    }

    public String getAddress_line_two() {
        return address_line_two;
    }

    public String getCountry() {
        return country;
    }

    public String getEmail() {
        return email;
    }

    public List<Phone_Model> getPhones() {
        return phones;
    }

    public List<Working_Hour_Model> getWorking_hours() {
        return working_hours;
    }

    public String getLandmark() {
        return landmark;
    }

    public String getMetro() {
        return metro;
    }

    public String getPluscode() {
        return pluscode;
    }

    public String getCity() {
        return city;
    }

    public String getBuilding_name() {
        return building_name;
    }

    @Override
    public int getid() {
        return id;
    }

    @Override
    public String getwebsite() {
        return website;
    }

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }
}
