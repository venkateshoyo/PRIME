package com.PRIME.main.operations.mainMenus.displayMenu.surface;

import org.renjin.sexp.DoubleVector;
import org.renjin.sexp.ListVector;
import smile.regression.RandomForest;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import smile.util.MulticoreExecutor;

/**
 * Created by HP on 7/11/2017.
 */
public class interpolate {

    public  RandomForest rf1;
    public  RandomForest rf2;
    public  interpolate( List<String> wellnames) throws ScriptException {

        loadlasfil data = new loadlasfil();
        HashMap<String,ListVector > welldata = data.allwell;
       ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine engine = factory.getEngineByName("Renjin");
//        engine.eval("StepDepth = (MaxDepth - MinDepth)/10000");
//        engine.eval("DEPTH = seq(MinDepth,MaxDepth,StepDepth)");
//        engine.eval("GRID = data.frame(LATI=Latitudes,LONG=Longitudes,DEPTH=DEPTH)");
        engine.eval("CombinedData = data.frame(LATI=c(),LONG=c(),DEPTH=c(),NEUT=c(),Saturation=c())");
        ListVector combinedata;

        for(int i=0;i<wellnames.size();i++)
        {
           engine.put("TempWell",welldata.get(wellnames.get(i)));
          // System.out.println("HVJHVK");
           //engine.eval("print(names(TempWell))");
           engine.eval("CombinedData = rbind(CombinedData,TempWell)");
        }
        ListVector Combined = (ListVector)engine.eval("CombinedData");

        DoubleVector  logs0 =(DoubleVector) Combined.get(0);
        DoubleVector logs1 =(DoubleVector) Combined.get(1);
        DoubleVector logs2=(DoubleVector) Combined.get(2);
        DoubleVector Saturation =(DoubleVector) Combined.get(3);
        DoubleVector Neut =(DoubleVector) Combined.get(4);
        int size = logs0.length();
        double [][] X = new double[size][3];
        double [] y1 = new double[size];
        double [] y2 = new double[size];
        for (int i = 0;i<size;i++){

                X[i][0]=logs0.get(i);
            X[i][1]=logs1.get(i);
            X[i][2]=logs2.get(i);

            y1[i]=Saturation.get(i);
            y2[i]=Neut.get(i);
            //System.out.println(X[i][0]);
        }

        rf1 = new RandomForest(X,y1,20,100000,5,3);

         rf2 = new RandomForest(X,y2,20,100000,5,3);
        //System.out.println(rf2.error()+"error of rf1");
    }

    public List interpolatesurface( ListVector list1,String Parameter) throws ScriptException {
        RandomForest rf = null;
        RandomForest rfu = null;
        List<Double> intensity = new ArrayList<>();
        List<Double> intensity1 = new ArrayList<>();
        double ma=Double.MIN_VALUE;
        double mi = Double.MAX_VALUE;
        if(Parameter =="Saturation")
        {
            rf=rf1;
            DoubleVector lati= (DoubleVector)list1.get(0);
            DoubleVector longi= (DoubleVector)list1.get(1);
            DoubleVector depths= (DoubleVector)list1.get(2);

            for (int i=0;i<lati.length();i++){
                double val =rf.predict(new double[] {lati.get(i),longi.get(i),depths.get(i)});
                intensity.add(val);
                ma =Double.max(ma,val);
                mi= Double.min(mi,val);
            }
            for(int i=0;i<lati.length();i++)
            {
                intensity1.add(intensity.get(i)/(ma-mi));
            }


        }
        else if(Parameter =="NEUT")
        {

            rf=rf2;
            DoubleVector lati= (DoubleVector)list1.get(0);
            DoubleVector longi= (DoubleVector)list1.get(1);
            DoubleVector depths= (DoubleVector)list1.get(2);

            for (int i=0;i<lati.length();i++){
                double val =rf.predict(new double[] {lati.get(i),longi.get(i),depths.get(i)});
                intensity.add(val);
                ma =Double.max(ma,val);
                mi= Double.min(mi,val);
            }
            for(int i=0;i<lati.length();i++)
            {
                intensity1.add(intensity.get(i)/(ma-mi));
            }
        }
        else if(Parameter =="OOIP")
        {

            rf=rf1;
            rfu=rf2;
            double Area = 1.0;
            double Height = 1.0;
            double VolumeFactor = 1.0;

            DoubleVector lati= (DoubleVector)list1.get(0);
            DoubleVector longi= (DoubleVector)list1.get(1);
            DoubleVector depths= (DoubleVector)list1.get(2);
            double[] NEUT = new double[lati.length()];
            double[] Saturation = new double[lati.length()];

            for (int i=0;i<lati.length();i++){
                //intensity.add(rfp.predict(new double[] {lati.get(i),longi.get(i),depths.get(i)}));
                NEUT[i] = rf.predict(new double[] {lati.get(i),longi.get(i),depths.get(i)});
                Saturation[i] = rfu.predict(new double[] {lati.get(i),longi.get(i),depths.get(i)});
                double val =(( 7758*Area*Height*NEUT[i]*(1 - Saturation[i]) )/VolumeFactor );
                intensity.add(val);
                ma =Double.max(ma,val);
                mi= Double.min(mi,val);
            }
            for(int i=0;i<lati.length();i++)
            {
                intensity1.add(intensity.get(i)/(ma-mi));
            }
        }


       return intensity1;
    }

//    public List interpolateOOIP( ListVector list1) throws ScriptException {
//        RandomForest rf = null;
//        RandomForest rfu = null;
//            rf=rf1;
//            rfu=rf2;
//        DoubleVector lati= (DoubleVector)list1.get(0);
//        DoubleVector longi= (DoubleVector)list1.get(1);
//        DoubleVector depths= (DoubleVector)list1.get(2);
//        List<Double> intensity = new ArrayList<>();
//        for (int i=0;i<lati.length();i++){
//            intensity.add(rf.predict(new double[] {lati.get(i),longi.get(i),depths.get(i)}));
//        }
//
//        DoubleVector latiu= (DoubleVector)list1.get(0);
//        DoubleVector longiu= (DoubleVector)list1.get(1);
//        DoubleVector depthsu= (DoubleVector)list1.get(2);
//        List<Double> intensityu = new ArrayList<>();
//        for (int i=0;i<lati.length();i++){
//            intensityu.add(rfu.predict(new double[] {lati.get(i),longi.get(i),depths.get(i)}));
//        }
//
//        return
//    }

}
