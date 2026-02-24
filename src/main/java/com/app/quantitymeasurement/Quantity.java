package com.app.quantitymeasurement;

import java.util.Objects;


import java.util.Objects;

public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;
    private static final double EPSILON = 1e-4; // Fix 1: tighter epsilon (was 0.0001)

    public Quantity(double value, U unit) {
        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");
        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Value must be finite");
        this.value = value;
        this.unit = unit;
    }

    public double getValue() { return value; }
    public U getUnit()       { return unit;  }

    public enum ArithmeticOperation {

        ADD {
            @Override
            public double compute(double a, double b) { return a + b; }
        },

        SUBTRACT {
            @Override
            public double compute(double a, double b) { return a - b; }
        },

        DIVIDE {
            @Override
            public double compute(double a, double b) {
                if (b == 0) throw new IllegalArgumentException("Cannot divide by zero");
                return a / b;
            }
        };

        public abstract double compute(double a, double b);
    }

    private void validateArithmeticOperands(
            Quantity<U> other,
            U targetUnit,
            boolean targetRequired,
            ArithmeticOperation operation) {

        if (other == null)
            throw new IllegalArgumentException("Other quantity cannot be null");

        if (!Double.isFinite(other.value))
            throw new IllegalArgumentException("Other value must be finite");

        if (targetRequired && targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        // Fix 2: validateOperationSupport BEFORE cross-category check.
        // TemperatureUnit must throw UnsupportedOperationException here,
        // before the cross-category check ever runs.
        this.unit.validateOperationSupport(operation.name());
        other.unit.validateOperationSupport(operation.name());

        if (!this.unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Cross-category operations not allowed");
    }

    private double performBaseArithmetic(Quantity<U> other, ArithmeticOperation operation) {
        double baseThis  = this.unit.convertToBaseUnit(this.value);
        double baseOther = other.unit.convertToBaseUnit(other.value);
        return operation.compute(baseThis, baseOther);
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        validateArithmeticOperands(other, targetUnit, true, ArithmeticOperation.ADD);
        double baseResult = performBaseArithmetic(other, ArithmeticOperation.ADD);
        return new Quantity<>(round(targetUnit.convertFromBaseUnit(baseResult)), targetUnit);
    }

    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
        validateArithmeticOperands(other, targetUnit, true, ArithmeticOperation.SUBTRACT);
        double baseResult = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);
        return new Quantity<>(round(targetUnit.convertFromBaseUnit(baseResult)), targetUnit);
    }

    public double divide(Quantity<U> other) {
        validateArithmeticOperands(other, null, false, ArithmeticOperation.DIVIDE);
        return performBaseArithmetic(other, ArithmeticOperation.DIVIDE);
    }

    public Quantity<U> convertTo(U targetUnit) {
        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");
        double baseValue = unit.convertToBaseUnit(value);
        return new Quantity<>(round(targetUnit.convertFromBaseUnit(baseValue)), targetUnit);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Quantity<?> other)) return false;
        if (!this.unit.getClass().equals(other.unit.getClass())) return false;

        double baseThis  = this.unit.convertToBaseUnit(this.value);
        double baseOther = other.unit.convertToBaseUnit(other.value);
        return Math.abs(baseThis - baseOther) < EPSILON; // uses tighter 1e-9 epsilon
    }

    @Override
    public int hashCode() {
        // Fix 3: round to 6 decimal places to stay consistent with 1e-9 epsilon
        return Objects.hash(Math.round(unit.convertToBaseUnit(value) * 1_000_000));
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}