package g2901_3000.s2998_minimum_number_of_operations_to_make_x_and_y_equal;

// #Medium #Dynamic_Programming #Breadth_First_Search #Memoization
// #2024_01_17_Time_0_ms_(100.00%)_Space_41.2_MB_(92.39%)

public class Solution {
	//@ requires(*The input integers `x` and `y` are positive.*);
	//@ requires(*The input integers `x` and `y` are within the range of 1 to 10^*);
	//@ ensures(*The method returns an integer representing the minimum number of operations required to make `x` and `y` equal.*);
    public int minimumOperationsToMakeEqual(int x, int y) {
        if (x <= y) {
            return y - x;
        }
        int res = x - y;
        res = Math.min(res, 1 + minimumOperationsToMakeEqual(x / 5, y) + x % 5);
        res = Math.min(res, 1 + minimumOperationsToMakeEqual(x / 5 + 1, y) + 5 - x % 5);
        res = Math.min(res, 1 + minimumOperationsToMakeEqual(x / 11, y) + x % 11);
        res = Math.min(res, 1 + minimumOperationsToMakeEqual(x / 11 + 1, y) + 11 - x % 11);
        return res;
    }
}