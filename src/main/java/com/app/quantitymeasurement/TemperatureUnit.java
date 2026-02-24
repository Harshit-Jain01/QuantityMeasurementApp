package com.app.quantitymeasurement;


public enum TemperatureUnit implements IMeasurable {

    CELSIUS {
        @Override
        public double convertToBaseUnit(double value) {
            return value; // Celsius is the base unit
        }

        @Override
        public double convertFromBaseUnit(double baseValue) {
            return baseValue;
        }
    },

    FAHRENHEIT {
        @Override
        public double convertToBaseUnit(double value) {
            return (value - 32) * 5.0 / 9.0;
        }

        @Override
        public double convertFromBaseUnit(double baseValue) {
            return (baseValue * 9.0 / 5.0) + 32;
        }
    };

    // Overrides the default true from IMeasurable
    @Override
    public boolean supportsArithmetic() {
        return false;
    }

    // Overrides the no-op default from IMeasurable — always throws
    @Override
    public void validateOperationSupport(String operation) {
        throw new UnsupportedOperationException(
                "Temperature does not support arithmetic operation: " + operation);
    }
}