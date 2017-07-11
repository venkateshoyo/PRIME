package com.PRIME.main.operations.mainMenus.displayMenu.surface;

import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Pair;
import org.jzy3d.plot3d.primitives.Scatter;


import javax.script.ScriptException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hkban on 7/9/2017.
 */
public class windowwell {
    Scatter scatter = null;
    Stage primaryStage = new Stage();
    static String name ="";


    GridPane gp = new GridPane();
    public Scatter coordinates()
    {
         int row =0;
         int column =0;
            Label WellName = new Label("WellName ");
            TextField well = new TextField();
            Button LoadWellLAS = new Button("LoadLASWell");
            LoadWellLAS.setOnAction(es->{
                Stage sp = new Stage();
                FileChooser file = new FileChooser();
                File filename = file.showOpenDialog(sp);
                try {
                    loadlasfil load = new loadlasfil();
                    Scatterplot plot = new Scatterplot();
                    scatter = plot.Scatterplot( load.loadlas(well.getText(),filename));

                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (ScriptException e1) {
                    e1.printStackTrace();
                }
                name = well.getText();
                primaryStage.close();
            });
            gp.add(WellName,0,++row);
            gp.add(well,1,row);
            gp.add(LoadWellLAS,2,row);
        gp.setHgap(10);
        gp.setVgap(10);
        Scene scene = new Scene(gp, 600, 100);
        scene.setFill(Color.GREY);
        primaryStage.setTitle("Molecule Sample Application");
        primaryStage.setScene(scene);
        primaryStage.showAndWait();
        return scatter;
    }

}
