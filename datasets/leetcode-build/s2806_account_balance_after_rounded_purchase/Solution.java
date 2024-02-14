package g2801_2900.s2806_account_balance_after_rounded_purchase;

// #Easy #Math #2023_09_25_Time_0_ms_(100.00%)_Space_39.2_MB_(64.97%)

public class Solution {
	//@ requires(*The initial bank account balance is 100 dollars.*);
	//@ requires(*The purchaseAmount is an integer representing the amount to be spent on a purchase in dollars.*);
	//@ requires(*The purchaseAmount is between 0 and 100 (inclusive).*);
	//@ ensures(*The account balance after making the purchase is returned as an integer.*);
	//@ ensures(*The account balance is calculated by subtracting the roundedAmount from the initial balance.*);
	//@ ensures(*The roundedAmount is the nearest multiple of 10 to the purchaseAmount.*);
	//@ ensures(*If there is more than one nearest multiple of 10, the largest multiple is chosen.*);
	//@ ensures(*The roundedAmount is a non-negative amount.*);
	//@ ensures(*The absolute difference between the roundedAmount and the purchaseAmount is minimized.*);
    public int accountBalanceAfterPurchase(int purchaseAmount) {
        int x = (int) ((purchaseAmount + 5) / (double) 10) * 10;
        return 100 - x;
    }
}