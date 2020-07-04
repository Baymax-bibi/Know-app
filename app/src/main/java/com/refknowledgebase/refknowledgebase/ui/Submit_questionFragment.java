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

    String [] categories = {"Category", "Category 1", "Category 2", "Category 3", "Category 4", "Category 5"};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_submit_question, container, false);
        spin_submit_question_category = root.findViewById(R.id.spin_submit_question_category);
        ArrayAdapter<String> adapter_category = new ArrayAdapter<String>(getContext(), R.layout.spinner_item, categories)
        {
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    return false;
                }
                else
                {
                    return true;
                }
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

