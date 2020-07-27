package com.refknowledgebase.refknowledgebase.fragment;

import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.refknowledgebase.refknowledgebase.R;
import com.refknowledgebase.refknowledgebase.buffer.mBuffer;
import com.refknowledgebase.refknowledgebase.directory_tab.List_Fragment;
import com.refknowledgebase.refknowledgebase.directory_tab.Map_Fragment;
import com.refknowledgebase.refknowledgebase.search.Search_Directory_Fragment;
import com.refknowledgebase.refknowledgebase.search.Search_Faq_Fragment;
import com.refknowledgebase.refknowledgebase.search.Search_Media_Fragment;

public class SearchFragment extends Fragment implements View.OnClickListener{

    TextView tv_search_faq, tv_search_media, tv_search_directory;
    Fragment fragment;
    ImageView faq_under, media_under, dir_under;
    RelativeLayout rl_faq, rl_media, rl_dir;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_search, container, false);

        rl_faq = root.findViewById(R.id.rl_faq);
        rl_media = root.findViewById(R.id.rl_media);
        rl_dir = root.findViewById(R.id.rl_dir);

        faq_under = root.findViewById(R.id.faq_under);
        media_under = root.findViewById(R.id.media_under);
        dir_under = root.findViewById(R.id.dir_under);

        tv_search_faq = root.findViewById(R.id.tv_search_faq);
        tv_search_media = root.findViewById(R.id.tv_search_media);
        tv_search_directory = root.findViewById(R.id.tv_search_directory);

        rl_faq.setOnClickListener(this);
        rl_media.setOnClickListener(this);
        rl_dir.setOnClickListener(this);

        LinearLayout rl_full;
        rl_full = (LinearLayout) root.findViewById(R.id.rl_full);
        rl_full.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });
        return root;
    }
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        if (mBuffer.type_search.equals("faq")){
            fragment = new Search_Faq_Fragment();
            tv_search_faq.setTextColor(getResources().getColor(R.color.login_bg));
            tv_search_media.setTextColor(getResources().getColor(R.color.light_nav));
            tv_search_directory.setTextColor(getResources().getColor(R.color.light_nav));
            faq_under.setVisibility(View.VISIBLE);
            media_under.setVisibility(View.GONE);
            dir_under.setVisibility(View.GONE);
        }else if (mBuffer.type_search.equals("media")){
            fragment = new Search_Media_Fragment();
            tv_search_faq.setTextColor(getResources().getColor(R.color.light_nav));
            tv_search_media.setTextColor(getResources().getColor(R.color.login_bg));
            tv_search_directory.setTextColor(getResources().getColor(R.color.light_nav));
            faq_under.setVisibility(View.GONE);
            media_under.setVisibility(View.VISIBLE);
            dir_under.setVisibility(View.GONE);
        }else if (mBuffer.type_search.equals("dir")){
            fragment = new Search_Directory_Fragment();
            tv_search_faq.setTextColor(getResources().getColor(R.color.light_nav));
            tv_search_media.setTextColor(getResources().getColor(R.color.light_nav));
            tv_search_directory.setTextColor(getResources().getColor(R.color.login_bg));
            faq_under.setVisibility(View.GONE);
            media_under.setVisibility(View.GONE);
            dir_under.setVisibility(View.VISIBLE);
        }
        loadFragment(fragment);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_faq:
                fragment = new Search_Faq_Fragment();
                tv_search_faq.setTextColor(getResources().getColor(R.color.login_bg));
                tv_search_media.setTextColor(getResources().getColor(R.color.light_nav));
                tv_search_directory.setTextColor(getResources().getColor(R.color.light_nav));
                faq_under.setVisibility(View.VISIBLE);
                media_under.setVisibility(View.GONE);
                dir_under.setVisibility(View.GONE);
                mBuffer.type_search = "faq";
                break;
            case R.id.rl_media:
                fragment = new Search_Media_Fragment();
                tv_search_faq.setTextColor(getResources().getColor(R.color.light_nav));
                tv_search_media.setTextColor(getResources().getColor(R.color.login_bg));
                tv_search_directory.setTextColor(getResources().getColor(R.color.light_nav));
                faq_under.setVisibility(View.GONE);
                media_under.setVisibility(View.VISIBLE);
                dir_under.setVisibility(View.GONE);
                mBuffer.type_search = "media";
                break;
            case R.id.rl_dir:
                fragment = new Search_Directory_Fragment();
                tv_search_faq.setTextColor(getResources().getColor(R.color.light_nav));
                tv_search_media.setTextColor(getResources().getColor(R.color.light_nav));
                tv_search_directory.setTextColor(getResources().getColor(R.color.login_bg));
                faq_under.setVisibility(View.GONE);
                media_under.setVisibility(View.GONE);
                dir_under.setVisibility(View.VISIBLE);
                mBuffer.type_search = "dir";
                break;
        }
        loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment){
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