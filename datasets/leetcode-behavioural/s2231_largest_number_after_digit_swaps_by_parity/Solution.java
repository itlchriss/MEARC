package g2201_2300.s2231_largest_number_after_digit_swaps_by_parity;

// #Easy #Sorting #Heap_Priority_Queue #2022_06_12_Time_1_ms_(98.32%)_Space_39.4_MB_(76.43%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*1. The input `num` must be a positive integer.*);
//@ ensures(*2. The input `num` must be less than or equal to 10^9.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*1. The output must be an integer.*);
//@ ensures(*2. The output must be the largest possible value of `num` after any number of swaps.*);
//@ ensures(*3. The output must be obtained by swapping any two digits of `num` that have the same parity (both odd digits or both even digits).*);
//@ ensures(*4. The output must not be obtained by swapping a digit with a digit of different parity.*);
    public int largestInteger(int num) {
        char[] str = String.valueOf(num).toCharArray();
        char temp;
        for (int i = 0; i < str.length; i++) {
            temp = str[i];
            int swapIndex = i;
            boolean even = str[i] % 2 == 0;
            int max = Integer.MIN_VALUE;
            if (even) {
                for (int j = i + 1; j < str.length; j++) {
                    if (str[j] % 2 == 0 && str[j] > str[i] && str[j] > max) {
                        max = str[j];
                        temp = str[j];
                        swapIndex = j;
                    }
                }
            } else {
                for (int j = i + 1; j < str.length; j++) {
                    if (str[j] % 2 != 0 && str[j] > str[i] && str[j] > max) {
                        max = str[j];
                        temp = str[j];
                        swapIndex = j;
                    }
                }
            }
            char tempStr = str[i];
            str[i] = temp;
            str[swapIndex] = tempStr;
        }
        return Integer.valueOf(new String(str));
    }
}