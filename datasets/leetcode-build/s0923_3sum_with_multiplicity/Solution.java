package g0901_1000.s0923_3sum_with_multiplicity;

// #Medium #Array #Hash_Table #Sorting #Two_Pointers #Counting
// #2022_03_29_Time_14_ms_(69.20%)_Space_44.2_MB_(48.00%)

public class Solution {
    private static final int MOD = (int) 1e9 + 7;
    private static final int MAX = 100;
	//@ requires(*The input array `arr` must have a length greater than or equal to 3.*);
	//@ requires(*The elements of `arr` must be integers between 0 and 100.*);
	//@ requires(*The target value `target` must be an integer between 0 and 300.*);
	//@ ensures(*The method should return an integer value.*);
	//@ ensures(*The returned value should be the number of tuples `i, j, k` such that `i < j < k` and `arr[i] + arr[j] + arr[k] == target`.*);
	//@ ensures(*The returned value should be modulo `10^9 + 7`.*);

    public int threeSumMulti(int[] arr, int target) {
        int answer = 0;
        int[] countRight = new int[MAX + 1];
        for (int num : arr) {
            ++countRight[num];
        }
        int[] countLeft = new int[MAX + 1];
        for (int j = 0; j < arr.length - 1; j++) {
            --countRight[arr[j]];
            int remains = target - arr[j];
            if (remains <= 2 * MAX) {
                for (int v = 0; v <= Math.min(remains, MAX); v++) {
                    if (remains - v <= MAX) {
                        int count = countRight[v] * countLeft[remains - v];
                        if (count > 0) {
                            answer = (answer + count) % MOD;
                        }
                    }
                }
            }
            ++countLeft[arr[j]];
        }
        return answer;
    }
}