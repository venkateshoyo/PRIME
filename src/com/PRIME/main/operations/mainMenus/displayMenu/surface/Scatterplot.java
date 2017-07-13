package com.PRIME.main.operations.mainMenus.displayMenu.surface;

import org.jzy3d.maths.Coord3d;
import org.jzy3d.plot3d.primitives.Scatter;
import org.renjin.sexp.DoubleVector;
import org.renjin.sexp.ListVector;

import java.io.IOException;

/**
 * Created by hkban on 7/9/2017.
 */
public class Scatterplot {

    public Scatter Scatterplot(ListVector well) throws IOException {

        ListVector vect = well;
        int size =(int)(well.get(0).length());
        Coord3d[] points = new Coord3d[size];
        DoubleVector pointsX = (DoubleVector)(vect.get(0));
        DoubleVector pointsY = (DoubleVector)(vect.get(1));
        DoubleVector pointsZ =(DoubleVector)(vect.get(2));
        //Color[]   colors = new Color[size];

        for (int i = 0; i < size; i++) {
            points[i] = new Coord3d(pointsX.get(i), pointsY.get(i), pointsZ.get(i));
            //System.out.println(scatterpoints[i].x+" "+scatterpoints[i].y+" "+scatterpoints[i].z);
        }
        Scatter scatter = new Scatter(points);

        scatter.setWidth(5f);
        return scatter;
    }
}
