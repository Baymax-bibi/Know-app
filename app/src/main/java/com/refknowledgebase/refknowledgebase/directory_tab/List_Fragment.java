package com.refknowledgebase.refknowledgebase.directory_tab;

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
import com.refknowledgebase.refknowledgebase.adapter.Directory_List_Index_Adapter;
import com.refknowledgebase.refknowledgebase.alphabetindex.RecyclerViewAdapter;
import com.refknowledgebase.refknowledgebase.buffer.mBuffer;
import com.refknowledgebase.refknowledgebase.model.Directory_List_Model;
import com.refknowledgebase.refknowledgebase.model.Directory_List_entitiesModel;
import com.refknowledgebase.refknowledgebase.model.MyListData;
import com.refknowledgebase.refknowledgebase.model.Service_Category_Model;
import com.refknowledgebase.refknowledgebase.myinterface.DirectListClickListner;
import com.refknowledgebase.refknowledgebase.myinterface.RecyclerViewClickListener;
import com.refknowledgebase.refknowledgebase.utils.Constant;
import com.refknowledgebase.refknowledgebase.utils.Methods;
import com.refknowledgebase.refknowledgebase.utils.PaginationScrollListener;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class List_Fragment extends Fragment{

    RecyclerView mRecyclerView, rv_index;
    Directory_List_Model directoryListModel;
    RecyclerViewAdapter recyclerViewAdapter;
    LinearLayoutManager layoutManager;
    DirectListClickListner directListClickListner;
    private static final int START_PAGE = 1;
    private int PER_PAGE = 10, CURRENTPAGE =START_PAGE, LASTPAGE, TOTALPAGE;
    private boolean isLoading = false, isLast = false;
    Fragment myFragment;
    private List<Directory_List_entitiesModel> results;
    Directory_List_Index_Adapter directoryListIndexAdapter;
    String INDEX_FILTER = "";
    private List<Service_Category_Model> mServiceArray;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_list, container, false);

        Methods.closeProgress();

        directListClickListner = new DirectListClickListner() {
            @Override
            public void recyclerViewListClicked(View v, int position) {
            mBuffer.SELECTED_DIR_LIST_ID = recyclerViewAdapter.getItem(position).getid();
            myFragment = new Directory_one_Fragment();
            Methods.showProgress(getContext());
            loadFragment(myFragment);
            }
        };
        recyclerViewAdapter = new RecyclerViewAdapter(getContext(), directListClickListner);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        mRecyclerView =  root.findViewById(R.id.fast_scroller_recycler);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(recyclerViewAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addOnScrollListener(new PaginationScrollListener(layoutManager) {
            @Override
            protected void loadMoreItems() {
                CURRENTPAGE += 1;
                isLoading = true;
                Methods.showProgress(getContext());
                loading_content();
            }

            @Override
            public int getTotalPageCount() {
                return TOTALPAGE;
            }

            @Override
            public boolean isLastPage() {
                return isLast;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });

        Methods.showProgress(getContext());
        loading_content();
//        index recyclerview
        final MyListData[] myListData = new MyListData[] {
                new MyListData("A"),
                new MyListData("B"),
                new MyListData("C"),
                new MyListData("D"),
                new MyListData("E"),
                new MyListData("F"),
                new MyListData("G"),
                new MyListData("H"),
                new MyListData("I"),
                new MyListData("J"),
                new MyListData("K"),
                new MyListData("L"),
                new MyListData("M"),
                new MyListData("N"),
                new MyListData("O"),
                new MyListData("P"),
                new MyListData("Q"),
                new MyListData("R"),
                new MyListData("S"),
                new MyListData("T"),
                new MyListData("U"),
                new MyListData("V"),
                new MyListData("W"),
                new MyListData("X"),
                new MyListData("Y"),
                new MyListData("Z")
        };

        rv_index = root.findViewById(R.id.rv_index);
        LinearLayoutManager layoutManager_index = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv_index.setLayoutManager(layoutManager_index);
        RecyclerViewClickListener recyclerViewClickListener = new RecyclerViewClickListener(){
            @Override
            public void recyclerViewListClicked(View v, int position) {
                INDEX_FILTER = myListData[position].getindex();
                CURRENTPAGE = 1;
                recyclerViewAdapter.clear();
                Methods.showProgress(getContext());
                loading_content();
            }
        };

        directoryListIndexAdapter = new Directory_List_Index_Adapter(getContext(), recyclerViewClickListener, myListData);

        rv_index.setAdapter(directoryListIndexAdapter);
        rv_index.setItemAnimator(new DefaultItemAnimator());

        return root;
    }

    private void loading_content() {
        RequestQueue queue = Volley.newRequestQueue(getContext());

        final String requestBody = "{\"with\":[\"translations\"],\n" +
                "\"with_conditions\":{},\n" +
                "\"conditions\":\"\",\n" +
                "\"filter_by_first_char\":\""+INDEX_FILTER+"\",\n" +
                "\"lang\":\"English\",\n" +
                "\"per_page\":"+PER_PAGE+",\n" +
                "\"page\":"+CURRENTPAGE+"}";

        String get_url = Constant.URL+Constant.API_DIRCTORY_LIST;
        StringRequest sr = new StringRequest(Request.Method.POST, get_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Methods.closeProgress();
                Gson gson = new Gson();
                directoryListModel = gson.fromJson(response, Directory_List_Model.class);

                results = directoryListModel.getEntities();
                LASTPAGE = directoryListModel.getLast_page();

                recyclerViewAdapter.addAll(results);

                for (int i = 0 ; i < results.size(); i ++){
                    if (results.get(i).getservice_categories() != null){
                        mServiceArray = new ArrayList<>(results.get(i).getservice_categories());
                    }
                    recyclerViewAdapter.addServiceAll(mServiceArray);
                }

                if (CURRENTPAGE >= LASTPAGE){
                    isLoading = true;
                }else {
                    isLoading = false;
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Methods.closeProgress();
            }
        }){
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() {
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

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private boolean loadFragment(Fragment fragment){
        if (fragment != null){
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contact_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}