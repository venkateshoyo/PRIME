package com.PRIME.main.functions.newW.wellWindow.contents;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class contentsContent {

    public static BorderPane contentsContent(){
        BorderPane content = new BorderPane();

        Label label = new Label("Content Content");
        content.setCenter(label);

        return content;
    }
}