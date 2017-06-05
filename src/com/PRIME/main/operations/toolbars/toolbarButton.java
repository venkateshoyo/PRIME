package com.PRIME.main.operations.toolbars;

import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;

public class toolbarButton {

    public static Button toolbarButton(String buttonText, String imageURL, String tooltipText){

        Button toolButton;
        if (buttonText.isEmpty())
            toolButton = new Button();
        else toolButton = new Button(buttonText);

        if (!imageURL.isEmpty())
            toolButton.setGraphic(new ImageView(imageURL));

        Tooltip tooltip;
        if (!tooltipText.isEmpty()){
            tooltip = new Tooltip(tooltipText);
            new hackTooltipStartTiming(tooltip);
            toolButton.setTooltip(tooltip);
        }
        return toolButton;
    }
}
