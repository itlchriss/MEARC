package g0501_0600.s0517_super_washing_machines;

// #Hard #Array #Greedy #2022_07_25_Time_1_ms_(94.02%)_Space_45.5_MB_(67.39%)

public class Solution {
	//@ requires(*The input array `machines` is not null.*);
	//@ requires(*The length of the input array `machines` is greater than or equal to 1.*);
	//@ requires(*The length of the input array `machines` is less than or equal to 10^4.*);
	//@ requires(*The elements of the input array `machines` are non-negative integers.*);
	//@ requires(*The number of dresses in each washing machine is less than or equal to 10^5.*);
	//@ ensures(*The method returns an integer representing the minimum number of moves required to make all the washing machines have the same number of dresses.*);
	//@ ensures(*If it is not possible to make all the washing machines have the same number of dresses, the method returns -1.*);
    public int findMinMoves(int[] machines) {
        int total = 0;
        for (int i : machines) {
            total += i;
        }
        if (total % machines.length != 0) {
            return -1;
        }
        int avg = total / machines.length;
        int cnt = 0;
        int max = 0;
        for (int load : machines) {
            cnt += load - avg;
            max = Math.max(Math.max(max, Math.abs(cnt)), load - avg);
        }
        return max;
    }
}