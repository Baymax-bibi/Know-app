package com.refknowledgebase.refknowledgebase.ui;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.refknowledgebase.refknowledgebase.R;

public class Submit_questionFragment extends Fragment {
    Spinner spin_submit_question_category;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_submit_question, container, false);

        String [] categories = {
                getContext().getResources().getString(R.string.home_assistance), getContext().getResources().getString(R.string.home_childProtection),
                getContext().getResources().getString(R.string.home_CommunityProtection), getContext().getResources().getString(R.string.home_education),
                getContext().getResources().getString(R.string.home_HealthCare), getContext().getResources().getString(R.string.home_HowUncr),
                getContext().getResources().getString(R.string.home_LegalAid), getContext().getResources().getString(R.string.home_Livelihoods),
                getContext().getResources().getString(R.string.home_Protection), getContext().getResources().getString(R.string.home_Refugee),
                getContext().getResources().getString(R.string.home_Registration), getContext().getResources().getString(R.string.home_Reporting),
                getContext().getResources().getString(R.string.home_Resettlement), getContext().getResources().getString(R.string.home_Residncy),
                getContext().getResources().getString(R.string.home_SGBV), getContext().getResources().getString(R.string.home_Covid),
                getContext().getResources().getString(R.string.home_IrregularMove), getContext().getResources().getString(R.string.home_TellRealStory),
        };

        spin_submit_question_category = root.findViewById(R.id.spin_submit_question_category);
        ArrayAdapter<String> adapter_category = new ArrayAdapter<String>(getContext(), R.layout.spinner_item, categories)
        {
            @Override
            public boolean isEnabled(int position){
                    return true;
            }
            @SuppressLint("ResourceAsColor")
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    tv.setTextColor(R.color.light_blue);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        spin_submit_question_category.setAdapter(adapter_category);

        adapter_category.setDropDownViewResource(R.layout.spinner_item);
        return root;
    }
}