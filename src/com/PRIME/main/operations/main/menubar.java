package com.PRIME.main.operations.main;

import com.PRIME.main.operations.mainMenus.analysisMenu.analysismenulist;
import com.PRIME.main.operations.mainMenus.editMenu.editmenulist;
import com.PRIME.main.operations.mainMenus.fileMenu.filemenulist;
import com.PRIME.main.operations.mainMenus.projectMenu.*;
import com.PRIME.main.operations.mainMenus.dataMenu.*;
import com.PRIME.main.operations.mainMenus.helpMenu.*;
import com.PRIME.main.operations.mainMenus.displayMenu.*;
import com.PRIME.main.operations.mainMenus.programmerMenu.*;

import com.PRIME.main.operations.toolbars.analysisMenu.crossplot;
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

import javax.swing.*;

class menubar {

    file fb = new file();
    crossplot crb = new crossplot();
    lasload lb = new lasload();
    display db =new display();
    edit eb = new edit();
    help hb = new help();
    recent rb = new recent();
    calculator cab = new calculator();
    ToolBar toolBar = new ToolBar();

    public VBox displayMenuBar(){
        VBox vBox= new VBox(10);
        main ob = new main();
        Stage mainstage = ob.getstage();

        //Menu Bar
        MenuBar menuBar= new MenuBar();
        menuBar.getStyleClass().add("topmenu");

        //File Menu
        Menu fileMenu = filemenulist.filemenulist();
        fileMenu.setOnAction(e->{
            this.toolBar=new ToolBar(fb.fileToolbar());
        });

        //Edit Menu
        Menu editMenu = editmenulist.editmenulist();
        editMenu.setOnAction(e->{
          this.toolBar= new ToolBar(eb.editToolbar());
        });

        //Project Menu
        Menu projectMenu = projectmenulist.projectmenulist();
        projectMenu.setOnAction(e->{
            this.toolBar= new ToolBar(rb.recentToolbar());
        });

        //Data Menu
        Menu dataMenu = datamenulist.datamenulist(mainstage);
        dataMenu.setOnAction(e->{
            this.toolBar= new ToolBar(lb.lasloadToolbar());
        });

        //Analysis Menu
        Menu analysisMenu = analysismenulist.analysismenulist(mainstage);
        analysisMenu.setOnAction(e->{
            this.toolBar= new ToolBar(crb.crossplotToolbar());
        });

        //Display Menu
        Menu displayMenu = displaymenulist.displaymenulist();
        displayMenu.setOnAction(e->{
            this.toolBar= new ToolBar(db.displayToolbar());
        });

        //Programmer Menu
        Menu programmerMenu = programmermenulist.programmermenulist();
        programmerMenu.setOnAction(e->{
            this.toolBar= new ToolBar(cab.calculatorToolbar());
        });

        //Help Menu
        Menu helpMenu = helpmenulist.helpmenulist();
        helpMenu.setOnAction(e->{
            this.toolBar= new ToolBar(hb.helpToolbar());;
        });

        menuBar.getMenus().addAll(fileMenu,projectMenu,dataMenu,editMenu,analysisMenu,displayMenu, programmerMenu,helpMenu);
        vBox.getChildren().addAll(menuBar,toolBar);

        return vBox;
    }



}
