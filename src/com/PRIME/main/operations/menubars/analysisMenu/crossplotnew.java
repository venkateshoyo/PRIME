package com.PRIME.main.operations.menubars.analysisMenu;

import com.PRIME.main.functions.crossP.xyzcrossplot;

public class crossplotnew {

    public void crossplots(double values[][], double range[][], String parameter[]) {
        if(range[0].length<3)
            System.out.println("Minimum 3 Parameters needed for crossplots");
        else {

            //By default crossplotting first three parameters
            xyzcrossplot ob = new xyzcrossplot();
            ob.crossplotdisplay(1, 2, 0, values, range, parameter);
        }
    }
}