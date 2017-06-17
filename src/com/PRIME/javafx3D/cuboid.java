package com.PRIME.javafx3D;

import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import org.fxyz.geometry.Point3D;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by hkban on 6/15/2017.
 */
public class cuboid extends Group {

    public List<Point3D> points;
    public int width = 2;
    public Color color = Color.WHITE;
    private TriangleMesh mesh;
    public MeshView meshView;
    public PhongMaterial material;


    public cuboid(List<Point3D> points, int width, Color color) {

            this.points = points;
            this.width = width;
            this.color = color;
            setDepthTest(DepthTest.ENABLE);
            mesh  = new TriangleMesh();
            //add each point. For each point add another point shifted on Z axis by width
            //This extra point allows us to build triangles later

            for(int i = 0; i<points.size()-1; i++) {
                //top right rear point
                mesh.getPoints().addAll(points.get(i).x + width, points.get(i).y, points.get(i).z + width);
                //top left rear point
                mesh.getPoints().addAll(points.get(i).x - width, points.get(i).y, points.get(i).z + width);
                //bottom right rear point

                mesh.getPoints().addAll(points.get(i+1).x + width, points.get(i+1).y, points.get(i+1).z + width);
                //bottom left rear point
                mesh.getPoints().addAll(points.get(i+1).x - width, points.get(i+1).y, points.get(i+1).z + width);
                //Front points
                //top right front point
                mesh.getPoints().addAll(points.get(i).x + width, points.get(i).y, points.get(i).z - width);
                //top left front point
                mesh.getPoints().addAll(points.get(i).x - width, points.get(i).y, points.get(i).z - width);
                //bottom right front point
                mesh.getPoints().addAll(points.get(i+1).x + width, points.get(i+1).y, points.get(i+1).z - width);
                //bottom left front point
                mesh.getPoints().addAll(points.get(i+1).x - width, points.get(i+1).y, points.get(i+1).z - width);
            }
        mesh.getTexCoords().addAll(0, 0);


        for (int p=0;p<(points.size()-1)*8;p+=8) {
            //Wind the next 8 vertices as a cube.  The cube itself will represent the data
            //Vertices wound counter-clockwise which is the default front face of any Triangle
            //Rear triangle faces should be wound clockwise to face away from center
            mesh.getFaces().addAll(p, 0, p + 3, 0, p + 2, 0); //TRR,BLR,BRR
            mesh.getFaces().addAll(p + 3, 0, p, 0, p + 1, 0); //BLR,TRR,TLR
            //left side faces
            mesh.getFaces().addAll(p + 1, 0, p + 5, 0, p + 3, 0); //TLR,TLF,BLR
            mesh.getFaces().addAll(p + 5, 0, p + 7, 0, p + 3, 0); //TLF,BLR,BLF
            //front side faces
            mesh.getFaces().addAll(p + 5, 0, p + 7, 0, p + 4, 0); //TLF,BLF,TLR
            mesh.getFaces().addAll(p + 4, 0, p + 7, 0, p + 6, 0); //TRF,BLF,BRF
            //front side faces
            mesh.getFaces().addAll(p + 4, 0, p + 6, 0, p + 2, 0); //TRF,BRF,BRR
            mesh.getFaces().addAll(p + 4, 0, p + 2, 0, p, 0); //TRF,BRR,TRR

            //Top faces
            mesh.getFaces().addAll(p, 0, p + 1, 0, p + 3, 0); //TRR,TLR,TRF
            mesh.getFaces().addAll(p + 1, 0, p + 5, 0, p + 3, 0); //TLR,TLF,TRF

            //bottom faces
            mesh.getFaces().addAll(p + 3, 0, p + 7, 0, p + 6, 0); //BLR,BLF,BRF
            mesh.getFaces().addAll(p + 3, 0, p + 6, 0, p + 2, 0); //BLR,BRF,BRR


        }


// Create a NeshView

        MeshView meshView = new MeshView();

        meshView.setMesh(mesh);
        meshView.setTranslateX(250);

        meshView.setTranslateY(100);

        meshView.setTranslateZ(400);

        // Scale the Meshview to make it look bigger

        meshView.setScaleX(10.0);

        meshView.setScaleY(10.0);

        meshView.setScaleZ(10.0);
        meshView = new MeshView(mesh);
        meshView.setDrawMode(DrawMode.FILL);  //Fill so that the line shows width
        material = new PhongMaterial(color);
        material.setDiffuseColor(color);
        material.setSpecularColor(color);
        meshView.setMaterial(material);
        //Make sure you Cull the Back so that no black shows through
        meshView.setCullFace(CullFace.BACK);

        //Add some ambient light so folks can see it
        AmbientLight light = new AmbientLight(Color.WHITE);
        light.getScope().add(meshView);
        getChildren().add(light);
        getChildren().add(meshView);
    }
}
