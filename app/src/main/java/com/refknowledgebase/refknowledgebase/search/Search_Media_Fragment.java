package com.refknowledgebase.refknowledgebase.search;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.refknowledgebase.refknowledgebase.DashboardActivity;
import com.refknowledgebase.refknowledgebase.R;
import com.refknowledgebase.refknowledgebase.adapter.SearchMediaAdapter;
import com.refknowledgebase.refknowledgebase.buffer.mBuffer;
import com.refknowledgebase.refknowledgebase.model.Search_Media_Model;
import com.refknowledgebase.refknowledgebase.model.Search_Media_entities_Model;
import com.refknowledgebase.refknowledgebase.myinterface.HomeContentClickListner;
import com.refknowledgebase.refknowledgebase.myinterface.SearchClickListner;
import com.refknowledgebase.refknowledgebase.utils.Constant;
import com.refknowledgebase.refknowledgebase.utils.Methods;
import com.refknowledgebase.refknowledgebase.utils.PaginationScrollListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class Search_Media_Fragment  extends Fragment implements SearchClickListner {

    RecyclerView rv_search_media;
    LinearLayoutManager layoutManager;
    Search_Media_Model search_media_model;
    SearchMediaAdapter searchMediaAdapter;
    private static final int PAGE_START = 1;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    // limiting to 5 for this tutorial, since total pages in actual API is very large. Feel free to modify.
    private int TOTAL_PAGES;
    private int currentPage = PAGE_START;
    private int PER_PAGE = 5;
    FrameLayout fl_search_media;

    private int LASTPAGE;
    TextView tv_1;
    String filter_C;

    TextView tv_cari;
Context mContext;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_search_media, container, false);
        tv_1 = root.findViewById(R.id.tv_1);
        mContext = getContext();
        return root;
    }
    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tv_cari = view.findViewById(R.id.tv_cari);
        tv_cari.setText(mBuffer.Search_key);

        filter_C = getString("SEARCH_KEY");
        Log.e("SEARCH_KEY", filter_C);
//        getMediaData();

        rv_search_media = view.findViewById(R.id.rv_search_media);
        fl_search_media = view.findViewById(R.id.fl_search_media);

        HomeContentClickListner homeContentClickListner = new HomeContentClickListner() {
            @Override
            public void Home_Content_ClickListner(View v, int position) {
                Toast.makeText(getContext(), position, Toast.LENGTH_SHORT).show();
            }
        };
        searchMediaAdapter = new SearchMediaAdapter(this, homeContentClickListner);
        rv_search_media.setAdapter(searchMediaAdapter);

//        rv_search_media.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rv_search_media.setLayoutManager(layoutManager);
        rv_search_media.setItemAnimator(new DefaultItemAnimator());


        rv_search_media.addOnScrollListener(new PaginationScrollListener(layoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += 1;
                Log.e("media","media load more");

                loadPage(mContext, "a");
//                if (TOTAL_PAGES / 10 < currentPage){
//                    Toast.makeText(getContext(), "End of Scroll", Toast.LENGTH_SHORT).show();
//                }
            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });

        Methods.showProgress(getContext());
        loadPage(mContext, "a");
    }

    private void loadPage(Context mContext, String searchIndex) {
        RequestQueue queue = Volley.newRequestQueue(mContext);

        String url = "http://api.project-info.gq/api/media/es-search?page=1&per_page=5&keywords="+mBuffer.Search_key+"&lang=English";
        Log.e("URL", url);
        StringRequest sr = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Methods.closeProgress();
                Gson gson = new Gson();
                search_media_model = gson.fromJson(response, Search_Media_Model.class);
                List<Search_Media_entities_Model> results = search_media_model.getEntities();
                searchMediaAdapter.addAll(results);

//                TOTAL_PAGES = search_media_model.getTotal();

                tv_1.setText("SHOWING "+search_media_model.getTotal()+" RESULTS FOR ");
                LASTPAGE = search_media_model.getLast_page();

                if (currentPage >= LASTPAGE){
                    isLoading = true;
                }else {
                    isLoading = false;
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Methods.closeProgress();
                Log.e("Country","Country failed" + error.toString());
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

    public String getString(String key) {
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(Constant.PREF, MODE_PRIVATE);
        String  selected =  mSharedPreferences.getString(key, "");
        return selected;
    }

    public void reloading(DashboardActivity dashboardActivity, String _searchIndex){
//        mContext = dashboardActivity.getApplicationContext();
//        searchMediaAdapter = new SearchMediaAdapter(this);
////        tv_1 = mContext
//        if (mContext != null){
//            loadPage(mContext, _searchIndex);
//        }else {
//            Log.e("getActivity", "null");
//        }

//        tv_cari = getActivity().findViewById(R.id.tv_cari);
//        tv_cari.setText("cacacaca");
    }

    @Override
    public void myAction(String search) {
        Log.e("OMG ", search);
    }
}