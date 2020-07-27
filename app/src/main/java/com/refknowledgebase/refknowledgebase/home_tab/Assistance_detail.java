package com.refknowledgebase.refknowledgebase.home_tab;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.makeramen.roundedimageview.RoundedImageView;
import com.refknowledgebase.refknowledgebase.DashboardActivity;
import com.refknowledgebase.refknowledgebase.R;
import com.refknowledgebase.refknowledgebase.adapter.Assistance_detail_Adapter;
import com.refknowledgebase.refknowledgebase.adapter.Related_Contact_Adapter;
import com.refknowledgebase.refknowledgebase.buffer.mBuffer;
import com.refknowledgebase.refknowledgebase.model.Contact_form_entities_Model;
import com.refknowledgebase.refknowledgebase.model.Directory_List_entitiesModel;
import com.refknowledgebase.refknowledgebase.model.FAQ_SavedModel;
import com.refknowledgebase.refknowledgebase.model.Hashtags_entities_Model;
import com.refknowledgebase.refknowledgebase.model.Home_Content_DetailModel;
import com.refknowledgebase.refknowledgebase.model.oAuth_Model;
import com.refknowledgebase.refknowledgebase.utils.Constant;
import com.refknowledgebase.refknowledgebase.utils.Methods;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.refknowledgebase.refknowledgebase.R.color.light_nav;

public class Assistance_detail extends Fragment implements View.OnClickListener{

    private static SpannableString spannableString;
    String question, answer;
    TextView tv_where, tv_content, tv_hashtag, tv_relatedContacts;
    String [] hashtags;
    RoundedImageView img_home_assistance_detail_top;
    List<Directory_List_entitiesModel> directory_list_entitiesModels_list;
    List<Hashtags_entities_Model> hashtagsEntitiesModels_list;
    Assistance_detail_Adapter assistanceDetailAdapter;
    Home_Content_DetailModel homeContentDetailModel;
    GridView related_contact_grid;
    ImageView img_saved, img_share;
    boolean saved_flag = false;
    int saved_faq_id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_home_assistance_detail, container, false);

        tv_where = root.findViewById(R.id.tv_where);
        tv_content = root.findViewById(R.id.tv_content);
        tv_hashtag = root.findViewById(R.id.tv_hashtag);
        img_home_assistance_detail_top = root.findViewById(R.id.img_home_assistance_detail_top);
        assistanceDetailAdapter = new Assistance_detail_Adapter();
        related_contact_grid = root.findViewById(R.id.related_contact_grid);
        tv_relatedContacts = root.findViewById(R.id.tv_relatedContacts);
        img_saved = root.findViewById(R.id.img_saved);
        img_share = root.findViewById(R.id.img_share);

        img_home_assistance_detail_top.requestLayout();

        init();
        getScreenSize();
        return root;
    }

    private void getScreenSize() {
        int screenSize = getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK;

        String toastMsg;
        switch(screenSize) {
            case Configuration.SCREENLAYOUT_SIZE_LARGE:
                toastMsg = "Large screen";
                img_home_assistance_detail_top.getLayoutParams().height = 400;
                break;
            case Configuration.SCREENLAYOUT_SIZE_NORMAL:
                img_home_assistance_detail_top.getLayoutParams().height = 500;
                toastMsg = "Normal screen";
                break;
            case Configuration.SCREENLAYOUT_SIZE_SMALL:
                img_home_assistance_detail_top.getLayoutParams().height = 700;
                toastMsg = "Small screen";
                break;
            default:
                toastMsg = "Screen size is neither large, normal or small";
        }
//        Toast.makeText(getContext(), toastMsg, Toast.LENGTH_LONG).show();
    }

    private void init() {
        hashtags = mBuffer.SELECTED_CONTENT_hashtags;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        spannableString = new SpannableString(mBuffer.service_category_name);
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(light_nav)), 0, spannableString.length(), 0);
        DashboardActivity.set_title(false,spannableString);

        DashboardActivity.set_hamburger();
        Methods.showProgress(getContext());

        img_saved.setOnClickListener(this);
        img_share.setOnClickListener(this);
        loadAssistanceDetail();
    }

    private void loadAssistanceDetail() {
        final RequestQueue queue = Volley.newRequestQueue(getContext());

        StringRequest sr = new StringRequest(Request.Method.GET, Constant.URL+Constant.API_SERVICE_CONTENT_DETAIL + "/" + mBuffer.SELECTED_CONTENT_id + "?with[]=hashtags&with[]=countries&with[]=contacts&with[]=socialMediaLinks&with[]=directories.contactForms.directoryCountry&with[]=directories.contactForms", new Response.Listener<String>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(String response) {
                Methods.closeProgress();
                Gson gson = new Gson();
                homeContentDetailModel = gson.fromJson(response, Home_Content_DetailModel.class);//draw data
                question = homeContentDetailModel.getQuestion();
                answer = homeContentDetailModel.getAnswer();
                tv_where.setText(question);
                tv_content.setText(Html.fromHtml(answer));
                directory_list_entitiesModels_list = homeContentDetailModel.getDirectories();
                if (directory_list_entitiesModels_list != null) {
                    assistanceDetailAdapter.addAll(directory_list_entitiesModels_list);
                    if (directory_list_entitiesModels_list.get(0).getimage() != null){
                        Uri img_home_assistance_detail_top_uri = Uri.parse(directory_list_entitiesModels_list.get(0).getimage());
                        String img_url = String.valueOf(img_home_assistance_detail_top_uri).replace("http", "https");
                        Picasso.with(getContext()).load(img_url).fit().into(img_home_assistance_detail_top);
                    }else {
                        img_home_assistance_detail_top.setVisibility(View.GONE);
                    }
                }else {
                    img_home_assistance_detail_top.setVisibility(View.GONE);
                }
//               related contact
                if (directory_list_entitiesModels_list != null){
                    assistanceDetailAdapter.addAll(directory_list_entitiesModels_list);
                        for (int i = 0; i < directory_list_entitiesModels_list.size(); i ++){
                            List<Contact_form_entities_Model> contact_form_entities_modelList = new ArrayList<>(directory_list_entitiesModels_list.get(i).getcontact_forms());
                            related_contact_grid.setAdapter(new Related_Contact_Adapter(getActivity(), contact_form_entities_modelList));
                        }

                }else {
                    tv_relatedContacts.setVisibility(View.GONE);
                }

                hashtagsEntitiesModels_list = homeContentDetailModel.getHashtags();
                if (hashtagsEntitiesModels_list != null){
                    assistanceDetailAdapter.addAll_Hashtags_entities_Model(hashtagsEntitiesModels_list);
                    String hashtag_st = "";
                    for (int i = 0; i < hashtagsEntitiesModels_list.size(); i++){
                        hashtag_st += "#" + hashtagsEntitiesModels_list.get(i).getHashtag() + "\t";
                    }
                    tv_hashtag.setText(hashtag_st);
                }else {
                    tv_hashtag.setText("#No Hashtag");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Methods.closeProgress();
                Log.e("Service category","Service category failed" + error.toString());
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                return params;
            }

            @Override
            public Map<String, String> getHeaders() {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                params.put("Authorization", mBuffer.token_type + " " + mBuffer.oAuth_token);
                return params;
            }
        };
        queue.add(sr);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_saved:
                if (!saved_flag){
                    Methods.showProgress(getContext());
                    saveFaq();
                }else {
                    deleteFaq();
                    Methods.showProgress(getContext());
                }
                break;
            case R.id.img_share:
                Intent shareIntent =   new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT,"Insert Subject here");
//                String app_url = "";
//                if (directory_list_entitiesModels_list.get(0).getimage() != null){
//                    app_url = directory_list_entitiesModels_list.get(0).getimage() + "\n\n";
//                }
                String app_tile = homeContentDetailModel.getQuestion() + "\n\n";
                String app_content = String.valueOf(Html.fromHtml(homeContentDetailModel.getAnswer()));
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT,app_tile + app_content);
                getContext().startActivity(Intent.createChooser(shareIntent, "Share via"));
                break;
        }
    }

    private void deleteFaq() {
        final RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest sr = new StringRequest(Request.Method.DELETE, Constant.URL+Constant.API_SAVED_FAQ + "/" + saved_faq_id, new Response.Listener<String>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(String response) {
                Methods.closeProgress();
                img_saved.setImageDrawable(getResources().getDrawable(R.drawable.un_saved));
                saved_flag = !saved_flag;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Methods.closeProgress();
                Toast.makeText(getContext(), "Some error Occured", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("name", "Mylist_FAQ");
                params.put("faqs[0]", String.valueOf(mBuffer.SELECTED_CONTENT_id));
                return params;
            }

            @Override
            public Map<String, String> getHeaders() {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                params.put("Authorization", mBuffer.token_type + " " + mBuffer.oAuth_token);
                return params;
            }
        };
        queue.add(sr);
    }

    private void saveFaq() {
        final RequestQueue queue = Volley.newRequestQueue(getContext());

        StringRequest sr = new StringRequest(Request.Method.POST, Constant.URL+Constant.API_LIST_FAQ, new Response.Listener<String>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(String response) {
                Methods.closeProgress();
                Gson gson = new Gson();
                FAQ_SavedModel faq_savedModel  = gson.fromJson(response, FAQ_SavedModel.class);
                saved_faq_id = faq_savedModel.getId();
                img_saved.setImageDrawable(getResources().getDrawable(R.drawable.detail_saved));
                saved_flag = !saved_flag;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Methods.closeProgress();
                Log.e("Service category","Service category failed" + error.toString());
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("name", "Mylist_FAQ");
                params.put("faqs[0]", String.valueOf(mBuffer.SELECTED_CONTENT_id));
                return params;
            }

            @Override
            public Map<String, String> getHeaders() {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                params.put("Authorization", mBuffer.token_type + " " + mBuffer.oAuth_token);
                return params;
            }
        };
        queue.add(sr);
    }
}
