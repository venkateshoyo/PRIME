package com.reconverge.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import com.reconverge.main.functions.close.closewindow;

public class main extends Application {
    public static Stage window;
    public BorderPane layout;
    public static Scene scene;

    public static void main(String args[])
    {
        launch(args);
    }

    public static Scene getscene() { return scene; }
    public Stage getstage() { return window; }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window=primaryStage;
        window.setTitle("PNRPM");

        closewindow ob = new closewindow();

        window.setOnCloseRequest(e -> {
            e.consume();
            ob.confirmclose();
        });

        //calling menubar class
        menubar mb= new menubar();
        MenuBar sample = mb.displayMenuBar();

        layout = new BorderPane();
        layout.setTop(sample);

        scene = new Scene(layout,600,500);
        window.setScene(scene);

        scene.getStylesheets().add(main.class.getResource("sample.css").toExternalForm());
        window.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));

        window.setMaximized(true);
        window.show();

        //calling shortcuts class
        shortcuts sc= new shortcuts();
        sc.shortcuts();
    }
}