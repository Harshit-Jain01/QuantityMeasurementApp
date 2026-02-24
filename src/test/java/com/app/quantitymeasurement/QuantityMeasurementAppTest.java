package com.app.quantitymeasurement;

import org.junit.jupiter.api.Test;

import com.app.quantitymeasurement.IMeasurable;
import com.app.quantitymeasurement.LengthUnit;
import com.app.quantitymeasurement.Quantity;
import com.app.quantitymeasurement.WeightUnit;

import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 0.0001;

    // =========================================================================
    // VOLUME — Equality
    // =========================================================================

    @Test
    void testEquality_LitreToLitre_SameValue() {
        assertTrue(new Quantity<>(1.0, VolumeUnit.LITRE)
                .equals(new Quantity<>(1.0, VolumeUnit.LITRE)));
    }

    @Test
    void testEquality_LitreToLitre_DifferentValue() {
        assertFalse(new Quantity<>(1.0, VolumeUnit.LITRE)
                .equals(new Quantity<>(2.0, VolumeUnit.LITRE)));
    }

    @Test
    void testEquality_LitreToMillilitre_EquivalentValue() {
        assertTrue(new Quantity<>(1.0, VolumeUnit.LITRE)
                .equals(new Quantity<>(1000.0, VolumeUnit.MILLILITRE)));
    }

    @Test
    void testEquality_MillilitreToLitre_EquivalentValue() {
        assertTrue(new Quantity<>(1000.0, VolumeUnit.MILLILITRE)
                .equals(new Quantity<>(1.0, VolumeUnit.LITRE)));
    }

    @Test
    void testEquality_LitreToGallon_EquivalentValue() {
        assertTrue(new Quantity<>(1.0, VolumeUnit.LITRE)
                .equals(new Quantity<>(0.264172, VolumeUnit.GALLON)));
    }

    @Test
    void testEquality_GallonToLitre_EquivalentValue() {
        assertTrue(new Quantity<>(1.0, VolumeUnit.GALLON)
                .equals(new Quantity<>(3.78541, VolumeUnit.LITRE)));
    }

    @Test
    void testEquality_VolumeVsLength_Incompatible() {
        assertFalse(new Quantity<>(1.0, VolumeUnit.LITRE)
                .equals(new Quantity<>(1.0, LengthUnit.FEET)));
    }

    @Test
    void testEquality_NullComparison() {
        assertFalse(new Quantity<>(1.0, VolumeUnit.LITRE).equals(null));
    }

    @Test
    void testEquality_SameReference() {
        Quantity<VolumeUnit> q = new Quantity<>(5.0, VolumeUnit.LITRE);
        assertTrue(q.equals(q));
    }

    @Test
    void testEquality_NullUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(1.0, null));
    }

    @Test
    void testEquality_TransitiveProperty() {
        Quantity<VolumeUnit> a = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> b = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> c = new Quantity<>(1.0, VolumeUnit.LITRE);
        assertTrue(a.equals(b));
        assertTrue(b.equals(c));
        assertTrue(a.equals(c));
    }

    @Test
    void testEquality_ZeroValue() {
        assertTrue(new Quantity<>(0.0, VolumeUnit.LITRE)
                .equals(new Quantity<>(0.0, VolumeUnit.MILLILITRE)));
    }

    @Test
    void testEquality_NegativeVolume() {
        assertTrue(new Quantity<>(-1.0, VolumeUnit.LITRE)
                .equals(new Quantity<>(-1000.0, VolumeUnit.MILLILITRE)));
    }

    @Test
    void testEquality_LargeVolumeValue() {
        assertTrue(new Quantity<>(1000000.0, VolumeUnit.MILLILITRE)
                .equals(new Quantity<>(1000.0, VolumeUnit.LITRE)));
    }

    @Test
    void testEquality_SmallVolumeValue() {
        assertTrue(new Quantity<>(0.001, VolumeUnit.LITRE)
                .equals(new Quantity<>(1.0, VolumeUnit.MILLILITRE)));
    }

    // =========================================================================
    // VOLUME — Addition
    // =========================================================================

    @Test
    void testAddition_SameUnit_MillilitrePlusMillilitre() {
        assertEquals(1000.0,
                new Quantity<>(500.0, VolumeUnit.MILLILITRE)
                        .add(new Quantity<>(500.0, VolumeUnit.MILLILITRE)).getValue(), EPSILON);
    }

    @Test
    void testAddition_CrossUnit_LitrePlusMillilitre() {
        assertEquals(2.0,
                new Quantity<>(1.0, VolumeUnit.LITRE)
                        .add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE)).getValue(), EPSILON);
    }

    @Test
    void testAddition_CrossUnit_MillilitrePlusLitre() {
        assertEquals(2000.0,
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE)
                        .add(new Quantity<>(1.0, VolumeUnit.LITRE)).getValue(), EPSILON);
    }

    @Test
    void testAddition_CrossUnit_GallonPlusLitre() {
        assertEquals(2.0,
                new Quantity<>(1.0, VolumeUnit.GALLON)
                        .add(new Quantity<>(3.78541, VolumeUnit.LITRE)).getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Litre() {
        assertEquals(2.0,
                new Quantity<>(1.0, VolumeUnit.LITRE)
                        .add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE), VolumeUnit.LITRE)
                        .getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Millilitre() {
        assertEquals(2000.0,
                new Quantity<>(1.0, VolumeUnit.LITRE)
                        .add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE), VolumeUnit.MILLILITRE)
                        .getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Gallon() {
        assertEquals(2.0,
                new Quantity<>(3.78541, VolumeUnit.LITRE)
                        .add(new Quantity<>(3.78541, VolumeUnit.LITRE), VolumeUnit.GALLON)
                        .getValue(), EPSILON);
    }

    @Test
    void testAddition_Commutativity() {
        Quantity<VolumeUnit> a = new Quantity<>(1.0, VolumeUnit.LITRE)
                .add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE));
        Quantity<VolumeUnit> b = new Quantity<>(1000.0, VolumeUnit.MILLILITRE)
                .add(new Quantity<>(1.0, VolumeUnit.LITRE));
        assertTrue(a.equals(b));
    }

    @Test
    void testAddition_WithZero() {
        assertEquals(5.0,
                new Quantity<>(5.0, VolumeUnit.LITRE)
                        .add(new Quantity<>(0.0, VolumeUnit.MILLILITRE)).getValue(), EPSILON);
    }

    @Test
    void testAddition_NegativeValues() {
        assertEquals(3.0,
                new Quantity<>(5.0, VolumeUnit.LITRE)
                        .add(new Quantity<>(-2000.0, VolumeUnit.MILLILITRE)).getValue(), EPSILON);
    }

    @Test
    void testAddition_LargeValues() {
        assertEquals(2e6,
                new Quantity<>(1e6, VolumeUnit.LITRE)
                        .add(new Quantity<>(1e6, VolumeUnit.LITRE)).getValue(), EPSILON);
    }

    // =========================================================================
    // VOLUME — Unit enum basics
    // =========================================================================

    @Test
    void testVolumeUnitEnum_LitreConstant() {
        assertEquals(1.0, VolumeUnit.LITRE.convertToBaseUnit(1.0), EPSILON);
    }

    @Test
    void testVolumeUnitEnum_MillilitreConstant() {
        assertEquals(0.001, VolumeUnit.MILLILITRE.convertToBaseUnit(1.0), EPSILON);
    }

    @Test
    void testVolumeUnitEnum_GallonConstant() {
        assertEquals(3.78541, VolumeUnit.GALLON.convertToBaseUnit(1.0), EPSILON);
    }

    @Test
    void testConvertFromBaseUnit_LitreToMillilitre() {
        assertEquals(1000.0, VolumeUnit.MILLILITRE.convertFromBaseUnit(1.0), EPSILON);
    }

    @Test
    void testGenericQuantity_VolumeOperations_Consistency() {
        Quantity<VolumeUnit> v1 = new Quantity<>(2.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(2000.0, VolumeUnit.MILLILITRE);
        assertTrue(v1.equals(v2));
        assertEquals(4.0, v1.add(v2).getValue(), EPSILON);
    }

    // =========================================================================
    // SUBTRACTION
    // =========================================================================

    @Test
    void testSubtraction_SameUnit_FeetMinusFeet() {
        assertEquals(new Quantity<>(5.0, LengthUnit.FEET),
                new Quantity<>(10.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(5.0, LengthUnit.FEET)));
    }

    @Test
    void testSubtraction_SameUnit_LitreMinusLitre() {
        assertEquals(new Quantity<>(7.0, VolumeUnit.LITRE),
                new Quantity<>(10.0, VolumeUnit.LITRE)
                        .subtract(new Quantity<>(3.0, VolumeUnit.LITRE)));
    }

    @Test
    void testSubtraction_CrossUnit_FeetMinusInches() {
        assertEquals(new Quantity<>(9.5, LengthUnit.FEET),
                new Quantity<>(10.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(6.0, LengthUnit.INCH)));
    }

    @Test
    void testSubtraction_CrossUnit_InchesMinusFeet() {
        assertEquals(new Quantity<>(60.0, LengthUnit.INCH),
                new Quantity<>(120.0, LengthUnit.INCH)
                        .subtract(new Quantity<>(5.0, LengthUnit.FEET)));
    }

    @Test
    void testSubtraction_ExplicitTargetUnit_Feet() {
        assertEquals(new Quantity<>(9.5, LengthUnit.FEET),
                new Quantity<>(10.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(6.0, LengthUnit.INCH), LengthUnit.FEET));
    }

    @Test
    void testSubtraction_ExplicitTargetUnit_Inches() {
        assertEquals(new Quantity<>(114.0, LengthUnit.INCH),
                new Quantity<>(10.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(6.0, LengthUnit.INCH), LengthUnit.INCH));
    }

    @Test
    void testSubtraction_ExplicitTargetUnit_Millilitre() {
        assertEquals(new Quantity<>(3000.0, VolumeUnit.MILLILITRE),
                new Quantity<>(5.0, VolumeUnit.LITRE)
                        .subtract(new Quantity<>(2.0, VolumeUnit.LITRE), VolumeUnit.MILLILITRE));
    }

    @Test
    void testSubtraction_ResultingInNegative() {
        assertEquals(new Quantity<>(-5.0, LengthUnit.FEET),
                new Quantity<>(5.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(10.0, LengthUnit.FEET)));
    }

    @Test
    void testSubtraction_ResultingInZero() {
        assertEquals(new Quantity<>(0.0, LengthUnit.FEET),
                new Quantity<>(10.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(120.0, LengthUnit.INCH)));
    }

    @Test
    void testSubtraction_WithZeroOperand() {
        assertEquals(new Quantity<>(5.0, LengthUnit.FEET),
                new Quantity<>(5.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(0.0, LengthUnit.INCH)));
    }

    @Test
    void testSubtraction_WithNegativeValues() {
        assertEquals(new Quantity<>(7.0, LengthUnit.FEET),
                new Quantity<>(5.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(-2.0, LengthUnit.FEET)));
    }

    @Test
    void testSubtraction_NonCommutative() {
        Quantity<LengthUnit> A = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> B = new Quantity<>(5.0, LengthUnit.FEET);
        assertNotEquals(A.subtract(B), B.subtract(A));
    }

    @Test
    void testSubtraction_WithLargeValues() {
        assertEquals(new Quantity<>(500000.0, WeightUnit.KILOGRAM),
                new Quantity<>(1e6, WeightUnit.KILOGRAM)
                        .subtract(new Quantity<>(5e5, WeightUnit.KILOGRAM)));
    }

    @Test
    void testSubtraction_NullOperand() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(10.0, LengthUnit.FEET).subtract(null));
    }

    @Test
    void testSubtraction_AllMeasurementCategories() {
        assertNotNull(new Quantity<>(5.0, LengthUnit.FEET).subtract(new Quantity<>(2.0, LengthUnit.FEET)));
        assertNotNull(new Quantity<>(5.0, WeightUnit.KILOGRAM).subtract(new Quantity<>(2.0, WeightUnit.KILOGRAM)));
        assertNotNull(new Quantity<>(5.0, VolumeUnit.LITRE).subtract(new Quantity<>(2.0, VolumeUnit.LITRE)));
    }

    @Test
    void testSubtraction_ChainedOperations() {
        assertEquals(new Quantity<>(7.0, LengthUnit.FEET),
                new Quantity<>(10.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(2.0, LengthUnit.FEET))
                        .subtract(new Quantity<>(1.0, LengthUnit.FEET)));
    }

    // =========================================================================
    // DIVISION
    // =========================================================================

    @Test // was missing @Test in original
    void testDivision_SameUnit_FeetDividedByFeet() {
        assertEquals(5.0,
                new Quantity<>(10.0, LengthUnit.FEET)
                        .divide(new Quantity<>(2.0, LengthUnit.FEET)), EPSILON);
    }

    @Test
    void testDivision_SameUnit_LitreDividedByLitre() {
        assertEquals(2.0,
                new Quantity<>(10.0, VolumeUnit.LITRE)
                        .divide(new Quantity<>(5.0, VolumeUnit.LITRE)), EPSILON);
    }

    @Test
    void testDivision_CrossUnit_FeetDividedByInches() {
        assertEquals(1.0,
                new Quantity<>(24.0, LengthUnit.INCH)
                        .divide(new Quantity<>(2.0, LengthUnit.FEET)), EPSILON);
    }

    @Test
    void testDivision_CrossUnit_KilogramDividedByGram() {
        assertEquals(1.0,
                new Quantity<>(2.0, WeightUnit.KILOGRAM)
                        .divide(new Quantity<>(2000.0, WeightUnit.GRAM)), EPSILON);
    }

    @Test
    void testDivision_RatioGreaterThanOne() {
        assertEquals(5.0,
                new Quantity<>(10.0, LengthUnit.FEET)
                        .divide(new Quantity<>(2.0, LengthUnit.FEET)), EPSILON);
    }

    @Test
    void testDivision_RatioLessThanOne() {
        assertEquals(0.5,
                new Quantity<>(5.0, LengthUnit.FEET)
                        .divide(new Quantity<>(10.0, LengthUnit.FEET)), EPSILON);
    }

    @Test
    void testDivision_RatioEqualToOne() {
        assertEquals(1.0,
                new Quantity<>(10.0, LengthUnit.FEET)
                        .divide(new Quantity<>(10.0, LengthUnit.FEET)), EPSILON);
    }

    @Test
    void testDivision_NonCommutative() {
        Quantity<LengthUnit> A = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> B = new Quantity<>(5.0, LengthUnit.FEET);
        assertNotEquals(A.divide(B), B.divide(A));
    }

    @Test
    void testDivision_WithLargeRatio() {
        assertEquals(1e6,
                new Quantity<>(1e6, WeightUnit.KILOGRAM)
                        .divide(new Quantity<>(1.0, WeightUnit.KILOGRAM)), EPSILON);
    }

    @Test
    void testDivision_WithSmallRatio() {
        assertEquals(1e-6,
                new Quantity<>(1.0, WeightUnit.KILOGRAM)
                        .divide(new Quantity<>(1e6, WeightUnit.KILOGRAM)), EPSILON);
    }

    @Test
    void testDivision_NullOperand() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(10.0, LengthUnit.FEET).divide(null));
    }

    @Test
    void testDivision_AllMeasurementCategories() {
        assertNotNull(new Quantity<>(5.0, LengthUnit.FEET).divide(new Quantity<>(2.0, LengthUnit.FEET)));
        assertNotNull(new Quantity<>(5.0, WeightUnit.KILOGRAM).divide(new Quantity<>(2.0, WeightUnit.KILOGRAM)));
        assertNotNull(new Quantity<>(5.0, VolumeUnit.LITRE).divide(new Quantity<>(2.0, VolumeUnit.LITRE)));
    }

    @Test
    void testDivision_NonAssociative() {
        // Division is NOT associative: (A/B)/C != A/(B/C)
        Quantity<LengthUnit> A = new Quantity<>(20.0, LengthUnit.FEET);
        Quantity<LengthUnit> B = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> C = new Quantity<>(2.0, LengthUnit.FEET);
        double left  = A.divide(B) / C.getValue();
        double right = A.getValue() / B.divide(C);
        assertNotEquals(left, right);
    }

    // =========================================================================
    // INTEGRATION — Subtraction + Division
    // =========================================================================

    @Test
    void testSubtractionAndDivision_Integration() {
        Quantity<LengthUnit> result = new Quantity<>(10.0, LengthUnit.FEET)
                .subtract(new Quantity<>(2.0, LengthUnit.FEET));
        assertEquals(4.0, result.divide(new Quantity<>(2.0, LengthUnit.FEET)), EPSILON);
    }

    @Test
    void testSubtractionAddition_Inverse() {
        Quantity<LengthUnit> A = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> B = new Quantity<>(5.0, LengthUnit.FEET);
        assertEquals(A, A.add(B).subtract(B));
    }

    @Test
    void testSubtraction_Immutability() {
        Quantity<LengthUnit> A = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> B = new Quantity<>(5.0, LengthUnit.FEET);
        A.subtract(B);
        assertEquals(10.0, A.getValue(), EPSILON);
    }

    @Test
    void testDivision_Immutability() {
        Quantity<LengthUnit> A = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> B = new Quantity<>(5.0, LengthUnit.FEET);
        A.divide(B);
        assertEquals(10.0, A.getValue(), EPSILON);
    }

    @Test
    void testSubtraction_PrecisionAndRounding() {
        Quantity<LengthUnit> result = new Quantity<>(10.555, LengthUnit.FEET)
                .subtract(new Quantity<>(0.555, LengthUnit.FEET));
        assertEquals(10.0, result.getValue(), EPSILON);
    }

    @Test
    void testDivision_PrecisionHandling() {
        assertEquals(3.33,
                new Quantity<>(10.0, LengthUnit.FEET)
                        .divide(new Quantity<>(3.0, LengthUnit.FEET)), 0.01);
    }

    // =========================================================================
    // REFACTORING / DELEGATION checks
    // =========================================================================

    @Test
    void testRefactoring_Add_DelegatesViaHelper() {
        Quantity<LengthUnit> q1 = new Quantity<>(10, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(5, LengthUnit.FEET);
        Quantity<LengthUnit> result = q1.add(q2);
        assertEquals(15.0, result.getValue());
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testRefactoring_Subtract_DelegatesViaHelper() {
        assertEquals(5.0,
                new Quantity<>(10, LengthUnit.FEET)
                        .subtract(new Quantity<>(5, LengthUnit.FEET)).getValue());
    }

    @Test
    void testRefactoring_Divide_DelegatesViaHelper() {
        assertEquals(2.0,
                new Quantity<>(10, LengthUnit.FEET)
                        .divide(new Quantity<>(5, LengthUnit.FEET)));
    }

    // =========================================================================
    // VALIDATION — consistent behaviour across all operations
    // =========================================================================

    @Test
    void testValidation_NullOperand_ConsistentAcrossOperations() {
        Quantity<LengthUnit> q = new Quantity<>(10, LengthUnit.FEET);
        Exception addEx = assertThrows(IllegalArgumentException.class, () -> q.add(null));
        Exception subEx = assertThrows(IllegalArgumentException.class, () -> q.subtract(null));
        Exception divEx = assertThrows(IllegalArgumentException.class, () -> q.divide(null));
        assertEquals(addEx.getMessage(), subEx.getMessage());
        assertEquals(addEx.getMessage(), divEx.getMessage());
    }

    @Test
    @SuppressWarnings("unchecked")
    void testValidation_CrossCategory_ConsistentAcrossOperations() {
        Quantity<LengthUnit> length = new Quantity<>(10, LengthUnit.FEET);
        Quantity<WeightUnit> weight = new Quantity<>(5, WeightUnit.KILOGRAM);
        Exception addEx = assertThrows(IllegalArgumentException.class, () -> length.add((Quantity) weight));
        Exception subEx = assertThrows(IllegalArgumentException.class, () -> length.subtract((Quantity) weight));
        Exception divEx = assertThrows(IllegalArgumentException.class, () -> length.divide((Quantity) weight));
        assertEquals(addEx.getMessage(), subEx.getMessage());
        assertEquals(addEx.getMessage(), divEx.getMessage());
    }

    @Test
    void testValidation_FiniteValue_ConsistentAcrossOperations() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(Double.NaN, LengthUnit.FEET));
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(Double.POSITIVE_INFINITY, LengthUnit.FEET));
    }

    @Test
    void testValidation_NullTargetUnit_AddSubtractReject() {
        Quantity<LengthUnit> q1 = new Quantity<>(10, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(5, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () -> q1.add(q2, null));
        assertThrows(IllegalArgumentException.class, () -> q1.subtract(q2, null));
    }

    // =========================================================================
    // UC-12 behaviour preserved
    // =========================================================================

    @Test
    void testAdd_UC12_BehaviorPreserved() {
        assertEquals(2.0,
                new Quantity<>(1, LengthUnit.FEET)
                        .add(new Quantity<>(12, LengthUnit.INCH)).getValue());
    }

    @Test
    void testSubtract_UC12_BehaviorPreserved() {
        assertEquals(9.5,
                new Quantity<>(10, LengthUnit.FEET)
                        .subtract(new Quantity<>(6, LengthUnit.INCH)).getValue());
    }

    @Test
    void testDivide_UC12_BehaviorPreserved() {
        assertEquals(1.0,
                new Quantity<>(24, LengthUnit.INCH)
                        .divide(new Quantity<>(2, LengthUnit.FEET)));
    }

    // =========================================================================
    // Rounding
    // =========================================================================

    @Test
    void testRounding_AddSubtract_TwoDecimalPlaces() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.2345, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(0.1111, LengthUnit.FEET);
        assertEquals(1.35, q1.add(q2).getValue());
        assertEquals(1.12, q1.subtract(new Quantity<>(0.1145, LengthUnit.FEET)).getValue());
    }

    @Test
    void testRounding_Divide_NoRounding() {
        assertEquals(3.5,
                new Quantity<>(7, LengthUnit.FEET)
                        .divide(new Quantity<>(2, LengthUnit.FEET)));
    }

    // =========================================================================
    // Target unit behaviour
    // =========================================================================

    @Test
    void testImplicitTargetUnit_AddSubtract() {
        assertEquals(LengthUnit.FEET,
                new Quantity<>(1, LengthUnit.FEET)
                        .add(new Quantity<>(12, LengthUnit.INCH)).getUnit());
    }

    @Test
    void testExplicitTargetUnit_AddSubtract_Overrides() {
        Quantity<LengthUnit> result = new Quantity<>(1, LengthUnit.FEET)
                .add(new Quantity<>(12, LengthUnit.INCH), LengthUnit.INCH);
        assertEquals(LengthUnit.INCH, result.getUnit());
        assertEquals(24.0, result.getValue());
    }

    // =========================================================================
    // Immutability
    // =========================================================================

    @Test
    void testImmutability_AfterAdd() {
        Quantity<LengthUnit> q1 = new Quantity<>(5, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(5, LengthUnit.FEET);
        q1.add(q2);
        assertEquals(5.0, q1.getValue());
        assertEquals(5.0, q2.getValue());
    }

    @Test
    void testImmutability_AfterSubtract() {
        Quantity<LengthUnit> q1 = new Quantity<>(5, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(3, LengthUnit.FEET);
        q1.subtract(q2);
        assertEquals(5.0, q1.getValue());
        assertEquals(3.0, q2.getValue());
    }

    @Test
    void testImmutability_AfterDivide() {
        Quantity<LengthUnit> q1 = new Quantity<>(10, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(2, LengthUnit.FEET);
        q1.divide(q2);
        assertEquals(10.0, q1.getValue());
        assertEquals(2.0, q2.getValue());
    }

    // =========================================================================
    // All categories at once
    // =========================================================================

    @Test
    void testAllOperations_AcrossAllCategories() {
        assertEquals(12.0, new Quantity<>(10, LengthUnit.FEET).add(new Quantity<>(2, LengthUnit.FEET)).getValue());
        assertEquals(2.0,  new Quantity<>(1, WeightUnit.KILOGRAM).add(new Quantity<>(1000, WeightUnit.GRAM)).getValue());
        assertEquals(2.0,  new Quantity<>(1, VolumeUnit.LITRE).add(new Quantity<>(1000, VolumeUnit.MILLILITRE)).getValue());
    }

    @Test
    void testHelper_BaseUnitConversion_Correct() {
        assertEquals(2.0,
                new Quantity<>(12, LengthUnit.INCH)
                        .add(new Quantity<>(12, LengthUnit.INCH), LengthUnit.FEET).getValue());
    }

    @Test
    void testArithmetic_Chain_Operations() {
        double result = new Quantity<>(10, LengthUnit.FEET)
                .add(new Quantity<>(2, LengthUnit.FEET))
                .subtract(new Quantity<>(1, LengthUnit.FEET))
                .divide(new Quantity<>(1, LengthUnit.FEET));
        assertEquals(11.0, result);
    }

    // =========================================================================
    // TEMPERATURE — Equality
    // =========================================================================

    @Test
    void testTemperatureEquality_CelsiusToCelsius_SameValue() {
        assertEquals(new Quantity<>(0.0, TemperatureUnit.CELSIUS),
                     new Quantity<>(0.0, TemperatureUnit.CELSIUS));
    }

    @Test
    void testTemperatureEquality_FahrenheitToFahrenheit_SameValue() {
        assertEquals(new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT),
                     new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT));
    }

    @Test
    void testTemperatureEquality_ReflexiveProperty() {
        Quantity<TemperatureUnit> temp = new Quantity<>(50.0, TemperatureUnit.CELSIUS);
        assertEquals(temp, temp);
    }

    @Test
    void testTemperatureDifferentValuesInequality() {
        assertNotEquals(new Quantity<>(50, TemperatureUnit.CELSIUS),
                        new Quantity<>(100, TemperatureUnit.CELSIUS));
    }

    @Test
    void testTemperatureConversionEdgeCase_VerySmallDifference() {
        assertTrue(new Quantity<>(25.00001, TemperatureUnit.CELSIUS)
                .equals(new Quantity<>(25.00002, TemperatureUnit.CELSIUS)));
    }

    // =========================================================================
    // TEMPERATURE — Conversion
    // =========================================================================

    @Test
    void testTemperatureConversion_CelsiusToFahrenheit_VariousValues() {
        assertEquals(122.0,
                new Quantity<>(50, TemperatureUnit.CELSIUS)
                        .convertTo(TemperatureUnit.FAHRENHEIT).getValue(), EPSILON);
        assertEquals(-4.0,
                new Quantity<>(-20, TemperatureUnit.CELSIUS)
                        .convertTo(TemperatureUnit.FAHRENHEIT).getValue(), EPSILON);
    }

    @Test
    void testTemperatureConversion_FahrenheitToCelsius_VariousValues() {
        assertEquals(50.0,
                new Quantity<>(122, TemperatureUnit.FAHRENHEIT)
                        .convertTo(TemperatureUnit.CELSIUS).getValue(), EPSILON);
        assertEquals(-20.0,
                new Quantity<>(-4, TemperatureUnit.FAHRENHEIT)
                        .convertTo(TemperatureUnit.CELSIUS).getValue(), EPSILON);
    }

    @Test
    void testTemperatureConversion_RoundTrip_PreservesValue() {
        double roundTrip = new Quantity<>(75, TemperatureUnit.CELSIUS)
                .convertTo(TemperatureUnit.FAHRENHEIT)
                .convertTo(TemperatureUnit.CELSIUS)
                .getValue();
        assertEquals(75.0, roundTrip, EPSILON);
    }

    @Test
    void testTemperatureConversion_SameUnit() {
        assertEquals(25.0,
                new Quantity<>(25, TemperatureUnit.CELSIUS)
                        .convertTo(TemperatureUnit.CELSIUS).getValue(), EPSILON);
    }

    @Test
    void testTemperatureConversion_ZeroValue() {
        assertEquals(32.0,
                new Quantity<>(0, TemperatureUnit.CELSIUS)
                        .convertTo(TemperatureUnit.FAHRENHEIT).getValue(), EPSILON);
    }

    @Test
    void testTemperatureConversion_NegativeValues() {
        assertEquals(-40.0,
                new Quantity<>(-40, TemperatureUnit.CELSIUS)
                        .convertTo(TemperatureUnit.FAHRENHEIT).getValue(), EPSILON);
    }

    @Test
    void testTemperatureConversion_LargeValues() {
        assertEquals(1832.0,
                new Quantity<>(1000, TemperatureUnit.CELSIUS)
                        .convertTo(TemperatureUnit.FAHRENHEIT).getValue(), EPSILON);
    }

    @Test
    void testTemperatureUnit_NonLinearConversion() {
        double result = new Quantity<>(100, TemperatureUnit.CELSIUS)
                .convertTo(TemperatureUnit.FAHRENHEIT).getValue();
        assertNotEquals(100 * 1.8, result);
        assertEquals(212.0, result, EPSILON);
    }

    // =========================================================================
    // TEMPERATURE — Unsupported arithmetic
    // =========================================================================

    @Test
    void testTemperatureUnsupportedOperation_Add() {
        assertThrows(UnsupportedOperationException.class,
                () -> new Quantity<>(100, TemperatureUnit.CELSIUS)
                        .add(new Quantity<>(50, TemperatureUnit.CELSIUS)));
    }

    @Test
    void testTemperatureUnsupportedOperation_Subtract() {
        assertThrows(UnsupportedOperationException.class,
                () -> new Quantity<>(100, TemperatureUnit.CELSIUS)
                        .subtract(new Quantity<>(50, TemperatureUnit.CELSIUS)));
    }

    @Test
    void testTemperatureUnsupportedOperation_Divide() {
        assertThrows(UnsupportedOperationException.class,
                () -> new Quantity<>(100, TemperatureUnit.CELSIUS)
                        .divide(new Quantity<>(50, TemperatureUnit.CELSIUS)));
    }

    @Test
    void testTemperatureUnsupportedOperation_ErrorMessage() {
        UnsupportedOperationException ex = assertThrows(UnsupportedOperationException.class,
                () -> new Quantity<>(100, TemperatureUnit.CELSIUS)
                        .add(new Quantity<>(50, TemperatureUnit.CELSIUS)));
        assertTrue(ex.getMessage().contains("Temperature"));
    }

    @Test
    void testTemperatureValidateOperationSupport_MethodBehavior() {
        assertThrows(UnsupportedOperationException.class,
                () -> TemperatureUnit.CELSIUS.validateOperationSupport("ADD"));
    }

    // =========================================================================
    // TEMPERATURE — Cross-category incompatibility
    // =========================================================================

    @Test
    void testTemperatureVsLengthIncompatibility() {
        assertFalse(new Quantity<>(100.0, TemperatureUnit.CELSIUS)
                .equals(new Quantity<>(100.0, LengthUnit.FEET)));
    }

    @Test
    void testTemperatureVsWeightIncompatibility() {
        assertFalse(new Quantity<>(50.0, TemperatureUnit.CELSIUS)
                .equals(new Quantity<>(50.0, WeightUnit.KILOGRAM)));
    }

    @Test
    void testTemperatureVsVolumeIncompatibility() {
        assertFalse(new Quantity<>(25.0, TemperatureUnit.CELSIUS)
                .equals(new Quantity<>(25.0, VolumeUnit.LITRE)));
    }

    // =========================================================================
    // TEMPERATURE — supportsArithmetic / interface checks
    // =========================================================================

    @Test
    void testOperationSupportMethods_TemperatureUnitAddition() {
        assertFalse(TemperatureUnit.CELSIUS.supportsArithmetic());
    }

    @Test
    void testOperationSupportMethods_TemperatureUnitDivision() {
        assertFalse(TemperatureUnit.FAHRENHEIT.supportsArithmetic());
    }

    @Test
    void testOperationSupportMethods_LengthUnitAddition() {
        assertTrue(LengthUnit.FEET.supportsArithmetic());
    }

    @Test
    void testOperationSupportMethods_WeightUnitDivision() {
        assertTrue(WeightUnit.KILOGRAM.supportsArithmetic());
    }

    @Test
    void testTemperatureDefaultMethodInheritance() {
        assertTrue(LengthUnit.FEET.supportsArithmetic());
        assertTrue(VolumeUnit.LITRE.supportsArithmetic());
    }

    @Test
    void testTemperatureEnumImplementsIMeasurable() {
        assertTrue(IMeasurable.class.isAssignableFrom(TemperatureUnit.class));
    }

    @Test
    void testTemperatureUnit_AllConstants() {
        assertNotNull(TemperatureUnit.CELSIUS);
        assertNotNull(TemperatureUnit.FAHRENHEIT);
    }

    @Test
    void testTemperatureUnit_NameMethod() {
        assertEquals("CELSIUS",    TemperatureUnit.CELSIUS.name());
        assertEquals("FAHRENHEIT", TemperatureUnit.FAHRENHEIT.name());
    }

    @Test
    void testTemperatureUnit_ConversionFactor() {
        assertEquals(0.0, TemperatureUnit.CELSIUS.convertToBaseUnit(0), EPSILON);
    }

    @Test
    void testTemperatureNullUnitValidation() {
        assertThrows(IllegalArgumentException.class, () -> new Quantity<>(100.0, null));
    }

    @Test
    void testTemperatureNullOperandValidation_InComparison() {
        assertFalse(new Quantity<>(100, TemperatureUnit.CELSIUS).equals(null));
    }

    @Test
    void testTemperatureIntegrationWithGenericQuantity() {
        Quantity<TemperatureUnit> temp = new Quantity<>(25, TemperatureUnit.CELSIUS);
        assertEquals(25.0, temp.getValue(), EPSILON);
        assertEquals(TemperatureUnit.CELSIUS, temp.getUnit());
    }

    @Test
    void testTemperatureBackwardCompatibility_UC1_Through_UC13() {
        assertEquals(2.0,
                new Quantity<>(10, WeightUnit.KILOGRAM)
                        .divide(new Quantity<>(5, WeightUnit.KILOGRAM)), EPSILON);
    }

    @Test
    void testIMeasurableInterface_Evolution_BackwardCompatible() {
        assertEquals(15.0,
                new Quantity<>(10, LengthUnit.FEET)
                        .add(new Quantity<>(5, LengthUnit.FEET)).getValue(), EPSILON);
    }
}