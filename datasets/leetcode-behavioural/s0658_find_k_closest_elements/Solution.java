package g0601_0700.s0658_find_k_closest_elements;

// #Medium #Array #Sorting #Binary_Search #Two_Pointers #Heap_Priority_Queue #Binary_Search_II_Day_2
// #2022_03_21_Time_3_ms_(99.20%)_Space_44.1_MB_(88.25%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
//@ ensures(*The integer array parameter `arr` must not be null.*);
//@ ensures(*The integer parameter `k` must be greater than or equal to 1 and less than or equal to the length of the integer array parameter `arr`.*);
//@ ensures(*The integer parameter `x` must be within the range of -10^4 to 10^4.*);
//@ ensures(*The integer collection result is sorted in ascending order.*);
//@ ensures(*The integer collection result contains `k` closest integers to the integer parameter `x` in the integer array parameter `arr`.*);
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int left = 0;
        int right = arr.length - k;
        List<Integer> answer = new ArrayList<>();
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (x - arr[mid] > arr[mid + k] - x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        for (int i = left; i < left + k; i++) {
            answer.add(arr[i]);
        }
        return answer;
    }
}