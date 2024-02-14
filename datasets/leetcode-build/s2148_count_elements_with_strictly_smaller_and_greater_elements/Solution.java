package g2101_2200.s2148_count_elements_with_strictly_smaller_and_greater_elements;

// #Easy #Array #Sorting #2022_06_07_Time_0_ms_(100.00%)_Space_40.6_MB_(88.75%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is greater than or equal to 2.*);
	//@ ensures(*The method returns an integer representing the number of elements in the input array `nums` that have both a strictly smaller and a strictly greater element.*);
	//@ ensures(*The input array `nums` remains unchanged after the method is executed.*);
    public int countElements(int[] nums) {
        int min = nums[0];
        int max = nums[0];
        int minocr = 1;
        int maxocr = 1;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] < min) {
                min = nums[i];
                minocr = 1;
            } else if (nums[i] == min) {
                minocr++;
            }
            if (nums[i] > max) {
                max = nums[i];
                maxocr = 1;
            } else if (nums[i] == max) {
                maxocr++;
            }
        }
        return min == max ? 0 : nums.length - minocr - maxocr;
    }
}