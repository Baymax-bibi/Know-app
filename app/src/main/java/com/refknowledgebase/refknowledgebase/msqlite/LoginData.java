package com.refknowledgebase.refknowledgebase.msqlite;

import android.net.Uri;

public class LoginData {
    private int _id;
    private String photo_uri;
    private String name;
    LoginData(){   }
    LoginData(int id, String _username, String _photo_uri){
        this._id = id;
        photo_uri = _photo_uri;
        name = _username;
    }
    public LoginData(String _username, String _photo_uri){
        photo_uri = _photo_uri;
        name = _username;
    }

    public String getPhoto_uri() {
        return photo_uri;
    }

    public void setPhoto_uri(String photo_uri) {
        this.photo_uri = photo_uri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}
