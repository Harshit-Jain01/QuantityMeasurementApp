package com.app.quantitymeasurement;

public enum LengthUnit {

	INCH(1.0), FEET(12.0), YARDS(36.0), CENTIMETERS(0.393701);

	private final double conversionFactor;

	// Constructor
	LengthUnit(double conversionFactor) {
		this.conversionFactor = conversionFactor;
	}

	public double getConversionFactor() {
		return conversionFactor;
	}
	
}