package com.app.quantitymeasurement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class InchesMeasurementTesting {

    // Same value equality
    @Test
    public void testInchesEquality_SameValue() {
        Inches i1 = new Inches(10.0);
        Inches i2 = new Inches(10.0);
        assertEquals(i1, i2);
    }

    // Different value inequality
    @Test
    public void testInchesEquality_DifferentValue() {
        Inches i1 = new Inches(10.0);
        Inches i2 = new Inches(11.0);
        assertNotEquals(i1, i2);
    }

    // Null comparison
    @Test
    public void testInchesEquality_NullComparison() {
        Inches i1 = new Inches(10.0);
        assertNotEquals(i1, null);
    }

    // Different class comparison
    @Test
    public void testInchesEquality_DifferentClass() {
        Inches i1 = new Inches(10.0);
        Feet f1 = new Feet(10.0);
        assertNotEquals(i1, f1);
    }

    // Same reference check
    @Test
    public void testInchesEquality_SameReference() {
        Inches i1 = new Inches(10.0);
        assertEquals(i1, i1);
    }
}