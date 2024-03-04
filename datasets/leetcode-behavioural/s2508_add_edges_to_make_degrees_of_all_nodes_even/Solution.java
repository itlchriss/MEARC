package g2501_2600.s2508_add_edges_to_make_degrees_of_all_nodes_even;

// #Hard #Hash_Table #Graph #2023_04_18_Time_36_ms_(95.00%)_Space_86.3_MB_(93.33%)

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input integer `n` is greater than or equal to 3.*);
//@ ensures(*The input list `edges` has a length greater than or equal to 2.*);
//@ ensures(*Each element `edges[i]` in the input list `edges` is a list of length 2.*);
//@ ensures(*The elements `a_i` and `b_i` in each `edges[i]` are integers between 1 and `n`, inclusive.*);
//@ ensures(*The elements `a_i` and `b_i` in each `edges[i]` are not equal.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns a boolean value.*);
//@ ensures(*If it is possible to make the degree of each node in the graph even, the method returns `true`.*);
//@ ensures(*If it is not possible to make the degree of each node in the graph even, the method returns `false`.*);
    public boolean isPossible(int n, List<List<Integer>> edges) {
        ArrayList<Integer>[] g = new ArrayList[n + 1];
        ArrayList<Integer> oddList = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            g[i] = new ArrayList<>();
        }
        for (List<Integer> edge : edges) {
            int x = edge.get(0);
            int y = edge.get(1);
            g[x].add(y);
            g[y].add(x);
        }
        for (int i = 1; i <= n; i++) {
            if (g[i].size() % 2 == 1) {
                oddList.add(i);
            }
        }
        int size = oddList.size();
        if (size == 0) {
            return true;
        }
        if (size == 1 || size == 3 || size > 4) {
            return false;
        }
        if (size == 2) {
            int x = oddList.get(0);
            int y = oddList.get(1);
            if (isNotConnected(x, y, g)) {
                return true;
            }
            for (int i = 1; i <= n; i++) {
                if (isNotConnected(i, x, g) && isNotConnected(i, y, g)) {
                    return true;
                }
            }
            return false;
        }
        int a = oddList.get(0);
        int b = oddList.get(1);
        int c = oddList.get(2);
        int d = oddList.get(3);
        if (isNotConnected(a, b, g) && isNotConnected(c, d, g)) {
            return true;
        }
        if (isNotConnected(a, c, g) && isNotConnected(b, d, g)) {
            return true;
        }
        return isNotConnected(a, d, g) && isNotConnected(b, c, g);
    }

    private boolean isNotConnected(int x, int y, ArrayList<Integer>[] g) {
        return !g[x].contains(y);
    }
}