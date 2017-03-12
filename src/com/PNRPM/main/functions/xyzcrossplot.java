package com.PNRPM.main.functions;

import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class xyzcrossplot {

    public void crossplotdisplay(int x, int y, int z, double values[][],double range[][],String parameter[]){

        final NumberAxis xaxis = new NumberAxis(range[0][x],range[1][x],(range[1][x]-range[0][x])/15);
        final NumberAxis yaxis = new NumberAxis(range[0][y],range[1][y],(range[1][y]-range[0][y])/15);

        ScatterChart scatterchart = new ScatterChart(xaxis,yaxis);
        xaxis.setLabel(parameter[x]);
        yaxis.setLabel(parameter[y]);
        scatterchart.setTitle(parameter[x]+" vs "+parameter[y]+" with variation in "+parameter[z]);

        double difference=(range[1][z]-range[0][z])/8;
        double zranges[] = new double[9];

        for (int j=0;j<=8;++j)
            zranges[j]=range[0][z]+j*difference;

        XYChart.Series series1 = new XYChart.Series();
        series1.setName(zranges[0]+" - "+zranges[1]);
        XYChart.Series series2 = new XYChart.Series();
        series2.setName(zranges[1]+" - "+zranges[2]);
        XYChart.Series series3 = new XYChart.Series();
        series3.setName(zranges[2]+" - "+zranges[3]);
        XYChart.Series series4 = new XYChart.Series();
        series4.setName(zranges[3]+" - "+zranges[4]);
        XYChart.Series series5 = new XYChart.Series();
        series1.setName(zranges[4]+" - "+zranges[5]);
        XYChart.Series series6 = new XYChart.Series();
        series1.setName(zranges[5]+" - "+zranges[6]);
        XYChart.Series series7 = new XYChart.Series();
        series1.setName(zranges[6]+" - "+zranges[7]);
        XYChart.Series series8 = new XYChart.Series();
        series1.setName(zranges[7]+" - "+zranges[8]);

        for (int i=0;i<values.length;++i)
        {
            if (values[i][z]<zranges[1])
                series1.getData().add(new XYChart.Data(values[i][x],values[i][y]));
            else if (values[i][z]>=zranges[1] && values[i][z]<zranges[2])
                series2.getData().add(new XYChart.Data(values[i][x],values[i][y]));
            else if (values[i][z]>=zranges[2] && values[i][z]<zranges[3])
                series3.getData().add(new XYChart.Data(values[i][x],values[i][y]));
            else if (values[i][z]>=zranges[3] && values[i][z]<zranges[4])
                series4.getData().add(new XYChart.Data(values[i][x],values[i][y]));
            else if (values[i][z]>=zranges[4] && values[i][z]<zranges[5])
                series5.getData().add(new XYChart.Data(values[i][x],values[i][y]));
            else if (values[i][z]>=zranges[5] && values[i][z]<zranges[6])
                series6.getData().add(new XYChart.Data(values[i][x],values[i][y]));
            else if (values[i][z]>=zranges[6] && values[i][z]<zranges[7])
                series7.getData().add(new XYChart.Data(values[i][x],values[i][y]));
            else
                series8.getData().add(new XYChart.Data(values[i][x],values[i][y]));
        }
        scatterchart.getData().addAll(series1,series2,series3,series4,series5,series6,series7,series8);
        Scene scene = new Scene(scatterchart);
        Stage crossplot = new Stage();
        crossplot.setScene(scene);
        crossplot.show();
    }
}