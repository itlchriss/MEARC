package g0701_0800.s0744_find_smallest_letter_greater_than_target;

// #Easy #Array #Binary_Search #Binary_Search_I_Day_4
// #2022_04_30_Time_0_ms_(100.00%)_Space_47.4_MB_(68.35%)

public class Solution {
	//@ requires(*1. The `letters` array is not null.*);
	//@ requires(*2. The `letters` array is sorted in non-decreasing order.*);
	//@ requires(*3. The `letters` array contains at least two different characters.*);
	//@ requires(*4. The `target` character is a lowercase English letter.*);
	//@ ensures(*1. The returned character is larger than the `target` character.*);
	//@ ensures(*2. The returned character is the smallest character in the `letters` array that is larger than the `target` character.*);
	//@ ensures(*3. If there is no character in the `letters` array that is larger than the `target` character, the returned character is the smallest character in the `letters` array.*);
    public char nextGreatestLetter(char[] letters, char target) {
        int start = 0;
        int end = letters.length - 1;
        // If target is greater than last element return first element of the array.
        if (letters[letters.length - 1] <= target) {
            return letters[start];
        }
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (letters[mid] <= target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return letters[start];
    }
}