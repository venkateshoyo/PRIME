package com.PRIME.main.operations.mainMenus.editMenu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class editmenulist {

    public static Menu editmenulist(){
        //Edit Menu
        Menu editMenu = new Menu("_Edit");

        //Edit Sub Menu starts------------------------------------------------------------------------------------------

        MenuItem editLogs = new MenuItem( "_Logs");
        editLogs.setOnAction(e -> System.out.println(""));

        MenuItem seismic = new MenuItem( "_Seismic");
        editLogs.setOnAction(e -> System.out.println(""));

        MenuItem ASCII = new MenuItem( "_ASCII");
        ASCII.setOnAction(e -> System.out.println(""));

        MenuItem Geometry = new MenuItem( "_Geometry");
        Geometry.setOnAction(e -> System.out.println(""));

        editMenu.getItems().addAll(editLogs,seismic,ASCII,Geometry);

        //Edit Sub Menu ends--------------------------------------------------------------------------------------------

        return editMenu;
    }
}
