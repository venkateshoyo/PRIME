package com.PRIME.main.operations.menubars.displayMenu.surface;

import javafx.util.Pair;
import org.jzy3d.colors.Color;
import org.jzy3d.colors.ColorMapper;
import org.jzy3d.colors.IColorMappable;
import org.jzy3d.colors.colormaps.ColorMapRainbow;
import org.jzy3d.colors.colormaps.IColorMap;

import java.util.HashMap;

/**
 * Created by hkban on 7/9/2017.
 */
public class MyColorFunction implements IColorMap {  //Define the function to assign colors to each point
    boolean colorMapDirection= true;
    ColorMapper rainbowMap;
    Color myColor;
    float energy ;
    public float zMin;
    public float zMax;
    double scale;
    HashMap< Pair<Double,Double>,Float > color = new HashMap<>();


    public MyColorFunction(HashMap< Pair<Double,Double>,Float > color){
        this.zMin = 0;
        this.zMax = 1;
        this.rainbowMap = new ColorMapper(new ColorMapRainbow(), this.zMin, this.zMax);
        this.scale= 2.0*Math.PI/100.0;
        this.color = color;
    }

    public void setDirection(boolean isStandard){
        colorMapDirection= isStandard;
    };
    public boolean getDirection(){
        return colorMapDirection;
    }
    public Color getColor(IColorMappable colorable, float x, float y, float z) {   //Returns the color of point(x,y,z)
        energy= (color.get((new Pair<Double,Double>((double)x,(double)y))));

        myColor= rainbowMap.getColor(energy); // We get the  color using the very useful RainbowMap
       // myColor.alphaSelf((float) Math.abs(energy/5.0));			// Giving some transparency to view the inside points
        return myColor;
    }

    public Color getColor(IColorMappable colorable, float v) {
        myColor= rainbowMap.getColor(v); // We get the  color using the very useful RainbowMap
        return myColor;
    };
}