package com.reconverge.main;

import com.reconverge.main.*;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

public class shortcuts {

    public static void shortcuts(){

        main.getscene().getAccelerators().put(
                new KeyCodeCombination(KeyCode.N, KeyCombination.SHORTCUT_DOWN),
                () -> {
                    System.out.println("Key pressed: New file");
                }
        );
    }
}
