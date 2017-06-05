package com.PRIME.main.operations.mainMenus.analysisMenu;

import javafx.application.*;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;



public class toolbar extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }


    @Override public void start(Stage primaryStage)
    {
        //Font.loadFont(Stage.class.getResource("C:\\Users\\hkban\\Desktop\\work\\src\\font-awesome-4.7.0\\font-awesome-4.7.0\\fonts\\fontawesome-webfont.ttf").
          //      toExternalForm(), 12);
        SVGPath svgpath1 = new SVGPath();
        String path1 = "M 10 10 L 30 10 L 20 30 z";
        svgpath1.setContent(path1);
        SVGPath svgpath2 = new SVGPath();
        String path2 = "M 10 10 L 30 10 L 20 30 z";
        svgpath2.setContent(path2);
        SVGPath svgpath3 = new SVGPath();
        String path3 = "M 10 10 L 30 10 L 20 30 z";
        svgpath3.setContent(path3);
        Button btnNew = new Button("New",svgpath1);
        Button btnPause = new Button("Pause",svgpath2);
        Button btnQuit = new Button("Quit",svgpath3);
        Label pictureLabel = AwesomeDude
                .createIconLabel("\uf03e", 32);

        Button refreshButton = AwesomeDude
                .createIconButton("\uf021", "Refresh");

        ToolBar toolBar1 = new ToolBar();
        toolBar1.getItems().addAll(

                new Separator(),
                btnNew,
                btnPause,
                btnQuit,
                new Separator(),pictureLabel,refreshButton,new Separator()
        );


        Button analysisMenu = new Button("_Analysis");
       // Menu analysisMenu=new Menu("_Analysis");

        Tooltip t= new Tooltip();
        t.setPrefSize(600, 50);
        t.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        t.setGraphic(toolBar1);
        Tooltip.install(analysisMenu,t);
        toolBar1.setOrientation(Orientation.HORIZONTAL);

        BorderPane pane = new BorderPane();
        MenuBar menuBar= new MenuBar();
        //menuBar.getMenus().add(analysisMenu);
        pane.setTop(analysisMenu);

        Scene scene = new Scene(pane, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("ToolBar Sample");
        primaryStage.show();
    }
    public static class AwesomeDude
    {

        public static Button createIconButton(String iconName)
        {
            return createIconButton(iconName, "", 16);
        }

        public static Button createIconButton(String iconName, String text)
        {
            return createIconButton(iconName, text, 16);
        }

        public static Button createIconButton(String iconName, int iconSize)
        {
            return createIconButton(iconName, "", iconSize);
        }

        public static Button createIconButton(String iconName, String text, int iconSize)
        {
            Label icon = createIconLabel(iconName);
            icon.setStyle("-fx-font-size: " + iconSize + "px;");
            return ButtonBuilder.create()
                    .text(text)
                    .graphic(icon)
                    .build();
        }

        public static Label createIconLabel(String iconName, String style)
        {
            return LabelBuilder.create()
                    .text(iconName)
                    .style(style)
                    .build();
        }

        public static Label createIconLabel(String iconName)
        {
            return createIconLabel(iconName, 16);
        }

        public static Label createIconLabel(String iconName, int iconSize)
        {
            return LabelBuilder.create()
                    .text(iconName)
                    .styleClass("icons")
                    .style("-fx-font-size: " + iconSize + "px;")
                    .build();
        }
    }
}
