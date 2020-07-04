package com.refknowledgebase.refknowledgebase.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.refknowledgebase.refknowledgebase.R;

public class FragmentDemo extends Fragment {

    public static final String ARG_OBJECT = "object";
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragmentdemo, container, false);

        Bundle args = getArguments();
        TextView textView = root.findViewById(R.id.textView);
        textView.setText(Integer.toString(args.getInt(ARG_OBJECT)));
        return root;
    }
}
