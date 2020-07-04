package com.refknowledgebase.refknowledgebase.model;

public class Search_Media_Model_temp {
    private String title;
    private String search_content;
    private String videoId;
    public Search_Media_Model_temp(String title, String content, String videoId) {

        this.title = title;
        this.search_content = content;
        this.videoId = videoId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String description) {
        this.title = title;
    }
    public String getSearch_content() {
        return search_content;
    }
    public void setSearch_content(String description) {
        this.search_content = search_content;
    }
    public String getVideoId() {
        return videoId;
    }
    public void setVideoId(int imgId) {
        this.videoId = videoId;
    }
}