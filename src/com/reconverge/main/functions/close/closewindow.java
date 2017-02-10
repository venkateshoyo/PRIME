package com.reconverge.main.functions.close;

import com.reconverge.main.main;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class closewindow{
        public void confirmclose(){

                main ob = new main();

                Stage confirm = new Stage();
                confirm.initModality(Modality.APPLICATION_MODAL);

                Label text= new Label("Are you sure you want to exit ?");
                confirm.setTitle("Confirm");

                Button yes = new Button("Yes");
                yes.setOnAction(e -> {
                        confirm.close();
                        ob.getstage().close();
                });

                Button no = new Button("No");
                no.setOnAction(e -> {
                        confirm.close();
                });

                HBox layout = new HBox();
                layout.setAlignment(Pos.CENTER);
                layout.setSpacing(10);
                layout.getChildren().addAll(yes,no);

                VBox layout11 = new VBox();
                layout11.setAlignment(Pos.CENTER);
                layout11.setSpacing(20);
                layout11.getChildren().addAll(text,layout);

                Scene scene1= new Scene(layout11,300,150);

                confirm.setScene(scene1);
                confirm.show();
                confirm.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        }
}
