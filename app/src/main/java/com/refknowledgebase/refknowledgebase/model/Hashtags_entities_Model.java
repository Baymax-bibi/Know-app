package com.refknowledgebase.refknowledgebase.model;

import com.google.gson.annotations.SerializedName;

public class Hashtags_entities_Model extends Hashtags_BaseModel{

    @SerializedName("id")
    private int id;
    @SerializedName("hashtag")
    private String hashtag;

    @Override
    public int getid() {
        return id;
    }
    public String getHashtag() {
        return hashtag;
    }
}
