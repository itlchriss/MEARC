package g0401_0500.s0448_find_all_numbers_disappeared_in_an_array;

// #Easy #Array #Hash_Table #Udemy_Arrays #2022_07_17_Time_3_ms_(100.00%)_Space_50.3_MB_(93.98%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is greater than or equal to 1.*);
	//@ requires(*The elements in the input array `nums` are integers.*);
	//@ requires(*The elements in the input array `nums` are within the range of 1 to n, where n is the length of the input array.*);
	//@ ensures(*The output is a list of integers.*);
	//@ ensures(*The output list contains all the integers in the range of 1 to n that do not appear in the input array `nums`.*);
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