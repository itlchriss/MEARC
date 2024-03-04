package g1301_1400.s1343_number_of_sub_arrays_of_size_k_and_average_greater_than_or_equal_to_threshold;

// #Medium #Array #Sliding_Window #2022_03_19_Time_5_ms_(46.57%)_Space_70.2_MB_(39.71%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `arr` is not null.*);
//@ ensures(*The length of the input array `arr` is greater than or equal to `k`.*);
//@ ensures(*The value of `k` is greater than or equal to 1.*);
//@ ensures(*The value of `threshold` is greater than or equal to 0.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the number of sub-arrays of size `k` with an average greater than or equal to `threshold`.*);
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int sum = 0;
        for (int i = 0; i < k - 1; i++) {
            sum += arr[i];
        }
        int count = 0;
        for (int i = k - 1; i < arr.length; i++) {
            sum += arr[i];
            if (i - k >= 0) {
                sum -= arr[i - k];
            }
            if (sum / k >= threshold) {
                count++;
            }
        }
        return count;
    }
}