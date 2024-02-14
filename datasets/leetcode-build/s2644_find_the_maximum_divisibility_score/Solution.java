package g2601_2700.s2644_find_the_maximum_divisibility_score;

// #Easy #Array #2023_09_06_Time_173_ms_(98.62%)_Space_43.8_MB_(61.38%)

public class Solution {
	//@ requires(*The length of `nums` and `divisors` is at least 1.*);
	//@ requires(*The length of `nums` and `divisors` is at most 1000.*);
	//@ requires(*The elements of `nums` and `divisors` are integers.*);
	//@ requires(*The elements of `nums` and `divisors` are at least 1.*);
	//@ requires(*The elements of `nums` and `divisors` are at most 10^9.*);
	//@ ensures(*The return value is an integer.*);
	//@ ensures(*The return value is one of the elements in `divisors`.*);
	//@ ensures(*The return value has the maximum divisibility score.*);
	//@ ensures(*If there is more than one integer with the maximum score, the return value is the minimum of them.*);
    public int maxDivScore(int[] nums, int[] divisors) {
        int maxDivisor = divisors[0];
        int maxDividedNums = 0;
        for (int divisor : divisors) {
            int dividedNums = 0;
            for (int num : nums) {
                if (num % divisor == 0) {
                    dividedNums++;
                }
            }
            if (dividedNums > maxDividedNums) {
                maxDividedNums = dividedNums;
                maxDivisor = divisor;
            }
            if (dividedNums == maxDividedNums && divisor < maxDivisor) {
                maxDivisor = divisor;
            }
        }
        return maxDivisor;
    }
}