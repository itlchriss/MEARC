package g2701_2800.s2769_find_the_maximum_achievable_number;

// #Easy #Math #2023_09_21_Time_1_ms_(100.00%)_Space_40.4_MB_(26.03%)

public class Solution {
	//@ requires(*The method takes two integer parameters, `num` and `t`.*);
	//@ requires(*`num` is a positive integer greater than or equal to 1.*);
	//@ requires(*`t` is a positive integer greater than or equal to 1.*);
	//@ ensures(*The method returns an integer value.*);
	//@ ensures(*The returned value is the maximum achievable number.*);
	//@ ensures(*The maximum achievable number can be obtained by increasing or decreasing `x` by 1, and simultaneously increasing or decreasing `num` by 1, no more than `t` times.*);
	//@ ensures(*The maximum achievable number is equal to `num` after applying the operations.*);
	//@ ensures(*There exists at least one achievable number.*);
    public int theMaximumAchievableX(int num, int t) {
        return num + t * 2;
    }
}