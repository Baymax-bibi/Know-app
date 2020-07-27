package com.refknowledgebase.refknowledgebase.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
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
import com.refknowledgebase.refknowledgebase.model.Home_Content_BaseModel;
import com.refknowledgebase.refknowledgebase.model.Home_Content_engitiesModel;
import com.refknowledgebase.refknowledgebase.model.Swipe_Tab_entitiesModel;
import com.refknowledgebase.refknowledgebase.myinterface.HomeContentClickListner;
import com.refknowledgebase.refknowledgebase.utils.Constant;

import java.util.ArrayList;
import java.util.List;

public class Home_Content_Adapter extends RecyclerView.Adapter<Home_Content_Adapter.ViewHolder> {

    Context mContext;
    private List<Home_Content_engitiesModel> content_list;
    String Category_name;


    private HomeContentClickListner mListener;

    public Home_Content_Adapter(Context _context, HomeContentClickListner _mListner){
        mContext = _context;
        content_list = new ArrayList<>();
        mListener = _mListner;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_home_assistance_new, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Home_Content_BaseModel homeContentBaseModel = content_list.get(position);

        holder.tv_title.setText(homeContentBaseModel.getQuestion());
        holder.tv_content.setText(Html.fromHtml(homeContentBaseModel.getAnswer()));

//        switch (Constant.SELECTED_CATEGORY_ID){
        switch (mBuffer.service_category_ids){
            case 9:
                holder.one_sysmbol.setImageDrawable(mContext.getDrawable(R.drawable.education));
                break;
            case 18:
                holder.one_sysmbol.setImageDrawable(mContext.getDrawable(R.drawable.health_care));
                break;
            case 27:
                holder.one_sysmbol.setImageDrawable(mContext.getDrawable(R.drawable.protection));
                break;
            case 42:
                holder.one_sysmbol.setImageDrawable(mContext.getDrawable(R.drawable.refugee_status_determination));
                break;
            case 81:
                holder.one_sysmbol.setImageDrawable(mContext.getDrawable(R.drawable.resettlement));
                break;
            case 84:
                holder.one_sysmbol.setImageDrawable(mContext.getDrawable(R.drawable.livelihoods));
                break;
            case 96:
                holder.one_sysmbol.setImageDrawable(mContext.getDrawable(R.drawable.how_to_contact_unhcr));
                break;
            case 107:
                holder.one_sysmbol.setImageDrawable(mContext.getDrawable(R.drawable.assistance));
                break;
            case 119:
                holder.one_sysmbol.setImageDrawable(mContext.getDrawable(R.drawable.residency));
                break;
            case 129:
                holder.one_sysmbol.setImageDrawable(mContext.getDrawable(R.drawable.legal_aid));
                break;
            case 131:
                holder.one_sysmbol.setImageDrawable(mContext.getDrawable(R.drawable.child_protection));
                break;
            case 135:
                holder.one_sysmbol.setImageDrawable(mContext.getDrawable(R.drawable.sgbv));
                break;
            case 136:
                holder.one_sysmbol.setImageDrawable(mContext.getDrawable(R.drawable.community_based_protection));
                break;
            case 137:
                holder.one_sysmbol.setImageDrawable(mContext.getDrawable(R.drawable.registration));
                break;
            case 138:
                holder.one_sysmbol.setImageDrawable(mContext.getDrawable(R.drawable.reporting_fraud_and_corruption));
                break;
            case 165:
                holder.one_sysmbol.setImageDrawable(mContext.getDrawable(R.drawable.irregular_movements));
                break;
            case 166:
                holder.one_sysmbol.setImageDrawable(mContext.getDrawable(R.drawable.telling_the_real_story));
                break;
            case 167:
                holder.one_sysmbol.setImageDrawable(mContext.getDrawable(R.drawable.covid_19));
                break;
        }

//        holder.tv_category.setText(Constant.SELECTED_CATEGORY);
        holder.tv_category.setText(mBuffer.service_category_name);
        if (mBuffer.is_search){
            switch (homeContentBaseModel.getService_category_ids()[0]){
                case 9:
                    holder.one_sysmbol.setImageDrawable(mContext.getDrawable(R.drawable.education));
                    Category_name = "Education";
                    break;
                case 18:
                    holder.one_sysmbol.setImageDrawable(mContext.getDrawable(R.drawable.health_care));
                    Category_name = "Health Care";
                    break;
                case 27:
                    holder.one_sysmbol.setImageDrawable(mContext.getDrawable(R.drawable.protection));
                    Category_name = "Protection";
                    break;
                case 42:
                    holder.one_sysmbol.setImageDrawable(mContext.getDrawable(R.drawable.refugee_status_determination));
                    Category_name = "Refugee Status Determination";
                    break;
                case 81:
                    holder.one_sysmbol.setImageDrawable(mContext.getDrawable(R.drawable.resettlement));
                    Category_name = "Resettlement";
                    break;
                case 84:
                    holder.one_sysmbol.setImageDrawable(mContext.getDrawable(R.drawable.livelihoods));
                    Category_name = "Livelihoods";
                    break;
                case 96:
                    holder.one_sysmbol.setImageDrawable(mContext.getDrawable(R.drawable.how_to_contact_unhcr));
                    Category_name = "How to contact UNHCR";
                    break;
                case 107:
                    holder.one_sysmbol.setImageDrawable(mContext.getDrawable(R.drawable.assistance));
                    Category_name = "Assistance";
                    break;
                case 119:
                    holder.one_sysmbol.setImageDrawable(mContext.getDrawable(R.drawable.residency));
                    Category_name = "Residency";
                    break;
                case 129:
                    holder.one_sysmbol.setImageDrawable(mContext.getDrawable(R.drawable.legal_aid));
                    Category_name = "Legal Aid";
                    break;
                case 131:
                    holder.one_sysmbol.setImageDrawable(mContext.getDrawable(R.drawable.child_protection));
                    Category_name = "Child Protection";
                    break;
                case 135:
                    holder.one_sysmbol.setImageDrawable(mContext.getDrawable(R.drawable.sgbv));
                    Category_name = "SGBV";
                    break;
                case 136:
                    holder.one_sysmbol.setImageDrawable(mContext.getDrawable(R.drawable.community_based_protection));
                    Category_name = "Community-based Protection";
                    break;
                case 137:
                    holder.one_sysmbol.setImageDrawable(mContext.getDrawable(R.drawable.registration));
                    Category_name = "Registration";
                    break;
                case 138:
                    holder.one_sysmbol.setImageDrawable(mContext.getDrawable(R.drawable.reporting_fraud_and_corruption));
                    Category_name = "Reporting Fraud and Corruption";
                    break;
                case 165:
                    holder.one_sysmbol.setImageDrawable(mContext.getDrawable(R.drawable.irregular_movements));
                    Category_name = "Irregular Movements";
                    break;
                case 166:
                    holder.one_sysmbol.setImageDrawable(mContext.getDrawable(R.drawable.telling_the_real_story));
                    Category_name = "Telling the Real Stroy";
                    break;
                case 167:
                    holder.one_sysmbol.setImageDrawable(mContext.getDrawable(R.drawable.covid_19));
                    Category_name = "COVID-19";
                    break;
            }
            holder.tv_category.setText(Category_name);
        }
        holder.rl_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent =   new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT,"Insert Subject here");
//                String app_url = " https://play.google.com/store/apps/details?id=my.example.javatpoint";
                String app_tile = homeContentBaseModel.getQuestion() + "\n\n";
                String app_content = String.valueOf(Html.fromHtml(homeContentBaseModel.getAnswer()));
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT,app_tile + app_content);
                mContext.startActivity(Intent.createChooser(shareIntent, "Share via"));
            }
        });

    }

    @Override
    public int getItemCount() {
        return content_list.size();
    }


    public void add(Home_Content_engitiesModel r){
        content_list.add(r);
        notifyItemInserted(content_list.size() - 1);
    }

    public void addAll(List<Home_Content_engitiesModel> moveResults) {
        for (Home_Content_engitiesModel result : moveResults) {
            add(result);
        }
    }
    public void remove(Home_Content_engitiesModel r) {
        int position = content_list.indexOf(r);
        if (position > -1) {
            content_list.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        while (getItemCount() > 0){
            remove(getItem(0));
        }
    }

    public Home_Content_engitiesModel getItem(int position) {
        return content_list.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_title, tv_content, tv_category;
        public RelativeLayout item_rl_assistance, rl_share;
        ImageView one_sysmbol;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView.setOnClickListener(this);
            this.tv_content = (TextView) itemView.findViewById(R.id.tv_content);
            this.tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            this.tv_category = (TextView) itemView.findViewById(R.id.tv_category);
            this.one_sysmbol = (ImageView) itemView.findViewById(R.id.one_sysmbol);
            item_rl_assistance = (RelativeLayout)itemView.findViewById(R.id.item_rl_assistance);
            this.rl_share = (RelativeLayout) itemView.findViewById(R.id.rl_share);
        }

        @Override
        public void onClick(View v) {
            mListener.Home_Content_ClickListner(v, this.getPosition());
        }
    }

}
