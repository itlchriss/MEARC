package g2401_2500.s2449_minimum_number_of_operations_to_make_arrays_similar;

// #Hard #Array #Sorting #Greedy #2022_12_14_Time_57_ms_(87.86%)_Space_59.7_MB_(93.46%)

import java.util.ArrayList;
import java.util.Arrays;

@SuppressWarnings("java:S2184")
public class Solution {
	//@ requires(*The input arrays `nums` and `target` are not null.*);
	//@ requires(*The length of `nums` is equal to the length of `target`.*);
	//@ requires(*The length of `nums` is greater than 0.*);
	//@ requires(*The elements of `nums` and `target` are positive integers.*);
	//@ requires(*It is possible to make `nums` similar to `target`.*);
	//@ ensures(*The output is a non-negative integer representing the minimum number of operations required to make `nums` similar to `target`.*);
	//@ ensures(*The elements of `nums` have the same frequency as the elements of `target`.*);
    public long makeSimilar(int[] nums, int[] target) {
        ArrayList<Integer> evenNums = new ArrayList<>();
        ArrayList<Integer> oddNums = new ArrayList<>();
        ArrayList<Integer> evenTar = new ArrayList<>();
        ArrayList<Integer> oddTar = new ArrayList<>();
        Arrays.sort(nums);
        Arrays.sort(target);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 0) {
                evenNums.add(nums[i]);
            } else {
                oddNums.add(nums[i]);
            }
            if (target[i] % 2 == 0) {
                evenTar.add(target[i]);
            } else {
                oddTar.add(target[i]);
            }
        }
        long countPositiveIteration = 0;
        long countNegativeIteration = 0;
        for (int i = 0; i < evenNums.size(); i++) {
            int num = evenNums.get(i);
            int tar = evenTar.get(i);
            long diff = (long) num - tar;
            long iteration = diff / 2;
            if (diff > 0) {
                countNegativeIteration += iteration;
            } else if (diff < 0) {
                countPositiveIteration += Math.abs(iteration);
            }
        }
        for (int i = 0; i < oddNums.size(); i++) {
            int num = oddNums.get(i);
            int tar = oddTar.get(i);
            long diff = (long) num - tar;
            long iteration = diff / 2;
            if (diff > 0) {
                countNegativeIteration += iteration;
            } else if (diff < 0) {
                countPositiveIteration += Math.abs(iteration);
            }
        }
        long totalDifference = countPositiveIteration - countNegativeIteration;
        return totalDifference == 0
                ? countPositiveIteration
                : countPositiveIteration + Math.abs(totalDifference);
    }
}