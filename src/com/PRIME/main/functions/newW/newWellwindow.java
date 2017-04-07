package com.PRIME.main.functions.newW;

import com.PRIME.database.menubar.recentprojects;
import com.PRIME.database.utils.DBUtils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class newWellwindow {

    public static void newWellwindow(){
        Stage  wellload = new Stage();
        wellload.setTitle(fetchlatestproject());

        BorderPane wellview = new BorderPane();

        BorderPane grid = new BorderPane();
        grid.setPadding(new Insets(15));

        Label file = new Label("File");
        file.setPadding(new Insets(0,25,0,0));
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

        HBox hb = new HBox(25);
        Label lb = new Label("");
        Button Browse = new Button("Browse");
        hb.getChildren().addAll(lb,Browse);
        grid.setRight(hb);

        wellview.setTop(grid);

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