package com.refknowledgebase.refknowledgebase;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.flipkart.youtubeview.YouTubePlayerView;
import com.flipkart.youtubeview.models.ImageLoader;
import com.google.gson.Gson;
import com.refknowledgebase.refknowledgebase.adapter.LandingThreeAdapter;
import com.refknowledgebase.refknowledgebase.adapter.SearchMediaAdapter;
import com.refknowledgebase.refknowledgebase.adapter.Swipe_Tab_Adapter;
import com.refknowledgebase.refknowledgebase.adapter.ThreeCategoryAdapter;
import com.refknowledgebase.refknowledgebase.adapter.ViewPagerAdapter;
import com.refknowledgebase.refknowledgebase.buffer.mBuffer;
import com.refknowledgebase.refknowledgebase.model.Search_Media_Model;
import com.refknowledgebase.refknowledgebase.model.Search_Media_entities_Model;
import com.refknowledgebase.refknowledgebase.model.Swipe_Tab_Model;
import com.refknowledgebase.refknowledgebase.model.Swipe_Tab_entitiesModel;
import com.refknowledgebase.refknowledgebase.myinterface.RecyclerViewClickListener;
import com.refknowledgebase.refknowledgebase.utils.Constant;
import com.refknowledgebase.refknowledgebase.utils.Methods;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Landing_three extends AppCompatActivity {

//    ViewPager viewPager;
//    LinearLayout sliderDotspanel;
//    private int dotscount;
//    private ImageView[] dots;
//    static Animation animationslide_l2r;
//    static Animation animationslide_r2l;
//    static LinearLayout ly_one;
//    static LinearLayout ly_two, ly_over;
//    OnSwipeTouchListener onSwipeTouchListener;
//    static ImageView img_dot_one;
//    static ImageView img_dot_two;
////    GridView category_grid;
//    private Swipe_Tab_Model swipeTabModel;
//    private boolean isLoading = false,  isLastPage = false, isLoading_content = false ;
//    private List<Swipe_Tab_entitiesModel> results;
//    private Swipe_Tab_Adapter swipeTabAdapter;
//    RecyclerViewClickListener recyclerViewClickListener;
//    RelativeLayout rl_107, rl_9, rl_18, rl_27, rl_42, rl_81, rl_84, rl_96, rl_119, rl_129, rl_131, rl_135, rl_136, rl_137, rl_138, rl_165, rl_166, rl_167;
//    RelativeLayout rl_slide;
//    WebView youTubePlayerView;
//    int current_position = 1;
//    ImageView img_pre, img_next;
//    Search_Media_Model search_media_model;
//    SearchMediaAdapter searchMediaAdapter;
//    ImageView img_poster;
//    private int Total_Media;
//
//    static boolean flag = true;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landingpage_three);

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//
////        viewPager = (ViewPager) findViewById(R.id.viewPager);
//        sliderDotspanel = findViewById(R.id.SliderDots);
//        youTubePlayerView = findViewById(R.id.video_landing);
//        img_pre = findViewById(R.id.img_pre);
//        img_next = findViewById(R.id.img_next);
//        img_poster = findViewById(R.id.img_poster);
//
//        img_pre.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                --current_position;
//                loadVideoData();
//            }
//        });
//        img_next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ++current_position;
//                loadVideoData();
//            }
//        });
//        loadVideoData();
//
//        rl_slide = findViewById(R.id.rl_slide);
//        ly_over = findViewById(R.id.ly_over);
//
//        rl_107 = (RelativeLayout) findViewById(R.id.rl_107);
//        rl_9 = (RelativeLayout) findViewById(R.id.rl_9);
//        rl_18 = (RelativeLayout) findViewById(R.id.rl_18);
//        rl_27 = (RelativeLayout) findViewById(R.id.rl_27);
//        rl_42 = (RelativeLayout) findViewById(R.id.rl_42);
//        rl_81 = (RelativeLayout) findViewById(R.id.rl_81);
//        rl_84 = (RelativeLayout) findViewById(R.id.rl_84);
//        rl_96 = (RelativeLayout) findViewById(R.id.rl_96);
//        rl_119 = (RelativeLayout) findViewById(R.id.rl_119);
//        rl_129 = (RelativeLayout) findViewById(R.id.rl_129);
//        rl_131 = (RelativeLayout) findViewById(R.id.rl_131);
//        rl_135 = (RelativeLayout) findViewById(R.id.rl_135);
//        rl_136 = (RelativeLayout) findViewById(R.id.rl_136);
//        rl_137 = (RelativeLayout) findViewById(R.id.rl_137);
//        rl_138 = (RelativeLayout) findViewById(R.id.rl_138);
//        rl_165 = (RelativeLayout) findViewById(R.id.rl_165);
//        rl_166 = (RelativeLayout) findViewById(R.id.rl_166);
//        rl_167 = (RelativeLayout) findViewById(R.id.rl_167);
//
//        rl_107.setOnClickListener(this);
//        rl_9.setOnClickListener(this);
//        rl_18.setOnClickListener(this);
//        rl_27.setOnClickListener(this);
//        rl_42.setOnClickListener(this);
//        rl_81.setOnClickListener(this);
//        rl_84.setOnClickListener(this);
//        rl_96.setOnClickListener(this);
//        rl_119.setOnClickListener(this);
//        rl_129.setOnClickListener(this);
//        rl_131.setOnClickListener(this);
//        rl_135.setOnClickListener(this);
//        rl_136.setOnClickListener(this);
//        rl_137.setOnClickListener(this);
//        rl_138.setOnClickListener(this);
//        rl_165.setOnClickListener(this);
//        rl_166.setOnClickListener(this);
//        rl_167.setOnClickListener(this);
//
//
//        loadcategory();
//
//        recyclerViewClickListener = new RecyclerViewClickListener() {
//            @Override
//            public void recyclerViewListClicked(View v, int position) {
//               Log.e("what", "is this");
//            }
//        };
//        swipeTabAdapter = new Swipe_Tab_Adapter(Landing_three.this, recyclerViewClickListener);
//
//        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
//
////        viewPager.setAdapter(viewPagerAdapter);
//
//        dotscount = viewPagerAdapter.getCount();
//        dots = new ImageView[dotscount];
//
//        for(int i = 0; i < dotscount; i++){
//
//            dots[i] = new ImageView(this);
//            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.viewpager_nondot));
//
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//
//            params.setMargins(8, 0, 8, 0);
//
////            sliderDotspanel.addView(dots[i], params);
//
//        }
//
//        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.viewpager_dot));
//
////        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
////            @Override
////            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
////
////            }
////
////            @Override
////            public void onPageSelected(int position) {
////
////                for(int i = 0; i< dotscount; i++){
////                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.viewpager_nondot));
////                }
////
////                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.viewpager_dot));
////
////            }
////
////            @Override
////            public void onPageScrollStateChanged(int state) {
////
////            }
////        });
//        ly_one = findViewById(R.id.ly_one);
//        ly_two = findViewById(R.id.ly_two);
//
//        animationslide_l2r = AnimationUtils.loadAnimation(this, R.anim.slide_right_left);
//        animationslide_r2l = AnimationUtils.loadAnimation(this, R.anim.slide_left_right);
////        animationslide_l2r.setAnimationListener(this);
////        animationslide_r2l.setAnimationListener(this);
//
////        category_grid = findViewById(R.id.category_grid);
//
//        img_dot_one = findViewById(R.id.img_dot_one);
//        img_dot_two = findViewById(R.id.img_dot_two);
//
//        onSwipeTouchListener = new OnSwipeTouchListener(this, findViewById(R.id.rl_slide));
//    }
//
//    private void loadVideoData() {
//        RequestQueue queue = Volley.newRequestQueue(Landing_three.this);
//
//        StringRequest sr = new StringRequest(Request.Method.GET, Constant.URL+Constant.API_MEDIA + "?page=" + current_position +"&per_page=" + 1, new Response.Listener<String>() {
//            @SuppressLint("WrongConstant")
//            @Override
//            public void onResponse(String response) {
//                Methods.closeProgress();
//                Gson gson = new Gson();
//                search_media_model = gson.fromJson(response, Search_Media_Model.class);
//                List<Search_Media_entities_Model> results = search_media_model.getEntities();
//
//                Total_Media = search_media_model.getTotal();
//
//                Context mContext = getApplicationContext();
//                String videoUrl = results.get(0).geturl();
//                if (results.get(0).getcontent_type().equals("VIDEO")){
//                    youTubePlayerView.setVisibility(View.VISIBLE);
//                    img_poster.setVisibility(View.GONE);
////                    if (videoUrl.contains("youtube")){
//                    Log.e("Video_url", videoUrl);
//                        String frameVideo = "<html><body style=\"margin:0; padding:0\"><iframe width=\"199\" height=\"114\" src=\"https://www.youtube.com/watch?v=lcfcG31AgyE\" frameborder=\"0\" allowfullscreen></iframe></body></html>";
//
//                        youTubePlayerView.setWebViewClient(new WebViewClient() {
//                            @Override
//                            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                                return false;
//                            }
//                        });
//                        WebSettings webSettings = youTubePlayerView.getSettings();
//                        webSettings.setJavaScriptEnabled(true);
//                        youTubePlayerView.loadData(frameVideo, "text/html", "utf-8");
////                    }else if(videoUrl.contains("vimeo")){
////
////
////                    }
//                }else if(results.get(0).getcontent_type().equals("POSTER")){
//                    youTubePlayerView.setVisibility(View.GONE);
//                    img_poster.setVisibility(View.VISIBLE);
//                    Picasso.with(img_poster.getContext()).load(videoUrl).fit().into(img_poster);
//                }
//
////                if (current_position >= Total_Media){
////                    isLoading = true;
////                }else {
////                    isLoading = false;
////                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Methods.closeProgress();
//                Log.e("Country","Country failed" + error.toString());
//            }
//        }){
//            @Override
//            protected Map<String,String> getParams(){
//                Map<String,String> params = new HashMap<String, String>();
//
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
//
//    @Override
//    public void onAnimationStart(Animation animation) {
//
//    }
//
//    @Override
//    public void onAnimationEnd(Animation animation) {
//
//    }
//
//    @Override
//    public void onAnimationRepeat(Animation animation) {
//
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.rl_107:
//                mBuffer.service_category_ids = 107;
//                mBuffer.service_category_name = "Assistance";
//                mBuffer.selectedItem = 0;
//                break;
//            case R.id.rl_131:
//                mBuffer.service_category_ids = 131;
//                mBuffer.service_category_name = "Child Protection";
//                mBuffer.selectedItem = 1;
//                break;
//            case R.id.rl_136:
//                mBuffer.service_category_ids = 136;
//                mBuffer.service_category_name = "Community-based Protection";
//                mBuffer.selectedItem = 2;
//                break;
//            case R.id.rl_9:
//                mBuffer.service_category_ids = 9;
//                mBuffer.service_category_name = "Education";
//                mBuffer.selectedItem = 3;
//                break;
//            case R.id.rl_18:
//                mBuffer.service_category_ids = 18;
//                mBuffer.service_category_name = "Health Care";
//                mBuffer.selectedItem = 4;
//                break;
//            case R.id.rl_96:
//                mBuffer.service_category_ids = 96;
//                mBuffer.service_category_name = "How to Contact UNHCR";
//                mBuffer.selectedItem = 5;
//                break;
//            case R.id.rl_129:
//                mBuffer.service_category_ids = 129;
//                mBuffer.service_category_name = "Legal Aid";
//                mBuffer.selectedItem = 6;
//                break;
//            case R.id.rl_84:
//                mBuffer.service_category_ids = 84;
//                mBuffer.service_category_name = "Livelihoods";
//                mBuffer.selectedItem = 7;
//                break;
//            case R.id.rl_27:
//                mBuffer.service_category_ids = 27;
//                mBuffer.service_category_name = "Protection";
//                mBuffer.selectedItem = 8;
//                break;
//            case R.id.rl_42:
//                mBuffer.service_category_ids = 42;
//                mBuffer.service_category_name = "Refugee Status Determination";
//                mBuffer.selectedItem = 9;
//                break;
//            case R.id.rl_137:
//                mBuffer.service_category_ids = 137;
//                mBuffer.service_category_name = "Registration";
//                mBuffer.selectedItem = 10;
//                break;
//            case R.id.rl_138:
//                mBuffer.service_category_ids = 138;
//                mBuffer.service_category_name = "Reporting Fraud and Corruption";
//                mBuffer.selectedItem = 11;
//                break;
//            case R.id.rl_81:
//                mBuffer.service_category_ids = 81;
//                mBuffer.service_category_name = "Resettlement";
//                mBuffer.selectedItem = 12;
//                break;
//            case R.id.rl_119:
//                mBuffer.service_category_ids = 119;
//                mBuffer.service_category_name = "Residency";
//                mBuffer.selectedItem = 13;
//                break;
//            case R.id.rl_135:
//                mBuffer.service_category_ids = 135;
//                mBuffer.service_category_name = "SGBV";
//                mBuffer.selectedItem = 14;
//                break;
//            case R.id.rl_167:
//                mBuffer.service_category_ids = 167;
//                mBuffer.service_category_name = "Covid-19";
//                mBuffer.selectedItem = 15;
//                break;
//            case R.id.rl_165:
//                mBuffer.service_category_ids = 165;
//                mBuffer.service_category_name = "Irregular Movements";
//                mBuffer.selectedItem = 16;
//                break;
//            case R.id.rl_166:
//                mBuffer.service_category_ids = 166;
//                mBuffer.service_category_name = "Telling the Real Story";
//                mBuffer.selectedItem = 17;
//                break;
//        }
//        startActivity(new Intent(Landing_three.this, DashboardActivity.class));
//        Constant.SELECTED_CATEGORY = mBuffer.service_category_name;
//        Constant.SELECTED_CATEGORY_ID = mBuffer.service_category_ids;
//        finish();
//    }
//
//    public static class OnSwipeTouchListener implements View.OnTouchListener {
//
//        private final GestureDetector gestureDetector;
//        Context context;
//        OnSwipeTouchListener(Context ctx, View mainView) {
//            gestureDetector = new GestureDetector(ctx, new GestureListener());
//            mainView.setOnTouchListener(this);
//            context = ctx;
//        }
//
//        @Override
//        public boolean onTouch(View v, MotionEvent event) {
//            return gestureDetector.onTouchEvent(event);
//        }
//
//        public static class GestureListener extends
//                GestureDetector.SimpleOnGestureListener {
//            private static final int SWIPE_THRESHOLD = 100;
//            private static final int SWIPE_VELOCITY_THRESHOLD = 100;
//            @Override
//            public boolean onDown(MotionEvent e) {
//                return true;
//            }
//            @Override
//            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//                boolean result = false;
//                try {
//                    float diffY = e2.getY() - e1.getY();
//                    float diffX = e2.getX() - e1.getX();
//                    if (Math.abs(diffX) > Math.abs(diffY)) {
//                        if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
//                            if (diffX > 0) {
//                                onSwipeLeft();
//                            } else {
//                                onSwipeRight();
//                            }
//                            result = true;
//                        }
//                    }
//                    else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
//                        if (diffY > 0) {
//                            onSwipeBottom();
//                        } else {
//                            onSwipeTop();
//                        }
//                        result = true;
//                    }
//                }
//                catch (Exception exception) {
//                    exception.printStackTrace();
//                }
//                return result;
//            }
//        }
//        void onSwipeRight() {
////            Toast.makeText(context, "Swiped Right", Toast.LENGTH_SHORT).show();
//
//            if (flag){
//                ly_one.startAnimation(animationslide_r2l);
//                ly_one.setVisibility(View.GONE);
//                ly_two.startAnimation(animationslide_l2r);
//                ly_two.setVisibility(View.VISIBLE);
//                img_dot_one.setImageResource(R.drawable.viewpager_nondot);
//                img_dot_two.setImageResource(R.drawable.viewpager_dot);
//                flag = false;
//
//                this.onSwipe.swipeRight();
//            }else {
//
//            }
//
//        }
//        void onSwipeLeft() {
////            Toast.makeText(context, "Swiped Left", Toast.LENGTH_SHORT).show();
//            if (!flag){
//                ly_one.startAnimation(animationslide_l2r);
//                ly_two.setVisibility(View.GONE);
//                ly_one.setVisibility(View.VISIBLE);
//                ly_two.startAnimation(animationslide_r2l);
//                img_dot_one.setImageResource(R.drawable.viewpager_dot);
//                img_dot_two.setImageResource(R.drawable.viewpager_nondot);
//                flag = true;
//                this.onSwipe.swipeLeft();
//            }else {
//
//            }
//
//        }
//        void onSwipeTop() {
//            Toast.makeText(context, "Swiped Up", Toast.LENGTH_SHORT).show();
//            this.onSwipe.swipeTop();
//        }
//        void onSwipeBottom() {
//            Toast.makeText(context, "Swiped Down", Toast.LENGTH_SHORT).show();
//            this.onSwipe.swipeBottom();
//        }
//        interface onSwipeListener {
//            void swipeRight();
//            void swipeTop();
//            void swipeBottom();
//            void swipeLeft();
//        }
//        onSwipeListener onSwipe;
//    }
//
//    private void loadcategory() {
//        RequestQueue queue = Volley.newRequestQueue(Landing_three.this);
//        String serviceCategory_url = Constant.URL+Constant.API_SERVICE_CATEGORY + "?page=" + 1 + "&per_page=" + 100;
//        StringRequest sr = new StringRequest(Request.Method.GET, serviceCategory_url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Log.e("response_content", response);
//                Methods.closeProgress();
//                Gson gson = new Gson();
//                swipeTabModel = gson.fromJson(response, Swipe_Tab_Model.class);
//                isLoading = false;
//                results = swipeTabModel.getEntities();
////                Collections.sort(results, new Comparator<Swipe_Tab_entitiesModel>() {
////                    @Override
////                    public int compare(Swipe_Tab_entitiesModel o1, Swipe_Tab_entitiesModel o2) {
////                        return o1.getname().compareTo(o2.getname());
////                    }
////                });
//                swipeTabAdapter.addAll(results);
//                List<Swipe_Tab_entitiesModel> gridswipetaplist = results;
////                category_grid.setAdapter(new ThreeCategoryAdapter(Landing_three.this, gridswipetaplist));
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Methods.closeProgress();
//            }
//        }){
//            @Override
//            protected Map<String,String> getParams(){
//                Map<String,String> params = new HashMap<String, String>();
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
    }
}