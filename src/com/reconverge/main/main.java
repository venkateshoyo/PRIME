package com.reconverge.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class main extends Application {
    Stage window;
    BorderPane layout;

    public static void main(String args[])
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window=primaryStage;
        window.setTitle("Reconverge");

        //File Menu
        Menu fileMenu = new Menu("file");
        Menu editMenu = new Menu("Edit");
        Menu projectMenu = new Menu("Project");
        Menu dataMenu = new Menu("Data");
        Menu plotsMenu = new Menu("Plots");
        Menu programmerMenu = new Menu("Programmer");
        Menu helpMenu = new Menu("Help");
        Menu aboutMenu = new Menu("About");

        //Menu Item
        fileMenu.getItems().add(new MenuItem("New File"));
        fileMenu.getItems().add(new MenuItem("Open File..."));
        fileMenu.getItems().add(new MenuItem("Open Folder..."));
        fileMenu.getItems().add(new MenuItem("Open Recent File"));
        fileMenu.getItems().add(new MenuItem("Save "));
        fileMenu.getItems().add(new MenuItem("Save As"));
        fileMenu.getItems().add(new MenuItem("Import"));
        fileMenu.getItems().add(new MenuItem("Export"));
        fileMenu.getItems().add(new MenuItem("Close File"));
        fileMenu.getItems().add(new MenuItem("Exit"));

        //Menu Bar
        MenuBar menuBar= new MenuBar();
        menuBar.getMenus().addAll(fileMenu,editMenu,projectMenu,dataMenu,plotsMenu,programmerMenu,helpMenu,aboutMenu);



        layout = new BorderPane();
        layout.setTop(menuBar);

        Scene scene = new Scene(layout,600,500);
        window.setScene(scene);
        window.show();
    }
}
