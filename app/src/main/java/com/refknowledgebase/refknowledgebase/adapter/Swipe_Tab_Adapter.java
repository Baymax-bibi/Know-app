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
import com.refknowledgebase.refknowledgebase.buffer.mBuffer;
import com.refknowledgebase.refknowledgebase.model.Swipe_Tab_BaseModel;
import com.refknowledgebase.refknowledgebase.model.Swipe_Tab_entitiesModel;
import com.refknowledgebase.refknowledgebase.myinterface.RecyclerViewClickListener;

import java.util.ArrayList;
import java.util.List;

public class Swipe_Tab_Adapter extends RecyclerView.Adapter<Swipe_Tab_Adapter.ViewHolder> {

    private Context mContext;
    private List<Swipe_Tab_entitiesModel> tab_list;
//    private int selectedItem;

    private RecyclerViewClickListener myListener;

    public Swipe_Tab_Adapter(Context _context, RecyclerViewClickListener _mListener){
        mContext = _context;
        tab_list = new ArrayList<>();
        myListener = _mListener;
//        mBuffer.selectedItem = 0;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_swipe_tab, parent, false);
        return new Swipe_Tab_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        Swipe_Tab_BaseModel swipe_tab_baseModel = tab_list.get(position);

        switch (swipe_tab_baseModel.getid()){
            case 9:
                holder.img_tab.setImageDrawable(mContext.getDrawable(R.drawable.education));
                break;
            case 18:
                holder.img_tab.setImageDrawable(mContext.getDrawable(R.drawable.health_care));
                break;
            case 27:
                holder.img_tab.setImageDrawable(mContext.getDrawable(R.drawable.protection));
                break;
            case 42:
                holder.img_tab.setImageDrawable(mContext.getDrawable(R.drawable.refugee_status_determination));
                break;
            case 81:
                holder.img_tab.setImageDrawable(mContext.getDrawable(R.drawable.resettlement));
                break;
            case 84:
                holder.img_tab.setImageDrawable(mContext.getDrawable(R.drawable.livelihoods));
                break;
            case 96:
                holder.img_tab.setImageDrawable(mContext.getDrawable(R.drawable.how_to_contact_unhcr));
                break;
            case 107:
                holder.img_tab.setImageDrawable(mContext.getDrawable(R.drawable.assistance));
                break;
            case 119:
                holder.img_tab.setImageDrawable(mContext.getDrawable(R.drawable.residency));
                break;
            case 129:
                holder.img_tab.setImageDrawable(mContext.getDrawable(R.drawable.legal_aid));
                break;
            case 131:
                holder.img_tab.setImageDrawable(mContext.getDrawable(R.drawable.child_protection));
                break;
            case 135:
                holder.img_tab.setImageDrawable(mContext.getDrawable(R.drawable.sgbv));
                break;
            case 136:
                holder.img_tab.setImageDrawable(mContext.getDrawable(R.drawable.community_based_protection));
                break;
            case 137:
                holder.img_tab.setImageDrawable(mContext.getDrawable(R.drawable.registration));
                break;
            case 138:
                holder.img_tab.setImageDrawable(mContext.getDrawable(R.drawable.reporting_fraud_and_corruption));
                break;
            case 165:
                holder.img_tab.setImageDrawable(mContext.getDrawable(R.drawable.irregular_movements));
                break;
            case 166:
                holder.img_tab.setImageDrawable(mContext.getDrawable(R.drawable.telling_the_real_story));
                break;
            case 167:
                holder.img_tab.setImageDrawable(mContext.getDrawable(R.drawable.covid_19));
                break;
        }


        final String name = swipe_tab_baseModel.getname();
        holder.tv_title.setText(name);

        holder.img_under.setVisibility(View.INVISIBLE);
        holder.tv_title.setTextColor(mContext.getResources().getColor(R.color.light_nav));
        holder.img_tab.setColorFilter(mContext.getResources().getColor(R.color.light_nav));

        if (mBuffer.selectedItem == position){
            holder.img_under.setVisibility(View.VISIBLE);
            holder.tv_title.setTextColor(mContext.getResources().getColor(R.color.fire_color));
            holder.img_tab.setColorFilter(mContext.getResources().getColor(R.color.fire_color));

        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int previousItem = mBuffer.selectedItem;
                mBuffer.selectedItem = position;
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
        ImageView img_tab, img_under;
        TextView tv_title;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            this.img_tab = (ImageView) itemView.findViewById(R.id.img_tab);
            this.img_under = (ImageView) itemView.findViewById(R.id.img_under);
        }

    }
}
