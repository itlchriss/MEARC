package g1401_1500.s1464_maximum_product_of_two_elements_in_an_array;

// #Easy #Array #Sorting #Heap_Priority_Queue #2022_03_29_Time_1_ms_(90.39%)_Space_43.6_MB_(29.18%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` must not be null.*);
//@ ensures(*The length of the input array `nums` must be at least 2.*);
//@ ensures(*Each element in the input array `nums` must be a positive integer.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer value.*);
//@ ensures(*The returned value is the maximum product of two elements in the input array `nums`, where each element is decremented by 1.*);
    public int maxProduct(int[] nums) {
        if (nums == null) {
            return 0;
        }

        int first = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;
        for (int num : nums) {
            if (num >= first) {
                second = first;
                first = num;
            } else if (num >= second) {
                second = num;
            }
        }
        return (first - 1) * (second - 1);
    }
}