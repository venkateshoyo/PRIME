package com.PRIME.javafx3D;


public class Coordinates {
    double longitude;
    double latitude;

    Coordinates(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }
    public double getLongitude(){
        return this.longitude;
    }
    public double getLatitude(){
        return this.latitude;
    }

    public static Coordinates[] transfer()
    {
        Coordinates [] sample = {new Coordinates(231.1908, 147.3136)
        , new Coordinates(405.5364, 139.2827),
                new Coordinates(528.066, 133.5066),
                new Coordinates(3616.3, 72.21759),
                new Coordinates(869.442, 112.8413)};

        return sample;
    }
}
