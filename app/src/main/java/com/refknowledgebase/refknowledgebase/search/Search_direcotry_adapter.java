package com.refknowledgebase.refknowledgebase.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.refknowledgebase.refknowledgebase.R;

public class Search_direcotry_adapter  extends RecyclerView.Adapter<Search_direcotry_adapter.ViewHolder>{
    private Search_Directory_model[] listdata;

    // RecyclerView recyclerView;
    public Search_direcotry_adapter(Search_Directory_model[] listdata) {
        this.listdata = listdata;
    }
    @Override
    public Search_direcotry_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_item_search_directory, parent, false);
        Search_direcotry_adapter.ViewHolder viewHolder = new Search_direcotry_adapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(Search_direcotry_adapter.ViewHolder holder, int position) {
        final Search_Directory_model search_directory_model = listdata[position];
        holder.tv_search_dirctory_distance.setText(listdata[position].getDistance());
        holder.tv_search_directory_association.setText(listdata[position].getAssociation());
        holder.tv_search_directory_address.setText(listdata[position].getAddress());
//        holder.img_search_directory_call.setImageResource(listdata[position].getImg_1());
//        holder.img_search_directory_location.setImageResource(listdata[position].getImg_2());
//        holder.img_search_directory_saved.setImageResource(listdata[position].getImg_2());

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return listdata.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //public ImageView img_search_directory_call, img_search_directory_location, img_search_directory_saved;
        public TextView tv_search_dirctory_distance, tv_search_directory_association, tv_search_directory_address;
        public RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);
//            this.img_search_directory_call = (ImageView) itemView.findViewById(R.id.img_search_directory_call);
//            this.img_search_directory_location = (ImageView) itemView.findViewById(R.id.img_search_directory_location);
//            this.img_search_directory_saved = (ImageView) itemView.findViewById(R.id.img_search_directory_saved);
            this.tv_search_dirctory_distance = (TextView) itemView.findViewById(R.id.tv_search_directory_distance);
            this.tv_search_directory_association = (TextView) itemView.findViewById(R.id.tv_search_directory_association);
            this.tv_search_directory_address = (TextView) itemView.findViewById(R.id.tv_search_directory_address);
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relativeLayout);
        }
    }
}