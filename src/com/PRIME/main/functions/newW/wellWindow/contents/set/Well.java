package com.PRIME.main.functions.newW.wellWindow.contents.set;

public class Well {
    public String well;
    public String set;
    public String reference;
    public String units;
    public boolean select;

    public Well(String well, String set, String reference, String units, boolean select) {
        this.well = well;
        this.set = set;
        this.reference = reference;
        this.units = units;
        this.select = select;
    }

    public String getWell() {
        return well;
    }
    public void setWell(String wellName) {
        this.well = well;
    }

    public String getSet() {
        return set;
    }
    public void setSet(String setType) {
        this.set = set;
    }

    public String getReference() {
        return reference;
    }
    public void setReference(String referenceName) {
        this.reference = reference;
    }

    public String getUnits() {
        return units;
    }
    public void setUnits(String unitsName) {
        this.units = units;
    }

    public boolean isSelect() {
        return select;
    }
    public void setSelect(boolean selectType) {
        this.select = select;
    }

}