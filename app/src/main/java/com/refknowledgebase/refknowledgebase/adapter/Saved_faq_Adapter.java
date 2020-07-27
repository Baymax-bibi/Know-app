package com.refknowledgebase.refknowledgebase.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.refknowledgebase.refknowledgebase.R;
import com.refknowledgebase.refknowledgebase.buffer.mBuffer;
import com.refknowledgebase.refknowledgebase.model.Home_Content_engitiesModel;
import com.refknowledgebase.refknowledgebase.model.Saved_entitiesModel;
import com.refknowledgebase.refknowledgebase.model.Saved_faq_Model;
import com.refknowledgebase.refknowledgebase.myinterface.HomeContentClickListner;
import com.refknowledgebase.refknowledgebase.utils.Constant;
import com.refknowledgebase.refknowledgebase.utils.Methods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Saved_faq_Adapter extends RecyclerView.Adapter<Saved_faq_Adapter.ViewHolder> {
    Context mContext;
    private List<Saved_entitiesModel> savedFaqModelList;
    private List<Home_Content_engitiesModel> home_content_engitiesModelList;
    int saved_faq_id;

    private HomeContentClickListner mListener;
    public Saved_faq_Adapter(Context _context, HomeContentClickListner _mListner){
        mContext = _context;
        savedFaqModelList = new ArrayList<>();
        home_content_engitiesModelList = new ArrayList<>();
        mListener = _mListner;
    }

    @NonNull
    @Override
    public Saved_faq_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_saved_faq, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Saved_faq_Adapter.ViewHolder holder, final int position) {
        final Home_Content_engitiesModel homeContentEngitiesModel = home_content_engitiesModelList.get(position);

        holder.tv_title.setText(homeContentEngitiesModel.getQuestion());
        holder.tv_content.setText(homeContentEngitiesModel.getShort_answer());
        holder.img_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.tv_content.setText(Html.fromHtml(homeContentEngitiesModel.getAnswer()));
            }
        });

        holder.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.Home_Content_ClickListner(v, position);
            }
        });
    }

    public void remove(Home_Content_engitiesModel r) {
        int position = home_content_engitiesModelList.indexOf(r);
        if (position > -1) {
            home_content_engitiesModelList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        while (getItemCount() > 0){
            remove(getItem(0));
        }
    }

    public Home_Content_engitiesModel getItem(int position) {
        return home_content_engitiesModelList.get(position);
    }

    @Override
    public int getItemCount() {
        return home_content_engitiesModelList.size();
    }

    public void add(Saved_entitiesModel r){
        savedFaqModelList.add(r);
        notifyItemInserted(savedFaqModelList.size() - 1);
    }
    public void addAll(List<Saved_entitiesModel> moveResults){
        for (Saved_entitiesModel result : moveResults){
            add(result);
        }
    }

    public void addAll_faq(List<Home_Content_engitiesModel> homeContentModelList) {
        for (Home_Content_engitiesModel result : homeContentModelList){
            add_faq(result);
        }
    }

    private void add_faq(Home_Content_engitiesModel result) {
        home_content_engitiesModelList.add(result);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
        TextView tv_title, tv_content, tv_category;
        public RelativeLayout item_rl_assistance;
        ImageView one_sysmbol, img_more, img_delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            this.itemView.setOnClickListener(this);
            this.tv_content = (TextView) itemView.findViewById(R.id.tv_content);
            this.tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            this.tv_category = (TextView) itemView.findViewById(R.id.tv_category);
            this.one_sysmbol = (ImageView) itemView.findViewById(R.id.one_sysmbol);
            this.img_more = itemView.findViewById(R.id.img_more);
            this.img_delete = itemView.findViewById(R.id.img_delete);
            item_rl_assistance = (RelativeLayout)itemView.findViewById(R.id.item_rl_assistance);
        }

//        @Override
//        public void onClick(View v) {
//            mListener.Home_Content_ClickListner(v, this.getPosition());
//        }
    }
}
