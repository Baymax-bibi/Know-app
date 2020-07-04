package com.refknowledgebase.refknowledgebase.search;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.refknowledgebase.refknowledgebase.R;
import com.refknowledgebase.refknowledgebase.adapter.Home_Content_Adapter;
import com.refknowledgebase.refknowledgebase.buffer.mBuffer;
import com.refknowledgebase.refknowledgebase.home_tab.Assistance_detail;
import com.refknowledgebase.refknowledgebase.model.Home_Content_Model;
import com.refknowledgebase.refknowledgebase.model.Home_Content_engitiesModel;
import com.refknowledgebase.refknowledgebase.model.Swipe_Tab_Model;
import com.refknowledgebase.refknowledgebase.model.Swipe_Tab_entitiesModel;
import com.refknowledgebase.refknowledgebase.myinterface.HomeContentClickListner;
import com.refknowledgebase.refknowledgebase.utils.Constant;
import com.refknowledgebase.refknowledgebase.utils.Methods;
import com.refknowledgebase.refknowledgebase.utils.PaginationScrollListener;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Search_Faq_Fragment extends Fragment {
    RecyclerView rv_faq_content;
    private List<Home_Content_engitiesModel> results_content;
    private Home_Content_Model homeContentModel;
    private Home_Content_Adapter homeContentAdapter;
    private boolean isLoading = false,  isLastPage = false, isLoading_content = false ;
    private static final int PAGE_START = 1;
    private int TOTAL_PAGES,  currentPage = PAGE_START, PER_PAGE = 5, LAST_PAGE, TOTAL_PAGES_content, PER_PAGE_content = 15, LAST_PAGE_content, service_category_ids = 107, currentPage_content;
    private LinearLayoutManager layoutManager_tab, layoutManager_content;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_search_faq, container, false);

        rv_faq_content = root.findViewById(R.id.rv_faq_content);
        HomeContentClickListner homeContentClickListner = new HomeContentClickListner() {
            @Override
            public void Home_Content_ClickListner(View v, int position) {
                mBuffer.SELECTED_CONTENT_id = homeContentAdapter.getItem(position).getid();
//            mBuffer.SELECTED_CONTENT_question = homeContentAdapter.getItem(position).getQuestion();
//            mBuffer.SELECTED_CONTENT_question_normalize = homeContentAdapter.getItem(position).getQuestion_normalize();
//            mBuffer.SELECTED_CONTENT_answer = homeContentAdapter.getItem(position).getAnswer();
//            mBuffer.SELECTED_CONTENT_status = homeContentAdapter.getItem(position).getStatus();
//            mBuffer.SELECTED_CONTENT_created_by = homeContentAdapter.getItem(position).getCreated_by();
//            mBuffer.SELECTED_CONTENT_visible = homeContentAdapter.getItem(position).getVisible();
//            mBuffer.SELECTED_CONTENT_service_category_ids = homeContentAdapter.getItem(position).getService_category_ids();
//            mBuffer.SELECTED_CONTENT_country_ids = homeContentAdapter.getItem(position).getCountry_ids();
//            mBuffer.SELECTED_CONTENT_nationality_ids = homeContentAdapter.getItem(position).getNationality_ids();
//            mBuffer.SELECTED_CONTENT_hashtags = homeContentAdapter.getItem(position).getHashtags();

//                fragment = new Assistance_detail();
//                loadFragment(fragment);
            }
        };
        homeContentAdapter = new Home_Content_Adapter(getContext(), homeContentClickListner);
        rv_faq_content.setAdapter(homeContentAdapter);
        layoutManager_content = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv_faq_content.setLayoutManager(layoutManager_content);
        rv_faq_content.setItemAnimator(new DefaultItemAnimator());
        rv_faq_content.addOnScrollListener(new PaginationScrollListener(layoutManager_content) {
            @Override
            protected void loadMoreItems() {
                currentPage_content += 1;
                isLoading_content = true;
                Methods.showProgress(getContext());
                loading_content();
            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES_content;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading_content;
            }
        });

        loading_content();
        Methods.showProgress(getContext());
        return root;
    }
    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        recyclerView_search_faq.setHasFixedSize(true);
//        layoutManager = new LinearLayoutManager(getContext());
//        recyclerView_search_faq.setLayoutManager(layoutManager);
//        recyclerView_search_faq.setItemAnimator(new DefaultItemAnimator());
//
//        Search_Faq_adapter adapter = new Search_Faq_adapter(myListData);
//        recyclerView_search_faq.setAdapter(adapter);
        loading_content();
    }

    private void loading_content() {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = "http://api.project-info.gq/api/faq/es-search?keywords="+mBuffer.Search_key+"&lang=English";
        Log.e("search_key", url);

        StringRequest sr = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                Log.e("response_content", response);
                Methods.closeProgress();
                Gson gson = new Gson();
                homeContentModel = gson.fromJson(response, Home_Content_Model.class);
                isLoading = false;
                results_content = homeContentModel.getEntities();
//                Collections.sort(results, new Comparator<Swipe_Tab_entitiesModel>() {
//                    @Override
//                    public int compare(Swipe_Tab_entitiesModel o1, Swipe_Tab_entitiesModel o2) {
//                        return o1.getname().compareTo(o2.getname());
//                    }
//                });
                homeContentAdapter.addAll(results_content);
                TOTAL_PAGES = homeContentModel.getLast_page();
                LAST_PAGE = homeContentModel.getLast_page();

                if (currentPage_content >= LAST_PAGE_content){
                    isLoading_content = true;
                }else {
                    isLoading_content = false;
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Methods.closeProgress();
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
}