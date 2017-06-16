package com.PRIME.javafx3D;

import java.io.*;
import java.util.ArrayList;
import javafx.scene.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/**
 *
 * @author Sean
 */
public class loadwell {


    public static Group meshView() {
        final int EARTH_RADIUS = 500;
        Group meshView = new Group();
        ArrayList<Double> dataX = new ArrayList<>();
        ArrayList<Double> dataY = new ArrayList<>();
        ArrayList<Double> dataZ = new ArrayList<>();
        BufferedReader bufferedReader;
        try {
            //BufferedReader fot  file input
            Stage stage = new Stage();
            final FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(stage);
            bufferedReader = new BufferedReader(new FileReader(file));
            bufferedReader.readLine();
            String text;
            Integer count = 0;

            //Reading each line and storing each parameter value to temporary matrix
            while ((text = bufferedReader.readLine()) != null && text.length() > 0) {


                //Checking for empty lines
                if (count<1000) {
                    String text2 = text.replaceAll(",", " ");
                    int textindex = 0;

                    Double longitude = Double.parseDouble(text.substring(textindex, text2.indexOf(" ", textindex)));

                    //      System.out.print(text.substring(textindex, text2.indexOf(" ", textindex))+" ");
                    textindex = text2.indexOf(" ", textindex) + 1;
                    Double latitude = Double.parseDouble(text.substring(textindex, text2.indexOf(" ", textindex)));

                    //System.out.print(text.substring(textindex, text2.indexOf(" ", textindex))+" ");
                    textindex = text2.indexOf(" ", textindex) + 1;
                    Double depth = Double.parseDouble(text.substring(textindex, text2.indexOf(" ", textindex)));

                    //System.out.print(text.substring(textindex, text2.indexOf(" ", textindex))+" ");
                    textindex = text2.indexOf(" ", textindex) + 1;
                    Double value = Double.parseDouble(text.substring(textindex, text2.length()));
                    //Intensity.add(value);
                    // System.out.println(text.substring(textindex, text2.length()));

                    latitude = latitude * Math.PI / 180;
                    longitude = longitude * Math.PI / 180;


                    double x = EARTH_RADIUS * Math.sin(latitude) * Math.cos(longitude);
                    double y = EARTH_RADIUS * Math.sin(latitude) * Math.sin(longitude);
                    // double z = (EARTH_RADIUS * Math.cos(latitude))+depth;
                    double z = 1500 - depth;


                    dataX.add(x);
                    dataY.add(z);
                    dataZ.add(y);
                  /*  Sphere sphere1 = new Sphere(3);
                    sphere1.setTranslateX(x);
                    sphere1.setTranslateY(z);
                    sphere1.setTranslateZ(y);
                    meshView.getChildren().add(sphere1);*/
                    // System.out.println(count);
                }
                count++;


            }
            ScatterPlotMesh scatterPlotMesh = new ScatterPlotMesh(100, 1, true);
            scatterPlotMesh.setXYZData(dataX, dataY, dataZ);
            meshView.getChildren().add(scatterPlotMesh);

        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        } finally {
        }

        return meshView;

    }
}
