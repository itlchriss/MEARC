package g2001_2100.s2097_valid_arrangement_of_pairs;

// #Hard #Depth_First_Search #Graph #Eulerian_Circuit
// #2022_05_25_Time_158_ms_(100.00%)_Space_122.1_MB_(92.55%)

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input `pairs` is a 2D integer array.*);
//@ ensures(*The length of `pairs` is greater than or equal to 1 and less than or equal to 10^5.*);
//@ ensures(*Each element in `pairs` is an array of length 2.*);
//@ ensures(*The values of `start_i` and `end_i` in each pair are integers between 0 and 10^9 (inclusive).*);
//@ ensures(*The value of `start_i` is not equal to the value of `end_i` for each pair.*);
//@ ensures(*No two pairs in `pairs` are exactly the same.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is a 2D integer array.*);
//@ ensures(*The length of the output is the same as the length of `pairs`.*);
//@ ensures(*For every index `i` where 1 <= i < pairs.length, `end_i-1` is equal to `start_i` in the output.*);
    public int[][] validArrangement(int[][] pairs) {
        HashMap<Integer, int[]> inOutedge = new HashMap<>();
        HashMap<Integer, Queue<Integer>> adList = getAdList(pairs, inOutedge);
        int start = getStart(inOutedge);
        int[][] res = new int[pairs.length][2];
        getRes(start, adList, res, pairs.length - 1);
        return res;
    }

    private HashMap<Integer, Queue<Integer>> getAdList(
            int[][] pairs, HashMap<Integer, int[]> inOutEdge) {
        HashMap<Integer, Queue<Integer>> adList = new HashMap<>();
        for (int[] pair : pairs) {
            int s = pair[0];
            int d = pair[1];
            Queue<Integer> set = adList.computeIfAbsent(s, k -> new LinkedList<>());
            set.add(d);
            int[] sEdgeCnt = inOutEdge.computeIfAbsent(s, k -> new int[2]);
            int[] dEdgeCnt = inOutEdge.computeIfAbsent(d, k -> new int[2]);
            sEdgeCnt[1]++;
            dEdgeCnt[0]++;
        }
        return adList;
    }

    private int getRes(int k, HashMap<Integer, Queue<Integer>> adList, int[][] res, int idx) {
        Queue<Integer> edges = adList.get(k);
        if (edges == null) {
            return idx;
        }
        while (!edges.isEmpty()) {
            int edge = edges.poll();
            idx = getRes(edge, adList, res, idx);
            res[idx--] = new int[] {k, edge};
        }
        return idx;
    }

    private int getStart(HashMap<Integer, int[]> map) {
        int start = -1;
        for (Map.Entry<Integer, int[]> entry : map.entrySet()) {
            int k = entry.getKey();
            int inEdge = entry.getValue()[0];
            int outEdge = entry.getValue()[1];
            start = k;
            if (outEdge - inEdge == 1) {
                return k;
            }
        }
        return start;
    }
}