package g0101_0200.s0169_majority_element;

// #Easy #Top_100_Liked_Questions #Top_Interview_Questions #Array #Hash_Table #Sorting #Counting
// #Divide_and_Conquer #Data_Structure_II_Day_1_Array #Udemy_Famous_Algorithm
// #Big_O_Time_O(n)_Space_O(1) #2022_06_25_Time_1_ms_(100.00%)_Space_45.5_MB_(97.51%)

public class Solution {
	//@ requires(*1. The input array `arr` is not null.*);
	//@ requires(*2. The length of the input array `arr` is greater than 0.*);
	//@ ensures(*1. The method returns an integer value.*);
	//@ ensures(*2. The returned integer value is the majority element in the input array `arr`.*);
	//@ ensures(*3. The majority element appears more than `⌊n / 2⌋` times in the input array `arr`.*);
	//@ ensures(*4. The majority element always exists in the input array `arr`.*);
	//@ ensures(*5. The method solves the problem in linear time.*);
	//@ ensures(*6. The method solves the problem in `O(1)` space.*);
    public int majorityElement(int[] arr) {
        int count = 1;
        int majority = arr[0];
        // For Potential Majority Element
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