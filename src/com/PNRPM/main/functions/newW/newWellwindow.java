package com.PNRPM.main.functions.newW;

import com.PNRPM.database.menubar.recentprojects;
import com.PNRPM.database.utils.DBUtils;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
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

        GridPane grid = new GridPane();

        Label file = new Label("File");
        grid.add(file,0,0,1,1);

        TextField filepath = new TextField("Default file path");
        grid.add(filepath,1,0,4,1);

        Button Browse = new Button("Browse");
        grid.add(Browse,5,0,1,1);

        wellview.setTop(grid);

        Scene scene = new Scene(wellview);
        wellload.setMaximized(true);
        wellload.setScene(scene);
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