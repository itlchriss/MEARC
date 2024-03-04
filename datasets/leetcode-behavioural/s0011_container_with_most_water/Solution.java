package g0001_0100.s0011_container_with_most_water;

// #Medium #Top_100_Liked_Questions #Top_Interview_Questions #Array #Greedy #Two_Pointers
// #Algorithm_II_Day_4_Two_Pointers #Big_O_Time_O(n)_Space_O(1)
// #2023_08_09_Time_3_ms_(94.75%)_Space_56.2_MB_(5.82%)

public class Solution {
//@ ensures(*The integer array parameter `height` must not be null.*);
//@ ensures(*The integer result is the maximum area of water that the container can contain based on the integer array parameter `height`.*);
//@ ensures(*The maximum area of water is calculated by finding the area between two vertical lines represented by the array elements, where the area is the minimum of the two heights multiplied by the distance between the two lines.*);
//@ ensures(*The maximum area of water must be calculated by considering all possible pairs of vertical lines in the array and finding the pair that gives the maximum area.*);
    public int maxArea(int[] height) {
        int maxArea = -1;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            if (height[left] < height[right]) {
                maxArea = Math.max(maxArea, height[left] * (right - left));
                left++;
            } else {
                maxArea = Math.max(maxArea, height[right] * (right - left));
                right--;
            }
        }
        return maxArea;
    }
}