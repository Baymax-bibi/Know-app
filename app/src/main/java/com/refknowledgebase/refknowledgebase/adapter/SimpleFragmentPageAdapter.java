package com.refknowledgebase.refknowledgebase.adapter;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.refknowledgebase.refknowledgebase.fragment.FragmentDemo;

public class SimpleFragmentPageAdapter extends FragmentStateAdapter {

    private String[] tabTitle = new String[] {"Tab_1Tab_1Tab_1","Tab_1Tab_1Tab_1","Tab_3Tab_3Tab_3","Tab_4Tab_4Tab_4","Tab_5Tab_5Tab_5","Tab_6Tab_6Tab_6","Tab_7Tab_7Tab_7","Tab_8Tab_8Tab_8","Tab_7Tab_7Tab_7","Tab_7Tab_7Tab_7","Tab_7Tab_7Tab_7"};

    public SimpleFragmentPageAdapter(Fragment fm){
        super(fm);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = new FragmentDemo();
        Bundle args = new Bundle();
        args.putInt(FragmentDemo.ARG_OBJECT, position+1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getItemCount() {
        return tabTitle.length;
    }
}