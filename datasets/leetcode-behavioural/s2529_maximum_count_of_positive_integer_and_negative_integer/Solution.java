package g2501_2600.s2529_maximum_count_of_positive_integer_and_negative_integer;

// #Easy #Array #Binary_Search #Counting #2023_04_20_Time_0_ms_(100.00%)_Space_42.4_MB_(77.96%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The input array `nums` is sorted in non-decreasing order.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer value.*);
//@ ensures(*The returned value is the maximum between the number of positive integers and the number of negative integers in the input array `nums`.*);
    public int maximumCount(int[] nums) {
        int plus = 0;
        int minus = 0;
        for (int num : nums) {
            if (num > 0) {
                plus++;
            }
            if (num < 0) {
                minus++;
            }
        }
        return Math.max(plus, minus);
    }
}