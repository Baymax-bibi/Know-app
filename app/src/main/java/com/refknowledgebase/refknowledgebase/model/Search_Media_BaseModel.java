package com.refknowledgebase.refknowledgebase.model;

public abstract class Search_Media_BaseModel {
    abstract public int getid();
    abstract public String getcreated_by();
    abstract public String getupdated_by();
    abstract public String getservice_categories();
//    abstract public String getmeta();
    abstract public int getlanguage_id();
    abstract public String getlanguage();
    abstract public String gettitle();
    abstract public String getdescription();
    abstract public String getcontent_type();
    abstract public int getlinked_faqs_count();
    abstract public String getnationalities();
    abstract public String getcountries();
    abstract public String geturl();
//    abstract public String getcreated_at();
//    abstract public String getupdated_at();
//    abstract public String getdeleted_at();
}
