package g0201_0300.s0274_h_index;

// #Medium #Array #Sorting #Counting_Sort #2022_11_05_Time_0_ms_(100.00%)_Space_40.3_MB_(86.98%)

public class Solution {
//@ ensures(*The integer array parameter `citations` must not be null.*);
//@ ensures(*The integer result is the researcher's `h-index` calculated based on the number of citations received for each paper in the integer array parameter `citations`.*);
//@ ensures(*The `h-index` is determined by finding the maximum value `h` such that `h` papers have at least `h` citations each, and the remaining papers have no more than `h` citations each.*);
    public int hIndex(int[] citations) {
        int len = citations.length;
        int[] freqArray = new int[len + 1];
        for (int citation : citations) {
            freqArray[Math.min(citation, len)]++;
        }
        int totalSoFar = 0;
        for (int k = len; k >= 0; k--) {
            totalSoFar += freqArray[k];
            if (totalSoFar >= k) {
                return k;
            }
        }
        return -1;
    }
}