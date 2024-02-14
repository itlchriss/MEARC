package g2701_2800.s2706_buy_two_chocolates;

// #Easy #Array #Sorting #2023_09_14_Time_1_ms_(100.00%)_Space_43_MB_(74.68%)

public class Solution {
	//@ requires(*The prices array must have at least 2 elements.*);
	//@ requires(*Each element in the prices array must be between 1 and 100 (inclusive).*);
	//@ requires(*The money value must be between 1 and 100 (inclusive).*);
	//@ ensures(*The returned value must be non-negative.*);
	//@ ensures(*If there is a way to buy two chocolates without going into debt, the returned value should be the amount of money left after buying the chocolates.*);
	//@ ensures(*If there is no way to buy two chocolates without going into debt, the returned value should be equal to the initial amount of money.*);
    public int buyChoco(int[] prices, int money) {
        int minPrice1 = Integer.MAX_VALUE;
        int minPrice2 = Integer.MAX_VALUE;

        for (int price : prices) {
            if (price < minPrice1) {
                minPrice2 = minPrice1;
                minPrice1 = price;
            } else if (price < minPrice2) {
                minPrice2 = price;
            }
        }

        int totalPrice = minPrice1 + minPrice2;

        if (totalPrice > money) {
            return money;
        } else {
            return money - totalPrice;
        }
    }
}