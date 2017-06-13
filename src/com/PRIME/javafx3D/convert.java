package com.PRIME.javafx3D;

import javafx.geometry.Point3D;
import java.awt.*;
import java.awt.image.BufferedImage;
import static java.awt.image.ImageObserver.HEIGHT;
import static java.awt.image.ImageObserver.WIDTH;



public class convert {
    public static Point3D[] method() {
        final int EARTH_RADIUS = 100;
        final double FOCAL_LENGTH = 50;


        BufferedImage bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bi.createGraphics();
        Coordinates[] coordinates = Coordinates.transfer();
        Point3D [] temp=new Point3D[coordinates.length];
        int i=0;
        for (Coordinates coordinate : coordinates) {
            double latitude = Double.valueOf(coordinate.getLatitude());
            double longitude = Double.valueOf(coordinate.getLongitude());

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

