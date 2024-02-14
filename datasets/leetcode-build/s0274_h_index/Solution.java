package g0201_0300.s0274_h_index;

// #Medium #Array #Sorting #Counting_Sort #2022_11_05_Time_0_ms_(100.00%)_Space_40.3_MB_(86.98%)

public class Solution {
	//@ requires(*The input array `citations` is not null.*);
	//@ requires(*The length of the input array `citations` is greater than or equal to 1.*);
	//@ requires(*The length of the input array `citations` is less than or equal to 5000.*);
	//@ requires(*Each element in the input array `citations` is an integer.*);
	//@ requires(*Each element in the input array `citations` is greater than or equal to 0.*);
	//@ requires(*Each element in the input array `citations` is less than or equal to 1000.*);
	//@ ensures(*The output is an integer representing the researcher's h-index.*);
	//@ ensures(*The output is the maximum possible h-index value.*);
	//@ ensures(*The output is greater than or equal to 0.*);
	//@ ensures(*The output is less than or equal to the length of the input array `citations`.*);
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