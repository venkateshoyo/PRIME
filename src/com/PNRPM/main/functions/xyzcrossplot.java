package com.PNRPM.main.functions;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.layout.*;
import javafx.scene.paint.LinearGradient;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;


class ResizableRectangle extends Rectangle {
    ResizableRectangle(double w, double h) {
        super(w, h);
    }

    @Override
    public boolean isResizable() {
        return true;
    }

    @Override
    public double minWidth(double height) {
        return 0.0;
    }
}

public class xyzcrossplot {
    public int xindex;
    public int yindex;
    public int zindex;
    public double values[][];
    public double range[][];
    public String parameter[];
    public double hHisto[] = new double[20];
    public double vHisto[] = new double[10];
    public double hHistorange[] = new double[21];
    public double vHistorange[] = new double[11];

    Stage crossplot;

    public void crossplotdisplay(int x, int y, int z, double v[][],double r[][],String p[]){

        // Initialising global variables
        xindex=x;
        yindex=y;
        zindex=z;
        values=v;
        range=r;
        parameter=p;

        crossplot = new Stage();
        crossplot.show();

        GridPane grid = new GridPane();

        BorderPane crossP = scatterplot();

        Histograms ob= new Histograms();
        BarChart Vbc= ob.vHistogram(parameter[yindex],vHisto,vHistorange);
        grid.add(Vbc,0,0);
        GridPane.setVgrow(Vbc, Priority.ALWAYS);

        BarChart Hbc= ob.hHistogram(parameter[xindex],hHisto,hHistorange);
        grid.add(Hbc,1,4);
        GridPane.setHgrow(Hbc, Priority.ALWAYS);

        grid.add(crossP,1,0);
        GridPane.setHgrow(crossP, Priority.ALWAYS);
        GridPane.setVgrow(crossP, Priority.ALWAYS);

        Scene scene = new Scene(grid);
        crossplot.setScene(scene);

        crossplot.setMaximized(true);
        scene.getStylesheets().add(xyzcrossplot.class.getResource("../resources/css/crossplot.css").toExternalForm());
    }

    public BorderPane scatterplot(){

        BorderPane crossP = new BorderPane();
        crossP.setPadding(new Insets(0));

        BorderPane legend = new BorderPane();
        legend.setPadding(new Insets(0,10,50,10));

        Rectangle rect = new ResizableRectangle(25,crossP.getHeight());
        LinearGradient g = LinearGradient.valueOf("from 0% 0% to 0% 100%, #ff0000  6.25% , #f48024 18.75%,  #edff00  31.25%,  #25ff00 43.75%,  #00ffe9 56.25%,  #1d00ff 68.75%,  #ff00af 81.25%,  #40142a 93.75%");
        rect.setFill(g);
        rect.heightProperty().bind(crossP.heightProperty().subtract(50));
        legend.setCenter(rect);

        double difference=(range[1][zindex]-range[0][zindex])/8;
        double zranges[] = new double[9];

        VBox vb = new VBox(crossplot.getHeight()/10);
        vb.setPadding(new Insets(0,10,0,10));

        for (int j=0;j<=8;++j) {
            zranges[j] = range[0][zindex] + j * difference;
            Text a = new Text(zranges[j]+"");
            vb.getChildren().add(a);
        }
        crossP.heightProperty().addListener(e-> vb.setSpacing(crossplot.getHeight()/10));

//        legend.setRight(vb);

        crossP.setRight(legend);

        vHistorange[0]=0.9*range[0][yindex];
        double vdiff = (1.1*range[1][yindex]-0.9*range[0][yindex])/10;
        hHistorange[0]=0.9*range[0][xindex];
        double hdiff = (1.1*range[1][xindex]-0.9*range[0][xindex])/20;

        for(int i=1;i<=10;++i){
            vHistorange[i] = vHistorange[0] + i*vdiff;
            hHistorange[2*i-1] = hHistorange[0] + (2*i-1)*hdiff;
            hHistorange[2*i] = hHistorange[0] + 2*i*hdiff;
        }

        final NumberAxis xaxis = new NumberAxis(0.9*range[0][xindex],1.1*range[1][xindex],(range[1][xindex]-range[0][xindex])/15);
        final NumberAxis yaxis = new NumberAxis(0.9*range[0][yindex],1.1*range[1][yindex],(range[1][yindex]-range[0][yindex])/15);

        ScatterChart scatterchart = new ScatterChart(xaxis,yaxis);
        xaxis.setLabel(parameter[xindex]);
        yaxis.setLabel(parameter[yindex]);

        XYChart.Series series1 = new XYChart.Series();
        series1.setName(zranges[0]+" - "+zranges[1]+"  ");
        XYChart.Series series2 = new XYChart.Series();
        series2.setName(zranges[1]+" - "+zranges[2]+"  ");
        XYChart.Series series3 = new XYChart.Series();
        series3.setName(zranges[2]+" - "+zranges[3]+"  ");
        XYChart.Series series4 = new XYChart.Series();
        series4.setName(zranges[3]+" - "+zranges[4]+"  ");
        XYChart.Series series5 = new XYChart.Series();
        series5.setName(zranges[4]+" - "+zranges[5]+"  ");
        XYChart.Series series6 = new XYChart.Series();
        series6.setName(zranges[5]+" - "+zranges[6]+"  ");
        XYChart.Series series7 = new XYChart.Series();
        series7.setName(zranges[6]+" - "+zranges[7]+"  ");
        XYChart.Series series8 = new XYChart.Series();
        series8.setName(zranges[7]+" - "+zranges[8]+"  ");

        for (int i=0;i<values.length;++i)
        {
            if (values[i][zindex]<zranges[1])
                series1.getData().add(new XYChart.Data(values[i][xindex],values[i][yindex]));
            else if (values[i][zindex]>=zranges[1] && values[i][zindex]<zranges[2])
                series2.getData().add(new XYChart.Data(values[i][xindex],values[i][yindex]));
            else if (values[i][zindex]>=zranges[2] && values[i][zindex]<zranges[3])
                series3.getData().add(new XYChart.Data(values[i][xindex],values[i][yindex]));
            else if (values[i][zindex]>=zranges[3] && values[i][zindex]<zranges[4])
                series4.getData().add(new XYChart.Data(values[i][xindex],values[i][yindex]));
            else if (values[i][zindex]>=zranges[4] && values[i][zindex]<zranges[5])
                series5.getData().add(new XYChart.Data(values[i][xindex],values[i][yindex]));
            else if (values[i][zindex]>=zranges[5] && values[i][zindex]<zranges[6])
                series6.getData().add(new XYChart.Data(values[i][xindex],values[i][yindex]));
            else if (values[i][zindex]>=zranges[6] && values[i][zindex]<zranges[7])
                series7.getData().add(new XYChart.Data(values[i][xindex],values[i][yindex]));
            else
                series8.getData().add(new XYChart.Data(values[i][xindex],values[i][yindex]));

            boolean hflag=true;
            boolean vflag=true;
            for (int j=1;j<=10;++j){
                if(vflag && (values[i][yindex]>vHistorange[j-1] && values[i][yindex]<vHistorange[j])){
                    vHisto[j-1]++;
                    vflag=false;
                }
                if(hflag && (values[i][xindex]>hHistorange[2*j-2] && values[i][xindex]<hHistorange[2*j-1])){
                    hHisto[2*j-2]++;
                    hflag=false;
                }
                else if(hflag && (values[i][xindex]>hHistorange[2*j-1] && values[i][xindex]<hHistorange[2*j])){
                    hHisto[2*j-1]++;
                    hflag=false;
                }
                if(!hflag && !vflag)
                    j=11;
            }
        }

        scatterchart.getData().addAll(series1,series2,series3,series4,series5,series6,series7,series8);
        scatterchart.setLegendVisible(false);
        crossP.setCenter(scatterchart);
        return crossP;
    }
}