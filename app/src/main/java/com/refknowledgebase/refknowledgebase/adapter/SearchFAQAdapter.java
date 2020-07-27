package com.refknowledgebase.refknowledgebase.adapter;

import android.content.Context;
import android.text.Html;
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
import com.refknowledgebase.refknowledgebase.myinterface.HomeContentClickListner;
import com.refknowledgebase.refknowledgebase.utils.Constant;

import java.util.ArrayList;
import java.util.List;

public class SearchFAQAdapter extends RecyclerView.Adapter<SearchFAQAdapter.ViewHolder> {

    Context mContext;
    private List<Home_Content_engitiesModel> content_list;
    String Category_name;


    private HomeContentClickListner mListener;

    public SearchFAQAdapter(Context _context, HomeContentClickListner _mListner){
        mContext = _context;
        content_list = new ArrayList<>();
        mListener = _mListner;
    }
    @NonNull
    @Override
    public SearchFAQAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_home_assistance_new, parent, false);
        return new SearchFAQAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchFAQAdapter.ViewHolder holder, int position) {

        Home_Content_BaseModel homeContentBaseModel = content_list.get(position);

        holder.tv_title.setText(homeContentBaseModel.getQuestion());
        holder.tv_content.setText(Html.fromHtml(homeContentBaseModel.getAnswer()));



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
        public RelativeLayout item_rl_assistance;
        ImageView one_sysmbol;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView.setOnClickListener(this);
            this.tv_content = (TextView) itemView.findViewById(R.id.tv_content);
            this.tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            this.tv_category = (TextView) itemView.findViewById(R.id.tv_category);
            this.one_sysmbol = (ImageView) itemView.findViewById(R.id.one_sysmbol);
            item_rl_assistance = (RelativeLayout)itemView.findViewById(R.id.item_rl_assistance);
        }

        @Override
        public void onClick(View v) {
            mListener.Home_Content_ClickListner(v, this.getPosition());
        }
    }

}
