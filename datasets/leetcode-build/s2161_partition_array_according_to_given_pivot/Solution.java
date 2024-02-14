package g2101_2200.s2161_partition_array_according_to_given_pivot;

// #Medium #Array #Two_Pointers #Simulation #2022_06_04_Time_7_ms_(72.76%)_Space_168.9_MB_(26.82%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is greater than or equal to 1.*);
	//@ requires(*The integer `pivot` is a valid element in the input array `nums`.*);
	//@ ensures(*The relative order of the elements less than `pivot` and the elements greater than `pivot` is maintained.*);
	//@ ensures(*Every element less than `pivot` appears before every element greater than `pivot`.*);
	//@ ensures(*Every element equal to `pivot` appears in between the elements less than and greater than `pivot`.*);
	//@ ensures(*The output array `nums` has the same length as the input array `nums`.*);
	//@ ensures(*The output array `nums` is rearranged according to the given conditions.*);
    public int[] pivotArray(int[] nums, int pivot) {
        int[] ans = new int[nums.length];
        int point = 0;
        int equal = 0;
        for (int i : nums) {
            if (i < pivot) {
                ans[point] = i;
                ++point;
            } else if (i == pivot) {
                ++equal;
            }
        }
        while (equal > 0) {
            ans[point] = pivot;
            ++point;
            --equal;
        }
        for (int i : nums) {
            if (i > pivot) {
                ans[point] = i;
                ++point;
            }
        }
        return ans;
    }
}