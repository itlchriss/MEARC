package g1901_2000.s1964_find_the_longest_valid_obstacle_course_at_each_position;

// #Hard #Array #Binary_Search #Binary_Indexed_Tree
// #2022_05_21_Time_79_ms_(85.00%)_Space_146.3_MB_(35.00%)

public class Solution {
	//@ requires(*The input array `obstacles` is not null.*);
	//@ requires(*The length of the input array `obstacles` is greater than or equal to 1.*);
	//@ requires(*The elements in the input array `obstacles` are positive integers.*);
	//@ ensures(*The output array `ans` is not null.*);
	//@ ensures(*The length of the output array `ans` is equal to the length of the input array `obstacles`.*);
	//@ ensures(*The elements in the output array `ans` are non-negative integers.*);
	//@ ensures(*For every index `i` between 0 and `n - 1` (inclusive), `ans[i]` is the length of the longest obstacle course in `obstacles` that satisfies the given conditions.*);
    public int[] longestObstacleCourseAtEachPosition(int[] obstacles) {
        return longestIncreasingSubsequence(obstacles);
    }

    private int[] longestIncreasingSubsequence(int[] obstacles) {
        int len = 1;
        int length = obstacles.length;
        int[] ans = new int[length];
        int[] arr = new int[length];
        arr[0] = obstacles[0];
        ans[0] = 1;
        for (int i = 1; i < length; i++) {
            int val = obstacles[i];
            if (val >= arr[len - 1]) {
                arr[len++] = val;
                ans[i] = len;
            } else {
                int idx = binarySearch(arr, 0, len - 1, val);
                arr[idx] = val;
                ans[i] = idx + 1;
            }
        }
        return ans;
    }

    private int binarySearch(int[] arr, int lo, int hi, int val) {
        int ans = -1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (val >= arr[mid]) {
                lo = mid + 1;
            } else {
                ans = mid;
                hi = mid - 1;
            }
        }
        return ans;
    }
}