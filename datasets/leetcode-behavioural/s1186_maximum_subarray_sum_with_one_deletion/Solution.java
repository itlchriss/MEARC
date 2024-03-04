package g1101_1200.s1186_maximum_subarray_sum_with_one_deletion;

// #Medium #Array #Dynamic_Programming #2022_03_03_Time_3_ms_(94.48%)_Space_52.3_MB_(76.90%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `arr` is not null.*);
//@ ensures(*The length of the input array `arr` is at least 1 and at most 10^5.*);
//@ ensures(*Each element in the input array `arr` is an integer between -10^4 and 10^4.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the maximum sum of a non-empty subarray with at most one element deletion.*);
//@ ensures(*The subarray chosen has at least one element remaining after the deletion.*);
//@ ensures(*The sum of the remaining elements in the subarray is the maximum possible.*);
    public int maximumSum(int[] arr) {
        int maxWithNoDeletions = arr[0];
        int maxWithOneDeletion = arr[0];
        int maxOverall = arr[0];
        for (int i = 1; i < arr.length; i++) {
            int numToProcess = arr[i];
            int nextMaxWithNoDeletions = Math.max(maxWithNoDeletions + numToProcess, numToProcess);
            int nextMaxWithOneDeletion =
                    Math.max(maxWithOneDeletion + numToProcess, maxWithNoDeletions);
            maxOverall =
                    Math.max(maxOverall, Math.max(nextMaxWithNoDeletions, nextMaxWithOneDeletion));
            maxWithNoDeletions = nextMaxWithNoDeletions;
            maxWithOneDeletion = nextMaxWithOneDeletion;
        }
        return maxOverall;
    }
}