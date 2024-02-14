package g0901_1000.s0996_number_of_squareful_arrays;

// #Hard #Array #Dynamic_Programming #Math #Bit_Manipulation #Backtracking #Bitmask
// #2022_03_31_Time_2_ms_(72.32%)_Space_41.9_MB_(32.14%)

import java.util.HashSet;
import java.util.Set;

public class Solution {
    int count;
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is greater than or equal to 1 and less than or equal to 12.*);
	//@ requires(*Each element in the input array `nums` is a non-negative integer.*);
	//@ ensures(*The method returns an integer representing the number of permutations of `nums` that are squareful.*);
	//@ ensures(*The returned integer is greater than or equal to 0.*);

    public int numSquarefulPerms(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return count;
        }
        backtrack(nums, n, 0);
        return count;
    }

    private void backtrack(int[] nums, int n, int start) {
        if (start == n) {
            count++;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = start; i < n; i++) {
            if (set.contains(nums[i])) {
                continue;
            }
            swap(nums, start, i);
            if (start == 0 || isPerfectSq(nums[start], nums[start - 1])) {
                backtrack(nums, n, start + 1);
            }
            swap(nums, start, i);
            set.add(nums[i]);
        }
    }

    private void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    private boolean isPerfectSq(int a, int b) {
        int x = a + b;
        double sqrt = Math.sqrt(x);
        return sqrt - (int) sqrt == 0;
    }
}