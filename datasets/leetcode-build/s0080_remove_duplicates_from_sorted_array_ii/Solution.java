package g0001_0100.s0080_remove_duplicates_from_sorted_array_ii;

// #Medium #Array #Two_Pointers #Udemy_Arrays #2023_08_11_Time_0_ms_(100.00%)_Space_44_MB_(12.69%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The input array `nums` is sorted in non-decreasing order.*);
	//@ ensures(*The length of the modified array `nums` is equal to the returned value `k`.*);
	//@ ensures(*The first `k` elements of the array `nums` contain the final result.*);
	//@ ensures(*Each unique element appears at most twice in the modified array `nums`.*);
	//@ ensures(*The relative order of the elements in the modified array `nums` is the same as the original array `nums`.*);
	//@ ensures(*The modified array `nums` is sorted in non-decreasing order.*);
	//@ ensures(*The elements beyond the first `k` elements in the modified array `nums` can be any value.*);
    public int removeDuplicates(int[] nums) {
        int i = 0;
        int k = 0;
        int count = 0;
        while (i < nums.length - 1) {
            ++count;
            if (count <= 2) {
                nums[k++] = nums[i];
            }
            if (nums[i] != nums[i + 1]) {
                count = 0;
                i++;
                continue;
            }
            i++;
        }
        ++count;
        if (count <= 2) {
            nums[k++] = nums[i];
        }
        return k;
    }
}