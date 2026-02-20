package com.app.quantitymeasurement;


import java.util.Objects;


public class QuantityMeasurementApp {

    /**
     * Inner class to represent Feet measurement
     */
    

    /**
     * Main method to demonstrate equality
     */
    public static void main(String[] args) {

        Feet feet1 = new Feet(1.0);
        Feet feet2 = new Feet(2.0);

        boolean result = feet1.equals(feet2);

        System.out.println("Are both measurements equal? " + result);
    }
}