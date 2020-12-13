package com.example.mobyardandroid.user;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mobyardandroid.R;

public class ProfileActivity extends AppCompatActivity {

    Settings settings;

    ImageView backIcon;
    TextView firstnameText, lastnameText, usernameText, mailText, descText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        settings = new Settings(this);

        backIcon = findViewById(R.id.profile_back_icon);
        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        firstnameText = findViewById(R.id.profile_firstname_data);
        lastnameText = findViewById(R.id.profile_lastname_data);
        usernameText = findViewById(R.id.profile_username_data);
        mailText = findViewById(R.id.profile_mail_data);
        descText = findViewById(R.id.prifile_desc_data);

        firstnameText.setText(settings.getFirstname());
        lastnameText.setText(settings.getLastname());
        usernameText.setText(settings.getUsername());
        mailText.setText(settings.getMail());
        descText.setText(settings.getDesc());


    }
}