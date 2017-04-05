package com.PNRPM.main.functions;

import com.PNRPM.database.utils.DBUtils;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class newProwindow {

    public void newfunction(){
        Stage confirm = new Stage();
        confirm.initModality(Modality.APPLICATION_MODAL);

        BorderPane nP = new BorderPane();
        nP.setPadding(new Insets(20));

        Label name = new Label("Enter Project Name");
        name.setId("name");
        name.setPadding(new Insets(5));

        TextField  text = new TextField();

        VBox vb = new VBox();
        vb.getChildren().addAll(name,text);

        Button create = new Button("Create");
        create.setOnAction(e -> {
            Connection con = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;

            String TABNAME = "`pnrpm`.`projects`";
            String query = DBUtils.prepareInsertQuery(TABNAME, "`Project Name`", "?");
            try{
                con = DBUtils.getConnection();
                stmt = con.prepareStatement(query);
                stmt.setString(1, text.getText());
                stmt.executeUpdate();
            }
            catch (SQLException sql){
//                sql.printStackTrace();
            }
            finally{
                DBUtils.closeAll(rs,stmt,con);
            }
        });
        create.setPadding(new Insets(5));

        Button cancel = new Button("Cancel");
        cancel.setOnAction(e -> {
            e.consume();
            confirm.close();
        });
        cancel.setPadding(new Insets(5));

        HBox hb = new HBox(20);
        hb.setAlignment(Pos.BASELINE_RIGHT);
        hb.getChildren().addAll(create,cancel);

        nP.setCenter(vb);
        nP.setBottom(hb);

        Scene scene= new Scene(nP,250,150);

        confirm.setScene(scene);
        confirm.show();
        confirm.setTitle("Create New Project");
        confirm.setResizable(false);
        scene.getStylesheets().add(newProwindow.class.getResource("../resources/css/themes/maintheme1.css").toExternalForm());
        confirm.getIcons().add(new Image(getClass().getResourceAsStream("../resources/images/main_favicon.png")));
    }
}