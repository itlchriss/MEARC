package g0901_1000.s0991_broken_calculator;

// #Medium #Math #Greedy #2022_03_31_Time_0_ms_(100.00%)_Space_41.1_MB_(40.35%)

public class Solution {
	//@ requires(*The method takes two integer parameters `startValue` and `target`.*);
	//@ requires(*The value of `startValue` is greater than or equal to 1.*);
	//@ requires(*The value of `target` is greater than or equal to 1.*);
	//@ ensures(*The method returns an integer representing the minimum number of operations needed to display `target` on the calculator.*);
	//@ ensures(*The value returned is greater than or equal to 0.*);
    public int brokenCalc(int startValue, int target) {
        int result = 0;
        while (startValue != target) {
            if (target > startValue && target % 2 != 0) {
                target += 1;
                result++;
            } else if (target > startValue) {
                target /= 2;
                result++;
            } else {
                result += startValue - target;
                break;
            }
        }
        return result;
    }
}