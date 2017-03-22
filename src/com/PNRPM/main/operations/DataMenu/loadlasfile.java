package com.PNRPM.main.operations.dataMenu;

import com.PNRPM.main.functions.verticalplots;
import com.PNRPM.main.functions.xyzcrossplot;
import com.PNRPM.main.operations.displayMenu.crossplot;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class loadlasfile {

    public double values[][]= new double[4][4];
    public double datas=0.0,increment=0.0;
    public String parameter[]=new String[30];
    public int noOfParameter=-1;
    public double range[][];

    public void loadlas(File selectedlas)throws IOException{
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(selectedlas));
            String text;
            Pattern regex = Pattern.compile("(\\d+(?:\\.\\d+)?)");
            boolean Isdata = false, Isparameter = false;
            int index = 0, parameterindex = -1;
            while ((text = bufferedReader.readLine()) != null && text.length() > 0) {
                if (Isdata) {
                    text = (text.replaceAll("[ ]+", " ")).substring(1);
                    text += " ";
                    int textindex = 0;
                    while (text.indexOf(" ", textindex) > 0) {
                        if (++parameterindex == 0)
                            values[index][parameterindex] = 0.3048 * Double.parseDouble(text.substring(textindex, text.indexOf(" ", textindex)));
                        else{
                            values[index][parameterindex] = Double.parseDouble(text.substring(textindex, text.indexOf(" ", textindex)));
                            if(index==0){
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
                } else if (text.length() > 4 && (text.substring(0, 4).equalsIgnoreCase("STRT"))) {
                    Matcher matcher = regex.matcher(text);
                    while (matcher.find()) {
                        datas -= Double.parseDouble(matcher.group(1));
                    }
                } else if (text.length() > 4 && (text.substring(0, 4).equalsIgnoreCase("STOP"))) {
                    Matcher matcher = regex.matcher(text);
                    while (matcher.find()) {
                        datas += Double.parseDouble(matcher.group(1));
                    }
                } else if (text.length() > 4 && (text.substring(0, 4).equalsIgnoreCase("STEP"))) {
                    Matcher matcher = regex.matcher(text);
                    while (matcher.find()) {
                        increment = Double.parseDouble(matcher.group(1));
                        datas /= increment;
                    }
                } else if (text.length() > 5 && (text.substring(1, 6).equalsIgnoreCase("depth"))) {
                    Isparameter = true;
                    parameter[++noOfParameter] = "Depth in meter";
                } else if (Isparameter && !(text.substring(0, 4).equalsIgnoreCase("~Asc"))) {
                    parameter[++noOfParameter] = text.substring(1, text.indexOf(" ", 1));
                } else if (text.length() > 4 && (text.substring(0, 4).equalsIgnoreCase("~Asc"))) {
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

            crossplot ob = new crossplot();
            ob.crossplots(values,range,parameter);
        }
        catch (FileNotFoundException ex) {}
        catch (IOException ex) {}
        finally {}
    }
}
