package g0401_0500.s0496_next_greater_element_i;

// #Easy #Array #Hash_Table #Stack #Monotonic_Stack #Programming_Skills_I_Day_5_Function
// #2022_07_21_Time_4_ms_(81.18%)_Space_43.7_MB_(77.46%)

import java.util.HashMap;
import java.util.Map;

public class Solution {
//@ requires(*For each value in the integer array parameter `nums1`, find the index `j` in the integer array parameter `nums2` such that the value in `nums1` is equal to the value in `nums2` at index `j`.*);
//@ ensures(*If there is a next greater element to the right of the value in `nums2` at index `j`, the corresponding value in the integer array result is the next greater element.*);
//@ ensures(*If there is no next greater element to the right of the value in `nums2` at index `j`, the corresponding value in the integer array result is -1.*);
//@ ensures(*The length of the integer array result is equal to the length of the integer array parameter `nums1`.*);
//@ ensures(*All values in the integer array result are either the next greater element to the right or -1.*);
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