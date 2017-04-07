package com.PRIME.main.functions.newW;

import com.PRIME.database.menubar.recentprojects;
import com.PRIME.database.utils.DBUtils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class newWellwindow {

    public static void newWellwindow(){
        Stage  wellload = new Stage();
        wellload.setTitle(fetchlatestproject());

        BorderPane wellview = new BorderPane();

        VBox vbHeader = new VBox(10);

        BorderPane grid = new BorderPane();
        grid.setPadding(new Insets(15));

        Label file = new Label("File");
        file.setPadding(new Insets(5,25,0,0));
        grid.setLeft(file);

        final ComboBox<String> combo = new ComboBox<String>();
        combo.getItems().addAll(
                "File Path 1",
                "File Path 2",
                "File Path 3",
                "File Path 4",
                "File Path 5"
        );
        combo.prefWidthProperty().bind(wellview.widthProperty());
        combo.setEditable(true);
        grid.setCenter(combo);

        HBox hbBrowse = new HBox(25);
        Label space = new Label("");
        Button Browse = new Button("Browse");
        hbBrowse.getChildren().addAll(space,Browse);
        grid.setRight(hbBrowse);

        HBox hbWellBy = new HBox(30);
        hbWellBy.setPadding(new Insets(0,15,0,0));

        Label wellBy = new Label("Import Well By: ");

        HBox radioButton = new HBox(20);
        radioButton.setPadding(new Insets(0));
        final ToggleGroup group = new ToggleGroup();
        RadioButton rb1 = new RadioButton("Well Name");
        rb1.setToggleGroup(group);
        rb1.setSelected(true);
        RadioButton rb2 = new RadioButton("API ID");
        rb2.setToggleGroup(group);
        RadioButton rb3 = new RadioButton("Well ID");
        rb3.setToggleGroup(group);
        radioButton.getChildren().addAll(rb1,rb2,rb3);

        Button wellImport = new Button("Import");

        hbWellBy.getChildren().addAll(wellBy,radioButton,wellImport);
        hbWellBy.setAlignment(Pos.BASELINE_RIGHT);

        vbHeader.getChildren().addAll(grid,hbWellBy);

        wellview.setTop(vbHeader);

        Scene scene = new Scene(wellview);
        wellload.setScene(scene);
        wellload.setMaximized(true);
        wellload.show();
    }

    public static String fetchlatestproject(){
        String project="";

        ResultSet rs = null;
        try{
            rs = recentprojects.recentprojects();
            rs.last();
            project = rs.getString("Project Name");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            DBUtils.closeResultSet(rs);
        }

        return project;
    }
}