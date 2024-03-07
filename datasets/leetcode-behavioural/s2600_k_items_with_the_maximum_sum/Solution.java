package g2501_2600.s2600_k_items_with_the_maximum_sum;

// #Easy #Math #Greedy #2023_08_29_Time_1_ms_(100.00%)_Space_40.3_MB_(19.10%)

@SuppressWarnings("java:S1172")
public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input integers `numOnes`, `numZeros`, `numNegOnes`, and `k` must be non-negative.*);
//@ ensures(*The sum of `numOnes`, `numZeros`, and `numNegOnes` must be greater than or equal to `k`.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the maximum possible sum of numbers written on the selected items.*);
//@ ensures(*The selected items must be chosen from the available items in the bag.*);
//@ ensures(*The number of selected items must be equal to `k`.*);
//@ ensures(*The selected items must have the highest possible sum of numbers written on them.*);
    public int kItemsWithMaximumSum(int numOnes, int numZeros, int numNegOnes, int k) {
        if (k <= numOnes) {
            return k;
        }
        if (k <= numOnes + numZeros) {
            return numOnes;
        }
        int remainingSum = k - (numOnes + numZeros);
        return numOnes - remainingSum;
    }
}