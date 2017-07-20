package com.PRIME.main.operations.menubars.projectMenu;


import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;

import com.lynden.gmapsfx.javascript.event.MapStateEventType;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.*;


import javafx.event.EventHandler;


import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Pair;
import netscape.javascript.JSObject;

import java.io.IOException;
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
        mapComponent.autosize();


        BorderPane bp = new BorderPane();

        this.setMarker = new Button("Load Las File");
        setMarker.setOnAction(e -> {

            try {
                this.coordinates = loadfile.coordinates();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            if (coordinates.getValue() != "empty") {
                MarkerOptions markerOptions = new MarkerOptions();
                LatLong markerLatLong = new LatLong(coordinates.getKey().getKey(), coordinates.getKey().getValue());
                map.setCenter(markerLatLong);
                markerOptions.position(markerLatLong).title(coordinates.getKey().getKey() + "," + coordinates.getKey().getValue()).animation(Animation.DROP).visible(Boolean.valueOf(true));
                Marker myMarker = new Marker(markerOptions);
                this.map.addMarker(myMarker);
                if (!coordinates.getValue().isEmpty()) {
                    InfoWindowOptions infoOptions = new InfoWindowOptions();
                    infoOptions.content(coordinates.getValue()).position(markerLatLong);
                    InfoWindow window = new InfoWindow(infoOptions);
                    window.open(this.map, myMarker);
                }

            }
        });

        bp.setCenter(this.mapComponent);

        SubScene subscene = new SubScene(bp, 1024, 900);

        subscene.addEventFilter(ScrollEvent.SCROLL, new EventHandler<ScrollEvent>() {

            @Override
            public void handle(ScrollEvent event) {
               if(map.getZoom()<4)
                   mapComponent.setZoom(3);
            }

        });
        Label harsh = new Label();



        mapComponent.addEventFilter(MouseEvent.MOUSE_EXITED_TARGET, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
               // mapComponent.setCenter(28.6139, 77.2090);
               // System.out.println("harsh");
            }
        });



        BorderPane vbox = new BorderPane();
        vbox.setTop(setMarker);
        vbox.setBottom(harsh);
        vbox.setCenter(subscene);

        Scene scene = new Scene(vbox,1024,800);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
    public void mapInitialized() {

        LatLong center = new LatLong(28.6139, 77.2090);
        MapOptions options = new MapOptions();

       options.center(center).mapMarker(true).zoom(5).overviewMapControl(true).panControl(false).rotateControl(false).scaleControl(false).streetViewControl(false).zoomControl(true).mapType(MapTypeIdEnum.HYBRID);
        this.map = this.mapComponent.createMap(options);
      map.addStateEventHandler(MapStateEventType.bounds_changed, () -> {
         double lati = map.getCenter().getLatitude();
           double longi = map.getCenter().getLongitude();
          if(map.getCenter().getLatitude()>83)
           { mapComponent.setCenter(83,longi);}
           if(map.getCenter().getLatitude()<-83)
           { mapComponent.setCenter(-83,longi);};
		});
        map.addUIEventHandler(UIEventType.click, (JSObject obj) -> {

        });

    }
}
