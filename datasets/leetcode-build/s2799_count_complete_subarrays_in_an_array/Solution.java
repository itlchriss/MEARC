package g2701_2800.s2799_count_complete_subarrays_in_an_array;

// #Medium #Array #Hash_Table #Sliding_Window #2023_09_14_Time_3_ms_(99.82%)_Space_44_MB_(32.27%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is greater than or equal to 1.*);
	//@ requires(*The elements in the input array `nums` are positive integers.*);
	//@ requires(*The input array `nums` has at most 1000 elements.*);
	//@ requires(*Each element in the input array `nums` is between 1 and 2000 (inclusive).*);
	//@ ensures(*The method returns an integer representing the number of complete subarrays.*);
	//@ ensures(*The returned value is greater than or equal to 0.*);
	//@ ensures(*The returned value is less than or equal to the total number of subarrays in the input array `nums`.*);
	//@ ensures(*The returned value is equal to the number of complete subarrays in the input array `nums`.*);
    public int countCompleteSubarrays(int[] nums) {
        int n = nums.length;
        int[] map = new int[2001];
        int last = 0;
        for (int i = 0; i < n; ++i) {
            map[nums[i]]++;
            if (map[nums[i]] == 1) {
                last = i;
            }
        }
        map = new int[2001];
        for (int i = 0; i <= last; ++i) {
            map[nums[i]]++;
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += n - last;
            map[nums[i]]--;
            if (map[nums[i]] == 0) {
                int possLast = 0;
                for (int j = last + 1; j < n && map[nums[i]] == 0; ++j) {
                    map[nums[j]]++;
                    possLast = j;
                }
                if (map[nums[i]] > 0) {
                    last = possLast;
                } else {
                    break;
                }
            }
        }
        return ans;
    }
}