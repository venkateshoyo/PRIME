package com.PRIME.main.functions.newW.wellWindow.contents.set;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class updateHeader {

    public static Well updateHeader(File selectedlas){

        Well data = new Well(null,null,null,null, true);
        String well=null;
        String set=null;
        String reference=null;
        String units=null;
        boolean select=true;

//        BufferedReader bufferedReader;
//        try {
//
//            //BufferedReader fot LAS/ ASCII file input
//            bufferedReader = new BufferedReader(new FileReader(selectedlas));
//
//            String text;
//            Pattern regex = Pattern.compile("(\\d+(?:\\.\\d+)?)"); //regex used as delimiter
//            boolean Isdata = false, Isparameter = false;
//            int index = 0, parameterindex = -1;
//
//            //Reading each line and storing each parameter value to temporary matrix
//            while ((text = bufferedReader.readLine()) != null && text.length() > 0) {
//
//                if (text.length() > 4 && (text.substring(0, 4).equalsIgnoreCase("WELL"))) {
//
//                    //Checking for Start Depth Data and working accordingly
//                    Matcher matcher = regex.matcher(text);
//                    while (matcher.find()) {
//                        datas -= Double.parseDouble(matcher.group(1));
//                    }
//                } else if (text.length() > 4 && (text.substring(0, 4).equalsIgnoreCase("STOP"))) {
//
//                    //Checking for End Depth Data and working accordingly
//                    Matcher matcher = regex.matcher(text);
//                    while (matcher.find()) {
//                        datas += Double.parseDouble(matcher.group(1));
//                    }
//                } else if (text.length() > 4 && (text.substring(0, 4).equalsIgnoreCase("STEP"))) {
//
//                    //Checking for Step Increment Data and working accordingly
//                    Matcher matcher = regex.matcher(text);
//                    while (matcher.find()) {
//                        increment = Double.parseDouble(matcher.group(1));
//                        datas /= increment;
//                    }
//                } else if (text.length() > 5 && (text.substring(1, 6).equalsIgnoreCase("depth"))) {
//
//                    //Checking for Start Depth Data and working accordingly
//                    Isparameter = true;
//                    parameter[++noOfParameter] = "Depth in meter";
//                } else if (Isparameter && !(text.substring(0, 4).equalsIgnoreCase("~Asc"))) {
//
//                    //"~Asc" Statement confirms start of Data
//                    parameter[++noOfParameter] = text.substring(1, text.indexOf(" ", 1));
//                } else if (text.length() > 4 && (text.substring(0, 4).equalsIgnoreCase("~Asc"))) {
//
//                    //Once Data is confirmed initialize values matrix for reading las/ASCII file values
//                    Isdata = true;
//                    values = new double[(int) datas + 1][noOfParameter + 1];
//                    range= new double[2][noOfParameter + 1];
//                }
//            }
//            range[0][0] = values[0][0];
//            range[1][0] = values[(int) datas][0];
//
//        }
//        catch (FileNotFoundException ex) {}
//        catch (IOException ex) {}
//        finally {}

        data = new Well(well,set,reference,units,select);
        return data;
    }
}