package g1501_1600.s1518_water_bottles;

// #Easy #Math #Simulation #2022_04_09_Time_0_ms_(100.00%)_Space_40.5_MB_(71.92%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The method should take two integer parameters `numBottles` and `numExchange`.*);
//@ ensures(*The value of `numBottles` should be between 1 and 100 (inclusive).*);
//@ ensures(*The value of `numExchange` should be between 2 and 100 (inclusive).*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method should return an integer value representing the maximum number of water bottles that can be drunk.*);
//@ ensures(*The number of water bottles that can be drunk is calculated by adding `numBottles` with the integer division of `numBottles` by `numExchange`, and adding 1 to the result.*);
//@ ensures(*The method should handle the case when `numBottles` is less than `numExchange` and return `numBottles` as the result.*);
    public int numWaterBottles(int numBottles, int numExchange) {
        int drunk = numBottles;
        int emptyBottles = numBottles;
        while (emptyBottles >= numExchange) {
            int exchangedBottles = emptyBottles / numExchange;
            drunk += exchangedBottles;
            int unUsedEmptyBottles = emptyBottles % numExchange;
            emptyBottles = exchangedBottles + unUsedEmptyBottles;
        }
        return drunk;
    }
}