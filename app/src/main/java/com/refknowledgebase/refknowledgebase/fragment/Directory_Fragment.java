package com.refknowledgebase.refknowledgebase.fragment;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.refknowledgebase.refknowledgebase.DashboardActivity;
import com.refknowledgebase.refknowledgebase.R;
import com.refknowledgebase.refknowledgebase.buffer.mBuffer;
import com.refknowledgebase.refknowledgebase.directory_tab.List_Fragment;
import com.refknowledgebase.refknowledgebase.directory_tab.Map_Fragment;
import com.refknowledgebase.refknowledgebase.utils.Methods;

public class Directory_Fragment extends Fragment implements View.OnClickListener{

    TextView tv_directory_list, tv_directory_map;

    Fragment fragment;
    ImageView img_map_under, img_list_under;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_directory, container, false);

        Methods.closeProgress();
        tv_directory_list = root.findViewById(R.id.tv_directory_list);
        tv_directory_map = root.findViewById(R.id.tv_directory_map);
        tv_directory_list.setOnClickListener(this);
        tv_directory_map.setOnClickListener(this);
        tv_directory_list.setTextColor(getResources().getColor(R.color.login_bg));
        img_list_under = root.findViewById(R.id.img_list_under);
        img_map_under = root.findViewById(R.id.img_map_under);

        return root;
    }
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        fragment = new List_Fragment();
        loadFragment(fragment);
//        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
//        getContext().startActivity(browserIntent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_directory_list:
                fragment = new List_Fragment();
                tv_directory_list.setTextColor(getResources().getColor(R.color.login_bg));
                tv_directory_map.setTextColor(getResources().getColor(R.color.light_nav));
                img_list_under.setVisibility(View.VISIBLE);
                img_map_under.setVisibility(View.GONE);
                mBuffer.isDirectMAP = false;
                break;
            case R.id.tv_directory_map:
                fragment = new Map_Fragment();
                tv_directory_list.setTextColor(getResources().getColor(R.color.light_nav));
                tv_directory_map.setTextColor(getResources().getColor(R.color.login_bg));
                img_list_under.setVisibility(View.GONE);
                img_map_under.setVisibility(View.VISIBLE);
                mBuffer.isDirectMAP = true;
                break;
        }
        loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment){
        Methods.showProgress(getContext());
        if (fragment != null){
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_directory_content, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}
