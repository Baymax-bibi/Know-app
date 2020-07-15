package com.refknowledgebase.refknowledgebase;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.flipkart.youtubeview.YouTubePlayerView;
import com.flipkart.youtubeview.models.ImageLoader;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.refknowledgebase.refknowledgebase.adapter.DashPagerAdapter;
import com.refknowledgebase.refknowledgebase.adapter.SearchMediaAdapter;
import com.refknowledgebase.refknowledgebase.adapter.Swipe_Tab_Adapter;
import com.refknowledgebase.refknowledgebase.adapter.ViewPagerAdapter;
import com.refknowledgebase.refknowledgebase.buffer.mBuffer;
import com.refknowledgebase.refknowledgebase.fragment.SearchFragment;
import com.refknowledgebase.refknowledgebase.model.Search_Media_Model;
import com.refknowledgebase.refknowledgebase.model.Search_Media_entities_Model;
import com.refknowledgebase.refknowledgebase.model.Swipe_Tab_Model;
import com.refknowledgebase.refknowledgebase.model.Swipe_Tab_entitiesModel;
import com.refknowledgebase.refknowledgebase.myinterface.RecyclerViewClickListener;
import com.refknowledgebase.refknowledgebase.utils.Constant;
import com.refknowledgebase.refknowledgebase.utils.Methods;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LandingFragment extends Fragment implements View.OnClickListener  {
    ViewPager viewPager;
    LinearLayout sliderDotspanel, over_media;
    private int dotscount;
    private ImageView[] dots;
    static Animation animationslide_l2r;
    static Animation animationslide_r2l;
    static LinearLayout ly_one;
    static LinearLayout ly_two;
    LinearLayout ly_over;
//    OnSwipeTouchListener onSwipeTouchListener;
    static ImageView img_dot_one;
    static ImageView img_dot_two;
    //    GridView category_grid;
    private Swipe_Tab_Model swipeTabModel;
    private boolean isLoading = false,  isLastPage = false, isLoading_content = false ;
    private List<Swipe_Tab_entitiesModel> results;
    private Swipe_Tab_Adapter swipeTabAdapter;
    RecyclerViewClickListener recyclerViewClickListener;
//    private static RelativeLayout rl_107;
//    private static RelativeLayout rl_9;
//    static RelativeLayout rl_18;
//    static RelativeLayout rl_27;
//    static RelativeLayout rl_42;
//    static RelativeLayout rl_81;
//    static RelativeLayout rl_84;
//    static RelativeLayout rl_96;
//    static RelativeLayout rl_119;
//    static RelativeLayout rl_129;
//    static RelativeLayout rl_131;
//    static RelativeLayout rl_135;
//    static RelativeLayout rl_136;
//    static RelativeLayout rl_137;
//    static RelativeLayout rl_138;
//    static RelativeLayout rl_165;
//    static RelativeLayout rl_166;
//    static RelativeLayout rl_167;
    RelativeLayout rl_full;
    RelativeLayout rl_slide;
    YouTubePlayerView youTubePlayerView;
    ImageView img_poster;
    int current_position = 1;
    ImageView img_pre, img_next;
    Search_Media_Model search_media_model;
    SearchMediaAdapter searchMediaAdapter;
    private int Total_Media;
    private int playerType;
    Fragment fragment;
    TextView tv_media;
    ImageView img_search_icon;
    EditText et_search_text;

    static boolean flag = true;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.landingpage_three, container, false);

        et_search_text = (EditText) root.findViewById(R.id.et_search_text);

        sliderDotspanel = root.findViewById(R.id.SliderDots);
        youTubePlayerView = root.findViewById(R.id.video_landing);
        img_poster = root.findViewById(R.id.img_poster);

        img_pre = root.findViewById(R.id.img_pre);
        img_next = root.findViewById(R.id.img_next);
        img_poster = root.findViewById(R.id.img_poster);

        tv_media = root.findViewById(R.id.tv_media);
        tv_media.setOnClickListener(this);

        playerType = 2;
        fragment = this;


        img_dot_one = root.findViewById(R.id.img_dot_one);
        img_dot_two = root.findViewById(R.id.img_dot_two);

        img_pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                --current_position;
                loadVideoData();
            }
        });
        img_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++current_position;
                loadVideoData();
            }
        });

        loadVideoData();


        final ViewPager viewPager = root.findViewById(R.id.dash_pager);
        final DashPagerAdapter adapter = new DashPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.e("SELECTEDDOT", String.valueOf(position));
                if (position == 0){
                img_dot_one.setImageResource(R.drawable.viewpager_dot);
                img_dot_two.setImageResource(R.drawable.viewpager_nondot);
                }else {
                    img_dot_one.setImageResource(R.drawable.viewpager_nondot);
                    img_dot_two.setImageResource(R.drawable.viewpager_dot);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


//        rl_slide = root.findViewById(R.id.rl_slide);
//        ly_over = root.findViewById(R.id.ly_over);
//
//        rl_107 = (RelativeLayout) root.findViewById(R.id.rl_107);
//        rl_9 = (RelativeLayout) root.findViewById(R.id.rl_9);
//        rl_18 = (RelativeLayout) root.findViewById(R.id.rl_18);
//        rl_27 = (RelativeLayout) root.findViewById(R.id.rl_27);
//        rl_42 = (RelativeLayout) root.findViewById(R.id.rl_42);
//        rl_81 = (RelativeLayout) root.findViewById(R.id.rl_81);
//        rl_84 = (RelativeLayout) root.findViewById(R.id.rl_84);
//        rl_96 = (RelativeLayout) root.findViewById(R.id.rl_96);
//        rl_119 = (RelativeLayout) root.findViewById(R.id.rl_119);
//        rl_129 = (RelativeLayout) root.findViewById(R.id.rl_129);
//        rl_131 = (RelativeLayout) root.findViewById(R.id.rl_131);
//        rl_135 = (RelativeLayout) root.findViewById(R.id.rl_135);
//        rl_136 = (RelativeLayout) root.findViewById(R.id.rl_136);
//        rl_137 = (RelativeLayout) root.findViewById(R.id.rl_137);
//        rl_138 = (RelativeLayout) root.findViewById(R.id.rl_138);
//        rl_165 = (RelativeLayout) root.findViewById(R.id.rl_165);
//        rl_166 = (RelativeLayout) root.findViewById(R.id.rl_166);
//        rl_167 = (RelativeLayout) root.findViewById(R.id.rl_167);
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
        over_media = root.findViewById(R.id.over_media);
        over_media.setOnClickListener(this);

        rl_full = (RelativeLayout) root.findViewById(R.id.rl_full);
        rl_full.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });
        img_search_icon = (ImageView) root.findViewById(R.id.img_search_icon);
        img_search_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_search_text.getText().toString().equals("")){
                    InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    Snackbar.make(v, "Search key is empty.", Snackbar.LENGTH_LONG)
                            .show();
                }else {
                    InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    mBuffer.To_where = "Search";
                    mBuffer.Search_key = et_search_text.getText().toString();
                    startActivity(new Intent(getActivity(), DashboardActivity.class));
                    getActivity().finish();
                    Methods.showProgress(getContext());
                }
            }
        });

//        loadcategory();

        recyclerViewClickListener = new RecyclerViewClickListener() {
            @Override
            public void recyclerViewListClicked(View v, int position) {
                Log.e("what", "is this");
            }
        };
        swipeTabAdapter = new Swipe_Tab_Adapter(getContext(), recyclerViewClickListener);

//        ly_one = root.findViewById(R.id.ly_one);
//        ly_two = root.findViewById(R.id.ly_two);

        animationslide_l2r = AnimationUtils.loadAnimation(getContext(), R.anim.slide_right_left);
        animationslide_r2l = AnimationUtils.loadAnimation(getContext(), R.anim.slide_left_right);


//        onSwipeTouchListener = new OnSwipeTouchListener(getContext(), root.findViewById(R.id.ly_over));

        return root;
    }
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private ImageLoader imageLoader = new ImageLoader() {
        @Override
        public void loadImage(@NonNull ImageView imageView, @NonNull String url, int height, int width) {
            Picasso.with(imageView.getContext()).load(url).resize(width, height).centerCrop().into(imageView);
        }
    };

    private void loadVideoData() {
//        Log.e("current", String.valueOf(current_position));
        RequestQueue queue = Volley.newRequestQueue(getContext());

        StringRequest sr = new StringRequest(Request.Method.GET, Constant.URL+Constant.API_MEDIA + "?page=" + current_position +"&per_page=" + 1, new Response.Listener<String>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(String response) {
                Methods.closeProgress();
                Gson gson = new Gson();
                search_media_model = gson.fromJson(response, Search_Media_Model.class);
                List<Search_Media_entities_Model> results = search_media_model.getEntities();

                Total_Media = search_media_model.getTotal();


                String videoUrl = results.get(0).geturl();
                if (results.get(0).getcontent_type().equals("VIDEO")){
                    youTubePlayerView.setVisibility(View.VISIBLE);
                    img_poster.setVisibility(View.GONE);
                    if (videoUrl.contains("youtube")){
//                    Log.e("Video_url", videoUrl);
                    String videoId = videoUrl.substring(videoUrl.length()-11);

                    youTubePlayerView.initPlayer(Constant.API_KEY, videoId, "https://cdn.rawgit.com/flipkart-incubator/inline-youtube-view/60bae1a1/youtube-android/youtube_iframe_player.html", playerType, null, fragment, imageLoader);

                    mBuffer.selected_media = videoId;

                    }else if(videoUrl.contains("vimeo")){
                    }
                    mBuffer.selected_media_type = "VIDEO";
                }else if(results.get(0).getcontent_type().equals("POSTER")){
                    youTubePlayerView.setVisibility(View.GONE);
                    img_poster.setVisibility(View.VISIBLE);
                    Picasso.with(img_poster.getContext()).load(videoUrl).fit().into(img_poster);
                    mBuffer.selected_media_type = "POSTER";
                    mBuffer.selected_media = videoUrl;
                }
//                startActivity(new Intent(getContext(), WebViewActivity.class));
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_poster:
                startActivity(new Intent(getContext(), WebViewActivity.class));
                Log.e("TAG", "img poster");
                break;
            case R.id.video_landing:
                startActivity(new Intent(getContext(), WebViewActivity.class));
                Log.e("TAG", "video_landing");
                break;
            case R.id.over_media:
                startActivity(new Intent(getContext(), WebViewActivity.class));
                Log.e("TAG", "over_media");
                break;

        }
//        switch (v.getId()){
//            case R.id.rl_107:
//                if (flag){
//                    mBuffer.service_category_ids = 107;
//                    mBuffer.service_category_name = "Assistance";
//                    mBuffer.selectedItem = 0;
//                    mBuffer.To_where = "Home";
//                    startActivity(new Intent(getActivity(), DashboardActivity.class));
//                    getActivity().finish();
//                    Methods.showProgress(getContext());
//                }
//                break;
//            case R.id.rl_9:
//                if (flag){
//                    mBuffer.service_category_ids = 9;
//                    mBuffer.service_category_name = "Education";
//                    mBuffer.selectedItem = 3;
//                    mBuffer.To_where = "Home";
//                    startActivity(new Intent(getActivity(), DashboardActivity.class));
//                    getActivity().finish();
//                    Methods.showProgress(getContext());
//                }
//
//                break;
//            case R.id.rl_18:
//                if (flag){
//                    mBuffer.service_category_ids = 18;
//                    mBuffer.service_category_name = "Health Care";
//                    mBuffer.selectedItem = 4;
//                    mBuffer.To_where = "Home";
//                    startActivity(new Intent(getActivity(), DashboardActivity.class));
//                    getActivity().finish();
//                }
//                break;
//            case R.id.rl_27:
//                if (flag){
//                    mBuffer.service_category_ids = 27;
//                    mBuffer.service_category_name = "Protection";
//                    mBuffer.selectedItem = 8;
//                    mBuffer.To_where = "Home";
//                    startActivity(new Intent(getActivity(), DashboardActivity.class));
//                    getActivity().finish();
//                }
//                break;
//            case R.id.rl_42:
//                if (flag){
//                    mBuffer.service_category_ids = 42;
//                    mBuffer.service_category_name = "Refugee Status Determination";
//                    mBuffer.selectedItem = 9;
//                    mBuffer.To_where = "Home";
//                    startActivity(new Intent(getActivity(), DashboardActivity.class));
//                    getActivity().finish();
//                }
//                break;
//            case R.id.rl_81:
//                if (flag){
//                    mBuffer.service_category_ids = 81;
//                    mBuffer.service_category_name = "Resettlement";
//                    mBuffer.selectedItem = 12;
//                    mBuffer.To_where = "Home";
//                    startActivity(new Intent(getActivity(), DashboardActivity.class));
//                    getActivity().finish();
//                }
//                break;
//            case R.id.rl_84:
//                if (flag){
//                    mBuffer.service_category_ids = 84;
//                    mBuffer.service_category_name = "Livelihoods";
//                    mBuffer.selectedItem = 7;
//                    mBuffer.To_where = "Home";
//                    startActivity(new Intent(getActivity(), DashboardActivity.class));
//                    getActivity().finish();
//                }
//                break;
//            case R.id.rl_96:
//                if (flag){
//                    mBuffer.service_category_ids = 96;
//                    mBuffer.service_category_name = "How to Contact UNHCR";
//                    mBuffer.selectedItem = 5;
//                    mBuffer.To_where = "Home";
//                    startActivity(new Intent(getActivity(), DashboardActivity.class));
//                    getActivity().finish();
//                }
//                break;
//            case R.id.rl_119:
//                if (flag){
//                    mBuffer.service_category_ids = 119;
//                    mBuffer.service_category_name = "Residency";
//                    mBuffer.selectedItem = 13;
//                    mBuffer.To_where = "Home";
//                    startActivity(new Intent(getActivity(), DashboardActivity.class));
//                    getActivity().finish();
//                }
//                break;
//            case R.id.rl_129:
//                if (flag){
//                    mBuffer.service_category_ids = 129;
//                    mBuffer.service_category_name = "Legal Aid";
//                    mBuffer.selectedItem = 6;
//                    mBuffer.To_where = "Home";
//                    startActivity(new Intent(getActivity(), DashboardActivity.class));
//                    getActivity().finish();
//                }
//                break;
////                second page
//            case R.id.rl_131:
//                if (!flag){
//                    mBuffer.service_category_ids = 131;
//                    mBuffer.service_category_name = "Child Protection";
//                    mBuffer.selectedItem = 1;
//                    mBuffer.To_where = "Home";
//                    startActivity(new Intent(getActivity(), DashboardActivity.class));
//                    getActivity().finish();
//                }
//                break;
//            case R.id.rl_135:
//                if (!flag){
//                    mBuffer.service_category_ids = 135;
//                    mBuffer.service_category_name = "SGBV";
//                    mBuffer.selectedItem = 14;
//                    mBuffer.To_where = "Home";
//                    startActivity(new Intent(getActivity(), DashboardActivity.class));
//                    getActivity().finish();
//                }
//
//                break;
//            case R.id.rl_136:
//                if (!flag){
//                    mBuffer.service_category_ids = 136;
//                    mBuffer.service_category_name = "Community-based Protection";
//                    mBuffer.selectedItem = 2;
//                    mBuffer.To_where = "Home";
//                    startActivity(new Intent(getActivity(), DashboardActivity.class));
//                    getActivity().finish();
//                }
//
//                break;
//            case R.id.rl_137:
//                if (!flag){
//                    mBuffer.service_category_ids = 137;
//                    mBuffer.service_category_name = "Registration";
//                    mBuffer.selectedItem = 10;
//                    mBuffer.To_where = "Home";
//                    startActivity(new Intent(getActivity(), DashboardActivity.class));
//                    getActivity().finish();
//                }
//
//                break;
//            case R.id.rl_138:
//                if (!flag){
//                    mBuffer.service_category_ids = 138;
//                    mBuffer.service_category_name = "Reporting Fraud and Corruption";
//                    mBuffer.selectedItem = 11;
//                    mBuffer.To_where = "Home";
//                    startActivity(new Intent(getActivity(), DashboardActivity.class));
//                    getActivity().finish();
//                }
//
//                break;
//            case R.id.rl_165:
//                if (!flag){
//                    mBuffer.service_category_ids = 165;
//                    mBuffer.service_category_name = "Irregular Movements";
//                    mBuffer.selectedItem = 16;
//                    mBuffer.To_where = "Home";
//                    startActivity(new Intent(getActivity(), DashboardActivity.class));
//                    getActivity().finish();
//                }
//
//                break;
//            case R.id.rl_166:
//                if (!flag){
//                    mBuffer.service_category_ids = 166;
//                    mBuffer.service_category_name = "Telling the Real Story";
//                    mBuffer.selectedItem = 17;
//                    mBuffer.To_where = "Home";
//                    startActivity(new Intent(getActivity(), DashboardActivity.class));
//                    getActivity().finish();
//                }
//
//                break;
//            case R.id.rl_167:
//                if (!flag){
//                    mBuffer.service_category_ids = 167;
//                    mBuffer.service_category_name = "Covid-19";
//                    mBuffer.selectedItem = 15;
//                    mBuffer.To_where = "Home";
//                    startActivity(new Intent(getActivity(), DashboardActivity.class));
//                    getActivity().finish();
//                }
//                break;
//            case R.id.tv_media:
//                mBuffer.To_where = "Media";
//                startActivity(new Intent(getActivity(), DashboardActivity.class));
//                getActivity().finish();
//
//        }


//        Constant.SELECTED_CATEGORY = mBuffer.service_category_name;
//        Constant.SELECTED_CATEGORY_ID = mBuffer.service_category_ids;
    }

//    public static class OnSwipeTouchListener implements View.OnTouchListener {
//
//        private final GestureDetector gestureDetector;
//        Context context;
//        OnSwipeTouchListener(Context ctx, View mainView) {
//            gestureDetector = new GestureDetector(ctx, new OnSwipeTouchListener.GestureListener());
//            mainView.setOnTouchListener(this);
//            context = ctx;
//        }
//
//        @Override
//        public boolean onTouch(View v, MotionEvent event) {
//            return gestureDetector.onTouchEvent(event);
//        }
//
//        public class GestureListener extends
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
////                            onSwipeBottom();
//                        } else {
////                            onSwipeTop();
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
//            if (flag){
//                ly_one.startAnimation(animationslide_r2l);
//                ly_one.setVisibility(View.GONE);
//                ly_two.startAnimation(animationslide_l2r);
//                ly_two.setVisibility(View.VISIBLE);
//                img_dot_one.setImageResource(R.drawable.viewpager_nondot);
//                img_dot_two.setImageResource(R.drawable.viewpager_dot);
//                flag = false;
//                this.onSwipe.swipeRight();
//
//            }else {
//
//            }
//        }
//        void onSwipeLeft() {
//            if (!flag){
//                ly_one.startAnimation(animationslide_l2r);
//                ly_two.setVisibility(View.GONE);
//                ly_one.setVisibility(View.VISIBLE);
//                ly_two.startAnimation(animationslide_r2l);
//                img_dot_one.setImageResource(R.drawable.viewpager_dot);
//                img_dot_two.setImageResource(R.drawable.viewpager_nondot);
//                flag = true;
//                this.onSwipe.swipeLeft();
//
//            }else {
//
//            }
//        }
////        void onSwipeTop() {
////            Toast.makeText(context, "Swiped Up", Toast.LENGTH_SHORT).show();
////            this.onSwipe.swipeTop();
////        }
////        void onSwipeBottom() {
////            Toast.makeText(context, "Swiped Down", Toast.LENGTH_SHORT).show();
////            this.onSwipe.swipeBottom();
////        }
//        interface onSwipeListener {
//            void swipeRight();
////            void swipeTop();
////            void swipeBottom();
//            void swipeLeft();
//        }
//        OnSwipeTouchListener.onSwipeListener onSwipe;
//    }

//    private void loadcategory() {
//        RequestQueue queue = Volley.newRequestQueue(getContext());
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
//    }
}
