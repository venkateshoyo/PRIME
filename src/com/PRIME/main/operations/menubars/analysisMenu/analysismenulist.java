package com.PRIME.main.operations.menubars.analysisMenu;

import com.PRIME.main.operations.menubars.dataMenu.loadlasfile;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class analysismenulist {

    public static Menu analysismenulist(Stage ob){
        //Analysis Menu
        Menu analysisMenu = new Menu("_Analysis");

        MenuItem singlewellview = new MenuItem( "Single _Well Viewer");
        singlewellview.setOnAction(e -> System.out.println(""));

        MenuItem multiwellview = new MenuItem( "_Multi Well Viewer");
        multiwellview.setOnAction(e -> System.out.println(""));

        MenuItem seimicviewer = new MenuItem( "_Seismic Viewer");
        seimicviewer.setOnAction(e -> System.out.println(""));

        MenuItem crossplots = new MenuItem( "_Cross Plots");
        crossplots.setOnAction(e -> {
            FileChooser loadlasdirrctory = new FileChooser();
            loadlasdirrctory.getExtensionFilters().add(new FileChooser.ExtensionFilter("LAS Files", "*.las"));
            loadlasdirrctory.setTitle("Load LAS file for crossplot ");
            File selectedlas =  loadlasdirrctory.showOpenDialog(ob);
            if(selectedlas == null){
                System.out.println("LAS file not selected");
            }else{
                loadlasfile lasobject = new loadlasfile();
                try {
                    lasobject.loadlas(selectedlas);
                }
                catch (IOException el){
                    el.printStackTrace();
                }
            }
        });

        analysisMenu.getItems().addAll(singlewellview,multiwellview,seimicviewer,crossplots);

        //Analysis Sub Menu ends--------------------------------------------------------------------------------------------

        return analysisMenu;
    }
}