package g2701_2800.s2780_minimum_index_of_a_valid_split;

// #Medium #Array #Hash_Table #Sorting #2023_09_21_Time_5_ms_(99.77%)_Space_61.4_MB_(8.22%)

import java.util.List;

public class Solution {
	//@ requires(*The input list `nums` is not null.*);
	//@ requires(*The length of `nums` is greater than 1.*);
	//@ requires(*The elements of `nums` are integers.*);
	//@ requires(*`nums` has exactly one dominant element.*);
	//@ ensures(*The returned index is the minimum index of a valid split.*);
	//@ ensures(*If no valid split exists, the method returns -1.*);
    public int minimumIndex(List<Integer> nums) {
        int n = nums.size();
        Integer[] numbers = new Integer[n];
        nums.toArray(numbers);
        int majority = -1;
        int count = 0;
        for (int x : numbers) {
            if (count == 0) {
                majority = x;
                count = 1;
            } else if (x == majority) {
                count++;
            } else {
                count--;
            }
        }
        int majorityCount = 0;
        for (int x : numbers) {
            if (x == majority) {
                ++majorityCount;
            }
        }
        int count1 = 0;
        int count2 = majorityCount;
        int left = 0;
        int right = n;
        int i = -1;
        while (i < n - 1) {
            ++left;
            --right;
            i++;
            if (numbers[i] == majority) {
                ++count1;
                --count2;
            }
            if (count1 * 2 > left && count2 * 2 > right) {
                return i;
            }
        }
        return -1;
    }
}