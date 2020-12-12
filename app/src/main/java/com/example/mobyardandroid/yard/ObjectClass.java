package com.example.mobyardandroid.yard;

import android.content.Context;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ObjectClass {

    boolean exist;
    String name, id, width, height, start, top, picture;

    Context context;
    String appDirObject;

    public ObjectClass(String id, String name, String width, String height, String start, String top, String picture) {
        this.name = name;
        this.id = id;
        this.width = width;
        this.height = height;
        this.start = start;
        this.top = top;
        this.picture = picture;
        this.exist = true;
    }

    public ObjectClass() {
        this.name = "";
        this.id = "";
        this.width = "0dp";
        this.height = "0dp";
        this.start = "0dp";
        this.top = "0dp";
        this.picture = "";
        this.exist = false;
    }

    public void objectData() throws IOException {

        context.getDir("objects", Context.MODE_PRIVATE);
        appDirObject = context.getDir("objects", Context.MODE_PRIVATE).getAbsolutePath();
        File dirFile = new File(appDirObject);
        File[] arrFiles = dirFile.listFiles();
        List<File> lst = Arrays.asList(arrFiles);

        String dir = appDirObject;
        dir += id + ".json";
        String info = formJsonFromObject();


        writeFile(dir, info);
    }

        public String formJsonFromObject(){

            return id + "," + name + "," + width + "," + height + "," + start + "," + top + "," + picture;
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
