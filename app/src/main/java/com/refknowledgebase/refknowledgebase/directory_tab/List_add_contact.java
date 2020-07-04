package com.refknowledgebase.refknowledgebase.directory_tab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import com.refknowledgebase.refknowledgebase.R;

public class List_add_contact extends Fragment implements View.OnClickListener{
    FrameLayout contact_container;
    TextView tv_savecontact;
    Fragment fragment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_list_new_contact, container, false);

        contact_container = root.findViewById(R.id.contact_container);
        tv_savecontact = root.findViewById(R.id.tv_savecontact);
        return root;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tv_savecontact.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_savecontact:
                fragment = new List_Fragment();
                break;
        }
        loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment){
        if (fragment != null){
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    //.replace(R.id.fragment_container, fragment)
                    .replace(R.id.contact_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

}

