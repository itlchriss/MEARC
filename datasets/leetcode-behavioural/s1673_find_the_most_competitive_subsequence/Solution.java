package g1601_1700.s1673_find_the_most_competitive_subsequence;

// #Medium #Array #Greedy #Stack #Monotonic_Stack
// #2022_04_19_Time_7_ms_(96.82%)_Space_54.9_MB_(88.07%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The input array `nums` has at least `k` elements.*);
//@ ensures(*The input array `nums` contains only non-negative integers.*);
//@ ensures(*The input integer `k` is a positive integer.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The returned array `result` is not null.*);
//@ ensures(*The length of the returned array `result` is equal to `k`.*);
//@ ensures(*The elements in the returned array `result` are selected from the input array `nums`.*);
//@ ensures(*The elements in the returned array `result` are in the same order as they appear in the input array `nums`.*);
//@ ensures(*The returned array `result` is the most competitive subsequence of `nums` of size `k`.*);
    public int[] mostCompetitive(int[] nums, int k) {
        int[] r = new int[k];
        int n = nums.length;
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                r[j] = nums[i];
                j++;
            } else {
                int l = j - 1;
                while ((l >= 0) && (nums[i] < r[l]) && ((n - i) >= (k - l))) {
                    l--;
                }
                j = l + 1;
                if (j < k) {
                    r[j] = nums[i];
                    j++;
                }
            }
        }
        return r;
    }
}