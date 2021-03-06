package com.PRIME.main.functions.newW.projectWindow;

import com.PRIME.database.mainMenus.dataMenu.projectload.projectload;
import com.PRIME.database.mainMenus.projectMenu.dbDirectory;
import com.PRIME.database.mainMenus.projectMenu.proDirectory;
import com.PRIME.database.utils.DBUtils;

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

        String proDir= proDirectory.proDirectory();
        TextField textDir = new TextField(proDir);
        nP.add(textDir,2,1,5,1);

        Button pDir = new Button("...");
        pDir.setOnAction(e -> {
            projectDirectory = directoryChooser.showDialog(newWindow);

            if(projectDirectory != null){
                if (projectDirectory.getAbsolutePath().length()==3)
                    textDir.setText(projectDirectory.getAbsolutePath() + proName.getText());
                else
                    textDir.setText(projectDirectory.getAbsolutePath() + "\\" + proName.getText());
            }
        });
        nP.add(pDir,7,1,1,1);

        Label dbDir = new Label("Database Directory");
        dbDir.setId("text");
        nP.add(dbDir,0,2,2,1);

        String dataDir= dbDirectory.databaseDirectory();
        TextField textDb = new TextField();
        nP.add(textDb,2,2,5,1);

        Button pDb = new Button("...");
        pDb.setOnAction(e -> {
            databaseDirectory = directoryChooser.showDialog(newWindow);

            if(databaseDirectory != null){
                if (databaseDirectory.getAbsolutePath().length()==3)
                    textDb.setText(databaseDirectory.getAbsolutePath() + proName.getText());
                else
                    textDb.setText(databaseDirectory.getAbsolutePath() + "\\" + proName.getText());
            }
        });
        nP.add(pDb,7,2,1,1);

        //______________________________________________________________________________________________

        proName.textProperty().addListener((observable, oldValue, newValue) -> {
            if(projectDirectory != null) {
                if (projectDirectory.getAbsolutePath().length()==3)
                    textDir.setText(projectDirectory.getAbsolutePath() + proName.getText());
                else
                    textDir.setText(projectDirectory.getAbsolutePath() + "\\" + proName.getText());
            }
            else{
                textDir.setText(proDir + "\\" + proName.getText());
            }
            if(databaseDirectory != null) {
                if (databaseDirectory.getAbsolutePath().length()==3)
                    textDb.setText(databaseDirectory.getAbsolutePath() + proName.getText());
                else
                    textDb.setText(databaseDirectory.getAbsolutePath() + "\\" + proName.getText());
            }
            else{
                textDb.setText(dataDir + "\\" + proName.getText());
            }
            if (proName.getText().isEmpty())
                textDb.setText("");
        });

        //______________________________________________________________________________________________

        Button create = new Button("Create");
        create.setOnAction(e -> {
            if (proName.getText()!=null && !(proName.getText()).isEmpty()) {
                projectload.projectload(proName.getText());
                e.consume();
                newWindow.close();
            }
        });
        create.setPadding(new Insets(5));

        Button cancel = new Button("Cancel");
        cancel.setPadding(new Insets(5));
        cancel.setOnAction(e -> {
            e.consume();
            newWindow.close();
        });

        //______________________________________________________________________________________________

        HBox hb = new HBox(20);
        hb.setAlignment(Pos.BASELINE_RIGHT);
        hb.getChildren().addAll(create,cancel);
        nP.add(hb,6,7,2,1);

        Scene scene= new Scene(nP,550,250);
        newWindow.setScene(scene);
        newWindow.show();
        newWindow.setTitle("Create New Project");
        newWindow.setResizable(false);
        scene.getStylesheets().add(newProwindow.class.getResource("../../../resources/css/themes/maintheme1.css").toExternalForm());
        newWindow.getIcons().add(new Image(getClass().getResourceAsStream("../../../resources/images/main_favicon.png")));
    }
}