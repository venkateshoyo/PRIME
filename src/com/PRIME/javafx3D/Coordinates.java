package com.PRIME.javafx3D;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.paint.*;
import javafx.scene.transform.Rotate;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Pair;
import org.fxyz.shapes.composites.ScatterPlot;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static com.PRIME.main.operations.main.main.scene;


public  class Coordinates extends Application {
    private PerspectiveCamera camera;
    private final double sceneWidth = 600;
    private final double sceneHeight = 600;
    private double cameraDistance = 5000;
    private ScatterPlot scatterPlot;
    private double scenex, sceney, scenez = 0;
    private double fixedXAngle, fixedYAngle, fixedZAngle = 0;
    private final DoubleProperty angleX = new SimpleDoubleProperty(0);
    private final DoubleProperty angleY = new SimpleDoubleProperty(0);
    private final DoubleProperty angleZ = new SimpleDoubleProperty(0);
    Integer EARTH_RADIUS = 6371;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group sceneRoot = new Group();
        Scene scene = new Scene(sceneRoot, sceneWidth, sceneHeight, true, SceneAntialiasing.BALANCED);
        scene.setFill(javafx.scene.paint.Color.BLACK);
        camera = new PerspectiveCamera(true);
        camera.setNearClip(0.1);
        camera.setFarClip(10000.0);
        camera.setTranslateZ(-1000);
        scene.setCamera(camera);

        scatterPlot = new ScatterPlot(1000, 1, true);
        sceneRoot.getChildren().addAll(scatterPlot);
        ArrayList<Double> dataX = new ArrayList<>();
        ArrayList<Double> dataY = new ArrayList<>();
        ArrayList<Double> dataZ = new ArrayList<>();
        ArrayList<Double> Intensity = new ArrayList<>();

         List<Pair<Double,Double>> coordinates= new ArrayList<Pair<Double,Double>>();

        BufferedReader bufferedReader;
        try {
            //BufferedReader fot  file input
            Stage stage= new Stage();
            final FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(stage);
            bufferedReader = new BufferedReader(new FileReader(file));
            bufferedReader.readLine();
            String text;
            Integer count=0;
            //Reading each line and storing each parameter value to temporary matrix
            while ((text = bufferedReader.readLine()) != null && text.length() > 0) {

                //Checking for empty lines
                if (text.length() > 0) {
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
                            Intensity.add(value);
                   // System.out.println(text.substring(textindex, text2.length()));

                    latitude = latitude * Math.PI / 180;
                    longitude = longitude * Math.PI / 180;

                    double x = EARTH_RADIUS * Math.sin(latitude) * Math.cos(longitude);
                    double y = EARTH_RADIUS * Math.sin(latitude) * Math.sin(longitude);
                    double z = (EARTH_RADIUS * Math.cos(latitude))-depth;
                    dataX.add(longitude);
                    dataY.add(latitude);
                    dataZ.add(depth);
                    count++;


                }
            }
            System.out.println(count);
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        } finally {
        }
        scatterPlot.setXYZData(dataX, dataY, dataZ);
        //Add a Mouse Handler for Rotations
        Rotate xRotate = new Rotate(0, Rotate.X_AXIS);
        Rotate yRotate = new Rotate(0, Rotate.Y_AXIS);
        Rotate zRotate = new Rotate(0, Rotate.Z_AXIS);

        scatterPlot.getTransforms().addAll(xRotate, yRotate,zRotate);
        //Use Binding so your rotation doesn't have to be recreated
        xRotate.angleProperty().bind(angleX);
        yRotate.angleProperty().bind(angleY);
        zRotate.angleProperty().bind(angleZ);

        //Start Tracking mouse movements only when a button is pressed
        scene.setOnMousePressed(event -> {
            scenex = event.getSceneX();
            sceney = event.getSceneY();
            fixedXAngle = angleX.get();
            fixedYAngle = angleY.get();
            if(event.isMiddleButtonDown()) {
                scenez = event.getSceneX();
                fixedZAngle = angleZ.get();
            }

        });
        //Angle calculation will only change when the button has been pressed
        scene.setOnMouseDragged(event -> {
            if(event.isMiddleButtonDown())
                angleZ.set(fixedZAngle - (scenez - event.getSceneY()));
            else
                angleX.set(fixedXAngle - (scenex - event.getSceneY()));


            angleY.set(fixedYAngle + sceney - event.getSceneX());
        });

        primaryStage.setTitle("F(X)yz ScatterPlotTest");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
