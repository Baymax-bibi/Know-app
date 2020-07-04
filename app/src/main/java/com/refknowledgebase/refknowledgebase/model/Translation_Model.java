package com.refknowledgebase.refknowledgebase.model;

import com.google.gson.annotations.SerializedName;

class Translation_Model {
    @SerializedName("id")
    private int id;
    @SerializedName("category_id")
    private int category_id;
    @SerializedName("language_id")
    private int language_id;
    @SerializedName("name")
    private String name;

    public int getId() {
        return id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public int getLanguage_id() {
        return language_id;
    }

    public String getName() {
        return name;
    }
}
