package g0501_0600.s0547_number_of_provinces;

// #Medium #Depth_First_Search #Breadth_First_Search #Graph #Union_Find
// #Algorithm_II_Day_6_Breadth_First_Search_Depth_First_Search
// #Graph_Theory_I_Day_8_Standard_Traversal #Level_2_Day_19_Union_Find
// #2022_08_02_Time_2_ms_(69.51%)_Space_54.2_MB_(42.16%)

import java.util.Arrays;

public class Solution {
	//@ requires(*The input `arr` is a 2D array representing the connectivity between cities.*);
	//@ requires(*The length of `arr` is equal to the number of cities `n`.*);
	//@ requires(*The length of each row in `arr` is equal to the number of cities `n`.*);
	//@ requires(*Each element in `arr` is either 0 or 1.*);
	//@ requires(*The diagonal elements of `arr` are all 1.*);
	//@ ensures(*The method returns an integer representing the total number of provinces.*);
	//@ ensures(*The returned value is greater than or equal to 0.*);
	//@ ensures(*The returned value is less than or equal to the number of cities `n`.*);
    public int findCircleNum(int[][] arr) {
        int[] parent = new int[arr.length];
        Arrays.fill(parent, -1);
        int ans = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr[i].length; j++) {
                if (arr[i][j] == 1) {
                    ans += union(i, j, parent);
                }
            }
        }
        return arr.length - ans;
    }

    private int union(int a, int b, int[] arr) {
        int ga = find(a, arr);
        int gb = find(b, arr);
        if (ga != gb) {
            arr[gb] = ga;
            return 1;
        }
        return 0;
    }

    private int find(int a, int[] arr) {
        if (arr[a] == -1) {
            return a;
        }
        arr[a] = find(arr[a], arr);
        return arr[a];
    }
}