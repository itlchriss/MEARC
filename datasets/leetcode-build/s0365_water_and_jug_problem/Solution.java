package g0301_0400.s0365_water_and_jug_problem;

// #Medium #Math #Depth_First_Search #Breadth_First_Search
// #Graph_Theory_I_Day_11_Breadth_First_Search
// #2022_07_12_Time_0_ms_(100.00%)_Space_40.9_MB_(76.00%)

public class Solution {
    private int gcd(int n1, int n2) {
        if (n2 == 0) {
            return n1;
        }
        return gcd(n2, n1 % n2);
    }
	//@ requires(*The values of `jug1Capacity`, `jug2Capacity`, and `targetCapacity` are positive integers.*);
	//@ requires(*The values of `jug1Capacity` and `jug2Capacity` are less than or equal to 10^6.*);
	//@ requires(*The value of `targetCapacity` is less than or equal to the sum of `jug1Capacity` and `jug2Capacity`.*);
	//@ ensures(*The method returns a boolean value indicating whether it is possible to measure exactly `targetCapacity` liters using the given jugs.*);
	//@ ensures(*If the method returns true, there is `targetCapacity` liters of water contained within one or both jugs by the end.*);

    public boolean canMeasureWater(int jug1, int jug2, int target) {
        if (jug1 + jug2 < target) {
            return false;
        }
        int gcd = gcd(jug1, jug2);
        return target % gcd == 0;
    }
}