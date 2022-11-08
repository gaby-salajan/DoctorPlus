package com.ns.doctorplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        ImageView splashImg = findViewById(R.id.logoView);

        Animation imgAnimation = AnimationUtils.loadAnimation(this, R.anim.intro_img_animation);

        splashImg.setAnimation(imgAnimation);

        new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                startActivity(new Intent(IntroActivity.this, LoginActivity.class));
                finish();
            }
        }
                .start();
    }
}