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
           engine.eval("print(names(TempWell))");
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
            System.out.println(X[i][0]);
        }

        rf1 = new RandomForest(X,y1,8,100000,5,3);

         rf2 = new RandomForest(X,y2,8,100000,5,3);
        System.out.println(rf2.error()+"error of rf1");
    }

    public List interpolatesurface( ListVector list1,String Parameter) throws ScriptException {
        RandomForest rf = null;
        if(Parameter =="Saturation")
        {
            rf=rf1;
        }
        else if(Parameter =="NEUT")
        {
            rf=rf2;
        }
        DoubleVector lati= (DoubleVector)list1.get(0);
        DoubleVector longi= (DoubleVector)list1.get(1);
        DoubleVector depths= (DoubleVector)list1.get(2);
        List<Double> intensity = new ArrayList<>();
        for (int i=0;i<lati.length();i++){
            intensity.add(rf.predict(new double[] {lati.get(i),longi.get(i),depths.get(i)}));
        }
//        loadlasfil data = new loadlasfil();
//        double Mindepth = data.MinDepth;
//        double Maxdepth =data.MaxDepth;
//        double MinLati = data.MinLati;double MaxLati =data.MaxLati;double MinLong = data.MinLong;double MaxLong = data.MaxLong;
//        DoubleVector depths= (DoubleVector)list1.get(2);
//        ScriptEngineManager factory = new ScriptEngineManager();
//        ScriptEngine engine = factory.getEngineByName("Renjin");
//        engine.put("MinLati",MinLati);engine.put("MaxLati",MaxLati);engine.put("MinLong",MinLong);engine.put("MaxLong",MaxLong);//engine.put("MaxDepth",Maxdepth);engine.put("MinDepth",Mindepth);
//        engine.eval("StepLati = (MaxLati - MinLati)/100;StepLong = (MaxLong - MinLong)/100;");
//        engine.eval("Latitudes = seq(MinLati,MaxLati,StepLati);Longitudes = seq(MinLong,MaxLong,StepLong);");

       return intensity;
    }

//    public ListVector interpolateregion( ListVector list1,ListVector list2,String Parameter)
//    {
//        RandomForest rf;
//        if(Parameter =="Saturation")
//        {
//            rf=rf1;
//        }
//        else if(Parameter =="NEUT")
//        {
//            rf=rf2;
//        }
//
//    }
}
