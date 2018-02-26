package com.PRIME.main.operations.menubars.displayMenu.surface;

import org.jzy3d.colors.Color;
import org.jzy3d.colors.ColorMapper;
import org.jzy3d.colors.colormaps.ColorMapRainbow;
import org.jzy3d.maths.Coord3d;
import org.jzy3d.plot3d.primitives.*;
import org.renjin.sexp.DoubleVector;
import org.renjin.sexp.ListVector;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * Created by hkban on 7/10/2017.
 */
public class RegionPlot {

    public String parameter = "";
    public  List<String> wellnames = new ArrayList<>();
    public Scatter scatter= null;
    public interpolate values;

    ColorMapper rainbowMap1 = new ColorMapper(new ColorMapRainbow(), 0, 100);



    public RegionPlot() throws ScriptException {
    }

    public Shape Regionplot(ListVector vect1, ListVector vect2, List<String> wellnames, String paramvalue, String plotvalue) throws IOException, ScriptException {
        this.wellnames = wellnames;
        this.parameter = paramvalue;
        values = new interpolate(wellnames);
        boolean flag = false;

        DoubleVector pointsX = (DoubleVector) (vect1.get(0));
        DoubleVector pointsY = (DoubleVector) (vect1.get(1));
        DoubleVector pointsZ = (DoubleVector) (vect1.get(2));
        DoubleVector pointsX1 = (DoubleVector) (vect2.get(0));
        DoubleVector pointsY1 = (DoubleVector) (vect2.get(1));
        DoubleVector pointsZ1 = (DoubleVector) (vect2.get(2));
        double[] gap = new double[pointsX.length()];
        int zlength = 100;
        for (int i = 0; i < pointsX.length(); i++) {
            gap[i] = Math.abs(pointsZ1.get(i) - pointsZ.get(i)) / zlength;
        }

        int xlength = pointsX.length();

        int ylength = pointsY.length();


        ListVector temp;
        DoubleVector tempz ;
        if ((pointsZ1.get(0) >= pointsZ.get(0))) {
            temp = vect2;
            tempz = pointsZ1;
        } else {
            temp = vect1;
            tempz = pointsZ;
        }
        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine engine = factory.getEngineByName("Renjin");


        engine.put("Surf", temp);
        engine.put("step", gap);
        List<Polygon> polygons = new ArrayList<Polygon>();
        if (Objects.equals(plotvalue, "SurfacePlots")) {
            polygons.addAll(Surfaceplot(vect1, wellnames));
            polygons.addAll(Surfaceplot(vect2, wellnames));

            int size = (int) Math.sqrt(pointsX.length());
            int diff = 0;
            int val = size * (size - 1);
            List<Double> pointx1 = new ArrayList<>();
            List<Double> pointy1 = new ArrayList<>();
            List<Double> pointz1 = new ArrayList<>();
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < size-1 ; j++){
                    for (int k = 0; k < zlength+2; k++) {
                        pointx1.add(pointsX.get((i * val) + j));
                        pointy1.add(pointsY.get((i * val) + j));
                        pointz1.add((pointsZ.get((i * val) + j)) - gap[(i * val) + j]);
                    }
                }
            }
            List<Double> intensity1 = values.interpolatesurface(pointx1,pointy1,pointz1,parameter);
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < size-1 ; j++) {
                    for (int k = 0; k < zlength+1; k++) {

                        Polygon polygond1 = new Polygon();
                        polygond1.add(new Point(new Coord3d((double) (pointsX.get((i * val) + j)), (double) (pointsY.get((i * val) + j)), (double) (tempz.get((i * val) + j)) - (gap[(i * val) + j]*k))));
                        polygond1.add(new Point(new Coord3d((double) (pointsX.get((i * val) + j + 1)), (double) (pointsY.get((i * val) + j + 1)), (double) (tempz.get((i * val) + j + 1)) - (gap[(i * val) + j]*k))));
                        polygond1.add(new Point(new Coord3d((double) (pointsX.get(((i) * val) + j + 1)), (double) (pointsY.get(((i) * val) + j + 1)), (double) (tempz.get(((i) * val) + j + 1) - (gap[(i * val) + j]*(k+1))))));
                        polygond1.add(new Point(new Coord3d((double) (pointsX.get(((i) * val) + j)), (double) (pointsY.get(((i) * val) + j)), (double) (tempz.get(((i) * val) + j) - (gap[(i * val) + j]*(k+1))))));
                       // polygond1.setColor(new Color(258, 0, 0, 0.1f));
                        Double col = intensity1.get( (j*(zlength+1)+k)*(i+1) ) * 100;
                        polygond1.setColor(rainbowMap1.getColor(col.intValue()).alphaSelf(0.1f));
                        polygons.add(polygond1);
                    }
                }
            }

            List<Double> pointx2 = new ArrayList<>();
            List<Double> pointy2 = new ArrayList<>();
            List<Double> pointz2 = new ArrayList<>();
            for (int j = 0; j < 2; j++) {
                for (int i = 0; i < size-1 ; i++) {
                    for (int k = 0; k < zlength+2; k++) {
                        pointx2.add(pointsX.get((i * size) + j * (size - 1)));
                        pointy2.add(pointsY.get((i * size) + j * (size - 1)));
                        pointz2.add((pointsZ.get((i * size) + j * (size - 1)) - gap[(i * size) + j * (size - 1)]));
                    }
                }
            }
            List<Double> intensity2 = values.interpolatesurface(pointx2,pointy2,pointz2,parameter);
            for (int j = 0; j < 2; j++) {
                for (int i = 0; i < size-1 ; i++) {
                    for (int k = 0; k < zlength+1; k++) {
                        Polygon polygond1 = new Polygon();
                        polygond1.add(new Point(new Coord3d((double) (pointsX.get((i * size) + j * (size - 1))), (double) (pointsY.get((i * size) + j * (size - 1))), (double) (tempz.get((i * size) + j * (size - 1)) - (gap[(i * size) + j * (size - 1)]*k)))));
                        polygond1.add(new Point(new Coord3d((double) (pointsX.get(((i + 1) * size) + j * (size - 1))), (double) (pointsY.get(((i + 1) * size) + j * (size - 1))), (double) (tempz.get(((i + 1) * size) + j * (size - 1)) - (gap[(i * size) + j * (size - 1)]*k)))));
                        polygond1.add(new Point(new Coord3d((double) (pointsX.get(((i + 1) * size) + j * (size - 1))), (double) (pointsY.get(((i + 1) * size) + j * (size - 1))), (double) (tempz.get(((i + 1) * size) + j * (size - 1)) - (gap[(i * size) + j * (size - 1)]*(k+1))))));
                        polygond1.add(new Point(new Coord3d((double) (pointsX.get(((i) * size) + j * (size - 1))), (double) (pointsY.get(((i) * size) + j * (size - 1))), (double) (tempz.get(((i) * size) + j * (size - 1)) - (gap[(i * size) + j * (size - 1)]*(k+1))))));
                       // polygond1.setColor(new Color(258, 0, 0, 0.1f));
                        Double col = intensity2.get( ((i*(zlength+1))+k)*(j+1) ) * 100;
                        polygond1.setColor(rainbowMap1.getColor(col.intValue()).alphaSelf(0.1f));
                        polygons.add(polygond1);
                    }
                }
            }
            flag = true;
        }



        if (plotvalue == "ScatterPlots") {
            int sizevalue = (xlength * (zlength + 1));
            Coord3d points[] = new Coord3d[sizevalue];

            Color[] colors = new Color[sizevalue];

            for (int i = 0; i < zlength + 1; i++) {
                engine.put("i", i);
                if (i == 0) {
                    engine.eval("Surf$DEPTH = Surf$DEPTH ");
                } else {
                    engine.eval("Surf$DEPTH = Surf$DEPTH - step");
                }
                ListVector NewSurface = (ListVector) engine.eval("Surf");
                DoubleVector pointsXu = (DoubleVector) (NewSurface.get(0));
                DoubleVector pointsYu = (DoubleVector) (NewSurface.get(1));
                DoubleVector pointsZu = (DoubleVector) (NewSurface.get(2));
                List<Double> intensity = values.interpolatesurface(NewSurface, parameter);

                int length = pointsXu.length();

                for (int j = 0; j < length; j++) {
                    points[(i * (length)) + j] = new Coord3d(pointsXu.get(j), pointsYu.get(j), pointsZu.get(j));

                    Double col = intensity.get(j)*100;

                    colors[(i * length) + j] = rainbowMap1.getColor(col.intValue());
                }
            }
            scatter = new Scatter(points, colors);
            scatter.setWidth(5f);
        }
if(!flag)
        {
        int size = (int) Math.sqrt(pointsX.length());


        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1; j++) {

                Polygon polygon = new Polygon();
                polygon.add(new Point(new Coord3d((double) (pointsX.get((i * size) + j)), (double) (pointsY.get((i * size) + j)), (double) (pointsZ.get((i * size) + j)))));
                polygon.add(new Point(new Coord3d((double) (pointsX.get((i * size) + j + 1)), (double) (pointsY.get((i * size) + j + 1)), (double) (pointsZ.get((i * size) + j + 1)))));
                polygon.add(new Point(new Coord3d((double) (pointsX.get(((i + 1) * size) + j + 1)), (double) (pointsY.get(((i + 1) * size) + j + 1)), (double) (pointsZ.get(((i + 1) * size) + j + 1)))));
                polygon.add(new Point(new Coord3d((double) (pointsX.get(((i + 1) * size) + j)), (double) (pointsY.get(((i + 1) * size) + j)), (double) (pointsZ.get(((i + 1) * size) + j)))));
                polygon.setColor(new Color(0, 258, 0, 0.1f));
                polygons.add(polygon);
            }
        }


        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1; j++) {

                Polygon polygon = new Polygon();
                polygon.add(new Point(new Coord3d((double) (pointsX1.get((i * size) + j)), (double) (pointsY1.get((i * size) + j)), (double) (pointsZ1.get((i * size) + j)))));
                polygon.add(new Point(new Coord3d((double) (pointsX1.get((i * size) + j + 1)), (double) (pointsY1.get((i * size) + j + 1)), (double) (pointsZ1.get((i * size) + j + 1)))));
                polygon.add(new Point(new Coord3d((double) (pointsX1.get(((i + 1) * size) + j + 1)), (double) (pointsY1.get(((i + 1) * size) + j + 1)), (double) (pointsZ1.get(((i + 1) * size) + j + 1)))));
                polygon.add(new Point(new Coord3d((double) (pointsX1.get(((i + 1) * size) + j)), (double) (pointsY1.get(((i + 1) * size) + j)), (double) (pointsZ1.get(((i + 1) * size) + j)))));
                polygon.setColor(new Color(0, 258, 0, 0.1f));
                polygons.add(polygon);

            }
        }
        int diff = 0;
        int val = size * (size - 1);
        ListVector corner ;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < size - 1; j++)
            {

                    Polygon polygond1 = new Polygon();
                    polygond1.add(new Point(new Coord3d((double) (pointsX.get((i * val) + j)), (double) (pointsY.get((i * val) + j)), (double) (pointsZ.get((i * val) + j) + diff))));
                    polygond1.add(new Point(new Coord3d((double) (pointsX.get((i * val) + j + 1)), (double) (pointsY.get((i * val) + j + 1)), (double) (pointsZ.get((i * val) + j + 1) + diff))));
                    polygond1.add(new Point(new Coord3d((double) (pointsX1.get(((i) * val) + j + 1)), (double) (pointsY1.get(((i) * val) + j + 1)), (double) (pointsZ1.get(((i) * val) + j + 1) + diff))));
                    polygond1.add(new Point(new Coord3d((double) (pointsX1.get(((i) * val) + j)), (double) (pointsY1.get(((i) * val) + j)), (double) (pointsZ1.get(((i) * val) + j) + diff))));
                    polygond1.setColor(new Color(258, 0, 0, 0.1f));
                    polygons.add(polygond1);
                }

        }

        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < size - 1; i++) {

                Polygon polygond1 = new Polygon();
                polygond1.add(new Point(new Coord3d((double) (pointsX.get((i * size) + j * (size - 1))), (double) (pointsY.get((i * size) + j * (size - 1))), (double) (pointsZ.get((i * size) + j * (size - 1)) + diff))));
                polygond1.add(new Point(new Coord3d((double) (pointsX.get(((i + 1) * size) + j * (size - 1))), (double) (pointsY.get(((i + 1) * size) + j * (size - 1))), (double) (pointsZ.get(((i + 1) * size) + j * (size - 1)) + diff))));
                polygond1.add(new Point(new Coord3d((double) (pointsX1.get(((i + 1) * size) + j * (size - 1))), (double) (pointsY1.get(((i + 1) * size) + j * (size - 1))), (double) (pointsZ1.get(((i + 1) * size) + j * (size - 1)) + diff))));
                polygond1.add(new Point(new Coord3d((double) (pointsX1.get(((i) * size) + j * (size - 1))), (double) (pointsY1.get(((i) * size) + j * (size - 1))), (double) (pointsZ1.get(((i) * size) + j * (size - 1)) + diff))));
                polygond1.setColor(new Color(258, 0, 0, 0.1f));
                polygons.add(polygond1);
            }
        }
    }


        Shape surface = new Shape(polygons);
       // surface.setColor(new Color(258, 0, 0, 0.2f));
        // Creates the 3d object

        surface.setWireframeDisplayed(false);
        surface.setFaceDisplayed(true);
        //surface.setWireframeColor(Color.RED);



        return surface;
    }



    public List<Polygon> Surfaceplot(ListVector vect, List<String> wellnames) throws IOException, ScriptException {


        DoubleVector pointsX = (DoubleVector) (vect.get(0));
        DoubleVector pointsY = (DoubleVector) (vect.get(1));
        DoubleVector pointsZ = (DoubleVector) (vect.get(2));


        List<Double> intensity = values.interpolatesurface(vect,parameter);


        List<Polygon> polygons = new ArrayList<Polygon>();
        int size = (int) Math.sqrt(pointsX.length());


        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1; j++) {

                Polygon polygon = new Polygon();
                polygon.add(new Point(new Coord3d((double) (pointsX.get((i * size) + j)), (double) (pointsY.get((i * size) + j)), (double) (pointsZ.get((i * size) + j)))));
                polygon.add(new Point(new Coord3d((double) (pointsX.get((i * size) + j + 1)), (double) (pointsY.get((i * size) + j + 1)), (double) (pointsZ.get((i * size) + j + 1)))));
                polygon.add(new Point(new Coord3d((double) (pointsX.get(((i + 1) * size) + j + 1)), (double) (pointsY.get(((i + 1) * size) + j + 1)), (double) (pointsZ.get(((i + 1) * size) + j + 1)))));
                polygon.add(new Point(new Coord3d((double) (pointsX.get(((i + 1) * size) + j)), (double) (pointsY.get(((i + 1) * size) + j)), (double) (pointsZ.get(((i + 1) * size) + j)))));
                Double col = intensity.get(i * size + j) * 100;
                polygon.setColor(rainbowMap1.getColor(col.intValue()).alphaSelf(0.1f));
                polygons.add(polygon);
            }
        }
        return polygons;
    }

    public Scatter Scatterplot() throws IOException {

        return scatter;
    }

}
