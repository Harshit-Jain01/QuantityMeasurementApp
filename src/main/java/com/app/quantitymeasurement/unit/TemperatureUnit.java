package com.app.quantitymeasurement.unit;

import java.util.function.Function;

/**
 * TemperatureUnit
 *
 * Enumeration of supported temperature measurement units.
 *
 * Implements {@link IMeasurable} but does <em>not</em> implement
 * {@link SupportsArithmetic}, so arithmetic operations (add, subtract) are
 * rejected by the service layer. Conversion between temperature units is
 * fully supported.
 *
 * <p>Temperature conversions are non-linear, so each constant stores two
 * {@link Function} objects rather than a single multiplication factor:</p>
 * <ul>
 *   <li>{@code toBase}   — converts this unit's value to the base unit (CELSIUS).</li>
 *   <li>{@code fromBase} — converts a CELSIUS value to this unit.</li>
 * </ul>
 *
 * <p><b>Base unit:</b> CELSIUS.</p>
 */
public enum TemperatureUnit implements IMeasurable {

    CELSIUS(
        v -> v,                          
        v -> v                          
    ),
    FAHRENHEIT(
        v -> (v - 32.0) * 5.0 / 9.0,   
        v -> v * 9.0 / 5.0 + 32.0       
    ),
    KELVIN(
        v -> v - 273.15,                 
        v -> v + 273.15                 
    );

    /** Converts a value in this unit to the base unit (CELSIUS). */
    private final Function<Double, Double> toBase;

    /** Converts a CELSIUS value to this unit. */
    private final Function<Double, Double> fromBase;

    TemperatureUnit(Function<Double, Double> toBase, Function<Double, Double> fromBase) {
        this.toBase   = toBase;
        this.fromBase = fromBase;
    }

    @Override
    public double convertToBaseUnit(double value) {
        return toBase.apply(value);
    }

  
    @Override
    public double convertFromBaseUnit(double baseValue) {
        return fromBase.apply(baseValue);
    }

    /**
     * Converts {@code value} from this unit to {@code target} in one step.
     *
     * @param value  temperature in this unit
     * @param target desired target unit
     * @return converted temperature value
     */
    public double convertTo(double value, TemperatureUnit target) {
        return target.convertFromBaseUnit(convertToBaseUnit(value));
    }

    /** @return the enum constant name as the unit identifier */
    @Override
    public String getUnitName() { return this.name(); }

    /** @return {@code "TemperatureUnit"} */
    @Override
    public String getMeasurementType() { return this.getClass().getSimpleName(); }

    /**
     * Returns the {@code TemperatureUnit} constant whose name matches {@code unitName}
     * (case-insensitive).
     *
     * @param unitName name of the unit to look up
     * @return matching constant
     * @throws IllegalArgumentException if no match is found
     */
    @Override
    public IMeasurable getUnitInstance(String unitName) {
        for (TemperatureUnit unit : TemperatureUnit.values()) {
            if (unit.getUnitName().equalsIgnoreCase(unitName)) return unit;
        }
        throw new IllegalArgumentException("Invalid temperature unit: " + unitName);
    }
}