package com.refknowledgebase.refknowledgebase;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.refknowledgebase.refknowledgebase.buffer.mBuffer;
import com.squareup.picasso.Picasso;

public class WebViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_activity);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

//        WebView mywebview = (WebView) findViewById(R.id.webView);
//        ImageView img_media = (ImageView) findViewById(R.id.img_media);
        ImageView close_nav = findViewById(R.id.close_nav);
        close_nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WebViewActivity.this, BlankActivity.class));
                finish();
            }
        });
//
//        if (mBuffer.selected_media_type.equals("VIDEO")){
//            mywebview.loadUrl(mBuffer.selected_media);
//            mywebview.setVisibility(View.VISIBLE);
//            img_media.setVisibility(View.GONE);
//        }else if (mBuffer.selected_media_type.equals("POSTER")){
//            Picasso.with(img_media.getContext()).load(mBuffer.selected_media).fit().into(img_media);
//            mywebview.setVisibility(View.GONE);
//            img_media.setVisibility(View.VISIBLE);
//        }
        Fragment fragment = new WebViewFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_webview, fragment)
                .commit();

    }

//    private int getScale(){
//        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
//        int width = display.getWidth();
//        Double val = new Double(width)/new Double(PIC_WIDTH);
//        val = val * 100d;
//        return val.intValue();
//    }
//    public void onBackPressed() {
//        Log.e("BACKPRESS", "webview");
//        startActivity(new Intent(WebViewActivity.this, BlankActivity.class));
//        finish();
//    }
}
