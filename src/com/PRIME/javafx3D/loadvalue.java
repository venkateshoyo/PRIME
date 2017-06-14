package com.PRIME.javafx3D;

// run this file


import com.PRIME.main.operations.toolbars.hackTooltipStartTiming;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Pair;
import org.apache.batik.anim.dom.SVGDOMImplementation;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscodingHints;
import org.apache.batik.transcoder.image.ImageTranscoder;
import org.apache.batik.util.SVGConstants;
import org.fxyz3d.shapes.Cone;


import javax.xml.bind.annotation.XmlType;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.List;

public class loadvalue extends Application {


    final Group root = new Group();
    final Xform world = new Xform();
    final PerspectiveCamera camera = new PerspectiveCamera(true);
    final Xform cameraXform = new Xform();
    final Xform cameraXform2 = new Xform();
    final Xform cameraXform3 = new Xform();
    private static final double CAMERA_INITIAL_DISTANCE = -5000;
    private static final double CAMERA_INITIAL_X_ANGLE = 0.0;
    private static final double CAMERA_INITIAL_Y_ANGLE = 320.0;
    private static final double CAMERA_NEAR_CLIP = 0.1;
    private static final double CAMERA_FAR_CLIP = 10000.0;
    private static final double CONTROL_MULTIPLIER = 0.1;
    private static final double SHIFT_MULTIPLIER = 10.0;
    private static final double MOUSE_SPEED = 0.1;
    private static final double ROTATION_SPEED = 2.0;
    private static final double TRACK_SPEED = 0.3;
    final int EARTH_RADIUS = 150;

    double mousePosX;
    double mousePosY;
    double mouseOldX;
    double mouseOldY;
    double mouseDeltaX;
    double mouseDeltaY;


    @Override
    public void start(Stage primaryStage) {

      /*  String temp ="\\src\\com\\PRIME\\javafx3D\\World_map_blank_without_borders.svg";
        String temp3=(System.getProperty("user.dir")+temp);
         temp3 =temp3.replaceAll("/","\\\\\\\\");


        MyTranscoder transcoder = new MyTranscoder();
        TranscodingHints hints = new TranscodingHints();
        hints.put(ImageTranscoder.KEY_WIDTH, 900f); //your image width
        hints.put(ImageTranscoder.KEY_HEIGHT, 500f); //your image height
        hints.put(ImageTranscoder.KEY_DOM_IMPLEMENTATION,    SVGDOMImplementation.getDOMImplementation());
        hints.put(ImageTranscoder.KEY_DOCUMENT_ELEMENT_NAMESPACE_URI, SVGConstants.SVG_NAMESPACE_URI);
        hints.put(ImageTranscoder.KEY_DOCUMENT_ELEMENT, SVGConstants.SVG_SVG_TAG);
        hints.put(ImageTranscoder.KEY_XML_PARSER_VALIDATING, false);

        transcoder.setTranscodingHints(hints);
        TranscoderInput input = null;

        try {
            input = new TranscoderInput(new FileReader(new File(temp3)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            transcoder.transcode(input, null);
        } catch (TranscoderException e) {
            e.printStackTrace();
        }
        BufferedImage bufferedImage = transcoder.getImage();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        JPEGImageEncoder imageEncoder = JPEGCodec.createJPEGEncoder(outputStream);
        try {
            imageEncoder.encode(bufferedImage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] bytes = outputStream.toByteArray();
        InputStream inputStream = new ByteArrayInputStream(bytes);

//javafx.scene.image.Image
        Image image = new Image(inputStream);
        //Coordinates.transfer();
        */
      // List<Pair<Double,Double>> coordinates = Coordinates.transfer();
        //Point3D [] temp = convert.method();
        double width =1000;
        double height = 1000;
        double depths =10;
        double translate = 1000;
        Box testBox = new Box(width,depths,height);
        testBox.setTranslateX(0);
        testBox.setTranslateY(translate);
        testBox.setTranslateZ(0);
        PhongMaterial material = new PhongMaterial();
        //material.setDiffuseMap(img);
        //testBox.setMaterial(material);
        testBox.setDrawMode(DrawMode.LINE);
        world.getChildren().add(testBox);
        BufferedReader bufferedReader;
        try {
            //BufferedReader fot  file input
            Stage stage= new Stage();
            final FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(stage);
            bufferedReader = new BufferedReader(new FileReader(file));
            bufferedReader.readLine();
            String text;
            Integer count=0;
            //Reading each line and storing each parameter value to temporary matrix
            while ((text = bufferedReader.readLine()) != null && text.length() > 0) {


                //Checking for empty lines
                //count<5000)||(count<1006000&&count>1001000
                if ((count<200000)) {
                    String text2 = text.replaceAll(",", " ");
                    int textindex = 0;

                    Double longitude = Double.parseDouble(text.substring(textindex, text2.indexOf(" ", textindex)));

                    //      System.out.print(text.substring(textindex, text2.indexOf(" ", textindex))+" ");
                    textindex = text2.indexOf(" ", textindex) + 1;
                    Double latitude = Double.parseDouble(text.substring(textindex, text2.indexOf(" ", textindex)));

                    //System.out.print(text.substring(textindex, text2.indexOf(" ", textindex))+" ");
                    textindex = text2.indexOf(" ", textindex) + 1;
                    Double depth = Double.parseDouble(text.substring(textindex, text2.indexOf(" ", textindex)));

                    //System.out.print(text.substring(textindex, text2.indexOf(" ", textindex))+" ");
                    textindex = text2.indexOf(" ", textindex) + 1;
                    Double value = Double.parseDouble(text.substring(textindex, text2.length()));
                    //Intensity.add(value);
                    // System.out.println(text.substring(textindex, text2.length()));

                    latitude = latitude * Math.PI / 180;
                    longitude = longitude * Math.PI / 180;


                    double x = EARTH_RADIUS * Math.sin(latitude) * Math.cos(longitude);
                    double y = EARTH_RADIUS * Math.sin(latitude) * Math.sin(longitude);
                   // double z = (EARTH_RADIUS * Math.cos(latitude))+depth;
                    double z = (translate)-depth;


                    //dataX.add(longitude);
                    //dataY.add(latitude);
                    //dataZ.add(depth);

                    Sphere sphere1 = new Sphere(3);
                    sphere1.setTranslateX(x);
                    sphere1.setTranslateY(z);
                    sphere1.setTranslateZ(y);
                    root.getChildren().add(sphere1);
                    sphere1= null;
                    System.out.println(count);

                }
                if(count%500000==0)System.gc();

                count++;
            }

        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        } finally {
        }
       // for(Integer i=0;i<temp.length;i++) {
      /*  for (Pair<Double,Double> coordinate : coordinates){
            //Drawing Sphere1

         Cone sphere1 = new Cone(34,2,20,Color.GREEN);

            //sphere1.setMaterial(new PhongMaterial(Color.RED));
            double latitude = coordinate.getKey();
            double longitude = coordinate.getValue();

            String tooltipText = latitude+" "+longitude;
            Tooltip tooltip = new Tooltip(tooltipText);
            tooltip.setFont(Font.font("", 10));
            new hackTooltipStartTiming(tooltip); //use this tooltip function for 0 delay between hover and showing tooltip
            Tooltip.install(sphere1,tooltip);

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
*/
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



