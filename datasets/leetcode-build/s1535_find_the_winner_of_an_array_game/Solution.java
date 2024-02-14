package g1501_1600.s1535_find_the_winner_of_an_array_game;

// #Medium #Array #Simulation #2022_04_10_Time_1_ms_(86.99%)_Space_79.8_MB_(41.78%)

public class Solution {
	//@ requires(*The input array `arr` must have a length greater than or equal to 2.*);
	//@ requires(*The integers in the input array `arr` must be distinct.*);
	//@ requires(*The value of `k` must be greater than or equal to 1.*);
	//@ ensures(*The output of the method must be an integer.*);
	//@ ensures(*The output of the method must be one of the integers in the input array `arr`.*);
	//@ ensures(*The output of the method must be the integer that wins `k` consecutive rounds in the game.*);
    public int getWinner(int[] arr, int k) {
        int winner = arr[0];
        int winTimes = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > winner) {
                winner = arr[i];
                winTimes = 1;
            } else {
                winTimes++;
            }
            if (winTimes >= k) {
                return winner;
            }
        }
        return winner;
    }
}