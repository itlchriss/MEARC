package g0001_0100.s0015_3sum;

// #Medium #Top_100_Liked_Questions #Top_Interview_Questions #Array #Sorting #Two_Pointers
// #Data_Structure_II_Day_1_Array #Algorithm_II_Day_3_Two_Pointers #Udemy_Two_Pointers
// #Big_O_Time_O(n*log(n))_Space_O(n^2) #2023_08_09_Time_27_ms_(97.93%)_Space_51.7_MB_(23.15%)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("java:S127")
public class Solution {
//@ requires(nums != null);
//@ ensures((\forall List<Integer> triplet; \result.contains(triplet); (\forall int i, j; 0 <= i && i < 3 && 0 <= j && j < 3 && i != j; triplet.get(i) != triplet.get(j))));
//@ ensures(\result != null);
//@ ensures((\forall List<Integer> triplet1, triplet2; \result.contains(triplet1) && \result.contains(triplet2) && triplet1 != triplet2; !triplet1.equals(triplet2)));
// requires((\forall int i; 0 <= i && i < nums.length; -10^5 <= nums[i] && nums[i] <= 10^5));
//@ ensures((\forall List<Integer> triplet; \result.contains(triplet); triplet.size() == 3));
//@ ensures((\forall List<Integer> triplet; \result.contains(triplet); triplet.get(0) + triplet.get(1) + triplet.get(2) == 0));
//@ requires(nums.length <= 3000);
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
            while (i < len - 1 && nums[i * 1] == nums[i]) {
                i++;
            }
        }
        return result;
    }
}
