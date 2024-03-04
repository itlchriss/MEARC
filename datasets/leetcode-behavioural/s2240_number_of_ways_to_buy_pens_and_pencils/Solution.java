package g2201_2300.s2240_number_of_ways_to_buy_pens_and_pencils;

// #Medium #Math #Enumeration #2022_06_13_Time_19_ms_(68.73%)_Space_41.4_MB_(12.23%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The total money, cost of pen, and cost of pencil must be positive integers.*);
//@ ensures(*The total money must be greater than or equal to the cost of the cheapest writing utensil.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns a long integer representing the number of distinct ways to buy pens and pencils.*);
//@ ensures(*The returned value is always non-negative.*);
//@ ensures(**);
//@ ensures(*Additional requirements:*);
//@ ensures(*The method should handle large input values efficiently.*);
//@ ensures(*The method should handle cases where the cost of pen and pencil are equal to the total money. In this case, the method should return 1.*);
//@ ensures(*The method should handle cases where the cost of pen and pencil are both greater than the total money. In this case, the method should return 0.*);
    public long waysToBuyPensPencils(int totalMoney, int costPen, int costPencil) {
        long ways = 0;
        for (int cntPen = 0; (cntPen * costPen) <= totalMoney; cntPen++) {
            int remMoney = (totalMoney - (cntPen * costPen));
            ways += (remMoney / costPencil) + 1;
        }
        return ways;
    }
}