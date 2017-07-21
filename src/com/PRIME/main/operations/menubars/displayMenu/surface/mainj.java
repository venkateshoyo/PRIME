package com.PRIME.main.operations.menubars.displayMenu.surface;

import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class mainj  {

    public void mainmethod() throws Exception {

        Stage primaryStage = new Stage();
        final SwingNode swingNode = new SwingNode();
        BorderPane layout = new BorderPane();

        layout.setCenter(swingNode);
        try {
            layout = new ColorWaveDemo().method();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(layout,1500,1500);
        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.setMaximized(true);
    }


}
