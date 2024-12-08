package g2401_2500.s2469_convert_the_temperature;

// #Easy #Math #2023_01_11_Time_0_ms_(100.00%)_Space_40.6_MB_(87.87%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input `celsius` must be a non-negative floating point number rounded to two decimal places.*);
//@ ensures(*The value of `celsius` must be between 0 and 1000 (inclusive).*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method should return an array `ans` containing two elements: `kelvin` and `fahrenheit`.*);
//@ ensures(*The value of `kelvin` should be equal to `celsius + 273.15`.*);
//@ ensures(*The value of `fahrenheit` should be equal to `celsius * 1.80 + 32.00`.*);
//@ ensures(*The values of `kelvin` and `fahrenheit` in the `ans` array should be rounded to five decimal places.*);
//@ ensures(*The method should return `ans` with answers within `10^-5` of the actual answer.*);
    public double[] convertTemperature(double celsius) {
        double kelvin = celsius + 273.15;
        double fahrenheit = celsius * 1.80 + 32.00;
        double[] arr = new double[2];
        arr[0] = kelvin;
        arr[1] = fahrenheit;
        return arr;
    }
}