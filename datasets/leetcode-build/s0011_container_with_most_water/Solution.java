package g0001_0100.s0011_container_with_most_water;

// #Medium #Top_100_Liked_Questions #Top_Interview_Questions #Array #Greedy #Two_Pointers
// #Algorithm_II_Day_4_Two_Pointers #Big_O_Time_O(n)_Space_O(1)
// #2023_08_09_Time_3_ms_(94.75%)_Space_56.2_MB_(5.82%)

public class Solution {
	//@ requires(*The input array `height` is not null.*);
	//@ requires(*The length of the input array `height` is greater than or equal to 2.*);
	//@ requires(*The values in the input array `height` are non-negative integers.*);
	//@ requires(*The values in the input array `height` are within the range of 0 to 10^4.*);
	//@ ensures(*The method returns an integer representing the maximum area of water that the container can contain.*);
	//@ ensures(*The returned maximum area is non-negative.*);
	//@ ensures(*The returned maximum area is the result of multiplying the width of the container (the difference between the indices of the two lines) with the minimum height of the two lines.*);
	//@ ensures(*The two lines that form the container are selected in such a way that the area of water contained in the container is maximized.*);
	//@ ensures(*The two lines that form the container are vertical and parallel to the y-axis.*);
	//@ ensures(*The two lines that form the container have their endpoints at coordinates (i, height[i]) and (i, 0) for some index i in the input array `height`.*);
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