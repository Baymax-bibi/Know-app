package com.refknowledgebase.refknowledgebase.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.refknowledgebase.refknowledgebase.model.Contact_form_entities_Model;
import com.refknowledgebase.refknowledgebase.model.Hashtags_entities_Model;
import com.refknowledgebase.refknowledgebase.model.Phone_Model;
import com.refknowledgebase.refknowledgebase.model.Working_Hour_Model;

import java.util.ArrayList;
import java.util.List;

public class Directory_List_one_Adapter extends BaseAdapter {

    Context mContext;
    List<Contact_form_entities_Model> contactFormEntitiesModelList;
    List<Phone_Model> phoneModelList;
    List<Hashtags_entities_Model> hashtags_entities_modelList;
    public Directory_List_one_Adapter(Context _context){
        mContext = _context;
        contactFormEntitiesModelList = new ArrayList<>();
        phoneModelList = new ArrayList<>();
        hashtags_entities_modelList = new ArrayList<>();
    }
    @Override
    public int getCount() {
        return 0;
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

    public void add(Contact_form_entities_Model r){
        contactFormEntitiesModelList.add(r);
        notifyDataSetChanged();
    }
    public void addAll(List<Contact_form_entities_Model> movieResults){
        for (Contact_form_entities_Model result: movieResults){
            add(result);
        }
    }

    public void addAll_Phone(List<Phone_Model> phone_modelList) {
        for (Phone_Model result: phone_modelList){
            add_Phone(result);
        }
    }

    private void add_Phone(Phone_Model result) {
        phoneModelList.add(result);
        notifyDataSetChanged();
    }

    public void addAll_hashtag(List<Hashtags_entities_Model> hashtags_entities_modelList) {
        for (Hashtags_entities_Model result: hashtags_entities_modelList){
            add_hashtag(result);
        }
    }

    private void add_hashtag(Hashtags_entities_Model result) {
        hashtags_entities_modelList.add(result);
        notifyDataSetChanged();
    }
}
