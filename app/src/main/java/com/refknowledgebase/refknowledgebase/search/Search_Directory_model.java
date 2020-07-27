package com.refknowledgebase.refknowledgebase.search;

public class Search_Directory_model {
    private String distance, association, address;
    //private int img_call, img_location, img_saved;
    public Search_Directory_model(String distance, String association, String address) {
        this.distance = distance;
        this.association = association;
        this.address = address;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getAssociation() {
        return association;
    }

    public void setAssociation(String association) {
        this.association = association;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}