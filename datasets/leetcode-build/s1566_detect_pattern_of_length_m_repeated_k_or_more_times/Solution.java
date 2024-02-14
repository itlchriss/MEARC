package g1501_1600.s1566_detect_pattern_of_length_m_repeated_k_or_more_times;

// #Easy #Array #Enumeration #2022_04_11_Time_1_ms_(49.25%)_Space_41.7_MB_(66.67%)

import java.util.Arrays;

public class Solution {
	//@ requires(*The input array `arr` must not be null.*);
	//@ requires(*The length of the input array `arr` must be at least 2.*);
	//@ requires(*The values in the input array `arr` must be positive integers.*);
	//@ requires(*The length `m` of the pattern must be between 1 and 100 (inclusive).*);
	//@ requires(*The number of repetitions `k` must be between 2 and 100 (inclusive).*);
	//@ ensures(*The method should return `true` if there exists a pattern of length `m` that is repeated `k` or more times in the input array `arr`.*);
	//@ ensures(*The method should return `false` if there is no pattern of length `m` that is repeated `k` or more times in the input array `arr`.*);
    public boolean containsPattern(int[] arr, int m, int k) {
        for (int i = 0; i < arr.length - m; i++) {
            int[] pattern = Arrays.copyOfRange(arr, i, i + m);
            int times = 1;
            for (int j = i + m; j < arr.length; j += m) {
                int[] candidate = Arrays.copyOfRange(arr, j, j + m);
                if (Arrays.equals(pattern, candidate)) {
                    times++;
                    if (times == k) {
                        return true;
                    }
                } else {
                    break;
                }
            }
        }
        return false;
    }
}