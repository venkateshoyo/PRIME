package com.PRIME.main.operations.main;

import com.PRIME.main.operations.menubars.analysisMenu.analysismenulist;
import com.PRIME.main.operations.menubars.editMenu.editmenulist;
import com.PRIME.main.operations.menubars.fileMenu.filemenulist;
import com.PRIME.main.operations.menubars.projectMenu.*;
import com.PRIME.main.operations.menubars.dataMenu.*;
import com.PRIME.main.operations.menubars.helpMenu.*;
import com.PRIME.main.operations.menubars.displayMenu.*;
import com.PRIME.main.operations.menubars.programmerMenu.*;

import com.PRIME.main.operations.toolbars.analysisMenu.crossplot;
import com.PRIME.main.operations.toolbars.toolBarFontAwesome;
import com.PRIME.main.operations.toolbars.dataMenu.lasload;
import com.PRIME.main.operations.toolbars.displayMenu.display;
import com.PRIME.main.operations.toolbars.editMenu.edit;
import com.PRIME.main.operations.toolbars.fileMenu.file;
import com.PRIME.main.operations.toolbars.helpMenu.help;
import com.PRIME.main.operations.toolbars.programmerMenu.calculator;
import com.PRIME.main.operations.toolbars.projectMenu.recent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

class menubar {

    VBox vbox = new VBox();
    public VBox displayMenuBar(){

        main ob = new main();
        Stage mainstage = ob.getstage();
        MenuBar menuBar= new MenuBar();
        vbox.getChildren().addAll(menuBar, toolBarFontAwesome.method());

        //File Menu
        Menu fileMenu = filemenulist.filemenulist();
        fileMenu.setOnShown(e->{
            vbox.getChildren().clear();
            vbox.getChildren().addAll(menuBar, file.fileToolbar());
        });

        //Edit Menu
        Menu editMenu = editmenulist.editmenulist();
        editMenu.setOnShown(e->{
            vbox.getChildren().clear();
            vbox.getChildren().addAll(menuBar, edit.editToolbar());
        });

        //Project Menu
        Menu projectMenu = projectmenulist.projectmenulist();
        projectMenu.setOnShown(e->{
            vbox.getChildren().clear();
            vbox.getChildren().addAll(menuBar, recent.recentToolbar() );
        });

        //Data Menu
        Menu dataMenu = datamenulist.datamenulist(mainstage);
        dataMenu.setOnShown(e->{
            vbox.getChildren().clear();
            vbox.getChildren().addAll(menuBar, lasload.lasloadToolbar());
        });

        //Analysis Menu
        Menu analysisMenu = analysismenulist.analysismenulist(mainstage);
        analysisMenu.setOnShown(e->{
            vbox.getChildren().clear();
            vbox.getChildren().addAll(menuBar, crossplot.crossplotToolbar());
        });

        //Display Menu
        Menu displayMenu = displaymenulist.displaymenulist();
        displayMenu.setOnShown(e->{
            vbox.getChildren().clear();
            vbox.getChildren().addAll(menuBar, display.displayToolbar());
        });

        //Programmer Menu
        Menu programmerMenu = programmermenulist.programmermenulist();
        programmerMenu.setOnShown(e->{
            vbox.getChildren().clear();
            vbox.getChildren().addAll(menuBar, calculator.calculatorToolbar() );
        });

        //Help Menu
        Menu helpMenu = helpmenulist.helpmenulist();
        helpMenu.setOnShown(e->{
            vbox.getChildren().clear();
            vbox.getChildren().addAll(menuBar, help.helpToolbar());
        });

        //Menu Bar

        menuBar.getStyleClass().add("topmenu");
        menuBar.getMenus().addAll(fileMenu,projectMenu,dataMenu,editMenu,analysisMenu,displayMenu, programmerMenu,helpMenu);

        return vbox;
    }
}
