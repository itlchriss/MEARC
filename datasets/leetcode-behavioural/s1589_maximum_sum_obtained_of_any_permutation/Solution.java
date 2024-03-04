package g1501_1600.s1589_maximum_sum_obtained_of_any_permutation;

// #Medium #Array #Sorting #Greedy #Prefix_Sum
// #2022_04_15_Time_25_ms_(92.45%)_Space_78.4_MB_(83.02%)

import java.util.Arrays;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The input array `requests` is not null.*);
//@ ensures(*The length of `nums` is equal to `n`.*);
//@ ensures(*The length of `requests` is equal to `m`.*);
//@ ensures(*Each element in `nums` is an integer between 0 and 10^- Each element in `requests` is an array of length - The first element of each array in `requests` is a valid index in `nums`.*);
//@ ensures(*The second element of each array in `requests` is a valid index in `nums`.*);
//@ ensures(*The start index of each request is less than or equal to the end index.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer.*);
//@ ensures(*The returned integer is the maximum total sum of all requests among all permutations of `nums`.*);
//@ ensures(*The returned integer is modulo 10^9 + 7.*);
    public int maxSumRangeQuery(int[] nums, int[][] requests) {
        Arrays.sort(nums);
        int l = nums.length;
        int[] tempArr = new int[l];
        // requests[i][0] incrementing index element by 1 and for requests[i][1]+1 decrementing by 1
        // this will help me get the freq of occurance of each index of array 'nums' in
        // all 'requests' intervals when I compute the sum array of tempArr.
        for (int[] request : requests) {
            int a = request[0];
            int b = request[1] + 1;
            tempArr[a]++;
            if (b < l) {
                tempArr[b]--;
            }
        }
        int prev = 0;
        for (int i = 0; i < l; i++) {
            tempArr[i] += prev;
            prev = tempArr[i];
        }
        Arrays.sort(tempArr);
        int index = l - 1;
        long ans = 0;
        while (index >= 0) {
            if (tempArr[index] == 0) {
                break;
            }
            long x = tempArr[index] % 1000000007;
            long y = nums[index] % 1000000007;
            index--;
            ans += x * y;
        }
        return (int) (ans % 1000000007);
    }
}