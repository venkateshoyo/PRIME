package com.PRIME.main.operations.mainMenus.dataMenu;

//Not very efficient code. Need to improve this. Also make it more clean.

import com.PRIME.main.operations.mainMenus.analysisMenu.crossplotnew;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class loadlasfile {

    public double values[][];
    public double datas=0.0,increment=0.0;
    public String parameter[]=new String[30];
    public int noOfParameter=-1;
    public double range[][];
    public static Double MaxLati = Double.MIN_VALUE;public static Double MinLati = Double.MAX_VALUE;
    public static Double MaxLong = Double.MIN_VALUE;public static Double MinLong = Double.MAX_VALUE;

    public void loadlas(File selectedlas)throws IOException{
        BufferedReader bufferedReader;
        try {
            //BufferedReader fot LAS/ ASCII file input
            bufferedReader = new BufferedReader(new FileReader(selectedlas));

            String text;
            Pattern regex = Pattern.compile("(\\d+(?:\\.\\d+)?)"); //regex used as delimiter
            boolean Isdata = false, Isparameter = false;
            int index = 0, parameterindex = -1;

            //Reading each line and storing each parameter value to temporary matrix
            while ((text = bufferedReader.readLine()) != null && text.length() > 0) {

                //Checking for empty lines
                if (Isdata) {

                    //Replacing multiple line spaces to single space
                    text = (text.replaceAll("[ ]+", " ")).substring(1);
                    text += " ";
                    int textindex = 0;

                    //finding indexOF of spaces which gives me parameter value just before that
                    while (text.indexOf(" ", textindex) > 0) {
                        if (++parameterindex == 0)
                            values[index][parameterindex] = 0.3048 * Double.parseDouble(text.substring(textindex, text.indexOf(" ", textindex)));
                            //0.3048 factor to convert meter to feet
                        else{
                            values[index][parameterindex] = Double.parseDouble(text.substring(textindex, text.indexOf(" ", textindex)));
                            if(index==0){

                                //Storing minimum at index 1 and maximum at index 2.
                                range[0][parameterindex]=values[index][parameterindex];
                                range[1][parameterindex]=values[index][parameterindex];
                            }
                            else {
                                if(range[0][parameterindex]>values[index][parameterindex])
                                    range[0][parameterindex]=values[index][parameterindex];
                                if(range[1][parameterindex]<values[index][parameterindex])
                                    range[1][parameterindex]=values[index][parameterindex];
                            }
                        }
                            textindex = text.indexOf(" ", textindex) + 1;
                    }
                    ++index;
                    parameterindex = -1;

                    //Reinitializing parameterindexIndex = -1 to start for new line
                } else if (text.length() > 4 && (text.substring(0, 4).equalsIgnoreCase("STRT"))) {

                    //Checking for Start Depth Data and working accordingly
                    Matcher matcher = regex.matcher(text);
                    while (matcher.find()) {
                        datas -= Double.parseDouble(matcher.group(1));
                    }
                } else if (text.length() > 4 && (text.substring(0, 4).equalsIgnoreCase("STOP"))) {

                    //Checking for End Depth Data and working accordingly
                    Matcher matcher = regex.matcher(text);
                    while (matcher.find()) {
                        datas += Double.parseDouble(matcher.group(1));
                    }
                } else if (text.length() > 4 && (text.substring(0, 4).equalsIgnoreCase("STEP"))) {

                    //Checking for Step Increment Data and working accordingly
                    Matcher matcher = regex.matcher(text);
                    while (matcher.find()) {
                        increment = Double.parseDouble(matcher.group(1));
                        datas /= increment;
                    }
                } else if (text.length() > 5 && (text.substring(1, 6).equalsIgnoreCase("depth"))) {

                    //Checking for Start Depth Data and working accordingly
                    Isparameter = true;
                    parameter[++noOfParameter] = "Depth in meter";
                } else if (Isparameter && !(text.substring(0, 4).equalsIgnoreCase("~Asc"))) {

                    //"~Asc" Statement confirms start of Data
                    parameter[++noOfParameter] = text.substring(1, text.indexOf(" ", 1));
                } else if (text.length() > 4 && (text.substring(0, 4).equalsIgnoreCase("~Asc"))) {

                    //Once Data is confirmed initialize values matrix for reading las/ASCII file values
                    Isdata = true;
                    values = new double[(int) datas + 1][noOfParameter + 1];
                    range= new double[2][noOfParameter + 1];
                }
            }
            range[0][0] = values[0][0];
            range[1][0] = values[(int) datas][0];

//           verticalplots vplotsobject = new verticalplots();
//           vplotsobject.displayplots(values,range,parameter);

//            xyzcrossplot crosplot = new xyzcrossplot();
//            crosplot.crossplotdisplay(2,4,0,values,range,parameter);

            crossplotnew ob = new crossplotnew();
            ob.crossplots(values,range,parameter);
        }
        catch (FileNotFoundException ex) {}
        catch (IOException ex) {}
        finally {}
    }
}
