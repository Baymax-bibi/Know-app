package com.refknowledgebase.refknowledgebase.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.refknowledgebase.refknowledgebase.R;
import com.refknowledgebase.refknowledgebase.model.Country_BaseModel;

import java.util.List;

public class CountryLVAdapter  extends ArrayAdapter<Country_BaseModel> {
    Context mConext;

    public CountryLVAdapter(Context _context, List<Country_BaseModel> objects){
        super(_context, 0, objects);
        mConext = _context;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(mConext).inflate(R.layout.item_country_lv,null);

        final TextView lbl= (TextView) convertView.findViewById(R.id.tv_country_name);
        lbl.setText("  " + getItem(position).getcountry());
//        lbl.setTextColor(Color.parseColor("#ff7a1b"));

//        lbl.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                lbl.setTextColor(Color.parseColor("#ff7a1b"));
//            }
//        });

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
        getContext().startActivity(browserIntent);
        return convertView;
    }
}
