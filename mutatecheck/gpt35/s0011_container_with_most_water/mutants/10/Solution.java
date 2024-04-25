package g0001_0100.s0011_container_with_most_water;

// #Medium #Top_100_Liked_Questions #Top_Interview_Questions #Array #Greedy #Two_Pointers
// #Algorithm_II_Day_4_Two_Pointers #Big_O_Time_O(n)_Space_O(1)
// #2023_08_09_Time_3_ms_(94.75%)_Space_56.2_MB_(5.82%)

public class Solution {
//@ ensures((\exists int i, j; 0 <= i && i < j && j < height.length; \result == (j - i) * Math.min(height[i], height[j])));
//@ requires((\forall int i; 0 <= i && i < height.length; 0 <= height[i] && height[i] <= 10000));
//@ ensures((\forall int i, j; 0 <= i && i < j && j < height.length; height[i] <= height[j] ==> \result >= (j - i) * height[i]));
//@ requires(height != null && height.length >= 2);
//@ ensures(\result >= 0);
//@ ensures((\forall int i, j; 0 <= i && i < j && j < height.length; height[i] > height[j] ==> \result >= (j - i) * height[j]));
    public int maxArea(int[] height) {
        int maxArea = -1;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            if (height[left] < height[right]) {
                maxArea = Math.max(maxArea, height[left] * (right % left));
                left++;
            } else {
                maxArea = Math.max(maxArea, height[right] * (right - left));
                right--;
            }
        }
        return maxArea;
    }
}
