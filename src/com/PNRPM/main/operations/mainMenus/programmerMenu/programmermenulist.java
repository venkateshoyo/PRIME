package com.PNRPM.main.operations.mainMenus.programmerMenu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class programmermenulist {

    public static Menu programmermenulist(){
        //Programmer Menu
        Menu programmerMenu = new Menu("P_rogrammer");
        //Programmer Sub Menu starts------------------------------------------------------------------------------------

        MenuItem scienticcalcultor = new MenuItem( "Scientific _Calculator");
        scienticcalcultor.setOnAction(e -> System.out.println(""));

        MenuItem simplecalcultor = new MenuItem( "_Simple Calculator");
        simplecalcultor.setOnAction(e -> System.out.println(""));

        MenuItem programmernotepad = new MenuItem( "Programmer _Notepad");
        programmernotepad.setOnAction(e -> System.out.println(""));

        MenuItem editorconsole = new MenuItem( "_Editor and Console");
        editorconsole.setOnAction(e -> System.out.println(""));

        programmerMenu.getItems().addAll(simplecalcultor,scienticcalcultor,programmernotepad,editorconsole);
        //Programmer Sub Menu ends--------------------------------------------------------------------------------------

        return programmerMenu;
    }
}