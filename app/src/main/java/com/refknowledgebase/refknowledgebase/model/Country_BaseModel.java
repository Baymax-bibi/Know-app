package com.refknowledgebase.refknowledgebase.model;

import com.android.volley.toolbox.StringRequest;

import java.util.List;

public abstract class Country_BaseModel {
    abstract public int getid();
    abstract public String getcountry();
    abstract public String getcontinent();
    abstract public String getcreated_by();
    abstract public String getupdated_by();
    abstract public String getcreated_at();
    abstract public String getupdated_at();
    abstract public String getdeleted_at();
    abstract public List<String> getentities();
}
