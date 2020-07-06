package com.refknowledgebase.refknowledgebase;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.refknowledgebase.refknowledgebase.buffer.mBuffer;
import com.refknowledgebase.refknowledgebase.fragment.Directory_Fragment;
import com.refknowledgebase.refknowledgebase.fragment.Home_Fragment;
import com.refknowledgebase.refknowledgebase.fragment.Media_Fragment;
import com.refknowledgebase.refknowledgebase.fragment.Notification_Fragment;
import com.refknowledgebase.refknowledgebase.fragment.Saved_Fragment;
import com.refknowledgebase.refknowledgebase.fragment.SearchFragment;
import com.refknowledgebase.refknowledgebase.myinterface.SearchClickListner;
import com.refknowledgebase.refknowledgebase.search.Search_Media_Fragment;
import com.refknowledgebase.refknowledgebase.ui.HelpFragment;
import com.refknowledgebase.refknowledgebase.ui.SettingFragment;
import com.refknowledgebase.refknowledgebase.ui.Submit_questionFragment;
import com.refknowledgebase.refknowledgebase.ui.TSFragment;
import com.refknowledgebase.refknowledgebase.utils.Constant;
import com.refknowledgebase.refknowledgebase.utils.Methods;
import com.squareup.picasso.Picasso;

public class DashboardActivity extends AppCompatActivity  implements View.OnClickListener {

    Fragment fragment;
    LinearLayout  ly_question, ly_setting, ly_help, ly_legal, ly_hide, ly_logout;
    RelativeLayout ly_home_but, ly_directory_but, ly_saved_but, ly_media_but, ly_noti_but, rl_navigation, rl_search_view;
    ImageView img_home, img_directory, img_saved, img_media, img_noti, close_nav, img_search, img_search_icon;
    static ImageView img_hamburger, img_back_assistance;
    TextView tv_home, tv_directory, tv_saved, tv_media, tv_noti, tv_name, et_search_text;
    static TextView tv_dashboard_title;
    static SpannableString spannableString;
    Animation animationsShow;
    boolean doublePressedBackToExit = false;
    CircularImageView img_profile;

    SearchClickListner searchClickListner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ly_logout = findViewById(R.id.ly_logout);
        ly_logout.setOnClickListener(this);

        img_profile = findViewById(R.id.img_avater);
        tv_name = findViewById(R.id.tv_name);

        img_home = findViewById(R.id.img_home);
        img_directory = findViewById(R.id.img_directory);
        img_saved = findViewById(R.id.img_saved);
        img_media = findViewById(R.id.img_media);
        img_noti = findViewById(R.id.img_noti);
        rl_search_view = findViewById(R.id.rl_search_view);
//search view
        img_search = findViewById(R.id.img_search);
        img_search.setOnClickListener(this);

        img_search_icon = findViewById(R.id.img_search_icon);
        img_search_icon.setOnClickListener(this);

        et_search_text = findViewById(R.id.et_search_text);
//        end of search view

        img_hamburger = findViewById(R.id.img_hamburger);
        img_back_assistance = findViewById(R.id.img_back_assistance);
        img_back_assistance.setOnClickListener(this);
        close_nav = findViewById(R.id.close_nav);

        tv_home = findViewById(R.id.tv_home);
        tv_directory = findViewById(R.id.tv_directory);
        tv_saved = findViewById(R.id.tv_saved);
        tv_media = findViewById(R.id.tv_media);
        tv_noti = findViewById(R.id.tv_noti);

        tv_dashboard_title = findViewById(R.id.tv_dashboard_title);

        rl_navigation = findViewById(R.id.rl_navigation);
        ly_question = findViewById(R.id.ly_question);
        ly_setting = findViewById(R.id.ly_setting);
        ly_help = findViewById(R.id.ly_help);
        ly_legal = findViewById(R.id.ly_legal);
        ly_question.setOnClickListener(this);
        ly_setting.setOnClickListener(this);
        ly_help.setOnClickListener(this);
        ly_legal.setOnClickListener(this);

        ly_home_but = findViewById(R.id.ly_home_but);
        ly_directory_but = findViewById(R.id.ly_directory_but);
        ly_saved_but = findViewById(R.id.ly_saved_but);
        ly_media_but = findViewById(R.id.ly_media_but);
        ly_noti_but = findViewById(R.id.ly_noti_but);

        ly_hide = findViewById(R.id.ly_hide);
        ly_hide.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        ly_home_but.setOnClickListener(this);
        ly_directory_but.setOnClickListener(this);
        ly_saved_but.setOnClickListener(this);
        ly_media_but.setOnClickListener(this);
        ly_noti_but.setOnClickListener(this);

        img_hamburger.setOnClickListener(this);
        close_nav.setOnClickListener(this);
//init go to Home_fragment
        if (mBuffer.To_where.equals("Home")){
            fragment = new Home_Fragment();
        }else if (mBuffer.To_where.equals("Media")){
            fragment = new Media_Fragment();
        }else if (mBuffer.To_where.equals("Search")){
            rl_search_view.setVisibility(View.VISIBLE);
            img_search.setVisibility(View.GONE);
            fragment = new SearchFragment();
            et_search_text.setText(mBuffer.Search_key);
        }
        loadFragment(fragment);

        spannableString = new SpannableString(getResources().getText(R.string.dash_refknowledge));
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.light_nav)), 0, 9, 0);
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.fire_color)), 9, spannableString.length(), 0);
        set_title(true, spannableString);

        init();
    }

    private void init() {
//        Toast.makeText(DashboardActivity.this, "AccessType" + getString(Constant.LOGINTYPE), Toast.LENGTH_SHORT).show();
        if (getString(Constant.LOGINTYPE).equals("GOOGLE")){
            String google_photo = getString(Constant.LOGIN_PHOTO);
            String google_name = getString(Constant.LOGIN_NAME);
            Picasso.with(DashboardActivity.this).load(Uri.parse(google_photo)).into(img_profile);
            tv_name.setText(google_name);
        }
        if (getString(Constant.LOGINTYPE).equals("FB")){
            Log.e("AccessToken", "FB");
            Log.e("AccessFBNAME", getString(Constant.LOGIN_FB_NAME));
            Log.e("AccessFBID", getString(Constant.LOGIN_FB_PHOTO));
            Log.e("AccessFBEMAIL", getString(Constant.LOGIN_EMAIL));

//            String fb_photo = getString(Constant.LOGIN_FB_PHOTO);
//            profilePictureView.setProfileId(fb_photo);
            Picasso.with(DashboardActivity.this).load("https://miro.medium.com/max/256/1*d69DKqFDwBZn_23mizMWcQ.png").into(img_profile);
            tv_name.setText(getString(Constant.LOGIN_FB_NAME));
        }

        if (getString(Constant.LOGINTYPE).equals("WITHOUT")){
            img_profile.setImageDrawable(getDrawable(R.drawable.avatar));
            tv_name.setText("");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ly_home_but:
                img_home.setImageDrawable(getDrawable(R.drawable.home));
                img_directory.setImageDrawable(getDrawable(R.drawable.directory));
                img_saved.setImageDrawable(getDrawable(R.drawable.saved));
                img_media.setImageDrawable(getDrawable(R.drawable.media));
                img_noti.setImageDrawable(getDrawable(R.drawable.notification));
                tv_home.setTextColor(getResources().getColor(R.color.txt_home_black));
                tv_directory.setTextColor(getResources().getColor(R.color.txt_home_gray));
                tv_saved.setTextColor(getResources().getColor(R.color.txt_home_gray));
                tv_media.setTextColor(getResources().getColor(R.color.txt_home_gray));
                tv_noti.setTextColor(getResources().getColor(R.color.txt_home_gray));

                spannableString = new SpannableString(getResources().getText(R.string.dash_refknowledge));
                spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.light_nav)), 0, 9, 0);
                spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.fire_color)), 9, spannableString.length(), 0);
                set_title(true,spannableString);

                rl_search_view.setVisibility(View.GONE);
                img_search.setVisibility(View.VISIBLE);
                img_hamburger.setVisibility(View.VISIBLE);
                img_back_assistance.setVisibility(View.GONE);
                img_search.setVisibility(View.VISIBLE);

//                mBuffer.service_category_ids = 107;
//                mBuffer.service_category_name = "Assistance";
                mBuffer.is_search = false;
                startActivity(new Intent(getApplicationContext(), BlankActivity.class));
//                fragment = new Home_Fragment();
//                Fragment fragment = new LandingFragment();
////                loadFragment(fragment);
//                getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.fl_full, fragment)
//                        .commit();
                break;
            case R.id.ly_directory_but:
                img_home.setImageDrawable(getDrawable(R.drawable.img_home));
                img_directory.setImageDrawable(getDrawable(R.drawable.directory_fire));
                img_saved.setImageDrawable(getDrawable(R.drawable.saved));
                img_media.setImageDrawable(getDrawable(R.drawable.media));
                img_noti.setImageDrawable(getDrawable(R.drawable.notification));

                tv_home.setTextColor(getResources().getColor(R.color.txt_home_gray));
                tv_directory.setTextColor(getResources().getColor(R.color.txt_home_black));
                tv_saved.setTextColor(getResources().getColor(R.color.txt_home_gray));
                tv_media.setTextColor(getResources().getColor(R.color.txt_home_gray));
                tv_noti.setTextColor(getResources().getColor(R.color.txt_home_gray));

                spannableString = new SpannableString(getResources().getText(R.string.dash_directory));
                spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.light_nav)), 0, spannableString.length(), 0);
                set_title(false,spannableString);
                fragment = new Directory_Fragment();

                rl_search_view.setVisibility(View.GONE);
                img_search.setVisibility(View.VISIBLE);
                img_hamburger.setVisibility(View.VISIBLE);
                img_back_assistance.setVisibility(View.GONE);
                img_search.setVisibility(View.GONE);
                break;
            case R.id.ly_saved_but:

                img_home.setImageDrawable(getDrawable(R.drawable.img_home));
                img_directory.setImageDrawable(getDrawable(R.drawable.directory));
                img_saved.setImageDrawable(getDrawable(R.drawable.saved_fire));
                img_media.setImageDrawable(getDrawable(R.drawable.media));
                img_noti.setImageDrawable(getDrawable(R.drawable.notification));

                tv_home.setTextColor(getResources().getColor(R.color.txt_home_gray));
                tv_directory.setTextColor(getResources().getColor(R.color.txt_home_gray));
                tv_saved.setTextColor(getResources().getColor(R.color.txt_home_black));
                tv_media.setTextColor(getResources().getColor(R.color.txt_home_gray));
                tv_noti.setTextColor(getResources().getColor(R.color.txt_home_gray));

                spannableString = new SpannableString(getResources().getText(R.string.dash_saved));
                spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.light_nav)), 0, spannableString.length(), 0);
                set_title(false,spannableString);

                rl_search_view.setVisibility(View.GONE);
                img_search.setVisibility(View.VISIBLE);
                img_hamburger.setVisibility(View.VISIBLE);
                img_back_assistance.setVisibility(View.GONE);

                img_search.setVisibility(View.GONE);
                fragment = new Saved_Fragment();
                break;
            case R.id.ly_media_but:

                img_home.setImageDrawable(getDrawable(R.drawable.img_home));
                img_directory.setImageDrawable(getDrawable(R.drawable.directory));
                img_saved.setImageDrawable(getDrawable(R.drawable.saved));
                img_media.setImageDrawable(getDrawable(R.drawable.media_fire));
                img_noti.setImageDrawable(getDrawable(R.drawable.notification));

                tv_home.setTextColor(getResources().getColor(R.color.txt_home_gray));
                tv_directory.setTextColor(getResources().getColor(R.color.txt_home_gray));
                tv_saved.setTextColor(getResources().getColor(R.color.txt_home_gray));
                tv_media.setTextColor(getResources().getColor(R.color.txt_home_black));
                tv_noti.setTextColor(getResources().getColor(R.color.txt_home_gray));

                spannableString = new SpannableString(getResources().getText(R.string.dash_media));
                spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.light_nav)), 0, spannableString.length(), 0);
                set_title(false,spannableString);

                rl_search_view.setVisibility(View.GONE);
                img_search.setVisibility(View.VISIBLE);
                img_hamburger.setVisibility(View.VISIBLE);
                img_back_assistance.setVisibility(View.GONE);
                img_search.setVisibility(View.GONE);

                fragment = new Media_Fragment();
                break;
            case R.id.ly_noti_but:
                img_home.setImageDrawable(getDrawable(R.drawable.img_home));
                img_directory.setImageDrawable(getDrawable(R.drawable.directory));
                img_saved.setImageDrawable(getDrawable(R.drawable.saved));
                img_media.setImageDrawable(getDrawable(R.drawable.media));
                img_noti.setImageDrawable(getDrawable(R.drawable.notification_fire));

                tv_home.setTextColor(getResources().getColor(R.color.txt_home_gray));
                tv_directory.setTextColor(getResources().getColor(R.color.txt_home_gray));
                tv_saved.setTextColor(getResources().getColor(R.color.txt_home_gray));
                tv_media.setTextColor(getResources().getColor(R.color.txt_home_gray));
                tv_noti.setTextColor(getResources().getColor(R.color.txt_home_black));
                img_search.setVisibility(View.GONE);

                spannableString = new SpannableString(getResources().getText(R.string.dash_notification));
                spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.light_nav)), 0, spannableString.length(), 0);
                set_title(false,spannableString);
                fragment = new Notification_Fragment();

                rl_search_view.setVisibility(View.GONE);
                img_search.setVisibility(View.VISIBLE);
                img_hamburger.setVisibility(View.VISIBLE);
                img_back_assistance.setVisibility(View.GONE);
                img_search.setVisibility(View.GONE);

                break;

            case R.id.img_hamburger:
                rl_navigation.setVisibility(View.VISIBLE);
                ly_hide.setVisibility(View.VISIBLE);
                break;
            case R.id.close_nav:
                rl_navigation.setVisibility(View.GONE);
                ly_hide.setVisibility(View.GONE);
                break;
            case R.id.ly_question:
                spannableString = new SpannableString(getResources().getText(R.string.submit_question));
                spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.light_nav)), 0, spannableString.length(), 0);
                set_title(false,spannableString);

//                fragment = new Submit_questionFragment();
//                rl_search_view.setVisibility(View.GONE);
//                img_search.setVisibility(View.VISIBLE);
                rl_navigation.setVisibility(View.GONE);
                ly_hide.setVisibility(View.GONE);
//                img_search.setVisibility(View.GONE);

                img_search.performClick();
                break;
            case R.id.ly_setting:
                spannableString = new SpannableString(getResources().getText(R.string.setting));
                spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.light_nav)), 0, spannableString.length(), 0);
                set_title(false,spannableString);

                fragment = new SettingFragment();

                rl_search_view.setVisibility(View.GONE);
                img_search.setVisibility(View.VISIBLE);
                rl_navigation.setVisibility(View.GONE);
                ly_hide.setVisibility(View.GONE);
                img_search.setVisibility(View.GONE);
                break;
            case R.id.ly_help:
                spannableString = new SpannableString(getResources().getText(R.string.help));
                spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.light_nav)), 0, spannableString.length(), 0);
                set_title(false,spannableString);

                fragment = new HelpFragment();

                rl_search_view.setVisibility(View.GONE);
                img_search.setVisibility(View.VISIBLE);
                rl_navigation.setVisibility(View.GONE);
                ly_hide.setVisibility(View.GONE);
                img_search.setVisibility(View.GONE);
                break;
            case R.id.ly_legal:
                spannableString = new SpannableString(getResources().getText(R.string.ts_and));
                spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.light_nav)), 0, spannableString.length(), 0);
                set_title(false,spannableString);

                fragment = new TSFragment();

                rl_search_view.setVisibility(View.GONE);
                img_search.setVisibility(View.VISIBLE);
                rl_navigation.setVisibility(View.GONE);
                ly_hide.setVisibility(View.GONE);
                img_search.setVisibility(View.GONE);
                break;
            case R.id.img_search:
                rl_search_view.setVisibility(View.VISIBLE);
                img_search.setVisibility(View.GONE);

                et_search_text.setText("");
                mBuffer.is_search = true;
                set_title(false,spannableString);
                fragment = new SearchFragment();
                break;
            case R.id.img_back_assistance:
                spannableString = new SpannableString(getResources().getText(R.string.dash_refknowledge));
                spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.light_nav)), 0, 9, 0);
                spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.fire_color)), 9, spannableString.length(), 0);

                set_title(true, spannableString);
                img_hamburger.setVisibility(View.VISIBLE);
                img_back_assistance.setVisibility(View.GONE);

                startActivity(new Intent(DashboardActivity.this, DashboardActivity.class));
                break;
            case R.id.ly_logout:
//                insertString(Constant.LOGINTYPE, "WITHOUT");
//                SharedPreferences preferences =getSharedPreferences(Constant.PREF,Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = preferences.edit();
//                editor.clear();
//                editor.apply();
//                finish();
                startActivity(new Intent(DashboardActivity.this, Activity_login.class));
                finish();
                break;
            case R.id.img_search_icon:
//                insertString("SEARCH_KEY", et_search_text.getText().toString());
//                Search_Media_Fragment searchMediaFragment = new Search_Media_Fragment();
//                searchMediaFragment.reloading(DashboardActivity.this, et_search_text.getText().toString());
                Log.e("searchkey", "???"+et_search_text.getText().toString());
                if (et_search_text.getText().toString().equals("")){
                    Snackbar.make(v, "Search key is empty.", Snackbar.LENGTH_LONG)
                            .show();
                }else {
                    mBuffer.Search_key = et_search_text.getText().toString();
                    fragment = new SearchFragment();
                }

                break;
        }
        loadFragment(fragment);
    }


    private boolean loadFragment(Fragment fragment){

        if (fragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.nav_host_fragment, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    public void onBackPressed() {
        if (doublePressedBackToExit) {
            System.exit(0);
        }
        else {
            this.doublePressedBackToExit = true;
            Toast.makeText(this, getResources().getText(R.string.exit), Toast.LENGTH_SHORT)
                    .show();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    doublePressedBackToExit = false;
                }
            }, 2000);
        }
    }
    public static void set_title(boolean b, SpannableString string){
        tv_dashboard_title.setText(string);
        if (b){
            tv_dashboard_title.setGravity(Gravity.CENTER);
        }else {
            tv_dashboard_title.setGravity(Gravity.LEFT);
        }
    };
    public static void set_hamburger(){
            img_hamburger.setVisibility(View.GONE);
            img_back_assistance.setVisibility(View.VISIBLE);
    };
    public String getString(String key) {
        SharedPreferences mSharedPreferences = getSharedPreferences(Constant.PREF, MODE_PRIVATE);
        String  selected =  mSharedPreferences.getString(key, "");
        return selected;
    }

    public synchronized void insertString(String key, String value) {
        SharedPreferences mSharedPreferences = getSharedPreferences(Constant.PREF, MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putString(key, value);
        mEditor.apply();
    }
}
