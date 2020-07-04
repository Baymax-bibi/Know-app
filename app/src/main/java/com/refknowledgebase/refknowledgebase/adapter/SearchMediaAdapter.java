package com.refknowledgebase.refknowledgebase.adapter;

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

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.flipkart.youtubeview.YouTubePlayerView;
import com.flipkart.youtubeview.models.ImageLoader;
import com.refknowledgebase.refknowledgebase.R;
import com.refknowledgebase.refknowledgebase.model.Search_Media_BaseModel;
import com.refknowledgebase.refknowledgebase.model.Search_Media_entities_Model;
import com.refknowledgebase.refknowledgebase.utils.Constant;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SearchMediaAdapter extends RecyclerView.Adapter<SearchMediaAdapter.YouTubePlayerViewHolder>  {
    Fragment mConext;
    private List<Search_Media_entities_Model> media_list;
//    private List<Search_Media_entities_Model> media_list_filtered;
    private int playerType;

    private ImageLoader imageLoader = new ImageLoader() {
        @Override
        public void loadImage(@NonNull ImageView imageView, @NonNull String url, int height, int width) {
            Picasso.with(imageView.getContext()).load(url).resize(width, height).centerCrop().into(imageView);
        }
    };

    public SearchMediaAdapter(Fragment _context){
        mConext = _context;
        media_list = new ArrayList<>();
//        media_list_filtered = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return media_list.size();
    }

    @NonNull
    @Override
    public YouTubePlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_media, parent, false);
        return new YouTubePlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final YouTubePlayerViewHolder holder, int position) {
        YouTubePlayerView playerView = holder.video_search;

        playerType = 2;
        final Search_Media_BaseModel search_media_baseModel = media_list.get(position);

        final String videoUrl = search_media_baseModel.geturl();
        holder.tv_content.setText(Html.fromHtml(search_media_baseModel.getdescription()));

        if (search_media_baseModel.getcontent_type().equals("VIDEO")){
            holder.video_search.setVisibility(View.VISIBLE);
            holder.img_search_sub.setVisibility(View.GONE);
//            holder.rl_vimeo.setVisibility(View.GONE);
            if (videoUrl.contains("youtube")){
                String videoId = videoUrl.substring(videoUrl.length()-11);
//                Log.e("Video data", videoUrl);
//                Log.e("VideoID", videoId);
                playerView.initPlayer(Constant.API_KEY, videoId, "https://cdn.rawgit.com/flipkart-incubator/inline-youtube-view/60bae1a1/youtube-android/youtube_iframe_player.html", playerType, null, mConext, imageLoader);
            }else if(videoUrl.contains("vimeo")){
                holder.video_search.setVisibility(View.GONE);
                holder.img_search_sub.setVisibility(View.GONE);
//                holder.rl_vimeo.setVisibility(View.VISIBLE);

                Log.e("VIMEO data", videoUrl);
                Log.e("This is ", "VIMEO");
                holder.img_vimeo_start.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        vimeovideo(videoUrl);
                        holder.img_vimeo_start.setVisibility(View.GONE);
                        String vimeoVideo = "<html><body><iframe src=\""+videoUrl+"\" frameborder=\"0\" ></iframe></body></html>";
                        holder.vv_vimeo.setWebViewClient(new WebViewClient() {
                            @Override
                            public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest request) {

                                webView.loadUrl(request.getUrl().toString());
                                return true;
                            }
                        });
                        WebSettings webSettings = holder.vv_vimeo.getSettings();
                        webSettings.setJavaScriptEnabled(true);
                        holder.vv_vimeo.loadData(vimeoVideo, "text/html", "utf-8");
                    }
                });
            }

        }else if(search_media_baseModel.getcontent_type().equals("POSTER")){
//            Log.e("Image data", videoUrl);
            holder.video_search.setVisibility(View.GONE);
            holder.img_search_sub.setVisibility(View.VISIBLE);
//            holder.rl_vimeo.setVisibility(View.GONE);
            Picasso.with(holder.img_search_sub.getContext()).load(videoUrl).fit().into(holder.img_search_sub);
        }
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

//    @Override
//    public Filter getFilter() {
//        return new Filter() {
//            @Override
//            protected FilterResults performFiltering(CharSequence charSequence) {
//                String charString = charSequence.toString();
//                Log.e("CharString", charString);
//                if (charString.isEmpty()) {
//                    media_list = media_list;
//                } else {
//                    List<Search_Media_entities_Model> filteredList = new ArrayList<>();
//                    Log.e("title_content_size", String.valueOf(media_list));
//                    for (Search_Media_entities_Model row : media_list) {
//
//                        // name match condition. this might differ depending on your requirement
//                        // here we are looking for name or phone number match
//                        Log.e("title_content", media_list.get(0).gettitle());
//                        if (row.gettitle().toLowerCase().contains(charString.toLowerCase()) || row.gettitle().contains(charSequence)) {
//                            filteredList.add(row);
//                            Log.e("Added", "adapter");
//                        }
//                    }
//
//                    media_list_filtered = filteredList;
//                }
//
//                FilterResults filterResults = new FilterResults();
//                filterResults.values = media_list_filtered;
//                return filterResults;
//            }
//
//            @Override
//            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
////                Log.e("filterResults", "filterResults.values");
//                media_list_filtered = (ArrayList<Search_Media_entities_Model>) filterResults.values;
//
//                // refresh the list with filtered data
//                notifyDataSetChanged();
//            }
//        };
//    }

    static class YouTubePlayerViewHolder extends RecyclerView.ViewHolder {
        YouTubePlayerView video_search;
        TextView tv_content;
        WebView vv_vimeo;
        RelativeLayout rl_vimeo;
        ImageView  img_search_sub, img_vimeo_start;
        public RelativeLayout relativeLayout;

        YouTubePlayerViewHolder(View itemView) {
            super(itemView);
            this.rl_vimeo = (RelativeLayout) itemView.findViewById(R.id.rl_vimeo);
            this.img_search_sub = (ImageView) itemView.findViewById(R.id.img_search_sub);
            this.img_vimeo_start = (ImageView) itemView.findViewById(R.id.img_vimeo_start);
            this.video_search = (YouTubePlayerView) itemView.findViewById(R.id.video_search);
            this.vv_vimeo = (WebView) itemView.findViewById(R.id.vv_vimeo);
            this.tv_content = (TextView) itemView.findViewById(R.id.tv_content);
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relativeLayout);
        }
    }


        /*
        Helpers - Pagination
   _________________________________________________________________________________________________
    */

    public void add(Search_Media_entities_Model r) {
        media_list.add(r);
        notifyItemInserted(media_list.size() - 1);
//        media_list_filtered.add(r);
//        notifyItemInserted(media_list_filtered.size() - 1);
//        Log.e("media_list_filtered", String.valueOf(r));
    }

    public void addAll(List<Search_Media_entities_Model> moveResults) {
        for (Search_Media_entities_Model result : moveResults) {
            add(result);
        }
    }


//    public Search_Media_entities_Model getItem(int position) {
//        return media_list.get(position);
//    }


}