package g0101_0200.s0169_majority_element;

// #Easy #Top_100_Liked_Questions #Top_Interview_Questions #Array #Hash_Table #Sorting #Counting
// #Divide_and_Conquer #Data_Structure_II_Day_1_Array #Udemy_Famous_Algorithm
// #Big_O_Time_O(n)_Space_O(1) #2022_06_25_Time_1_ms_(100.00%)_Space_45.5_MB_(97.51%)

public class Solution {
//@ requires(*The integer array parameter `arr` must not be null.*);
//@ requires(*The majority element is the element that appears more than ⌊n / 2⌋ times, where `n` is the size of the array `arr`.*);
//@ requires(*The method must solve the problem in linear time and in O(1) space.*);
//@ ensures(*The integer result is the majority element in the integer array parameter `arr`.*);
//@ ensures(*The integer result is within the range of -2^31 to 2^31 - 1.*);
    public int majorityElement(int[] arr) {
        int count = 1;
        //@ assume 1 <= arr.length <= Math.pow(50, 4);
        int majority = arr[0];
        // For Potential Majority Element
        // maintaining 1 <= i <= arr.length;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == majority) {
                count++;
            } else {
                if (count > 1) {
                    count--;
                } else {
                    majority = arr[i];
                }
            }
        }
        // For Confirmation
        count = 0;
        for (int j : arr) {
            if (j == majority) {
                count++;
            }
        }
        if (count >= (arr.length / 2) + 1) {
            return majority;
        } else {
            return -1;
        }
    }
}