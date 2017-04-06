package com.PNRPM.main.functions.newW;

import com.PNRPM.database.utils.DBUtils;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class newProwindow {

    public DirectoryChooser directoryChooser = new DirectoryChooser();
    public File projectDirectory ;
    public File databaseDirectory ;

    public void newfunction(){
        Stage newWindow = new Stage();
        newWindow.initModality(Modality.APPLICATION_MODAL);

        GridPane nP = new GridPane();
        nP.setPadding(new Insets(40));
        nP.setHgap(15);
        nP.setVgap(10);

        Label proLabel = new Label("Project Name");
        proLabel.setId("text");
        nP.add(proLabel,0,0,2,1);

        TextField  proName = new TextField();
        proName.setMinWidth(365);
        nP.add(proName,2,0,5,1);

        Label projDir = new Label("Project Directory");
        projDir.setId("text");
        nP.add(projDir,0,1,2,1);

        TextField textDir = new TextField();
        nP.add(textDir,2,1,5,1);

        Button pDir = new Button("...");
        pDir.setOnAction(e -> {
            projectDirectory = directoryChooser.showDialog(newWindow);

            if(projectDirectory != null){
                textDir.setText(projectDirectory.getAbsolutePath()+"\\"+proName.getText());
            }
        });
        nP.add(pDir,7,1,1,1);

        Label dbDir = new Label("Database Directory");
        dbDir.setId("text");
        nP.add(dbDir,0,2,2,1);

        TextField textDb = new TextField();
        nP.add(textDb,2,2,5,1);

        Button pDb = new Button("...");
        pDb.setOnAction(e -> {
            databaseDirectory = directoryChooser.showDialog(newWindow);

            if(databaseDirectory != null){
                textDb.setText(databaseDirectory.getAbsolutePath()+"\\"+proName.getText());
            }
        });
        nP.add(pDb,7,2,1,1);

        proName.textProperty().addListener((observable, oldValue, newValue) -> {
            if(projectDirectory != null) {
                textDir.setText(projectDirectory.getAbsolutePath() + "\\" + proName.getText());
            }
            else{
                textDir.setText(proName.getText());
            }
            if(databaseDirectory != null) {
                textDb.setText(databaseDirectory.getAbsolutePath() + "\\" + proName.getText());
            }
            else{
                textDb.setText("Some_Default_Directory" + "\\" + proName.getText());
            }
            if (proName.getText().isEmpty())
                textDb.setText("");
        });

        Button create = new Button("Create");
        create.setOnAction(e -> {
            if (proName.getText()!=null && !(proName.getText()).isEmpty()) {
                Connection con = null;
                PreparedStatement stmt = null;
                ResultSet rs = null;

                String TABNAME = "`pnrpm`.`projects`";
                String query = DBUtils.prepareInsertQuery(TABNAME, "`Project Name`", "?");
                try {
                    con = DBUtils.getConnection();
                    stmt = con.prepareStatement(query);
                    stmt.setString(1, proName.getText());
                    stmt.executeUpdate();
                } catch (SQLException sql) {
                    sql.printStackTrace();
                } finally {
                    DBUtils.closeAll(rs, stmt, con);
                }
            }
        });
        create.setPadding(new Insets(5));

        Button cancel = new Button("Cancel");
        cancel.setPadding(new Insets(5));
        cancel.setOnAction(e -> {
            e.consume();
            newWindow.close();
        });

        HBox hb = new HBox(20);
        hb.setAlignment(Pos.BASELINE_RIGHT);
        hb.getChildren().addAll(create,cancel);
        nP.add(hb,6,7,2,1);

        Scene scene= new Scene(nP,550,250);
        newWindow.setScene(scene);
        newWindow.show();
        newWindow.setTitle("Create New Project");
        newWindow.setResizable(false);
        scene.getStylesheets().add(newProwindow.class.getResource("../../resources/css/themes/maintheme1.css").toExternalForm());
        newWindow.getIcons().add(new Image(getClass().getResourceAsStream("../../resources/images/main_favicon.png")));
    }
}