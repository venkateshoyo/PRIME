package com.PRIME.main.operations.mainMenus.projectMenu;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class loadfile {


    public static Pair<Pair<Double, Double>, String> coordinates() throws IOException {
        double lati = 55555;
        double longi = 55555;
        String Text = "";
        FileChooser loadlasdirrctory = new FileChooser();
        loadlasdirrctory.getExtensionFilters().add(new FileChooser.ExtensionFilter("LAS Files", "*.las"));
        loadlasdirrctory.setTitle("Load LAS file for crossplot ");
        Stage ob = new Stage();
        File selectedlas = loadlasdirrctory.showOpenDialog(ob);
        if(selectedlas == null){
            System.out.println("LAS file not selected");
        }else {
//
            BufferedReader bufferedReader;

            try {
                //BufferedReader fot LAS/ ASCII file input
                bufferedReader = new BufferedReader(new FileReader(selectedlas));

                String text;
                Pattern regex = Pattern.compile("(-?(\\d+)+(\\.)?(\\d+)*)"); //regex used as delimiter

                //Reading each line and storing each parameter value to temporary matrix
                while ((text = bufferedReader.readLine()) != null && text.length() > 0) {

                    //Checking for empty lines

                    if (text.length() > 4 && (text.substring(0, 4).equalsIgnoreCase("LATI"))) {

                        //Checking for Start Depth Data and working accordingly
                        Matcher matcher = regex.matcher(text);
                        while (matcher.find()) {
                            lati = Double.parseDouble(matcher.group(1));
                            // System.out.println(lati);
                        }
                    } else if (text.length() > 4 && (text.substring(0, 4).equalsIgnoreCase("LONG"))) {

                        //Checking for Start Depth Data and working accordingly
                        Matcher matcher = regex.matcher(text);
                        while (matcher.find()) {
                            longi = Double.parseDouble(matcher.group(1));
                            //  System.out.println(lati);
                        }
                    } else if (text.length() > 4 && (text.substring(0, 4).equalsIgnoreCase("WELL"))) {

                        //Checking for Start Depth Data and working accordingly
                        text.replaceAll(" ","");
                       // Matcher matcher = regex.matcher();
                        Text = text.substring(5,text.length());
                    }

                }
                if (lati == 55555 || longi == 55555) {
                    System.out.println("LATITUDE AND LONGITUDE ARE MISSING");
                    Text = "empty";
                }


            } catch (FileNotFoundException ex) {
            } catch (IOException ex) {
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
            }
        }
        return new Pair<Pair<Double, Double>, String>(new Pair<Double, Double>(lati, longi), Text);

    }
    }




