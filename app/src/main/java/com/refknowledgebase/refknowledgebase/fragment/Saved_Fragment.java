package com.refknowledgebase.refknowledgebase.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.refknowledgebase.refknowledgebase.R;
import com.refknowledgebase.refknowledgebase.home_saved.Saved_article_Fragment;
import com.refknowledgebase.refknowledgebase.home_saved.Saved_contact_Fragment;
import com.refknowledgebase.refknowledgebase.home_saved.Saved_media_Fragment;

public class Saved_Fragment extends Fragment implements View.OnClickListener{

    TextView tv_saved_article, tv_saved_media, tv_saved_contact;
    Fragment fragment;
    ImageView article_under, media_under, contact_under;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_saved, container, false);

        article_under = root.findViewById(R.id.article_under);
        media_under = root.findViewById(R.id.media_under);
        contact_under = root.findViewById(R.id.contact_under);

        tv_saved_article = root.findViewById(R.id.tv_saved_article);
        tv_saved_media = root.findViewById(R.id.tv_saved_media);
        tv_saved_contact = root.findViewById(R.id.tv_saved_contact);

        tv_saved_article.setOnClickListener(this);
        tv_saved_media.setOnClickListener(this);
        tv_saved_contact.setOnClickListener(this);

        return root;
    }
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        fragment = new Saved_article_Fragment();
        loadFragment(fragment);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_saved_article:
                fragment = new Saved_article_Fragment();
                tv_saved_article.setTextColor(getResources().getColor(R.color.login_bg));
                tv_saved_media.setTextColor(getResources().getColor(R.color.light_nav));
                tv_saved_contact.setTextColor(getResources().getColor(R.color.light_nav));
                article_under.setVisibility(View.VISIBLE);
                media_under.setVisibility(View.GONE);
                contact_under.setVisibility(View.GONE);
                break;
            case R.id.tv_saved_media:
                fragment = new Saved_media_Fragment();
                tv_saved_article.setTextColor(getResources().getColor(R.color.light_nav));
                tv_saved_media.setTextColor(getResources().getColor(R.color.login_bg));
                tv_saved_contact.setTextColor(getResources().getColor(R.color.light_nav));
                article_under.setVisibility(View.GONE);
                media_under.setVisibility(View.VISIBLE);
                contact_under.setVisibility(View.GONE);
                break;
            case R.id.tv_saved_contact:
                fragment = new Saved_contact_Fragment();
                tv_saved_article.setTextColor(getResources().getColor(R.color.light_nav));
                tv_saved_media.setTextColor(getResources().getColor(R.color.light_nav));
                tv_saved_contact.setTextColor(getResources().getColor(R.color.login_bg));
                article_under.setVisibility(View.GONE);
                media_under.setVisibility(View.GONE);
                contact_under.setVisibility(View.VISIBLE);
                break;
        }
        loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment){
        if (fragment != null){
            getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_saved, fragment)
                .commit();
            return true;
        }
        return false;
    }
}