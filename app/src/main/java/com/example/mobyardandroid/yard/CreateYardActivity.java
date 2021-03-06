package com.example.mobyardandroid.yard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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

    TextView idText;
    MaterialEditText nameYard, weightYard, heightYard, descYard;
    String idYard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_yard);

        randS = new RandomString();

        backIcon = findViewById(R.id.yard_create_back);
        yardCreate = findViewById(R.id.yard_create_btn);
        idText = findViewById(R.id.yard_create_id);


        yardsData = new YardsData(getBaseContext());

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

                Double.parseDouble( heightYard.getText().toString() );

                String yardName = nameYard.getText().toString();
                String yardDesc = descYard.getText().toString();
                Double yardWeight = Double.parseDouble(weightYard.getText().toString());
                Double yardHeight = Double.parseDouble(heightYard.getText().toString());

                if (yardName.isEmpty()){

                } else if ( (yardWeight < 0) || (yardWeight > 200) ) {

                } else if ( (yardHeight < 0) || (yardHeight > 200) ) {

                } else {
                    yardsData.add(
                            idYard, yardName, yardDesc, yardWeight, yardHeight,
                            0.0, 0.0
                    );

                    startActivity(new Intent(CreateYardActivity.this, MainYardActivity.class));
                    finish();
                }
            }
        });




    }

    @Override
    protected void onStart() {
        super.onStart();

        idYard = randS.gen(15);
        while ( yardsData.isIdTaken(idYard) ){
            idYard = randS.gen(15);
        }
        idText.setText(idYard);

    }
}