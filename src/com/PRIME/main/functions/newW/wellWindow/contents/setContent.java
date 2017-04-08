package com.PRIME.main.functions.newW.wellWindow.contents;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class setContent {

    public static BorderPane setContent(){
        BorderPane set = new BorderPane();

        Label label = new Label("Set Content");
        set.setCenter(label);

        return set;
    }
}