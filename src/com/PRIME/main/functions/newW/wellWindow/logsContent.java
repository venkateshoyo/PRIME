package com.PRIME.main.functions.newW.wellWindow;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class logsContent {

    public static BorderPane logsContent(){
        BorderPane log = new BorderPane();

        Label label = new Label("Logs Content");
        log.setCenter(label);

        return log;
    }
}