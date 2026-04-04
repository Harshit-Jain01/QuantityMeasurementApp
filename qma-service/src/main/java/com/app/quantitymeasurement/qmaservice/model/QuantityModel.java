package com.app.quantitymeasurement.qmaservice.model;

import com.app.quantitymeasurement.qmaservice.unit.IMeasurable;

/**
 * QuantityModel
 *
 * Immutable internal model pairing a numeric value with its measurable unit.
 * Used by the service layer to carry operands and results during comparison,
 * conversion, and arithmetic calculations.
 */
public class QuantityModel<U extends IMeasurable> {

    private final Double value;
    private final U unit;

   
    public QuantityModel(double value, U unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Value must be finite");
        }
        this.value = value;
        this.unit  = unit;
    }

   
    public double getValue() {
        return value;
    }

    
    public U getUnit() {
        return unit;
    }

    
    @Override
    public String toString() {
        return String.format("%s %s",
            Double.toString(value).replaceAll("\\.0+$", ""),
            unit.getUnitName());
    }
}