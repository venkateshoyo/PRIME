package com.PNRPM.main.operations.displayMenu;

import com.PNRPM.main.functions.crossP.xyzcrossplot;

public class crossplotnew {

    public void crossplots(double values[][], double range[][], String parameter[]) {
        xyzcrossplot ob = new xyzcrossplot();
        ob.crossplotdisplay(1,2,0,values,range,parameter);
    }
}