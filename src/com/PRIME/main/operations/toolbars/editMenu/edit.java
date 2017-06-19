package com.PRIME.main.operations.toolbars.editMenu;

import com.PRIME.main.operations.toolbars.toolbarButton;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;

import static com.PRIME.main.operations.toolbars.editMenu.constants.*;

public class edit {
    public static ToolBar editToolbar() {

        ToolBar toolbar = new ToolBar();

        Button openFileBtn = toolbarButton.toolbarButton(openFileButtonText, openFileImageURL, openFileTooltipText);

        Button printBtn = toolbarButton.toolbarButton(printButtonText, printImageURL, printTooltipText);

        Button snapshotBtn = toolbarButton.toolbarButton(snapshotButtonText, snapshotImageURL, snapshotTooltipText);

        toolbar.getItems().addAll(openFileBtn, printBtn, snapshotBtn);
        return toolbar;
    }
}
