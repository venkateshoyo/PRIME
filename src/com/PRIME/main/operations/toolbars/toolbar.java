package com.PRIME.main.operations.toolbars;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class toolbar extends Application {

    @Override
    public void start(Stage primaryStage) {

        //Setup the VBox Container and BorderPane
        BorderPane root = new BorderPane();
        VBox topContainer = new VBox();

        //Setup the Main Menu bar and the ToolBar
        MenuBar mainMenu = new MenuBar();
        ToolBar toolBar = new ToolBar();

        //Create SubMenu File.
        Menu file = new Menu("File");
        MenuItem openFile = new MenuItem("Open File");
        MenuItem exitApp = new MenuItem("Exit");
        file.getItems().addAll(openFile,exitApp);

        //Create SubMenu Edit.
        Menu edit = new Menu("Edit");
        MenuItem properties = new MenuItem("Properties");
        edit.getItems().add(properties);

        //Create SubMenu Help.
        Menu help = new Menu("Help");
        MenuItem visitWebsite = new MenuItem("Visit Website");
        help.getItems().add(visitWebsite);

        mainMenu.getMenus().addAll(file, edit, help);

        //Create some toolbar buttons
        Button openFileBtn = new Button();
        Button printBtn = new Button();
        Button snapshotBtn = new Button();

        //Add some button graphics
        openFileBtn.setGraphic(new ImageView("http://icons.iconarchive.com/icons/capital18/ethereal-2/128/Toolbar-Browser-Search-icon.png"));
        printBtn.setGraphic(new ImageView("http://icons.iconarchive.com/icons/capital18/ethereal-2/128/Toolbar-Browser-Search-icon.png"));
        snapshotBtn.setGraphic(new ImageView("http://icons.iconarchive.com/icons/capital18/ethereal-2/128/Toolbar-Browser-Search-icon.png"));

        toolBar.getItems().addAll(openFileBtn, printBtn, snapshotBtn);

        //Add the ToolBar and Main Meu to the VBox
        topContainer.getChildren().add(mainMenu);
        topContainer.getChildren().add(toolBar);

        //Apply the VBox to the Top Border
        root.setTop(topContainer);

        Scene scene = new Scene(root, 300, 250);

        //Setup the Stage.
        primaryStage.setTitle("MenuExample");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

}