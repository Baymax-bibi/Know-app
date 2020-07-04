package com.refknowledgebase.refknowledgebase.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Service_Category_Model {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("translations")
    private List<Translation_Model> translations;
    @SerializedName("icon")
    private String icon;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Translation_Model> getTranslations() {
        return translations;
    }

    public String getIcon() {
        return icon;
    }
}
