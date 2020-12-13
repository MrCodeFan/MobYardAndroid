package com.example.mobyardandroid.user;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

public class Settings {
    private SharedPreferences PersonalData;
    private SharedPreferences.Editor PersonalDataEditor;
    private boolean editSheared = false;
    String firstname, lastname;
    String mail, username;
    String desc;
    ArrayList<String> yards, yardsOwner;
    Context context;

    boolean isMailVerified;
    boolean isPublic;

    public Settings(String firstname, String lastname, String mail, String username) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.mail = mail;
        this.username = username;
    }

    public Settings() {
        this.firstname = "";
        this.lastname = "";
        this.mail = "";
        this.username = "";
    }

    public Settings(Context context) {
        this.load(context);
        PersonalData = this.context.getSharedPreferences(
                "PersonalData",
                Context.MODE_PRIVATE
        );
        PersonalDataEditor = PersonalData.edit();
    }

    public void load(Context context){
        this.context = context.getApplicationContext();
        this.load();
    }

    public void load(){
        PersonalData = this.context.getSharedPreferences(
                "PersonalData",
                Context.MODE_PRIVATE
        );
        PersonalDataEditor = PersonalData.edit();
        this.editSheared = true;
        firstname = PersonalData.getString("firstname", "defFirstname");
        lastname = PersonalData.getString("lastname", "defLastname");
        username = PersonalData.getString("username", "defUsername");
        mail = PersonalData.getString("mail", "defMail");
    }


    public void edit_name(String firstname, String lastname, String username){
        this.username = username;
        edit_name(firstname, lastname);
        if ( this.editSheared ){
            PersonalDataEditor.putString("username", username);
            PersonalDataEditor.apply();
        }
    }
    public void edit_name(String firstname, String lastname){
        this.lastname = lastname;
        edit_name(firstname);
        if ( this.editSheared ){
            PersonalDataEditor.putString("lastname", lastname);
            PersonalDataEditor.apply();
        }
    }
    public void edit_name(String firstname){
        this.firstname = firstname;
        if ( this.editSheared ){
            PersonalDataEditor.putString("firstname", firstname);
            PersonalDataEditor.apply();
        }
    }

    public void edit_mail(String mail){
        this.mail = mail;
        if ( this.editSheared ){
            PersonalDataEditor.putString("mail", mail);
            PersonalDataEditor.apply();
        }
    }

    public void edit_username(String username){
        this.username = username;
        if ( this.editSheared ){
            PersonalDataEditor.putString("username", username);
            PersonalDataEditor.apply();
        }
    }

    public boolean getEditShared(){ return editSheared; }

    public void addYard(String yardId){
        if (!yards.contains(yardId)){
            yards.add(yardId);
        }
    }

    public void addOwnerYard(String yardId){
        if (!yardsOwner.contains(yardId)){
            yardsOwner.add(yardId);
            addYard(yardId);
        }
    }


    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getMail() {
        return mail;
    }

    public String getUsername() {
        return username;
    }
    public String getDesc(){
        return desc;
    }
}
