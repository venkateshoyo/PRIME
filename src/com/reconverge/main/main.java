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
        Menu fileMenu = new Menu("_File");
        Menu editMenu = new Menu("_Edit");
        Menu projectMenu = new Menu("_Project");
        Menu dataMenu = new Menu("_Data");
        Menu plotsMenu = new Menu("_Plots");
        Menu programmerMenu = new Menu("P_rogrammer");
        Menu helpMenu = new Menu("_Help");
        Menu aboutMenu = new Menu("_About");

        //Menu Item

        MenuItem newFile = new MenuItem("_New File");
        newFile.setOnAction(e -> System.out.println(""));

        MenuItem openFile = new MenuItem("_Open File...");
        openFile.setOnAction(e -> System.out.println());

        MenuItem openFolder = new MenuItem("Open _Folder...");
        openFolder.setOnAction(e -> System.out.println());

        MenuItem openRecent = new MenuItem("Open _Recent File");
        openRecent.setOnAction(e -> System.out.println());

        SeparatorMenuItem firstseparater= new SeparatorMenuItem();

        MenuItem save = new MenuItem("_Save ");
        save.setOnAction(e -> System.out.println());

        MenuItem saveAs = new MenuItem("Save _As");
        saveAs.setOnAction(e -> System.out.println());

        SeparatorMenuItem secondseparater= new SeparatorMenuItem();

        MenuItem importFile = new MenuItem("_Import");
        importFile.setOnAction(e -> System.out.println());

        MenuItem exportFile = new MenuItem("_Export");
        exportFile.setOnAction(e -> System.out.println());
        exportFile.setDisable(true);

        MenuItem settings = new MenuItem("Se_ttings");
        settings.setOnAction(e -> System.out.println());

        SeparatorMenuItem thirdseparater= new SeparatorMenuItem();

        MenuItem closeFile = new MenuItem("_Close File");
        closeFile.setOnAction(e -> System.out.println());

        MenuItem exit = new MenuItem("E_xit");
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
