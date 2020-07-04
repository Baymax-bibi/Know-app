package com.refknowledgebase.refknowledgebase.fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.refknowledgebase.refknowledgebase.home_tab.Assistance;
import com.refknowledgebase.refknowledgebase.home_tab.Education;
import com.refknowledgebase.refknowledgebase.home_tab.Health;
import com.refknowledgebase.refknowledgebase.home_tab.Legal;

public class HomepageAdapter extends FragmentStatePagerAdapter {
    public HomepageAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                Assistance tab1 =new Assistance();
                return tab1;
            case 1:
                Education tab2 = new Education();
                return tab2;
            case 2:
                Health tab3 = new Health();
                return tab3;
            case 3:
                Legal tab4 = new Legal();
                return tab4;
            default:
                return  null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
