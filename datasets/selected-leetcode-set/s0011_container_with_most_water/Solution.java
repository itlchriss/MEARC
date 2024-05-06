package g0001_0100.s0011_container_with_most_water;

// #Medium #Top_100_Liked_Questions #Top_Interview_Questions #Array #Greedy #Two_Pointers
// #Algorithm_II_Day_4_Two_Pointers #Big_O_Time_O(n)_Space_O(1)
// #2023_08_09_Time_3_ms_(94.75%)_Space_56.2_MB_(5.82%)

public class Solution {
//@ ensures(*The integer array parameter `height` must not be null.*);
//@ ensures(*The length of the integer array parameter `height` is greater than or equal to 2 and is less than or equal to 100000.*);
//@ ensures(*All values in the integer array parameter `height` are greater than or equal to 0 and are less than or equal to 10000.*);
//@ ensures(*The integer result is the maximum area of water that can be contained by the two lines in the container formed by the vertical lines represented by the integer array parameter `height`.*);
//@ ensures(*The integer result is greater than or equal to 0.*);
    public int maxArea(int[] height) {
        int maxArea = -1;
        int left = 0;
        int right = height.length - 1;
        //@ assume 1 <= height.length <= Integer.MAX_VALUE;
        //@ ghost int k;
        //@ maintaining 0 <= left < height.length;
        //@ maintaining 0 <= right < height.length;
        while (left < right) {
            if (height[left] < height[right]) {
                //@ set k = height[left];
                //@ assume Integer.MIN_VALUE  <= k * (right - left) <= Integer.MAX_VALUE;
                maxArea = Math.max(maxArea, height[left] * (right - left));
                left++;
            } else {
                //@ set k = height[right];
                //@ assume Integer.MIN_VALUE <= k * (right - left) <= Integer.MAX_VALUE;
                maxArea = Math.max(maxArea, height[right] * (right - left));
                right--;
            }
        }
        return maxArea;
    }
}