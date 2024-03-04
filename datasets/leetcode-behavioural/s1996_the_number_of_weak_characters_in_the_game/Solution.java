package g1901_2000.s1996_the_number_of_weak_characters_in_the_game;

// #Medium #Array #Sorting #Greedy #Stack #Monotonic_Stack
// #2022_05_16_Time_121_ms_(81.46%)_Space_89.3_MB_(79.64%)

import java.util.Arrays;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input `properties` is a 2D integer array.*);
//@ ensures(*The length of `properties` is at least 2 and at most 10^5.*);
//@ ensures(*Each element in `properties` is an array of length 2.*);
//@ ensures(*The attack and defense values in `properties` are integers between 1 and 10^5 (inclusive).*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the number of weak characters.*);
//@ ensures(*A character is considered weak if there exists another character with both attack and defense levels strictly greater than the character's attack and defense levels.*);
    public int numberOfWeakCharacters(int[][] properties) {
        Arrays.sort(properties, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        int max = properties[properties.length - 1][1];
        int count = 0;
        for (int i = properties.length - 2; i >= 0; i--) {
            if (properties[i][1] < max) {
                count++;
            }
            max = Math.max(max, properties[i][1]);
        }
        return count;
    }
}