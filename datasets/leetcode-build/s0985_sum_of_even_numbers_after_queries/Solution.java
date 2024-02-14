package g0901_1000.s0985_sum_of_even_numbers_after_queries;

// #Medium #Array #Simulation #2022_03_31_Time_6_ms_(71.11%)_Space_74.1_MB_(22.54%)

public class Solution {
	//@ requires(*The input array `nums` must not be null.*);
	//@ requires(*The input array `queries` must not be null.*);
	//@ requires(*The length of `nums` must be greater than or equal to 1.*);
	//@ requires(*The length of `queries` must be greater than or equal to 1.*);
	//@ requires(*The values in `nums` must be within the range of -10^4 to 10^4.*);
	//@ requires(*The values in `queries` must be within the range of -10^4 to 10^4.*);
	//@ requires(*The indices in `queries` must be within the range of 0 to the length of `nums` minus 1.*);
	//@ ensures(*The method returns an integer array `answer`.*);
	//@ ensures(*The length of `answer` is equal to the length of `queries`.*);
	//@ ensures(*Each element in `answer` is the sum of the even values in `nums` after applying the corresponding query.*);
	//@ ensures(*The values in `nums` are updated according to the queries.*);
	//@ ensures(*The sum of the even values in `nums` is printed after applying each query.*);
    public int[] sumEvenAfterQueries(int[] nums, int[][] queries) {
        int[] result = new int[queries.length];
        int res = 0;
        for (int num : nums) {
            res += (num & 1) == 0 ? num : 0;
        }
        int k = 0;
        for (int[] query : queries) {
            res -= (nums[query[1]] & 1) == 0 ? nums[query[1]] : 0;
            nums[query[1]] += query[0];
            if ((nums[query[1]] & 1) == 0) {
                res += nums[query[1]];
            }
            result[k++] = res;
        }
        return result;
    }
}