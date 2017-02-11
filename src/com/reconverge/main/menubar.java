package com.reconverge.main;

import javafx.scene.control.*;

class menubar {

    public static MenuBar displayMenuBar(){

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
        loadlas.setOnAction(e -> System.out.println(""));

        MenuItem loaddlis = new MenuItem( "Load _DlIS");
        loaddlis.setOnAction(e -> System.out.println(""));

        MenuItem loadseismic = new MenuItem( "Load _Seismic");
        loadseismic.setOnAction(e -> System.out.println(""));

        MenuItem loadascii = new MenuItem( "Load ASC_II");
        loadascii.setOnAction(e -> System.out.println(""));

        MenuItem loaddirectionalsurvey = new MenuItem( "Load Di_rectional Survey");
        loaddirectionalsurvey.setOnAction(e -> System.out.println(""));

        MenuItem loadcheckshot = new MenuItem( "Load _CheckShot");
        loadcheckshot.setOnAction(e -> System.out.println(""));

        MenuItem loadimage = new MenuItem( "Load Image _File");
        loadimage.setOnAction(e -> System.out.println(""));

        MenuItem loadarray = new MenuItem( "Load _Array");
        loadarray.setOnAction(e -> System.out.println(""));

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
        crossplots.setOnAction(e -> System.out.println(""));

        analysisMenu.getItems().addAll(singlewellview,multiwellview,seimicviewer,crossplots);

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

        MenuItem whatsnew = new MenuItem("_Whats New in Reconverge");
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

        MenuItem about = new MenuItem("_About");
        about.setOnAction(e -> System.out.println());

        helpMenu.getItems().addAll(findAction,help,gettingstarted, keymap,helpseparater_1,demos,tip,whatsnew,helpseparater_2,supportcenter,feedback,showlogs,helpseparater_3,updates,about);

        //Help Sub Menu ends--------------------------------------------------------------------------------------------


        //Menu Bar
        MenuBar menuBar= new MenuBar();
        menuBar.getStyleClass().add("topmenu");
        menuBar.getMenus().addAll(fileMenu,editMenu,projectMenu,dataMenu,analysisMenu,programmerMenu,helpMenu);

        return menuBar;
    }
}
