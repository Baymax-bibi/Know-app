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
//        for (int i = 0; i < home_content_engitiesModelList.size(); i ++){
//            holder.tv_title.setText(home_content_engitiesModelList.get(i).getQuestion());
//            holder.tv_content.setText(home_content_engitiesModelList.get(i).getShort_answer());
//        }
//        Log.e("Count", String.valueOf(home_content_engitiesModelList.size()));
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
//                saved_faq_id = homeContentEngitiesModel.getid();
//                Log.e("saved_faq_id", String.valueOf(saved_faq_id));
//                deleteSavedFaq();
                mListener.Home_Content_ClickListner(v, position);
            }
        });
    }

//    private void deleteSavedFaq() {
//        final RequestQueue queue = Volley.newRequestQueue(mContext);
//
//        StringRequest sr = new StringRequest(Request.Method.DELETE, Constant.URL+Constant.API_SAVED_FAQ + "/" + saved_faq_id, new Response.Listener<String>() {
//            @SuppressLint("LongLogTag")
//            @Override
//            public void onResponse(String response) {
//                Methods.closeProgress();
////                Gson gson = new Gson();
////                FAQ_SavedModel faq_savedModel  = gson.fromJson(response, FAQ_SavedModel.class);
////                saved_faq_id = faq_savedModel.getId();
////                img_saved.setImageDrawable(getResources().getDrawable(R.drawable.un_saved));
//                Toast.makeText(mContext, "Successfully Deleted", Toast.LENGTH_SHORT).show();
//                notifyDataSetChanged();
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Methods.closeProgress();
//                Log.e("Service category","Service category failed" + error.toString());
//                Toast.makeText(mContext, "Some error Occured", Toast.LENGTH_SHORT).show();
//            }
//        }){
//            @Override
//            protected Map<String,String> getParams(){
//                Map<String,String> params = new HashMap<String, String>();
//                params.put("name", "Mylist_FAQ");
//                params.put("faqs[0]", String.valueOf(mBuffer.SELECTED_CONTENT_id));
//                return params;
//            }
//
//            @Override
//            public Map<String, String> getHeaders() {
//                Map<String,String> params = new HashMap<String, String>();
//                params.put("Content-Type","application/x-www-form-urlencoded");
//                params.put("Authorization", mBuffer.token_type + " " + mBuffer.oAuth_token);
//                return params;
//            }
//        };
//        queue.add(sr);
//    }

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
