package com.refknowledgebase.refknowledgebase.model;

import java.util.List;

public abstract class Directory_List_BaseModel {
    abstract public int getid();
    abstract public String getname();
    abstract public String getalternative_name();
    abstract public String getacronym();
    abstract public String getcreated_by();
    abstract public String getupdated_by();
    abstract public String getdescription();
//    abstract public String getdirectory_type();
//    abstract public List<Hashtags_entities_Model> gethashtags();
//    abstract public String gethashtags();
    abstract public int gettype_id();
    abstract public List<Service_Category_Model> getservice_categories();
//    abstract public String getdirectory_media_links();
//    abstract public String gettranslations();
    abstract public String getstatus();
    abstract public String getimage();
    abstract public List<Contact_form_entities_Model> getcontact_forms();
    abstract public int getmedia_id();
//    abstract public String getdirectory_media();
//    abstract public String getcreated_at();
//    abstract public String getupdated_at();
//    abstract public String getdeleted_at();
    abstract public String[] getcountries();
}
