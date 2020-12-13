package com.example.mobyardandroid.utils;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.system.Os;

import androidx.annotation.RequiresApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class YardsData {

    ArrayList<Yards> yardsArrayList;
    List<Yards> yardsList;
    RandomString randS;
    String appDirYards;

    Context context;


    @RequiresApi(api = Build.VERSION_CODES.O)
    public YardsData(Context context) {
        this.context = context.getApplicationContext();

        randS = new RandomString();
        //yardsList = new List<Yards>();
        yardsArrayList = new ArrayList<Yards>();
        load();

        String[] tokens = { "AFLMSFML544SGGSSG54G4SVD", "FEA4EF8GR61WG51W4G5", "BR8S8TE49EVVW6181BTRE4", "BR8S8TE49EVVW6181B1548"};
        for (int i = 0; i < 3; i++){
            Yards yards = new Yards();
            yards.exist = true;
            yards.name = String.format("Yard №%d", i + 1 );
            yards.id = tokens[i];
            yardsArrayList.add(yards);
        }


    }




    @RequiresApi(api = Build.VERSION_CODES.O)
    public Yards getYardByFile(String fileName){
        Yards tempYard = new Yards();
        try
        {
            String content = Files.lines(
                    Paths.get(
                            fileName
                    ) ).reduce(
                    "",
                    String::concat
            );

            try {
                JSONObject obj = new JSONObject(content);

                tempYard.id = obj.getString("id");
                tempYard.desc = obj.getString("desc");
                tempYard.name = obj.getString("name");
                tempYard.longitude = obj.getDouble("longitude");
                tempYard.weight = obj.getDouble("weight");
                tempYard.height = obj.getDouble("height");
                tempYard.latitude = obj.getDouble("latitude");

                tempYard.exist = true;
            }
            catch (org.json.JSONException e) {

            }
        }
        catch (java.io.IOException e) {

        }

        return tempYard;
    }

    public void add( String yardId, String name, String desc, Double weight, Double height, Double longitude, Double latitude ){
        Yards yards = new Yards(yardId, name, desc, weight, height, longitude, latitude);
        yardsArrayList.add(yards);
        try {
            saveYard(yards);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ;
    }

    public void saveYard(String yardId ) throws IOException {
        Yards yard = getYard(yardId);
        if (yard.exist){
            saveYard(yard);
        }
    }
    public void saveYard( Yards yard ) throws IOException {
        String dir = appDirYards;
        dir += yard.id + ".json";
        String info = formJsonFromYard(yard);
        writeFile(dir, info);
    }



    public Yards getYard(String id) {
        Yards yard = new Yards();
        for (int i = 0; i < yardsArrayList.size(); i++ ){
            if ( yardsArrayList.get(i).id == id ) {
                return yardsArrayList.get(i);
            }
        }

        return yard;
    }

    public String formJsonFromYard(Yards yards){
        String output = "{";
        String longitudeString = String.valueOf(yards.getLongitude());
        String latitudeString = String.valueOf(yards.getLatitude());

        String heightString = String.valueOf(yards.getHeight());
        String weightString = String.valueOf(yards.getWeight());

        output += String.format("\"id\":\"%s\",", yards.getId() );
        output += String.format("\"name\":\"%s\",", yards.getName() );
        output += String.format("\"desc\":\"%s\",", yards.getDesc() );

        output += String.format("\"desc\":\"%s\",", yards.getOwner() );

        output += String.format("\"longitude\":%s,", longitudeString );
        output += String.format("\"latitude\":%s,", latitudeString );

        output += String.format("\"height\":%s,", heightString );
        output += String.format("\"weight\":%s", weightString );

        output += "}";

        return  output;
    }

    public void writeFile(String dir, String info) throws IOException {
        try (
                FileWriter f = new FileWriter( dir);
        ) {

            f.write(info);
        }
        catch (java.io.IOException e){

        }

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void load(){

        context.getDir( "yards", Context.MODE_PRIVATE );
        appDirYards = context.getDir( "yards", Context.MODE_PRIVATE ).getAbsolutePath();
        File dir = new File(appDirYards);
        File[] arrFiles = dir.listFiles();
        List<File> lst = Arrays.asList(arrFiles);

        File tempFile;

        for (int i = 0; i < lst.size(); i++ ) {
            tempFile = lst.get(i);

            String fileName = tempFile.getName();
            Yards yard = getYardByFile(fileName);
            yardsList.add(yard);
        }
    }

    public List<Yards> getListYards(){
        return yardsList;
    }

    public ArrayList<Yards> getArrayListYards(){
        return yardsArrayList;
    }

    public boolean isIdTaken(String id){
        for ( int i = 0; i < yardsArrayList.size(); i++ ){
            if (yardsArrayList.get(i).id.equals(id)) { return true; }
        }
        return false;
    }

    public ArrayList<Yards> getSearchRequest(String someData) {
        ArrayList<Yards> yardsTemp = new ArrayList<>();
        Yards yards;
        for ( int i = 0; i < yardsArrayList.size(); i++ ){
            yards = yardsArrayList.get(i);
            if ( yards.getId().contains(someData)
                    || yards.getName().contains(someData)
                    || yards.getDesc().contains(someData) ) {
                yardsTemp.add(yards);
            } else if (0==0) {

            }
        }

        return yardsTemp;

    }
}
