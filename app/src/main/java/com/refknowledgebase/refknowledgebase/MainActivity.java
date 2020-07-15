package com.refknowledgebase.refknowledgebase;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.refknowledgebase.refknowledgebase.buffer.mBuffer;
import com.refknowledgebase.refknowledgebase.model.oAuth_Model;
import com.refknowledgebase.refknowledgebase.utils.Constant;
import com.refknowledgebase.refknowledgebase.utils.Methods;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    oAuth_Model oAuth_model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mBuffer.map_selected_country = getString("MAPCOUNTRY");
        mBuffer.map_lat = getString("MAPLAT");
        mBuffer.map_long = getString("MAPLONG");
        insertString("SEARCH_KEY", "a");
        getAuthTocken();
    }

    private void getAuthTocken() {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest sr = new StringRequest(Request.Method.POST,Constant.URL+Constant.OAuth, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.e("TAG", "getAutoTocken");
                Methods.closeProgress();
                Gson gson = new Gson();
                oAuth_model = gson.fromJson(response, oAuth_Model.class);
                if (oAuth_model != null){
                    String token_type = oAuth_model.getToken_type();
                    String expires_in = oAuth_model.getExpires_in();
                    String access_token = oAuth_model.getAccess_token();
                    String refresh_token = oAuth_model.getRefresh_token();

                    mBuffer.oAuth_token = access_token;
                    mBuffer.token_type = token_type;

                    if (!mBuffer.oAuth_token.equals("") && !mBuffer.token_type.equals("")){
                        if (getBoolean(Constant.AFTERLANG)){
//                            Intent intent = new Intent(MainActivity.this, Landing_three.class);
//                            startActivity(intent);
//                            finish();
                            Fragment fragment = new LandingFragment();
                            loadFragment(fragment);

                        }else {
                            Intent intent = new Intent(MainActivity.this, Activity_Sec.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "OAuth key Failed.", Toast.LENGTH_SHORT).show();
                getAuthTocken();
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("grant_type","password");
                params.put("client_id","6");
                params.put("client_secret", "BQ8GZifIDRI9b6LR7PAhhO2nddeqOdEk8WFZLy6c");
                params.put("username","app@user.com");
                params.put("password","aRURx{3Yx^^r");

                return params;
            }
            @Override
            public Map<String, String> getHeaders() {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                return params;
            }
        };
        queue.add(sr);
    }
    public synchronized boolean getBoolean(String key) {
        SharedPreferences mSharedPreferences = getSharedPreferences(Constant.PREF, MODE_PRIVATE);
        boolean selected =  mSharedPreferences.getBoolean(key, false);
        return selected;
    }
    public synchronized void insertBoolean(String key, boolean value) {
        SharedPreferences mSharedPreferences = getSharedPreferences(Constant.PREF, MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putBoolean(key, value);
        mEditor.apply();
    }
    public synchronized void insertString(String key, String value) {
        SharedPreferences mSharedPreferences = getSharedPreferences(Constant.PREF, MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putString(key, value);
        mEditor.apply();
    }
    public String getString(String key) {
        SharedPreferences mSharedPreferences = getSharedPreferences(Constant.PREF, MODE_PRIVATE);
        return mSharedPreferences.getString(key, "");
    }

    private boolean loadFragment(Fragment fragment){
        if (fragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_main, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}