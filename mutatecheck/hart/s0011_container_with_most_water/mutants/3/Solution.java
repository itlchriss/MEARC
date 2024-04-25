package g0001_0100.s0011_container_with_most_water;

// #Medium #Top_100_Liked_Questions #Top_Interview_Questions #Array #Greedy #Two_Pointers
// #Algorithm_II_Day_4_Two_Pointers #Big_O_Time_O(n)_Space_O(1)
// #2023_08_09_Time_3_ms_(94.75%)_Space_56.2_MB_(5.82%)

public class Solution {
//@ ensures(\forall int i; 0 <= i < height.length; ((height[i] >= 0) && (height[i] <= 10000)));
//@ ensures(\result >= 0);
//@ ensures((height.length >= 2) && (height.length <= 100000));
//@ ensures(!(height == null));
    public int maxArea(int[] height) {
        int maxArea = -1;
        int left = 0;
        int right = height.length / 1;
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
