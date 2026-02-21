package com.app.quantitymeasurement;


import java.util.Objects;


public class QuantityMeasurementApp {
	
	public static boolean checkFeetEquality(double value1, double value2) {
        Feet f1 = new Feet(value1);
        Feet f2 = new Feet(value2);
        return f1.equals(f2);
    }

	 public static boolean checkInchesEquality(double value1, double value2) {
	        Inches i1 = new Inches(value1);
	        Inches i2 = new Inches(value2);
	        return i1.equals(i2);
	    }
	 
    public static void main(String[] args) {
    		
    		//check feet equality
    	  System.out.println("Feet 1.0 & 1.0 Equal? "+ checkFeetEquality(11.0, 11.0));
               
    	  //check inches equality
          System.out.println("Inches 1.0 & 1.0 Equal? " + checkInchesEquality(1.0, 1.0));
                 
    }
    
   
	
}