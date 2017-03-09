package com.PNRPM.main.functions;

import javafx.beans.binding.BooleanBinding;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class verticalplots {

    public void displayplots(double values[][], double range[][], String parameter[]){
        Stage plotwindow = new Stage();
        HBox hb = new HBox(10);
        plotzoom ob = new plotzoom();
        for(int i=1;i<=2;++i){

            BorderPane border = new BorderPane();

            verticallinechart vlineobject = new verticallinechart();
            LineChart graph = vlineobject.plotVerticalLineChart(i,values,range[0][i],range[1][i],range[1][0],range[0][0],values.length-1, parameter[i]);

            border.setCenter(graph);

            Rectangle zoomRect = new Rectangle();
            zoomRect.setManaged(false);
            zoomRect.setFill(Color.LIGHTSEAGREEN.deriveColor(0, 1, 1, 0.5));
            border.setTop(zoomRect);

            final HBox controls = new HBox(10);
            controls.setPadding(new Insets(10));
            controls.setAlignment(Pos.CENTER);

            final Button zoomButton = new Button("Zoom");
            final Button resetButton = new Button("Reset");
            final int param=i;
            zoomButton.setOnAction(e -> ob.doZoom(zoomRect, graph, range[0][param],range[1][param]));
            final double lowerlimit=range[0][i];
            final double upperlimit=range[1][i];
            resetButton.setOnAction(e->
            {
                NumberAxis xAxis = (NumberAxis)graph.getXAxis();
                xAxis.setLowerBound(lowerlimit);
                xAxis.setUpperBound(upperlimit);
                NumberAxis yAxis = (NumberAxis)graph.getYAxis();
                yAxis.setLowerBound(range[1][0]);
                yAxis.setUpperBound(range[0][0]);

                zoomRect.setWidth(0);
                zoomRect.setHeight(0);
            });
            final BooleanBinding disableControls =
                    zoomRect.widthProperty().lessThan(5)
                            .or(zoomRect.heightProperty().lessThan(5));
            zoomButton.disableProperty().bind(disableControls);
            controls.getChildren().addAll(zoomButton, resetButton);

            ob.setUpZooming(zoomRect, graph);

            border.setBottom(controls);
            hb.getChildren().add(border);
        }
        Scene scene = new Scene(hb);
        plotwindow.setScene(scene);
        plotwindow.show();
    }
}
