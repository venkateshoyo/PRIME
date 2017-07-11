package com.PRIME.main.operations.mainMenus.displayMenu.surface;

import org.jzy3d.colors.Color;
import org.jzy3d.colors.ColorMapper;
import org.jzy3d.colors.colormaps.ColorMapRainbow;
import org.jzy3d.maths.Coord3d;
import org.jzy3d.plot3d.primitives.Point;
import org.jzy3d.plot3d.primitives.Polygon;
import org.jzy3d.plot3d.primitives.Shape;
import org.renjin.sexp.DoubleVector;
import org.renjin.sexp.ListVector;

import javax.script.ScriptException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hkban on 7/9/2017.
 */
public class Surfaceplot {

    public Shape Surfaceplot(ListVector vect,List<String> wellnames) throws IOException, ScriptException {


        DoubleVector pointsX = (DoubleVector)(vect.get(0));
        DoubleVector pointsY = (DoubleVector)(vect.get(1));
        DoubleVector pointsZ =(DoubleVector)(vect.get(2));
        interpolate values = new interpolate(wellnames);

        List<Double> intensity =values.interpolatesurface(vect,"NEUT");



        List<Polygon> polygons = new ArrayList<Polygon>();
        int size = (int)Math.sqrt(pointsX.length());
        ColorMapper rainbowMap1 = new ColorMapper(new ColorMapRainbow(), 0,5000);

        for (int i = 0; i < size-1; i++) {
            for (int j = 0; j < size-1; j++) {

                Polygon polygon = new Polygon();
                polygon.add(new Point(new Coord3d((double) (pointsX.get((i * size) + j)), (double) (pointsY.get((i * size) + j)), (double) (pointsZ.get((i * size) + j)))));
                polygon.add(new Point(new Coord3d((double) (pointsX.get((i * size) + j + 1)), (double) (pointsY.get((i * size) + j + 1)), (double) (pointsZ.get((i * size) + j + 1)))));
                polygon.add(new Point(new Coord3d((double) (pointsX.get(((i + 1) * size) + j + 1)), (double) (pointsY.get(((i + 1) * size) + j + 1)), (double) (pointsZ.get(((i + 1) * size) + j + 1)))));
                polygon.add(new Point(new Coord3d((double) (pointsX.get(((i + 1) * size) + j)), (double) (pointsY.get(((i + 1) * size) + j)), (double) (pointsZ.get(((i + 1) * size) + j)))));
               Double col =intensity.get(i*size+j)*10000;
               System.out.println(col);
               System.out.println(intensity.size());
                polygon.setColor(rainbowMap1.getColor(col.intValue()));
                polygons.add(polygon);
            }
        }
        Shape surface = new Shape(polygons);
        surface.setWireframeDisplayed(false);
        surface.setFaceDisplayed(true);
        // surface.setColor(new Color(0,258,0,0.1f));
        surface.setWireframeColor(Color.RED);


        return surface;
    }
}
