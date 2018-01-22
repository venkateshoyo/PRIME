package com.PRIME.main.operations.menubars.displayMenu.surface;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class display {

    public void mainmethod() throws Exception {

        Stage primaryStage = new Stage();
        BorderPane layout = new BorderPane();

        try {
            layout = new ColorWaveDemo().method();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(layout,1500,1500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
