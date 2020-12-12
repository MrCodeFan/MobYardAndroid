package com.example.mobyardandroid.auth;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import java.util.HashMap;

import com.example.mobyardandroid.R;
import com.example.mobyardandroid.MainActivity;

public class RegisterActivity extends AppCompatActivity {

    MaterialEditText username, email, password, firstname, lastname;
    Button btn_register;

    FirebaseAuth auth;
    DatabaseReference reference;

    SharedPreferences RegisterPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);

        auth = FirebaseAuth.getInstance();

        btn_register = findViewById(R.id.btn_register);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_username = username.getText().toString();
                String txt_email = email.getText().toString();
                String txt_password = password.getText().toString();
                String txt_firstname = firstname.getText().toString();
                String txt_lastname = lastname.getText().toString();

                if (TextUtils.isEmpty(txt_email) ||
                        TextUtils.isEmpty(txt_password) ||
                        TextUtils.isEmpty(txt_username) ||
                        TextUtils.isEmpty(txt_firstname)
                ){
                    Toast.makeText(
                            RegisterActivity.this,
                            "All (*) fields are required",
                            Toast.LENGTH_SHORT
                    ).show();
                } else if (txt_password.length() < 6) {
                    Toast.makeText(
                            RegisterActivity.this,
                            "Password 6 characters",
                            Toast.LENGTH_SHORT
                    ).show();
                } else {
                    register(txt_username, txt_email, txt_password, txt_firstname, txt_lastname);

                }
            }
        });
    }

    private void register(String txt_username, String txt_email, String txt_password, String txt_firstname, String txt_lastname) {
        auth.createUserWithEmailAndPassword(txt_email, txt_password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            String userId = null;
                            if (firebaseUser != null)
                                userId = firebaseUser.getUid();

                            reference = FirebaseDatabase
                                    .getInstance()
                                    .getReference("Users")
                                    .child(userId);

                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("id", userId);
                            hashMap.put("username", txt_username);
                            hashMap.put("imageUrl", "default");
                            hashMap.put("Firstname", txt_firstname);
                            hashMap.put("Lastname", txt_lastname);

                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        RegisterPrefs = getSharedPreferences(
                                                "PersonalData",
                                                MODE_PRIVATE
                                        );

                                        SharedPreferences.Editor editor = RegisterPrefs.edit();
                                        editor.putString("mail", txt_email );
                                        editor.putString("username", txt_username );
                                        editor.putString("firstname", txt_firstname );
                                        editor.putString("lastname", txt_lastname );
                                        editor.apply();


                                        Intent intent = new Intent(RegisterActivity.this, UserDashboardActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });

                        } else {
                            Toast.makeText(
                                    RegisterActivity.this,
                                    "You can`t with this email or password",
                                    Toast.LENGTH_SHORT
                            ).show();
                        }
                    }
                });
    }
}