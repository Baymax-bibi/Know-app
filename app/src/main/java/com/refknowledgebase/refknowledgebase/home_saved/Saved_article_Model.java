package com.refknowledgebase.refknowledgebase.home_saved;

public class Saved_article_Model {
    private String title;
    private String content;
    public Saved_article_Model(String _title, String _content){
        this.title = _title;
        this.content = _content;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
