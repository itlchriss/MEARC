package g1001_1100.s1024_video_stitching;

// #Medium #Array #Dynamic_Programming #Greedy #2022_02_26_Time_1_ms_(88.78%)_Space_42.3_MB_(11.00%)

import java.util.Arrays;

public class Solution {
	//@ requires(*The input array `clips` is not null.*);
	//@ requires(*The length of the input array `clips` is greater than or equal to - Each element in the input array `clips` is an array of length - The start time of each clip is greater than or equal to - The end time of each clip is greater than or equal to the start time.*);
	//@ requires(*The end time of each clip is less than or equal to - The input `time` is greater than or equal to - The input `time` is less than or equal to*);
	//@ ensures(*The method returns an integer representing the minimum number of clips needed to cover the entire sporting event.*);
	//@ ensures(*If it is impossible to cover the entire sporting event, the method returns -1.*);
    public int videoStitching(int[][] clips, int time) {
        Arrays.sort(clips, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        int count = 0;
        int covered = 0;
        int i = 0;
        int start = 0;
        while (start < time) {
            while (i < clips.length && clips[i][0] <= start) {
                covered = Math.max(covered, clips[i][1]);
                i++;
            }
            if (start == covered) {
                return -1;
            }
            count++;
            start = covered;
        }
        return count;
    }
}