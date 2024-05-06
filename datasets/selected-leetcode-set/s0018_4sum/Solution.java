package g0001_0100.s0018_4sum;

// #Medium #Array #Sorting #Two_Pointers #2023_08_09_Time_3_ms_(99.77%)_Space_43.9_MB_(82.30%)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("java:S135")
public class Solution {
//@ ensures(*The length of the integer array parameter `nums` is greater than or equal to 1 and is less than or equal to 200.*);
//@ ensures(*All values in the integer array parameter `nums` are greater than or equal to -10^9 and are less than or equal to 10^9.*);
//@ ensures(*The integer parameter `target` is greater than or equal to -10^9 and is less than or equal to 10^9.*);
//@ ensures(*The length of the list of lists result is not fixed and can vary.*);
//@ ensures(*Each list in the list of lists result contains 4 distinct integers from the integer array parameter `nums`.*);
//@ ensures(*The sum of the 4 integers in each list in the list of lists result is equal to the integer parameter `target`.*);
//@ ensures(*All lists in the list of lists result are unique.*);
    public List<List<Integer>> fourSum(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        //@ ghost int cons = n;
        // loop_invariant 0 <= i <= cons - 3;
        //@ maintaining 0 <= i <= nums.length;
        //@ decreases cons - 3 - i;
        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            if ((long) nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                break;
            }
            if ((long) nums[i] + nums[n - 3] + nums[n - 2] + nums[n - 1] < target) {
                continue;
            }
            //@ loop_invariant i + 1 <= j <= cons - 2;
            //@ decreases cons - 2 - j;
            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                if ((long) nums[j] + nums[j + 1] + nums[j + 2] > target - nums[i]) {
                    break;
                }
                if ((long) nums[j] + nums[n - 2] + nums[n - 1] < target - nums[i]) {
                    continue;
                }
                int tempTarget = target - (nums[i] + nums[j]);
                int low = j + 1;
                int high = n - 1;
                //@ maintaining 0 <= low < nums.length;
                //@ maintaining 0 <= high < nums.length;
                while (low < high) {
                    int curSum = nums[low] + nums[high];
                    if (curSum == tempTarget) {
                        List<Integer> tempList = new ArrayList<>();
                        tempList.add(nums[i]);
                        tempList.add(nums[j]);
                        tempList.add(nums[low]);
                        tempList.add(nums[high]);
                        result.add(tempList);
                        low++;
                        high--;
                        //@ maintaining 0 <= low < nums.length;
                        while (low < high && nums[low] == nums[low - 1]) {
                            low++;
                        }
                        //@ maintaining 0 <= high < nums.length;
                        while (low < high && nums[high] == nums[high + 1]) {
                            high--;
                        }
                    } else if (curSum < tempTarget) {
                        low++;
                    } else {
                        high--;
                    }
                }
            }
        }
        return result;
    }
}