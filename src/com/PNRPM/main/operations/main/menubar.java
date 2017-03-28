package com.PNRPM.main.operations.main;

//import com.PNRPM.main.operations.fileMenu.*;
//import com.PNRPM.main.operations.editMenu.*;
//import com.PNRPM.main.operations.projectMenu.*;
import com.PNRPM.main.operations.dataMenu.*;
//import com.PNRPM.main.operations.analysisMenu.*;
import com.PNRPM.main.operations.displayMenu.*;
//import com.PNRPM.main.operations.programmerMenu.*;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import java.io.File;
import java.io.IOException;

class menubar {

    public MenuBar displayMenuBar(){

        main ob = new main();
        //File Menu
        Menu fileMenu = new Menu("_File");

        //File Sub Menu starts------------------------------------------------------------------------------------------
        MenuItem newFile = new MenuItem( "_New File             Ctrl+N");
        newFile.setOnAction(e -> System.out.println(""));

        MenuItem openFile = new MenuItem("_Open File...         Ctrl+O");
        openFile.setOnAction(e -> System.out.println());

        MenuItem openFolder = new MenuItem("Open _Folder...");
        openFolder.setOnAction(e -> System.out.println());

        MenuItem openRecent = new MenuItem("Open _Recent File");
        openRecent.setOnAction(e -> System.out.println());

        SeparatorMenuItem fileseparater_1= new SeparatorMenuItem();

        MenuItem save = new MenuItem(    "_Save                   Ctrl+S");
        save.setOnAction(e -> System.out.println());

        MenuItem saveAs = new MenuItem("Save _As");
        saveAs.setOnAction(e -> System.out.println());

        SeparatorMenuItem fileseparater_2= new SeparatorMenuItem();

        Menu print = new Menu("Print");

        MenuItem printscreen = new MenuItem( "_Screen");
        printscreen.setOnAction(e -> System.out.println(""));

        MenuItem printwindow = new MenuItem( "_Window");
        printwindow.setOnAction(e -> System.out.println(""));

        MenuItem printplotter = new MenuItem( "Pl_otter");
        printplotter.setOnAction(e -> System.out.println(""));

        print.getItems().addAll(printscreen,printwindow,printplotter);

        SeparatorMenuItem fileseparater_3= new SeparatorMenuItem();

        MenuItem importFile = new MenuItem("_Import");
        importFile.setOnAction(e -> System.out.println());

        MenuItem exportFile = new MenuItem("_Export");
        exportFile.setOnAction(e -> System.out.println());
        exportFile.setDisable(true);

        MenuItem settings = new MenuItem("Se_ttings");
        settings.setOnAction(e -> System.out.println());

        SeparatorMenuItem fileseparater_4= new SeparatorMenuItem();

        MenuItem closeFile = new MenuItem("_Close File");
        closeFile.setOnAction(e -> System.out.println());

        MenuItem exit = new MenuItem(   "Exit                   Ctrl+W");
        exit.setOnAction(e -> System.out.println());

        fileMenu.getItems().addAll(newFile,openFile,openFolder,openRecent,fileseparater_1,save,saveAs,fileseparater_2,print,fileseparater_3,importFile,exportFile,settings,fileseparater_4,closeFile,exit);

        //File Sub Menu ends--------------------------------------------------------------------------------------------


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


        //Project Menu
        Menu projectMenu = new Menu("_Project");

        //Project Sub Menu starts---------------------------------------------------------------------------------------
        MenuItem openproject = new MenuItem( "_Open Project...");
        openproject.setOnAction(e -> System.out.println(""));

        MenuItem newproject = new MenuItem( "_New Project");
        newproject.setOnAction(e -> System.out.println(""));

        MenuItem recentproject = new MenuItem( "_Recent Project");
        recentproject.setOnAction(e -> System.out.println(""));

        MenuItem lastsaveproject = new MenuItem( "_Last Saved");
        lastsaveproject.setOnAction(e -> System.out.println(""));

        Menu autosave = new Menu("Auto Save Duration");

        RadioMenuItem none = new RadioMenuItem("None");
        none.setOnAction(e -> System.out.println(""));

        RadioMenuItem halfhourautosave = new RadioMenuItem("30 minutes");
        halfhourautosave.setOnAction(e -> System.out.println(""));

        RadioMenuItem onehourautosave = new RadioMenuItem("1 hour");
        onehourautosave.setOnAction(e -> System.out.println(""));

        ToggleGroup toggle = new ToggleGroup();

        none.setToggleGroup(toggle);
        halfhourautosave.setToggleGroup(toggle);
        onehourautosave.setToggleGroup(toggle);
        none.setSelected(true);

        TextField manual = new TextField();
        manual.setPromptText("Duration in minutes");
        CustomMenuItem custom = new CustomMenuItem(manual);
        custom.setHideOnClick(false);

        autosave.getItems().addAll(none,onehourautosave,halfhourautosave,custom);

        projectMenu.getItems().addAll(openproject,newproject,recentproject,lastsaveproject,autosave);

        //Project Sub Menu ends-----------------------------------------------------------------------------------------


        //Data Menu
        Menu dataMenu = new Menu("_Data");

        //Data Sub Menu starts------------------------------------------------------------------------------------------

        MenuItem loadlas = new MenuItem( "Load _LAS");
        loadlas.setOnAction(e -> {
            FileChooser loadlasdirrctory = new FileChooser();
            loadlasdirrctory.getExtensionFilters().add(new ExtensionFilter("LAS Files", "*.las"));
            loadlasdirrctory.setTitle("Load LAS file");
            File selectedlas =  loadlasdirrctory.showOpenDialog(ob.getstage());
            if(selectedlas == null){
                System.out.println("LAS file not selected");
            }else{
                loadlasfile lasobject = new loadlasfile();
                try {
                    lasobject.loadlas(selectedlas);
                }
                catch (IOException el){
                    System.out.println("Error in loading");
                }
            }
        });

        MenuItem loaddlis = new MenuItem( "Load _DlIS");
        loaddlis.setOnAction(e -> {
            DirectoryChooser loaddiisdirrctory = new DirectoryChooser();
            File selecteddiis =  loaddiisdirrctory.showDialog(ob.getstage());
            if(selecteddiis == null){
                System.out.println("LAS file not selected");
            }else{
                loadlasfile diisobject = new loadlasfile();
            }
        });

        MenuItem loadseismic = new MenuItem( "Load _Seismic");
        loadseismic.setOnAction(e -> {
            DirectoryChooser loadseismicdirrctory = new DirectoryChooser();
            File selectedseismic =  loadseismicdirrctory.showDialog(ob.getstage());
            if(selectedseismic == null){
                System.out.println("Seismic file not selected");
            }else{
                loadlasfile seismicobject = new loadlasfile();
            }
        });

        MenuItem loadascii = new MenuItem( "Load ASC_II");
        loadascii.setOnAction(e -> {
            DirectoryChooser loadasciidirrctory = new DirectoryChooser();
            File selectedascii =  loadasciidirrctory.showDialog(ob.getstage());
            if(selectedascii == null){
                System.out.println("ASCII file not selected");
            }else{
                loadlasfile asciiobject = new loadlasfile();
            }
        });

        MenuItem loaddirectionalsurvey = new MenuItem( "Load Di_rectional Survey");
        loaddirectionalsurvey.setOnAction(e -> {
            DirectoryChooser loaddirectionaldirrctory = new DirectoryChooser();
            File selecteddirectional =  loaddirectionaldirrctory.showDialog(ob.getstage());
            if(selecteddirectional == null){
                System.out.println("Directional file not selected");
            }else{
                loadlasfile directionalobject = new loadlasfile();
            }
        });

        MenuItem loadcheckshot = new MenuItem( "Load _CheckShot");
        loadcheckshot.setOnAction(e -> {
            DirectoryChooser loadcheckshotdirrctory = new DirectoryChooser();
            File selectedcheckshot =  loadcheckshotdirrctory.showDialog(ob.getstage());
            if(selectedcheckshot == null){
                System.out.println("CheckShot file not selected");
            }else{
                loadlasfile checkshotobject = new loadlasfile();
            }
        });

        MenuItem loadimage = new MenuItem( "Load Image _File");
        loadimage.setOnAction(e -> {
            DirectoryChooser loadimagedirrctory = new DirectoryChooser();
            File selectedimage =  loadimagedirrctory.showDialog(ob.getstage());
            if(selectedimage == null){
                System.out.println("Image file not selected");
            }else{
                loadlasfile imageobject = new loadlasfile();
            }
        });

        MenuItem loadarray = new MenuItem( "Load _Array");
        loadarray.setOnAction(e -> {
            DirectoryChooser loadarraydirrctory = new DirectoryChooser();
            File selectedarray =  loadarraydirrctory.showDialog(ob.getstage());
            if(selectedarray == null){
                System.out.println("Array file not selected");
            }else{
                loadlasfile arrayobject = new loadlasfile();
            }
        });

        dataMenu.getItems().addAll(loadlas,loaddlis,loadseismic,loadascii,loaddirectionalsurvey,loadcheckshot,loadimage,loadarray);

        //Data Sub Menu ends--------------------------------------------------------------------------------------------


        //Analysis Menu
        Menu analysisMenu = new Menu("_Analysis");
        //Analysis Sub Menu starts--------------------------------------------------------------------------------------

        MenuItem singlewellview = new MenuItem( "Single _Well Viewer");
        singlewellview.setOnAction(e -> System.out.println(""));

        MenuItem multiwellview = new MenuItem( "_Multi Well Viewer");
        multiwellview.setOnAction(e -> System.out.println(""));

        MenuItem seimicviewer = new MenuItem( "_Seismic Viewer");
        seimicviewer.setOnAction(e -> System.out.println(""));

        MenuItem crossplots = new MenuItem( "_Cross Plots");
        crossplots.setOnAction(e -> {
            FileChooser loadlasdirrctory = new FileChooser();
            loadlasdirrctory.getExtensionFilters().add(new ExtensionFilter("LAS Files", "*.las"));
            loadlasdirrctory.setTitle("Load LAS file for crossplot ");
            File selectedlas =  loadlasdirrctory.showOpenDialog(ob.getstage());
            if(selectedlas == null){
                System.out.println("LAS file not selected");
            }else{
                loadlasfile lasobject = new loadlasfile();
                try {
                    lasobject.loadlas(selectedlas);
                }
                catch (IOException el){
                    System.out.println("Error in loading");
                }
            }
        });

        analysisMenu.getItems().addAll(singlewellview,multiwellview,seimicviewer,crossplots);

        //Analysis Sub Menu ends--------------------------------------------------------------------------------------------



        //Display Menu
        Menu displayMenu = new Menu("_Display");
        //Display Sub Menu starts--------------------------------------------------------------------------------------

        MenuItem display_location = new MenuItem( "_Location");
        display_location.setOnAction(e -> System.out.println(""));

        MenuItem display_logs = new MenuItem( "L_ogs");
        display_logs.setOnAction(e -> System.out.println(""));

        MenuItem display_seimic = new MenuItem( "_Seismic");
        display_seimic.setOnAction(e -> System.out.println(""));

        MenuItem display_ASCII = new MenuItem( "_ASCII");
        display_ASCII.setOnAction(e -> System.out.println(""));

        displayMenu.getItems().addAll(display_location,display_logs,display_seimic,display_ASCII);

        //Analysis Sub Menu ends--------------------------------------------------------------------------------------------



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


        //Help Menu
        Menu helpMenu = new Menu("_Help");

        //Help Sub Menu starts------------------------------------------------------------------------------------------
        MenuItem findAction = new MenuItem("Fi_nd Action");
        newFile.setOnAction(e -> System.out.println(""));

        MenuItem help = new MenuItem("_Help");
        help.setOnAction(e -> System.out.println());

        MenuItem gettingstarted = new MenuItem("_Getting Started");
        gettingstarted.setOnAction(e -> System.out.println());

        MenuItem keymap = new MenuItem("_Keymap Reference");
        keymap.setOnAction(e -> System.out.println());

        SeparatorMenuItem helpseparater_1= new SeparatorMenuItem();

        MenuItem demos = new MenuItem("_Demos and Screencasts ");
        demos.setOnAction(e -> System.out.println());

        MenuItem tip = new MenuItem("_Tips of the Day");
        tip.setOnAction(e -> System.out.println());

        MenuItem whatsnew = new MenuItem("_Whats New in PNRPM");
        whatsnew.setOnAction(e -> System.out.println());

        SeparatorMenuItem helpseparater_2= new SeparatorMenuItem();

        MenuItem supportcenter = new MenuItem("_Support Center");
        supportcenter.setOnAction(e -> System.out.println());

        MenuItem feedback = new MenuItem("Submit _Feedback");
        feedback.setOnAction(e -> System.out.println());
        feedback.setDisable(true);

        MenuItem showlogs = new MenuItem("Show log in _Explorer");
        showlogs.setOnAction(e -> System.out.println());

        SeparatorMenuItem helpseparater_3= new SeparatorMenuItem();

        MenuItem updates = new MenuItem("Check for _Updates");
        updates.setOnAction(e -> System.out.println());

        MenuItem literature = new MenuItem("Related literatures");
        literature.setOnAction(e -> System.out.println());

        MenuItem about = new MenuItem("_About");
        about.setOnAction(e -> System.out.println());

        helpMenu.getItems().addAll(findAction,help,gettingstarted, keymap,helpseparater_1,demos,tip,whatsnew,helpseparater_2,supportcenter,feedback,showlogs,helpseparater_3,updates,literature, about);

        //Help Sub Menu ends--------------------------------------------------------------------------------------------


        //Menu Bar
        MenuBar menuBar= new MenuBar();
        menuBar.getStyleClass().add("topmenu");
        menuBar.getMenus().addAll(fileMenu,projectMenu,dataMenu,editMenu,analysisMenu,displayMenu, programmerMenu,helpMenu);

        return menuBar;
    }
}
