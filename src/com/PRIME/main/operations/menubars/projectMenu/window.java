package com.PRIME.main.operations.menubars.projectMenu;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Pair;

/**
 * Created by hkban on 6/18/2017.
 */
public class window {
    static double latid = 0;
    static double longit = 0;
    static String TextIn ="";

    public Pair< Pair<Double, Double>,String> coordinates()

    {
        Stage primaryStage = new Stage();
        Label latitude = new Label("latitude");
        Label longitude = new Label("longitude");
        Label Text = new Label("Text");
        TextField lat = new TextField();
        TextField longi = new TextField();
        TextField tex = new TextField();
        Button Submit = new Button("Load");

        GridPane gp = new GridPane();
        gp.add(latitude, 0, 0);
        gp.add(lat, 1, 0);
        gp.add(longitude, 0, 1);
        gp.add(longi, 1, 1);
        gp.add(Submit, 0, 3);
        gp.add(Text,0,2);
        gp.add(tex,1,2);

        Scene scene = new Scene(gp, 300, 100);
        scene.setFill(Color.GREY);
        primaryStage.setTitle("Molecule Sample Application");
        primaryStage.setScene(scene);





        Submit.setOnAction(e -> {
            latid = Double.parseDouble(lat.getText());
            longit = Double.parseDouble(longi.getText());
            TextIn =tex.getText();

            primaryStage.close();
        });

        primaryStage.setOnCloseRequest(e->{
            TextIn = "empty";
           // primaryStage.close();
        });

        primaryStage.showAndWait();
        return new Pair< Pair<Double, Double>,String>(new Pair<Double, Double>(latid,longit),TextIn);
    }


}


