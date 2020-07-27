package com.refknowledgebase.refknowledgebase.home_saved;

import android.annotation.SuppressLint;
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
import com.refknowledgebase.refknowledgebase.model.Swipe_Tab_entitiesModel;
import com.refknowledgebase.refknowledgebase.myinterface.HomeContentClickListner;
import com.refknowledgebase.refknowledgebase.myinterface.RecyclerViewClickListener;
import com.refknowledgebase.refknowledgebase.utils.Constant;
import com.refknowledgebase.refknowledgebase.utils.Methods;
import com.refknowledgebase.refknowledgebase.utils.PaginationScrollListener;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Saved_article_Fragment extends Fragment {
    RecyclerView recyclerView_home_saved_article;
    LinearLayoutManager layoutManager;
    private boolean isLoading = false,  isLastPage = false ;
    private static final int PAGE_START = 1;
    private int TOTAL_PAGES,  currentPage = PAGE_START, PER_PAGE = 100, LAST_PAGE;
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
//                Toast.makeText(getContext(), "Click + delete" + saved_entitiesModelList.get(position).getId(), Toast.LENGTH_SHORT).show();

                deleteSavedFaq(position, saved_entitiesModelList.get(position).getId());
            }
        };
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView_home_saved_article.setLayoutManager(layoutManager);
        savedFaqAdapter = new Saved_faq_Adapter(getContext(), homeContentClickListner);
        recyclerView_home_saved_article.setAdapter(savedFaqAdapter);
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

        private void deleteSavedFaq(final int position, int saved_faq_id) {
        final RequestQueue queue = Volley.newRequestQueue(getContext());

        StringRequest sr = new StringRequest(Request.Method.DELETE, Constant.URL+Constant.API_SAVED_FAQ + "/" + saved_faq_id, new Response.Listener<String>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(String response) {
                Methods.closeProgress();
//                Gson gson = new Gson();
//                FAQ_SavedModel faq_savedModel  = gson.fromJson(response, FAQ_SavedModel.class);
//                saved_faq_id = faq_savedModel.getId();
//                img_saved.setImageDrawable(getResources().getDrawable(R.drawable.un_saved));
//                Toast.makeText(getContext(), "Successfully Deleted", Toast.LENGTH_SHORT).show();
//                savedFaqAdapter.notifyItemRemoved(position);
//                savedFaqAdapter.notifyDataSetChanged();
                savedFaqAdapter.clear();
                loading_saved_faq();
                Methods.showProgress(getContext());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Methods.closeProgress();
                Log.e("Service category","Service category failed" + error.toString());
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


                Collections.sort(saved_entitiesModelList, new Comparator<Saved_entitiesModel>() {
                    @Override
                    public int compare(Saved_entitiesModel o1, Saved_entitiesModel o2) {
                        return Integer.compare(o2.getId(), o1.getId());
                    }
                });

                LAST_PAGE = savedFaqModel.getLast_page();
                TOTAL_PAGES = savedFaqModel.getLast_page();

                savedFaqAdapter.addAll(saved_entitiesModelList);

                for (int i = 0; i < saved_entitiesModelList.size(); i++){
                    if (saved_entitiesModelList.get(i).getFaqs() != null){
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