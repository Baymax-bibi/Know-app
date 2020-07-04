package com.refknowledgebase.refknowledgebase.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class Home_Content_engitiesModel extends Home_Content_BaseModel{

    @SerializedName("id")
    private int id;
    @SerializedName("question")
    private String question;
    @SerializedName("question_normalize")
    private String question_normalize;
    @SerializedName("short_answer")
    private String short_answer;
    @SerializedName("answer")
    private String answer;
    @SerializedName("status")
    private String status;
    @SerializedName("created_by")
    private int created_by;
    @SerializedName("visible")
    private String visible;
    @SerializedName("service_category_ids")
    private int[] service_category_ids;
    @SerializedName("country_ids")
    private int[] country_ids;
    @SerializedName("nationality_ids")
    private int[] nationality_ids;
    @SerializedName("hashtags")
    private String[] hashtags;
//    @SerializedName("created_at")
//    private List<Home_Content_entities_created_at> created_at;
//    @SerializedName("updated_at")
//    private Date updated_at;


    @Override
    public int getid() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getQuestion_normalize() {
        return question_normalize;
    }

    public String getAnswer() {
        return answer;
    }

    public String getStatus() {
        return status;
    }

    public int getCreated_by() {
        return created_by;
    }

    public String getVisible() {
        return visible;
    }

    public int[] getService_category_ids() {
        return service_category_ids;
    }

    public int[] getCountry_ids() {
        return country_ids;
    }

    public int[] getNationality_ids() {
        return nationality_ids;
    }

    public String[] getHashtags() {
        return hashtags;
    }

    public String getShort_answer() {
        return short_answer;
    }

    public void setShort_answer(String short_answer) {
        this.short_answer = short_answer;
    }

//    public List<Home_Content_entities_created_at> getCreated_at() {
//        return created_at;
//    }

//    public Date getUpdated_at() {
//        return updated_at;
//    }
}
