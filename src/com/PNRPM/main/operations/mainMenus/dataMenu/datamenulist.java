package com.PNRPM.main.operations.mainMenus.dataMenu;

import com.PNRPM.main.functions.newW.newWellwindow;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class datamenulist {

    public static Menu datamenulist(Stage ob){
        //Data Menu
        Menu dataMenu = new Menu("_Data");

        MenuItem loadlas = new MenuItem( "Load _LAS");
        loadlas.setOnAction(e -> {
            newWellwindow.newWellwindow();
        });

        MenuItem loaddlis = new MenuItem( "Load _DlIS");
        loaddlis.setOnAction(e -> {
            DirectoryChooser loaddiisdirrctory = new DirectoryChooser();
            File selecteddiis =  loaddiisdirrctory.showDialog(ob);
            if(selecteddiis == null){
                System.out.println("LAS file not selected");
            }else{
                loadlasfile diisobject = new loadlasfile();
            }
        });

        MenuItem loadseismic = new MenuItem( "Load _Seismic");
        loadseismic.setOnAction(e -> {
            DirectoryChooser loadseismicdirrctory = new DirectoryChooser();
            File selectedseismic =  loadseismicdirrctory.showDialog(ob);
            if(selectedseismic == null){
                System.out.println("Seismic file not selected");
            }else{
                loadlasfile seismicobject = new loadlasfile();
            }
        });

        MenuItem loadascii = new MenuItem( "Load ASC_II");
        loadascii.setOnAction(e -> {
            DirectoryChooser loadasciidirrctory = new DirectoryChooser();
            File selectedascii =  loadasciidirrctory.showDialog(ob);
            if(selectedascii == null){
                System.out.println("ASCII file not selected");
            }else{
                loadlasfile asciiobject = new loadlasfile();
            }
        });

        MenuItem loaddirectionalsurvey = new MenuItem( "Load Di_rectional Survey");
        loaddirectionalsurvey.setOnAction(e -> {
            DirectoryChooser loaddirectionaldirrctory = new DirectoryChooser();
            File selecteddirectional =  loaddirectionaldirrctory.showDialog(ob);
            if(selecteddirectional == null){
                System.out.println("Directional file not selected");
            }else{
                loadlasfile directionalobject = new loadlasfile();
            }
        });

        MenuItem loadcheckshot = new MenuItem( "Load _CheckShot");
        loadcheckshot.setOnAction(e -> {
            DirectoryChooser loadcheckshotdirrctory = new DirectoryChooser();
            File selectedcheckshot =  loadcheckshotdirrctory.showDialog(ob);
            if(selectedcheckshot == null){
                System.out.println("CheckShot file not selected");
            }else{
                loadlasfile checkshotobject = new loadlasfile();
            }
        });

        MenuItem loadimage = new MenuItem( "Load Image _File");
        loadimage.setOnAction(e -> {
            DirectoryChooser loadimagedirrctory = new DirectoryChooser();
            File selectedimage =  loadimagedirrctory.showDialog(ob);
            if(selectedimage == null){
                System.out.println("Image file not selected");
            }else{
                loadlasfile imageobject = new loadlasfile();
            }
        });

        MenuItem loadarray = new MenuItem( "Load _Array");
        loadarray.setOnAction(e -> {
            DirectoryChooser loadarraydirrctory = new DirectoryChooser();
            File selectedarray =  loadarraydirrctory.showDialog(ob);
            if(selectedarray == null){
                System.out.println("Array file not selected");
            }else{
                loadlasfile arrayobject = new loadlasfile();
            }
        });

        dataMenu.getItems().addAll(loadlas,loaddlis,loadseismic,loadascii,loaddirectionalsurvey,loadcheckshot,loadimage,loadarray);

        //Data Sub Menu ends--------------------------------------------------------------------------------------------

        return dataMenu;
    }
}
