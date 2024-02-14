package g2801_2900.s2856_minimum_array_length_after_pair_removals;

// #Medium #Array #Hash_Table #Greedy #Binary_Search #Two_Pointers #Counting
// #2023_12_15_Time_5_ms_(97.63%)_Space_59.1_MB_(36.50%)

import java.util.List;

public class Solution {
	//@ requires(*The input list `nums` is not null.*);
	//@ requires(*The input list `nums` is sorted in non-decreasing order.*);
	//@ ensures(*The length of the modified list `nums` is minimized after performing the operation any number of times.*);
	//@ ensures(*The modified list `nums` is sorted in non-decreasing order.*);
	//@ ensures(*The modified list `nums` does not contain any pair of elements where the first element is greater than the second element.*);
    public int minLengthAfterRemovals(List<Integer> nums) {
        int n = nums.size();
        int i = 0;
        int j;
        if (n % 2 == 0) {
            j = n / 2;
        } else {
            j = n / 2 + 1;
        }
        int count = 0;
        while (i < n / 2 && j < n) {
            if (nums.get(i) < nums.get(j)) {
                count += 2;
            }
            i++;
            j++;
        }
        return n - count;
    }
}