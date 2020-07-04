package com.refknowledgebase.refknowledgebase.model;

import com.google.gson.annotations.SerializedName;

class Contacts_entities_Model {
    @SerializedName("id")
    private int id;
    @SerializedName("directory_id")
    private int directory_id;
    @SerializedName("contact_label")
    private String contact_label;
    @SerializedName("is_default")
    private int is_default;
    @SerializedName("website")
    private String website;
    @SerializedName("country_id")
    private int country_id;
}
