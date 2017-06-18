package com.PRIME.main.functions.logP;

import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class verticalplots {

    public void displayplots(double values[][], double range[][], String parameter[]){

        int noofplots=range[0].length-1;

        Stage plotwindow = new Stage();
        plotzoom ob = new plotzoom();

        ScrollPane plots = new ScrollPane();
        plots.setFitToHeight(true);

        Rectangle zoomRect = new Rectangle();
        zoomRect.setManaged(false);
        zoomRect.setFill(Color.LIGHTSEAGREEN.deriveColor(0, 1, 1, 0.5));

        LineChart[] graph = new LineChart[noofplots];

        verticallinechart vlineobject = new verticallinechart();

        HBox hb = new HBox(10);

        for(int i=1;i<=noofplots;++i){

            graph[i-1]=vlineobject.plotVerticalLineChart(i,values,range[0][i],range[1][i],range[1][0],range[0][0],values.length-1, parameter[i]);
            graph[i-1].setLegendVisible(false);
            ob.setUpZooming(zoomRect, graph[i-1]);

            if(noofplots>6)
                graph[i-1].setMaxWidth(180);
            else
                plots.setFitToWidth(true);

            hb.getChildren().add(graph[i-1]);
        }

        hb.getChildren().add(zoomRect);
        plots.setContent(hb);

        Scene scene = new Scene(plots);

        plots.setOnMousePressed(e-> zoomRect.setVisible(true));
        plots.setOnMouseReleased(e-> {
            if (!e.isDragDetect()) {
                for (int i = 1; i <= noofplots; ++i) {
                    final int param = i;
                    ob.doZoom(zoomRect, graph[i - 1], range[0][param], range[1][param]);
                }
                zoomRect.setVisible(false);
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

        scene.getStylesheets().add(verticalplots.class.getResource("../../resources/css/themes/maintheme1.css").toExternalForm());

        plotwindow.setScene(scene);
        plotwindow.show();
    }
}
