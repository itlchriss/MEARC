package g2601_2700.s2612_minimum_reverse_operations;

// #Hard #Array #Breadth_First_Search #Ordered_Set
// #2023_08_30_Time_19_ms_(100.00%)_Space_59_MB_(78.00%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input integer `n` is greater than or equal to - The input integer `p` is between 0 and `n - 1`, inclusive.*);
//@ ensures(*The input integer array `banned` is not null.*);
//@ ensures(*The values in the `banned` array are between 0 and `n - 1`, inclusive.*);
//@ ensures(*The input integer `k` is between 1 and `n`, inclusive.*);
//@ ensures(*The value at position `p` in the `arr` array is - The value at position `banned[i]` in the `arr` array is 0 for all `i` in the `banned` array.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The returned array `ans` has a length of `n`.*);
//@ ensures(*The values in the `ans` array are between -1 and the maximum number of reverse operations needed to bring the 1 to any position in `arr`, inclusive.*);
//@ ensures(*The value at position `i` in the `ans` array is the minimum number of reverse operations needed to bring the 1 to position `i` in `arr`, or -1 if it is impossible.*);
    public int[] minReverseOperations(int n, int p, int[] banned, int k) {
        int[] out = new int[n];
        for (int i = 0; i < n; i++) {
            out[i] = -1;
        }
        for (int node : banned) {
            out[node] = -2;
        }
        List<Integer> nodes = new ArrayList<>();
        nodes.add(p);
        int depth = 0;
        out[p] = depth;
        int step = k - 1;
        int[] nextNode2s = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            nextNode2s[i] = i + 2;
        }
        while (!nodes.isEmpty()) {
            depth++;
            List<Integer> newNodes = new ArrayList<>();
            for (int node1 : nodes) {
                int loReverseStart = Math.max(node1 - step, 0);
                int hiReverseStart = Math.min(node1, n - k);
                int loNode2 = 2 * loReverseStart + k - 1 - node1;
                int hiNode2 = 2 * hiReverseStart + k - 1 - node1;
                int postHiNode2 = hiNode2 + 2;
                int node2 = loNode2;
                while (node2 <= hiNode2) {
                    int nextNode2 = nextNode2s[node2];
                    nextNode2s[node2] = postHiNode2;
                    if (node2 < n && out[node2] == -1) {
                        newNodes.add(node2);
                        out[node2] = depth;
                    }
                    node2 = nextNode2;
                }
            }
            nodes = newNodes;
        }
        for (int i = 0; i < n; i++) {
            if (out[i] == -2) {
                out[i] = -1;
            }
        }
        return out;
    }
}