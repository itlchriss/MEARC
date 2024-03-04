package g1601_1700.s1691_maximum_height_by_stacking_cuboids;

// #Hard #Array #Dynamic_Programming #Sorting #2022_04_15_Time_6_ms_(83.07%)_Space_42_MB_(80.25%)

import java.util.Arrays;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input `arr` is a 2D array of integers representing the dimensions of `n` cuboids.*);
//@ ensures(*The dimensions of each cuboid in `arr` are positive integers.*);
//@ ensures(*The dimensions of each cuboid in `arr` are less than or equal to *);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the maximum height of the stacked cuboids.*);
//@ ensures(*The cuboids are stacked in a way that satisfies the placement conditions:*);
//@ ensures(*  - The width of a cuboid being placed on top is less than or equal to the width of the cuboid below.*);
//@ ensures(*  - The length of a cuboid being placed on top is less than or equal to the length of the cuboid below.*);
//@ ensures(*  - The height of a cuboid being placed on top is less than or equal to the height of the cuboid below.*);
//@ ensures(*The dimensions of each cuboid can be rearranged by rotating it to put it on another cuboid.*);
    public int maxHeight(int[][] arr) {
        for (int[] a : arr) {
            Arrays.sort(a);
        }
        Arrays.sort(
                arr,
                (a, b) -> {
                    if (a[0] != b[0]) {
                        return a[0] - b[0];
                    } else if (a[1] != b[1]) {
                        return a[1] - b[1];
                    }
                    return a[2] - b[2];
                });
        int ans = 0;
        int[] dp = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            dp[i] = arr[i][2];
            for (int j = 0; j < i; j++) {
                if (arr[i][0] >= arr[j][0] && arr[i][1] >= arr[j][1] && arr[i][2] >= arr[j][2]) {
                    dp[i] = Math.max(dp[i], arr[i][2] + dp[j]);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}