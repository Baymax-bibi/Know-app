package com.refknowledgebase.refknowledgebase.model;

import com.google.gson.annotations.SerializedName;

public class Swipe_Tab_entitiesModel extends Swipe_Tab_BaseModel {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("parent")
    private String parent;
    @SerializedName("children")
    private String[] children;
    @SerializedName("translations")
    private String translations;
    @SerializedName("parent_id")
    private int parent_id;
    @SerializedName("icon")
    private String icon;


    @Override
    public int getid() {
        return id;
    }

    @Override
    public String getname() {
        return name;
    }

    @Override
    public String getparent() {
        return parent;
    }

    @Override
    public String[] getchildren() {
        return children;
    }

    @Override
    public String gettranslations() {
        return translations;
    }

    @Override
    public int getparent_id() {
        return parent_id;
    }

    @Override
    public String geticon() {
        return icon;
    }

    public int getId() {
        return id;
    }
}
