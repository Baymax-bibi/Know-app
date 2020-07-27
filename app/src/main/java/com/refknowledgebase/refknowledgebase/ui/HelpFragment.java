package com.refknowledgebase.refknowledgebase.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.refknowledgebase.refknowledgebase.R;

public class HelpFragment extends Fragment implements View.OnClickListener, Animation.AnimationListener {

    boolean flag_img_help_3_2 = true;
    ImageView img_help_3_2 , img_content_3;
    TextView tv_content_3;
    Animation animationslidedown;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_help, container, false);

        img_help_3_2 = root.findViewById(R.id.img_help_3_2);
        img_content_3 = root.findViewById(R.id.img_help_content_3);
        tv_content_3 = root.findViewById(R.id.tv_help_content_3);
        animationslidedown = AnimationUtils.loadAnimation(getContext(), R.anim.slide_down);
        animationslidedown.setAnimationListener(this);
        return root;
    }
    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        img_help_3_2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_help_3_2:
                if (flag_img_help_3_2){
                    img_help_3_2.setImageResource(R.drawable.fire_arrow_down);
                    img_content_3.setVisibility(View.VISIBLE);
                    tv_content_3.setVisibility(View.VISIBLE);
                    img_content_3.startAnimation(animationslidedown);
                    tv_content_3.startAnimation(animationslidedown);
                    flag_img_help_3_2 = false;
                }else {
                    img_help_3_2.setImageResource(R.drawable.fire_arrow_right);
                    img_content_3.setVisibility(View.GONE);
                    tv_content_3.setVisibility(View.GONE);
                    flag_img_help_3_2 = true;
                }
                break;
        }
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}