package com.refknowledgebase.refknowledgebase.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Saved_faq_Model {

    @SerializedName("entities")
    private List<Saved_entitiesModel> entities;
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
    @SerializedName("pagination")
    private Boolean pagination;

    public List<Saved_entitiesModel> getEntities() {
        return entities;
    }

    public void setEntities(List<Saved_entitiesModel> entities) {
        this.entities = entities;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public int getLast_page() {
        return last_page;
    }

    public void setLast_page(int last_page) {
        this.last_page = last_page;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public Boolean getPagination() {
        return pagination;
    }

    public void setPagination(Boolean pagination) {
        this.pagination = pagination;
    }
}
