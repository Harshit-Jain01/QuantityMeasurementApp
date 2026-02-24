package com.app.quantitymeasurement;



public class QuantityMeasurementApp {

    public static void main(String[] args) {

        // --- Length ---
        Quantity<LengthUnit> length1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> length2 = new Quantity<>(6.0, LengthUnit.INCH);

        System.out.println("Subtract Length (feet):  " + length1.subtract(length2));
        System.out.println("Subtract Length (inches): " + length1.subtract(length2, LengthUnit.INCH));

        // --- Weight ---
        Quantity<WeightUnit> weight1 = new Quantity<>(10.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> weight2 = new Quantity<>(5.0, WeightUnit.KILOGRAM);

        System.out.println("Division Weight: " + weight1.divide(weight2));

        // --- Volume ---
        Quantity<VolumeUnit> volume1 = new Quantity<>(5.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> volume2 = new Quantity<>(10.0, VolumeUnit.LITRE);

        System.out.println("Division Volume: " + volume1.divide(volume2));

        // --- Temperature ---
        Quantity<TemperatureUnit> t1 = new Quantity<>(100, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> t2 = new Quantity<>(212, TemperatureUnit.FAHRENHEIT);

        System.out.println("100°C == 212°F: " + t1.equals(t2));
        System.out.println("212°F in Celsius: " + t2.convertTo(TemperatureUnit.CELSIUS));

        // Temperature arithmetic is NOT supported — demonstrate graceful error handling
        try {
            t1.add(t2);
        } catch (UnsupportedOperationException e) {
            System.out.println("Expected error: " + e.getMessage());
        }
    }
}