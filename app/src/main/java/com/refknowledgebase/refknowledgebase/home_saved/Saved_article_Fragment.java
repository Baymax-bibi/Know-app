package com.refknowledgebase.refknowledgebase.home_saved;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
import com.refknowledgebase.refknowledgebase.adapter.Saved_faq_Adapter;
import com.refknowledgebase.refknowledgebase.buffer.mBuffer;
import com.refknowledgebase.refknowledgebase.model.Home_Content_Model;
import com.refknowledgebase.refknowledgebase.model.Home_Content_engitiesModel;
import com.refknowledgebase.refknowledgebase.model.Saved_entitiesModel;
import com.refknowledgebase.refknowledgebase.model.Saved_faq_Model;
import com.refknowledgebase.refknowledgebase.myinterface.HomeContentClickListner;
import com.refknowledgebase.refknowledgebase.myinterface.RecyclerViewClickListener;
import com.refknowledgebase.refknowledgebase.utils.Constant;
import com.refknowledgebase.refknowledgebase.utils.Methods;
import com.refknowledgebase.refknowledgebase.utils.PaginationScrollListener;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Saved_article_Fragment extends Fragment {
    RecyclerView recyclerView_home_saved_article;
    LinearLayoutManager layoutManager;
    private boolean isLoading = false,  isLastPage = false ;
    private static final int PAGE_START = 1;
    private int TOTAL_PAGES,  currentPage = PAGE_START, PER_PAGE = 5, LAST_PAGE;
    Saved_faq_Model savedFaqModel;
    List<Saved_entitiesModel> saved_entitiesModelList;
    Saved_faq_Adapter savedFaqAdapter;
    HomeContentClickListner homeContentClickListner;
    List<Home_Content_engitiesModel> homeContentModelList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_saved_article, container, false);
        return root;
    }
    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView_home_saved_article = view.findViewById(R.id.recyclerView_home_saved);

        homeContentClickListner = new HomeContentClickListner() {

            @Override
            public void Home_Content_ClickListner(View v, int position) {
//                Toast.makeText(getContext(), "Click" + position, Toast.LENGTH_SHORT).show();
            }
        };
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView_home_saved_article.setLayoutManager(layoutManager);
        savedFaqAdapter = new Saved_faq_Adapter(getContext(), homeContentClickListner);
        recyclerView_home_saved_article.setAdapter(savedFaqAdapter);
//        recyclerView_home_saved_article.setHasFixedSize(true);

        recyclerView_home_saved_article.setItemAnimator(new DefaultItemAnimator());
        recyclerView_home_saved_article.addOnScrollListener(new PaginationScrollListener(layoutManager) {
            @Override
            protected void loadMoreItems() {
                if (isLastPage){

                }else {
                    currentPage += 1;
                    isLoading = true;
                    Methods.showProgress(getContext());
                    loading_saved_faq();
                }
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
        loading_saved_faq();
    }

    private void loading_saved_faq() {
        RequestQueue queue = Volley.newRequestQueue(getContext());

        String url = Constant.URL+Constant.API_SAVED_FAQ + "?page="+currentPage+"&per_page="+PER_PAGE+"&paginate=true&with[0]=faqs";

        StringRequest sr = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Methods.closeProgress();

                Gson gson = new Gson();
                savedFaqModel = gson.fromJson(response, Saved_faq_Model.class);

                saved_entitiesModelList = savedFaqModel.getEntities();
                LAST_PAGE = savedFaqModel.getLast_page();
                TOTAL_PAGES = savedFaqModel.getLast_page();

                savedFaqAdapter.addAll(saved_entitiesModelList);

                for (int i = 0; i < saved_entitiesModelList.size(); i++){
                    if (saved_entitiesModelList.get(i).getFaqs() != null){
                        Log.e("TAG", String.valueOf(saved_entitiesModelList.get(i).getFaqs()));
                        homeContentModelList = new ArrayList<>(saved_entitiesModelList.get(i).getFaqs());

                        savedFaqAdapter.addAll_faq(homeContentModelList);
                    }
                }

                if (currentPage >= LAST_PAGE){
                    isLoading = true;
                    isLastPage = true;
                }else {
                    isLoading = false;
                    isLastPage = false;
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Methods.closeProgress();
                Log.e("Service category", error.toString());
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
                params.put("Content-Type","application/json");
                params.put("Authorization", mBuffer.token_type + " " + mBuffer.oAuth_token);
                return params;
            }
        };
        queue.add(sr);
    }
}