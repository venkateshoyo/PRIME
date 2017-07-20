package com.PRIME.main.operations.menubars.displayMenu.surface;

import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.jzy3d.plot3d.primitives.Shape;
import org.renjin.sexp.ListVector;

import javax.script.ScriptException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hkban on 7/9/2017.
 */
public class windowsurface {

    Stage primaryStage = new Stage();
    static String name ="";
    List<Point3D> uppersurface= new ArrayList<>();
    Shape surface = null;

    GridPane gp = new GridPane();
    public Shape coordinates(List<String> wellnames) throws IOException, ScriptException {
        TextField xval[]= new TextField[wellnames.size()];
        TextField yval[]= new TextField[wellnames.size()];
        TextField zval[]= new TextField[wellnames.size()];
        Label name[] = new Label[wellnames.size()];
        RadioButton[] addbutton = new RadioButton[wellnames.size()];
        int row =0;
        int column =0;
        Label wellname = new Label("WELL NAME");
        gp.add(wellname,0,0);
        Label xcoordi = new Label("xcoordi");
        gp.add(xcoordi,1,0);
        Label ycoordi = new Label("ycoordi");
        gp.add(ycoordi,2,0);
        Label zcoordi = new Label("zcoordi");
        gp.add(zcoordi,3,0);

        Label Add = new Label("ADDWELL");
        gp.add(Add,4,0);
        for(int i=0;i<wellnames.size();i++)
        {
            row++;
             name[i] = new Label(wellnames.get(i));
            gp.add(name[i],0,row);
             xval[i] =new TextField();
            gp.add(xval[i],1,row);
            yval[i] =new TextField();
            gp.add(yval[i],2,row);
             zval[i] =new TextField();
            gp.add(zval[i],3,row);
            addbutton[i]= new RadioButton();
            gp.add(addbutton[i],4,row);
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
Button show = new Button("SHOW SURFACE");
        gp.add(show,5,++row);



        show.setOnAction(e->{
//                        uppersurface.add(new Point3D(-39.43171,174.18095,3565.55040));
//            uppersurface.add(new Point3D(-39.47241,174.17548,5000.02440));
//            uppersurface.add(new Point3D(-39.49464,174.18576,3000.09760));
            for(int i=0;i<wellnames.size();i++)
            {
                if(addbutton[i].isSelected()) {
                    Point3D point = new Point3D(Double.parseDouble(xval[i].getText()), Double.parseDouble(yval[i].getText()), Double.parseDouble(zval[i].getText()));
                    uppersurface.add(point);
                }
            }
            Surfaceplot plot  = new Surfaceplot();
            ListVector list = null;
            try {
                list = Surface.method(uppersurface);
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (ScriptException e1) {
                e1.printStackTrace();
            }
            try {
                //surface =
                        plot.Surfaceplot(list,wellnames,paramvalue.getSelectedToggle().getUserData().toString());
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (ScriptException e1) {
                e1.printStackTrace();
            }
            primaryStage.close();
        });
        gp.setHgap(10);
        gp.setVgap(10);
        Scene scene = new Scene(gp, 1000, 300);
        scene.setFill(Color.GREY);
        primaryStage.setTitle("Molecule Sample Application");
        primaryStage.setScene(scene);
        primaryStage.showAndWait();
        return surface;
    }
}
