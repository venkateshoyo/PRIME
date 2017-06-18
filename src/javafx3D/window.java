package javafx3D;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
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
    static double latid = 90;
    static double longit = 45;

    public Pair<Double, Double> coordinates()

    {
        Stage primaryStage = new Stage();
        Label latitude = new Label("latitude");
        Label longitude = new Label("longitude");
        TextField lat = new TextField();
        TextField longi = new TextField();
        Button Submit = new Button("Load");

        GridPane gp = new GridPane();
        gp.add(latitude, 0, 0);
        gp.add(lat, 1, 0);
        gp.add(longitude, 0, 1);
        gp.add(longi, 1, 1);
        gp.add(Submit, 0, 2);

        Scene scene = new Scene(gp, 300, 100);
        scene.setFill(Color.GREY);
        primaryStage.setTitle("Molecule Sample Application");
        primaryStage.setScene(scene);




        Submit.setOnAction(e -> {
            latid = Double.parseDouble(lat.getText());
            longit = Double.parseDouble(longi.getText());
            primaryStage.close();
        });



        primaryStage.showAndWait();
        return new Pair<Double, Double>(latid, longit);
    }


}


