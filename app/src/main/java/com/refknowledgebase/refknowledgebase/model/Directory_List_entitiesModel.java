package com.refknowledgebase.refknowledgebase.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Directory_List_entitiesModel extends Directory_List_BaseModel{
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("alternative_name")
    private String alternative_name;
    @SerializedName("acronym")
    private String acronym;
    @SerializedName("created_by")
    private String created_by;
    @SerializedName("updated_by")
    private String updated_by;
    @SerializedName("description")
    private String description;
//    @SerializedName("directory_type")
//    private String directory_type;
//@SerializedName("hashtags")
////    private List<Hashtags_entities_Model> hashtags;
//private String hashtags;
    @SerializedName("type_id")
    private int type_id;
    @SerializedName("service_categories")
    private List<Service_Category_Model> service_categories;
//    @SerializedName("directory_media_links")
//    private String directory_media_links;
//    @SerializedName("translations")
//    private String translations;
    @SerializedName("status")
    private String status;
    @SerializedName("image")
    private String image;
    @SerializedName("contact_forms")
    private List<Contact_form_entities_Model> contact_forms;
    @SerializedName("media_id")
    private int media_id;


    @SerializedName("countries")
    private String[] countries;
//    @SerializedName("directory_media")
//    private String directory_media;
//    @SerializedName("created_at")
//    private String created_at;
//    @SerializedName("updated_at")
//    private String updated_at;
//    @SerializedName("deleted_at")
//    private String deleted_at;

    @Override
    public int getid() {
        return id;
    }

    @Override
    public String getname() {
        return name;
    }

    @Override
    public String getalternative_name() {
        return alternative_name;
    }

    @Override
    public String getacronym() {
        return acronym;
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
    public String getdescription() {
        return description;
    }

//    @Override
//    public String gethashtags() {
//        return hashtags;
//    }

//    @Override
//    public List<Hashtags_entities_Model> gethashtags() {
//        return hashtags;
//    }


    @Override
    public int gettype_id() {
        return type_id;
    }

    @Override
    public List<Service_Category_Model> getservice_categories() {
        return service_categories;
    }

//    @Override
//    public String getdirectory_media_links() {
//        return directory_media_links;
//    }

//    @Override
//    public String gettranslations() {
//        return translations;
//    }

    @Override
    public String getstatus() {
        return status;
    }

    @Override
    public String getimage() {
        return image;
    }

    @Override
    public List<Contact_form_entities_Model> getcontact_forms() {
        return contact_forms;
    }

    @Override
    public int getmedia_id() {
        return media_id;
    }

    @Override
    public String[] getcountries() {
        return countries;
    }

//    @Override
//    public String getdirectory_media() {
//        return directory_media;
//    }

//    @Override
//    public String getcreated_at() {
//        return created_at;
//    }
//
//    @Override
//    public String getupdated_at() {
//        return updated_at;
//    }
//
//    @Override
//    public String getdeleted_at() {
//        return deleted_at;
//    }
}
