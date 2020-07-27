package com.refknowledgebase.refknowledgebase.home_saved;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.refknowledgebase.refknowledgebase.R;

public class Saved_article_adapter extends RecyclerView.Adapter<Saved_article_adapter.ViewHolder>{
    private Saved_article_Model[] listdata;


    // RecyclerView recyclerView;
    public Saved_article_adapter(Saved_article_Model[] listdata) {
        this.listdata = listdata;
    }
    @Override
    public Saved_article_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.item_saved_article, parent, false);
        Saved_article_adapter.ViewHolder viewHolder = new Saved_article_adapter.ViewHolder(listItem);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(Saved_article_adapter.ViewHolder holder, final int position) {
        final Saved_article_Model assistancemodel = listdata[position];
        holder.title.setText(listdata[position].getTitle());
        holder.content.setText(listdata[position].getContent());
    }


    @Override
    public int getItemCount() {
        return listdata.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView content;
        public TextView title;
        public RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.title = (TextView) itemView.findViewById(R.id.tv_title);
            this.content = (TextView) itemView.findViewById(R.id.tv_content);
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