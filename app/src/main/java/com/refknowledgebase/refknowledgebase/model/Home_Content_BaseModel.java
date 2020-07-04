package com.refknowledgebase.refknowledgebase.model;

public abstract class Home_Content_BaseModel {
    abstract public int getid();
    abstract public String getQuestion();
    abstract public String getQuestion_normalize();
    abstract public String getAnswer();
    abstract public String getStatus();
    abstract public int getCreated_by();
    abstract public String getVisible();
    abstract public int[] getService_category_ids();
    abstract public int[] getCountry_ids();
    abstract public int[] getNationality_ids();
    abstract public String[] getHashtags();
}
