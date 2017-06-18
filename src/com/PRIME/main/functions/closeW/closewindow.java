package com.PRIME.main.functions.closeW;

import com.PRIME.database.closeWIndow.getCloseCheck;
import com.PRIME.database.closeWIndow.setCloseCheck;
import com.PRIME.database.utils.DBUtils;
import com.PRIME.main.operations.main.main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class closewindow {

    public void closefunction(){
        main ob = new main();

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        if(getCloseCheck.getCloseCheck()==1){
            ob.getstage().close();
            return;
        }

        Stage confirm = new Stage();
        confirm.initModality(Modality.APPLICATION_MODAL);

        Label text= new Label("Are you sure you want to exit ?");
        text.setId("labeltext");
        confirm.setTitle("Confirm");

        CheckBox dontask = new CheckBox("Don't ask me again");
        dontask.setId("dontagain");
        dontask.setSelected(true);

        Button yes = new Button("Yes");
        yes.setId("yes");
        yes.setOnAction(e -> {
            confirm.close();
            ob.getstage().close();
            if(dontask.isSelected())
                setCloseCheck.setCloseCheck();
        });

        Button no = new Button("No");
        no.setId("no");
        no.setOnAction(e -> confirm.close());

        HBox layout = new HBox();
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(30);
        layout.setPadding(new Insets(15,35,10,35));
        layout.getChildren().addAll(yes,no);

        VBox layout11 = new VBox();
        layout11.setAlignment(Pos.CENTER);
        layout11.setSpacing(5);
        layout11.getChildren().addAll(text,layout, dontask);

        Scene scene= new Scene(layout11,250,150);

        confirm.setScene(scene);
        confirm.show();
        confirm.setResizable(false);
        scene.getStylesheets().add(closewindow.class.getResource("../../resources/css/close.css").toExternalForm());
        confirm.getIcons().add(new Image(getClass().getResourceAsStream("../../resources/images/close_favicon.png")));
    }
}
