package com.refknowledgebase.refknowledgebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.refknowledgebase.refknowledgebase.buffer.mBuffer;

public class Landing_two  extends Fragment implements View.OnClickListener {
    ImageView img_child_protection, img_sgbv, img_community_based_protection, img_registration, img_reporting_fraud_and_corruption, img_care_1, img_benefits_1, img_Covid_19;

    static RelativeLayout rl_131;
    static RelativeLayout rl_135;
    static RelativeLayout rl_136;
    static RelativeLayout rl_137;
    static RelativeLayout rl_138;
    static RelativeLayout rl_165;
    static RelativeLayout rl_166;
    static RelativeLayout rl_167;
    static boolean flag = false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root = inflater.inflate(R.layout.fragment_landing_two, container, false);

        rl_131 = (RelativeLayout) root.findViewById(R.id.rl_131);
        rl_135 = (RelativeLayout) root.findViewById(R.id.rl_135);
        rl_136 = (RelativeLayout) root.findViewById(R.id.rl_136);
        rl_137 = (RelativeLayout) root.findViewById(R.id.rl_137);
        rl_138 = (RelativeLayout) root.findViewById(R.id.rl_138);
        rl_165 = (RelativeLayout) root.findViewById(R.id.rl_165);
        rl_166 = (RelativeLayout) root.findViewById(R.id.rl_166);
        rl_167 = (RelativeLayout) root.findViewById(R.id.rl_167);

        rl_131.setOnClickListener(this);
        rl_135.setOnClickListener(this);
        rl_136.setOnClickListener(this);
        rl_137.setOnClickListener(this);
        rl_138.setOnClickListener(this);
        rl_165.setOnClickListener(this);
        rl_166.setOnClickListener(this);
        rl_167.setOnClickListener(this);

        img_child_protection = root.findViewById(R.id.img_child_protection);
        img_sgbv = root.findViewById(R.id.img_sgbv);
        img_community_based_protection = root.findViewById(R.id.img_community_based_protection);
        img_registration = root.findViewById(R.id.img_registration);
        img_reporting_fraud_and_corruption = root.findViewById(R.id.img_reporting_fraud_and_corruption);
        img_care_1 = root.findViewById(R.id.img_care_1);
        img_benefits_1 = root.findViewById(R.id.img_benefits_1);
        img_Covid_19 = root.findViewById(R.id.img_Covid_19);

        init();

        return root;
    }

    private void init() {
        switch (mBuffer.service_category_ids){
            case 131:
                img_child_protection.setImageDrawable(getResources().getDrawable(R.drawable.circle_protection_fire));
                break;
            case 135:
                img_sgbv.setImageDrawable(getResources().getDrawable(R.drawable.circle_sgbv_fire));
                break;
            case 136:
                img_community_based_protection.setImageDrawable(getResources().getDrawable(R.drawable.circle_community_based_protection_fire));
                break;
            case 137:
                img_registration.setImageDrawable(getResources().getDrawable(R.drawable.circle_registration_fire));
                break;
            case 138:
                img_reporting_fraud_and_corruption.setImageDrawable(getResources().getDrawable(R.drawable.circle_reporting_fraud_and_corruption_fire));
                break;
            case 165:
                img_care_1.setImageDrawable(getResources().getDrawable(R.drawable.circle_irregular_movements_fire));
                break;
            case 166:
                img_benefits_1.setImageDrawable(getResources().getDrawable(R.drawable.circle_telling_the_real_story_fire));
                break;
            case 167:
                img_Covid_19.setImageDrawable(getResources().getDrawable(R.drawable.circle_covid_19_fire));
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //                second page
            case R.id.rl_131:
                if (!flag){
                    mBuffer.service_category_ids = 131;
                    mBuffer.service_category_name = "Child Protection";
                    mBuffer.selectedItem = 1;
                    mBuffer.To_where = "Home";
                    startActivity(new Intent(getActivity(), DashboardActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                    getActivity().finish();
                }
                break;
            case R.id.rl_135:
                if (!flag){
                    mBuffer.service_category_ids = 135;
                    mBuffer.service_category_name = "SGBV";
                    mBuffer.selectedItem = 14;
                    mBuffer.To_where = "Home";
                    startActivity(new Intent(getActivity(), DashboardActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                    getActivity().finish();
                }

                break;
            case R.id.rl_136:
                if (!flag){
                    mBuffer.service_category_ids = 136;
                    mBuffer.service_category_name = "Community-based Protection";
                    mBuffer.selectedItem = 2;
                    mBuffer.To_where = "Home";
                    startActivity(new Intent(getActivity(), DashboardActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                    getActivity().finish();
                }

                break;
            case R.id.rl_137:
                if (!flag){
                    mBuffer.service_category_ids = 137;
                    mBuffer.service_category_name = "Registration";
                    mBuffer.selectedItem = 10;
                    mBuffer.To_where = "Home";
                    startActivity(new Intent(getActivity(), DashboardActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                    getActivity().finish();
                }

                break;
            case R.id.rl_138:
                if (!flag){
                    mBuffer.service_category_ids = 138;
                    mBuffer.service_category_name = "Reporting Fraud and Corruption";
                    mBuffer.selectedItem = 11;
                    mBuffer.To_where = "Home";
                    startActivity(new Intent(getActivity(), DashboardActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                    getActivity().finish();
                }

                break;
            case R.id.rl_165:
                if (!flag){
                    mBuffer.service_category_ids = 165;
                    mBuffer.service_category_name = "Irregular Movements";
                    mBuffer.selectedItem = 16;
                    mBuffer.To_where = "Home";
                    startActivity(new Intent(getActivity(), DashboardActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                    getActivity().finish();
                }

                break;
            case R.id.rl_166:
                if (!flag){
                    mBuffer.service_category_ids = 166;
                    mBuffer.service_category_name = "Telling the Real Story";
                    mBuffer.selectedItem = 17;
                    mBuffer.To_where = "Home";
                    startActivity(new Intent(getActivity(), DashboardActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                    getActivity().finish();
                }

                break;
            case R.id.rl_167:
                if (!flag){
                    mBuffer.service_category_ids = 167;
                    mBuffer.service_category_name = "Covid-19";
                    mBuffer.selectedItem = 15;
                    mBuffer.To_where = "Home";
                    startActivity(new Intent(getActivity(), DashboardActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                    getActivity().finish();
                }
                break;
        }
    }
}
