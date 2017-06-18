package com.PRIME.main.operations.toolbars.fileMenu;

import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;

import com.PRIME.main.operations.toolbars.toolbarButton;

import static com.PRIME.main.operations.toolbars.fileMenu.constants.*;

public class file {

    public static ToolBar fileToolbar() {

        ToolBar toolbar = new ToolBar();

        Button openFileBtn = toolbarButton.toolbarButton(openFileButtonText, openFileImageURL, openFileTooltipText);

        Button printBtn = toolbarButton.toolbarButton(printButtonText, printImageURL, printTooltipText);

        Button snapshotBtn = toolbarButton.toolbarButton(snapshotButtonText, snapshotImageURL, snapshotTooltipText);

        toolbar.getItems().addAll(openFileBtn, printBtn, snapshotBtn);
        return toolbar;
    }
}