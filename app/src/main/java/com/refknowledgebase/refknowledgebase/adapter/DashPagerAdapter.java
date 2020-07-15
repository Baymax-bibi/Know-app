package com.refknowledgebase.refknowledgebase.adapter;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.refknowledgebase.refknowledgebase.Landing_one;
import com.refknowledgebase.refknowledgebase.Landing_two;

public class DashPagerAdapter extends FragmentStatePagerAdapter {
    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    int mNoOfTabs;
    public DashPagerAdapter(FragmentManager fm){
        super(fm);
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                Landing_one tab1 =new Landing_one();
                return tab1;
            case 1:
                Landing_two tab2 = new Landing_two();
                return tab2;
            default:
                return  null;
        }
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return 2;
    }

}
