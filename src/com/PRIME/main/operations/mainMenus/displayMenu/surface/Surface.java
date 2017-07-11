package com.PRIME.main.operations.mainMenus.displayMenu.surface;


import javafx.geometry.Point3D;
import libsvm.*;
import org.jzy3d.maths.Coord3d;
import org.jzy3d.maths.Range;
import org.jzy3d.plot3d.primitives.Shape;
import org.renjin.primitives.sequence.RepDoubleVector;
import org.renjin.sexp.DoubleArrayVector;
import org.renjin.sexp.DoubleVector;
import org.renjin.sexp.ListVector;


import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 7/7/2017.
 */
public class Surface {
    public static ListVector method (List<Point3D> uppersurface) throws IOException, ScriptException {
        //Process r = Runtime.getRuntime().exec("C:\\Program Files\\R\\R-3.4.0\\bin\\Rscript.exe Script1.R");

    //    MinLati, MaxLati, MinLong, MaxLong from loadlasfile.java
        loadlasfil file = new loadlasfil();
        double MinLati = file.MinLati;double MaxLati =file.MaxLati;double MinLong = file.MinLong;double MaxLong = file.MaxLong;
       //double MinLati = -39.49464;double MaxLati = -39.43171;double MinLong = 174.17548;double MaxLong = 174.18576;
        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine engine = factory.getEngineByName("Renjin");

//      List<Point3D> uppersurface = new ArrayList<Point3D>();
//        uppersurface.add(new Point3D(-39.43171,174.18095,3565.55040));
//      uppersurface.add(new Point3D(-39.47241,174.17548,5000.02440));
////
//      uppersurface.add(new Point3D(-39.49464,174.18576,3000.09760));
//        uppersurface.add(new Point3D(-39.44,174.181,5400));
//        uppersurface.add(new Point3D(-39.48,174.185,5800));
//       // uppersurfac.add(new Point3D(-39.45,174.183,5000));
//       // uppersurface.add(new Point3D(-39.46,174.173,900));
        double[] X = new double[uppersurface.size()];double[] Y = new double[uppersurface.size()];
        double[] Z = new double[uppersurface.size()];
        //DoubleVector X = new DoubleArrayVector(); ListVector Y = new ListVector();ListVector Z = new ListVector();
        for (int i = 0;i < uppersurface.size();i++){
            Z[i] = uppersurface.get(i).getZ();
        }
        //engine.put("Lati",X);engine.put("Long",Y);engine.put("Depth",Z);
        //engine.eval("newdf = data.frame(Lati = Lati,Long = Long,Depth = Depth)");
        engine.put("MinLati",MinLati);engine.put("MaxLati",MaxLati);engine.put("MinLong",MinLong);engine.put("MaxLong",MaxLong);
        engine.eval("StepLati = (MaxLati - MinLati)/100;StepLong = (MaxLong - MinLong)/100;");
        engine.eval("Latitudes = seq(MinLati,MaxLati,StepLati);Longitudes = seq(MinLong,MaxLong,StepLong);");
        engine.eval("grids = length(Latitudes)");
        //engine.eval("n = length(Latitudes)*length(Longitudes)");
        engine.eval("testdf = data.frame(Lati = 1:grids*grids,Long = 1:grids*grids,DEPTH = 1:grids*grids)");
        engine.eval("for (i in 1:grids){ for (j in 1:grids){testdf[j + grids*(i-1),1] = Latitudes[i];testdf[j + grids*(i-1),2] = Longitudes[j];}}");

        //engine.eval("testdf = data.frame(Lati = Latitudes,Long = Longitudes)");
        svm_parameter param = new svm_parameter();
        param.svm_type = svm_parameter.EPSILON_SVR;
        param.kernel_type = svm_parameter.RBF;
        param.eps = 0.001;param.C=100000;param.gamma = 10000;
        //param.eps = 0.001;param.C=10;param.gamma = 1;
        svm_problem problem = new svm_problem();
        problem.l = uppersurface.size();
        problem.y = Z;
        problem.x = new svm_node[uppersurface.size()][2];
        for (int i = 0;i < uppersurface.size();i++){
            svm_node nodeX=new svm_node();svm_node nodeY=new svm_node();
            nodeX.index=0;nodeX.index=1;
            nodeX.value = uppersurface.get(i).getX(); nodeY.value = uppersurface.get(i).getY();
            problem.x[i][0]=nodeX;problem.x[i][1]=nodeY;
        }
        svm_model model= svm.svm_train(problem,param);
        DoubleArrayVector lati = (DoubleArrayVector)engine.eval("testdf$Lati");
        DoubleArrayVector longi = (DoubleArrayVector)engine.eval("testdf$Long");

        //svm.svm_cross_validation(problem,param,10, problem.y);

        double[] predictions =new double[longi.length()];
        for (int i=0;i<lati.length();i++){
            svm_node[] testexample = new svm_node[2];
            svm_node nodeX=new svm_node();svm_node nodeY=new svm_node();
            nodeX.index=0;nodeX.index=1;
            nodeX.value = lati.get(i); nodeY.value = longi.get(i);
            testexample[0] = nodeX; testexample[1] = nodeY;
            predictions[i] = svm.svm_predict(model,testexample);
        }
        engine.put("pred",predictions);
        engine.eval("testdf$DEPTH = pred");
        ListVector points = (ListVector)engine.eval("testdf");

//        System.out.println(predictions[100]);
//        System.out.println(predictions[3535]);
//        System.out.println(predictions[10153]);
//        System.out.println(predictions.length);
//        System.out.println(file.MinLati+" "+file.MaxLati+" "+file.MinLong+" "+file.MaxLong);
      //  engine.eval("write.csv(testdf,'C:/Users/HP/IdeaProjects/Testing/Result.csv',row.names=F)");
        return points;
    }
}
