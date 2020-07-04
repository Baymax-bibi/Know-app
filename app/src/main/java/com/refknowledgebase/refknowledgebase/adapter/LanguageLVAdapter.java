package com.refknowledgebase.refknowledgebase.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.refknowledgebase.refknowledgebase.R;
import com.refknowledgebase.refknowledgebase.model.Country_BaseModel;
import com.refknowledgebase.refknowledgebase.model.Language_BaseModel;

import java.util.List;

public class LanguageLVAdapter  extends ArrayAdapter<Language_BaseModel> {
    Context mConext;

    public LanguageLVAdapter(Context _context, List<Language_BaseModel> objects){
        super(_context, 0, objects);
        mConext = _context;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(mConext).inflate(R.layout.item_language_lv,null);

        final TextView lbl= (TextView) convertView.findViewById(R.id.tv_lang_name);
        lbl.setText("  " + getItem(position).getname());
        return convertView;
    }
}
