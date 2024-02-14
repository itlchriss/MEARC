package g2501_2600.s2579_count_total_number_of_colored_cells;

// #Medium #Math #2023_08_22_Time_0_ms_(100.00%)_Space_39.2_MB_(60.33%)

public class Solution {
	//@ requires(*The input `n` is a positive integer.*);
	//@ ensures(*The method returns a long value representing the number of colored cells at the end of `n` minutes.*);
	//@ ensures(*The grid is an infinitely large two-dimensional grid of uncolored unit cells.*);
	//@ ensures(*At the first minute, any arbitrary unit cell is colored blue.*);
	//@ ensures(*Every minute thereafter, every uncolored cell that touches a blue cell is colored blue.*);
	//@ ensures(*The number of colored cells at the end of `n` minutes is returned.*);
    public long coloredCells(int n) {
        return (long) Math.pow(n, 2) + (long) Math.pow(n - (double) 1, 2);
    }
}