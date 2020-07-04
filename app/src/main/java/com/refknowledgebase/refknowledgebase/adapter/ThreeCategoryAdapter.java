package com.refknowledgebase.refknowledgebase.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.refknowledgebase.refknowledgebase.R;
import com.refknowledgebase.refknowledgebase.model.Swipe_Tab_entitiesModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ThreeCategoryAdapter extends ArrayAdapter<Swipe_Tab_entitiesModel> {
    Context mContext;
    public ThreeCategoryAdapter(@NonNull Context context, List<Swipe_Tab_entitiesModel> resource) {
        super(context, 0, resource);
        mContext = context;
    }

    public View getView(int position, View convertView, @NotNull ViewGroup parent) {

        convertView= LayoutInflater.from(mContext).inflate(R.layout.item_three_category,null);
        TextView tv_category;
        tv_category = (TextView) convertView.findViewById(R.id.tv_category);

        tv_category.setText(getItem(position).getname());
        return convertView;
    }
}
