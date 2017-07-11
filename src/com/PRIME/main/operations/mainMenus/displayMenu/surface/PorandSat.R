# df - Stored dataframe
print(dim(df))
newdf = data.frame(DEPTH=df[names(df)[1]])

calculateRHOG= function(df,newdf){
  print("rhog")
  d1=1;d2=2;d3=3;d4=4;d5=5;comp1=0;comp2=0;comp3=0;comp4=0;comp5=0;bool=F;
  if ("Vsh" %in% names(df)){bool=T;comp1=df$Vsh}
  if ("Vsa" %in% names(df)){bool=T;comp2=df$Vsa}
  if ("Vcl" %in% names(df)){bool=T;comp3=df$Vcl}
  if ("Vli" %in% names(df)){bool=T;comp4=df$Vli}
  if ("Vsl" %in% names(df)){bool=T;comp5=df$Vsl}
  print(dim(newdf))
  newdf$RHOG = (d1*comp1 + d2*comp2 + d3*comp3 + d4*comp4+d5*comp5)
  if (bool==F){print("Incomplete set of parameters for RHOG")}
  return (newdf)
}

calculatePOROSITY= function(df,newdf){
  print("poro")
  df$RHOF=1
  if (!"RHOB" %in% names(df)){print("Incomplete set of parameters for porosity")}
  else{newdf$NEUT = (df$RHOG - df$RHOB)/(df$RHOG - df$RHOF)}
  return (newdf)
}

calculateTEMP= function(df,newdf){
  print("tem")
  Tsurface = 24; Tempgrad = -17.22/30.48  # Deg Celcius/metre
  newdf$TEMP = Tsurface + (Tempgrad * df[,names(df)[1]])
  return (newdf)
}

calculateWaterResistivity = function(df,newdf){
  print("rwater")
  Rw1 = 0.07;T1 = 24;
  newdf$WaterResistivity = Rw1*(T1 + 21.5)/(newdf$TEMP + 21.5)
  return (newdf)
}

calculateSaturation = function(df,newdf){
  print("Sat")
  a=1;m=2;n=2;
  if (! "RESD" %in% names(df)){
    print("Missing True Resistivity")
  }else{
  newdf$Saturation = ((a * abs(newdf$WaterResistivity))/(abs(newdf$NEUT^m)*(abs(df$RESD))))^(1/n)
  }
  return (newdf)
}

if(! "NEUT" %in% names(df)){
  if (! "RHOG" %in% names(df)){
    newdf = calculateRHOG(df,newdf)
  }else{newdf$RHOG = df$RHOG}
  newdf = calculatePOROSITY(df,newdf)
}else{
  newdf$NEUT = df$NEUT
}

if (! "Saturation" %in% names(df)){
  if (! "WaterResistivity" %in% names(df)){
    if (! "TEMP" %in% names(df)){
      newdf = calculateTEMP(df,newdf)
    }else{
      newdf$TEMP=df$TEMP}
    newdf = calculateWaterResistivity(df,newdf)
  }else{
    newdf$WaterResistivity= df$WaterResistivity
  }
  newdf = calculateSaturation(df,newdf)
}else{newdf$Saturation = df$Saturation}


# write.csv(newdf,"C:\\Users\\Admin\\Desktop\\NEWDF.csv",row.names=F)