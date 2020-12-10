package com.example.mobyardandroid.load;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mobyardandroid.R;
import com.example.mobyardandroid.auth.StartActivity;

public class StartLoadActivity extends AppCompatActivity {

    private static int Timer = 5000;

    // Variables
    ImageView backgroundImage;
    TextView poweredByLine;

    // Animations
    Animation sideAnim, bottomAnim, pictureAnim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        setContentView(R.layout.activity_start_load);

        // Hooks
        backgroundImage = findViewById(R.id.imageView);
        poweredByLine = findViewById(R.id.powered_by);

        // Animations
        sideAnim = AnimationUtils.loadAnimation(this, R.anim.start_bottom_anim);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.start_load_anim);
        pictureAnim = AnimationUtils.loadAnimation(this, R.anim.start_picture_anim);

        // set Animations in elements

        backgroundImage.setAnimation(pictureAnim);
        poweredByLine.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(StartLoadActivity.this, OnboardingActivity.class);
                startActivity(intent);
                finish();
            }
        }, Timer);
    }
}