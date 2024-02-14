package g0801_0900.s0826_most_profit_assigning_work;

// #Medium #Array #Sorting #Greedy #Binary_Search #Two_Pointers #Binary_Search_II_Day_11
// #2022_03_24_Time_21_ms_(83.83%)_Space_72_MB_(5.39%)

public class Solution {
	//@ requires(*The arrays `difficulty`, `profit`, and `worker` must not be null.*);
	//@ requires(*The lengths of the arrays `difficulty`, `profit`, and `worker` must be equal.*);
	//@ requires(*The length of the arrays `difficulty`, `profit`, and `worker` must be greater than 0.*);
	//@ requires(*The values in the arrays `difficulty`, `profit`, and `worker` must be positive integers.*);
	//@ ensures(*The method returns an integer representing the maximum profit that can be achieved after assigning the workers to the jobs.*);
	//@ ensures(*The maximum profit is calculated based on the given difficulty, profit, and worker arrays.*);
	//@ ensures(*Each worker can be assigned at most one job.*);
	//@ ensures(*Each job can be completed multiple times.*);
	//@ ensures(*If a worker cannot complete any job, their profit is 0.*);
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int n = 100000;
        int[] maxProfit = new int[n];

        for (int i = 0; i < difficulty.length; i++) {
            maxProfit[difficulty[i]] = Math.max(maxProfit[difficulty[i]], profit[i]);
        }

        for (int i = 1; i < n; i++) {
            maxProfit[i] = Math.max(maxProfit[i], maxProfit[i - 1]);
        }

        int sum = 0;
        for (int efficiency : worker) {
            sum += maxProfit[efficiency];
        }
        return sum;
    }
}