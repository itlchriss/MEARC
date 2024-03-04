package g1401_1500.s1437_check_if_all_1s_are_at_least_length_k_places_away;

// #Easy #Array #2022_03_28_Time_2_ms_(73.75%)_Space_71.7_MB_(39.20%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is greater than or equal to 1.*);
//@ ensures(*The value of `k` is non-negative.*);
//@ ensures(*The value of `k` is less than or equal to the length of the input array `nums`.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns a boolean value indicating whether all `1`'s in the input array `nums` are at least `k` places away from each other.*);
    public boolean kLengthApart(int[] nums, int k) {
        int last = -k - 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                if (i - last - 1 >= k) {
                    last = i;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}