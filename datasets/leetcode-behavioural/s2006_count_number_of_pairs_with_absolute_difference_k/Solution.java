package g2001_2100.s2006_count_number_of_pairs_with_absolute_difference_k;

// #Easy #Array #Hash_Table #Counting #2022_05_23_Time_8_ms_(58.60%)_Space_42.2_MB_(74.50%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is greater than or equal to 2.*);
//@ ensures(*The value of `k` is greater than or equal to 1.*);
//@ ensures(*The value of `k` is less than or equal to 99.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the number of pairs `(i, j)` where `i < j` and `|nums[i] - nums[j]| == k`.*);
//@ ensures(*The method does not modify the input array `nums`.*);
//@ ensures(*The method does not modify the value of `k`.*);
    public int countKDifference(int[] nums, int k) {
        int pairs = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (Math.abs(nums[i] - nums[j]) == k) {
                    pairs++;
                }
            }
        }
        return pairs;
    }
}