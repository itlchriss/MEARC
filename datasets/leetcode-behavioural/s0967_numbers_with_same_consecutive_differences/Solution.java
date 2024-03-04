package g0901_1000.s0967_numbers_with_same_consecutive_differences;

// #Medium #Breadth_First_Search #Backtracking
// #2022_03_31_Time_1_ms_(100.00%)_Space_43.2_MB_(35.45%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input values `n` and `k` must be integers.*);
//@ ensures(*`n` must be between 2 and 9 (inclusive).*);
//@ ensures(*`k` must be between 0 and 9 (inclusive).*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an array of non-negative integers.*);
//@ ensures(*The length of each integer in the array is `n`.*);
//@ ensures(*The absolute difference between every two consecutive digits in each integer is `k`.*);
//@ ensures(*The integers in the array must not have leading zeros.*);
    public int[] numsSameConsecDiff(int n, int k) {
        k = Math.abs(k);
        List<Integer> list = new ArrayList<>();
        dfs(list, 100000, 0, n, k);
        int[] res = new int[list.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    private void dfs(List<Integer> list, int can, int len, int n, int k) {
        if (len == n) {
            list.add(can);
            return;
        }
        if (can == 0) {
            return;
        }
        if (len == 0) {
            for (int i = 0; i <= 9; i++) {
                dfs(list, i, 1, n, k);
            }
        } else {
            int last = can % 10;
            int a = last + k;
            int b = last - k;
            if (b >= 0) {
                dfs(list, can * 10 + b, len + 1, n, k);
            }
            if (k != 0 && a < 10) {
                dfs(list, can * 10 + a, len + 1, n, k);
            }
        }
    }
}