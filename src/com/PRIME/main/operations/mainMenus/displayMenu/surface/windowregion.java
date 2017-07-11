package com.PRIME.main.operations.mainMenus.displayMenu.surface;

import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.jzy3d.plot3d.primitives.Shape;
import org.renjin.sexp.ListVector;
import com.PRIME.main.operations.mainMenus.displayMenu.surface.RegionPlot;
import com.PRIME.main.operations.mainMenus.displayMenu.surface.Surface;

import javax.script.ScriptException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hkban on 7/10/2017.
 */
public class windowregion {

    Stage primaryStage = new Stage();
    static String name ="";
    List<Point3D> uppersurface= new ArrayList<>();
    List<Point3D> lowersurface= new ArrayList<>();
    Shape surface = null;

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
        Button show = new Button("SHOW SURFACE");
        gp.add(show,8,++row);



        show.setOnAction(e->{
            for(int i=0;i<wellnames.size();i++)
            {
                if(addbutton[i].isSelected()) {
                    Point3D point1 = new Point3D(Double.parseDouble(xval1[i].getText()), Double.parseDouble(yval1[i].getText()), Double.parseDouble(zval1[i].getText()));
                    Point3D point2 = new Point3D(Double.parseDouble(xval2[i].getText()), Double.parseDouble(yval2[i].getText()), Double.parseDouble(zval2[i].getText()));
                    uppersurface.add(point1);
                    lowersurface.add(point2);
                }
            }
            RegionPlot plot  = new RegionPlot();
            ListVector list1 = null;
            ListVector list2 = null;
            try {
                list1 = Surface.method(uppersurface);
                list2 = Surface.method(lowersurface);
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (ScriptException e1) {
                e1.printStackTrace();
            }
            try {
                surface = plot.Regionplot(list1,list2);
            } catch (IOException e1) {
                e1.printStackTrace();
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
}
