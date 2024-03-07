package g1901_2000.s1995_count_special_quadruplets;

// #Easy #Array #Enumeration #2022_05_16_Time_2_ms_(99.20%)_Space_42.8_MB_(30.85%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` must not be null.*);
//@ ensures(*The length of the input array `nums` must be at least 4.*);
//@ ensures(*Each element in the input array `nums` must be between 1 and 100 (inclusive).*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the number of distinct quadruplets that satisfy the given requirements.*);
//@ ensures(*The method does not modify the input array `nums`.*);
    public int countQuadruplets(int[] nums) {
        int count = 0;
        // max nums value is 100 so two elements sum can be max 200
        int[] m = new int[201];
        for (int i = 1; i < nums.length - 2; i++) {
            for (int j = 0; j < i; j++) {
                // update all possible 2 sums
                m[nums[j] + nums[i]]++;
            }
            for (int j = i + 2; j < nums.length; j++) {
                // fix third element and search for fourth - third in 2 sums as a  + b + c = d == a
                // +  b = d - c
                int diff = nums[j] - nums[i + 1];
                if (diff >= 0) {
                    count += m[diff];
                }
            }
        }
        return count;
    }
}