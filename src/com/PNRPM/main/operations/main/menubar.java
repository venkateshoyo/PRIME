package com.PNRPM.main.operations.main;

import com.PNRPM.main.operations.mainMenus.analysisMenu.analysismenulist;
import com.PNRPM.main.operations.mainMenus.editMenu.editmenulist;
import com.PNRPM.main.operations.mainMenus.fileMenu.filemenulist;
import com.PNRPM.main.operations.mainMenus.projectMenu.*;
import com.PNRPM.main.operations.mainMenus.dataMenu.*;
import com.PNRPM.main.operations.mainMenus.helpMenu.*;
import com.PNRPM.main.operations.mainMenus.displayMenu.*;
import com.PNRPM.main.operations.mainMenus.programmerMenu.*;

import javafx.scene.control.*;
import javafx.stage.Stage;

class menubar {

    public MenuBar displayMenuBar(){

        main ob = new main();
        Stage mainstage = ob.getstage();

        //File Menu
        Menu fileMenu = filemenulist.filemenulist();

        //Edit Menu
        Menu editMenu = editmenulist.editmenulist();

        //Project Menu
        Menu projectMenu = projectmenulist.projectmenulist();

        //Data Menu
        Menu dataMenu = datamenulist.datamenulist(mainstage);

        //Analysis Menu
        Menu analysisMenu = analysismenulist.analysismenulist(mainstage);

        //Display Menu
        Menu displayMenu = displaymenulist.displaymenulist();

        //Programmer Menu
        Menu programmerMenu = programmermenulist.programmermenulist();

        //Help Menu
        Menu helpMenu = helpmenulist.helpmenulist();

        //Menu Bar
        MenuBar menuBar= new MenuBar();
        menuBar.getStyleClass().add("topmenu");
        menuBar.getMenus().addAll(fileMenu,projectMenu,dataMenu,editMenu,analysisMenu,displayMenu, programmerMenu,helpMenu);

        return menuBar;
    }
}
