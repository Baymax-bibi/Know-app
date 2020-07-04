package com.refknowledgebase.refknowledgebase.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.refknowledgebase.refknowledgebase.model.Directory_List_entitiesModel;
import com.refknowledgebase.refknowledgebase.model.Hashtags_entities_Model;

import java.util.ArrayList;
import java.util.List;

public class Assistance_detail_Adapter extends BaseAdapter {

    private List<Directory_List_entitiesModel> results;
    private List<Hashtags_entities_Model> Hashtags_entities_Model_list;

    public Assistance_detail_Adapter(){
        results = new ArrayList<>();
        Hashtags_entities_Model_list = new ArrayList<>();
    }
    public void add(Directory_List_entitiesModel r){
        results.add(r);
    }


    public void addAll(List<Directory_List_entitiesModel> directory_list_entitiesModels_list) {
        for (Directory_List_entitiesModel result : directory_list_entitiesModels_list) {
            add(result);
        }
    }

    public void add_Hashtags_entities_Model(Hashtags_entities_Model r){
        Hashtags_entities_Model_list.add(r);
    }


    public void addAll_Hashtags_entities_Model(List<Hashtags_entities_Model> Hashtags_entities_Model_list) {
        for (Hashtags_entities_Model result : Hashtags_entities_Model_list) {
            add_Hashtags_entities_Model(result);
        }
    }

    @Override
    public int getCount() {
        return results.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
