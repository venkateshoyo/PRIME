package com.PRIME.main.operations.menubars.fileMenu.settings.globalVariable;

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

        Tab lithologyTab = new Tab("Lithologies");
        lithologyTab.setClosable(false);
        lithologyTab.setContent(lithologies.lithoTable());

        Tab logIdTab= new Tab("LogID Strings and Display Units");
        logIdTab.setClosable(false);
        logIdTab.setContent(logID.logIDTable());

        Tab mineralTab = new Tab("Minerals");
        mineralTab.setClosable(false);

        Tab fluidTab = new Tab("fluids");
        fluidTab.setClosable(false);

        tabPane.getTabs().addAll(lithologyTab,logIdTab,mineralTab,fluidTab);

        layout.setCenter(tabPane);

        Stage mainUI = new Stage();
        mainUI.setTitle("Project Settings");

        Scene scene = new Scene(layout,900,700);
        mainUI.setScene(scene);

        mainUI.initModality(Modality.APPLICATION_MODAL);
        mainUI.showAndWait();

    }

}
