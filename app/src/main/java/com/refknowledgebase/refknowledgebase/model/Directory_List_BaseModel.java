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
    abstract public int gettype_id();
    abstract public List<Service_Category_Model> getservice_categories();
    abstract public String getstatus();
    abstract public String getimage();
    abstract public List<Contact_form_entities_Model> getcontact_forms();
    abstract public int getmedia_id();

    abstract public String[] getcountries();
}
