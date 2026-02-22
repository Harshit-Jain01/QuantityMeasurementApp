package com.app.quantitymeasurement;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class LengthMeasurementTesting {

    @Test
    void testEquality_YardToYard_SameValue() {

        QuantityLength q1 = new QuantityLength(5.0, LengthUnit.YARDS);
        QuantityLength q2 = new QuantityLength(5.0, LengthUnit.YARDS);

        assertTrue(q1.equals(q2));
    }

    @Test
    void testEquality_YardToYard_DifferentValue() {

        QuantityLength q1 = new QuantityLength(5.0, LengthUnit.YARDS);
        QuantityLength q2 = new QuantityLength(7.0, LengthUnit.YARDS);

        assertFalse(q1.equals(q2));
    }

    @Test
    void testEquality_YardToFeet_EquivalentValue() {

        QuantityLength yard = new QuantityLength(4.0, LengthUnit.YARDS);
        QuantityLength feet = new QuantityLength(12.0, LengthUnit.FEET);

        assertTrue(yard.equals(feet));
    }

    @Test
    void testEquality_FeetToYard_EquivalentValue() {

        QuantityLength feet = new QuantityLength(15.0, LengthUnit.FEET);
        QuantityLength yard = new QuantityLength(5.0, LengthUnit.YARDS);

        assertTrue(feet.equals(yard));
    }

    @Test
    void testEquality_YardToInches_EquivalentValue() {

        QuantityLength yard = new QuantityLength(3.0, LengthUnit.YARDS);
        QuantityLength inches = new QuantityLength(108.0, LengthUnit.INCH);

        assertTrue(yard.equals(inches));
    }

    @Test
    void testEquality_InchesToYard_EquivalentValue() {

        QuantityLength inches = new QuantityLength(144.0, LengthUnit.INCH);
        QuantityLength yard = new QuantityLength(4.0, LengthUnit.YARDS);

        assertTrue(inches.equals(yard));
    }

    @Test
    void testEquality_YardToFeet_NonEquivalentValue() {

        QuantityLength yard = new QuantityLength(3.0, LengthUnit.YARDS);
        QuantityLength feet = new QuantityLength(8.0, LengthUnit.FEET);

        assertFalse(yard.equals(feet));
    }

    @Test
    void testEquality_CentimetersToInches_EquivalentValue() {

        QuantityLength cm = new QuantityLength(5.08, LengthUnit.CENTIMETERS);
        QuantityLength inches = new QuantityLength(2.0, LengthUnit.INCH);

        assertTrue(cm.equals(inches));
    }

    @Test
    void testEquality_CentimetersToFeet_NonEquivalentValue() {

        QuantityLength cm = new QuantityLength(50.0, LengthUnit.CENTIMETERS);
        QuantityLength feet = new QuantityLength(1.0, LengthUnit.FEET);

        assertFalse(cm.equals(feet));
    }

    @Test
    void testEquality_MultiUnit_TransitiveProperty() {

        QuantityLength yard = new QuantityLength(6.0, LengthUnit.YARDS);
        QuantityLength feet = new QuantityLength(18.0, LengthUnit.FEET);
        QuantityLength inches = new QuantityLength(216.0, LengthUnit.INCH);

        assertTrue(yard.equals(feet));
        assertTrue(feet.equals(inches));
        assertTrue(yard.equals(inches));
    }

    @Test
    void testEquality_YardWithNullUnit() {

        assertThrows(IllegalArgumentException.class, () -> new QuantityLength(10.0, null));
    }

    @Test
    void testEquality_CentimetersWithNullUnit() {

        assertThrows(IllegalArgumentException.class, () -> new QuantityLength(20.0, null));
    }

    @Test
    void testEquality_YardSameReference() {

        QuantityLength yard = new QuantityLength(8.0, LengthUnit.YARDS);
        assertTrue(yard.equals(yard));
    }

    @Test
    void testEquality_CentimetersSameReference() {

        QuantityLength cm = new QuantityLength(25.0, LengthUnit.CENTIMETERS);
        assertTrue(cm.equals(cm));
    }

    @Test
    void testEquality_YardNullComparison() {

        QuantityLength yard = new QuantityLength(9.0, LengthUnit.YARDS);
        assertFalse(yard.equals(null));
    }

    @Test
    void testEquality_CentimetersNullComparison() {

        QuantityLength cm = new QuantityLength(30.0, LengthUnit.CENTIMETERS);
        assertFalse(cm.equals(null));
    }

    @Test
    void testEquality_AllUnits_ComplexScenario() {

        QuantityLength yards = new QuantityLength(7.0, LengthUnit.YARDS);
        QuantityLength feet = new QuantityLength(21.0, LengthUnit.FEET);
        QuantityLength inches = new QuantityLength(252.0, LengthUnit.INCH);

        assertTrue(yards.equals(feet));
        assertTrue(feet.equals(inches));
        assertTrue(yards.equals(inches));
    }
}