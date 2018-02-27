package com.PRIME.main.operations.menubars.projectMenu.projectSetting;

import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class main {

    public static void UI(){

        BorderPane layout = new BorderPane();

        TabPane tabPane = new TabPane();

        Tab set = new Tab("Lithologies");
        set.setClosable(false);
//        set.setContent(contentsContent.contentsContent());

        Tab contents= new Tab("LogID Strings and Display Units");
        contents.setClosable(false);

        Tab comments = new Tab("Minerals");
        comments.setClosable(false);

        Tab logs = new Tab("fluids");
        logs.setClosable(false);

        tabPane.getTabs().addAll(set,contents,comments,logs);

        layout.setCenter(tabPane);

        Stage mainUI = new Stage();
        mainUI.setTitle("Project Settings");

        Scene scene = new Scene(layout,600,500);
        mainUI.setScene(scene);

        mainUI.initModality(Modality.APPLICATION_MODAL);
        mainUI.showAndWait();

    }

}
