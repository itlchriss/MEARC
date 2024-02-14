package g1601_1700.s1643_kth_smallest_instructions;

// #Hard #Array #Dynamic_Programming #Math #Combinatorics
// #2022_04_21_Time_1_ms_(100.00%)_Space_43.3_MB_(20.97%)

@SuppressWarnings("java:S135")
public class Solution {
	//@ requires(*The `destination` array must have a length of 2.*);
	//@ requires(*The values in the `destination` array must be positive integers.*);
	//@ requires(*The `k` value must be a positive integer.*);
	//@ requires(*The `row` and `column` values must be positive integers between 1 and 15.*);
	//@ requires(*The `k` value must be less than or equal to the number of combinations of `row + column` choose `row`.*);
	//@ ensures(*The method should return a string representing the lexicographically smallest instructions that will lead Bob to the `destination`.*);
	//@ ensures(*The returned string should consist of only the characters 'H' and 'V'.*);
	//@ ensures(*The length of the returned string should be equal to the sum of the values in the `destination` array.*);
	//@ ensures(*The returned string should be one of the valid instructions that will lead Bob to the `destination`.*);
	//@ ensures(*The returned string should be the `k`th lexicographically smallest instruction.*);
    public String kthSmallestPath(int[] destination, int k) {
        StringBuilder sb = new StringBuilder();
        int v = destination[0];
        int n = v + destination[1];
        while (true) {
            int range = choose(--n, v);
            if (k <= range) {
                sb.append('H');
            } else {
                sb.append('V');
                v--;
                k -= range;
            }
            if (v == 0) {
                for (int i = 1; i <= n; i++) {
                    sb.append('H');
                }
                break;
            } else if (v == n) {
                for (int i = 1; i <= v; i++) {
                    sb.append('V');
                }
                break;
            }
        }

        return sb.toString();
    }

    private int choose(int n, int k) {
        if (n - k < k) {
            k = n - k;
        }
        int answer = 1;
        for (int i = 1; i <= k; i++) {
            answer = answer * (n + 1 - i) / i;
        }
        return answer;
    }
}