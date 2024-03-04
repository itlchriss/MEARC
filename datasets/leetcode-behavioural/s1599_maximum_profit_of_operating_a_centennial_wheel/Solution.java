package g1501_1600.s1599_maximum_profit_of_operating_a_centennial_wheel;

// #Medium #Array #Simulation #2022_04_08_Time_4_ms_(95.65%)_Space_75.6_MB_(52.17%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `customers` must not be null.*);
//@ ensures(*The length of the input array `customers` must be greater than 0.*);
//@ ensures(*The values in the input array `customers` must be non-negative integers.*);
//@ ensures(*The values in the input array `customers` must not exceed 50.*);
//@ ensures(*The values of `boardingCost` and `runningCost` must be positive integers.*);
//@ ensures(*The value of `boardingCost` must not exceed 100.*);
//@ ensures(*The value of `runningCost` must not exceed 100.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the minimum number of rotations needed to maximize profit.*);
//@ ensures(*If there is no scenario where the profit is positive, the method returns -1.*);
    public int minOperationsMaxProfit(int[] customers, int boardingCost, int runningCost) {
        int maxProfit = 0;
        int shift = -1;
        int waiting = 0;
        int profit = 0;
        for (int i = 0; i < customers.length; i++) {
            // In each shift adding new passenger to the waiting line
            waiting += customers[i];
            profit += Math.min(waiting, 4) * boardingCost - runningCost;
            if (profit > maxProfit) {
                shift = i + 1;
                maxProfit = profit;
            }
            waiting = Math.max(waiting - 4, 0);
        }
        // profitable to serve all the remaining waiting line?
        if (boardingCost * 4 > runningCost) {
            shift += waiting / 4;
            // profitable for the last round?
            shift += waiting % 4 * boardingCost - runningCost > 0 ? 1 : 0;
        }
        return shift;
    }
}