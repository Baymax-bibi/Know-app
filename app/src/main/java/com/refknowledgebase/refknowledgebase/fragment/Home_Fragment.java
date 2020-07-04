package com.refknowledgebase.refknowledgebase.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
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
import com.refknowledgebase.refknowledgebase.adapter.Swipe_Tab_Adapter;
import com.refknowledgebase.refknowledgebase.buffer.mBuffer;
import com.refknowledgebase.refknowledgebase.home_tab.Assistance_detail;
import com.refknowledgebase.refknowledgebase.model.Home_Content_Model;
import com.refknowledgebase.refknowledgebase.model.Home_Content_engitiesModel;
import com.refknowledgebase.refknowledgebase.model.Swipe_Tab_Model;
import com.refknowledgebase.refknowledgebase.model.Swipe_Tab_entitiesModel;
import com.refknowledgebase.refknowledgebase.myinterface.HomeContentClickListner;
import com.refknowledgebase.refknowledgebase.myinterface.RecyclerViewClickListener;
import com.refknowledgebase.refknowledgebase.utils.Constant;
import com.refknowledgebase.refknowledgebase.utils.Methods;
import com.refknowledgebase.refknowledgebase.utils.PaginationScrollListener;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Home_Fragment extends Fragment {

    private RecyclerView rv_tab_bar, rv_home_content;
    private Swipe_Tab_Adapter swipeTabAdapter;
    private Swipe_Tab_Model swipeTabModel;
    private LinearLayoutManager layoutManager_tab, layoutManager_content;
    FrameLayout home_pager;
    private boolean isLoading = false,  isLastPage = false, isLoading_content = false ;
    private static final int PAGE_START = 1;
    private int TOTAL_PAGES,  currentPage = PAGE_START, PER_PAGE = 5, LAST_PAGE, TOTAL_PAGES_content, PER_PAGE_content = 15, LAST_PAGE_content, currentPage_content;
    RecyclerViewClickListener recyclerViewClickListener;
    private List<Swipe_Tab_entitiesModel> results;
    private Home_Content_Adapter homeContentAdapter;
    private Home_Content_Model homeContentModel;
    private List<Home_Content_engitiesModel> results_content;
    Fragment fragment;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        home_pager = root.findViewById(R.id.home_pager);
        layoutManager_tab = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rv_tab_bar = root.findViewById(R.id.rv_tab_bar);
        rv_tab_bar.setLayoutManager(layoutManager_tab);

        recyclerViewClickListener = new RecyclerViewClickListener() {
            @Override
            public void recyclerViewListClicked(View v, int position) {
            mBuffer.service_category_ids = swipeTabAdapter.getItem(position).getid();
            currentPage_content = 1;
            LAST_PAGE_content = 0;
            homeContentAdapter.clear();
            Constant.SELECTED_CATEGORY = swipeTabAdapter.getItem(position).getname();
            Constant.SELECTED_CATEGORY_ID = swipeTabAdapter.getItem(position).getId();
            Methods.showProgress(getContext());
            loading_content();
            }
        };

        swipeTabAdapter = new Swipe_Tab_Adapter(getContext(), recyclerViewClickListener);
        rv_tab_bar.setAdapter(swipeTabAdapter);
//        rv_tab_bar.getLayoutManager().scrollToPosition(mBuffer.selectedItem);
//        layoutManager_tab.scrollToPositionWithOffset(2, 10);
//        int itemToScroll = rv_tab_bar.getChildAdapterPosition(root);
//        int centerOfScreen = rv_tab_bar.getWidth() / 2 - root.getWidth() / 2;
//        layoutManager_tab.scrollToPositionWithOffset(itemToScroll, centerOfScreen);
        rv_tab_bar.setItemAnimator(new DefaultItemAnimator());
        rv_tab_bar.addOnScrollListener(new PaginationScrollListener(layoutManager_tab) {
            @Override
            protected void loadMoreItems() {
                currentPage += 1;
                isLoading = true;
                Methods.showProgress(getContext());

                loadnextpage();
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
        loadfirstpage();

//content
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

            fragment = new Assistance_detail();
            loadFragment(fragment);
            }
        };
        rv_home_content = root.findViewById(R.id.rv_home_content);

        homeContentAdapter = new Home_Content_Adapter(getContext(), homeContentClickListner);
        rv_home_content.setAdapter(homeContentAdapter);
        layoutManager_content = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv_home_content.setLayoutManager(layoutManager_content);
        rv_home_content.setItemAnimator(new DefaultItemAnimator());
        rv_home_content.addOnScrollListener(new PaginationScrollListener(layoutManager_content) {
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

        Methods.showProgress(getContext());
        loading_content();

        return root;
    }

    private void loading_content() {
        RequestQueue queue = Volley.newRequestQueue(getContext());

        final String requestBody = "{\n" +
                "    \"with\":[\"service_category_ids\"],\n" +
                "    \"with_conditions\":{\n" +
                "        \"service_category_ids\":[\""+mBuffer.service_category_ids+"\"]\n" +
                "    },\n" +
                "    \"conditions\":\"\",\n" +
                "    \"per_page\":10,\n" +
                "    \"page\":"+currentPage_content+",\n" +
                "    \"lang\":\"English\"\n" +
                "}";

        String url = Constant.URL+Constant.API_SERVICE_CONTENT_EN;

        StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Methods.closeProgress();

                Gson gson = new Gson();
                homeContentModel = gson.fromJson(response, Home_Content_Model.class);

                results_content = homeContentModel.getEntities();
                LAST_PAGE_content = homeContentModel.getLast_page();

                homeContentAdapter.addAll(results_content);

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
                Log.e("Service category", error.toString());
            }
        }){
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError{
                try {
                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                    return null;
                }
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

    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    private void loadfirstpage() {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String serviceCategory_url = Constant.URL+Constant.API_SERVICE_CATEGORY + "?page=" + currentPage + "&per_page=" + PER_PAGE;
        StringRequest sr = new StringRequest(Request.Method.GET, serviceCategory_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                Log.e("response_content", response);
                Methods.closeProgress();
                Gson gson = new Gson();
                swipeTabModel = gson.fromJson(response, Swipe_Tab_Model.class);
                isLoading = false;
                results = swipeTabModel.getEntities();
                Collections.sort(results, new Comparator<Swipe_Tab_entitiesModel>() {
                    @Override
                    public int compare(Swipe_Tab_entitiesModel o1, Swipe_Tab_entitiesModel o2) {
                        return o1.getname().compareTo(o2.getname());
                    }
                });
                swipeTabAdapter.addAll(results);
                TOTAL_PAGES = swipeTabModel.getLast_page();
                LAST_PAGE = swipeTabModel.getLast_page();

                if (currentPage >= LAST_PAGE){
                    isLoading = true;
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

    private void loadnextpage() {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String loadNext_url = Constant.URL+Constant.API_SERVICE_CATEGORY + "?page=" + currentPage + "&per_page=" + PER_PAGE;
        StringRequest sr = new StringRequest(Request.Method.GET, loadNext_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Methods.closeProgress();
                Gson gson = new Gson();
                swipeTabModel = gson.fromJson(response, Swipe_Tab_Model.class);
                isLoading = false;
                results = swipeTabModel.getEntities();
                Collections.sort(results, new Comparator<Swipe_Tab_entitiesModel>() {
                    @Override
                    public int compare(Swipe_Tab_entitiesModel o1, Swipe_Tab_entitiesModel o2) {
                        return o1.getname().compareTo(o2.getname());
                    }
                });
                swipeTabAdapter.addAll(results);

                TOTAL_PAGES = swipeTabModel.getLast_page();
                LAST_PAGE = swipeTabModel.getLast_page();

                if (currentPage >= LAST_PAGE){
                    isLoading = true;
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

    private void loadFragment(Fragment fragment){
        if (fragment != null){
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    //.replace(R.id.fragment_container, fragment)
                    .replace(R.id.nav_host_fragment, fragment)
                    .commit();
        }
    }

}
