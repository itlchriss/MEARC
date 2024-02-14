package g0601_0700.s0659_split_array_into_consecutive_subsequences;

// #Medium #Array #Hash_Table #Greedy #Heap_Priority_Queue
// #2022_03_21_Time_4_ms_(95.88%)_Space_72.5_MB_(21.48%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The input array `nums` is sorted in non-decreasing order.*);
	//@ ensures(*The method returns `true` if it is possible to split `nums` into one or more subsequences such that each subsequence is a consecutive increasing sequence with a length of 3 or more.*);
	//@ ensures(*The method returns `false` if it is not possible to split `nums` into such subsequences.*);
    public boolean isPossible(int[] nums) {
        int[] element = new int[2001];
        for (int num : nums) {
            element[num + 1000] += 1;
        }
        for (int i = 0; i < element.length; i++) {
            while (element[i] > 0) {
                int length = 1;
                while (i + length < element.length
                        && element[i + length] >= element[i + length - 1]) {
                    length += 1;
                }
                if (length < 3) {
                    return false;
                } else {
                    for (int j = i; j < i + length; j++) {
                        element[j] -= 1;
                    }
                }
            }
        }
        return true;
    }
}