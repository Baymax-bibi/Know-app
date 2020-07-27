package com.refknowledgebase.refknowledgebase.search;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.refknowledgebase.refknowledgebase.R;
import com.refknowledgebase.refknowledgebase.alphabetindex.RecyclerViewAdapter;
import com.refknowledgebase.refknowledgebase.model.Directory_List_entitiesModel;
import com.refknowledgebase.refknowledgebase.model.Service_Category_Model;
import com.refknowledgebase.refknowledgebase.myinterface.DirectListClickListner;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Search_direcotry_adapter  extends RecyclerView.Adapter<Search_direcotry_adapter.ViewHolder>{
    private List<Directory_List_entitiesModel> mDataArray;
    private List<Service_Category_Model> mServiceArray;
    private DirectListClickListner directListClickListner;

    public Search_direcotry_adapter(Context context, DirectListClickListner _directListClickListner) {
        mDataArray = new ArrayList<>();
        mServiceArray = new ArrayList<>();

        directListClickListner = _directListClickListner;
    }

    @Override
    public int getItemCount() {
        return mDataArray.size();
    }

    @NotNull
    @Override
    public Search_direcotry_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_search_directory, parent, false);
        return new Search_direcotry_adapter.ViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final Search_direcotry_adapter.ViewHolder holder, final int position) {
        if(mDataArray.get(position).getacronym() != null){
            holder.mTextView.setText(""+mDataArray.get(position).getname() + " ("+mDataArray.get(position).getacronym()+")");
        }else {
            holder.mTextView.setText(""+mDataArray.get(position).getname());
        }

        holder.tv_servicename.setText(""+mServiceArray.get(position).getName());
        String Country_list = "";
        for (int country_i = 0; country_i < mDataArray.get(position).getcountries().length; country_i ++){
            Country_list += " " + mDataArray.get(position).getcountries()[country_i];
        }
        holder.tv_country.setText("Country: " + Country_list);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                directListClickListner.recyclerViewListClicked(v, position);
            }
        });
    }

    public void add(Directory_List_entitiesModel r){
        mDataArray.add(r);
        notifyItemInserted(mDataArray.size() - 1);
    }

    public void addAll(List<Directory_List_entitiesModel> moveResults) {
        for (Directory_List_entitiesModel result : moveResults) {
            add(result);
        }
    }

    public Directory_List_entitiesModel getItem(int position) {
        return mDataArray.get(position);
    }

    public void clear() {
        while (getItemCount() > 0){
            remove(getItem(0));
        }
    }

    public void remove(Directory_List_entitiesModel r) {
        int position = mDataArray.indexOf(r);
        if (position > -1) {
            mDataArray.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void addServiceAll(List<Service_Category_Model> serviceCategoryModelList) {
        for (Service_Category_Model result: serviceCategoryModelList){
            addService(result);
        }
    }

    private void addService(Service_Category_Model serviceCategoryModelList) {
        mServiceArray.add(serviceCategoryModelList);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTextView;
        TextView tv_servicename, tv_country;

        ViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.tv_search_directory_distance);
            tv_servicename = itemView.findViewById(R.id.tv_search_directory_association);
            tv_country = itemView.findViewById(R.id.tv_search_directory_address);

        }
    }
}