package g1601_1700.s1648_sell_diminishing_valued_colored_balls;

// #Medium #Array #Math #Sorting #Greedy #Binary_Search #Heap_Priority_Queue
// #Binary_Search_II_Day_19 #2022_04_22_Time_27_ms_(80.64%)_Space_56.4_MB_(80.28%)

import java.util.Arrays;

public class Solution {
	//@ requires(*The `inventory` array must not be null.*);
	//@ requires(*The `inventory` array must have at least one element.*);
	//@ requires(*The `inventory` array must contain positive integers.*);
	//@ requires(*The `orders` variable must be a positive integer.*);
	//@ requires(*The `orders` variable must be less than or equal to the sum of all elements in the `inventory` array.*);
	//@ ensures(*The method returns an integer value.*);
	//@ ensures(*The returned value is the maximum total value that can be attained after selling `orders` colored balls.*);
	//@ ensures(*The returned value is modulo 10^9 + 7.*);
    public int maxProfit(int[] inventory, int orders) {
        int n = inventory.length;
        long mod = (long) 1e9 + 7;
        long totalValue = 0;
        Arrays.sort(inventory);
        int count = 0;
        for (int i = n - 1; i >= 0; i--) {
            count++;
            if (i == 0 || inventory[i] > inventory[i - 1]) {
                long diff = i == 0 ? inventory[i] : inventory[i] - inventory[i - 1];
                if (count * diff < orders) {
                    totalValue += (2L * inventory[i] - diff + 1) * diff * count / 2 % mod;
                    orders -= count * diff;
                } else {
                    diff = orders / count;
                    long remainder = orders % count;
                    totalValue += (2L * inventory[i] - diff + 1) * diff * count / 2 % mod;
                    totalValue += (inventory[i] - diff) * remainder % mod;
                    totalValue %= mod;
                    break;
                }
            }
        }
        return (int) totalValue;
    }
}