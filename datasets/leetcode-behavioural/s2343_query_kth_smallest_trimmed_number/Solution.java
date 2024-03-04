package g2301_2400.s2343_query_kth_smallest_trimmed_number;

// #Medium #Array #String #Sorting #Heap_Priority_Queue #Divide_and_Conquer #Quickselect #Radix_Sort
// #2022_07_19_Time_52_ms_(75.00%)_Space_58.8_MB_(37.50%)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The input array `queries` is not null.*);
//@ ensures(*The length of `nums` is greater than or equal to - The length of each string in `nums` is greater than or equal to - The length of each string in `nums` is equal to the length of the first string in `nums`.*);
//@ ensures(*The length of `queries` is greater than or equal to - The length of each array in `queries` is equal to - The value of `k` in each query is greater than or equal to - The value of `k` in each query is less than or equal to the length of `nums`.*);
//@ ensures(*The value of `trim` in each query is greater than or equal to - The value of `trim` in each query is less than or equal to the length of each string in `nums`.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The returned array `answer` is not null.*);
//@ ensures(*The length of `answer` is equal to the length of `queries`.*);
//@ ensures(*Each element in `answer` is an integer.*);
//@ ensures(*The integers in `answer` represent the indices of the k<sup>th</sup> smallest trimmed numbers in `nums`.*);
    public int[] smallestTrimmedNumbers(String[] nums, int[][] queries) {
        int numberOfDigits = nums[0].length();
        int placeValue = numberOfDigits;
        List<int[]> list = new ArrayList<>(numberOfDigits);
        while (--placeValue >= 0) {
            countSort(nums, placeValue, numberOfDigits, list);
        }
        int[] op = new int[queries.length];
        int i = 0;
        for (int[] query : queries) {
            int listIndex = query[1] - 1;
            int place = query[0] - 1;
            op[i++] = list.get(listIndex)[place];
        }
        return op;
    }

    private void countSort(String[] arr, int exp, int numberOfDigits, List<int[]> list) {
        int n = arr.length;
        String[] output = new String[n];
        int i;
        int[] count = new int[10];
        Arrays.fill(count, 0);
        // Store count of occurrences in count[]
        for (i = 0; i < n; i++) {
            int digit = arr[i].charAt(exp) - '0';
            count[digit]++;
        }
        for (i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }
        // Build the output array
        int[] op = new int[n];
        for (i = n - 1; i >= 0; i--) {
            int digit = arr[i].charAt(exp) - '0';
            int place = count[digit] - 1;
            output[place] = arr[i];
            if (exp == numberOfDigits - 1) {
                op[place] = i;
            } else {
                op[place] = list.get(list.size() - 1)[i];
            }
            count[digit]--;
        }
        list.add(op);
        System.arraycopy(output, 0, arr, 0, n);
    }
}