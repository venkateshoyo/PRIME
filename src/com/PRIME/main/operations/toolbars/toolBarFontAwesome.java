package com.PRIME.main.operations.toolbars;

import com.PRIME.main.operations.toolbars.hackTooltipStartTiming;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;

public class toolBarFontAwesome {

    public static ToolBar method(){
        BorderPane borderPane = new BorderPane();
        ToolBar toolBar = new ToolBar();

        Button Menu = GlyphsDude.createIconButton(FontAwesomeIcon.LOCATION_ARROW,"Menu");
        Tooltip text = new Tooltip("Menu");
        new hackTooltipStartTiming(text);
        Menu.setTooltip(text);

        Button Menu2 = GlyphsDude.createIconButton(FontAwesomeIcon.ARROW_DOWN,"DOWN");
        Tooltip text1 = new Tooltip("DOWN");
        Menu2.setTooltip(text1);
        new hackTooltipStartTiming(text1);

        Button Menu3 = GlyphsDude.createIconButton(FontAwesomeIcon.ARROW_UP,"UP");
        Tooltip text2 = new Tooltip("UP");
        new hackTooltipStartTiming(text2);
        Menu3.setTooltip(text2);

        toolBar.getItems().addAll(Menu,Menu2,Menu3);

        borderPane.setTop( toolBar );
        return toolBar;
    }
}