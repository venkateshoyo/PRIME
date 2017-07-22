package com.PRIME.main.operations.menubars.displayMenu.surface;

//Not very efficient code. Need to improve this. Also make it more clean.


import org.renjin.primitives.sequence.IntSequence;
import org.renjin.sexp.*;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.*;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class loadlasfil {

    public double values[][];
    public double datas=0.0,increment=0.0;
    public String parameter[]=new String[100];
    public int noOfParameter=-1;
    public double range[][];
    double lati = 0;
    double longi = 0;
    String Text = "";
    public static Double MaxLati = -Double.MAX_VALUE;
    public static Double MinLati = Double.MAX_VALUE;
    public static Double MaxLong = -Double.MAX_VALUE;
    public static Double MinLong = Double.MAX_VALUE;
    public static Double MaxDepth=-Double.MAX_VALUE;
    public static Double MinDepth =Double.MAX_VALUE;
    public static HashMap<String,ListVector > allwell = new HashMap<>();

    public ListVector loadlas(String name,File selectedlas) throws IOException, ScriptException {
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
                        if (++parameterindex == 0) {
                            values[index][parameterindex] =  Double.parseDouble(text.substring(textindex, text.indexOf(" ", textindex)));
                        }

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
                        double depth =Double.parseDouble(matcher.group(1));
                        datas -= depth;
                        MinDepth = Math.min(MinDepth,depth);
                    }
                } else if (text.length() > 4 && (text.substring(0, 4).equalsIgnoreCase("STOP"))) {

                    //Checking for End Depth Data and working accordingly
                    Matcher matcher = regex.matcher(text);
                    while (matcher.find()) {
                        double depth = Double.parseDouble(matcher.group(1));
                        datas += depth;
                        MaxDepth = Math.max(MaxDepth, depth);
                    }
                } else if (text.length() > 4 && (text.substring(0, 4).equalsIgnoreCase("STEP"))) {

                    //Checking for Step Increment Data and working accordingly
                    Matcher matcher = regex.matcher(text);
                    while (matcher.find()) {
                        increment = Double.parseDouble(matcher.group(1));
                        datas /= increment;
                    }
                } else if (text.length() > 5 && (text.substring(0, 5).equalsIgnoreCase("DEPTH"))) {

                    //Checking for Start Depth Data and working accordingly
                    Isparameter = true;
                    parameter[++noOfParameter] = "DEPTH";
                } else if (Isparameter && !(text.substring(0, 2).equalsIgnoreCase("~A"))) {

                    //"~Asc" Statement confirms start of Data
                    if(text.substring(0, 2).equalsIgnoreCase("~P"))
                        Isparameter= false;
                    else {
                        parameter[++noOfParameter] = text.substring(0, text.indexOf(" ", 1));
                    }

                } else if (text.length() > 4 && (text.substring(0, 2).equalsIgnoreCase("~A"))) {

                    //Once Data is confirmed initialize values matrix for reading las/ASCII file v       alues
                    Isdata = true;
                    values = new double[(int) (datas+0.5) + 1][noOfParameter + 1];
                    range= new double[2][noOfParameter + 1];
                }
                else if (text.length() > 4 && (text.substring(0, 4).equalsIgnoreCase("LATI"))) {

                    //Checking for Start Depth Data and working accordingly
                    Pattern regex1 = Pattern.compile("(-?(\\d+)+(\\.)?(\\d+)*)"); //regex used as delimiter
                    Matcher matcher = regex1.matcher(text);
                    while (matcher.find()) {
                        lati = Double.parseDouble(matcher.group(1));
                        MinLati = Math.min(MinLati,lati);
                        MaxLati = Math.max(MaxLati, lati);
                    }
                } else if (text.length() > 4 && (text.substring(0, 4).equalsIgnoreCase("LONG"))) {

                    //Checking for Start Depth Data and working accordingly
                    Pattern regex1 = Pattern.compile("(-?(\\d+)+(\\.)?(\\d+)*)"); //regex used as delimiter
                    Matcher matcher = regex1.matcher(text);
                    while (matcher.find()) {
                        longi = Double.parseDouble(matcher.group(1));
                        MinLong = Math.min(MinLong,longi);
                        MaxLong = Math.max(MaxLong, longi);
                    }
                } else if (text.length() > 4 && (text.substring(0, 4).equalsIgnoreCase("WELL"))) {

                    //Checking for Start Depth Data and working accordingly
                    text.replaceAll(" ","");
                    // Matcher matcher = regex.matcher();
                    Text = text.substring(5,text.length());
                }
            }

            range[0][0] = values[0][0];
            range[1][0] = values[(int) datas][0];
        }
        catch (FileNotFoundException ex) {}
        catch (IOException ex) {}
        finally {}

        noOfParameter++;
        double transpose[][] = new double[noOfParameter][values.length];
        for (int c = 0 ; c < noOfParameter ; c++ )
        {
            for (int r = 0 ; r < values.length ; r++ )
                transpose[c][r] = values[r][c];
        }

        ListVector.NamedBuilder dataframe = ListVector.newNamedBuilder();
        dataframe.setAttribute(Symbols.CLASS, new StringArrayVector("data.frame"));
        dataframe.setAttribute(Symbols.ROW_NAMES, new IntSequence(1, 1, values.length));

        for(int col=0;col<noOfParameter;col++) {
            double[] valuesds = transpose[col];
            dataframe.add(parameter[col], new DoubleArrayVector(valuesds));
        }

        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine engine = factory.getEngineByName("Renjin");

        engine.put("df", dataframe.build());
        engine.put("LATITUDE",lati);engine.put("LONGITUDE",longi);
        engine.eval("newdf1 = data.frame(LATI = LATITUDE,LONG = LONGITUDE,DEPTH = df$DEPTH)");

        ListVector list = (ListVector)engine.eval("newdf1");
        String path = System.getProperty("user.dir");

        engine.eval(new FileReader(path+"/src/com/PRIME/main/operations/menubars/displayMenu/surface/PorandSat.R"));
        engine.put("LATITUDE",lati);engine.put("LONGITUDE",longi);
        engine.eval("newdf$LATI = LATITUDE;newdf$LONG = LONGITUDE;");
        engine.eval("DATA = newdf[,c('LATI','LONG','DEPTH','NEUT','Saturation')]");
        ListVector list1 = (ListVector)engine.eval("DATA");
        allwell.put(name,list1);

        return list;
    }
}
