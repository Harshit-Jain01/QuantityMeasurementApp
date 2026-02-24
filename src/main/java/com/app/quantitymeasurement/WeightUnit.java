package com.app.quantitymeasurement;


public enum WeightUnit implements IMeasurable{
	KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.453592);

    private final double conversionFactor;

    WeightUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }

    @Override
    public double convertToBaseUnit(double value) {
        // No rounding here — Quantity.round() handles all rounding uniformly
        return value * conversionFactor;
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / conversionFactor;
    }

    public String getUnitName() {
        return this.name();
    }
}