package com.refknowledgebase.refknowledgebase.search;

public class Search_Faq_model {
    private String description;
    private String title;
    private int img_1, img_2;
    public Search_Faq_model(String title, String description, int img_1, int img_2) {
        this.description = description;
        this.title = title;
        this.img_1 = img_1;
        this.img_2 = img_2;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImg_1() {
        return img_1;
    }

    public void setImg_1(int img_1) {
        this.img_1 = img_1;
    }

    public int getImg_2() {
        return img_2;
    }

    public void setImg_2(int img_2) {
        this.img_2 = img_2;
    }
}