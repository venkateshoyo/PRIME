package com.PRIME.main.operations.menubars.displayMenu.surface;

import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.jzy3d.plot3d.primitives.Scatter;
import org.jzy3d.plot3d.primitives.Shape;
import org.renjin.sexp.ListVector;

import javax.script.ScriptException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by hkban on 7/10/2017.
 */
public class windowregion {

    Stage primaryStage = new Stage();
    static String name ="";
    List<Point3D> uppersurface= new ArrayList<>();
    List<Point3D> lowersurface= new ArrayList<>();
    Shape surface = null;
    Scatter scatter =null;
    static boolean flag = false;

    GridPane gp = new GridPane();
    public Shape coordinates(List<String> wellnames) throws IOException, ScriptException {

        TextField xval1[]= new TextField[wellnames.size()];
        TextField yval1[]= new TextField[wellnames.size()];
        TextField zval1[]= new TextField[wellnames.size()];
        TextField xval2[]= new TextField[wellnames.size()];
        TextField yval2[]= new TextField[wellnames.size()];
        TextField zval2[]= new TextField[wellnames.size()];
        Label name[] = new Label[wellnames.size()];
        RadioButton[] addbutton = new RadioButton[wellnames.size()];
        int row =0;
        int column =0;
        Label wellname = new Label("WELL NAME");
        gp.add(wellname,0,0);
        Label x1coordi = new Label("x1coordi");
        gp.add(x1coordi,1,0);
        Label y1coordi = new Label("y1coordi");
        gp.add(y1coordi,2,0);
        Label z1coordi = new Label("z1coordi");
        gp.add(z1coordi,3,0);
        Label x2coordi = new Label("x2coordi");
        gp.add(x2coordi,4,0);
        Label y2coordi = new Label("y2coordi");
        gp.add(y2coordi,5,0);
        Label z2coordi = new Label("z2coordi");
        gp.add(z2coordi,6,0);
        Label Add = new Label("ADDWELL");
        gp.add(Add,7,0);
        for(int i=0;i<wellnames.size();i++)
        {
            row++;
            name[i] = new Label(wellnames.get(i));
            gp.add(name[i],0,row);
            xval1[i] =new TextField();
            gp.add(xval1[i],1,row);
            yval1[i] =new TextField();
            gp.add(yval1[i],2,row);
            zval1[i] =new TextField();
            gp.add(zval1[i],3,row);
            xval2[i] =new TextField();
            gp.add(xval2[i],4,row);
            yval2[i] =new TextField();
            gp.add(yval2[i],5,row);
            zval2[i] =new TextField();
            gp.add(zval2[i],6,row);
            addbutton[i]= new RadioButton();
            gp.add(addbutton[i],7,row);
        }
        ToggleGroup paramvalue = new ToggleGroup();
        Label Parameter = new Label("Parameter");
        gp.add(Parameter,0,++row);
        RadioButton Saturation  = new RadioButton("Saturation");
        Saturation.setUserData("Saturation");
        Saturation.setToggleGroup(paramvalue);
        Saturation.setSelected(true);
        gp.add(Saturation,1,row);
        RadioButton Neut = new RadioButton("NEUT");
        Neut.setUserData("NEUT");
        Neut.setToggleGroup(paramvalue);
        gp.add(Neut,2,row);
        RadioButton OOIP = new RadioButton("OOIP");
        OOIP.setUserData("OOIP");
        OOIP.setToggleGroup(paramvalue);
        gp.add(OOIP,3,row);


        Label Plots = new Label("Plots");
        gp.add(Plots,0,++row);
        ToggleGroup plotvalue = new ToggleGroup();
        RadioButton ScatterPoints = new RadioButton("ScatterPlots");
        ScatterPoints.setUserData("ScatterPlots");
        ScatterPoints.setToggleGroup(plotvalue);
        gp.add(ScatterPoints,1,row);
        RadioButton Surfaceplots = new RadioButton("SurfacePlots");
        Surfaceplots.setUserData("SurfacePlots");
        Surfaceplots.setToggleGroup(plotvalue);
        gp.add(Surfaceplots,2,row);
        Button show = new Button("SHOW SURFACE");
        gp.add(show,8,++row);


        show.setOnAction(e->{
            uppersurface.add(new Point3D(-39.43171,174.18095,3565.55040));
            uppersurface.add(new Point3D(-39.47241,174.17548,5000.02440));
            uppersurface.add(new Point3D(-39.49464,174.18576,3000.09760));
            lowersurface.add(new Point3D(-39.43171,174.18095,2565.55040));
            lowersurface.add(new Point3D(-39.47241,174.17548,4000.02440));
            lowersurface.add(new Point3D(-39.49464,174.18576,2000.09760));
//            for(int i=0;i<wellnames.size();i++)
//            {
//                if(addbutton[i].isSelected()) {
//
//
//                    Point3D point1 = new Point3D(Double.parseDouble(xval1[i].getText()), Double.parseDouble(yval1[i].getText()), Double.parseDouble(zval1[i].getText()));
//                    Point3D point2 = new Point3D(Double.parseDouble(xval2[i].getText()), Double.parseDouble(yval2[i].getText()), Double.parseDouble(zval2[i].getText()));
//                    uppersurface.add(point1);
//                    lowersurface.add(point2);
//                }
//            }
            String plotval="";
            if(plotvalue.getSelectedToggle()!=null)
            {plotval=plotvalue.getSelectedToggle().getUserData().toString();}


            RegionPlot plot  = null;
            try {
                plot = new RegionPlot();
            } catch (ScriptException e1) {
                e1.printStackTrace();
            }
            ListVector list1 = null;
            ListVector list2 = null;
            try {
                list1 = Surface.method(uppersurface);
                list2 = Surface.method(lowersurface);
            } catch (IOException | ScriptException e1) {
                e1.printStackTrace();
            }
            try {
                surface = plot.Regionplot(list1,list2,wellnames,paramvalue.getSelectedToggle().getUserData().toString(),plotval);
            } catch (IOException | ScriptException e1) {
                e1.printStackTrace();
            }

            if(Objects.equals(plotval, "ScatterPlots"))
            {

                try {
                     scatter = plot.Scatterplot();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                flag = true;
            }
            primaryStage.close();
        });
        gp.setHgap(10);
        gp.setVgap(10);
        Scene scene = new Scene(gp, 1500, 300);
        scene.setFill(Color.GREY);
        primaryStage.setTitle("Molecule Sample Application");
        primaryStage.setScene(scene);
        primaryStage.showAndWait();
        return surface;
    }

    public Scatter Discrete(){
        return scatter;
    }
}
