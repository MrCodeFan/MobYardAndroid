package com.example.mobyardandroid.yard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobyardandroid.R;
import com.example.mobyardandroid.auth.LoginActivity;
import com.example.mobyardandroid.auth.StartActivity;
import com.example.mobyardandroid.load.OnboardingActivity;
import com.example.mobyardandroid.utils.Yards;
import com.example.mobyardandroid.utils.YardsData;

import java.time.Instant;
import java.util.ArrayList;

public class YardInfoActivity extends AppCompatActivity {

    ImageView backIcon;
    Button goToEdit;
    SharedPreferences infoPref;

    LinearLayout mainLinearLayout, idLayout, nameLayout,
            longitudeLayout, latitudeLayout, descLayout;
    TextView idText, nameText, longitudeText, latitudeText, descText;

    YardsData yardsData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yard_info);

        infoPref = getSharedPreferences(
                "YardInfoActivity",
                MODE_PRIVATE
        );
        String yardId = infoPref.getString("yard_id", "");
        // Toast.makeText(YardInfoActivity.this, yardId, Toast.LENGTH_SHORT).show();
        yardsData = new YardsData();
        Yards yards = yardsData.getYard(yardId);
        if ( ! yards.isExist() ){
            Toast.makeText(YardInfoActivity.this, "Some troubles was detected with this yard!", Toast.LENGTH_SHORT).show();
            finish();
        }

        backIcon = findViewById(R.id.info_back_icon);
        goToEdit = findViewById(R.id.info_edit_btn);

        // mainLinearLayout = findViewById(R.id.yard_id);
        idLayout = findViewById(R.id.yard_id);
        nameLayout = findViewById(R.id.yard_name);
        idText = findViewById(R.id.yard_id_data);
        nameText = findViewById(R.id.yard_name_data);
        longitudeText = findViewById(R.id.yard_longitude_data);
        latitudeText = findViewById(R.id.yard_latitide_data);
        descText = findViewById(R.id.yard_desc_data);

        idText.setText(yards.getId());
        nameText.setText(yards.getName());
        longitudeText.setText(yards.getLongitude().toString());
        latitudeText.setText(yards.getLatitude().toString());
        descText.setText(yards.getDesc());


        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        goToEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(
                        YardInfoActivity.this,
                        MainYardActivity.class
                ));
                finish();
            }
        });
    }
}