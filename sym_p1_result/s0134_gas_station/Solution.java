package g0101_0200.s0134_gas_station;

// #Medium #Top_Interview_Questions #Array #Greedy
// #2022_06_24_Time_2_ms_(94.26%)_Space_62.5_MB_(87.11%)

public class Solution {
//@ requires(*There are `n` gas stations along a circular route, where the amount of gas at the <code>i<sup>th</sup></code> station is `gas[i]`.*);
//@ requires(*You have a car with an unlimited gas tank and it costs `cost[i]` of gas to travel from the <code>i<sup>th</sup></code> station to its next <code>(i + 1)<sup>th</sup></code> station.*);
//@ requires(*You begin the journey with an empty tank at one of the gas stations.*);
//@ requires(*If there exists a solution, it is guaranteed to be unique*);
//@ requires(*Example 1:*);
//@ requires(*Input: gas = [1,2,3,4,5], cost = [3,4,5,1,2]*);
//@ requires(*Output: 3*);
//@ requires(*Explanation:*);
//@ requires(*Start at station 3 (index 3) and fill up with 4 unit of gas.*);
//@ requires(*Your tank = 0 + 4 = 4*);
//@ requires(*Travel to station 4.*);
//@ requires(*Your tank = 4 - 1 + 5 = 8*);
//@ requires(*Travel to station 0.*);
//@ requires(*Your tank = 8 - 2 + 1 = 7*);
//@ requires(*Travel to station 1.*);
//@ requires(*Your tank = 7 - 3 + 2 = 6*);
//@ requires(*Travel to station 2.*);
//@ requires(*Your tank = 6 - 4 + 3 = 5*);
//@ requires(*Travel to station 3.*);
//@ requires(*The cost is 5.*);
//@ requires(*Your gas is just enough to travel back to station 3.*);
//@ requires(*Example 2:*);
//@ requires(*Input: gas = [2,3,4], cost = [3,4,3]*);
//@ requires(*Output: -1*);
//@ requires(*Explanation:*);
//@ requires(*You can't start at station 0 or 1, as there is not enough gas to travel to the next station.*);
//@ requires(*Let's start at station 2 and fill up with 4 unit of gas.*);
//@ requires(*Your tank = 0 + 4 = 4*);
//@ requires(*Travel to station 0.*);
//@ requires(*Your tank = 4 - 3 + 2 = 3*);
//@ requires(*Travel to station 1.*);
//@ requires(*Your tank = 3 - 3 + 3 = 3*);
//@ requires(*You cannot travel back to station 2, as it requires 4 unit of gas but you only have 3.*);
//@ requires(*Therefore, you can't travel around the circuit once no matter where you start.*);
//@ requires(*Constraints:*);
//@ requires(*`gas.length == n`*);
//@ requires(*`cost.length == n`*);
//@ requires(*<code>1 <= n <= 10<sup>5</sup></code>*);
//@ requires(*<code>0 <= gas[i], cost[i] <= 10<sup>4</sup></code>*);
//@ ensures(*Given two integer arrays param_gas and param_cost, the result is the starting gas station's index if you can travel around the circuit once in the clockwise direction, otherwise the result is `-1`.*);
//@ ensures(*Therefore, the result is 3 as the starting index.*);
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