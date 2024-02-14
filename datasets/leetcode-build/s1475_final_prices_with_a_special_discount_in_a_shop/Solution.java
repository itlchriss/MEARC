package g1401_1500.s1475_final_prices_with_a_special_discount_in_a_shop;

// #Easy #Array #Stack #Monotonic_Stack #2022_04_04_Time_2_ms_(80.33%)_Space_44.8_MB_(17.62%)

public class Solution {
	//@ requires(*The input array `prices` is not null.*);
	//@ requires(*The length of the input array `prices` is greater than or equal to - The elements in the input array `prices` are integers.*);
	//@ requires(*The elements in the input array `prices` are greater than or equal to - The elements in the input array `prices` are less than or equal to 10^*);
	//@ ensures(*The output array is not null.*);
	//@ ensures(*The length of the output array is equal to the length of the input array.*);
	//@ ensures(*The elements in the output array are integers.*);
	//@ ensures(*The elements in the output array are greater than or equal to - The elements in the output array are less than or equal to the corresponding elements in the input array.*);
    public int[] finalPrices(int[] prices) {
        int[] result = new int[prices.length];
        for (int i = 0; i < prices.length; i++) {
            boolean foundDiscount = false;
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[j] <= prices[i]) {
                    result[i] = prices[i] - prices[j];
                    foundDiscount = true;
                    break;
                }
            }
            if (!foundDiscount) {
                result[i] = prices[i];
            }
        }
        result[prices.length - 1] = prices[prices.length - 1];
        return result;
    }
}