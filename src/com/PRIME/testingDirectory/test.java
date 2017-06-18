package com.PRIME.testingDirectory;


import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.elevation.ElevationResult;
import com.lynden.gmapsfx.elevation.ElevationService;
import com.lynden.gmapsfx.elevation.ElevationServiceCallback;
import com.lynden.gmapsfx.elevation.ElevationStatus;
import com.lynden.gmapsfx.elevation.LocationElevationRequest;
import com.lynden.gmapsfx.elevation.PathElevationRequest;
import com.lynden.gmapsfx.javascript.event.UIEventHandler;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.Animation;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.LatLongBounds;
import com.lynden.gmapsfx.javascript.object.MVCArray;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.shapes.ArcBuilder;
import com.lynden.gmapsfx.shapes.Circle;
import com.lynden.gmapsfx.shapes.CircleOptions;
import com.lynden.gmapsfx.shapes.Polygon;
import com.lynden.gmapsfx.shapes.PolygonOptions;
import com.lynden.gmapsfx.shapes.Polyline;
import com.lynden.gmapsfx.shapes.PolylineOptions;
import com.lynden.gmapsfx.shapes.Rectangle;
import com.lynden.gmapsfx.shapes.RectangleOptions;
import com.lynden.gmapsfx.zoom.MaxZoomResult;
import com.lynden.gmapsfx.zoom.MaxZoomService;
import com.lynden.gmapsfx.zoom.MaxZoomServiceCallback;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

public class test  implements MapComponentInitializedListener {

    protected GoogleMapView mapComponent;
    protected GoogleMap map;
    private Button btnZoomIn;
    private Button btnZoomOut;
    private Label lblZoom;
    private Label lblCenter;
    private Label lblClick;
    private ComboBox<MapTypeIdEnum> mapTypeCombo;
    public test() {
    }
    public void project() throws Exception {
        Stage stage = new Stage();
        this.mapComponent = new GoogleMapView();
        this.mapComponent.addMapInializedListener(this);
        BorderPane bp = new BorderPane();
        ToolBar tb = new ToolBar();
        this.btnZoomIn = new Button("Zoom In");
        this.btnZoomIn.setOnAction((e) -> {
            this.map.zoomProperty().set(this.map.getZoom() + 1);
        });
        this.btnZoomIn.setDisable(true);
        this.btnZoomOut = new Button("Zoom Out");
        this.btnZoomOut.setOnAction((e) -> {
            this.map.zoomProperty().set(this.map.getZoom() - 1);
        });
        this.btnZoomOut.setDisable(true);
        this.lblZoom = new Label();
        this.lblCenter = new Label();
        this.lblClick = new Label();
        this.mapTypeCombo = new ComboBox();
        this.mapTypeCombo.setOnAction((e) -> {
            this.map.setMapType((MapTypeIdEnum)this.mapTypeCombo.getSelectionModel().getSelectedItem());
        });
        this.mapTypeCombo.setDisable(true);
        Button btnType = new Button("Map type");
        btnType.setOnAction((e) -> {
            this.map.setMapType(MapTypeIdEnum.HYBRID);
        });
        tb.getItems().addAll(new Node[]{this.btnZoomIn, this.btnZoomOut, this.mapTypeCombo, new Label("Zoom: "), this.lblZoom, new Label("Center: "), this.lblCenter, new Label("Click: "), this.lblClick});
        bp.setTop(tb);
        bp.setCenter(this.mapComponent);
        Scene scene = new Scene(bp);
        stage.setScene(scene);
        stage.show();
    }
    public void mapInitialized() {
        LatLong center = new LatLong(47.606189D, -122.335842D);
        this.mapComponent.addMapReadyListener(() -> {
            this.checkCenter(center);
        });
        MapOptions options = new MapOptions();
        options.center(center).mapMarker(true).zoom(9).overviewMapControl(false).panControl(false).rotateControl(false).scaleControl(false).streetViewControl(false).zoomControl(false).mapType(MapTypeIdEnum.TERRAIN);
        this.map = this.mapComponent.createMap(options);
        MarkerOptions markerOptions = new MarkerOptions();
        LatLong markerLatLong = new LatLong(47.606189D, -122.335842D);
        markerOptions.position(markerLatLong).title("My new Marker").animation(Animation.DROP).visible(Boolean.valueOf(true));
        Marker myMarker = new Marker(markerOptions);
        MarkerOptions markerOptions2 = new MarkerOptions();
        LatLong markerLatLong2 = new LatLong(47.906189D, -122.335842D);
        markerOptions2.position(markerLatLong2).title("My new Marker").visible(Boolean.valueOf(true));
        Marker myMarker2 = new Marker(markerOptions2);
        this.map.addMarker(myMarker);
        this.map.addMarker(myMarker2);
        InfoWindowOptions infoOptions = new InfoWindowOptions();
        infoOptions.content("<h2>Here's an info window</h2><h3>with some info</h3>").position(center);
        InfoWindow window = new InfoWindow(infoOptions);
        window.open(this.map, myMarker);
        this.lblCenter.setText(this.map.getCenter().toString());
        this.map.centerProperty().addListener((obs, o, n) -> {
            this.lblCenter.setText(n.toString());
        });
        this.lblZoom.setText(Integer.toString(this.map.getZoom()));
        this.map.zoomProperty().addListener((obs, o, n) -> {
            this.lblZoom.setText(n.toString());
        });
        this.map.addUIEventHandler(UIEventType.click, (obj) -> {
            LatLong ll = new LatLong((JSObject)obj.getMember("latLng"));
            this.lblClick.setText(ll.toString());
        });
        this.btnZoomIn.setDisable(false);
        this.btnZoomOut.setDisable(false);
        this.mapTypeCombo.setDisable(false);
        this.mapTypeCombo.getItems().addAll(MapTypeIdEnum.ALL);
        LatLong[] ary = new LatLong[]{markerLatLong, markerLatLong2};
        MVCArray mvc = new MVCArray(ary);
        PolylineOptions polyOpts = (PolylineOptions)((PolylineOptions)(new PolylineOptions()).path(mvc).strokeColor("red")).strokeWeight(2.0D);
        Polyline poly = new Polyline(polyOpts);
        this.map.addMapShape(poly);
        this.map.addUIEventHandler(poly, UIEventType.click, (obj) -> {
            LatLong ll = new LatLong((JSObject)obj.getMember("latLng"));
            System.out.println("You clicked the line at LatLong: lat: " + ll.getLatitude() + " lng: " + ll.getLongitude());
        });
        LatLong poly1 = new LatLong(47.429945D, -122.84363D);
        LatLong poly2 = new LatLong(47.361153D, -123.0304D);
        LatLong poly3 = new LatLong(47.387193D, -123.11554D);
        LatLong poly4 = new LatLong(47.585789D, -122.96722D);
        LatLong[] pAry = new LatLong[]{poly1, poly2, poly3, poly4};
        MVCArray pmvc = new MVCArray(pAry);
        PolygonOptions polygOpts = (PolygonOptions)((PolygonOptions)((PolygonOptions)((PolygonOptions)((PolygonOptions)(new PolygonOptions()).paths(pmvc).strokeColor("blue")).strokeWeight(2.0D)).editable(false)).fillColor("lightBlue")).fillOpacity(0.5D);
        Polygon pg = new Polygon(polygOpts);
        this.map.addMapShape(pg);
        this.map.addUIEventHandler(pg, UIEventType.click, (obj) -> {
            pg.setEditable(!pg.getEditable());
        });
        LatLong centreC = new LatLong(47.545481D, -121.87384D);
        CircleOptions cOpts = (CircleOptions)((CircleOptions)((CircleOptions)((CircleOptions)(new CircleOptions()).center(centreC).radius(5000.0D).strokeColor("green")).strokeWeight(2.0D)).fillColor("orange")).fillOpacity(0.3D);
        Circle c = new Circle(cOpts);
        this.map.addMapShape(c);
        this.map.addUIEventHandler(c, UIEventType.click, (obj) -> {
            c.setEditable(!c.getEditable());
        });
        LatLongBounds llb = new LatLongBounds(new LatLong(47.533893D, -122.89856D), new LatLong(47.580694D, -122.80312D));
        RectangleOptions rOpts = (RectangleOptions)((RectangleOptions)((RectangleOptions)(new RectangleOptions()).bounds(llb).strokeColor("black")).strokeWeight(2.0D)).fillColor("null");
        Rectangle rt = new Rectangle(rOpts);
        this.map.addMapShape(rt);
        LatLong arcC = new LatLong(47.227029D, -121.81641D);
        double startBearing = 0.0D;
        double endBearing = 30.0D;
        double radius = 30000.0D;
        MVCArray path = ArcBuilder.buildArcPoints(arcC, startBearing, endBearing, radius);
        path.push(arcC);
        Polygon arc = new Polygon((PolygonOptions)((PolygonOptions)((PolygonOptions)((PolygonOptions)((PolygonOptions)(new PolygonOptions()).paths(path).strokeColor("blue")).fillColor("lightBlue")).fillOpacity(0.3D)).strokeWeight(2.0D)).editable(false));
        this.map.addMapShape(arc);
        this.map.addUIEventHandler(arc, UIEventType.click, (obj) -> {
            arc.setEditable(!arc.getEditable());
        });
        LatLong ll = new LatLong(-41.2D, 145.9D);
        LocationElevationRequest ler = new LocationElevationRequest(new LatLong[]{ll});
        ElevationService es = new ElevationService();
        es.getElevationForLocations(ler, new ElevationServiceCallback() {
            public void elevationsReceived(ElevationResult[] results, ElevationStatus status) {
                System.out.println("We got results from the Location Elevation request:");
                ElevationResult[] var3 = results;
                int var4 = results.length;
                for(int var5 = 0; var5 < var4; ++var5) {
                    ElevationResult er = var3[var5];
                    System.out.println("LER: " + er.getElevation());
                }
            }
        });
        LatLong lle = new LatLong(-42.2D, 145.9D);
        PathElevationRequest per = new PathElevationRequest(new LatLong[]{ll, lle}, 3);
        ElevationService esb = new ElevationService();
        esb.getElevationAlongPath(per, new ElevationServiceCallback() {
            public void elevationsReceived(ElevationResult[] results, ElevationStatus status) {
                System.out.println("We got results from the Path Elevation Request:");
                ElevationResult[] var3 = results;
                int var4 = results.length;
                for(int var5 = 0; var5 < var4; ++var5) {
                    ElevationResult er = var3[var5];
                    System.out.println("PER: " + er.getElevation());
                }
            }
        });
        MaxZoomService mzs = new MaxZoomService();
        mzs.getMaxZoomAtLatLng(lle, new MaxZoomServiceCallback() {
            public void maxZoomReceived(MaxZoomResult result) {
                System.out.println("Max Zoom Status: " + result.getStatus());
                System.out.println("Max Zoom: " + result.getMaxZoom());
            }
        });
    }
    private void checkCenter(LatLong center) {
        System.out.println("Testing fromLatLngToPoint using: " + center);
        Point2D p = this.map.fromLatLngToPoint(center);
        System.out.println("Testing fromLatLngToPoint result: " + p);
        System.out.println("Testing fromLatLngToPoint expected: " + this.mapComponent.getWidth() / 2.0D + ", " + this.mapComponent.getHeight() / 2.0D);
    }

}
