package g1401_1500.s1402_reducing_dishes;

// #Hard #Array #Dynamic_Programming #Sorting #Greedy
// #2022_03_25_Time_3_ms_(66.20%)_Space_41.3_MB_(80.80%)

import java.util.Arrays;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `satisfaction` is not null.*);
//@ ensures(*The length of the input array `satisfaction` is greater than or equal to - The length of the input array `satisfaction` is less than or equal to - Each element in the input array `satisfaction` is an integer.*);
//@ ensures(*Each element in the input array `satisfaction` is greater than or equal to -- Each element in the input array `satisfaction` is less than or equal to *);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the maximum sum of like-time coefficients.*);
//@ ensures(*The maximum sum of like-time coefficients is calculated based on the given formula: `time[i] * satisfaction[i]`.*);
//@ ensures(*The dishes can be prepared in any order.*);
//@ ensures(*The chef can discard some dishes to maximize the sum of like-time coefficients.*);
//@ ensures(*If the input array `satisfaction` is empty, the method returns 0.*);
    public int maxSatisfaction(int[] satisfaction) {
        Arrays.sort(satisfaction);
        int sum = 0;
        int mulSum = 0;
        for (int i = 0; i < satisfaction.length; i++) {
            sum += satisfaction[i];
            mulSum += (i + 1) * satisfaction[i];
        }
        int maxVal = Math.max(0, mulSum);
        for (int j : satisfaction) {
            mulSum -= sum;
            sum -= j;
            maxVal = Math.max(maxVal, mulSum);
        }
        return maxVal;
    }
}