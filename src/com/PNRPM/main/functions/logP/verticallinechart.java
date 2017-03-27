package com.PNRPM.main.functions.logP;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class verticallinechart {

    public LineChart plotVerticalLineChart(int index, double values[][], double xlowerlimit, double xupperlimit,double ylowerlimit, double yupperlimit,int length, String parameter){
        //Defining the x axis
        NumberAxis xAxis = new NumberAxis(xlowerlimit,xupperlimit,(xupperlimit-xlowerlimit)/5);
        xAxis.setLabel(parameter);
        xAxis.setAutoRanging(false);

        //Defining the y axis
        NumberAxis yAxis = new NumberAxis(ylowerlimit,yupperlimit,-100);
        yAxis.setAutoRanging(false);
        if(index == 1)
            yAxis.setLabel("Depth in meter");

        //Creating the line chart
        LineChart linechart = new LineChart(xAxis, yAxis);

        linechart.setAnimated(false);
        linechart.setCreateSymbols(false);

        //Prepare XYChart.Series objects by setting data
        XYChart.Series series = new XYChart.Series();
        series.setName(parameter);

        for (int j=0;j<=length;++j){
            series.getData().add(new XYChart.Data(values[j][index],values[j][0]));
        }
        linechart.setAxisSortingPolicy(LineChart.SortingPolicy.NONE);

        linechart.getData().add(series);
        series.getNode().setStyle("-fx-stroke-width: 1;-fx-stroke: red; ");

        return linechart;
    }
}
