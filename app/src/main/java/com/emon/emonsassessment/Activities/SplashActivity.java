package com.emon.emonsassessment.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.emon.emonsassessment.R;

public class SplashActivity extends AppCompatActivity {

    private TextView title;

    Animation middleAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        //animation initialize
        middleAnimation = AnimationUtils.loadAnimation(this,R.anim.middle_animation);

        //view initialize
        title = findViewById(R.id.title_text);

        //setting animations
        title.setAnimation(middleAnimation);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,AuthActivity.class));
                finish();
            }
        },2500);
    }
}