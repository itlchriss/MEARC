package g2301_2400.s2303_calculate_amount_paid_in_taxes;

// #Easy #Array #Simulation #2022_06_16_Time_1_ms_(92.41%)_Space_45.9_MB_(63.30%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input brackets array is not null.*);
//@ ensures(*The brackets array is not empty.*);
//@ ensures(*The brackets array is sorted in ascending order by the upper bound.*);
//@ ensures(*The upper bounds in the brackets array are unique.*);
//@ ensures(*The upper bound of the last tax bracket is greater than or equal to the income.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns a double value representing the amount of money to be paid in taxes.*);
//@ ensures(*The returned value is within 10^-5 of the actual answer.*);
    public double calculateTax(int[][] brackets, int income) {
        // you can remove this line
        if (income == 0) {
            return 0;
        }
        double sum = 0;
        double prev = 0;
        for (int[] bracket : brackets) {
            double salary = Math.min(bracket[0], income);
            double tax = bracket[1];
            sum += (salary - prev) * tax;
            prev = salary;
        }
        return sum / 100;
    }
}