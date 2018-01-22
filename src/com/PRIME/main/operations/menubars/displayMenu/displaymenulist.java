package com.PRIME.main.operations.menubars.displayMenu;

import com.PRIME.main.operations.menubars.displayMenu.surface.display;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class displaymenulist {

    public static Menu displaymenulist(){
        //Display Menu
        Menu displayMenu = new Menu("_Display");

        MenuItem display_location = new MenuItem( "_Location");
        display_location.setOnAction(e -> System.out.println(""));

        MenuItem display_logs = new MenuItem( "L_ogs");
        display_logs.setOnAction(e -> {
            display surface = new display();
            try {
                surface.mainmethod();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        MenuItem display_seimic = new MenuItem( "_Seismic");
        display_seimic.setOnAction(e -> System.out.println(""));

        MenuItem display_ASCII = new MenuItem( "_ASCII");
        display_ASCII.setOnAction(e -> System.out.println(""));

        displayMenu.getItems().addAll(display_location,display_logs,display_seimic,display_ASCII);

        //Display Sub Menu ends--------------------------------------------------------------------------------------------

        return displayMenu;
    }
}