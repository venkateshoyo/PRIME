package com.PRIME.main.operations.mainMenus.displayMenu.surface;

import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import org.renjin.script.RenjinScriptEngine;

/**
 * Created by hkban on 7/6/2017.
 */
public class mainj  {

    public void mainmethod() throws Exception {

        Stage primaryStage = new Stage();
        final SwingNode swingNode = new SwingNode();
        BorderPane layout = new BorderPane();

        layout.setCenter(swingNode);
//        layout.setTop(vb);
        try {
            layout = new ColorWaveDemo().method();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Scene scene = new Scene(layout,1500,1500);
        primaryStage.setScene(scene);
        primaryStage.show();


        primaryStage.setResizable(false);
    }


}
