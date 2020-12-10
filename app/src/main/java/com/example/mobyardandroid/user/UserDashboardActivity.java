package com.example.mobyardandroid.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.mobyardandroid.R;
import com.example.mobyardandroid.auth.StartActivity;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class UserDashboardActivity extends AppCompatActivity {

    SharedPreferences YardsPrefs, PersPrefs;
    String email, userId, username, lastName, firstName;

    RecyclerView recyclerYards;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        setContentView(R.layout.activity_user_dashboard);


        PersPrefs = getSharedPreferences(
                "PersonalData",
                MODE_PRIVATE
        );

        email = PersPrefs.getString("mail", "");
        userId = PersPrefs.getString("id", "");
        username = PersPrefs.getString("username", "");
        lastName = PersPrefs.getString("Lastname", "");
        firstName = PersPrefs.getString("Firstname", "");

        // Hooks
        recyclerYards = findViewById(R.id.recycler_yards);

        recyclerYards();
    }


    protected void onStart() {
        super.onStart();

        YardsPrefs = getSharedPreferences(
                "YardsData",
                MODE_PRIVATE
        );


    }

    private void recyclerYards(){
        recyclerYards.setHasFixedSize(true);
        recyclerYards.setLayoutManager(new LinearLayoutManager(
                    this,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
        );

        ArrayList<HomeAdapter> homeAdapters = new ArrayList<>();

        for (int i = 1; i < 4; i++ ) {
            homeAdapters.add(
                    new HomeAdapter(
                            R.drawable.yard_pic_template,
                            getString(R.string.yards_title),
                            getString(R.string.yards_id),
                            getString(R.string.yards_desc),
                            i
                    )
            );
        }



        adapter = new YardsHomeAdapter(homeAdapters);

        recyclerYards.setAdapter(adapter);

    }
}