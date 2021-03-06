package com.example.mobyardandroid.load;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mobyardandroid.R;
import com.example.mobyardandroid.auth.StartActivity;

public class OnboardingActivity extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout dotsLayout;
    SliderAdapter sliderAdapter;
    Button getStartedBtn, nextBtn, skipBtn;
    Animation animation;
    int currPos = 0;

    TextView[] dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        setContentView(R.layout.activity_onboarding);

        // Hooks
        viewPager = findViewById(R.id.slider);
        dotsLayout = findViewById(R.id.dots);
        getStartedBtn = findViewById(R.id.get_started_btn);
        nextBtn = findViewById(R.id.next_btn);
        skipBtn = findViewById(R.id.skip_btn);

        getStartedBtn.setVisibility(View.INVISIBLE);

        // Call Adapter
        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter( sliderAdapter );

        addDots(0);

        viewPager.addOnPageChangeListener(changeListener);
    }

    public void skip(View view){
        startActivity( new Intent(this, StartActivity.class ));
    }

    public void start(View view){
        this.skip(view);
        finish();
    }

    public void next(View view){
        viewPager.setCurrentItem( currPos + 1 );
    }
    private void addDots(int position){
        dots = new TextView[3];
        dotsLayout.removeAllViews();

        for (int i = 0; i < dots.length; i++){
            dots[i]=new TextView( this );
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            if (i == position){
                dots[i].setTextColor(getResources().getColor(R.color.Darkslategray));
            } else {
                dots[i].setTextColor(getResources().getColor(R.color.Gray));
            }

            dotsLayout.addView(dots[i]);
        }
    }

    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);
            currPos = position;

            if (position < 2){
                nextBtn.setVisibility( View.VISIBLE );
                animation = AnimationUtils.loadAnimation(
                        OnboardingActivity.this,
                        R.anim.right_smooth_anim_dis
                );
                if (getStartedBtn.getVisibility() == View.VISIBLE) {
                    getStartedBtn.setAnimation(animation);
                    getStartedBtn.setVisibility(View.INVISIBLE);
                }


            } else {
                animation = AnimationUtils.loadAnimation(OnboardingActivity.this, R.anim.start_bottom_anim);
                getStartedBtn.setAnimation(animation);
                getStartedBtn.setVisibility(View.VISIBLE);
                nextBtn.setVisibility( View.INVISIBLE );
                skipBtn.setVisibility( View.INVISIBLE );
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}