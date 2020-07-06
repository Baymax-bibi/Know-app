package com.refknowledgebase.refknowledgebase;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.refknowledgebase.refknowledgebase.buffer.mBuffer;
import com.refknowledgebase.refknowledgebase.model.oAuth_Model;

public class BlankActivity extends AppCompatActivity {
    oAuth_Model oAuth_model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blank);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Fragment fragment = new LandingFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_full, fragment)
                .commit();
    }
}
