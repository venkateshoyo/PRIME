package com.PNRPM.main.functions;

import com.PNRPM.main.operations.main.main;
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
        series1.setName((int)zranges[0]+" - "+(int)zranges[1]+"  ");
        XYChart.Series series2 = new XYChart.Series();
        series2.setName((int)zranges[1]+" - "+(int)zranges[2]+"  ");
        XYChart.Series series3 = new XYChart.Series();
        series3.setName((int)zranges[2]+" - "+(int)zranges[3]+"  ");
        XYChart.Series series4 = new XYChart.Series();
        series4.setName((int)zranges[3]+" - "+(int)zranges[4]+"  ");
        XYChart.Series series5 = new XYChart.Series();
        series5.setName((int)zranges[4]+" - "+(int)zranges[5]+"  ");
        XYChart.Series series6 = new XYChart.Series();
        series6.setName((int)zranges[5]+" - "+(int)zranges[6]+"  ");
        XYChart.Series series7 = new XYChart.Series();
        series7.setName((int)zranges[6]+" - "+(int)zranges[7]+"  ");
        XYChart.Series series8 = new XYChart.Series();
        series8.setName((int)zranges[7]+" - "+(int)zranges[8]+"  ");

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
        scene.getStylesheets().add(main.class.getResource("../../resources/css/crossplot.css").toExternalForm());
        Stage crossplot = new Stage();
        crossplot.setScene(scene);
        crossplot.show();
    }
}