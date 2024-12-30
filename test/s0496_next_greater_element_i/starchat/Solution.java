package g0401_0500.s0496_next_greater_element_i;

// #Easy #Array #Hash_Table #Stack #Monotonic_Stack #Programming_Skills_I_Day_5_Function
// #2022_07_21_Time_4_ms_(81.18%)_Space_43.7_MB_(77.46%)

import java.util.HashMap;
import java.util.Map;

public class Solution {
//@ requires(*The length of the integer array parameter `nums1` is less than or equal to the length of the integer array parameter `nums2` and is greater than or equal to 1.*);
//@ requires(*All values in the integer array parameter `nums1` are less than or equal to 10000 and are greater than or equal to 0.*);
//@ requires(*All values in the integer array parameter `nums2` are less than or equal to 10000 and are greater than or equal to 0.*);
//@ requires(*All integers in the integer array parameter `nums1` are unique.*);
//@ requires(*All integers in the integer array parameter `nums2` are unique.*);
//@ requires(*All the integers of `nums1` also appear in `nums2`.*);
//@ requires(**);
//@ requires(*The given context is about finding the next greater element for each element in an array `nums1` that is a subset of another array `nums2`. The method signature `public int[] nextGreaterElement(int[] nums1, int[] nums2)` is provided to solve this problem. The method behavioural specifications outline the constraints and requirements for the input parameters and the expected output format.*);
//@ requires(**);
//@ requires(*The given context is related to the "Next Greater Element I" problem, which involves finding the next greater element for each element in an array `nums1` that is a subset of another array `nums2`. The method signature `public int[] nextGreaterElement(int[] nums1, int[] nums2)` is provided to solve this problem. The method behavioural specifications outline the constraints and requirements for the input parameters and the expected output format.*);
//@ requires(**);
//@ requires(*The constraints and requirements for the input parameters are as follows:*);
//@ requires(**);
//@ requires(*The length of the integer array parameter `nums1` is less than or equal to the length of the integer array parameter `nums2` and is greater than or equal to 1.*);
//@ requires(*All values in the integer array parameter `nums1` are less than or equal to 10000 and are greater than or equal to 0.*);
//@ requires(*All values in the integer array parameter `nums2` are less than or equal to 10000 and are greater than or equal to 0.*);
//@ requires(*All integers in the integer array parameter `nums1` are unique.*);
//@ requires(*All integers in the integer array parameter `nums2` are unique.*);
//@ requires(*All the integers of `nums1` also appear in `nums2`.*);
//@ requires(**);
//@ requires(*The expected output format is an integer array `ans` of the same length as the integer array parameter `nums1`. The `i`-th element of the `ans` array represents the next greater element for the `i`-th element of `nums1` in `nums2`. If there is no next greater element, then the answer for that query is `-1`.*);
//@ requires(**);
//@ requires(*The given context is related to the "Next Greater Element I" problem, which involves finding the next greater element for each element in an array `nums1` that is a subset of another array `nums2`. The method signature `public int[] nextGreaterElement(int[] nums1, int[] nums2)` is provided to solve this problem. The method behavioural specifications outline the constraints and requirements for the input parameters and the expected output format.*);
//@ requires(**);
//@ requires(*The constraints and requirements for the input parameters are as follows:*);
//@ requires(**);
//@ requires(*The length of the integer array parameter `nums1` is less than or equal to the length of the integer array parameter `nums2` and is greater than or equal to 1.*);
//@ requires(*All values in the integer array parameter `nums1` are less than or equal to 10000 and are greater than or equal to 0.*);
//@ requires(*All values in the integer array parameter `nums2` are less than or equal to 10000 and are greater than or equal to 0.*);
//@ requires(*All integers in the integer array parameter `nums1` are unique.*);
//@ requires(*All integers in the integer array parameter `nums2` are unique.*);
//@ requires(*All the integers of `nums1` also appear in `nums2`.*);
//@ requires(**);
//@ requires(*The expected output format is an integer array `ans` of the same length as the integer array parameter `nums1`. The `i`-th element of the `ans` array represents the next greater*);
//@ ensures(*The integer result array is of the same length as the integer array parameter `nums1`.*);
//@ ensures(*If the integer array parameter `nums1` is equal to [4,1,2] and the integer array parameter `nums2` is equal to [1,3,4,2], the integer result array is equal to [-1,3,-1].*);
//@ ensures(*If the integer array parameter `nums1` is equal to [2,4] and the integer array parameter `nums2` is equal to [1,2,3,4], the integer result array is equal to [3,-1].*);
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