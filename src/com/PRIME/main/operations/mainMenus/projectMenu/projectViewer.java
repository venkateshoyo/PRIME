package com.PRIME.main.operations.mainMenus.projectMenu;


import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.elevation.*;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.*;
import com.lynden.gmapsfx.shapes.*;
import com.lynden.gmapsfx.shapes.Polyline;
import com.lynden.gmapsfx.shapes.PolylineOptions;


import com.lynden.gmapsfx.zoom.MaxZoomResult;
import com.lynden.gmapsfx.zoom.MaxZoomService;
import com.lynden.gmapsfx.zoom.MaxZoomServiceCallback;
import javafx.geometry.Point2D;

import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;
import netscape.javascript.JSObject;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class projectViewer  implements MapComponentInitializedListener {

    protected GoogleMapView mapComponent;
    protected GoogleMap map;
    public Button setMarker;
    public Pair<Pair<Double, Double>,String> coordinates;
    public ArrayList<LatLong> ary = new ArrayList<>();

    public void project() throws Exception {

        Stage stage = new Stage();
        this.mapComponent = new GoogleMapView();
       this.mapComponent.addMapInializedListener(this);


        BorderPane bp = new BorderPane();

        this.setMarker = new Button("setMarker");
        setMarker.setOnAction(e->{
            window w = new window();
            this.coordinates = w.coordinates();
            if(coordinates.getValue()!="empty") {
                MarkerOptions markerOptions = new MarkerOptions();
                LatLong markerLatLong = new LatLong(coordinates.getKey().getKey(), coordinates.getKey().getValue());
                map.setCenter(markerLatLong);
                markerOptions.position(markerLatLong).title(coordinates.getKey().getKey() + "," + coordinates.getKey().getValue()).animation(Animation.DROP).visible(Boolean.valueOf(true));
                Marker myMarker = new Marker(markerOptions);
                this.map.addMarker(myMarker);
                if(!coordinates.getValue().isEmpty()){
                InfoWindowOptions infoOptions = new InfoWindowOptions();
                infoOptions.content(coordinates.getValue()).position(markerLatLong);
                InfoWindow window = new InfoWindow(infoOptions);
                window.open(this.map, myMarker);}

            }
        });

        bp.setCenter(this.mapComponent);

        SubScene subscene = new SubScene(bp,800,900);
       Camera camera= new PerspectiveCamera();
        //camera.setTranslateZ(500);
        //subscene.setCamera(camera);

      camera.setTranslateY(15);
//        if(bp.getScaleX()<1)
//            bp.setScaleX(1);
//        if(bp.getScaleY()<2)
//            bp.setScaleY(2);
        subscene.setCamera(camera);

        BorderPane vbox = new BorderPane();
        vbox.setTop(setMarker);
        vbox.setCenter(subscene);

        Scene scene = new Scene(vbox,800,800);
        //scene.getX();
        //scene.set
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
    public void mapInitialized() {

        LatLong center = new LatLong(28.6139, 77.2090);
        MapOptions options = new MapOptions();

        options.center(center).mapMarker(true).zoom(2).overviewMapControl(true).panControl(false).rotateControl(false).scaleControl(false).streetViewControl(false).zoomControl(true).mapType(MapTypeIdEnum.HYBRID);
        this.map = this.mapComponent.createMap(options);

    }
}
