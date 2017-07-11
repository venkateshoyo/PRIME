package com.PRIME.main.operations.mainMenus.displayMenu.surface;

import javafx.embed.swing.SwingNode;
import javafx.geometry.Point3D;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.util.Pair;
import org.jzy3d.chart.Chart;
import org.jzy3d.colors.ColorMapper;
import org.jzy3d.colors.colormaps.ColorMapRainbow;
import org.jzy3d.plot3d.primitives.*;
import org.jzy3d.plot3d.primitives.Shape;
import org.jzy3d.plot3d.primitives.axes.AxeBox;
import org.jzy3d.plot3d.rendering.canvas.Quality;
import org.jzy3d.plot3d.rendering.legends.colorbars.ColorbarLegend;
import javax.script.ScriptException;
import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;




public class ColorWaveDemo {
    static List< Pair<String,Point3D> > welldata = new ArrayList<>();
    static List<String> wellnames = new ArrayList<>();
    static ArrayList<Double> paramter = new ArrayList<>();


    int countsu = 0;
    int countsc =0;


    public BorderPane method() throws IOException {


        Chart chart1 = new Chart(Quality.Advanced, "swing");
        Chart chart = new Chart(Quality.Advanced, "swing"); // Use "swing" canvas

        chart.addController(new CameraMouseController(chart));
        final SwingNode swingNode = new SwingNode();
        BorderPane layout = new BorderPane();

        Button addsurface = new Button("loadsurface");
        Button region = new Button("region");
        Button addwell = new Button("addwell");

        addsurface.setOnAction(e -> {
            countsu++;
                windowsurface window = new windowsurface();
            Shape surface = null;
            try {
                surface = window.coordinates(wellnames);
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (ScriptException e1) {
                e1.printStackTrace();
            }
            chart.getScene().add(surface);
            chart1.getScene().add(surface);

            chart.getView().setBoundManual(chart1.getView().getBounds());
            chart.getView().setAxe(new AxeBox(chart1.getView().getBounds()));
            chart.getView().setSquared(true);
            chart.getView().setMaximized(false);


        });


        addwell.setOnAction(e -> {
            windowwell well = new windowwell();
            Scatter wellplot = well.coordinates();
            wellnames.add(well.name);
            chart1.getScene().add(wellplot);
            chart.getScene().add(wellplot);
            if (countsc == 0&& countsu==0) {
                chart.getView().setBoundManual(chart1.getView().getBounds());
                chart.getView().setAxe(new AxeBox(chart1.getView().getBounds().selfMargin(50.0f)));
                chart.getView().setSquared(true);
                countsc++;
            } else {
                chart.getView().setBoundManual(chart1.getView().getBounds());
                chart.getView().setAxe(new AxeBox(chart1.getView().getBounds()));
                if(countsu==0)
                    chart.getView().setAxe(new AxeBox(chart1.getView().getBounds().selfMargin(0.01f)));
                else
                    chart.getView().setAxe(new AxeBox(chart1.getView().getBounds()));
                chart.getView().setSquared(true);
            }
        });

        region.setOnAction(e -> {
            countsu++;
            windowregion window = new windowregion();
            Shape regionsh = null;
            try {
                regionsh = window.coordinates(wellnames);
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (ScriptException e1) {
                e1.printStackTrace();
            }
            chart.getScene().add(regionsh);
            chart1.getScene().add(regionsh);

            chart.getView().setBoundManual(chart1.getView().getBounds());
            chart.getView().setAxe(new AxeBox(chart1.getView().getBounds()));
            chart.getView().setSquared(true);
            chart.getView().setMaximized(false);
        });
//        Coord3d[] coord3ds= new Coord3d[6];
//        coord3ds[0]= new Coord3d(-39.43171,174.18095,3565.55040);
//        coord3ds[1]= new Coord3d(-39.47241,174.17548,5000.02440);
//        coord3ds[2]= new Coord3d(-39.49464,174.18576,3000.09760);
//        coord3ds[3]= new Coord3d(-39.45,174.183,5000);
//        coord3ds[4]= new Coord3d(-39.44,174.181,5400);
//        coord3ds[5]= new Coord3d(-39.48,174.185,5800);
//       // coord3ds[6]= new Coord3d(-39.46,174.173,900);
//        Scatter poi = new Scatter();
//        poi.setData(coord3ds);
//        poi.setWidth(10f);
//        poi.setColor(Color.BLACK);
//        chart1.getScene().add(poi);
//         chart.getScene().add(poi);
//        chart.getView().setAxe(new AxeBox(chart1.getView().getBounds()));
        Shape polygon = new Shape();
        ColorMapper rainbowMap = new ColorMapper(new ColorMapRainbow(), 0, 200000);
        polygon.setColorMapper(rainbowMap);
        ColorbarLegend cbar = new ColorbarLegend(polygon, chart1.getView().getAxe().getLayout());
        chart.getScene().add(polygon);
        chart.getAxeLayout().setXAxeLabel("latitude");
        chart.getAxeLayout().setYAxeLabel("longitude");
        chart.getAxeLayout().setZAxeLabel("depth");
        polygon.setLegend(cbar);
        polygon.setLegendDisplayed(true);
        swingNode.setContent((JComponent) chart.getCanvas());




        layout.setCenter(swingNode);
        HBox hbox = new HBox(addwell,addsurface,region);
        hbox.setSpacing(10);

        layout.setTop(hbox);


        return layout;

    }

    public ColorWaveDemo() {
    }

}



























//    public static JPanel  init() {
//
//        Chart chart = new Chart(Quality.Fastest,"swing"); // Use "swing" canvas
//        // chart = new Chart();
//        points =loadwell.meshView();
//        List<Polygon> polygons = new ArrayList<Polygon>();
//        for(int i = 0; i < 10; i++){
//            for(int j = 0; j < 10; j++){
//
//                Polygon polygon = new Polygon();
//                polygon.add(new Point( new Coord3d((double)(points.get((i*11)+j).getX()),(double)(points.get((i*11)+j).getY()), (double)(points.get((i*11)+j).getZ())  )));
//                polygon.add(new Point( new Coord3d((double)(points.get((i*11)+j+1).getX()), (double)(points.get((i*11)+j+1).getY()), (double)(points.get((i*11)+j+1).getZ())) ));
//                polygon.add(new Point( new Coord3d((double)(points.get(((i+1)*11)+j+1).getX()), (double)(points.get(((i+1)*11)+j+1).getY()), (double)(points.get(((i+1)*11)+j+1).getZ()) )));
//                polygon.add(new Point( new Coord3d((double)(points.get(((i+1)*11)+j).getX()), (double)(points.get(((i+1)*11)+j).getY()), (double)(points.get(((i+1)*11)+j).getZ())) ));
//                //polygon.setColor(new Color(258,0,258,1.0f));
//                polygons.add(polygon);
//            }
//        }
//
//        // Creates the 3d object
//        Shape surface = new Shape(polygons);
//        surface.setColorMapper(new ColorMapper(new ColorMapRainbow(), surface.getBounds().getZmin(), surface.getBounds().getZmax(), new org.jzy3d.colors.Color(1,1,1,1f)));
//        surface.setWireframeDisplayed(false);
//        surface.setFaceDisplayed(true);
//        // surface.setColor(new Color(0,258,0,0.1f));
//        surface.setWireframeColor(Color.RED);
//
//        // points1 =loadwell1.meshView();
//        double diff = 0;
//        List<Polygon> polygonsd = new ArrayList<Polygon>();
//        for(int i = 0; i < 10; i++){
//            for(int j = 0; j < 10; j++){
//
//                Polygon polygond = new Polygon();
//                polygond.add(new Point( new Coord3d((double)(points1.get((i*11)+j).getX()),(double)(points1.get((i*11)+j).getY()), (double)(points1.get((i*11)+j).getZ()+diff)  )));
//                polygond.add(new Point( new Coord3d((double)(points1.get((i*11)+j+1).getX()), (double)(points1.get((i*11)+j+1).getY()), (double)(points1.get((i*11)+j+1).getZ()+diff)) ));
//                polygond.add(new Point( new Coord3d((double)(points1.get(((i+1)*11)+j+1).getX()), (double)(points1.get(((i+1)*11)+j+1).getY()), (double)(points1.get(((i+1)*11)+j+1).getZ()+diff) )));
//                polygond.add(new Point( new Coord3d((double)(points1.get(((i+1)*11)+j).getX()), (double)(points1.get(((i+1)*11)+j).getY()), (double)(points1.get(((i+1)*11)+j).getZ()+diff)) ));
//                // polygond.setColor(new Color(0,0,258,1.0f));
//                polygonsd.add(polygond);
//            }
//        }
//
//
//        // Creates the 3d object
//        Shape surfaced = new Shape(polygonsd);
//        surfaced.setColorMapper(new ColorMapper(new ColorMapRainbow(), surfaced.getBounds().getZmin(), surfaced.getBounds().getZmax(), new org.jzy3d.colors.Color(1,1,1,1f)));
//        // surfaced.setColor(new Color(0,258,0,0.1f));
//        surfaced.setWireframeDisplayed(false);
//        surfaced.setFaceDisplayed(true);
//        surfaced.setWireframeColor(Color.RED);
//        ColorbarLegend cbar = new ColorbarLegend(surfaced, chart.getView().getAxe().getLayout());
//        surfaced.setLegend(cbar);
//
//
//        List<Polygon> polygonsd1 = new ArrayList<Polygon>();
//        for(int i = 0; i < 2; i++){
//            for(int j = 0; j < 10; j++){
//
//                Polygon polygond1 = new Polygon();
//                polygond1.add(new Point( new Coord3d((double)(points.get((i*110)+j).getX()),(double)(points.get((i*110)+j).getY()), (double)(points.get((i*110)+j).getZ()+diff)  )));
//                polygond1.add(new Point( new Coord3d((double)(points.get((i*110)+j+1).getX()), (double)(points.get((i*110)+j+1).getY()), (double)(points.get((i*110)+j+1).getZ()+diff)) ));
//                polygond1.add(new Point( new Coord3d((double)(points1.get(((i)*110)+j+1).getX()), (double)(points1.get(((i)*110)+j+1).getY()), (double)(points1.get(((i)*110)+j+1).getZ()+diff) )));
//                polygond1.add(new Point( new Coord3d((double)(points1.get(((i)*110)+j).getX()), (double)(points1.get(((i)*110)+j).getY()), (double)(points1.get(((i)*110)+j).getZ()+diff)) ));
//                polygonsd1.add(polygond1);
//            }
//        }
//
//        for(int j = 0; j < 2; j++){
//            for(int i = 0; i < 10; i++){
//
//                Polygon polygond1 = new Polygon();
//                polygond1.add(new Point( new Coord3d((double)(points.get((i*11)+j*10).getX()),(double)(points.get((i*11)+j*10).getY()), (double)(points.get((i*11)+j*10).getZ()+diff)  )));
//                polygond1.add(new Point( new Coord3d((double)(points.get(((i+1)*11)+j*10).getX()), (double)(points.get(((i+1)*11)+j*10).getY()), (double)(points.get(((i+1)*11)+j*10).getZ()+diff)) ));
//                polygond1.add(new Point( new Coord3d((double)(points1.get(((i+1)*11)+j*10).getX()), (double)(points1.get(((i+1)*11)+j*10).getY()), (double)(points1.get(((i+1)*11)+j*10).getZ()+diff) )));
//                polygond1.add(new Point( new Coord3d((double)(points1.get(((i)*11)+j*10).getX()), (double)(points1.get(((i)*11)+j*10).getY()), (double)(points1.get(((i)*11)+j*10).getZ()+diff)) ));
//                polygonsd1.add(polygond1);
//            }
//        }
//
//
//        // Creates the 3d object
//        Shape surfaced1 = new Shape(polygonsd1);
//        // surfaced1.setColorMapper(new ColorMapper(new ColorMapRainbow(), surfaced1.getBounds().getZmin(), surfaced1.getBounds().getZmax(), new org.jzy3d.colors.Color(1,1,1,1f)));
//        surfaced1.setColor(new Color(0,258,0,0.1f));
//        surfaced1.setWireframeDisplayed(false);
//        surfaced1.setFaceDisplayed(true);
//        surfaced1.setWireframeColor(Color.RED);
//
//
//
//        Cylinder test = new Cylinder();
//        test.setData(new Coord3d(  174.18095, -39.43171,  -3565.55040      ),-(float)Math.abs((double)3765.49920-3565.55040),0.0002f,20,20,Color.YELLOW);
//        test.setFaceDisplayed(true);
//        test.setWireframeDisplayed(false);
//
//        Cylinder test1 = new Cylinder();
//        test1.setData(new Coord3d(174.17548,-39.47241 , -500.02440     ),-((float)Math.abs((double)1200.15000-500.02440)),0.0002f,20,20,Color.BLUE);
//        test1.setFaceDisplayed(true);
//        test1.setWireframeDisplayed(false);
//
//        Cylinder test2 = new Cylinder();
//        test2.setData(new Coord3d(174.18576,-39.49464,   -2000.09760   ),-((float)Math.abs((double)2300.02080-2000.09760)),0.0002f,20,20,Color.GREEN);
//        test2.setFaceDisplayed(true);
//        test2.setWireframeDisplayed(false);
//
//
//        ArrayList<Double>  minmax = loadwell2.minmax();
//        paramter = loadwell2.parameter();
//        points2 =loadwell2.meshView();
//        int size =points2.size() ;
//
//
//
//
//
//        Coord3d[] points = new Coord3d[size];
//        Color[]   colors = new Color[size];
//        double minI =minmax.get(0);
//        double maxI = minmax.get(1);
//
//        for(int i=0; i<size; i++){
//
//
//            points[i] = new Coord3d(points2.get(i).getX(), points2.get(i).getY(), points2.get(i).getZ());
//
//
//
//            if(paramter.get(i)>minI && paramter.get(i) <maxI) {
//                float intense =(float)( javafx.scene.paint.Color.BLUE.getHue() + (javafx.scene.paint.Color.RED.getHue() - javafx.scene.paint.Color.BLUE.getHue()) * (paramter.get(i) - minI) / (maxI - minI)) ;
//                // colors[i] = new Color(new java.awt.Color(java.awt.Color.HSBtoRGB(intense, 1.0f, 1.0f)));
//                double diffe = maxI-minI;
//                double per = (paramter.get(i) - minI) / diffe;
//                diffe = diffe/3;
//                float red = (float)((258*(paramter.get(i) - minI-diffe-diffe)) / diffe);
//                float green = (float)((258*(paramter.get(i) - minI)) / diffe);
//                float blue =(float)((258*(paramter.get(i) - minI-diffe)) / diffe);
//
//                if(per>=0 && per<0.2)
//                {
//                    double con = per*5;
//                    double alt = 1-con;
//                    colors[i] = new Color(0,0,(float)(258*con),(float) con);
//                }
//                if(per>=0.2 && per<0.4)
//                {
//
//                    double con = (per-0.2)*5;
//                    double alt = 1-con;
//                    colors[i] = new Color(0,(float)(258*alt),(float)(258*con),(float) con);
//                }
//                if(per>=0.4 && per<0.6)
//                {
//                    double con = (per-0.4)*5;
//                    double alt = 1-con;
//                    colors[i] = new Color((float)(258*alt),0,(float)(258*con),(float) con);
//                }
//                if(per>=0.6 && per<0.8)
//                {
//                    double con = (per-0.6)*5;
//                    double alt = 1-con;
//                    colors[i] = new Color((float)(258*con),(float)(258*alt),0,(float) con);
//                }
//                if(per>=0.8 && per<=1)
//                {
//                    double con = (per-0.8)*5;
//                    double alt = 1-con;
//                    colors[i] = new Color((float)(258*con),0,0,(float) con);
//                }
//
//                if(green<0||green>258)
//                    green=0;
//                if(red<0||red>258)
//                    red=0;
//                if(blue<0||blue>258)
//                    blue=0;
//                //{colors[i] = new Color(red,green,blue,1.0f);}
//            }
//            else
//            {
//                colors[i] = new Color(1,1,1,0);
//            }
//            // colors[i]  = new Color(255*intense,0,0);
//
//        }
//
//
//
//
//        Scatter scatter = new Scatter(points, colors);
//
//        scatter.setWidth(2.0f);
//
//
//
//
//        //chart.getScene().add(surface1);
//        chart.getScene().add(scatter);
//
//
//
//        chart.getAxeLayout().setXAxeLabel("latitude");
//        chart.getAxeLayout().setYAxeLabel("longitude");
//        chart.getAxeLayout().setZAxeLabel("depth");
//
//
//        chart.getScene().add(surfaced);
//        chart.getScene().add(surface);
//        chart.getScene().add(test);
//        chart.getScene().add(test1);
//        chart.getScene().add(test2);
//        chart.getScene().add(surfaced1);
//
//        //chart.getView().setBackgroundColor(new Color(java.awt.Color.decode("#"+Integer.toHexString(0))));
//
//
//
//
//        chart.addController(new CameraMouseController(chart));
//
//
//
//
//        JPanel panel3d = new JPanel();
//
//
//        panel3d.setPreferredSize(new Dimension(800, 600)); // Set default size for panel3d
//        panel3d.setLayout(new java.awt.BorderLayout());
//        panel3d.add((JComponent)chart.getCanvas(), BorderLayout.CENTER); // Add chart in CENTER so that it will be resized automatically
//        return panel3d;
//    }
//}