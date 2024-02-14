package g2001_2100.s2073_time_needed_to_buy_tickets;

// #Easy #Array #Simulation #Queue #2022_05_27_Time_0_ms_(100.00%)_Space_41.9_MB_(45.92%)

public class Solution {
	//@ requires(*The input array `tickets` must not be null.*);
	//@ requires(*The length of the input array `tickets` must be equal to `n`.*);
	//@ requires(*The value of `n` must be greater than or equal to 1.*);
	//@ requires(*The value of `k` must be greater than or equal to 0 and less than `n`.*);
	//@ ensures(*The output must be an integer representing the time taken for the person at position `k` to finish buying tickets.*);
	//@ ensures(*The output must be greater than or equal to 0.*);
    public int timeRequiredToBuy(int[] tickets, int k) {
        int res = 0;
        for (int i = 0; i < tickets.length; i++) {
            if (i <= k) {
                res += Math.min(tickets[k], tickets[i]);
            } else {
                res += Math.min(tickets[k] - 1, tickets[i]);
            }
        }
        return res;
    }
}