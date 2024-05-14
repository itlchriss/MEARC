package g0101_0200.s0134_gas_station;

// #Medium #Top_Interview_Questions #Array #Greedy
// #2022_06_24_Time_2_ms_(94.26%)_Space_62.5_MB_(87.11%)

public class Solution {
//@ requires(*The length of the integer array parameter `gas` is less than or equal to 100000 and is greater than or equal to 1.*);
//@ requires(*The length of the integer array parameter `cost` is equal to the length of the integer array parameter `gas`.*);
//@ requires(*All the values in the integer array parameter `gas` and `cost` are less than or equal to 10000 and are greater than or equal to 0.*);
//@ ensures(*If there exists a solution to travel around the circuit once in the clockwise direction, the integer result is the starting gas station's index.*);
//@ ensures(*If there is no solution to travel around the circuit once in the clockwise direction, the integer result is -1.*);
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int sumGas = 0;
        int sumCost = 0;
        int curGas = 0;
        int result = -1;
        //@ assume 1 <= gas.length <= 10;
        //@ assume gas.length == cost.length;
        //@ maintaining 0 <= i <= gas.length;
        //@ maintaining 0 <= i <= cost.length;
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