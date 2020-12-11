package com.example.mobyardandroid.yard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.mobyardandroid.R;
import com.example.mobyardandroid.auth.LoginActivity;
import com.example.mobyardandroid.auth.StartActivity;
import com.example.mobyardandroid.load.OnboardingActivity;

import java.time.Instant;

public class YardInfoActivity extends AppCompatActivity {

    ImageView backIcon;
    Button goToEdit;
    SharedPreferences infoPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yard_info);

        infoPref = getSharedPreferences(
                "YardInfoActivity",
                MODE_PRIVATE
        );

        backIcon = findViewById(R.id.info_back_icon);
        goToEdit = findViewById(R.id.info_edit_btn);

        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        goToEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(YardInfoActivity.this, MainYardActivity.class));

                finish();
            }
        });
    }
}