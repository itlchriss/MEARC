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
//@ ensures(*Method behavioural requirements:*);
//@ ensures(*The integer parameter `jug1` must be greater than or equal to 1.*);
//@ ensures(*The integer parameter `jug2` must be greater than or equal to 1.*);
//@ ensures(*The integer parameter `target` must be greater than or equal to 1.*);
//@ ensures(*The boolean result is true if it is possible to measure exactly `target` liters using the two jugs with capacities `jug1Capacity` and `jug2Capacity`.*);
//@ ensures(*If the boolean result is true, there must be `target` liters of water contained within one or both buckets by the end.*);

    public boolean canMeasureWater(int jug1, int jug2, int target) {
        if (jug1 + jug2 < target) {
            return false;
        }
        int gcd = gcd(jug1, jug2);
        return target % gcd == 0;
    }
}