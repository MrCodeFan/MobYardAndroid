package com.example.mobyardandroid.yard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.mobyardandroid.R;
import com.example.mobyardandroid.user.UserDashboardActivity;
import com.example.mobyardandroid.utils.RandomString;
import com.example.mobyardandroid.utils.YardsData;
import com.rengwuxian.materialedittext.MaterialEditText;

public class CreateYardActivity extends AppCompatActivity {

    ImageView backIcon;
    Button yardCreate;
    YardsData yardsData;

    RandomString randS;

    MaterialEditText nameYard, weightYard, heightYard, descYard;
    String idYard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_yard);

        randS = new RandomString();

        backIcon = findViewById(R.id.yard_create_back);
        yardCreate = findViewById(R.id.yard_create_btn);



        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        yardCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Function of creating button

                nameYard = findViewById(R.id.yard_create_name);
                weightYard = findViewById(R.id.yard_create_weight);
                heightYard = findViewById(R.id.yard_create_height);
                descYard = findViewById(R.id.yard_create_desc);

                idYard = randS.gen(15);
                while(yardsData.isIdTaken(idYard)){
                    idYard = randS.gen(15);
                }

                yardsData = new YardsData(getBaseContext());
                Double.parseDouble(heightYard.getText().toString());


                yardsData.add(
                        idYard,
                        nameYard.getText().toString(),
                        descYard.getText().toString(),
                        Double.parseDouble(weightYard.getText().toString()),
                        Double.parseDouble(heightYard.getText().toString()),
                        0.0, 0.0
                );

                startActivity(new Intent(CreateYardActivity.this, UserDashboardActivity.class));
                finish();
            }
        });




    }
}