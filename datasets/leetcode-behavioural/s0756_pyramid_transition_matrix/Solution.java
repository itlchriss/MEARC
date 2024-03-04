package g0701_0800.s0756_pyramid_transition_matrix;

// #Medium #Depth_First_Search #Breadth_First_Search #Bit_Manipulation
// #2022_03_25_Time_129_ms_(82.94%)_Space_40.5_MB_(95.88%)

import java.util.List;

public class Solution {
    private boolean dfs(char[] c, int i, int l, int[][] map) {
        if (l == 1) {
            return true;
        }
        if (i == l - 1) {
            return dfs(c, 0, l - 1, map);
        }
        char save = c[i];
        char p = 'A';
        for (int v = map[c[i] - 'A'][c[i + 1] - 'A']; v != 0; v >>= 1, p++) {
            if ((v & 1) != 0) {
                c[i] = p;
                if (dfs(c, i + 1, l, map)) {
                    return true;
                }
            }
        }
        c[i] = save;
        return false;
    }
//@ ensures(*Preconditions:*);
//@ ensures(*The length of the `bottom` string is between 2 and 6, inclusive.*);
//@ ensures(*The length of each string in the `allowed` list is 3.*);
//@ ensures(*The letters in the `bottom` string and the `allowed` list are from the set {'A', 'B', 'C', 'D', 'E', 'F'}.*);
//@ ensures(*All the values in the `allowed` list are unique.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns a boolean value indicating whether it is possible to build the pyramid all the way to the top using the given `bottom` and `allowed` list.*);
//@ ensures(*If the method returns true, it means that every triangular pattern in the pyramid is in the `allowed` list.*);
//@ ensures(*If the method returns false, it means that there is at least one triangular pattern in the pyramid that is not in the `allowed` list.*);

    public boolean pyramidTransition(String bottom, List<String> allowed) {
        int[][] map = new int[7][7];
        for (String s : allowed) {
            map[s.charAt(0) - 'A'][s.charAt(1) - 'A'] |= 1 << (s.charAt(2) - 'A');
        }
        return dfs(bottom.toCharArray(), 0, bottom.length(), map);
    }
}