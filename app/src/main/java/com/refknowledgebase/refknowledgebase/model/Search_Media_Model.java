package com.refknowledgebase.refknowledgebase.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Search_Media_Model {

    @SerializedName("total")
    private int total;
    @SerializedName("current_page")
    private int current_page;
    @SerializedName("last_page")
    private int last_page;
    @SerializedName("per_page")
    private int per_page;
    @SerializedName("to")
    private int to;
    @SerializedName("entities")
    private List<Search_Media_entities_Model> entities;
    @SerializedName("pagination")
    private Boolean pagination;

    public int getTotal() {
        return total;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public int getLast_page() {
        return last_page;
    }

    public int getPer_page() {
        return per_page;
    }

    public int getTo() {
        return to;
    }

    public List<Search_Media_entities_Model> getEntities() {
        return entities;
    }

    public Boolean getPagination() {
        return pagination;
    }
}
