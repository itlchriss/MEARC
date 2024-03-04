package g2501_2600.s2525_categorize_box_according_to_criteria;

// #Easy #Math #2023_04_19_Time_0_ms_(100.00%)_Space_40.1_MB_(36.65%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input values `length`, `width`, `height`, and `mass` are integers.*);
//@ ensures(*The values of `length`, `width`, and `height` are between 1 and 10^5 (inclusive).*);
//@ ensures(*The value of `mass` is between 1 and 10^3 (inclusive).*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns a string representing the category of the box.*);
//@ ensures(*If any of the dimensions of the box is greater or equal to 10^4, the category is "Bulky".*);
//@ ensures(*If the volume of the box is greater or equal to 10^9, the category is "Bulky".*);
//@ ensures(*If the mass of the box is greater or equal to 100, the category is "Heavy".*);
//@ ensures(*If the box is both "Bulky" and "Heavy", the category is "Both".*);
//@ ensures(*If the box is neither "Bulky" nor "Heavy", the category is "Neither".*);
//@ ensures(*If the box is "Bulky" but not "Heavy", the category is "Bulky".*);
//@ ensures(*If the box is "Heavy" but not "Bulky", the category is "Heavy".*);
    public String categorizeBox(int length, int width, int height, int mass) {
        long vol = (long) length * width * height;
        boolean b = false;
        boolean h = false;
        if (length >= 10000 || width >= 10000 || height >= 10000 || vol >= 1000000000) {
            b = true;
        }
        if (mass >= 100) {
            h = true;
        }
        if (b && h) {
            return "Both";
        } else if (!b && !h) {
            return "Neither";
        } else if (b) {
            return "Bulky";
        } else {
            return "Heavy";
        }
    }
}