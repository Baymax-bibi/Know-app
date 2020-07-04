package com.refknowledgebase.refknowledgebase.directory_tab;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
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
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.refknowledgebase.refknowledgebase.R;
import com.refknowledgebase.refknowledgebase.adapter.Directory_List_Index_Adapter;
import com.refknowledgebase.refknowledgebase.alphabetindex.RecyclerViewAdapter;
import com.refknowledgebase.refknowledgebase.buffer.mBuffer;
import com.refknowledgebase.refknowledgebase.model.Directory_List_Model;
import com.refknowledgebase.refknowledgebase.model.Directory_List_entitiesModel;
import com.refknowledgebase.refknowledgebase.model.Service_Category_Model;
import com.refknowledgebase.refknowledgebase.myinterface.DirectListClickListner;
import com.refknowledgebase.refknowledgebase.utils.Constant;
import com.refknowledgebase.refknowledgebase.utils.GeocodingLocation;
import com.refknowledgebase.refknowledgebase.utils.Methods;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.parse.Parse.getApplicationContext;
import static com.refknowledgebase.refknowledgebase.utils.Constant.countries;
import static com.refknowledgebase.refknowledgebase.utils.Constant.radius;

public class Map_Fragment extends Fragment implements GoogleMap.OnMarkerClickListener, OnMapReadyCallback, View.OnClickListener{
    private GoogleMap mMap;
    Projection projection;

    private static LatLng PERTH = null;
    private Marker mPerth;

    LinearLayout ly_map_radius, ly_map_country;
    TextView tv_map_radius, tv_map_country;

    RecyclerView mRecyclerView, rv_index;
    Directory_List_Model directoryListModel;
    RecyclerViewAdapter recyclerViewAdapter;
    LinearLayoutManager layoutManager;
    DirectListClickListner directListClickListner;
    private static final int START_PAGE = 1;
    private int PER_PAGE = 1000, CURRENTPAGE =START_PAGE, LASTPAGE, TOTALPAGE;
    private boolean isLoading = false, isLast = false;
    Fragment myFragment;
    private List<Directory_List_entitiesModel> results;
    Directory_List_Index_Adapter directoryListIndexAdapter;
    String INDEX_FILTER = "";
    private List<Service_Category_Model> mServiceArray;
    SupportMapFragment mapFragment;
    public Map_Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_map, container, false);

        PERTH = new LatLng(Double.parseDouble(mBuffer.map_lat), Double.parseDouble(mBuffer.map_long));
        Log.e("LATLong", mBuffer.map_lat + " : " + mBuffer.map_long);
        mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Methods.closeProgress();
        ly_map_radius = root.findViewById(R.id.ly_map_radius);
        ly_map_country = root.findViewById(R.id.ly_map_country);

        tv_map_radius = root.findViewById(R.id.tv_map_radius);
        tv_map_country = root.findViewById(R.id.tv_map_country);
        tv_map_country.setText(mBuffer.map_selected_country);
        tv_map_country.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.e("addTextChangedListener","beforeTextChanged");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                Log.e("addTextChangedListener","onTextChanged");
            }

            @Override
            public void afterTextChanged(Editable s) {

                Log.e("addTextChangedListener","afterTextChanged");
                mBuffer.map_selected_country = tv_map_country.getText().toString();
                GeocodingLocation locationAddres = new GeocodingLocation();
                locationAddres.getAddressFromLocation(tv_map_country.getText().toString(),
                        getApplicationContext(), new GeocoderHandler());
            }
        });

//        loading_content();
        return root;
    }
    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ly_map_radius.setOnClickListener(this);
        ly_map_country.setOnClickListener(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        projection = mMap.getProjection();
        mMap.clear();

        mPerth = mMap.addMarker(new MarkerOptions()
                .position(PERTH)
//                .anchor(0.5f,0.5f)
//                .infoWindowAnchor(0.5f, 0)
                .title(mBuffer.map_selected_country)
//                .snippet("LAT: " + mBuffer.map_lat + ", LONG: " + mBuffer.map_long)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));


        mMap.setOnMarkerClickListener(this);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(PERTH));
    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        return false;
    }

    @Override
    public void onClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        switch (v.getId()){
            case R.id.ly_map_country:
                builder.setTitle("Select Country");
                builder.setItems(countries, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tv_map_country.setText(countries[which]);
                    }
                });
                break;
            case R.id.ly_map_radius:
                builder.setTitle("Select Radius");
                builder.setItems(radius, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tv_map_radius.setText(radius[which] + " km radius");
                    }
                });
                break;
        }
        AlertDialog dialog = builder.create();
        dialog.show();
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
            public byte[] getBody() throws AuthFailureError {
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

    private class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            String locationAddress;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    break;
                default:
                    locationAddress = null;
            }
//            latLongTV.setText(locationAddress);
//            Log.e("LATLong", locationAddress);
            String[] location = locationAddress.split(",");
            mBuffer.map_lat = location[0];
            mBuffer.map_long = location[1];
//            mapFragment.getMapAsync((OnMapReadyCallback) getContext());

            Fragment currentFragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.fl_directory_content);
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.detach(currentFragment);
            fragmentTransaction.attach(currentFragment);
            fragmentTransaction.commit();
        }
    }
}

