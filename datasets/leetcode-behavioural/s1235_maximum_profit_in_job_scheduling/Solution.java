package g1201_1300.s1235_maximum_profit_in_job_scheduling;

// #Hard #Array #Dynamic_Programming #Sorting #Binary_Search
// #2022_03_12_Time_27_ms_(89.19%)_Space_73.8_MB_(49.40%)

import java.util.Arrays;
import java.util.Comparator;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The lengths of the `startTime`, `endTime`, and `profit` arrays are equal.*);
//@ ensures(*The length of the arrays is not greater than 5 * 10^4.*);
//@ ensures(*The elements of the `startTime` array are greater than or equal to 1 and less than the corresponding elements in the `endTime` array.*);
//@ ensures(*The elements of the `endTime` array are less than or equal to 10^9.*);
//@ ensures(*The elements of the `profit` array are greater than or equal to 1 and less than or equal to 10^4.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer value representing the maximum profit that can be obtained.*);
//@ ensures(*The maximum profit is calculated by selecting a subset of jobs such that there are no two jobs with overlapping time ranges.*);
//@ ensures(*If multiple subsets have the same maximum profit, any one of them can be chosen as the result.*);
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        int[][] time = new int[n][3];
        for (int i = 0; i < n; i++) {
            time[i][0] = startTime[i];
            time[i][1] = endTime[i];
            time[i][2] = profit[i];
        }
        Arrays.sort(time, Comparator.comparingInt(a -> a[1]));
        int[][] maxP = new int[n][2];
        int lastPos = -1;
        int currProfit;
        for (int i = 0; i < n; i++) {
            currProfit = time[i][2];
            for (int j = lastPos; j >= 0; j--) {
                if (maxP[j][1] <= time[i][0]) {
                    currProfit += maxP[j][0];
                    break;
                }
            }
            if (lastPos == -1 || currProfit > maxP[lastPos][0]) {
                lastPos++;
                maxP[lastPos][0] = currProfit;
                maxP[lastPos][1] = time[i][1];
            }
        }
        return maxP[lastPos][0];
    }
}