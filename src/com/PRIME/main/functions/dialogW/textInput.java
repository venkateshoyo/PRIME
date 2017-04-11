package com.PRIME.main.functions.dialogW;

import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class textInput {

    public static String textInput(String title,String msg,String defaultvalue){

        TextInputDialog dlg = new TextInputDialog(defaultvalue);
        dlg.setTitle(title);
        dlg.setHeaderText(msg);

        dlg.getEditor();

        Optional<String> result = dlg.showAndWait();
        if (result.isPresent()) {
            return result.get();
        }
        else
            return null;
    }
}