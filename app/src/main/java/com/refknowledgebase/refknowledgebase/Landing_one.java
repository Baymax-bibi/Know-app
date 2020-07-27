package com.refknowledgebase.refknowledgebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;

import com.refknowledgebase.refknowledgebase.buffer.mBuffer;
import com.refknowledgebase.refknowledgebase.utils.Methods;

public class Landing_one extends Fragment implements View.OnClickListener {

        private static RelativeLayout rl_107;
    private static RelativeLayout rl_9;
    static RelativeLayout rl_18;
    static RelativeLayout rl_27;
    static RelativeLayout rl_42;
    static RelativeLayout rl_81;
    static RelativeLayout rl_84;
    static RelativeLayout rl_96;
    static RelativeLayout rl_119;
    static RelativeLayout rl_129;

    ImageView img_assistance, img_education, img_health_care, img_protection, img_refugee_status_determination, img_resettlement, img_livelihoods, img_how_to_contact_unhcr, img_residency, img_legal_aid;
    static boolean flag = true;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root = inflater.inflate(R.layout.fragment_landing_one, container, false);


        rl_107 = (RelativeLayout) root.findViewById(R.id.rl_107);
        rl_9 = (RelativeLayout) root.findViewById(R.id.rl_9);
        rl_18 = (RelativeLayout) root.findViewById(R.id.rl_18);
        rl_27 = (RelativeLayout) root.findViewById(R.id.rl_27);
        rl_42 = (RelativeLayout) root.findViewById(R.id.rl_42);
        rl_81 = (RelativeLayout) root.findViewById(R.id.rl_81);
        rl_84 = (RelativeLayout) root.findViewById(R.id.rl_84);
        rl_96 = (RelativeLayout) root.findViewById(R.id.rl_96);
        rl_119 = (RelativeLayout) root.findViewById(R.id.rl_119);
        rl_129 = (RelativeLayout) root.findViewById(R.id.rl_129);

        rl_107.setOnClickListener(this);
        rl_9.setOnClickListener(this);
        rl_18.setOnClickListener(this);
        rl_27.setOnClickListener(this);
        rl_42.setOnClickListener(this);
        rl_81.setOnClickListener(this);
        rl_84.setOnClickListener(this);
        rl_96.setOnClickListener(this);
        rl_119.setOnClickListener(this);
        rl_129.setOnClickListener(this);

        img_assistance = root.findViewById(R.id.img_assistance);
        img_education = root.findViewById(R.id.img_education);
        img_health_care = root.findViewById(R.id.img_health_care);
        img_protection = root.findViewById(R.id.img_protection);
        img_refugee_status_determination = root.findViewById(R.id.img_refugee_status_determination);
        img_resettlement = root.findViewById(R.id.img_resettlement);
        img_livelihoods = root.findViewById(R.id.img_livelihoods);
        img_how_to_contact_unhcr = root.findViewById(R.id.img_how_to_contact_unhcr);
        img_residency = root.findViewById(R.id.img_residency);
        img_legal_aid = root.findViewById(R.id.img_legal_aid);

        init();

        return root;
    }

    private void init() {
        switch (mBuffer.service_category_ids){
                case 9:
                    img_education.setImageDrawable(getResources().getDrawable(R.drawable.circle_education_fire));
                    break;
                case 18:
                    img_health_care.setImageDrawable(getResources().getDrawable(R.drawable.circle_health_care_fire));
                    break;
                case 27:
                    img_protection.setImageDrawable(getResources().getDrawable(R.drawable.circle_protection_fire));
                    break;
                case 42:
                    img_refugee_status_determination.setImageDrawable(getResources().getDrawable(R.drawable.circle_refugee_status_determination_fire));
                    break;
                case 81:
                    img_resettlement.setImageDrawable(getResources().getDrawable(R.drawable.circle_resettlement_fire));
                    break;
                case 84:
                    img_livelihoods.setImageDrawable(getResources().getDrawable(R.drawable.circle_livelihoods_fire));
                    break;
                case 96:
                    img_how_to_contact_unhcr.setImageDrawable(getResources().getDrawable(R.drawable.circle_how_to_contact_unhcr_fire));
                    break;
                case 107:
                    img_assistance.setImageDrawable(getResources().getDrawable(R.drawable.circle_support_fire));
                    break;
                case 119:
                    img_residency.setImageDrawable(getResources().getDrawable(R.drawable.circle_residency_fire));
                    break;
                case 129:
                    img_legal_aid.setImageDrawable(getResources().getDrawable(R.drawable.circle_legal_aid_fire));
                    break;
            }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_107:
                if (flag){
                    mBuffer.service_category_ids = 107;
                    mBuffer.service_category_name = "Assistance";
                    mBuffer.selectedItem = 0;
                    mBuffer.To_where = "Home";
                    startActivity(new Intent(getActivity(), DashboardActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                    getActivity().finish();
                    Methods.showProgress(getContext());
                }
                break;
            case R.id.rl_9:
                if (flag){
                    mBuffer.service_category_ids = 9;
                    mBuffer.service_category_name = "Education";
                    mBuffer.selectedItem = 3;
                    mBuffer.To_where = "Home";
                    startActivity(new Intent(getActivity(), DashboardActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                    getActivity().finish();
                    Methods.showProgress(getContext());
                }

                break;
            case R.id.rl_18:
                if (flag){
                    mBuffer.service_category_ids = 18;
                    mBuffer.service_category_name = "Health Care";
                    mBuffer.selectedItem = 4;
                    mBuffer.To_where = "Home";
                    startActivity(new Intent(getActivity(), DashboardActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                    getActivity().finish();
                    Methods.showProgress(getContext());
                }
                break;
            case R.id.rl_27:
                if (flag){
                    mBuffer.service_category_ids = 27;
                    mBuffer.service_category_name = "Protection";
                    mBuffer.selectedItem = 8;
                    mBuffer.To_where = "Home";
                    startActivity(new Intent(getActivity(), DashboardActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                    getActivity().finish();
                }
                break;
            case R.id.rl_42:
                if (flag){
                    mBuffer.service_category_ids = 42;
                    mBuffer.service_category_name = "Refugee Status Determination";
                    mBuffer.selectedItem = 9;
                    mBuffer.To_where = "Home";
                    startActivity(new Intent(getActivity(), DashboardActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                    getActivity().finish();
                }
                break;
            case R.id.rl_81:
                if (flag){
                    mBuffer.service_category_ids = 81;
                    mBuffer.service_category_name = "Resettlement";
                    mBuffer.selectedItem = 12;
                    mBuffer.To_where = "Home";
                    startActivity(new Intent(getActivity(), DashboardActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                    getActivity().finish();
                }
                break;
            case R.id.rl_84:
                if (flag){
                    mBuffer.service_category_ids = 84;
                    mBuffer.service_category_name = "Livelihoods";
                    mBuffer.selectedItem = 7;
                    mBuffer.To_where = "Home";
                    startActivity(new Intent(getActivity(), DashboardActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                    getActivity().finish();
                }
                break;
            case R.id.rl_96:
                if (flag){
                    mBuffer.service_category_ids = 96;
                    mBuffer.service_category_name = "How to Contact UNHCR";
                    mBuffer.selectedItem = 5;
                    mBuffer.To_where = "Home";
                    startActivity(new Intent(getActivity(), DashboardActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                    getActivity().finish();
                }
                break;
            case R.id.rl_119:
                if (flag){
                    mBuffer.service_category_ids = 119;
                    mBuffer.service_category_name = "Residency";
                    mBuffer.selectedItem = 13;
                    mBuffer.To_where = "Home";
                    startActivity(new Intent(getActivity(), DashboardActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                    getActivity().finish();
                }
                break;
            case R.id.rl_129:
                if (flag){
                    mBuffer.service_category_ids = 129;
                    mBuffer.service_category_name = "Legal Aid";
                    mBuffer.selectedItem = 6;
                    mBuffer.To_where = "Home";
                    startActivity(new Intent(getActivity(), DashboardActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                    getActivity().finish();
                }
                break;
        }
    }
}
