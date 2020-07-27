package com.refknowledgebase.refknowledgebase.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.refknowledgebase.refknowledgebase.R;
import com.refknowledgebase.refknowledgebase.model.Swipe_Tab_BaseModel;
import com.refknowledgebase.refknowledgebase.model.Swipe_Tab_entitiesModel;
import com.refknowledgebase.refknowledgebase.myinterface.RecyclerViewClickListener;

import java.util.ArrayList;
import java.util.List;

public class LandingThreeAdapter extends RecyclerView.Adapter<LandingThreeAdapter.ViewHolder> {{
}

    private Context mContext;
    private List<Swipe_Tab_entitiesModel> tab_list;
    private int selectedItem;
    private RecyclerViewClickListener myListener;

    public LandingThreeAdapter(Context _context, RecyclerViewClickListener _mListener){
        mContext = _context;
        tab_list = new ArrayList<>();
        myListener = _mListener;
        selectedItem = 0;
    }

    @NonNull
    @Override
    public LandingThreeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_three_service, parent, false);
        return new LandingThreeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LandingThreeAdapter.ViewHolder holder, final int position) {
        Swipe_Tab_BaseModel swipe_tab_baseModel = tab_list.get(position);

        final String name = swipe_tab_baseModel.getname();

            holder.tv_title.setText(name);

        holder.tv_title.setTextColor(mContext.getResources().getColor(R.color.white));
        holder.img_tab.setColorFilter(mContext.getResources().getColor(R.color.white));


        if (selectedItem == position){
                holder.tv_title.setTextColor(mContext.getResources().getColor(R.color.fire_color));
                holder.img_tab.setColorFilter(mContext.getResources().getColor(R.color.fire_color));

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
        return tab_list.size();
    }
    public void add(Swipe_Tab_entitiesModel r){
        tab_list.add(r);
        notifyItemInserted(tab_list.size() - 1);
    }

    public void addAll(List<Swipe_Tab_entitiesModel> moveResults) {
        for (Swipe_Tab_entitiesModel result : moveResults) {
            add(result);
        }
    }
    public void remove(Swipe_Tab_entitiesModel r) {
        int position = tab_list.indexOf(r);
        if (position > -1) {
            tab_list.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        while (getItemCount() > 0){
            remove(getItem(0));
        }
    }

    public Swipe_Tab_entitiesModel getItem(int position) {
        return tab_list.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_tab;
        TextView tv_title;
        RelativeLayout rl_one_tab;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
//            this.itemView.setOnClickListener(this);
            this.rl_one_tab = (RelativeLayout) itemView.findViewById(R.id.rl_one_tab);
            this.rl_one_tab = (RelativeLayout) itemView.findViewById(R.id.rl_one_tab);
//            rl_one_tab.setOnClickListener(this);
            this.tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            this.img_tab = (ImageView) itemView.findViewById(R.id.img_tab);

        }
    }
}
