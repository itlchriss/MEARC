package g0401_0500.s0496_next_greater_element_i;

// #Easy #Array #Hash_Table #Stack #Monotonic_Stack #Programming_Skills_I_Day_5_Function
// #2022_07_21_Time_4_ms_(81.18%)_Space_43.7_MB_(77.46%)

import java.util.HashMap;
import java.util.Map;

public class Solution {
//@ requires(*The length of the integer array parameter `nums1` is less than or equal to the length of the integer array parameter `nums2` and is greater than or equal to 1.*);
//@ requires(*The length of the integer array parameter `nums2` is less than or equal to 1000 and is greater than or equal to 1.*);
//@ requires(*All values in the integer array parameter `nums1` are less than or equal to 10000 and are greater than or equal to 0.*);
//@ requires(*All values in the integer array parameter `nums2` are less than or equal to 10000 and are greater than or equal to 0.*);
//@ requires(*All integers in the integer array parameter `nums1` are unique.*);
//@ requires(*All integers in the integer array parameter `nums2` are unique.*);
//@ ensures(*The integer result array should have the same length as the integer array parameter `nums1`.*);
//@ ensures(*If there is no next greater element for a value in `nums1`, then the corresponding value in the integer result array should be -1.*);
//@ ensures(*The integer result array should be in the same order as the integer array parameter `nums1`.*);
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