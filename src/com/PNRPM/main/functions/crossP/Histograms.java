package com.PNRPM.main.functions.crossP;

import javafx.geometry.Insets;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class Histograms {

    public BarChart vHistogram(String ylabel, double vHisto[], double vHistorange[]){
        final NumberAxis xAxis = new NumberAxis();
        final CategoryAxis yAxis = new CategoryAxis();

        final BarChart<Number,String> bc = new BarChart<>(xAxis,yAxis);
        bc.setPadding(new Insets(7,0,17,0));
        bc.setLegendVisible(false);
        bc.getYAxis().setTickLabelsVisible(false);
        bc.getYAxis().setOpacity(0);
        bc.setCategoryGap(0);
        bc.setBarGap(0);
//        xAxis.setLabel("Frequency");
        xAxis.setTickLabelRotation(90);
        yAxis.setLabel(ylabel);

        XYChart.Series series1 = new XYChart.Series();
        for(int i=1;i<=10;++i)
            series1.getData().add(new XYChart.Data(vHisto[i-1],vHistorange[i-1]+"-"+vHistorange[i]));
        bc.getData().addAll(series1);

        return bc;
    }

    public BarChart hHistogram(String xlabel, double hHisto[], double hHistorange[]){
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();

        final BarChart<String,Number> bc = new BarChart<>(xAxis,yAxis);
        bc.setPadding(new Insets(0,49,0,48));
        bc.setLegendVisible(false);
        bc.getXAxis().setTickLabelsVisible(false);
        bc.getXAxis().setOpacity(0);
        bc.setCategoryGap(0);
        bc.setBarGap(0);
        xAxis.setLabel(xlabel);
//        yAxis.setLabel("Frequency");

        XYChart.Series series1 = new XYChart.Series();
        for(int i=1;i<=20;++i)
            series1.getData().add(new XYChart.Data(hHistorange[i-1]+"-"+hHistorange[i],hHisto[i-1]));
        bc.getData().addAll(series1);

        return bc;
    }
}
