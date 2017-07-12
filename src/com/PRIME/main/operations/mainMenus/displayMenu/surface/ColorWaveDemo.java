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

        Shape polygon = new Shape();
        ColorMapper rainbowMap = new ColorMapper(new ColorMapRainbow(), 0, 1);
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
