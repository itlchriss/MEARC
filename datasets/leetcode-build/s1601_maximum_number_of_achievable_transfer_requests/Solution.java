package g1601_1700.s1601_maximum_number_of_achievable_transfer_requests;

// #Hard #Array #Bit_Manipulation #Backtracking #Enumeration
// #2022_04_11_Time_26_ms_(84.02%)_Space_42.3_MB_(62.89%)

public class Solution {
    private int max = 0;
	//@ requires(*The input `n` is a positive integer representing the number of buildings.*);
	//@ requires(*The input `requests` is a non-empty array of length `m` where each element is an array of length 2 representing a transfer request.*);
	//@ requires(*Each transfer request in `requests` is valid, meaning the values of `from_i` and `to_i` are between 0 and `n-1`, inclusive.*);
	//@ ensures(*The method returns an integer representing the maximum number of achievable requests.*);
	//@ ensures(*The maximum number of achievable requests is determined by finding a combination of transfers that results in a net change of zero for each building.*);
	//@ ensures(*The method considers all possible combinations of transfers to find the maximum number of achievable requests.*);

    public int maximumRequests(int n, int[][] requests) {
        helper(requests, 0, new int[n], 0);
        return max;
    }

    private void helper(int[][] requests, int index, int[] count, int num) {
        if (index == requests.length) {
            for (int i : count) {
                if (0 != i) {
                    return;
                }
            }
            max = Math.max(max, num);
            return;
        }
        count[requests[index][0]]++;
        count[requests[index][1]]--;
        helper(requests, index + 1, count, num + 1);
        count[requests[index][0]]--;
        count[requests[index][1]]++;
        helper(requests, index + 1, count, num);
    }
}