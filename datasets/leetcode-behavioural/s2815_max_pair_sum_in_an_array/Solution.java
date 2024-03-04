package g2801_2900.s2815_max_pair_sum_in_an_array;

// #Easy #Array #Hash_Table #2023_11_20_Time_8_ms_(67.30%)_Space_43.9_MB_(23.59%)

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is at least 2.*);
//@ ensures(*The elements in the input array `nums` are positive integers.*);
//@ ensures(*The maximum value of the elements in the input array `nums` is at most 10^4.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the maximum sum of a pair of numbers from `nums` such that the maximum digit in both numbers are equal.*);
//@ ensures(*If no such pair exists, the method returns -1.*);
    public int maxSum(int[] nums) {
        // what we'll return
        int maxSum = -1;
        Map<Integer, PriorityQueue<Integer>> maximumDigitToNumber = new HashMap<>();
        for (int i = 1; i <= 9; ++i) {
            maximumDigitToNumber.put(i, new PriorityQueue<>(Comparator.reverseOrder()));
        }
        for (int n : nums) {
            maximumDigitToNumber.get(getMaximumDigit(n)).add(n);
        }
        for (Map.Entry<Integer, PriorityQueue<Integer>> me : maximumDigitToNumber.entrySet()) {
            if (me.getValue().size() <= 1) {
                continue;
            }
            int sum = me.getValue().poll() + me.getValue().poll();
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum;
    }

    private int getMaximumDigit(int n) {
        int maxDigit = 1;
        for (int nMod10 = n % 10; n > 0; n /= 10, nMod10 = n % 10) {
            maxDigit = Math.max(maxDigit, nMod10);
        }
        return maxDigit;
    }
}