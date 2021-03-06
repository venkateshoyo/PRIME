package com.PRIME.main.operations.main;

import com.PRIME.main.operations.close.closeconfirm;
import com.PRIME.main.operations.sidebar.SidebarPanel;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class home extends Application {
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

        //Confirming CLose window call, DB Checked.
        window.setOnCloseRequest(e -> {
            e.consume();
            closeconfirm.closeconfirm();
        });

        //creating menubar class object
        menubar mb= new menubar();

        layout = new BorderPane();

       // MenuBar menus = mb.displayMenuBar();
        //ToolBar tools = file.fileToolbar();

        VBox header = new VBox(0);
        header.getChildren().add(mb.displayMenuBar());

        layout.setTop(header);

        layout.setCenter(SidebarPanel.side());

        scene = new Scene(layout,600,500);
        window.setScene(scene);

        scene.getStylesheets().add(home.class.getResource("../../resources/css/main.css").toExternalForm());
        window.getIcons().add(new Image(getClass().getResourceAsStream("../../resources/images/main_favicon.png")));

        window.setMaximized(true);
        window.show();
        window.setOnCloseRequest(e->{System.exit(0);});

        //creating shortcut class object
        shortcuts.shortcuts();
    }
}