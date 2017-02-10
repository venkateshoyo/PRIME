package com.reconverge.main;

import javafx.scene.control.*;

class menubar {

    public static MenuBar displayMenuBar(){

        //File Menu
        Menu fileMenu = new Menu("_File");

        //File Sub Menu
        MenuItem newFile = new MenuItem("_New File");
        newFile.setOnAction(e -> System.out.println(""));

        MenuItem openFile = new MenuItem("_Open File...");
        openFile.setOnAction(e -> System.out.println());

        MenuItem openFolder = new MenuItem("Open _Folder...");
        openFolder.setOnAction(e -> System.out.println());

        MenuItem openRecent = new MenuItem("Open _Recent File");
        openRecent.setOnAction(e -> System.out.println());

        SeparatorMenuItem fileseparater_1= new SeparatorMenuItem();

        MenuItem save = new MenuItem("_Save ");
        save.setOnAction(e -> System.out.println());

        MenuItem saveAs = new MenuItem("Save _As");
        saveAs.setOnAction(e -> System.out.println());

        SeparatorMenuItem fileseparater_2= new SeparatorMenuItem();

        MenuItem importFile = new MenuItem("_Import");
        importFile.setOnAction(e -> System.out.println());

        MenuItem exportFile = new MenuItem("_Export");
        exportFile.setOnAction(e -> System.out.println());
        exportFile.setDisable(true);

        MenuItem settings = new MenuItem("Se_ttings");
        settings.setOnAction(e -> System.out.println());

        SeparatorMenuItem fileseparater_3= new SeparatorMenuItem();

        MenuItem closeFile = new MenuItem("_Close File");
        closeFile.setOnAction(e -> System.out.println());

        fileMenu.getItems().addAll(newFile,openFile,openFolder,openRecent,fileseparater_1,save,saveAs,fileseparater_2,importFile,exportFile,settings,fileseparater_3,closeFile);


        //edit Menu
        Menu editMenu = new Menu("_Edit");
        CheckMenuItem showlinenumbers = new CheckMenuItem("Show Line numbers");
        showlinenumbers.setOnAction(e -> {
            if(showlinenumbers.isSelected())
                System.out.println("Execute when item checked");
            else
                System.out.println("Do when not checked");
        });
        showlinenumbers.setSelected(true);

        editMenu.getItems().addAll(showlinenumbers);


        //Project Menu
        Menu projectMenu = new Menu("_Project");


        //Data Menu
        Menu dataMenu = new Menu("_Data");


        //edit Menu
        Menu plotsMenu = new Menu("_Plots");

        //Programmer Menu
        Menu programmerMenu = new Menu("P_rogrammer");


        //Help Menu
        Menu helpMenu = new Menu("_Help");

        //Help Sub Menu
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




        //Menu Bar
        MenuBar menuBar= new MenuBar();
        menuBar.getStyleClass().add("topmenu");
        menuBar.getMenus().addAll(fileMenu,editMenu,projectMenu,dataMenu,plotsMenu,programmerMenu,helpMenu);

        return menuBar;
    }
}
