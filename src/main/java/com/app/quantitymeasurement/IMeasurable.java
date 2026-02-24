package com.app.quantitymeasurement;

public interface IMeasurable {
	  double convertToBaseUnit(double value);

	    double convertFromBaseUnit(double baseValue);

	    // Simple default — returns true for all units except TemperatureUnit (which overrides)
	    default boolean supportsArithmetic() {
	        return true;
	    }

	    // No-op by default; TemperatureUnit overrides to throw
	    default void validateOperationSupport(String operation) {
	        // allowed — do nothing
	    }
}