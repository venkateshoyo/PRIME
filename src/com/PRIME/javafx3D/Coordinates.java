package com.PRIME.javafx3D;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;




public  class Coordinates{



    public static List<Pair<Double,Double>>transfer(){

         List<Pair<Double,Double>> coordinates= new ArrayList<Pair<Double,Double>>();

        BufferedReader bufferedReader;
        try {
            //BufferedReader fot  file input
            Stage stage= new Stage();
            final FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(stage);
            bufferedReader = new BufferedReader(new FileReader(file));

            String text;
            Integer count=0;
            System.out.print(bufferedReader.readLine());
            //Reading each line and storing each parameter value to temporary matrix
            while ((text = bufferedReader.readLine()) != null && text.length() > 0) {

                //Checking for empty lines
                if (text.length() > 0) {
                    int textindex = 0;
                    text = (text.replaceAll(",", " "));
                    Double longitude = Double.parseDouble(text.substring(textindex, text.indexOf(" ", textindex)));
                    Integer index = text.indexOf(" ",textindex);
                    Double latitude = Double.parseDouble(text.substring(index,text.length()));
                    coordinates.add(new Pair(longitude,latitude));


                    count++;
                }
            }

        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        } finally {
        }
        return coordinates;
    }
}
