package g2101_2200.s2122_recover_the_original_array;

// #Hard #Array #Hash_Table #Sorting #Enumeration
// #2022_06_02_Time_19_ms_(89.04%)_Space_54.9_MB_(39.73%)

import java.util.ArrayList;
import java.util.Arrays;

public class Solution {
    private int[] res;
	//@ requires(*The input array `nums` has a length of `2n`, where `n` is a positive integer.*);
	//@ requires(*The integers in `nums` are positive.*);
	//@ requires(*There exists at least one valid array `arr` that can be recovered.*);
	//@ ensures(*The output array `arr` has a length of `n`.*);
	//@ ensures(*The integers in `arr` are positive.*);
	//@ ensures(*The sum of the differences between the corresponding elements in `arr` and `lower` is equal to the sum of the differences between the corresponding elements in `higher` and `arr`.*);
	//@ ensures(*The combination of `lower` and `higher` gives a permutation of `nums`.*);
	//@ ensures(*If there are multiple valid arrays, any one of them can be returned.*);

    public int[] recoverArray(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        ArrayList<Integer> diffs = new ArrayList<>();
        int smallest = nums[0];
        for (int i = 1; i < n; i++) {
            int k = (nums[i] - smallest) / 2;
            if ((nums[i] - smallest) % 2 == 0 && k != 0) {
                diffs.add(k);
            }
        }
        for (int k : diffs) {
            if (check(n, k, nums)) {
                break;
            }
        }
        return res;
    }

    private boolean check(int n, int k, int[] nums) {
        res = new int[n / 2];
        boolean[] visited = new boolean[n];
        int lower = 0;
        int higher = 1;
        int count = 0;
        while (lower < n) {
            if (visited[lower]) {
                lower++;
                continue;
            }
            int lowerVal = nums[lower];
            int higherVal = lowerVal + (2 * k);
            while (higher < n) {
                if (nums[higher] == higherVal && !visited[higher]) {
                    break;
                }
                higher++;
            }
            if (higher == n) {
                return false;
            }
            visited[lower] = true;
            visited[higher] = true;
            res[count] = lowerVal + k;
            count++;
            if (count == n / 2) {
                return true;
            }
            lower++;
            higher++;
        }
        return false;
    }
}