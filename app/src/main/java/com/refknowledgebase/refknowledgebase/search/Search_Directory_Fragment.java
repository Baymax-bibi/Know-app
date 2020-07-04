package com.refknowledgebase.refknowledgebase.search;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.refknowledgebase.refknowledgebase.R;
import com.refknowledgebase.refknowledgebase.utils.Constant;

import static com.refknowledgebase.refknowledgebase.utils.Constant.countries;
import static com.refknowledgebase.refknowledgebase.utils.Constant.radius;

public class Search_Directory_Fragment  extends Fragment implements View.OnClickListener{
    RecyclerView recyclerView_search_directory;
    LinearLayout ly_search_direcotry_radius, ly_search_direcotry_country;
    LinearLayoutManager layoutManager;
    TextView tv_search_directory_radius, tv_search_directory_country;

    int country_id = 0;

    Search_Directory_model[] myListData = new Search_Directory_model[] {
            new Search_Directory_model("CARITAS", "Association.", "1 Mahmoud Seduky, Shubra"),
            new Search_Directory_model("CARITAS", "Association.", "1 Mahmoud Seduky, Shubra"),
            new Search_Directory_model("CARITAS", "Association.", "1 Mahmoud Seduky, Shubra"),
            new Search_Directory_model("CARITAS", "Association.", "1 Mahmoud Seduky, Shubra"),
            new Search_Directory_model("CARITAS", "Association.", "1 Mahmoud Seduky, Shubra"),
            new Search_Directory_model("CARITAS", "Association.", "1 Mahmoud Seduky, Shubra"),
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_search_directory, container, false);
        ly_search_direcotry_radius = root.findViewById(R.id.ly_search_direcotry_radius);
        recyclerView_search_directory = root.findViewById(R.id.recyclerView_search_directory);
        tv_search_directory_radius = root.findViewById(R.id.tv_search_directory_radius);
        ly_search_direcotry_country = root.findViewById(R.id.ly_search_direcotry_country);
        tv_search_directory_country = root.findViewById(R.id.tv_search_directory_country);
        return root;
    }
    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView_search_directory.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView_search_directory.setLayoutManager(layoutManager);
        recyclerView_search_directory.setItemAnimator(new DefaultItemAnimator());

        Search_direcotry_adapter adapter = new Search_direcotry_adapter(myListData);
        recyclerView_search_directory.setAdapter(adapter);

        ly_search_direcotry_radius.setOnClickListener(this);
        ly_search_direcotry_country.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        switch (v.getId()){
            case R.id.ly_search_direcotry_country:
                builder.setTitle(getResources().getText(R.string.select_country));
                builder.setItems(countries, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tv_search_directory_country.setText(countries[which]);
                    }
                });
                break;
            case R.id.ly_search_direcotry_radius:
                builder.setTitle(getResources().getText(R.string.select_radius));
                builder.setItems(radius, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tv_search_directory_radius.setText(radius[which] + getResources().getText(R.string.spinner_radius));
                    }
                });
                break;
        }
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}