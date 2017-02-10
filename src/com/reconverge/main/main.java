package com.reconverge.main;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class main extends Application {
    public Stage window;
    public BorderPane layout;
    public static Scene scene;

    public static void main(String args[])
    {
        launch(args);
    }

    public static Scene getscene() { return scene; }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window=primaryStage;
        window.setTitle("Reconverge");

//        window.setOnCloseRequest(e -> {
//            e.consume();
//            confirmclose();
//        });

        //calling menubar class
        menubar mb= new menubar();
        MenuBar sample = mb.displayMenuBar();

        layout = new BorderPane();
        layout.setTop(sample);

        scene = new Scene(layout,600,500);
        window.setScene(scene);

        scene.getStylesheets().add(main.class.getResource("sample.css").toExternalForm());
        window.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));

        window.show();

        //calling shortcuts class
        shortcuts sc= new shortcuts();
        sc.shortcuts();
    }

    public void confirmclose(){
        Stage confirm = new Stage();
        confirm.initModality(Modality.APPLICATION_MODAL);

        Label text= new Label("Are you sure you want to exit ?");
        confirm.setTitle("Confirm");

        Button yes = new Button("Yes");
        yes.setOnAction(e -> {
            confirm.close();
            window.close();
        });

        Button no = new Button("No");
        no.setOnAction(e -> {
            confirm.close();
        });

        HBox layout = new HBox();
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(10);
        layout.getChildren().addAll(yes,no);

        VBox layout11 = new VBox();
        layout11.setAlignment(Pos.CENTER);
        layout11.setSpacing(20);
        layout11.getChildren().addAll(text,layout);

        Scene scene1= new Scene(layout11,300,150);
//        scene1.setOnKeyPressed(e -> System.out.println("Key pressed: "+ e.getCode()));

        confirm.setScene(scene1);
        confirm.show();
        confirm.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
    }
}
