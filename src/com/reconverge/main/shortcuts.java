package com.reconverge.main;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;

public class shortcuts {

    public static void shortcuts(){

        main.getscene().getAccelerators().put(
                new KeyCodeCombination(KeyCode.N, KeyCombination.SHORTCUT_DOWN),
                () -> {
                    System.out.println("Key pressed: New file");
                }
        );
        main.getscene().getAccelerators().put(
                new KeyCodeCombination(KeyCode.O, KeyCombination.SHORTCUT_DOWN),
                () -> {
                    System.out.println("Key pressed: Open file");
                }
        );
        main.getscene().getAccelerators().put(
                new KeyCodeCombination(KeyCode.W, KeyCombination.SHORTCUT_DOWN),
                () -> {
                    System.out.println("Key pressed: Exit");
                }
        );
    }
}
