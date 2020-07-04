package com.refknowledgebase.refknowledgebase.model;

import com.google.gson.annotations.SerializedName;

public class Search_Media_entities_Model extends Search_Media_BaseModel {
    @SerializedName("id")
    private int id;
    @SerializedName("created_by")
    private String created_by;
    @SerializedName("updated_by")
    private String updated_by;
    @SerializedName("service_categories")
    private String service_categories;
//    @SerializedName("meta")
//    private String meta;
    @SerializedName("language_id")
    private int language_id;
    @SerializedName("language")
    private String language;
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("content_type")
    private String content_type;
    @SerializedName("linked_faqs_count")
    private int linked_faqs_count;
    @SerializedName("nationalities")
    private String nationalities;
    @SerializedName("countries")
    private String countries;
    @SerializedName("url")
    private String url;
//    @SerializedName("created_at")
//    private String created_at;
//    @SerializedName("updated_at")
//    private String updated_at;
//    @SerializedName("deleted_at")
//    private String deleted_at;

    @Override
    public int getid() {
        return id;
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
    public String getservice_categories() {
        return service_categories;
    }

//    @Override
//    public String getmeta() {
//        return meta;
//    }

    @Override
    public int getlanguage_id() {
        return language_id;
    }

    @Override
    public String getlanguage() {
        return language;
    }

    @Override
    public String gettitle() {
        return title;
    }

    @Override
    public String getdescription() {
        return description;
    }

    @Override
    public String getcontent_type() {
        return content_type;
    }

    @Override
    public int getlinked_faqs_count() {
        return linked_faqs_count;
    }

    @Override
    public String getnationalities() {
        return nationalities;
    }

    @Override
    public String getcountries() {
        return countries;
    }

    @Override
    public String geturl() {
        return url;
    }

//    @Override
//    public String getcreated_at() {
//        return created_at;
//    }
//
//    @Override
//    public String getupdated_at() {
//        return updated_at;
//    }
//
//    @Override
//    public String getdeleted_at() {
//        return deleted_at;
//    }
}
