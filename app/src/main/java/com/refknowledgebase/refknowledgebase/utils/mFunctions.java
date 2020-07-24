package com.refknowledgebase.refknowledgebase.utils;

import android.annotation.SuppressLint;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.refknowledgebase.refknowledgebase.buffer.mBuffer;

import java.util.HashMap;
import java.util.Map;

public class mFunctions {
//        private void deleteSavedFaq() {
//        final RequestQueue queue = Volley.newRequestQueue(mContext);
//
//        StringRequest sr = new StringRequest(Request.Method.DELETE, Constant.URL+Constant.API_SAVED_FAQ + "/" + saved_faq_id, new Response.Listener<String>() {
//            @SuppressLint("LongLogTag")
//            @Override
//            public void onResponse(String response) {
//                Methods.closeProgress();
////                Gson gson = new Gson();
////                FAQ_SavedModel faq_savedModel  = gson.fromJson(response, FAQ_SavedModel.class);
////                saved_faq_id = faq_savedModel.getId();
////                img_saved.setImageDrawable(getResources().getDrawable(R.drawable.un_saved));
//                Toast.makeText(mContext, "Successfully Deleted", Toast.LENGTH_SHORT).show();
//                notifyDataSetChanged();
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Methods.closeProgress();
//                Log.e("Service category","Service category failed" + error.toString());
//                Toast.makeText(mContext, "Some error Occured", Toast.LENGTH_SHORT).show();
//            }
//        }){
//            @Override
//            protected Map<String,String> getParams(){
//                Map<String,String> params = new HashMap<String, String>();
//                params.put("name", "Mylist_FAQ");
//                params.put("faqs[0]", String.valueOf(mBuffer.SELECTED_CONTENT_id));
//                return params;
//            }
//
//            @Override
//            public Map<String, String> getHeaders() {
//                Map<String,String> params = new HashMap<String, String>();
//                params.put("Content-Type","application/x-www-form-urlencoded");
//                params.put("Authorization", mBuffer.token_type + " " + mBuffer.oAuth_token);
//                return params;
//            }
//        };
//        queue.add(sr);
//    }
}
