package g1601_1700.s1630_arithmetic_subarrays;

// #Medium #Array #Sorting #Programming_Skills_II_Day_9
// #2022_04_19_Time_8_ms_(93.62%)_Space_54.4_MB_(59.83%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input arrays `nums`, `l`, and `r` are not null.*);
//@ ensures(*The length of `nums` is greater than or equal to 2.*);
//@ ensures(*The length of `l` is equal to the length of `r`.*);
//@ ensures(*The length of `l` is greater than or equal to 1.*);
//@ ensures(*The values in `l` and `r` are valid indices for the array `nums`.*);
//@ ensures(*The values in `l` are less than the corresponding values in `r`.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The returned list `answer` is not null.*);
//@ ensures(*The length of `answer` is equal to the length of `l`.*);
//@ ensures(*Each element in `answer` is either `true` or `false`.*);
    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        List<Boolean> result = new ArrayList<>();
        int n = l.length;
        for (int i = 0; i < n; i++) {
            result.add(check(nums, l[i], r[i]));
        }
        return result;
    }

    private boolean check(int[] nums, int l, int r) {
        int n = r - l;
        if (n == 0) {
            return true;
        }
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = l; i <= r; i++) {
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[i]);
        }
        if ((max - min) % n != 0) {
            return false;
        }
        int diff = (max - min) / n;
        if (diff == 0) {
            return true;
        }
        boolean[] checked = new boolean[max - min + 1];
        for (int i = l; i <= r; i++) {
            int currentDiff = nums[i] - min;
            if (checked[currentDiff] || currentDiff % diff != 0) {
                return false;
            }
            checked[currentDiff] = true;
        }
        return true;
    }
}