package com.example.mobyardandroid.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mobyardandroid.user.UserDashboardActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

import com.example.mobyardandroid.R;
import com.example.mobyardandroid.MainActivity;

public class LoginActivity extends AppCompatActivity {
    MaterialEditText email, password;
    Button btn_login;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_email = email.getText().toString();
                String txt_password = password.getText().toString();

                if (TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)){
                    Toast.makeText(
                            LoginActivity.this,
                            "All fields are required",
                            Toast.LENGTH_SHORT
                    ).show();
                } else {
                    auth.signInWithEmailAndPassword(txt_email, txt_password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){

                                        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();


                                        DatabaseReference reference = FirebaseDatabase
                                                .getInstance()
                                                .getReference("Users")
                                                .child( firebaseUser.getUid() );


                                        saveUserData( FirebaseDatabase.getInstance()
                                                .getReference("Users")
                                                .child( firebaseUser.getUid()
                                                )
                                        );



                                        Intent intent = new Intent(
                                                LoginActivity.this,
                                                UserDashboardActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Toast.makeText(
                                                LoginActivity.this,
                                                "Authentication failed!",
                                                Toast.LENGTH_SHORT
                                        ).show();
                                    }
                                }
                            });
                }
            }
        });

    }

    private void saveUserData(DatabaseReference user) {
        String firstname = user.child("Firstname").getKey();
        String lastname = user.child("Lastname").getKey();
        String id = user.child("id").getKey();
        String username = user.child("username").;
        SharedPreferences.Editor editor = getSharedPreferences(
                "PersonalData",
                Context.MODE_PRIVATE
        ).edit();
        editor.putString("firstname", firstname);
        editor.putString("lastname", lastname);
        editor.putString("id", id);
        editor.putString("username", username);
        editor.putString("mail", email.getText().toString());
        editor.apply();
    }
}
