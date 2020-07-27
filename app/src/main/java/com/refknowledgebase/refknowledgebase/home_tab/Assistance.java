package com.refknowledgebase.refknowledgebase.home_tab;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.refknowledgebase.refknowledgebase.R;

public class Assistance extends Fragment {

    RecyclerView recyclerView_home_assistance;
    LinearLayoutManager layoutManager;
    Assistance_model[] myListData = new Assistance_model[] {
            new Assistance_model("Am I eligible for a cash grant?", R.drawable.migrate),
            new Assistance_model("Info", android.R.drawable.ic_dialog_info),
            new Assistance_model("Delete", android.R.drawable.ic_delete),
            new Assistance_model("Dialer", android.R.drawable.ic_dialog_dialer),
            new Assistance_model("Alert", android.R.drawable.ic_dialog_alert),
            new Assistance_model("Map", android.R.drawable.ic_dialog_map),
            new Assistance_model("Email", android.R.drawable.ic_dialog_email),
            new Assistance_model("Info", android.R.drawable.ic_dialog_info),
            new Assistance_model("Delete", android.R.drawable.ic_delete),
            new Assistance_model("Dialer", android.R.drawable.ic_dialog_dialer),
            new Assistance_model("Alert", android.R.drawable.ic_dialog_alert),
            new Assistance_model("Map", android.R.drawable.ic_dialog_map),
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_assistance, container, false);
    }
    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView_home_assistance = view.findViewById(R.id.recyclerView_home_assistance);
        recyclerView_home_assistance.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView_home_assistance.setLayoutManager(layoutManager);
        recyclerView_home_assistance.setItemAnimator(new DefaultItemAnimator());

        Assistance_adapter adapter = new Assistance_adapter(myListData);
        recyclerView_home_assistance.setAdapter(adapter);

        recyclerView_home_assistance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemPosition = recyclerView_home_assistance.getChildLayoutPosition(v);
                String item = myListData[itemPosition].getDescription();
            }
        });


    }
}
