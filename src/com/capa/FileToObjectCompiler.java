package com.capa;

import com.capa.Country;
import com.capa.FileReader;

import java.util.ArrayList;

public class FileToObjectCompiler {
     private String[] a1;
     private String path;
     private ArrayList<Country> answearobjects = new ArrayList<>();

    public ArrayList<Country> getAnswearobjects() {
        return answearobjects;
    }
    public FileToObjectCompiler(String path) {
        this.path = path;
    }
    public void getAnswers() {
        try {
            answearobjects.clear();
            FileReader fileReader = new FileReader(path);
            for (String x : fileReader.getListOfAnswers()
            ) {
                a1 = x.split(";");
                Country country = new Country(a1[0],a1[1]);
                answearobjects.add(country);
            }
            fileReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
