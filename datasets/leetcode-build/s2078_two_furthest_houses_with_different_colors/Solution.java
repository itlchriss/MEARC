package g2001_2100.s2078_two_furthest_houses_with_different_colors;

// #Easy #Array #Greedy #2022_05_29_Time_0_ms_(100.00%)_Space_41.3_MB_(72.67%)

public class Solution {
	//@ requires(*The input array `colors` is not null.*);
	//@ requires(*The length of the input array `colors` is greater than or equal to 2.*);
	//@ requires(*The elements of the input array `colors` are integers.*);
	//@ requires(*The elements of the input array `colors` are within the range of 0 to 100.*);
	//@ ensures(*The return value is an integer representing the maximum distance between two houses with different colors.*);
	//@ ensures(*The return value is greater than or equal to 0.*);
    public int maxDistance(int[] colors) {
        int left = 0;
        int right = colors.length - 1;
        int max = 0;
        while (left < right) {
            if (colors[left] != colors[right]) {
                max = Math.max(max, right - left);
                break;
            } else {
                left++;
            }
        }
        left = 0;
        while (left < right) {
            if (colors[left] != colors[right]) {
                max = Math.max(max, right - left);
                break;
            } else {
                right--;
            }
        }
        return max;
    }
}