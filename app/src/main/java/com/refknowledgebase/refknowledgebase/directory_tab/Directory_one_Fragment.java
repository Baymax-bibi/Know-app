package com.refknowledgebase.refknowledgebase.directory_tab;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
import com.refknowledgebase.refknowledgebase.adapter.Directory_List_one_Adapter;
import com.refknowledgebase.refknowledgebase.adapter.Related_Contact_Adapter;
import com.refknowledgebase.refknowledgebase.adapter.WorkingHourAdapter;
import com.refknowledgebase.refknowledgebase.buffer.mBuffer;
import com.refknowledgebase.refknowledgebase.fragment.Directory_Fragment;
import com.refknowledgebase.refknowledgebase.model.Contact_form_entities_Model;
import com.refknowledgebase.refknowledgebase.model.Directory_List_Model;
import com.refknowledgebase.refknowledgebase.model.Directory_List_entitiesModel;
import com.refknowledgebase.refknowledgebase.model.Hashtags_entities_Model;
import com.refknowledgebase.refknowledgebase.model.Home_Content_DetailModel;
import com.refknowledgebase.refknowledgebase.model.Phone_Model;
import com.refknowledgebase.refknowledgebase.model.Working_Hour_Model;
import com.refknowledgebase.refknowledgebase.utils.Constant;
import com.refknowledgebase.refknowledgebase.utils.Methods;
import com.squareup.picasso.Picasso;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Directory_one_Fragment  extends Fragment implements View.OnClickListener{
TextView tv_number_con, tv_mail_con, tv_website_con, tv_address_con, tv_landmark_con, tv_near_con, tv_pluscode_con, tv_hashtag, tv_top;
    Directory_List_entitiesModel listEntitiesModel;
    List<Contact_form_entities_Model> contactFormEntitiesModelList;
    Directory_List_one_Adapter directoryListOneAdapter;
    RecyclerView rv_working;
    WorkingHourAdapter workingHourAdapter;
    RelativeLayout rl_contact_info;
    ImageView img_loc;
    Fragment fragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_dirctory_one_result, container, false);
        Methods.closeProgress();
        tv_number_con = root.findViewById(R.id.tv_number_con);
        tv_mail_con = root.findViewById(R.id.tv_mail_con);
        tv_website_con = root.findViewById(R.id.tv_website_con);
        tv_address_con = root.findViewById(R.id.tv_address_con);
        tv_landmark_con = root.findViewById(R.id.tv_landmark_con);
        tv_near_con = root.findViewById(R.id.tv_near_con);
        tv_pluscode_con = root.findViewById(R.id.tv_pluscode_con);
        tv_hashtag = root.findViewById(R.id.tv_hashtag);
        tv_top = root.findViewById(R.id.tv_top);
        rv_working = root.findViewById(R.id.rv_working);

        rl_contact_info = root.findViewById(R.id.rl_contact_info);
        img_loc = root.findViewById(R.id.img_loc);

        directoryListOneAdapter = new Directory_List_one_Adapter(getContext());
        workingHourAdapter = new WorkingHourAdapter(getContext());
        return root;
    }
    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rl_contact_info.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        img_loc.setOnClickListener(this);
        Methods.showProgress(getContext());
        loadingData();
    }

    private void loadingData() {
        RequestQueue queue = Volley.newRequestQueue(getContext());

        final String requestBody = "?&with[]=hashtags&with[]=directoryType&with[]=directoryMedia&with[]=directoryMediaLinks&with[]=serviceCategories&with[]=serviceCategories.translations&with[]=contactForms&with[]=contactForms.workingHours&with[]=contactForms.phones&with[]=contactForms.directoryCountry&with[]=translations&with[]=directoryType.translations";

        String get_url = Constant.URL+Constant.API_DIRCTORY_LIST_ONE + "/" + mBuffer.SELECTED_DIR_LIST_ID + requestBody;

        StringRequest sr = new StringRequest(Request.Method.GET, get_url, new Response.Listener<String>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(String response) {
                Methods.closeProgress();
                Gson gson = new Gson();
                listEntitiesModel = gson.fromJson(response, Directory_List_entitiesModel.class);
                tv_top.setText(listEntitiesModel.getname());

                mBuffer.map_selected_country = listEntitiesModel.getname();

                contactFormEntitiesModelList = listEntitiesModel.getcontact_forms();
                directoryListOneAdapter.addAll(contactFormEntitiesModelList);
                for (int i = 0; i < contactFormEntitiesModelList.size(); i++){
                    if (contactFormEntitiesModelList.get(i).getwebsite() != null){
                        String website = contactFormEntitiesModelList.get(i).getwebsite();
                        tv_website_con.setText(website);
                    }else {
                        tv_website_con.setText("No Website");
                    }

                    if (contactFormEntitiesModelList.get(i).getEmail() != null){
                        tv_mail_con.setText(contactFormEntitiesModelList.get(i).getEmail());
                    }else {
                        tv_mail_con.setText("No Email");
                    }

                    if (contactFormEntitiesModelList.get(i).getLandmark() != null){
                        tv_landmark_con.setText(contactFormEntitiesModelList.get(i).getLandmark());
                    }else {
                        tv_landmark_con.setText("No Landmark");
                    }

                    if (contactFormEntitiesModelList.get(i).getMetro() != null){
                        tv_near_con.setText(contactFormEntitiesModelList.get(i).getMetro());
                    }else {
                        tv_near_con.setText("No Metro");
                    }

                    if (contactFormEntitiesModelList.get(i).getPluscode() != null){
                        tv_pluscode_con.setText(contactFormEntitiesModelList.get(i).getPluscode());
                    }else {
                        tv_pluscode_con.setText("No Pluscode");
                    }

                    String add_line_one = "", add_City = "", add_country = "";
                    if (contactFormEntitiesModelList.get(i).getAddress_line_one() != null){
                        add_line_one = contactFormEntitiesModelList.get(i).getAddress_line_one();
                    }
                    if (contactFormEntitiesModelList.get(i).getAddress_line_two() != null){
                        add_City = contactFormEntitiesModelList.get(i).getCity();
                    }
                    if (contactFormEntitiesModelList.get(i).getCountry() != null){
                        add_country = contactFormEntitiesModelList.get(i).getCountry();
                    }
                    tv_address_con.setText(add_line_one + " " + add_City + " " + add_country);
                }
//                phone number
                for (int i = 0; i < contactFormEntitiesModelList.size(); i++){
                    if (contactFormEntitiesModelList.get(i).getPhones() != null){
                        List<Phone_Model> phone_modelList = new ArrayList<>(contactFormEntitiesModelList.get(i).getPhones());
                        directoryListOneAdapter.addAll_Phone(phone_modelList);
                        String phone_number = "";
                        for (int phone_j = 0; phone_j < phone_modelList.size(); phone_j++){
                            phone_number = "+"+phone_modelList.get(phone_j).getCountry_code() +" " + phone_modelList.get(phone_j).getArea_code() + " " + phone_modelList.get(phone_j).getPhone() + "  ";
                        }
                        tv_number_con.setText(phone_number);
                    }else {
                        tv_number_con.setText("No Phone");
                    }
                }

                for (int i = 0; i < contactFormEntitiesModelList.size(); i++){
                    if (contactFormEntitiesModelList.get(i).getWorking_hours() != null){
                        List<Working_Hour_Model> workingHourModelList = new ArrayList<>(contactFormEntitiesModelList.get(i).getWorking_hours());
                        workingHourAdapter.addAll_workingHour(workingHourModelList);
                    }
                }
//                contact location
                if (contactFormEntitiesModelList.get(0).getLat() != null && contactFormEntitiesModelList.get(0).getLng() != null){
                    mBuffer.map_lat = contactFormEntitiesModelList.get(0).getLat();
                    mBuffer.map_long = contactFormEntitiesModelList.get(0).getLng();
                }else {
                    mBuffer.map_lat = "0.0";
                    mBuffer.map_long = "0.0";
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
            case R.id.img_loc:
                fragment = new Map_Fragment();
                loadFragment(fragment);
                Methods.showProgress(getContext());
                break;
        }
    }

    private boolean loadFragment(Fragment fragment){
        Methods.showProgress(getContext());
        if (fragment != null){
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_search_content, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}