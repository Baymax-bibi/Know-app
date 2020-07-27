package com.refknowledgebase.refknowledgebase.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

//import com.flipkart.youtubeview.YouTubePlayerView;
//import com.flipkart.youtubeview.models.ImageLoader;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.refknowledgebase.refknowledgebase.R;
import com.refknowledgebase.refknowledgebase.buffer.mBuffer;
import com.refknowledgebase.refknowledgebase.model.Home_Content_engitiesModel;
import com.refknowledgebase.refknowledgebase.model.Search_Media_BaseModel;
import com.refknowledgebase.refknowledgebase.model.Search_Media_entities_Model;
import com.refknowledgebase.refknowledgebase.myinterface.HomeContentClickListner;
import com.refknowledgebase.refknowledgebase.utils.Constant;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.refknowledgebase.refknowledgebase.utils.Constant.DEVELOPER_KEY;

public class SearchMediaAdapter extends RecyclerView.Adapter<SearchMediaAdapter.YouTubePlayerViewHolder>  {
    Context mConext;
    private HomeContentClickListner mListener;
    private List<Search_Media_entities_Model> media_list;

    public SearchMediaAdapter(Context _context, HomeContentClickListner _mListner){
        mConext = _context;
        media_list = new ArrayList<>();
        mListener = _mListner;
    }

    @Override
    public int getItemCount() {
        return media_list.size();
    }

    @NonNull
    @Override
    public YouTubePlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_media, parent, false);

        return new YouTubePlayerViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull final YouTubePlayerViewHolder holder, final int position) {
        final Search_Media_BaseModel search_media_baseModel = media_list.get(position);

        String videoUrl = search_media_baseModel.geturl();

        videoUrl = videoUrl.replace("httpss", "https");
        videoUrl = videoUrl.replace("http", "https");
        holder.tv_content.setText(Html.fromHtml(search_media_baseModel.getdescription()));

        if (search_media_baseModel.getcontent_type().equals("VIDEO")){
            holder.img_search_sub.setVisibility(View.GONE);
            if (videoUrl.contains("youtube")){
                holder.rl_video_landing.setVisibility(View.VISIBLE);
                holder.rl_vimeo.setVisibility(View.GONE);
                String videoId = videoUrl.substring(videoUrl.length()-11);
                Picasso.with(mConext).load(Uri.parse("https://i4.ytimg.com/vi/"+ videoId+"/0.jpg")).into(holder.img_first_screen);
                final String finalVideoUrl2 = videoUrl;
                holder.img_start_video.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    mBuffer.selected_media = finalVideoUrl2.substring(finalVideoUrl2.length()-11);
                    Intent intent = YouTubeStandalonePlayer.createVideoIntent((Activity) mConext, DEVELOPER_KEY, mBuffer.selected_media);
                    mConext.startActivity(intent);
                    }
                });
            }
            else if(videoUrl.contains("vimeo")){
                ViewGroup.LayoutParams params =  holder.rl_full.getLayoutParams();
                params.height = 0;
                holder.rl_full.setLayoutParams(params);
                holder.rl_video_landing.setVisibility(View.GONE);
                holder.rl_vimeo.setVisibility(View.VISIBLE);

                videoUrl = videoUrl.replace("httpss", "https");

                final String finalVideoUrl1 = videoUrl;

                videoUrl = videoUrl.replace("httpss", "https");
                holder.img_vimeo_start.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        holder.img_vimeo_start.setVisibility(View.GONE);
                        holder.vv_vimeo.setVideoURI(Uri.parse(finalVideoUrl1));
                        holder.vv_vimeo.start();
                    }
                });
            }

        }else if(search_media_baseModel.getcontent_type().equals("POSTER")){
            holder.rl_vimeo.setVisibility(View.GONE);
            holder.rl_video_landing.setVisibility(View.GONE);
            holder.img_search_sub.setVisibility(View.VISIBLE);

            Picasso.with(holder.img_search_sub.getContext()).load(videoUrl).fit().into(holder.img_search_sub);

            final String finalVideoUrl = videoUrl;
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mBuffer.selected_media_id = finalVideoUrl;
                    mListener.Home_Content_ClickListner(v, position);
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    static class YouTubePlayerViewHolder extends RecyclerView.ViewHolder {
        TextView tv_content;
        VideoView vv_vimeo;
        ImageView  img_search_sub, img_vimeo_start, img_first_screen, img_start_video;
        RelativeLayout rl_vimeo, rl_video_landing, rl_full;

        YouTubePlayerViewHolder(View itemView) {
            super(itemView);
            this.rl_full = (RelativeLayout) itemView.findViewById(R.id.rl_full);
            this.rl_vimeo = (RelativeLayout) itemView.findViewById(R.id.rl_vimeo);
            this.img_search_sub = (ImageView) itemView.findViewById(R.id.img_search_sub);
            this.img_vimeo_start = (ImageView) itemView.findViewById(R.id.img_vimeo_start);
            this.vv_vimeo = (VideoView) itemView.findViewById(R.id.vv_vimeo);
            this.tv_content = (TextView) itemView.findViewById(R.id.tv_content);
            this.rl_video_landing = (RelativeLayout)itemView.findViewById(R.id.rl_video_landing);
            this.img_first_screen = (ImageView) itemView.findViewById(R.id.img_first_screen);
            this.img_start_video = (ImageView) itemView.findViewById(R.id.img_start_video);
        }
    }


        /*
        Helpers - Pagination
   _________________________________________________________________________________________________
    */

    public void add(Search_Media_entities_Model r) {
        media_list.add(r);
        notifyItemInserted(media_list.size() - 1);
    }

    public void addAll(List<Search_Media_entities_Model> moveResults) {
        for (Search_Media_entities_Model result : moveResults) {
            add(result);
        }
    }

}