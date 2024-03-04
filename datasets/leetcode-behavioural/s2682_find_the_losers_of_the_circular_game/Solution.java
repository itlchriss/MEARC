package g2601_2700.s2682_find_the_losers_of_the_circular_game;

// #Easy #Array #Hash_Table #Simulation #2023_09_12_Time_1_ms_(100.00%)_Space_43.6_MB_(51.49%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The value of `n` is a positive integer.*);
//@ ensures(*The value of `k` is a positive integer.*);
//@ ensures(*The value of `k` is less than or equal to `n`.*);
//@ ensures(*The value of `n` is less than or equal to 50.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an array of integers.*);
//@ ensures(*The length of the returned array is equal to the number of losers in the game.*);
//@ ensures(*The elements in the returned array are in ascending order.*);
//@ ensures(*The elements in the returned array are the friends who did not receive the ball in the entire game.*);
    public int[] circularGameLosers(int n, int k) {
        int[] pointsMap = new int[n];
        int friend = 0;
        int turn = 1;
        while (true) {
            pointsMap[friend] = pointsMap[friend] + 1;
            if (pointsMap[friend] == 2) {
                break;
            }
            friend = (friend + turn * k) % n;
            turn++;
        }
        int[] result = new int[n - (turn - 1)];
        int i = 0;
        for (int index = 0; index < pointsMap.length; index++) {
            if (pointsMap[index] == 0) {
                result[i] = index + 1;
                i++;
            }
        }
        return result;
    }
}