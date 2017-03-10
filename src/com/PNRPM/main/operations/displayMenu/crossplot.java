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

        Text text = new Text("Select the X and Y axis labels corresponding to Z-axis parameter.");
        legends.setTop(text);

        VBox xaxis = new VBox(5);
        xaxis.setPadding(new Insets(20));
        VBox yaxis = new VBox(5);
        yaxis.setPadding(new Insets(20));
        VBox zaxis = new VBox(5);
        zaxis.setPadding(new Insets(20));

        ToggleGroup group1 = new ToggleGroup();
        ToggleGroup group2 = new ToggleGroup();
        ToggleGroup group3 = new ToggleGroup();
        for(int i=1;i<noOfParameter;++i)
        {
            RadioButton button1 = new RadioButton(parameter[i]);
            button1.setMinWidth(150);
            button1.setToggleGroup(group1);
            if(i==2)
                button1.setSelected(true);
            xaxis.getChildren().add(button1);

            RadioButton button2 = new RadioButton(parameter[i]);
            button2.setMinWidth(150);
            button2.setToggleGroup(group2);
            if(i==2)
                button2.setSelected(true);
            yaxis.getChildren().add(button2);

            RadioButton button3 = new RadioButton(parameter[i]);
            button3.setMinWidth(150);
            button3.setToggleGroup(group3);
            if(i==1)
                button3.setSelected(true);
            zaxis.getChildren().add(button3);
        }

        ScrollPane x= new ScrollPane();
        x.setContent(xaxis);
        x.setFitToWidth(true);

        ScrollPane y= new ScrollPane();
        y.setContent(yaxis);
        y.setFitToWidth(true);

        ScrollPane z= new ScrollPane();
        z.setContent(zaxis);
        z.setFitToWidth(true);

        BorderPane xlabel = new BorderPane();
        Label xlegend = new Label("X-axis Parameter");
        xlegend.setMinWidth(170);
        xlegend.setAlignment(Pos.BASELINE_CENTER);
        xlabel.setTop(xlegend);
        xlabel.setLeft(x);

        BorderPane ylabel = new BorderPane();
        Label ylegend = new Label("Y-axis Parameter");
        ylegend.setMinWidth(170);
        ylegend.setAlignment(Pos.BASELINE_CENTER);
        ylabel.setTop(ylegend);
        ylabel.setCenter(y);

        BorderPane zlabel = new BorderPane();
        Label zlegend = new Label("Z-axis Parameter");
        zlegend.setMinWidth(170);
        zlegend.setAlignment(Pos.BASELINE_CENTER);
        zlabel.setTop(zlegend);
        zlabel.setRight(z);

        HBox labels= new HBox();
        labels.setSpacing(15);
        labels.setPadding(new Insets(10));
        labels.getChildren().addAll(xlabel,ylabel,zlabel);

        legends.setCenter(labels);

        final HBox controls = new HBox(10);
        controls.setPadding(new javafx.geometry.Insets(10));
        controls.setAlignment(Pos.BASELINE_RIGHT);

        final Button ok = new Button("Plot");
        final Button cancel = new Button("Cancel");
        cancel.setOnAction(e-> cPlot.close());

        controls.getChildren().addAll(ok, cancel);
        legends.setBottom(controls);

        Scene scene = new Scene(legends,620,400);
        cPlot.setScene(scene);
        cPlot.show();
        cPlot.setResizable(false);
    }
}
