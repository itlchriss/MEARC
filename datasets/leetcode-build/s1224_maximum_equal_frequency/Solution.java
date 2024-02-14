package g1201_1300.s1224_maximum_equal_frequency;

// #Hard #Array #Hash_Table #2022_03_12_Time_17_ms_(93.59%)_Space_76.9_MB_(39.74%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is at least 2.*);
	//@ requires(*The elements in the input array `nums` are positive integers.*);
	//@ requires(*The length of the input array `nums` is at most 10^5.*);
	//@ requires(*The elements in the input array `nums` are at most 10^5.*);
	//@ ensures(*The method returns an integer representing the longest possible length of an array prefix of `nums`.*);
	//@ ensures(*After removing exactly one element from the prefix, every number that has appeared in the prefix will have the same number of occurrences.*);
	//@ ensures(*If after removing one element there are no remaining elements, it is still considered that every appeared number has the same number of occurrences (0).*);
    public int maxEqualFreq(int[] nums) {
        int[] count = new int[100001];
        int[] freq = new int[100001];
        int n = nums.length;
        for (int num : nums) {
            count[num]++;
            freq[count[num]]++;
        }
        for (int i = n - 1; i > 0; i--) {
            if (freq[count[nums[i]]] * count[nums[i]] == i) {
                return i + 1;
            }
            freq[count[nums[i]]]--;
            count[nums[i]]--;
            if (freq[count[nums[i - 1]]] * count[nums[i - 1]] == i) {
                return i + 1;
            }
        }
        return 1;
    }
}