package com.PRIME.main.operations.main.sidebar;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class inputBox
{

    public static String nameInput = "";
    public static String nameIn = "";

    public static String  display() {
        nameInput="";
        nameIn="";
        Stage window = new Stage();

        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Input Name");
        window.setMinWidth(250);

        Label  type = new Label("Save as type :: ");
        TextField name = new TextField("");
        TextField typein= new TextField("");
        typein.setPromptText("txt");

        Label label = new Label("        Name ::  ");
        Button input = new Button("Create");

        HBox named = new HBox(10);
        named.getChildren().addAll(label,name);
        named.setHgrow(name, Priority.ALWAYS);
        name.setMaxWidth(Double.MAX_VALUE);

        HBox typed = new HBox(10);
        typed.getChildren().addAll(type,typein);
        typed.setHgrow(typein, Priority.ALWAYS);
        typein.setMaxWidth(Double.MAX_VALUE);
        HBox empty = new HBox(10);
        Button cancel = new Button("Cancel");

        HBox button = new HBox(10);
        button.getChildren().addAll(empty,input,cancel);
        button.setHgrow(empty, Priority.ALWAYS);
        empty.setMaxWidth(Double.MAX_VALUE);

        input.setAlignment(Pos.TOP_LEFT);
        cancel.setAlignment(Pos.TOP_LEFT);
        input.setOnAction(e -> {
            nameInput = name.getText();
            nameIn = typein.getText();
            window.close();});

        cancel.setOnAction(e ->

                window.close());

        VBox layout = new VBox(20);
        layout.getChildren().addAll(named,typed,button);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30, 30, 30, 30));

        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        String out = nameInput + "."+ nameIn;

        return out;
    }

}
