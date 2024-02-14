package g0301_0400.s0375_guess_number_higher_or_lower_ii;

// #Medium #Dynamic_Programming #Math #Game_Theory
// #2022_07_12_Time_2_ms_(100.00%)_Space_41.2_MB_(94.15%)

@SuppressWarnings("java:S135")
public class Solution {
    int[][] matrix;
	//@ requires(*The input `n` is an integer greater than or equal to 1.*);
	//@ ensures(*The output is an integer representing the minimum amount of money needed to guarantee a win regardless of the number picked by the opponent.*);
	//@ ensures(*The output is 0 if `n` is equal to 1.*);
	//@ ensures(*The output is 1 if `n` is equal to 2.*);
	//@ ensures(*The output is 16 if `n` is equal to 10.*);
	//@ ensures(*The output is the sum of the guessed numbers and the corresponding payments made in the winning strategy.*);
	//@ ensures(*The winning strategy involves guessing numbers within a certain range based on the opponent's response (higher or lower).*);
	//@ ensures(*The worst case scenario in the winning strategy is that the total payment is equal to the output.*);

    public int getMoneyAmount(int n) {
        matrix = new int[n + 1][n + 1];
        return get(1, n);
    }

    private int get(int min, int max) {
        if (max - min < 3) {
            return max - min <= 0 ? 0 : max - 1;
        }
        if (matrix[min][max] != 0) {
            return matrix[min][max];
        }
        int select = max - 3;
        int minRes = Integer.MAX_VALUE;
        int res;
        int end = min + ((max - min) >> 1) - 1;
        int cnt = 0;
        while (true) {
            res = select + Math.max(get(min, select - 1), get(select + 1, max));
            if (res > minRes) {
                cnt++;
                if (cnt >= 3) {
                    break;
                }
            }
            if (res < minRes) {
                minRes = res;
            }
            select--;
            if (select <= end) {
                break;
            }
        }
        matrix[min][max] = minRes;
        return minRes;
    }
}