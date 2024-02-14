package g2601_2700.s2653_sliding_subarray_beauty;

// #Medium #Array #Hash_Table #Sliding_Window #2023_09_06_Time_33_ms_(86.67%)_Space_60.4_MB_(44.44%)

@SuppressWarnings("java:S135")
public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is greater than or equal to `k`.*);
	//@ requires(*The value of `k` is greater than or equal to 1.*);
	//@ requires(*The value of `x` is greater than or equal to 1 and less than or equal to `k`.*);
	//@ requires(*The elements in the input array `nums` are integers ranging from -50 to 50.*);
	//@ ensures(*The output array `result` is not null.*);
	//@ ensures(*The length of the output array `result` is equal to `n - k + 1`.*);
	//@ ensures(*The elements in the output array `result` are integers.*);
	//@ ensures(*The elements in the output array `result` represent the beauty of each subarray of size `k` in order from the first index in the input array `nums`.*);
    public int[] getSubarrayBeauty(int[] nums, int k, int x) {
        int[] arr = new int[101];
        int j = 0;
        int[] ans = new int[nums.length - k + 1];
        int ind = 0;
        for (int i = 0; i < nums.length; i++) {
            arr[nums[i] + 50]++;
            if (i < k - 1) {
                continue;
            }
            if (ind > 0 && ans[ind - 1] < nums[i] && ans[ind - 1] < nums[j - 1]) {
                arr[nums[j++] + 50]--;
                ans[ind] = ans[ind - 1];
                ind++;
                continue;
            }
            int count = 0;
            int si = -1;
            while (count < x) {
                si++;
                if (arr[si] != 0) {
                    count += arr[si];
                }
            }
            arr[nums[j++] + 50]--;
            ans[ind++] = (si - 50 < 1) ? si - 50 : 0;
        }
        return ans;
    }
}