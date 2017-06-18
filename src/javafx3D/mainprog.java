package javafx3D;


import com.PRIME.main.operations.toolbars.hackTooltipStartTiming;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.Sphere;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Pair;

import javax.swing.*;


/**
 * Created by hkban on 6/16/2017.
 */

public class mainprog extends Application {


    final Group root = new Group();
    final Group subroot = new Group();
    final Xform world = new Xform();
    final PerspectiveCamera camera = new PerspectiveCamera(true);
    final Xform cameraXform = new Xform();
    final Xform cameraXform2 = new Xform();
    final Xform cameraXform3 = new Xform();
    private static final double CAMERA_INITIAL_DISTANCE = -1800;
    private static final double CAMERA_NEAR_CLIP = 0.1;
    private static final double CAMERA_FAR_CLIP = 10000.0;
    private static final double CONTROL_MULTIPLIER = 0.1;
    private static final double SHIFT_MULTIPLIER = 10.0;
    private static final double MOUSE_SPEED = 0.1;
    private static final double ROTATION_SPEED = 2.0;
    private static final double TRACK_SPEED = 0.3;




    double mousePosX;
    double mousePosY;
    double mouseOldX;
    double mouseOldY;
    double mouseDeltaX;
    double mouseDeltaY;



    @Override
    public void start(Stage primaryStage) {
        final double EARTH_RADIUS  = 400;
        Sphere earth = new Sphere(EARTH_RADIUS);
        Group value = new Group();
        value.getChildren().add(earth);

        final double VIEWPORT_SIZE = 800;

        final double MAP_WIDTH  = 8192 / 2d;
        final double MAP_HEIGHT = 4092 / 2d;

        PhongMaterial earthMaterial = new PhongMaterial();
        earthMaterial.setDiffuseMap(
                new Image(
                        getClass().getResourceAsStream("diffue_world_map.jpg"),
                        MAP_WIDTH,
                        MAP_HEIGHT,
                        true,
                        true
                )
        );
        earthMaterial.setBumpMap(
                new Image(
                        getClass().getResourceAsStream("normal_worldmap.jpg"),
                        MAP_WIDTH,
                        MAP_HEIGHT,
                        true,
                        true
                )
        );
        earthMaterial.setSpecularMap(
                new Image(
                        getClass().getResourceAsStream("specular_worldmap.jpg"),
                        MAP_WIDTH,
                        MAP_HEIGHT,
                        true,
                        true
                )
        );

        earth.setMaterial(
                earthMaterial
        );

        buildCamera();
        Button button = new Button("load well points");
        button.setOnAction(e->{
            Sphere Cone = new Sphere(10);
            PhongMaterial material1= new PhongMaterial();
            material1.setDiffuseColor(Color.RED);
            Cone.setMaterial(material1);
            window sh = new window();
            Pair<Double,Double> coordinates = sh.coordinates();
            double latitude=coordinates.getKey();
            double longitude=coordinates.getValue();

            double latitude1 =latitude;
            double  longitude1 = longitude;
             latitude1 = latitude1 * Math.PI / 180;
            longitude1 = longitude1 * Math.PI / 180;



            double x = EARTH_RADIUS * Math.cos(latitude1)*Math.sin(longitude1) ;
            double y = EARTH_RADIUS * Math.sin(latitude1);
            double z = EARTH_RADIUS * Math.cos(latitude1)*Math.cos(longitude1);


            Cone.setTranslateX(x);
            Cone.setTranslateY(-y);
            Cone.setTranslateZ(-z);
            Tooltip tooltip = new Tooltip("lat:"+latitude+",long:"+longitude+",x:"+x+",y:"+y+"z:"+z);
            tooltip.setFont(Font.font("", 10));
            new hackTooltipStartTiming(tooltip);
            Tooltip.install(Cone,tooltip);
             value.getChildren().add(Cone);

        });
        SubScene subscene = new SubScene(value, 1300, VIEWPORT_SIZE, true, SceneAntialiasing.BALANCED);
        subscene.setFill(Color.rgb(10, 10, 40));

        subscene.setCamera(camera);
        //handleKeyboard(scene, world);
        handleMouse(subscene, world);
        SplitPane sp = new SplitPane(button,subscene);
        makeZoomable(subscene);
        Scene scene = new Scene(sp, 1500, 1500, true,SceneAntialiasing.BALANCED);
        scene.setFill(Color.GREY);
        primaryStage.setTitle("Molecule Sample Application");
        primaryStage.setScene(scene);

        primaryStage.show();
       // testBox.getChildren().clear();



    }





    private void buildCamera() {
        subroot.getChildren().add(cameraXform);
        cameraXform.getChildren().add(camera);
        cameraXform.getChildren().add(cameraXform2);
        cameraXform2.getChildren().add(cameraXform3);
        cameraXform3.getChildren().add(camera);


        camera.setNearClip(CAMERA_NEAR_CLIP);
        camera.setFarClip(CAMERA_FAR_CLIP);
        camera.setTranslateZ(CAMERA_INITIAL_DISTANCE);

    }


    public void makeZoomable(SubScene control) {

        final double MAX_SCALE = 15.0;
        final double MIN_SCALE = 0.1;

        control.addEventFilter(ScrollEvent.ANY, new EventHandler<ScrollEvent>() {

            @Override
            public void handle(ScrollEvent event) {

                double delta = 1.2;

                double scale = control.getScaleX();

                if (event.getDeltaY() < 0) {
                    scale /= delta;
                } else {
                    scale *= delta;
                }

                scale = clamp(scale, MIN_SCALE, MAX_SCALE);

                control.setScaleX(scale);
                control.setScaleY(scale);

                event.consume();

            }

        });

    }

    public static double clamp(double value, double min, double max) {

        if (Double.compare(value, min) < 0)
            return min;

        if (Double.compare(value, max) > 0)
            return max;

        return value;
    }
    private void handleMouse(SubScene scene, final Node root) {

        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                mousePosX = me.getSceneX();
                mousePosY = me.getSceneY();
                mouseOldX = me.getSceneX();
                mouseOldY = me.getSceneY();
            }
        });

        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
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
                    cameraXform.ry.setAngle(cameraXform.ry.getAngle() +
                            mouseDeltaX * modifier * ROTATION_SPEED);  //
                    cameraXform.rx.setAngle(cameraXform.rx.getAngle() -
                            mouseDeltaY * modifier * ROTATION_SPEED);  // -
                } else if (me.isSecondaryButtonDown()) {
                    double z = camera.getTranslateZ();
                    double newZ = z + mouseDeltaX * MOUSE_SPEED * modifier;
                    camera.setTranslateZ(newZ);


                } else if (me.isMiddleButtonDown()) {
                    cameraXform2.t.setX(cameraXform2.t.getX() +
                            mouseDeltaX * MOUSE_SPEED * modifier * TRACK_SPEED);  // -
                    cameraXform2.t.setY(cameraXform2.t.getY() +
                            mouseDeltaY * MOUSE_SPEED * modifier * TRACK_SPEED);  // -

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

