package g0801_0900.s0851_loud_and_rich;

// #Medium #Array #Depth_First_Search #Graph #Topological_Sort
// #2022_03_27_Time_3_ms_(99.67%)_Space_62.9_MB_(75.99%)

public class Solution {
	//@ requires(*The input array `richer` is not null.*);
	//@ requires(*The input array `quiet` is not null.*);
	//@ requires(*The length of `quiet` is equal to the number of people `n`.*);
	//@ requires(*The values in `quiet` are unique.*);
	//@ requires(*The values in `quiet` are integers between 0 and `n-1`.*);
	//@ requires(*The length of `richer` is less than or equal to `n * (n - 1) / 2`.*);
	//@ requires(*The pairs in `richer` are unique.*);
	//@ requires(*The values in `richer` are integers between 0 and `n-1`.*);
	//@ requires(*The values in `richer` do not contain any pairs where `a_i` is equal to `b_i`.*);
	//@ requires(*The observations in `richer` are logically consistent.*);
	//@ ensures(*The output array `answer` is not null.*);
	//@ ensures(*The length of `answer` is equal to the number of people `n`.*);
	//@ ensures(*The values in `answer` are integers between 0 and `n-1`.*);
	//@ ensures(*For each index `x` in `answer`, the corresponding value `y` is the least quiet person among all people who definitely have equal to or more money than person `x`.*);
    public int[] loudAndRich(int[][] richer, int[] quiet) {
        int[] result = new int[quiet.length];
        for (int i = 0; i < quiet.length; i++) {
            result[i] = i;
        }
        for (int k = 0; k < quiet.length; k++) {
            boolean changed = false;
            for (int[] r : richer) {
                if (quiet[result[r[0]]] < quiet[result[r[1]]]) {
                    result[r[1]] = result[r[0]];
                    changed = true;
                }
            }
            if (!changed) {
                break;
            }
        }
        return result;
    }
}