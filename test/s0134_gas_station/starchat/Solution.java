package g0101_0200.s0134_gas_station;

// #Medium #Top_Interview_Questions #Array #Greedy
// #2022_06_24_Time_2_ms_(94.26%)_Space_62.5_MB_(87.11%)

public class Solution {
//@ requires(*The length of the integer array parameter `gas` is equal to the length of the integer array parameter `cost`.*);
//@ requires(*The length of the integer array parameter `gas` is less than or equal to 100000 and is greater than or equal to 1.*);
//@ requires(*The length of the integer array parameter `cost` is less than or equal to 100000 and is greater than or equal to 1.*);
//@ requires(*All values in the integer array parameter `gas` are less than or equal to 10000 and is greater than or equal to 0.*);
//@ requires(*All values in the integer array parameter `cost` are less than or equal to 10000 and is greater than or equal to 0.*);
//@ requires(**);
//@ requires(*Given the following context,*);
//@ requires(*Software specification: *);
//@ requires(*1137\. N-th Tribonacci Number*);
//@ requires(**);
//@ requires(*Easy*);
//@ requires(**);
//@ requires(*The Tribonacci sequence `Tn` is defined as follows: *);
//@ requires(**);
//@ requires(*`T0 = 0`, `T1 = 1`, `T2 = 1`, and `Tn+3 = Tn + Tn+1 + Tn+2` for `n >= 0`.*);
//@ requires(**);
//@ requires(*Given `n`, return the `nth` Tribonacci number.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** n = 4*);
//@ requires(***Output:** 4*);
//@ requires(***Explanation:***);
//@ requires(*T\_3 = 0 + 1 + 1 = 2*);
//@ requires(*T\_4 = 1 + 1 + 2 = 4*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** n = 25*);
//@ requires(***Output:** 1389537*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `0 <= n <= 37`*);
//@ requires(**   The answer is guaranteed to fit within a **32-bit** integer, i.e. `answer <= 2^31 - 1`.*);
//@ requires(**);
//@ requires(*Method signature: public int tribonacci(int n)*);
//@ requires(**);
//@ requires(*What are the method behavioural specifications for the given context?*);
//@ requires(**);
//@ requires(*output format: a list with '-' as bullets*);
//@ requires(**);
//@ requires(*The integer parameter `n` is less than or equal to 37 and is greater than or equal to 0.*);
//@ requires(**);
//@ requires(*Given the following context,*);
//@ requires(*Software specification: *);
//@ requires(*1023\. Camelcase Matching*);
//@ requires(**);
//@ requires(*Medium*);
//@ requires(**);
//@ requires(*A query word matches a given `pattern` if we can insert lowercase letters to the pattern word so that it equals the `query`. (We may insert each character at any position, and may insert 0 characters.)*);
//@ requires(**);
//@ requires(*Return a list of `boolean` values, where `answers[i]` is `true` if and only if the `ith` query word matches the `ith` pattern.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** queries = \[ "FooBar ", "FooBarTest ", "FootBall ", "FrameBuffer ", "ForceFeedBack "\], pattern =  "FB "*);
//@ requires(***Output:** \[true,false,true,true,false\]*);
//@ requires(***Explanation:** *);
//@ requires(*"FooBar " can be generated like "F" +*);
//@ ensures(*If the integer result is not equal to -1, the car can travel around the circuit once in the clockwise direction starting from the gas station with the index equal to the integer result.*);
//@ ensures(*If the integer result is equal to -1, the car cannot travel around the circuit once in the clockwise direction starting from any gas station.*);
//@ ensures(*If the integer result is not equal to -1, it is guaranteed to be unique.*);
//@ ensures(*If the integer array parameter `gas` is equal to [2,3,4] and the integer array parameter `cost` is equal to [3,4,3], the integer result is equal to -1.*);
//@ ensures(*If the integer array parameter `gas` is equal to [1,2,3,4,5] and the integer array parameter `cost` is equal to [3,4,5,1,2], the integer result is equal to 3.*);
//@ ensures(*The integer result is less than or equal to 231 - 1 and is greater than or equal to 0.*);
//@ ensures(*If the integer parameter `n` is equal to 0, the integer result is equal to 0.*);
//@ ensures(*If the integer parameter `n` is equal to 1 or 2, the integer result is equal to 1.*);
//@ ensures(*If the integer parameter `n` is greater than or equal to 3, the integer result is the sum of the previous three Tribonacci numbers.*);
//@ ensures(*If the integer parameter `n` is equal to 4, the integer result is equal to 4.*);
//@ ensures(*If the integer parameter `n` is equal to 25, the integer result is equal to 1389537.*);
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