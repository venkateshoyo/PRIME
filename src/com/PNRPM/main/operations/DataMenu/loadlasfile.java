package com.PNRPM.main.operations.DataMenu;


import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class loadlasfile {

    public String text;
    public void loadlas(File selectedlas)throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new FileReader(selectedlas));
        try {
            bufferedReader = new BufferedReader(new FileReader(selectedlas));
            String text;
            while ((text = bufferedReader.readLine()) != null) {
                System.out.println(text);
            }

        } catch (FileNotFoundException ex) {
//            Logger.getLogger(JavaFX_OpenFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
//            Logger.getLogger(JavaFX_OpenFile.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }
        String text;
        while ((text = bufferedReader.readLine()) != null) {
            System.out.println(text);
        }
    }

}
