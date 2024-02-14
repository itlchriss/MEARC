package g0601_0700.s0658_find_k_closest_elements;

// #Medium #Array #Sorting #Binary_Search #Two_Pointers #Heap_Priority_Queue #Binary_Search_II_Day_2
// #2022_03_21_Time_3_ms_(99.20%)_Space_44.1_MB_(88.25%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
	//@ requires(*The input array `arr` is not null.*);
	//@ requires(*The input array `arr` is sorted in ascending order.*);
	//@ requires(*The input integer `k` is greater than or equal to - The input integer `k` is less than or equal to the length of the array `arr`.*);
	//@ requires(*The input integer `x` is within the range of the minimum and maximum values in the array `arr`.*);
	//@ ensures(*The output list is not null.*);
	//@ ensures(*The output list contains `k` integers.*);
	//@ ensures(*The output list is sorted in ascending order.*);
	//@ ensures(*The integers in the output list are the `k` closest integers to `x` in the array `arr`.*);
	//@ ensures(*For any two integers `a` and `b` in the output list, if `|a - x| < |b - x|`, then `a` appears before `b` in the output list.*);
	//@ ensures(*For any two integers `a` and `b` in the output list, if `|a - x| == |b - x|` and `a < b`, then `a` appears before `b` in the output list.*);
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