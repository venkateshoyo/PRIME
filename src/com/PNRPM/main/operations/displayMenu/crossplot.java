package com.PNRPM.main.operations.displayMenu;

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class crossplot {

    public void crossplots(){

        int noOfParameter=6;
        String parameter[] = {"ToggleGroup","ToggleGroup","ToggleGroup","ToggleGroup","ToggleGroup","ToggleGroup"};

        Stage cPlot = new Stage();

        BorderPane legends = new BorderPane();

        Text text = new Text("Select the X and y axis legends for crossplots.");
        legends.setTop(text);

        VBox xaxis = new VBox(5);
        xaxis.setPadding(new Insets(20));
        VBox yaxis = new VBox(5);
        yaxis.setPadding(new Insets(20));

        ToggleGroup group1 = new ToggleGroup();
        ToggleGroup group2 = new ToggleGroup();
        for(int i=1;i<noOfParameter;++i)
        {
            RadioButton button1 = new RadioButton(parameter[i]);
            button1.setToggleGroup(group1);
            if(i==1)
                button1.setSelected(true);
            xaxis.getChildren().add(button1);

            RadioButton button2 = new RadioButton(parameter[i]);
            button2.setToggleGroup(group2);
            if(i==2)
                button2.setSelected(true);
            yaxis.getChildren().add(button2);
        }

        BorderPane xlabel = new BorderPane();
        xlabel.setLeft(xaxis);
        BorderPane ylabel = new BorderPane();
        ylabel.setLeft(yaxis);

        HBox labels= new HBox();
        labels.getChildren().addAll(xlabel,ylabel);

        legends.setCenter(labels);

        final HBox controls = new HBox(10);
        controls.setPadding(new javafx.geometry.Insets(10));
        controls.setAlignment(Pos.BASELINE_RIGHT);

        final Button ok = new Button("Plot");
        final Button cancel = new Button("Cancel");
        cancel.setOnAction(e-> cPlot.close());

        controls.getChildren().addAll(ok, cancel);
        legends.setBottom(controls);

        Scene scene = new Scene(legends,300,300);
        cPlot.setScene(scene);
        cPlot.show();
    }
}
