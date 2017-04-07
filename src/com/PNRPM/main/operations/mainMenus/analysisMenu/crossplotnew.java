package com.PNRPM.main.operations.mainMenus.analysisMenu;

import com.PNRPM.main.functions.crossP.xyzcrossplot;

public class crossplotnew {

    public void crossplots(double values[][], double range[][], String parameter[]) {
        if(range[0].length<3)
            System.out.println("Minimum 3 Parameters needed for crossplots");
        else {
            xyzcrossplot ob = new xyzcrossplot();
            ob.crossplotdisplay(1, 2, 0, values, range, parameter);
        }
    }
}