package com.example.mobyardandroid.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobyardandroid.R;
import com.example.mobyardandroid.auth.LoginActivity;
import com.example.mobyardandroid.auth.StartActivity;
import com.example.mobyardandroid.utils.Yards;
import com.example.mobyardandroid.utils.YardsData;
import com.example.mobyardandroid.yard.CreateYardActivity;
import com.example.mobyardandroid.yard.MainYardActivity;
import com.example.mobyardandroid.yard.YardInfoActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.List;

public class UserDashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseUser firebaseUser;
    FirebaseAuth auth;

    // User`s data
    Settings settings;
    SharedPreferences YardsPrefs, YardInfoPrefs;

    // Yard`s data
    RecyclerView recyclerYards;
    RelativeLayout recyclerSearchYardsLayout, recyclerYardsLayout;
    RecyclerView.Adapter adapter;

    YardsData yardsData;
    List<Yards> yardsList;
    ArrayList<Yards> yardsArrayList;

    // Drawer Menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView menuIcon, addIcon, searchIcon, stopSearchIcon;
    LinearLayout contentView;
    static final float END_SCALE = 0.7f;

    // Animation
    Animation animationDisappear, animationAppear;

    EditText searchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        setContentView(R.layout.activity_user_dashboard);

        auth = FirebaseAuth.getInstance();
        firebaseUser = auth.getCurrentUser();

        yardsData = new YardsData(this );
        // yardsList = yardsData.getListYards();
        yardsArrayList = yardsData.getArrayListYards();


        // Hooks
        recyclerYards = findViewById(R.id.recycler_yards);
        recyclerSearchYardsLayout = findViewById(R.id.recycler_relative_search_yards);
        recyclerYardsLayout = findViewById(R.id.recycler_relative_yards);
        menuIcon = findViewById(R.id.menu_icon);
        addIcon = findViewById(R.id.add_icon);
        searchIcon = findViewById(R.id.search_icon);
        stopSearchIcon = findViewById(R.id.cancel_icon);

        RelativeLayout findField = findViewById(R.id.find_field);
        contentView = findViewById(R.id.content);


        animationDisappear = AnimationUtils.loadAnimation(UserDashboardActivity.this, R.anim.left_smooth_anim_dis );
        animationAppear = AnimationUtils.loadAnimation(UserDashboardActivity.this, R.anim.start_picture_anim );

        // Menu Hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);


        navigationDrawer();


//        NavigationView navigationViewNew = findViewById(R.id.nav_view);
//        View header = navigationViewNew.getHeaderView(R.layout.menu_header);
//        TextView name_box = header.findViewById(R.id.account_name);
//        TextView mail_box = header.findViewById(R.id.account_mail);
//        name_box.setText( lastName + " " + firstName );
//        mail_box.setText( email );

        addIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(
                        UserDashboardActivity.this,
                        CreateYardActivity.class
                ));
            }
        });

        findField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSearch();
            }
        });

        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSearch();
            }
        });

        stopSearchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopSearch();
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                boolean successful = false;
                int id = item.getItemId();


                if (id == R.id.nav_home) {
                    successful = true;
                } else if (id == R.id.nav_add) {
                    successful = true;
                    startActivity(new Intent(
                            UserDashboardActivity.this,
                            CreateYardActivity.class
                    ));
                } else if (id == R.id.nav_search) {
                    successful = true;
                    startSearch();
                } else if (id == R.id.nav_profile) {
                    successful = true;
                    startActivity(new Intent(
                            UserDashboardActivity.this,
                            ProfileActivity.class
                    ));
                } else if (id == R.id.nav_logout) {
                    successful = true;
                    auth.signOut();
                    startActivity(new Intent(
                            UserDashboardActivity.this,
                            StartActivity.class
                    ));
                    finish();
                } else if (id == R.id.nav_inv) {
                    successful = true;
                    final Intent shareIntent = new Intent( android.content.Intent.ACTION_SEND );
                    shareIntent.setType("text/plain");
                    String shareBody = getString(R.string.share_app);

                    String shareSub = "Share Sub";
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                    startActivity(
                            Intent.createChooser(
                                    shareIntent,
                                    "Share with Friends"
                            )
                    );
                } else if (id == R.id.nav_rep) {

                } else if (id == R.id.nav_faq) {

                }

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                if (successful)
                    drawer.closeDrawer(GravityCompat.START);
                return successful;
            }
        });


        searchEditText = findViewById(R.id.search_edit_text);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (settings.getRealSearch()) {
                    doSearch();
                }
            }
        });

    }

    private void setSearchRecyclerYard(){
        RecyclerView RSY = findViewById( R.id.recycler_search_yards );
        RecyclerView RY = findViewById( R.id.recycler_yards );

        recyclerSearchYardsLayout.setVisibility(View.VISIBLE);
        recyclerYardsLayout.setVisibility(View.INVISIBLE);

        RSY.setVisibility(View.VISIBLE);
        RSY.setClickable(true);
        RY.setVisibility(View.INVISIBLE);
        RY.setClickable(false);

        recyclerYards = RSY;
    }

    private void setRecyclerYard(){
        RecyclerView RSY = findViewById( R.id.recycler_search_yards );
        RecyclerView RY = findViewById( R.id.recycler_yards );

        recyclerSearchYardsLayout.setVisibility(View.INVISIBLE);
        recyclerYardsLayout.setVisibility(View.VISIBLE);

        RSY.setVisibility(View.INVISIBLE);
        RSY.setClickable(false);

        RY.setVisibility(View.VISIBLE);
        RY.setClickable(true);

        recyclerYards = RY;
    }

    // Navigation Drower Function
    private void navigationDrawer() {

        // Navigation Drawer
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener( this );
        navigationView.setCheckedItem(R.id.nav_home);

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer( GravityCompat.START );
                }
            }
        });

        animateNavigationDrawer();
    }

    private void animateNavigationDrawer() {
        // This code not our (CatX)
        // We took this code from one tutorial
        drawerLayout.setScrimColor(getResources().getColor(R.color.add_background));
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START)){
            drawerLayout.closeDrawer( GravityCompat.START );
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
    }


    protected void onStart() {
        super.onStart();

        YardsPrefs = getSharedPreferences(
                "YardsData",
                MODE_PRIVATE
        );

        settings = new Settings(this);

        // Changing the header menu ( Name and E-mail )
        View headerView = navigationView.getHeaderView(0);
        TextView nameText = headerView.findViewById(R.id.account_name);
        TextView mailText = headerView.findViewById(R.id.account_mail);


        nameText.setText(settings.getFirstLastName());
        mailText.setText(settings.getMail());

        stopSearch();
        recyclerYards();

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

        int sizeYAL = yardsArrayList.size();
        for (int i = 0; i < sizeYAL; i++ ){
            Yards yard = yardsArrayList.get(i);
            String yard_id = yard.getId();
            String yard_desc = yard.getDesc();
            String yard_title = yard.getName();
            homeAdapters.add(
                    new HomeAdapter(
                            R.drawable.yard_pic_template,
                            yard_title,
                            "Yard ID: " + yard_id,
                            yard_desc,
                            i + 1
                    )
            );
        }


        adapter = new YardsHomeAdapter(
                homeAdapters,
                getSharedPreferences(
                        "YardInfoActivity",
                        MODE_PRIVATE
                )
        );

        recyclerYards.setAdapter(adapter);

    }



    private void startSearch(){
        findViewById(R.id.search_edit_text).setVisibility(View.VISIBLE);
        findViewById(R.id.search_text).setVisibility(View.INVISIBLE);
        findViewById(R.id.cancel_icon).setVisibility(View.VISIBLE);
        findViewById(R.id.cancel_icon).setClickable(true);
        findViewById(R.id.search_icon).setClickable(true);
        findViewById(R.id.find_field).setClickable(false);

        //findViewById(R.id.search_edit_text).addFocusables(new ArrayList<>(), );

        setSearchRecyclerYard();

    }

    private void doSearch(){
        EditText searchEditText = findViewById(R.id.search_edit_text);
        String searchText = searchEditText.getText().toString();

        yardsArrayList = yardsData.getSearchRequest(searchText);
        if (yardsArrayList.size()>0 || true){
            recyclerYards();
        }
    }

    private void stopSearch(){
        EditText editText = findViewById(R.id.search_edit_text);
        editText.setVisibility(View.INVISIBLE);
        editText.setText("");
        findViewById(R.id.search_text).setVisibility(View.VISIBLE);

        findViewById(R.id.cancel_icon).setVisibility(View.INVISIBLE);
        findViewById(R.id.cancel_icon).setClickable(false);
        findViewById(R.id.search_icon).setClickable(false);
        findViewById(R.id.find_field).setClickable(true);

        yardsArrayList = yardsData.getArrayListYards();
        setRecyclerYard();
    }


    private void editYardInfoPref(String yardId){
        YardInfoPrefs = getSharedPreferences(
                "YardInfoActivity",
                MODE_PRIVATE
        );

        SharedPreferences.Editor editor = YardInfoPrefs.edit();
        editor.putString("yard_id", yardId );

    }

}