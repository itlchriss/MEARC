package g0101_0200.s0134_gas_station;

// #Medium #Top_Interview_Questions #Array #Greedy
// #2022_06_24_Time_2_ms_(94.26%)_Space_62.5_MB_(87.11%)

public class Solution {
//@ requires(gas.length > 0 && gas.length <= 100000);
//@ requires(gas.length == cost.length);
//@ ensures((\forall int i; 0 <= i && i < gas.length; (\sum int j; 0 <= j && j < gas.length; gas[(i+j) % gas.length] - cost[(i+j) % gas.length]) >= 0) ==> \result != -1);
//@ requires((\forall int i; 0 <= i && i < cost.length; cost[i] >= 0 && cost[i] <= 10000));
//@ requires((\forall int i; 0 <= i && i < gas.length; gas[i] >= 0 && gas[i] <= 10000));
//@ ensures(\result >= -1 && \result < gas.length);
//@ ensures((\forall int i; 0 <= i && i < gas.length; (\sum int j; 0 <= j && j < gas.length; gas[(i+j) % gas.length] - cost[(i+j) % gas.length]) < 0) ==> \result == -1);
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int sumGas = 0;
        int sumCost = 0;
        int curGas = 0;
        int result = -1;
        for (int i = 0; i != gas.length; i++) {
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
