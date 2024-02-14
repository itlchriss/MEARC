package g0101_0200.s0134_gas_station;

// #Medium #Top_Interview_Questions #Array #Greedy
// #2022_06_24_Time_2_ms_(94.26%)_Space_62.5_MB_(87.11%)

public class Solution {
	//@ requires(*The length of the `gas` array is equal to the length of the `cost` array.*);
	//@ requires(*The length of the `gas` and `cost` arrays is greater than or equal to - The values in the `gas` and `cost` arrays are non-negative integers.*);
	//@ requires(*The values in the `gas` and `cost` arrays are less than or equal to 10^*);
	//@ ensures(*If there exists a solution, the method returns the starting gas station's index.*);
	//@ ensures(*If there is no solution, the method returns -1.*);
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int sumGas = 0;
        int sumCost = 0;
        int curGas = 0;
        int result = -1;
        for (int i = 0; i < gas.length; i++) {
            curGas += gas[i] - cost[i];
            // re-calculate the starting point
            if (curGas < 0) {
                result = -1;
                curGas = 0;
            } else if (result == -1) {
                // set initial starting point
                result = i;
            }
            sumGas += gas[i];
            sumCost += cost[i];
        }
        if (sumGas < sumCost) {
            return -1;
        }
        return result;
    }
}