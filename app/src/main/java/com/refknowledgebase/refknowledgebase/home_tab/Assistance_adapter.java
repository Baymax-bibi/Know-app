package com.refknowledgebase.refknowledgebase.home_tab;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.refknowledgebase.refknowledgebase.DashboardActivity;
import com.refknowledgebase.refknowledgebase.R;
import com.refknowledgebase.refknowledgebase.utils.Constant;

import static android.content.Context.MODE_PRIVATE;
import static com.refknowledgebase.refknowledgebase.R.color.light_nav;


public class Assistance_adapter extends RecyclerView.Adapter<Assistance_adapter.ViewHolder>{
    private Assistance_model[] listdata;


    // RecyclerView recyclerView;
    public Assistance_adapter(Assistance_model[] listdata) {
        this.listdata = listdata;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_item_home_assistance, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.textView.setText(listdata[position].getDescription());
        holder.imageView.setImageResource(listdata[position].getImgId());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Fragment myFragment = new Assistance_detail();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.home_pager, myFragment).addToBackStack(null).commit();
            }
        });

    }


    @Override
    public int getItemCount() {
        return listdata.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public RelativeLayout relativeLayout;

        ViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.img_home_assistance);
            this.textView = (TextView) itemView.findViewById(R.id.tv_title);
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