package com.PRIME.main.operations.menubars.fileMenu;

import com.PRIME.main.operations.menubars.fileMenu.settings.globalVariable.main;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

public class filemenulist {

    public static Menu filemenulist(){
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

        //___________________________________________________________________________

        SeparatorMenuItem fileseparater_1= new SeparatorMenuItem();

        MenuItem save = new MenuItem(    "_Save                   Ctrl+S");
        save.setOnAction(e -> System.out.println());

        MenuItem saveAs = new MenuItem("Save _As");
        saveAs.setOnAction(e -> System.out.println());

        //___________________________________________________________________________

        SeparatorMenuItem fileseparater_2= new SeparatorMenuItem();

        Menu print = new Menu("Print");

        MenuItem printscreen = new MenuItem( "_Screen");
        printscreen.setOnAction(e -> System.out.println(""));

        MenuItem printwindow = new MenuItem( "_Window");
        printwindow.setOnAction(e -> System.out.println(""));

        MenuItem printplotter = new MenuItem( "Pl_otter");
        printplotter.setOnAction(e -> System.out.println(""));

        print.getItems().addAll(printscreen,printwindow,printplotter);

        //___________________________________________________________________________

        SeparatorMenuItem fileseparater_3= new SeparatorMenuItem();

        MenuItem importFile = new MenuItem("_Import");
        importFile.setOnAction(e -> System.out.println());

        MenuItem exportFile = new MenuItem("_Export");
        exportFile.setOnAction(e -> System.out.println());
        exportFile.setDisable(true);

        Menu settings = new Menu("Se_ttings");

        MenuItem globalVariable = new MenuItem( "Global Variable Settings");
        globalVariable.setOnAction(e -> main.UI());

        settings.getItems().addAll(globalVariable);

        SeparatorMenuItem fileseparater_4= new SeparatorMenuItem();

        //___________________________________________________________________________

        MenuItem closeFile = new MenuItem("_Close File");
        closeFile.setOnAction(e -> System.out.println());

        MenuItem exit = new MenuItem(   "Exit                   Ctrl+W");
        exit.setOnAction(e -> System.out.println());

        fileMenu.getItems().addAll(newFile,openFile,openFolder,openRecent,fileseparater_1,save,saveAs,fileseparater_2,print,fileseparater_3,importFile,exportFile,settings,fileseparater_4,closeFile,exit);

        //File Sub Menu ends--------------------------------------------------------------------------------------------
        return fileMenu;
    }
}
