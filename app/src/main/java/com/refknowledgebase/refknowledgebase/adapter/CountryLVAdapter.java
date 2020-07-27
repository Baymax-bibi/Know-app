package com.refknowledgebase.refknowledgebase.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.refknowledgebase.refknowledgebase.R;
import com.refknowledgebase.refknowledgebase.model.Country_BaseModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class CountryLVAdapter  extends ArrayAdapter<Country_BaseModel> {
    Context mConext;

    public CountryLVAdapter(Context _context, List<Country_BaseModel> objects){
        super(_context, 0, objects);
        mConext = _context;
    }
    @NotNull
    @SuppressLint({"ViewHolder", "SetTextI18n", "InflateParams"})
    @Override
    public View getView(int position, View convertView, @NotNull ViewGroup parent) {
        convertView= LayoutInflater.from(mConext).inflate(R.layout.item_country_lv,null);

        final TextView lbl= (TextView) convertView.findViewById(R.id.tv_country_name);
        lbl.setText("  " + Objects.requireNonNull(getItem(position)).getcountry());
        return convertView;
    }
}
