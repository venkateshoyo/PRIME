package com.PNRPM.main.functions;

import javafx.beans.binding.BooleanBinding;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class verticalplots {

    public void displayplots(double values[][], double range[][], String parameter[]){

        int noofplots=2;

        Stage plotwindow = new Stage();
        plotzoom ob = new plotzoom();

        BorderPane plots = new BorderPane();

        Rectangle zoomRect = new Rectangle();
        zoomRect.setManaged(false);
        zoomRect.setFill(Color.LIGHTSEAGREEN.deriveColor(0, 1, 1, 0.5));

        LineChart[] graph = new LineChart[noofplots];

        verticallinechart vlineobject = new verticallinechart();

        HBox hb = new HBox(10);

        for(int i=1;i<=noofplots;++i){

            graph[i-1]=vlineobject.plotVerticalLineChart(i,values,range[0][i],range[1][i],range[1][0],range[0][0],values.length-1, parameter[i]);

            ob.setUpZooming(zoomRect, graph[i-1]);

            hb.getChildren().add(graph[i-1]);
        }


//        Button zoomButton = new Button("zoom");
//        zoomButton.setOnAction(e -> {
//            for (int i=1;i<=noofplots;++i){
//                final int param =i;
//                ob.doZoom(zoomRect, graph[i-1], range[0][param],range[1][param]);
//            }
//        });
        plots.setTop(zoomRect);

//        hb.getChildren().add(zoomButton);
        plots.setCenter(hb);

        Scene scene = new Scene(plots);

        scene.setOnDragDropped (e->{
            System.out.println("Drag over");
            for (int i=1;i<=noofplots;++i){
                final int param =i;
                ob.doZoom(zoomRect, graph[i-1], range[0][param],range[1][param]);
            }
        });

        scene.setOnMouseReleased(e-> {
            if(e.getButton().equals(MouseButton.PRIMARY)){
                if(e.getClickCount() == 2){
                    for (int i=1;i<=noofplots;++i){
                        NumberAxis yAxis = (NumberAxis)graph[i-1].getYAxis();
                        yAxis.setLowerBound(range[1][0]);
                        yAxis.setUpperBound(range[0][0]);
                        zoomRect.setWidth(0);
                        zoomRect.setHeight(0);
                    }
                }
            }
        });

        plotwindow.setScene(scene);
        plotwindow.setMaximized(true);
        plotwindow.show();
    }
}
