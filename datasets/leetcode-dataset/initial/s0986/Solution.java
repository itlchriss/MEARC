package g0901_1000.s0986_interval_list_intersections;

// #Medium #Array #Two_Pointers #Algorithm_II_Day_4_Two_Pointers
// #2022_03_31_Time_2_ms_(99.95%)_Space_43.1_MB_(85.56%)

import java.util.ArrayList;

public class Solution {
<<<<<<< HEAD
=======
//@ ensures(*Return _the intersection of these two interval lists_.*);

>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        ArrayList<int[]> list = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < firstList.length && j < secondList.length) {
            int start = Math.max(firstList[i][0], secondList[j][0]);
            int end = Math.min(firstList[i][1], secondList[j][1]);
            if (start <= end) {
                list.add(new int[] {start, end});
            }
            if (firstList[i][1] > end) {
                j++;
            } else {
                i++;
            }
        }
        return list.toArray(new int[0][]);
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
