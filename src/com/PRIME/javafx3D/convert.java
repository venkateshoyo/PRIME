package com.PRIME.javafx3D;

import javafx.geometry.Point3D;
import javafx.util.Pair;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

import static java.awt.image.ImageObserver.HEIGHT;
import static java.awt.image.ImageObserver.WIDTH;



/*public class convert {
    public static Point3D[] method() {
        final int EARTH_RADIUS = 100;
        final double FOCAL_LENGTH = 50;


       List<Pair<Double,Double>> coordinates = Coordinates.transfer();

        System.out.print(coordinates.size());
        Point3D [] temp=new Point3D[coordinates.size()];
        Integer i=0;
        for (Pair<Double,Double> coordinate : coordinates) {
            double latitude = Double.valueOf(coordinate.getKey());
            double longitude = Double.valueOf(coordinate.getValue());

            latitude = latitude * Math.PI / 180;
            longitude = longitude * Math.PI / 180;

            double x = EARTH_RADIUS * Math.sin(latitude) * Math.cos(longitude);
            double y = EARTH_RADIUS * Math.sin(latitude) * Math.sin(longitude);
            double z = EARTH_RADIUS * Math.cos(latitude);
             temp[i]=  new Point3D(x, y, z);

            double projectedX = x * FOCAL_LENGTH / (FOCAL_LENGTH + z);
            double projectedY = y * FOCAL_LENGTH / (FOCAL_LENGTH + z);

            // scale the map bigger
            int magnifiedX = (int) Math.round(projectedX * 5);
            int magnifiedY = (int) Math.round(projectedY * 5);
                i++;
             }
            return temp;
        }
    }
*/
