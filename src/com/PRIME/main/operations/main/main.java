package com.PRIME.main.operations.main;

import com.PRIME.main.operations.close.closeconfirm;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

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
        window.setTitle(System.getProperty("user.dir")+"   PRIME ES IITR 2017.1.0");

        window.setOnCloseRequest(e -> {
            e.consume();
            closeconfirm.closeconfirm();
        });

        //creating menubar class object
        menubar mb= new menubar();
        MenuBar sample = mb.displayMenuBar();

        layout = new BorderPane();
        layout.setTop(sample);

        scene = new Scene(layout,600,500);
        window.setScene(scene);

        scene.getStylesheets().add(main.class.getResource("../../resources/css/main.css").toExternalForm());
        window.getIcons().add(new Image(getClass().getResourceAsStream("../../resources/images/main_favicon.png")));

        window.setMaximized(true);
        window.show();

        //creating shortcut class object
        shortcuts sc= new shortcuts();
        sc.shortcuts();
    }
}