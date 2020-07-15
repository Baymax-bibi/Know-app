package com.refknowledgebase.refknowledgebase;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.refknowledgebase.refknowledgebase.utils.Constant;

import me.tankery.lib.circularseekbar.CircularSeekBar;

public class Activity_Loading  extends AppCompatActivity implements View.OnClickListener{
    CircularSeekBar circle_bar;
    TextView tv_percent, tv_loading;
    private int pStatus = 0;
//    TextView tv_close;
    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

//        tv_close = findViewById(R.id.tv_close);
//        tv_close.setOnClickListener(this);

        tv_loading = findViewById(R.id.tv_loading);

        circle_bar = findViewById(R.id.circle_bar);
        tv_percent = findViewById(R.id.tv_percent);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (pStatus <= 100) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            circle_bar.setProgress(pStatus);
                            tv_percent.setText(pStatus + " %");
                        }
                    });
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    pStatus++;
                }
//                Intent intent_google = new Intent(Activity_Loading.this, DashboardActivity.class);
//                startActivity(intent_google);
                finish();
            }
        }).start();
//        language setting

//        if (getString(Constant.SELECTED_LANGUAGE).equals("English")){
//        }else if (getString(Constant.SELECTED_LANGUAGE).equals("Arabic")){
//            tv_loading.setText(getResources().getText(R.string.loading_ar));
//        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
//            case R.id.tv_close:
//                Intent intent_google = new Intent(Activity_Loading.this, DashboardActivity.class);
//                startActivity(intent_google);
//                finish();
        }
    }

    public String getString(String key) {
        SharedPreferences mSharedPreferences = getSharedPreferences(Constant.PREF, MODE_PRIVATE);
        String  selected =  mSharedPreferences.getString(key, "");
        return selected;
    }
}
