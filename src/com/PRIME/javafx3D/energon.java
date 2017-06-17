import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;
import com.sun.j3d.utils.behaviors.mouse.MouseZoom;
import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.*;
import javax.swing.*;
import javax.vecmath.Color3f;
import javax.vecmath.Point3f;
import java.awt.*;

/**
 * Created by hkban on 6/17/2017.
 */
public final class energon extends JPanel {


    int s = 0, count = 0;

    public energon() {
        setLayout(new BorderLayout());
        GraphicsConfiguration gc=SimpleUniverse.getPreferredConfiguration();
        Canvas3D canvas3D = new Canvas3D(gc);
        add("Center", canvas3D);

        BranchGroup scene = createSceneGraph();
        scene.compile();
        // SimpleUniverse is a Convenience Utility class
        SimpleUniverse simpleU = new SimpleUniverse(canvas3D);
        // This moves the ViewPlatform back a bit so the
        // objects in the scene can be viewed.
        simpleU.getViewingPlatform().setNominalViewingTransform();
        simpleU.addBranchGraph(scene);
    }
    public BranchGroup createSceneGraph() {
        BranchGroup lineGroup = new BranchGroup();
        Appearance app = new Appearance();
        ColoringAttributes ca = new ColoringAttributes(new Color3f(204.0f, 204.0f,          204.0f), ColoringAttributes.SHADE_FLAT);
        app.setColoringAttributes(ca);

        Point3f[] plaPts = new Point3f[4];
        Color3f[] colPts=new Color3f[4]; //parallel to coordinates, colors.
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j <2; j++) {
                plaPts[count] = new Point3f(i/10.0f,j/10.0f,0);
                colPts[count]=new Color3f(i/3.0f,j/3.0f,(float) ((i+j)/3.0));//my arbitrary color set :)
                count++;
            }
        }
        PointArray pla = new PointArray(4, GeometryArray.COORDINATES|GeometryArray.COLOR_3);
        pla.setColors(0,colPts); //this is the color-array setting
        pla.setCoordinates(0, plaPts);
        //between here!
        PointAttributes a_point_just_bigger=new PointAttributes();
        a_point_just_bigger.setPointSize(10.0f);//10 pixel-wide point

        a_point_just_bigger.setPointAntialiasingEnable(true);//now points are sphere-like(not a cube)
        app.setPointAttributes(a_point_just_bigger);
        //and here! sets the point-attributes so it is easily seen.
        Shape3D plShape = new Shape3D(pla, app);
        TransformGroup objRotate = new TransformGroup();
        MouseRotate m1=new MouseRotate();
        MouseZoom m2=new MouseZoom();
        MouseTranslate m3=new MouseTranslate();
        m1.setTransformGroup(objRotate);
        m2.setTransformGroup(objRotate);
        m3.setTransformGroup(objRotate);
        m1.setSchedulingBounds(new BoundingSphere());
      //  m2.setSchedulingBounds(new BoundingSphere());
       // m3.setSchedulingBounds(new BoundingSphere());
        lineGroup.addChild(m1);
        lineGroup.addChild(m2);
        lineGroup.addChild(m3);
        objRotate.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        objRotate.addChild(plShape);
        lineGroup.addChild(objRotate);
        return lineGroup;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.add(new JScrollPane(new energon()));
        frame.setSize(300, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}