package g0001_0100.s0015_3sum;

// #Medium #Top_100_Liked_Questions #Top_Interview_Questions #Array #Sorting #Two_Pointers
// #Data_Structure_II_Day_1_Array #Algorithm_II_Day_3_Two_Pointers #Udemy_Two_Pointers
// #Big_O_Time_O(n*log(n))_Space_O(n^2) #2023_08_09_Time_27_ms_(97.93%)_Space_51.7_MB_(23.15%)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("java:S127")
public class Solution {
//@ requires(*The length of the integer array parameter `nums` is less than or equal to 3000 and is greater than or equal to 0.*);
//@ requires(*All the values in the integer array parameter `nums` are less than or equal to 100000 and are greater than or equal to -100000.*);
//@ ensures(*The length of the list result is equal to the number of unique triplets `[nums[i], nums[j], nums[k]]` such that `i != j`, `i != k`, and `j != k`, and `nums[i] + nums[j] + nums[k] == 0`.*);
//@ ensures(*Each element in the list result is a list containing three integers representing a valid triplet.*);
//@ ensures(*The list result must not contain duplicate triplets.*);
//@ ensures(*If the integer array parameter `nums` is empty, the list result is also empty.*);
//@ ensures(*If the integer array parameter `nums` contains only one element which is 0, the list result is empty.*);
//@ ensures(*If the integer array parameter `nums` is `[-1,0,1,2,-1,-4]`, the list result contains `[-1,-1,2]` and `[-1,0,1]`.*);
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        final int len = nums.length;
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        int l;
        int r;
        //@ assume \forall int j; 0 <= j < nums.length; -10000 <= nums[j] <= 10000;
        //@ assume 1000 >= nums.length >= 3;
        //@ loop_invariant 0 <= i <= len;
        for (int i = 0; i < len - 2; i++) {
            l = i + 1;
            r = len - 1;
            //@ maintaining 0 <= l <= nums.length - 1;
            //@ maintaining 0 <= r < nums.length;
            while (r > l) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum < 0) {
                    l++;
                } else if (sum > 0) {
                    r--;
                } else {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[l]);
                    list.add(nums[r]);
                    result.add(list);
                    //@ maintaining l <= r && 0 <= l < nums.length - 1;
                    while (l < r && nums[l + 1] == nums[l]) {
                        l++;
                    }
                    //@ maintaining r <= l;
                    while (r > l && nums[r - 1] == nums[r]) {
                        r--;
                    }
                    l++;
                    r--;
                }
            }
            //@ maintaining 0 <= i <= len - 1;
            while (i < len - 1 && nums[i + 1] == nums[i]) {
                i++;
            }
        }
        return result;
    }
}