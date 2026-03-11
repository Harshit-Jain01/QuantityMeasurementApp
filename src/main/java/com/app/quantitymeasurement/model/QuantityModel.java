package com.app.quantitymeasurement.model;

import com.app.quantitymeasurement.IMeasurable;

public class QuantityModel<U extends IMeasurable> {

    public double value;
    public U unit;

    //Constructor to create a QuantityModel with a value and unit.
     
    public QuantityModel(double value, U unit) {
        this.value = value;
        this.unit = unit;
    }

    @Override
    public String toString() {
        return value + " " + unit.getUnitName();
    }
}