package g0401_0500.s0448_find_all_numbers_disappeared_in_an_array;

// #Easy #Array #Hash_Table #Udemy_Arrays #2022_07_17_Time_3_ms_(100.00%)_Space_50.3_MB_(93.98%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
//@ ensures(*The integer array parameter `nums` must not be null.*);
//@ ensures(*The size of the integer collection result is equal to the difference between the length of the integer array parameter `nums` and the number of integers in the range `[1, n]` that do not appear in the integer array parameter `nums`.*);
//@ ensures(*The integer collection result contains all the integers in the range `[1, n]` that do not appear in the integer array parameter `nums`.*);
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int[] arr = new int[nums.length];
        int counter = 0;
        for (int i = 0; i < nums.length; i++) {
            arr[nums[counter] - 1] = 1;
            counter++;
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                res.add(i + 1);
            }
        }
        return res;
    }
}