package g1801_1900.s1846_maximum_element_after_decreasing_and_rearranging;

// #Medium #Array #Sorting #Greedy #2022_05_07_Time_4_ms_(93.59%)_Space_74.4_MB_(23.49%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `arr` is not null.*);
//@ ensures(*The length of the input array `arr` is greater than or equal to 1.*);
//@ ensures(*All elements in the input array `arr` are positive integers.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The value of the first element in the modified array `arr` is 1.*);
//@ ensures(*The absolute difference between any two adjacent elements in the modified array `arr` is less than or equal to 1.*);
//@ ensures(*The maximum possible value of an element in the modified array `arr` is returned as an integer.*);
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        int[] count = new int[arr.length + 1];
        for (int j : arr) {
            count[Math.min(j, arr.length)]++;
        }
        int ans = 1;
        for (int i = 1; i < count.length; i++) {
            ans = Math.min(i, ans + count[i]);
        }
        return ans;
    }
}