package g2201_2300.s2221_find_triangular_sum_of_an_array;

// #Medium #Array #Math #Simulation #Combinatorics
// #2022_06_09_Time_78_ms_(83.64%)_Space_42.4_MB_(85.99%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is greater than or equal to 1.*);
//@ ensures(*Each element in the input array `nums` is an integer between 0 and 9 (inclusive).*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is an integer representing the triangular sum of the input array `nums`.*);
//@ ensures(*The input array `nums` is modified according to the process described in the requirements.*);
//@ ensures(*The length of the modified array `nums` is either 1 or 0.*);
//@ ensures(*If the length of the modified array `nums` is 1, then the value of the only element in `nums` is the triangular sum.*);
//@ ensures(*If the length of the modified array `nums` is 0, then the triangular sum is 0.*);
    public int triangularSum(int[] nums) {
        int len = nums.length;
        while (len-- > 1) {
            for (int i = 0; i < len; i++) {
                nums[i] += nums[i + 1];
                nums[i] %= 10;
            }
        }
        return nums[0];
    }
}