package com.refknowledgebase.refknowledgebase.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.refknowledgebase.refknowledgebase.R;
import com.refknowledgebase.refknowledgebase.model.Contact_form_entities_Model;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Related_Contact_Adapter extends ArrayAdapter<Contact_form_entities_Model> {

    private Context mcontext;

    public Related_Contact_Adapter(@NonNull Context context, List<Contact_form_entities_Model> resource) {
        super(context, 0, resource);
        mcontext = context;
    }

    @NotNull
    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, @NotNull ViewGroup parent) {

        convertView= LayoutInflater.from(mcontext).inflate(R.layout.item_related_contact,null);
        TextView tv_add_two, tv_add_one;
        tv_add_one = (TextView) convertView.findViewById(R.id.tv_add_one);
        tv_add_two = (TextView) convertView.findViewById(R.id.tv_add_two);

        tv_add_one.setText(getItem(position).getContact_label());
        tv_add_two.setText(getItem(position).getAddress_line_one());
        return convertView;
    }
}
