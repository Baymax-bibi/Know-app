package com.refknowledgebase.refknowledgebase.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Language_Model {
    @SerializedName("total")
    private String total;
    @SerializedName("current_page")
    private String current_page;
    @SerializedName("last_page")
    private String last_page;
    @SerializedName("per_page")
    private String per_page;
    @SerializedName("to")
    private String to;
    @SerializedName("entities")
    private List<Language_entities_Model> entities;
    @SerializedName("pagination")
    private Boolean pagination;

    public String getTotal() {
        return total;
    }

    public String getCurrent_page() {
        return current_page;
    }

    public String getLast_page() {
        return last_page;
    }

    public String getPer_page() {
        return per_page;
    }

    public String getTo() {
        return to;
    }

    public List<Language_entities_Model> getEntities() {
        return entities;
    }

    public Boolean getPagination() {
        return pagination;
    }
}
