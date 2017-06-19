package com.PRIME.main.operations.mainMenus.projectMenu;


import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;

import com.lynden.gmapsfx.javascript.object.*;




import javafx.event.EventHandler;


import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;

import javafx.stage.Stage;
import javafx.util.Pair;
import java.util.ArrayList;

public class projectViewer  implements MapComponentInitializedListener {
    double mousePosX;
    double mousePosY;
    double mouseOldX;
    double mouseOldY;
    double mouseDeltaX;
    double mouseDeltaY;

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
        setMarker.setOnAction(e -> {
            window w = new window();
            this.coordinates = w.coordinates();
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
//        subscene.addEventFilter(MouseEvent.MOUSE_ENTERED_TARGET, new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                System.out.println("");
//            }
//        });

        BorderPane vbox = new BorderPane();
        vbox.setTop(setMarker);
        vbox.setCenter(subscene);

        Scene scene = new Scene(vbox,1024,800);
        //scene.getX();
        //scene.set
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
    public void mapInitialized() {

        LatLong center = new LatLong(28.6139, 77.2090);
        MapOptions options = new MapOptions();

        options.center(center).mapMarker(true).zoom(3).overviewMapControl(true).panControl(false).rotateControl(false).scaleControl(false).streetViewControl(false).zoomControl(true).mapType(MapTypeIdEnum.HYBRID);
        this.map = this.mapComponent.createMap(options);

    }
}
