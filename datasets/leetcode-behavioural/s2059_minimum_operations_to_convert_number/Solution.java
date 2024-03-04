package g2001_2100.s2059_minimum_operations_to_convert_number;

// #Medium #Array #Breadth_First_Search #2022_05_28_Time_97_ms_(64.14%)_Space_116.9_MB_(51.03%)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` must not be null.*);
//@ ensures(*The length of the input array `nums` must be between 1 and 1000 (inclusive).*);
//@ ensures(*The integers in the input array `nums` must be distinct.*);
//@ ensures(*The input integer `start` must be between 0 and 1000 (inclusive).*);
//@ ensures(*The input integer `goal` must be between -10^9 and 10^9 (inclusive).*);
//@ ensures(*The input integer `start` must not be equal to the input integer `goal`.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output integer must be the minimum number of operations needed to convert `start` into `goal`.*);
//@ ensures(*If it is not possible to convert `start` into `goal`, the output integer must be -1.*);
    public int minimumOperations(int[] nums, int start, int goal) {
        boolean[] seen = new boolean[1001];
        List<Integer> q = Arrays.asList(goal);
        int cnt = 0;
        while (!q.isEmpty()) {
            ++cnt;
            List<Integer> q1 = new ArrayList<>();
            for (int x : q) {
                for (int n : nums) {
                    for (int xn : new int[] {x + n, x - n, x ^ n}) {
                        if (xn >= 0 && xn <= 1000 && !seen[xn]) {
                            if (xn == start) {
                                return cnt;
                            }
                            seen[xn] = true;
                            q1.add(xn);
                        }
                    }
                }
                q = q1;
            }
        }
        return -1;
    }
}