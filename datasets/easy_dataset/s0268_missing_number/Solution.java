package g0201_0300.s0268_missing_number;

// #Easy #Top_Interview_Questions #Array #Hash_Table #Math #Sorting #Binary_Search #Bit_Manipulation
// #2022_07_05_Time_1_ms_(72.07%)_Space_50.6_MB_(76.88%)

public class Solution {
//@ ensures(*The integer array parameter `nums` must not be null.*);
//@ ensures(*The integer result is the only number in the range [0, n] that is missing from the integer array parameter `nums`.*);
    public int missingNumber(int[] nums) {
        int a = nums.length;
        for (int i = 0; i < nums.length; i++) {
            a ^= i;
            a ^= nums[i];
        }
        return a;
    }
}