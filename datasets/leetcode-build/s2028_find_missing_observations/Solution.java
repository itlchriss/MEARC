package g2001_2100.s2028_find_missing_observations;

// #Medium #Array #Math #Simulation #2022_05_25_Time_10_ms_(31.40%)_Space_165.4_MB_(26.74%)

public class Solution {
	//@ requires(*The input array `rolls` must not be null.*);
	//@ requires(*The length of the input array `rolls` must be equal to `m`.*);
	//@ requires(*The value of each element in the input array `rolls` must be between 1 and 6 (inclusive).*);
	//@ requires(*The value of `mean` must be between 1 and 6 (inclusive).*);
	//@ requires(*The value of `n` must be between 1 and 10^5 (inclusive).*);
	//@ requires(*The sum of `n + m` must be divisible by `n + m`.*);
	//@ ensures(*The output array must not be null.*);
	//@ ensures(*The length of the output array must be equal to `n`.*);
	//@ ensures(*The value of each element in the output array must be between 1 and 6 (inclusive).*);
	//@ ensures(*The sum of the input array `rolls` and the output array must be divisible by `n + m`.*);
	//@ ensures(*The average value of the input array `rolls` and the output array must be equal to `mean`.*);
    public int[] missingRolls(int[] rolls, int mean, int n) {
        int m = rolls.length;
        int msum = 0;
        int[] res = new int[n];
        for (int roll : rolls) {
            msum += roll;
        }
        int totalmn = mean * (m + n) - msum;
        if (totalmn < n || totalmn > n * 6) {
            return new int[0];
        }
        int j = 0;
        while (totalmn > 0) {
            int dice = Math.min(6, totalmn - n + 1);
            res[j] = dice;
            totalmn = totalmn - dice;
            n--;
            j++;
        }
        return res;
    }
}