package com.refknowledgebase.refknowledgebase.adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.refknowledgebase.refknowledgebase.R;
import com.refknowledgebase.refknowledgebase.home_tab.Assistance_detail;
import com.refknowledgebase.refknowledgebase.model.Search_Media_Model_temp;

public class RV_Search_MediaAdapter extends RecyclerView.Adapter<RV_Search_MediaAdapter.ViewHolder>{
    private Search_Media_Model_temp[] listdata;
    Context mConext;

    // RecyclerView recyclerView;
    public RV_Search_MediaAdapter(Context _context, Search_Media_Model_temp[] listdata) {
        mConext = _context;
        this.listdata = listdata;
    }
    @Override
    public RV_Search_MediaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.item_search_media, parent, false);
        RV_Search_MediaAdapter.ViewHolder viewHolder = new RV_Search_MediaAdapter.ViewHolder(listItem);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RV_Search_MediaAdapter.ViewHolder holder, final int position) {
        final Search_Media_Model_temp assistancemodel = listdata[position];

        MediaController mediaController= new MediaController(mConext);
        mediaController.setAnchorView(holder.video_search);

        holder.tv_title.setText(listdata[position].getTitle());
        holder.tv_content.setText(listdata[position].getSearch_content());
        holder.video_search.setVideoURI(Uri.parse(listdata[position].getVideoId()));
        holder.video_search.requestFocus();

//        holder.media_start.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                holder.video_search.start();
//                holder.media_start.setVisibility(View.GONE);
//            }
//        });

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Fragment myFragment = new Assistance_detail();
                Log.e("RV_Search_MediaAdapter", ""+position);
//                activity.getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, myFragment).addToBackStack(null).commit();
            }
        });

    }


    @Override
    public int getItemCount() {
        return listdata.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public VideoView video_search;
        public TextView tv_title;
        public TextView tv_content;
//        public ImageView media_start;
        public RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);
//            this.media_start = (ImageView) itemView.findViewById(R.id.media_start);
            this.video_search = (VideoView) itemView.findViewById(R.id.video_search);
            this.tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            this.tv_content = (TextView) itemView.findViewById(R.id.tv_content);
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relativeLayout);
        }
    }
//    public synchronized void insertString(String key, String value) {
//        SharedPreferences mSharedPreferences = context.getSharedPreferences(Constant.PREF, MODE_PRIVATE);
//        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
//        mEditor.putString(key, value);
//        mEditor.apply();
//    }
}