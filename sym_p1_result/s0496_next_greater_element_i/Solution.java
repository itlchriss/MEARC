package g0401_0500.s0496_next_greater_element_i;

// #Easy #Array #Hash_Table #Stack #Monotonic_Stack #Programming_Skills_I_Day_5_Function
// #2022_07_21_Time_4_ms_(81.18%)_Space_43.7_MB_(77.46%)

import java.util.HashMap;
import java.util.Map;

public class Solution {
//@ requires(*The next greater element of some element `x` in an array is the first greater element that is to the right of `x` in the same array.*);
//@ requires(*You are given two distinct 0-indexed integer arrays param_nums1 and param_nums2, where param_nums1 is a subset of param_nums2.*);
//@ requires(*For each `0 <= i < nums1.length`, find the index `j` such that `nums1[i] == nums2[j]` and determine the next greater element of `nums2[j]` in param_nums2.*);
//@ requires(*If there is no next greater element, then the answer for this query is `-1`.*);
//@ requires(*Return an array `ans` of length `nums1.length` such that `ans[i]` is the next greater element as described above.*);
//@ requires(*Example 1:*);
//@ requires(*Input: nums1 = [4,1,2], nums2 = [1,3,4,2]*);
//@ requires(*Output: [-1,3,-1]*);
//@ requires(*Explanation:*);
//@ requires(*The next greater element for each value of nums1 is as follows:*);
//@ requires(*4 is underlined in nums2 = [1,3,4,2].*);
//@ requires(*There is no next greater element, so the answer is -1.*);
//@ requires(*1 is underlined in nums2 = [1,3,4,2].*);
//@ requires(*The next greater element is 3.*);
//@ requires(*2 is underlined in nums2 = [1,3,4,2].*);
//@ requires(*There is no next greater element, so the answer is -1.*);
//@ requires(*Example 2:*);
//@ requires(*Input: nums1 = [2,4], nums2 = [1,2,3,4]*);
//@ requires(*Output: [3,-1]*);
//@ requires(*Explanation:*);
//@ requires(*The next greater element for each value of nums1 is as follows:*);
//@ requires(*2 is underlined in nums2 = [1,2,3,4].*);
//@ requires(*The next greater element is 3.*);
//@ requires(*4 is underlined in nums2 = [1,2,3,4].*);
//@ requires(*There is no next greater element, so the answer is -1.*);
//@ requires(*Constraints:*);
//@ requires(*`1 <= nums1.length <= nums2.length <= 1000`*);
//@ requires(*<code>0 <= nums1[i], nums2[i] <= 10<sup>4</sup></code>*);
//@ requires(*All integers in param_nums1 and param_nums2 are unique.*);
//@ requires(*All the integers of param_nums1 also appear in param_nums2.*);
//@ requires(*Follow up: Could you find an `O(nums1.length + nums2.length)` solution?*);
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> indexMap = new HashMap<>();
        //@ maintaining 0 <= i <= nums2.length;
        for (int i = 0; i < nums2.length; i++) {
            indexMap.put(nums2[i], i);
        }
        //@ maintaining 0 <= i <= nums1.length;
        for (int i = 0; i < nums1.length; i++) {
            int num = nums1[i];
            int index = indexMap.get(num);
            if (index == nums2.length - 1) {
                nums1[i] = -1;
            } else {
                boolean found = false;
                while (index < nums2.length) {
                    if (nums2[index] > num) {
                        nums1[i] = nums2[index];
                        found = true;
                        break;
                    }
                    index++;
                }
                if (!found) {
                    nums1[i] = -1;
                }
            }
        }
        return nums1;
    }
}