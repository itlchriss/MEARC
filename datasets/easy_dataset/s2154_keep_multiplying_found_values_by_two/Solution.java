package g2101_2200.s2154_keep_multiplying_found_values_by_two;

// #Easy #Array #Hash_Table #Sorting #Simulation
// #2022_06_01_Time_1_ms_(93.21%)_Space_44.6_MB_(46.46%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The input array `nums` is not empty.*);
//@ ensures(*The input integer `original` is not null.*);
//@ ensures(*The input integer `original` is within the range of 1 to 1000.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer value.*);
//@ ensures(*The returned integer value is the final value of `original`.*);
//@ ensures(*If `original` is found in `nums`, it is multiplied by two and the new value becomes the new `original`.*);
//@ ensures(*If `original` is not found in `nums`, the method stops and returns the current value of `original`.*);
    public int findFinalValue(int[] nums, int original) {
        int i = 0;
        while (i < nums.length) {
            if (nums[i] == original) {
                original = original * 2;
                i = -1;
            }
            i++;
        }
        return original;
    }
}