package com.refknowledgebase.refknowledgebase.model;

import com.google.gson.annotations.SerializedName;

public class Language_entities_Model extends Language_BaseModel{
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("acronym")
    private String acronym;
    @SerializedName("is_active")
    private int is_active;
    @SerializedName("is_default")
    private int is_default;
    @SerializedName("text_direction")
    private String text_direction;
    @SerializedName("created_by")
    private String created_by;
    @SerializedName("updated_by")
    private String updated_by;
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("updated_at")
    private String updated_at;
    @SerializedName("deleted_at")
    private String deleted_at;

    @Override
    public int getid() {
        return id;
    }

    @Override
    public String getname() {
        return name;
    }

    @Override
    public String getacronym() {
        return acronym;
    }

    @Override
    public int getis_active() {
        return is_active;
    }

    @Override
    public int getis_default() {
        return is_default;
    }

    @Override
    public String gettext_direction() {
        return text_direction;
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
    public String getcreated_at() {
        return created_at;
    }

    @Override
    public String getupdated_at() {
        return updated_at;
    }

    @Override
    public String getdeleted_at() {
        return deleted_at;
    }
}
