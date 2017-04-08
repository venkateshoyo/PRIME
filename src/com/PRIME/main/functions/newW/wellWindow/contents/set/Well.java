package com.PRIME.main.functions.newW.wellWindow.contents.set;

import javafx.beans.property.SimpleStringProperty;

public class Well {
    public final SimpleStringProperty well;
    public final SimpleStringProperty set;
    public final SimpleStringProperty reference;
    public final SimpleStringProperty units;
    public final SimpleStringProperty select;

    public Well(String wellName, String setType, String referenceName, String unitsName, String selectType) {
        this.well = new SimpleStringProperty(wellName);
        this.set = new SimpleStringProperty(setType);
        this.reference = new SimpleStringProperty(referenceName);
        this.units = new SimpleStringProperty(unitsName);
        this.select = new SimpleStringProperty(selectType);
    }

    public String getWell() {
        return well.get();
    }
    public void setWell(String wellName) {
        well.set(wellName);
    }

    public String getSet() {
        return set.get();
    }
    public void setSet(String setType) {
        set.set(setType);
    }

    public String getReference() {
        return reference.get();
    }
    public void setReference(String referenceName) {
        reference.set(referenceName);
    }

    public String getUnits() {
        return units.get();
    }
    public void setUnits(String unitsName) {
        units.set(unitsName);
    }

    public String getSelect() {
        return select.get();
    }
    public void setSelect(String selectType) {
        select.set(selectType);
    }

}