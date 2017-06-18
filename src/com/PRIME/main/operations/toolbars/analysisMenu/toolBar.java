package com.PRIME.main.operations.toolbars.analysisMenu;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import static javafx.application.Application.launch;


public class toolBar extends Application {

    public void start( Stage stage ) throws Exception
    {


        BorderPane borderPane = new BorderPane();
        ToolBar toolBar = new ToolBar();

        Button Menu = GlyphsDude.createIconButton(FontAwesomeIcon.LOCATION_ARROW,"Menu");
        Button Menu2 = GlyphsDude.createIconButton(FontAwesomeIcon.ARROW_DOWN,"DOWN");
        Button Menu3 = GlyphsDude.createIconButton(FontAwesomeIcon.ARROW_UP,"UP");
        toolBar.getItems().addAll(Menu,Menu2,Menu3);


        borderPane.setTop( toolBar );
        Scene scene = new Scene( borderPane );


        stage.setScene( scene );
        stage.setWidth( 300 );
        stage.setHeight( 400 );
        stage.setTitle( "ToolBar" );
        stage.show();
    }
    public static void main(String args[])
    {
        launch(args);
    }

}