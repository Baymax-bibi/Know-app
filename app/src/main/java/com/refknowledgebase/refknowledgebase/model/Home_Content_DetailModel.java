package com.refknowledgebase.refknowledgebase.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Home_Content_DetailModel {

    @SerializedName("id")
    private int id;
    @SerializedName("question")
    private String question;
    @SerializedName("answer")
    private String answer;
    @SerializedName("my_lists")
    private String my_lists;
    @SerializedName("short_answer")
    private String short_answer;
    @SerializedName("created_by")
    private String created_by;
    @SerializedName("updated_by")
    private String updated_by;
    @SerializedName("service_categories")
    private String service_categories;
    @SerializedName("nationalities")
    private String nationalities;
    @SerializedName("countries")
    private List<Country_entities_Model> countries;
    @SerializedName("hashtags")
    private List<Hashtags_entities_Model> hashtags;
    @SerializedName("directories")
    private List<Directory_List_entitiesModel> directories;
    @SerializedName("contacts")
    private List<Contacts_entities_Model> contacts;
    @SerializedName("media")
    private String media;
//    @SerializedName("social_media_links")
//    private String social_media_links;
    @SerializedName("comments")
    private String comments;
    @SerializedName("translations")
    private String translations;
    @SerializedName("visible")
    private String visible;
    @SerializedName("status")
    private String status;
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("updated_at")
    private String updated_at;
    @SerializedName("deleted_at")
    private String deleted_at;

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getMy_lists() {
        return my_lists;
    }

    public String getShort_answer() {
        return short_answer;
    }

    public List<Hashtags_entities_Model> getHashtags() {
        return hashtags;
    }

    public List<Country_entities_Model> getCountries() {
        return countries;
    }

    public List<Contacts_entities_Model> getContacts() {
        return contacts;
    }

    public List<Directory_List_entitiesModel> getDirectories() {
        return directories;
    }
}
