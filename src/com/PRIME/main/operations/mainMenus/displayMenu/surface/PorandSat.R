# df - Stored dataframe
df[df==-999.25]=NA
newdf = data.frame(DEPTH=df[names(df)[1]])

calculateRHOG= function(df,newdf){
  d1=2.6;d2=2.5;d3=2.4;d4=2.5;d5=2.75;              # Densities of components
  comp1=0;comp2=0;comp3=0;comp4=0;comp5=0;bool=F;
  if ("Vsh" %in% names(df)){bool=T;comp1=df$Vsh}    # Shale
  if ("Vsa" %in% names(df)){bool=T;comp2=df$Vsa}    # Sandstone
  if ("Vcl" %in% names(df)){bool=T;comp3=df$Vcl}    # Claystone
  if ("Vli" %in% names(df)){bool=T;comp4=df$Vli}    # Limestone
  if ("Vsl" %in% names(df)){bool=T;comp5=df$Vsl}    # Slate
  newdf$RHOG = (d1*comp1 + d2*comp2 + d3*comp3 + d4*comp4+d5*comp5)
  if (bool==F){print("Incomplete set of parameters for RHOG")}
  return (newdf)
}

calculatePOROSITY= function(df,newdf){
  df$RHOF=1
  if (!"RHOB" %in% names(df)){print("Incomplete set of parameters for porosity : Need RHOB")}
  else{newdf$NEUT = (df$RHOG - df$RHOB)/(df$RHOG - df$RHOF)}
  return (newdf)
}

calculateTEMP= function(df,newdf){
  Tsurface = 24; Tempgrad = 0.556/30.48            # Degree Celcius/metre
  newdf$TEMP = Tsurface + (Tempgrad * df[,names(df)[1]])
  return (newdf)
}

calculateWaterResistivity = function(df,newdf){
  Rw1 = 0.07;T1 = 24;                               # Resistivity of Mud at surface (24 Degrees)
  newdf$WaterResistivity = Rw1*(T1 + 21.5)/(newdf$TEMP + 21.5)
  return (newdf)
}

calculateSaturation = function(df,newdf){
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
newdf[newdf==-999.25]=NA
newdf = newdf[complete.cases(newdf),]
