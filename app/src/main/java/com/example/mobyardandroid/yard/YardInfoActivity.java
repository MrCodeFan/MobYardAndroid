package com.example.mobyardandroid.yard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.mobyardandroid.R;

public class YardInfoActivity extends AppCompatActivity {

    ImageView backIcon;
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

        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}