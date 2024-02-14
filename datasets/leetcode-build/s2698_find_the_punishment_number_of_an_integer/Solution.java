package g2601_2700.s2698_find_the_punishment_number_of_an_integer;

// #Medium #Math #Backtracking #2023_09_13_Time_5_ms_(97.06%)_Space_39.4_MB_(96.57%)

public class Solution {
	//@ requires(*The input `n` is a positive integer.*);
	//@ requires(*`n` is less than or equal to 1000.*);
	//@ ensures(*The output is an integer.*);
	//@ ensures(*The output is the punishment number of `n`, which is the sum of the squares of all integers `i` such that:*);
	//@ ensures(*- `1 <= i <= n`*);
	//@ ensures(*- The decimal representation of `i * i` can be partitioned into contiguous substrings such that the sum of the integer values of these substrings equals `i`.*);
    public int punishmentNumber(int n) {
        return calculatePunishmentNumber(n);
    }

    private boolean partition(int x, int target) {
        if (x == target) {
            return true;
        }
        if (target < 0 || x < target) {
            return false;
        }
        return partition(x / 10, target - (x % 10))
                || partition(x / 100, target - (x % 100))
                || partition(x / 1000, target - (x % 1000));
    }

    private int calculatePunishmentNumber(int n) {
        int res = 0;
        for (int i = 1; i <= n; i++) {
            int iSq = i * i;
            if (partition(iSq, i)) {
                res += iSq;
            }
        }
        return res;
    }
}