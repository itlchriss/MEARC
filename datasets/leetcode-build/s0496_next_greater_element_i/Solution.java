package g0401_0500.s0496_next_greater_element_i;

// #Easy #Array #Hash_Table #Stack #Monotonic_Stack #Programming_Skills_I_Day_5_Function
// #2022_07_21_Time_4_ms_(81.18%)_Space_43.7_MB_(77.46%)

import java.util.HashMap;
import java.util.Map;

public class Solution {
	//@ requires(*The input arrays `nums1` and `nums2` are not null.*);
	//@ requires(*The length of `nums1` is greater than or equal to - The length of `nums2` is greater than or equal to - The length of `nums1` is less than or equal to the length of `nums2`.*);
	//@ requires(*All integers in `nums1` and `nums2` are unique.*);
	//@ requires(*All integers in `nums1` also appear in `nums2`.*);
	//@ ensures(*The returned array `ans` is not null.*);
	//@ ensures(*The length of `ans` is equal to the length of `nums1`.*);
	//@ ensures(*Each element in `ans` is the next greater element of the corresponding element in `nums1` in `nums2`.*);
	//@ ensures(*If there is no next greater element for an element in `nums1`, the corresponding element in `ans` is -1.*);
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            indexMap.put(nums2[i], i);
        }
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