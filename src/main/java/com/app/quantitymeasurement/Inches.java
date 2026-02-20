package com.app.quantitymeasurement;

import java.util.Objects;

public class Inches {

    private final double value;

    public Inches(double value) {
        if (Double.isNaN(value)) {
            throw new IllegalArgumentException("Invalid numeric value for Inches");
        }
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null) return false;

        if (!(obj instanceof Inches)) return false;

        Inches other = (Inches) obj;

        return Double.compare(this.value, other.value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
