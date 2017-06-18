package com.PRIME.main.operations.toolbars.helpMenu;

import com.PRIME.main.operations.toolbars.toolbarButton;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;

import static com.PRIME.main.operations.toolbars.fileMenu.constants.*;
import static com.PRIME.main.operations.toolbars.fileMenu.constants.snapshotImageURL;
import static com.PRIME.main.operations.toolbars.fileMenu.constants.snapshotTooltipText;

public class help {
    public static ToolBar helpToolbar() {

        ToolBar toolbar = new ToolBar();

        Button openFileBtn = toolbarButton.toolbarButton(openFileButtonText, openFileImageURL, openFileTooltipText);

        Button printBtn = toolbarButton.toolbarButton(printButtonText, printImageURL, printTooltipText);

        Button snapshotBtn = toolbarButton.toolbarButton(snapshotButtonText, snapshotImageURL, snapshotTooltipText);

        toolbar.getItems().addAll(openFileBtn, printBtn, snapshotBtn);
        return toolbar;
    }
}
