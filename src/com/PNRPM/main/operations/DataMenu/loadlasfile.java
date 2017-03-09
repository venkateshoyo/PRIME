package com.PNRPM.main.operations.dataMenu;

import com.PNRPM.main.functions.plotzoom;
import com.PNRPM.main.functions.verticallinechart;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.binding.BooleanBinding;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
        BufferedReader bufferedReader;
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
        plotzoom ob = new plotzoom();
        for(int i=1;i<=8;++i){

            BorderPane border = new BorderPane();

            verticallinechart vlineobject = new verticallinechart();
            LineChart graph = vlineobject.plotVerticalLineChart(i,values,range[0][i],range[1][i],range[1][0],range[0][0],(int)datas, parameter[i]);

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
        laswindow.setScene(scene);
        laswindow.show();
    }
}
