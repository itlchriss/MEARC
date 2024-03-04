package g2001_2100.s2023_number_of_pairs_of_strings_with_concatenation_equal_to_target;

// #Medium #Array #String #2022_05_25_Time_36_ms_(36.00%)_Space_42.8_MB_(69.54%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` must have a length greater than or equal to - Each element in the `nums` array must be a string consisting of digits.*);
//@ ensures(*The `target` string must have a length greater than or equal to - The `target` string must consist of digits.*);
//@ ensures(*The `nums` array and `target` string must not have leading zeros.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method should return an integer representing the number of pairs of indices `(i, j)` where `i != j` and the concatenation of `nums[i] + nums[j]` equals `target`.*);
    public int numOfPairs(String[] nums, String target) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i != j) {
                    String con = nums[i] + nums[j];
                    if (con.equals(target)) {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }
}