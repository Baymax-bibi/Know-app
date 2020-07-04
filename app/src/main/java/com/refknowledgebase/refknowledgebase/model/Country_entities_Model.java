package com.refknowledgebase.refknowledgebase.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Country_entities_Model extends Country_BaseModel {
    @SerializedName("id")
    private int id;
    @SerializedName("country")
    private String country;
    @SerializedName("continent")
    private String continent;
    @SerializedName("created_by")
    private String created_by;
    @SerializedName("updated_by")
    private String updated_by;
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("updated_at")
    private String updated_at;
    @SerializedName("deleted_at")
    private String deleted_at;

    @Override
    public int getid() {
        return id;
    }

    @Override
    public String getcountry() {
        return country;
    }

    @Override
    public String getcontinent() {
        return continent;
    }

    @Override
    public String getcreated_by() {
        return created_by;
    }

    @Override
    public String getupdated_by() {
        return updated_by;
    }

    @Override
    public String getcreated_at() {
        return created_at;
    }

    @Override
    public String getupdated_at() {
        return updated_at;
    }

    @Override
    public String getdeleted_at() {
        return deleted_at;
    }

    @Override
    public List<String> getentities() {
        return null;
    }
}
