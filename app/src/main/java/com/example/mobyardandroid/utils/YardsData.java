package com.example.mobyardandroid.utils;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
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

    String appDirYards;
/*
    @RequiresApi(api = Build.VERSION_CODES.O)
    public YardsData() {
        /*
        Context.getDir( "yards", MODE_PRIVATE );
        appDirYards = getDir( "yards", MODE_PRIVATE );
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
*/




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

    public void add( String yardId, String name, String desc, Double longitude, Double latitude ) throws IOException {
        Yards yards = new Yards(yardId, name, desc, longitude, latitude);
        yardsList.add(yards);
        saveYard(yards);
    }

    public void saveYard(String yardId ) throws IOException {
        Yards yard = getYard(yardId);
        if (yard.exist){
            saveYard(yard);
        }
    }
    public void saveYard(Yards yard ) throws IOException {
        String dir = appDirYards;
        dir += yard.id + ".json";
        String info = formJsonFromYard(yard);


        writeFile(dir, info);


    }



    public Yards getYard(String id) {
        Yards yard = new Yards();

        for (int i = 0; i < yardsList.size(); i++ ){

            if ( yardsList.get(i).id == id ) {
                return yardsList.get(i);
            }
        }

        return yard;
    }

    public String formJsonFromYard(Yards yards){

        return "";
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
}
