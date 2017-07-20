package com.PRIME.main.operations.menubars.displayMenu.surface;

import org.renjin.sexp.DoubleVector;
import org.renjin.sexp.ListVector;
import smile.math.Math;
import smile.regression.RandomForest;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import smile.validation.RMSE;

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

        }

        int[] Mtry = {2,3};int[] NodeSize = {4,5,6};

        double[][] xtrain = new double[1000][];double[][] xtest = new double[200][];
        double[] y1train = new double[1000];double[] y2train = new double[1000];
        double[] y1test = new double[200];double[] y2test = new double[200];
        //CrossValidation cv = new CrossValidation(1000,3);
        RMSE rsq = new RMSE();
        int[] rows1 = new int[1000];
        double[] predicted1 = new double[200]; double[] predicted2 = new double[200];
        for (int i = 0;i<1000;i++){
            rows1[i] = (int)Math.random(0,X.length);
            xtrain[i] = X[rows1[i]];
            y1train[i] = y1[rows1[i]];
            y2train[i] = y2[rows1[i]];
        }
        int[] rows2 = new int[200];
        for (int i = 0;i<200;i++){
            rows2[i] = (int)Math.random(0,X.length);
            xtest[i] = X[rows2[i]];
            y1test[i] = y1[rows2[i]];
            y2test[i] = y2[rows2[i]];
        }
        double MinRMSE1 = Double.MAX_VALUE; double MinRMSE2 = Double.MAX_VALUE;
        int OptimizedMtry1=2; int OptimizedNodeSize1 = 4;
        int OptimizedMtry2=2; int OptimizedNodeSize2 = 4;
        for (int N:NodeSize){
            for (int M:Mtry){
               RandomForest model1 = new RandomForest(xtrain,y1train,20,100000,N,M);
               //System.out.println(model1.error() + "N "+ N);
                RandomForest model2 = new RandomForest(xtrain,y2train,20,100000,N,M);
                //System.out.println(model2.error() + "M"+ M);
               for (int i=0;i<200;i++){
                   predicted1[i]=model1.predict(xtest[i]);
                   predicted2[i]=model2.predict(xtest[i]);
               }
                double temp1 = rsq.measure(predicted1,y1test);
                double temp2 = rsq.measure(predicted2,y2test);
                if (temp1 < MinRMSE1){
                    MinRMSE1=temp1;
                    OptimizedMtry1=M;
                    OptimizedNodeSize1=N;
                }
                if (temp2 < MinRMSE2){
                    MinRMSE2=temp2;
                    OptimizedMtry2=M;
                    OptimizedNodeSize2=N;
                }
            }
        }
        //System.out.println("Mtry1 -" + OptimizedMtry1 + "NodeSize1 -"+ OptimizedNodeSize1);
        //System.out.println("Mtry2 -" + OptimizedMtry2 + "NodeSize2 -"+ OptimizedNodeSize2);
        rf1 = new RandomForest(X,y1,20,100000,OptimizedNodeSize1,OptimizedMtry1);

         rf2 = new RandomForest(X,y2,20,100000,OptimizedNodeSize2,OptimizedMtry2);

    }

    double ma=Double.MIN_VALUE;
    double mi = Double.MAX_VALUE;
    public List interpolatesurface( ListVector list1,String Parameter) throws ScriptException {
        RandomForest rf = null;
        RandomForest rfu = null;
        List<Double> intensity = new ArrayList<>();
        List<Double> intensity1 = new ArrayList<>();

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

        }


       return intensity;
    }

    public List interpolatesurface( List<Double> listx,List<Double> listy,List<Double> listz,String Parameter) throws ScriptException {
        RandomForest rf = null;
        RandomForest rfu = null;
        List<Double> intensity = new ArrayList<>();
        List<Double> intensity1 = new ArrayList<>();

        if(Parameter =="Saturation")
        {
            rf=rf1;


            for (int i=0;i<listx.size();i++){
                double val =rf.predict(new double[] {listx.get(i),listy.get(i),listz.get(i)});
                intensity.add(val);
                ma =Double.max(ma,val);
                mi= Double.min(mi,val);
            }



        }
        else if(Parameter =="NEUT")
        {

            rf=rf2;


            for (int i=0;i<listx.size();i++){
                double val =rf.predict(new double[] {listx.get(i),listy.get(i),listz.get(i)});
                intensity.add(val);
                ma =Double.max(ma,val);
                mi= Double.min(mi,val);
            }

        }
        else if(Parameter =="OOIP")
        {

            rf=rf1;
            rfu=rf2;
            double Area = 1.0;
            double Height = 1.0;
            double VolumeFactor = 1.0;


            double[] NEUT = new double[listx.size()];
            double[] Saturation = new double[listx.size()];

            for (int i=0;i<listx.size();i++){
                //intensity.add(rfp.predict(new double[] {lati.get(i),longi.get(i),depths.get(i)}));
                NEUT[i] = rf.predict(new double[] {listx.get(i),listy.get(i),listz.get(i)});
                Saturation[i] = rfu.predict(new double[] {listx.get(i),listy.get(i),listz.get(i)});
                double val =(( 7758*Area*Height*NEUT[i]*(1 - Saturation[i]) )/VolumeFactor );
                intensity.add(val);
                ma =Double.max(ma,val);
                mi= Double.min(mi,val);
            }

        }


        return intensity;
    }


}
