package g1201_1300.s1227_airplane_seat_assignment_probability;

// #Medium #Dynamic_Programming #Math #Brainteaser #Probability_and_Statistics
// #2022_03_12_Time_1_ms_(15.63%)_Space_41.7_MB_(12.95%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input `n` is a positive integer greater than or equal to 1.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns a double value representing the probability that the `n`th person gets their own seat.*);
//@ ensures(*The returned probability is between 0 and 1, inclusive.*);
    public double nthPersonGetsNthSeat(int n) {
        if (n == 1) {
            return 1.0D;
        }
        return 0.5D;
    }
}