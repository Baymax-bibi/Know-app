package com.refknowledgebase.refknowledgebase;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.flipkart.youtubeview.YouTubePlayerView;
import com.flipkart.youtubeview.models.ImageLoader;
import com.refknowledgebase.refknowledgebase.buffer.mBuffer;
import com.refknowledgebase.refknowledgebase.utils.Constant;
import com.squareup.picasso.Picasso;

public class WebViewFragment extends Fragment {
    YouTubePlayerView youTubePlayerView;
    private int playerType;
    Fragment fragment;
//    ImageView img_thum, img_media_start;
    RelativeLayout rl_youtube;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.webview_framgent, container, false);

        youTubePlayerView = root.findViewById(R.id.video_landing);
        ImageView img_media = (ImageView) root.findViewById(R.id.img_poster);
//        img_thum = root.findViewById(R.id.img_thum);
//        img_media_start = root.findViewById(R.id.img_media_start);
        rl_youtube = root.findViewById(R.id.rl_youtube);
        playerType = 2;
        fragment = this;
        Log.e("TAG_media", mBuffer.selected_media);
        if (mBuffer.selected_media_type.equals("VIDEO")){
            youTubePlayerView.setVisibility(View.VISIBLE);
            img_media.setVisibility(View.GONE);
//            Picasso.with(img_thum.getContext()).load(mBuffer.selected_media).fit().into(img_thum);
            youTubePlayerView.initPlayer(Constant.API_KEY, mBuffer.selected_media, "https://cdn.rawgit.com/flipkart-incubator/inline-youtube-view/60bae1a1/youtube-android/youtube_iframe_player.html", playerType, null, fragment, imageLoader);

        }else if (mBuffer.selected_media_type.equals("POSTER")){
            Picasso.with(img_media.getContext()).load(mBuffer.selected_media).fit().into(img_media);
            youTubePlayerView.setVisibility(View.GONE);
            img_media.setVisibility(View.VISIBLE);
        }
        return root;
    }

    private ImageLoader imageLoader = new ImageLoader() {
        @Override
        public void loadImage(@NonNull ImageView imageView, @NonNull String url, int height, int width) {
            Picasso.with(imageView.getContext()).load(url).resize(width, height).centerCrop().into(imageView);
        }
    };
}
