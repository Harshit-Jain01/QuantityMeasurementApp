package com.app.quantitymeasurement;


import java.util.Objects;


public class QuantityMeasurementApp {
	
	 public static boolean checkInchesEquality(double value1, double value2) {
	        Inches i1 = new Inches(value1);
	        Inches i2 = new Inches(value2);
	        return i1.equals(i2);
	    }
	 
    public static void main(String[] args) {
    	
    	//creating objects of feet
        Feet feet1 = new Feet(1.0);
        Feet feet2 = new Feet(2.0);

        boolean result = feet1.equals(feet2);
        
        // checking feet equality
        System.out.println("Are both measurements equal? " + result);
        
        // checking inches equality
        System.out.println("Inches 1.0 & 1.0 Equal? "
                + checkInchesEquality(1.0, 1.0));
    }
    
   
	
}