package com.PRIME.javafx3D;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;

import java.util.ArrayList;
import java.util.List;


public class cube {
        public static Group createCube(double size) {

        Group cube = new Group();

        // size of the cube
        Color color = Color.DARKCYAN;

        List<Axis> cubeFaces = new ArrayList<>();
        Axis r;

        // back face
        r = new Axis(size);
//        r.setFill(color.deriveColor(0.0, 1.0, (1 - 0.5 * 1), 1.0));
        r.setTranslateX(-0.5 * size);
        r.setTranslateY(-0.5 * size);
        r.setTranslateZ(0.5 * size);

        cubeFaces.add(r);

        // bottom face
        r = new Axis(size);
        // r.setFill(color.deriveColor(0.0, 1.0, (1 - 0.4 * 1), 1.0));
        r.setTranslateX(-0.5 * size);
        r.setTranslateY(0);
        r.setRotationAxis(Rotate.X_AXIS);
        r.setRotate(90);

        cubeFaces.add(r);

        // right face
        r = new Axis(size);
        //  r.setFill(color.deriveColor(0.0, 1.0, (1 - 0.3 * 1), 1.0));
        r.setTranslateX(-1 * size);
        r.setTranslateY(-0.5 * size);
        r.setRotationAxis(Rotate.Y_AXIS);
        r.setRotate(90);

        cubeFaces.add( r);

        // left face
        r = new Axis(size);
        //  r.setFill(color.deriveColor(0.0, 1.0, (1 - 0.2 * 1), 1.0));
        r.setTranslateX(0);
        r.setTranslateY(-0.5 * size);
        r.setRotationAxis(Rotate.Y_AXIS);
        r.setRotate(90);

        cubeFaces.add(r);

        // top face
        r = new Axis(size);
        //   r.setFill(color.deriveColor(0.0, 1.0, (1 - 0.1 * 1), 1.0));
        r.setTranslateX(-0.5 * size);
        r.setTranslateY(-1 * size);
        r.setRotationAxis(Rotate.X_AXIS);
        r.setRotate(90);

        cubeFaces.add( r);

        // front face
        r = new Axis(size);
        // r.setFill(color.deriveColor(0.0, 0.0, (1 - 0.1 * 1), 1.0));
        r.setTranslateX(-0.5 * size);
        r.setTranslateY(-0.5 * size);
        r.setTranslateZ(-0.5 * size);

        cubeFaces.add( r);

        cube.getChildren().addAll(cubeFaces);

        return cube;
        }


    public static class Axis extends Pane {

        Rectangle wall;

        public Axis(double size) {

            // wall
            // first the wall, then the lines => overlapping of lines over walls
            // works
            // wall = new Rectangle(size, size);
            //getChildren().add(wall);

            // grid
            double zTranslate = 0;
            double lineWidth = 0.1;
            Color gridColor = Color.RED;

            for (int y = 0; y <= size; y += size / 10) {

                Line line = new Line(0, 0, size, 0);
                line.setStroke(gridColor);
                line.setFill(gridColor);
                line.setTranslateY(y);
                line.setTranslateZ(zTranslate);
                line.setStrokeWidth(lineWidth);

                getChildren().addAll(line);

            }

            for (int x = 0; x <= size; x += size / 10) {

                Line line = new Line(0, 0, 0, size);
                line.setStroke(gridColor);
                line.setFill(gridColor);
                line.setTranslateX(x);
                line.setTranslateZ(zTranslate);
                line.setStrokeWidth(lineWidth);

                getChildren().addAll(line);

            }

            // labels
            // TODO: for some reason the text makes the wall have an offset
          /*  for (int y = 0; y <= size; y += size / 10) {

                Text text = new Text("" + y);
                text.setTranslateX(size + 10 + 10);

                text.setTranslateY(y + 20);
                text.setTranslateZ(zTranslate + 20);

                getChildren().addAll(text);*/

            }

        }
    }

