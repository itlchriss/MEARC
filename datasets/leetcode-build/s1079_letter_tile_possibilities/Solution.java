package g1001_1100.s1079_letter_tile_possibilities;

// #Medium #String #Backtracking #2022_02_26_Time_3_ms_(86.86%)_Space_42.3_MB_(51.05%)

import java.util.Arrays;

public class Solution {
    private int count;
	//@ requires(*The input string `tiles` is not null.*);
	//@ requires(*The length of the input string `tiles` is between 1 and 7 (inclusive).*);
	//@ requires(*The input string `tiles` consists of uppercase English letters.*);
	//@ ensures(*The method returns an integer representing the number of possible non-empty sequences of letters that can be made using the letters printed on the tiles.*);

    public int numTilePossibilities(String tiles) {
        count = 0;
        char[] chars = tiles.toCharArray();
        Arrays.sort(chars);
        boolean[] visited = new boolean[chars.length];
        dfs(chars, 0, visited);
        return count;
    }

    private void dfs(char[] chars, int length, boolean[] visited) {
        if (length == chars.length) {
            return;
        }
        for (int i = 0; i < chars.length; i++) {
            if (visited[i] || i - 1 >= 0 && chars[i] == chars[i - 1] && !visited[i - 1]) {
                continue;
            }
            count++;
            visited[i] = true;
            dfs(chars, length + 1, visited);
            visited[i] = false;
        }
    }
}