package g2001_2100.s2038_remove_colored_pieces_if_both_neighbors_are_the_same_color;

// #Medium #String #Math #Greedy #Game_Theory #2022_05_26_Time_22_ms_(47.78%)_Space_53.9_MB_(61.32%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input string `colors` must not be null.*);
//@ ensures(*The length of the input string `colors` must be greater than or equal to *);
//@ ensures(*Postconditions:*);
//@ ensures(*The method should return `true` if Alice wins the game, and `false` if Bob wins the game.*);
    public boolean winnerOfGame(String colors) {
        int ans = 0;
        for (int i = 1; i < colors.length() - 1; i++) {
            if (colors.charAt(i) == colors.charAt(i - 1)
                    && colors.charAt(i) == colors.charAt(i + 1)) {
                if (colors.charAt(i) == 'A') {
                    ans++;
                } else {
                    ans--;
                }
            }
        }
        return ans > 0;
    }
}