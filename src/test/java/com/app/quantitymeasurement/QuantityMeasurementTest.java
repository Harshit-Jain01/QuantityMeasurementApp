package com.app.quantitymeasurement;


import java.util.Objects;


 class QuantityMeasurementAppTest{

   
    // Inner class to represent Feet measurement
     
    public static class Feet {

        private final double value; // Immutable field

        
         //Constructor to initialize feet value
        public Feet(double value) {
            this.value = value;
        }

        public double getValue() {
            return value;
        }

        
         //Override equals() to compare Feet objects
        @Override
        public boolean equals(Object obj) {

            // 1. Reference check (Reflexive property)
            if (this == obj) {
                return true;
            }

            // 2. Null check
            if (obj == null) {
                return false;
            }

            // 3. Type check
            if (getClass() != obj.getClass()) {
                return false;
            }

            // 4. Safe casting
            Feet other = (Feet) obj;

            // 5. Compare double values properly
            return Double.compare(this.value, other.value) == 0;
        }

        
        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }

    
     //Main method to demonstrate equality
    public static void main(String[] args) {

        Feet feet1 = new Feet(1.0);
        Feet feet2 = new Feet(1.0);

        boolean result = feet1.equals(feet2);

        System.out.println("Are both measurements equal? " + result);
    }
}