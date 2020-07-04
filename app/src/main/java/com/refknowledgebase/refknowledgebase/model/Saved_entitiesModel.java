package com.refknowledgebase.refknowledgebase.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Saved_entitiesModel {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("faqs")
    private List<Home_Content_engitiesModel> faqs;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Home_Content_engitiesModel> getFaqs() {
        return faqs;
    }

    public void setFaqs(List<Home_Content_engitiesModel> faqs) {
        this.faqs = faqs;
    }
}
