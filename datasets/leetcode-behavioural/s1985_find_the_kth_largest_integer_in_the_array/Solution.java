package g1901_2000.s1985_find_the_kth_largest_integer_in_the_array;

// #Medium #Array #String #Sorting #Heap_Priority_Queue #Divide_and_Conquer #Quickselect
// #2022_05_22_Time_34_ms_(77.42%)_Space_65.7_MB_(20.14%)

import java.util.Arrays;

@SuppressWarnings("java:S2234")
public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is greater than or equal to `k`.*);
//@ ensures(*Each string in `nums` represents an integer without leading zeros.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output string represents the `k`th largest integer in `nums`.*);
//@ ensures(*Duplicate numbers are counted distinctly.*);
    public String kthLargestNumber(String[] nums, int k) {
        Arrays.sort(nums, (n1, n2) -> compareStringInt(n2, n1));
        return nums[k - 1];
    }

    private int compareStringInt(String n1, String n2) {
        if (n1.length() != n2.length()) {
            return n1.length() < n2.length() ? -1 : 1;
        }
        for (int i = 0; i < n1.length(); i++) {
            int n1Digit = n1.charAt(i) - '0';
            int n2Digit = n2.charAt(i) - '0';
            if (n1Digit > n2Digit) {
                return 1;
            } else if (n2Digit > n1Digit) {
                return -1;
            }
        }
        return 0;
    }
}