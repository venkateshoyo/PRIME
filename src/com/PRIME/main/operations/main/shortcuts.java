package com.PRIME.main.operations.main;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;

public class shortcuts {

    public static void shortcuts(){

        home.getscene().getAccelerators().put(
                new KeyCodeCombination(KeyCode.N, KeyCombination.SHORTCUT_DOWN),
                () -> {
                    System.out.println("Key pressed: New file");
                }
        );
        home.getscene().getAccelerators().put(
                new KeyCodeCombination(KeyCode.O, KeyCombination.SHORTCUT_DOWN),
                () -> {
                    System.out.println("Key pressed: Open file");
                }
        );
        home.getscene().getAccelerators().put(
                new KeyCodeCombination(KeyCode.W, KeyCombination.SHORTCUT_DOWN),
                () -> {
                    System.out.println("Key pressed: Exit");
                }
        );
        home.getscene().getAccelerators().put(
                new KeyCodeCombination(KeyCode.S, KeyCombination.SHORTCUT_DOWN),
                () -> {
                    System.out.println("Key pressed: Save");
                }
        );
    }
}
