package g2601_2700.s2651_calculate_delayed_arrival_time;

// #Easy #Math #2023_09_06_Time_0_ms_(100.00%)_Space_39.6_MB_(47.38%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The arrivalTime parameter must be a positive integer.*);
//@ ensures(*The delayedTime parameter must be a positive integer.*);
//@ ensures(*The arrivalTime parameter must be less than 24.*);
//@ ensures(*The delayedTime parameter must be less than or equal to 24.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the delayed arrival time.*);
//@ ensures(*The delayed arrival time is calculated by adding the arrivalTime and delayedTime parameters.*);
//@ ensures(*If the delayed arrival time is greater than or equal to 24, the method returns 0.*);
    public int findDelayedArrivalTime(int ar, int de) {
        if (ar + de >= 24) {
            return Math.abs(ar + de - 24);
        }
        return ar + de;
    }
}