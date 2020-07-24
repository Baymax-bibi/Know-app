package com.refknowledgebase.refknowledgebase;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.google.gson.Gson;
import com.refknowledgebase.refknowledgebase.adapter.DashPagerAdapter;
import com.refknowledgebase.refknowledgebase.adapter.SearchMediaAdapter;
import com.refknowledgebase.refknowledgebase.adapter.Swipe_Tab_Adapter;
import com.refknowledgebase.refknowledgebase.buffer.mBuffer;
import com.refknowledgebase.refknowledgebase.model.Search_Media_Model;
import com.refknowledgebase.refknowledgebase.model.Search_Media_entities_Model;
import com.refknowledgebase.refknowledgebase.model.Swipe_Tab_Model;
import com.refknowledgebase.refknowledgebase.model.Swipe_Tab_entitiesModel;
import com.refknowledgebase.refknowledgebase.myinterface.RecyclerViewClickListener;
import com.refknowledgebase.refknowledgebase.utils.Constant;
import com.refknowledgebase.refknowledgebase.utils.Methods;
import com.refknowledgebase.refknowledgebase.utils.youtubeconfig;
import com.squareup.picasso.Picasso;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.refknowledgebase.refknowledgebase.utils.Constant.DEVELOPER_KEY;

public class LandingFragment extends Fragment implements View.OnClickListener  {
    LinearLayout sliderDotspanel;
    ImageView img_first_screen;
    static Animation animationslide_l2r;
    static Animation animationslide_r2l;
    static ImageView img_dot_one;
    static ImageView img_dot_two;
    RecyclerViewClickListener recyclerViewClickListener;
    RelativeLayout rl_full, rl_video_landing;
    ImageView img_poster;
    int current_position = 1;
    ImageView img_pre, img_next,img_start_video;
    Search_Media_Model search_media_model;
    Fragment fragment;
    TextView tv_media;
    ImageView img_search_icon;
    EditText et_search_text;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.landingpage_three, container, false);

        rl_video_landing = (RelativeLayout) root.findViewById(R.id.rl_video_landing);
        img_first_screen = (ImageView) root.findViewById(R.id.img_first_screen);

        et_search_text = (EditText) root.findViewById(R.id.et_search_text);
        sliderDotspanel = root.findViewById(R.id.SliderDots);

        img_pre = root.findViewById(R.id.img_pre);
        img_next = root.findViewById(R.id.img_next);
        img_poster = root.findViewById(R.id.img_poster);
        img_poster.setOnClickListener(this);

        tv_media = root.findViewById(R.id.tv_media);
        tv_media.setOnClickListener(this);

        img_start_video = root.findViewById(R.id.img_start_video);
        img_start_video.setOnClickListener(this);

        fragment = this;

        img_dot_one = root.findViewById(R.id.img_dot_one);
        img_dot_two = root.findViewById(R.id.img_dot_two);

        img_pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                --current_position;
                loadVideoData();
                Methods.showProgress(getContext());
            }
        });
        img_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++current_position;
                loadVideoData();
                Methods.showProgress(getContext());
            }
        });

        loadVideoData();
        Methods.showProgress(getContext());

        final ViewPager viewPager = root.findViewById(R.id.dash_pager);
        final DashPagerAdapter adapter = new DashPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
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
                    startActivity(new Intent(getActivity(), DashboardActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                    getActivity().finish();
                    Methods.showProgress(getContext());
                }
            }
        });

        recyclerViewClickListener = new RecyclerViewClickListener() {
            @Override
            public void recyclerViewListClicked(View v, int position) {
                Log.e("what", "is this");
            }
        };

        animationslide_l2r = AnimationUtils.loadAnimation(getContext(), R.anim.slide_right_left);
        animationslide_r2l = AnimationUtils.loadAnimation(getContext(), R.anim.slide_left_right);

        return root;
    }
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void loadVideoData() {
        RequestQueue queue = Volley.newRequestQueue(requireContext());
        StringRequest sr = new StringRequest(Request.Method.GET, Constant.URL+Constant.API_MEDIA + "?page=" + current_position +"&per_page=" + 1, new Response.Listener<String>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(String response) {
                Methods.closeProgress();
                Gson gson = new Gson();
                search_media_model = gson.fromJson(response, Search_Media_Model.class);
                List<Search_Media_entities_Model> results = search_media_model.getEntities();

                String videoUrl = results.get(0).geturl();
                videoUrl = videoUrl.replace("http", "https");

                if (results.get(0).getcontent_type().equals("VIDEO")){
                    rl_video_landing.setVisibility(View.VISIBLE);
                    img_poster.setVisibility(View.GONE);
                    if (videoUrl.contains("youtube")){
                    String videoId = videoUrl.substring(videoUrl.length()-11);

                    videoUrl = videoUrl.replace("httpss", "https");

                    Picasso.with(getContext()).load(Uri.parse("https://i4.ytimg.com/vi/"+ videoId+"/0.jpg")).into(img_first_screen);
                    mBuffer.selected_media = videoId;

                    }else if(videoUrl.contains("vimeo")){
                        mBuffer.selected_media = videoUrl;
                    }
                    mBuffer.selected_media_type = "VIDEO";
                }else if(results.get(0).getcontent_type().equals("POSTER")){
                    rl_video_landing.setVisibility(View.GONE);
                    img_poster.setVisibility(View.VISIBLE);

                    Picasso.with(img_poster.getContext()).load(videoUrl).fit().into(img_poster);
                    mBuffer.selected_media_type = "POSTER";
                    mBuffer.selected_media = videoUrl;
                }
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

    @SuppressLint("LongLogTag")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_poster:
                final Dialog dialog = new Dialog(requireContext());
                dialog.setContentView(R.layout.show_image);
                ImageView image = (ImageView) dialog.findViewById(R.id.img_media);
                Picasso.with(getContext()).load(Uri.parse(mBuffer.selected_media)).into(image);
                Button dialogButton = (Button) dialog.findViewById(R.id.btn_close_img);
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
                break;

            case R.id.img_start_video:
                Intent intent = YouTubeStandalonePlayer.createVideoIntent(requireActivity(), DEVELOPER_KEY, mBuffer.selected_media);
                Log.e("activity_developerkey_selected", requireActivity()+" : " + DEVELOPER_KEY + " : " + mBuffer.selected_media);
                startActivity(intent);
                break;
        }
    }
}
