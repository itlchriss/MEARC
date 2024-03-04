package g2401_2500.s2410_maximum_matching_of_players_with_trainers;

// #Medium #Array #Sorting #Greedy #Two_Pointers
// #2022_10_24_Time_28_ms_(98.31%)_Space_60.2_MB_(94.06%)

import java.util.Arrays;

@SuppressWarnings("java:S135")
public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The `players` array must not be null.*);
//@ ensures(*The `trainers` array must not be null.*);
//@ ensures(*The length of the `players` array must be greater than or equal to 1.*);
//@ ensures(*The length of the `trainers` array must be greater than or equal to 1.*);
//@ ensures(*The elements in the `players` array must be integers.*);
//@ ensures(*The elements in the `trainers` array must be integers.*);
//@ ensures(*The elements in the `players` array must be greater than or equal to 1.*);
//@ ensures(*The elements in the `trainers` array must be greater than or equal to 1.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the maximum number of matchings between players and trainers.*);
//@ ensures(*The returned value is greater than or equal to 0.*);
//@ ensures(*The returned value is less than or equal to the length of the `players` array.*);
//@ ensures(*Each player can be matched with at most one trainer.*);
//@ ensures(*Each trainer can be matched with at most one player.*);
//@ ensures(*The ability of a player must be less than or equal to the training capacity of a trainer for them to be matched.*);
//@ ensures(*The maximum number of matchings returned is the maximum possible number of matchings that satisfy the given conditions.*);
    public int matchPlayersAndTrainers(int[] players, int[] trainers) {
        Arrays.sort(players);
        Arrays.sort(trainers);
        int i = 0;
        int j = 0;
        int count = 0;
        i = 0;
        while (i < players.length) {
            while (trainers[j] < players[i]) {
                j++;
                if (j >= trainers.length) {
                    break;
                }
            }
            if (j >= trainers.length) {
                break;
            }
            if (trainers[j] >= players[i]) {
                count++;
            }
            i++;
            j++;
            if (j >= trainers.length || i >= players.length) {
                break;
            }
        }
        return count;
    }
}