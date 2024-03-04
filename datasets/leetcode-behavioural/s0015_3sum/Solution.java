package g0001_0100.s0015_3sum;

// #Medium #Top_100_Liked_Questions #Top_Interview_Questions #Array #Sorting #Two_Pointers
// #Data_Structure_II_Day_1_Array #Algorithm_II_Day_3_Two_Pointers #Udemy_Two_Pointers
// #Big_O_Time_O(n*log(n))_Space_O(n^2) #2023_08_09_Time_27_ms_(97.93%)_Space_51.7_MB_(23.15%)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("java:S127")
public class Solution {
//@ ensures(*The integer array parameter `nums` must not be null.*);
//@ ensures(*The size of the list of lists result is equal to the number of unique triplets `[nums[i], nums[j], nums[k]]` such that `i != j`, `i != k`, and `j != k`, and `nums[i] + nums[j] + nums[k] == 0`.*);
//@ ensures(*Each triplet `[nums[i], nums[j], nums[k]]` in the list result must not contain duplicate elements.*);
//@ ensures(*If there are no valid triplets that satisfy the conditions, the list result is empty.*);
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        final int len = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        int l;
        int r;
        for (int i = 0; i < len - 2; i++) {
            l = i + 1;
            r = len - 1;
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
                    while (l < r && nums[l + 1] == nums[l]) {
                        l++;
                    }
                    while (r > l && nums[r - 1] == nums[r]) {
                        r--;
                    }
                    l++;
                    r--;
                }
            }
            while (i < len - 1 && nums[i + 1] == nums[i]) {
                i++;
            }
        }
        return result;
    }
}