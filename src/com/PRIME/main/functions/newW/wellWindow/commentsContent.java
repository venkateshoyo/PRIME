package com.PRIME.main.functions.newW.wellWindow;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class commentsContent {

    public static BorderPane commentsContent(){
        BorderPane comments = new BorderPane();

        Label label = new Label("Comment Content");
        comments.setCenter(label);

        return comments;
    }
}