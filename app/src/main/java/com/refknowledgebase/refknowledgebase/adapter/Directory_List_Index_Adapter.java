package com.refknowledgebase.refknowledgebase.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.refknowledgebase.refknowledgebase.R;
import com.refknowledgebase.refknowledgebase.model.MyListData;
import com.refknowledgebase.refknowledgebase.myinterface.RecyclerViewClickListener;

public class Directory_List_Index_Adapter extends RecyclerView.Adapter<Directory_List_Index_Adapter.ViewHolder> {

    private Context mContext;
    private int selectedItem;
    private RecyclerViewClickListener myListener;
    private MyListData[] listdata;

    // RecyclerView recyclerView;
    public Directory_List_Index_Adapter(Context context, RecyclerViewClickListener recyclerViewClickListener, MyListData[] listdata) {
        this.listdata = listdata;
        mContext = context;
        myListener = recyclerViewClickListener;
        selectedItem = 0;
    }

    @NonNull
    @Override
    public Directory_List_Index_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_index, parent, false);
        return new Directory_List_Index_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Directory_List_Index_Adapter.ViewHolder holder, final int position) {
        holder.tv_index.setText(listdata[position].getindex());
        holder.tv_index.setTextColor(mContext.getResources().getColor(R.color.txt_index_gray));
        holder.view_outline.setVisibility(View.GONE);

        if (selectedItem == position){
            holder.view_outline.setVisibility(View.VISIBLE);
            holder.tv_index.setTextColor(mContext.getResources().getColor(R.color.light_nav));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int previousItem = selectedItem;
                selectedItem = position;
                notifyItemChanged(previousItem);
                notifyItemChanged(position);

                myListener.recyclerViewListClicked(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listdata.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_index;
        View view_outline;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_index = itemView.findViewById(R.id.tv_index);
            this.view_outline = itemView.findViewById(R.id.view_outline);
        }
    }
}
