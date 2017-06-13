package com.PRIME.javafx3D;

// run this file

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.util.List;

public class loadvalue extends Application {


    final Group root = new Group();
    final Xform world = new Xform();
    final PerspectiveCamera camera = new PerspectiveCamera(true);
    final Xform cameraXform = new Xform();
    final Xform cameraXform2 = new Xform();
    final Xform cameraXform3 = new Xform();
    private static final double CAMERA_INITIAL_DISTANCE = -450;
    private static final double CAMERA_INITIAL_X_ANGLE = 70.0;
    private static final double CAMERA_INITIAL_Y_ANGLE = 320.0;
    private static final double CAMERA_NEAR_CLIP = 0.1;
    private static final double CAMERA_FAR_CLIP = 10000.0;
    private static final double CONTROL_MULTIPLIER = 0.1;
    private static final double SHIFT_MULTIPLIER = 10.0;
    private static final double MOUSE_SPEED = 0.1;
    private static final double ROTATION_SPEED = 2.0;
    private static final double TRACK_SPEED = 0.3;
    final int EARTH_RADIUS = 100;

    double mousePosX;
    double mousePosY;
    double mouseOldX;
    double mouseOldY;
    double mouseDeltaX;
    double mouseDeltaY;


    @Override
    public void start(Stage primaryStage) {

        List<Pair<Double,Double>> coordinates = Coordinates.transfer();
        //Point3D [] temp = convert.method();
        Group root2= new Group();
        Sphere testBox = new Sphere(EARTH_RADIUS);
        testBox.setTranslateX(0);
        testBox.setTranslateY(0);
        testBox.setTranslateZ(0);
        testBox.setCullFace(CullFace.NONE);
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseMap(new Image(getClass().getResource("map.jpg").toExternalForm()));
        testBox.setMaterial(material);
        //testBox.setMaterial(new PhongMaterial(Color.WHITE));
        // testBox.setDrawMode(DrawMode.LINE);
        // testBox.setCullFace(CullFace.BACK);
        world.getChildren().add(testBox);
       // for(Integer i=0;i<temp.length;i++) {
        for (Pair<Double,Double> coordinate : coordinates){
            //Drawing Sphere1
            System.out.println(coordinate.getKey()+" " +coordinate.getValue());
            Cylinder sphere1 = new Cylinder();
            //Setting the radius of the Sphere
            sphere1.setRadius(0.1);

            sphere1.setHeight(4);
            sphere1.setMaterial(new PhongMaterial(Color.RED));
            double latitude = coordinate.getKey();
            double longitude = coordinate.getValue();

            latitude = latitude * Math.PI / 180;
            longitude = longitude * Math.PI / 180;

            double x = EARTH_RADIUS * Math.sin(latitude) * Math.cos(longitude);
            double y = EARTH_RADIUS * Math.sin(latitude) * Math.sin(longitude);
            double z = EARTH_RADIUS * Math.cos(latitude);

            //Setting the position of the sphere
            sphere1.setTranslateX(x);
            sphere1.setTranslateY(y);
            sphere1.setTranslateZ(z);
            world.getChildren().add(sphere1);
        }

        root.getChildren().add(world);
        root.getChildren().add(new AmbientLight(Color.WHITE));
        root.setDepthTest(DepthTest.ENABLE);
        // buildScene();
        buildCamera();

        //camera.setFieldOfView();
        Scene scene = new Scene(root, 1024, 768, true,SceneAntialiasing.BALANCED);
        scene.setFill(Color.GREY);

        //handleKeyboard(scene, world);
        handleMouse(scene, world);

        primaryStage.setTitle("Molecule Sample Application");
        primaryStage.setScene(scene);

        primaryStage.show();

        scene.setCamera(camera);

    }

    private void buildCamera() {
        root.getChildren().add(cameraXform);
        cameraXform.getChildren().add(camera);
        cameraXform.getChildren().add(cameraXform2);
        cameraXform2.getChildren().add(cameraXform3);
        cameraXform3.getChildren().add(camera);
        cameraXform.setRotateZ(180.0);

        camera.setNearClip(CAMERA_NEAR_CLIP);
        camera.setFarClip(CAMERA_FAR_CLIP);
        camera.setTranslateZ(CAMERA_INITIAL_DISTANCE);
        cameraXform.ry.setAngle(CAMERA_INITIAL_Y_ANGLE);
        cameraXform.rx.setAngle(CAMERA_INITIAL_X_ANGLE);
    }



    private void handleMouse(Scene scene, final Node root) {

        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent me) {
                mousePosX = me.getSceneX();
                mousePosY = me.getSceneY();
                mouseOldX = me.getSceneX();
                mouseOldY = me.getSceneY();
            }
        });
        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent me) {
                mouseOldX = mousePosX;
                mouseOldY = mousePosY;
                mousePosX = me.getSceneX();
                mousePosY = me.getSceneY();
                mouseDeltaX = (mousePosX - mouseOldX);
                mouseDeltaY = (mousePosY - mouseOldY);

                double modifier = 1.0;

                if (me.isControlDown()) {
                    modifier = CONTROL_MULTIPLIER;
                }
                if (me.isShiftDown()) {
                    modifier = SHIFT_MULTIPLIER;
                }
                if (me.isPrimaryButtonDown()) {
                    cameraXform.ry.setAngle(cameraXform.ry.getAngle() -
                            mouseDeltaX*modifier*ROTATION_SPEED);  //
                    cameraXform.rx.setAngle(cameraXform.rx.getAngle() +
                            mouseDeltaY*modifier*ROTATION_SPEED);  // -
                }
                else if (me.isSecondaryButtonDown()) {
                    double z = camera.getTranslateZ();
                    double newZ = z + mouseDeltaX*MOUSE_SPEED*modifier;
                    camera.setTranslateZ(newZ);
                }
                else if (me.isMiddleButtonDown()) {
                    cameraXform2.t.setX(cameraXform2.t.getX() +
                            mouseDeltaX*MOUSE_SPEED*modifier*TRACK_SPEED);  // -
                    cameraXform2.t.setY(cameraXform2.t.getY() +
                            mouseDeltaY*MOUSE_SPEED*modifier*TRACK_SPEED);  // -
                }
            }
        }); // setOnMouseDragged
    } //handleMouse

   private void handleKeyboard(Scene scene, final Node root) {

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case Z:
                        cameraXform2.t.setX(0.0);
                        cameraXform2.t.setY(0.0);
                        cameraXform.ry.setAngle(CAMERA_INITIAL_Y_ANGLE);
                        cameraXform.rx.setAngle(CAMERA_INITIAL_X_ANGLE);
                        break;
                   /* case X:
                        axisGroup.setVisible(!axisGroup.isVisible());
                        break;
                    case V:
                        moleculeGroup.setVisible(!moleculeGroup.isVisible());
                        break;*/
                } // switch
            } // handle()
        });  // setOnKeyPressed
    }  //  handleKeyboard()

    public static void main(String[] args) {


        launch(args);
    }
}
