package g0801_0900.s0896_monotonic_array;

// #Easy #Array #Programming_Skills_II_Day_1 #2022_03_28_Time_2_ms_(86.21%)_Space_94_MB_(5.42%)
//@ non_null_by_default
public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is greater than or equal to 1.*);
//@ ensures(*The elements in the input array `nums` are integers.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns `true` if the input array `nums` is monotonic (either monotone increasing or monotone decreasing).*);
//@ ensures(*The method returns `false` if the input array `nums` is not monotonic.*);
//@ ensures(*The method does not modify the input array `nums`.*);
    public boolean isMonotonic(int[] nums) {
        int i = 0;
        //@ havoc i;
        //@ loop_writes i;
        //@ maintaining 0 <= i <= nums.length - 2;
        for (; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                break;
            }
        }
        if (i == nums.length - 1) {
            return true;
        }
        i = 0;
        //@ loop_writes i;
        //@ maintaining 0 <= i <= nums.length;
        for (; i < nums.length - 1; i++) {
            if (nums[i] < nums[i + 1]) {
                break;
            }
        }
        return i == nums.length - 1;
    }
}