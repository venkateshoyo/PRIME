package com.PNRPM.main.operations.DataMenu;

import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class loadlasfile {

    public double values[][]= new double[4][4];
    public double datas=0.0,increment=0.0;
    public String parameter[]=new String[30];
    public int noOfParameter=-1;
    public double range[][];

    public void loadlas(File selectedlas)throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new FileReader(selectedlas));
        try {
            bufferedReader = new BufferedReader(new FileReader(selectedlas));
            String text;
            Pattern regex = Pattern.compile("(\\d+(?:\\.\\d+)?)");
            boolean Isdata = false, Isparameter = false;
            int index = 0, parameterindex = -1;
            while ((text = bufferedReader.readLine()) != null && text.length() > 0) {
                if (Isdata) {
                    text = (text.replaceAll("[ ]+", " ")).substring(1);
                    text += " ";
                    int textindex = 0;
                    while (text.indexOf(" ", textindex) > 0) {
                        if (++parameterindex == 0)
                            values[index][parameterindex] = 0.3048 * Double.parseDouble(text.substring(textindex, text.indexOf(" ", textindex)));
                        else{
                            values[index][parameterindex] = Double.parseDouble(text.substring(textindex, text.indexOf(" ", textindex)));
                            if(index==0){
                                range[0][parameterindex]=values[index][parameterindex];
                                range[1][parameterindex]=values[index][parameterindex];
                            }
                            else {
                                if(range[0][parameterindex]>values[index][parameterindex])
                                    range[0][parameterindex]=values[index][parameterindex];
                                if(range[1][parameterindex]<values[index][parameterindex])
                                    range[1][parameterindex]=values[index][parameterindex];
                            }
                        }
                            textindex = text.indexOf(" ", textindex) + 1;
                    }
                    ++index;
                    parameterindex = -1;
                } else if (text.length() > 4 && (text.substring(0, 4).equalsIgnoreCase("STRT"))) {
                    Matcher matcher = regex.matcher(text);
                    while (matcher.find()) {
                        datas -= Double.parseDouble(matcher.group(1));
                    }
                } else if (text.length() > 4 && (text.substring(0, 4).equalsIgnoreCase("STOP"))) {
                    Matcher matcher = regex.matcher(text);
                    while (matcher.find()) {
                        datas += Double.parseDouble(matcher.group(1));
                    }
                } else if (text.length() > 4 && (text.substring(0, 4).equalsIgnoreCase("STEP"))) {
                    Matcher matcher = regex.matcher(text);
                    while (matcher.find()) {
                        increment = Double.parseDouble(matcher.group(1));
                        datas /= increment;
                    }
                } else if (text.length() > 5 && (text.substring(1, 6).equalsIgnoreCase("depth"))) {
                    Isparameter = true;
                    parameter[++noOfParameter] = "Depth in meter";
                } else if (Isparameter && !(text.substring(0, 4).equalsIgnoreCase("~Asc"))) {
                    parameter[++noOfParameter] = text.substring(1, text.indexOf(" ", 1));
                } else if (text.length() > 4 && (text.substring(0, 4).equalsIgnoreCase("~Asc"))) {
                    Isdata = true;
                    values = new double[(int) datas + 1][noOfParameter + 1];
                    range= new double[2][noOfParameter + 1];
                }
            }
            range[0][0] = values[0][0];
            range[1][0] = values[(int) datas][0];
            displaylas();
        }
        catch (FileNotFoundException ex) {}
        catch (IOException ex) {}
        finally {}
    }

    public void displaylas(){
        Stage laswindow = new Stage();
        HBox hb = new HBox(10);
        for(int i=1;i<=1;++i){

            VBox vb= new VBox(10);
            vb.setPrefHeight(1000);

            LineChart graph = datadisplay(i);
            vb.getChildren().add(graph);

            Rectangle zoomRect = new Rectangle();
            zoomRect.setManaged(false);
            zoomRect.setFill(Color.LIGHTSEAGREEN.deriveColor(0, 1, 1, 0.5));
            vb.getChildren().add(zoomRect);

            final HBox controls = new HBox(10);
            controls.setPadding(new Insets(10));
            controls.setAlignment(Pos.CENTER);

            final Button zoomButton = new Button("Zoom");
            final Button resetButton = new Button("Reset");
            zoomButton.setOnAction(e -> doZoom(zoomRect, graph));
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
            setUpZooming(zoomRect, graph);

            vb.getChildren().add(controls);
            hb.getChildren().add(vb);
        }
        Scene scene = new Scene(hb);
        laswindow.setScene(scene);
        laswindow.show();
    }

    public LineChart datadisplay(int i){
        //Defining the x axis
        NumberAxis xAxis = new NumberAxis(range[0][i],range[1][i],(range[1][i]-range[0][i])/5);
        xAxis.setLabel(parameter[i]);
        xAxis.setAutoRanging(false);

        //Defining the y axis
        NumberAxis yAxis = new NumberAxis(range[1][0],range[0][0],-increment);
        yAxis.setAutoRanging(false);
        if(i == 1)
            yAxis.setLabel("Depth in meter");

        //Creating the line chart
        LineChart linechart = new LineChart(xAxis, yAxis);

        linechart.setAnimated(false);
        linechart.setCreateSymbols(false);

        //Prepare XYChart.Series objects by setting data
        XYChart.Series series = new XYChart.Series();
        series.setName(parameter[i]);

        for (int j=0;j<=(int)datas;++j){
            series.getData().add(new XYChart.Data(values[j][i],values[j][0]));
        }
        linechart.setAxisSortingPolicy(LineChart.SortingPolicy.NONE);

        linechart.getData().add(series);
        series.getNode().setStyle("-fx-stroke-width: 1;-fx-stroke: red; ");

        return linechart;
    }

    private void setUpZooming(final Rectangle rect, final Node zoomingNode) {
        final ObjectProperty<Point2D> mouseAnchor = new SimpleObjectProperty<>();
        zoomingNode.setOnMousePressed(e-> {
                mouseAnchor.set(new Point2D(e.getX(), e.getY()));
                rect.setWidth(0);
                rect.setHeight(0);
        });
        zoomingNode.setOnMouseDragged(event -> {
                double x = event.getX();
                double y = event.getY();
                rect.setX(Math.min(x, mouseAnchor.get().getX()));
                rect.setY(Math.min(y, mouseAnchor.get().getY()));
                rect.setWidth(Math.abs(x - mouseAnchor.get().getX()));
                rect.setHeight(Math.abs(y - mouseAnchor.get().getY()));
        });
    }

    private void doZoom(Rectangle zoomRect, LineChart<Number, Number> chart) {
        Point2D zoomTopLeft = new Point2D(zoomRect.getX(), zoomRect.getY());
        Point2D zoomBottomRight = new Point2D(zoomRect.getX() + zoomRect.getWidth(), zoomRect.getY() + zoomRect.getHeight());
        final NumberAxis yAxis = (NumberAxis) chart.getYAxis();
        Point2D yAxisInScene = yAxis.localToScene(0, 0);
        final NumberAxis xAxis = (NumberAxis) chart.getXAxis();
        Point2D xAxisInScene = xAxis.localToScene(0, 0);
        double xOffset = zoomTopLeft.getX() - yAxisInScene.getX() ;
        double yOffset = zoomBottomRight.getY() - xAxisInScene.getY();
        double xAxisScale = xAxis.getScale();
        double yAxisScale = yAxis.getScale();
        System.out.println(xAxisScale);
        System.out.println(yAxisScale);
//        xAxis.setLowerBound(xAxis.getLowerBound() + xOffset / xAxisScale);
        xAxis.setUpperBound(xAxis.getLowerBound() + zoomRect.getWidth() / xAxisScale);
        yAxis.setLowerBound(yAxis.getLowerBound() + yOffset / yAxisScale);
        yAxis.setUpperBound(yAxis.getLowerBound() - zoomRect.getHeight() / yAxisScale);
        zoomRect.setWidth(0);
        zoomRect.setHeight(0);
    }

}
