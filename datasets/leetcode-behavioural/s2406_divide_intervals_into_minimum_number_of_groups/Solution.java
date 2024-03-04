package g2401_2500.s2406_divide_intervals_into_minimum_number_of_groups;

// #Medium #Array #Sorting #Greedy #Two_Pointers #Heap_Priority_Queue #Prefix_Sum
// #2022_10_23_Time_144_ms_(71.27%)_Space_151.1_MB_(13.30%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input `intervals` is a 2D integer array.*);
//@ ensures(*Each element in `intervals` is an array of length 2.*);
//@ ensures(*The first element of each interval is less than or equal to the second element.*);
//@ ensures(*The length of `intervals` is between 1 and 10^5.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The return value is an integer representing the minimum number of groups needed to divide the intervals.*);
//@ ensures(*Each interval is in exactly one group.*);
//@ ensures(*No two intervals in the same group intersect each other.*);
//@ ensures(*The return value is the minimum possible number of groups.*);
    public int minGroups(int[][] intervals) {
        int maxElement = 0;
        for (int[] i : intervals) {
            maxElement = Math.max(maxElement, i[0]);
            maxElement = Math.max(maxElement, i[1]);
        }
        long[] prefixSum = new long[maxElement + 2];
        for (int[] i : intervals) {
            prefixSum[i[0]] += 1;
            prefixSum[i[1] + 1] -= 1;
        }
        long ans = 0;
        for (int i = 1; i <= maxElement + 1; i++) {
            prefixSum[i] += prefixSum[i - 1];
            ans = Math.max(ans, prefixSum[i]);
        }
        return (int) ans;
    }
}