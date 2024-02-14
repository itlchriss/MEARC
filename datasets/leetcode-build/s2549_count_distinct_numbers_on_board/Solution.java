package g2501_2600.s2549_count_distinct_numbers_on_board;

// #Easy #Array #Hash_Table #Math #Simulation #2023_08_15_Time_0_ms_(100.00%)_Space_39.2_MB_(75.23%)

public class Solution {
	//@ requires(*The input `n` is a positive integer.*);
	//@ requires(*The method `distinctIntegers` takes an integer `n` as input.*);
	//@ ensures(*The method `distinctIntegers` returns an integer representing the number of distinct integers present on the board after 10^9 days have elapsed.*);
	//@ ensures(*The board initially contains the number `n`.*);
	//@ ensures(*For each number `x` present on the board, all numbers `1 <= i <= n` such that `x % i == 1` are added to the board.*);
	//@ ensures(*Once a number is placed on the board, it remains on the board until the end.*);
	//@ ensures(*The distinct numbers on the board after 10^9 days have elapsed are returned.*);
    public int distinctIntegers(int n) {
        if (n == 1) {
            return 1;
        }
        return n - 1;
    }
}