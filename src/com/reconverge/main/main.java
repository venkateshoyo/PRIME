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

        MenuItem newFile = new MenuItem("New File");
        newFile.setOnAction(e -> System.out.println());

        MenuItem openFile = new MenuItem("Open File...");
        openFile.setOnAction(e -> System.out.println());

        MenuItem openFolder = new MenuItem("Open Folder...");
        openFolder.setOnAction(e -> System.out.println());

        MenuItem openRecent = new MenuItem("Open Recent File");
        openRecent.setOnAction(e -> System.out.println());

        SeparatorMenuItem firstseparater= new SeparatorMenuItem();

        MenuItem save = new MenuItem("Save ");
        save.setOnAction(e -> System.out.println());

        MenuItem saveAs = new MenuItem("Save As");
        saveAs.setOnAction(e -> System.out.println());

        SeparatorMenuItem secondseparater= new SeparatorMenuItem();

        MenuItem importFile = new MenuItem("Import");
        importFile.setOnAction(e -> System.out.println());

        MenuItem exportFile = new MenuItem("Export");
        exportFile.setOnAction(e -> System.out.println());

        MenuItem settings = new MenuItem("Settings");
        settings.setOnAction(e -> System.out.println());

        SeparatorMenuItem thirdseparater= new SeparatorMenuItem();

        MenuItem closeFile = new MenuItem("Close File");
        closeFile.setOnAction(e -> System.out.println());

        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(e -> System.out.println());

        fileMenu.getItems().addAll(newFile,openFile,openFolder,openRecent,firstseparater,save,saveAs,secondseparater,importFile,exportFile,settings,thirdseparater,closeFile,exit);

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
