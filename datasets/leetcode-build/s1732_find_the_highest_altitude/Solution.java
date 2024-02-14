package g1701_1800.s1732_find_the_highest_altitude;

// #Easy #Array #Prefix_Sum #2022_04_28_Time_0_ms_(100.00%)_Space_40.1_MB_(83.65%)

public class Solution {
	//@ requires(*The input array `gain` is not null.*);
	//@ requires(*The length of the input array `gain` is greater than or equal to 1.*);
	//@ requires(*The length of the input array `gain` is less than or equal to 100.*);
	//@ requires(*Each element in the input array `gain` is an integer.*);
	//@ requires(*Each element in the input array `gain` is within the range of -100 to 100.*);
	//@ ensures(*The method returns an integer representing the highest altitude of a point.*);
	//@ ensures(*The highest altitude is calculated by summing up the net gain in altitude between each pair of points in the input array `gain`.*);
	//@ ensures(*The highest altitude is the maximum value obtained from the summation.*);
    public int largestAltitude(int[] gain) {
        int max = 0;
        int[] altitudes = new int[gain.length + 1];
        for (int i = 0; i < gain.length; i++) {
            altitudes[i + 1] = altitudes[i] + gain[i];
            max = Math.max(max, altitudes[i + 1]);
        }
        return max;
    }
}