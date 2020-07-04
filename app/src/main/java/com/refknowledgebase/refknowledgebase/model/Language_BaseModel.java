package com.refknowledgebase.refknowledgebase.model;

import java.util.List;

public abstract class Language_BaseModel {
    abstract public int getid();
    abstract public String getname();
    abstract public String getacronym();
    abstract public int getis_active();
    abstract public int getis_default();
    abstract public String gettext_direction();
    abstract public String getcreated_by();
    abstract public String getupdated_by();
    abstract public String getcreated_at();
    abstract public String getupdated_at();
    abstract public String getdeleted_at();

}
