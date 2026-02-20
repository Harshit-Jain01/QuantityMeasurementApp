package com.app.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class FeetMeasurementTesting {

    @Test
    public void testEquality_SameValue() {
        Feet f1 = new Feet(1.0);
        Feet f2 = new Feet(1.0);

        assertTrue(f1.equals(f2), "1.0 ft should be equal to 1.0 ft");
    }

    @Test
    public void testEquality_DifferentValue() {
        Feet f1 = new Feet(1.0);
        Feet f2 = new Feet(2.0);

        assertFalse(f1.equals(f2), "1.0 ft should not be equal to 2.0 ft");
    }

    @Test
    public void testEquality_NullComparison() {
        Feet f1 = new Feet(1.0);

        assertFalse(f1.equals(null), "Feet should not be equal to null");
    }

    @Test
    public void testEquality_DifferentClass() {
        Feet f1 = new Feet(1.0);
        String value = "1.0";

        assertFalse(f1.equals(value), "Feet should not be equal to a different type");
    }

    @Test
    public void testEquality_SameReference() {
        Feet f1 = new Feet(1.0);

        assertTrue(f1.equals(f1), "Object should be equal to itself (reflexive)");
    }
}