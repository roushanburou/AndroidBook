package com.brkc.anim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private final String TAG="MainActivity" ;

    private Button translatebt,scalebt,rotatebt,alphabt,setbt;
    private ImageView imagev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init_view();
    }

    void init_view()
    {
        translatebt =findViewById(R.id.translate);
        scalebt =findViewById(R.id.scale);
        rotatebt =findViewById(R.id.rotate);
        imagev =findViewById(R.id.image);
        alphabt =findViewById(R.id.alpha);
        setbt =findViewById(R.id.set);

        final Animation scaleAnimation =AnimationUtils.loadAnimation(this,R.anim.scale_anim);
        final Animation translateAnimation = AnimationUtils.loadAnimation(this,R.anim.translate_anim);
        final Animation rotateAnimation =AnimationUtils.loadAnimation(this,R.anim.rotate_anim);
        final Animation alphaAnimation  =AnimationUtils.loadAnimation(this,R.anim.alpha_anim);
        final Animation setAnimation  =AnimationUtils.loadAnimation(this,R.anim.set_anim);

        translatebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imagev.startAnimation(translateAnimation);
            }
        });
        scalebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imagev.startAnimation(scaleAnimation);
            }
        });
        rotatebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagev.startAnimation(rotateAnimation);
            }
        });
        alphabt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagev.startAnimation(alphaAnimation);
            }
        });

        setbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagev.startAnimation(setAnimation);
            }
        });
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.e("TAG","onAnimationStart");

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.e("TAG","onAnimationEnd");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.e("TAG","onAnimationRepeat");
            }
        });
    }
}
