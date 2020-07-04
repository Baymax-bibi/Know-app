package com.refknowledgebase.refknowledgebase.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Country_Model{

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
    private List<Country_entities_Model> entities;
    @SerializedName("pagination")
    private Boolean pagination;


    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(String current_page) {
        this.current_page = current_page;
    }

    public String getLast_page() {
        return last_page;
    }

    public void setLast_page(String last_page) {
        this.last_page = last_page;
    }

    public String getPer_page() {
        return per_page;
    }

    public void setPer_page(String per_page) {
        this.per_page = per_page;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Boolean getPagination() {
        return pagination;
    }

    public void setPagination(Boolean pagination) {
        this.pagination = pagination;
    }

    public List<Country_entities_Model> getEntities() {
        return entities;
    }

    public void setEntities(List<Country_entities_Model> entities) {
        this.entities = entities;
    }
}
