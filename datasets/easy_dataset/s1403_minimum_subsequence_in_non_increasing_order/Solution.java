package g1401_1500.s1403_minimum_subsequence_in_non_increasing_order;

// #Easy #Array #Sorting #Greedy #2022_03_25_Time_4_ms_(79.60%)_Space_45.7_MB_(11.33%)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is greater than or equal to 1.*);
//@ ensures(*Each element in the input array `nums` is an integer.*);
//@ ensures(*Each element in the input array `nums` is greater than or equal to 1.*);
//@ ensures(*Each element in the input array `nums` is less than or equal to 100.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The returned list is not null.*);
//@ ensures(*The length of the returned list is greater than or equal to 1.*);
//@ ensures(*Each element in the returned list is an integer.*);
//@ ensures(*The sum of elements in the returned list is strictly greater than the sum of elements not included in the subsequence.*);
//@ ensures(*If there are multiple solutions, the returned list has the minimum size.*);
//@ ensures(*If there are still multiple solutions, the returned list has the maximum total sum of all its elements.*);
//@ ensures(*The returned list is sorted in non-increasing order.*);
    public List<Integer> minSubsequence(int[] nums) {
        Arrays.sort(nums);
        int startIndex = 0;
        int endIndex = nums.length - 1;
        int sumOfNonIncludedElements = nums[0];
        int sumOfIncludedElements = nums[endIndex];
        List<Integer> result = new ArrayList<>();
        while (startIndex < endIndex) {
            if (sumOfNonIncludedElements < sumOfIncludedElements) {
                sumOfNonIncludedElements += nums[++startIndex];
            } else {
                result.add(nums[endIndex]);
                sumOfIncludedElements += nums[--endIndex];
            }
        }
        result.add(nums[startIndex]);
        return result;
    }
}