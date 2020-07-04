package com.refknowledgebase.refknowledgebase.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.refknowledgebase.refknowledgebase.R;

public class Search_Faq_adapter extends RecyclerView.Adapter<Search_Faq_adapter.ViewHolder>{
    private Search_Faq_model[] listdata;

    // RecyclerView recyclerView;
    public Search_Faq_adapter(Search_Faq_model[] listdata) {
        this.listdata = listdata;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_item_search_faq, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Search_Faq_model assistancemodel = listdata[position];
        holder.tv_search_faq_title.setText(listdata[position].getTitle());
        holder.tv_search_faq_content.setText(listdata[position].getDescription());
        holder.img_search_faq_1.setImageResource(listdata[position].getImg_1());
        holder.img_search_faq_2.setImageResource(listdata[position].getImg_2());

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
        public ImageView img_search_faq_2, img_search_faq_1;
        public TextView tv_search_faq_title, tv_search_faq_content;
        public RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.img_search_faq_1 = (ImageView) itemView.findViewById(R.id.img_search_faq_1);
            this.img_search_faq_2 = (ImageView) itemView.findViewById(R.id.img_search_faq_2);
            this.tv_search_faq_title = (TextView) itemView.findViewById(R.id.tv_search_faq_title);
            this.tv_search_faq_content = (TextView) itemView.findViewById(R.id.tv_search_faq_content);
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relativeLayout);
        }
    }
}