package com.example.mobyardandroid.utils;

import java.io.File;
import java.io.FileNotFoundException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class YardsData {
    /*ArrayList<Yards> yardsArrayList;
    List<Yards> yardsList;

    String appDirYards;

    public YardsData() throws FileNotFoundException {
        appDirYards = getDir( "yards", MODE_PRIVATE);
        File dir = new File(appDirYards);
        File[] arrFiles = dir.listFiles();
        List<File> lst = Arrays.asList(arrFiles);

        File tempFile;

        for (int i = 0; i < lst.size(); i++ ) {
            tempFile = lst.get(i);

            String fileName = tempFile.getName();
            String content = Files.lines(
                    Paths.get(
                            fileName
                    ) ).reduce(
                    "",
                    String::concat
            );


        }




    }

    public void add(String yardId ) {



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
*/


}
