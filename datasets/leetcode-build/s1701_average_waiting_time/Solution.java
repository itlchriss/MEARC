package g1701_1800.s1701_average_waiting_time;

// #Medium #Array #Simulation #2022_04_24_Time_2_ms_(100.00%)_Space_94.5_MB_(56.50%)

public class Solution {
	//@ requires(*The input array `customers` is not null.*);
	//@ requires(*The input array `customers` is not empty.*);
	//@ requires(*The arrival times in the input array `customers` are sorted in non-decreasing order.*);
	//@ requires(*The arrival times and time needed to prepare the order for each customer are positive integers.*);
	//@ requires(*The arrival times and time needed to prepare the order for each customer are not greater than 10^*);
	//@ ensures(*The method returns a double value representing the average waiting time of all customers.*);
	//@ ensures(*The average waiting time is calculated by summing up the waiting times of all customers and dividing it by the total number of customers.*);
	//@ ensures(*The waiting time for each customer is calculated by subtracting the arrival time of the customer from the time when the chef finishes preparing the order for that customer.*);
	//@ ensures(*The waiting times are calculated in the order the customers were given in the input array `customers`.*);
    public double averageWaitingTime(int[][] customers) {
        int ctime = 0;
        double ans = 0;
        for (int[] customer : customers) {
            if (customer[0] >= ctime) {
                ctime = customer[0] + customer[1];
                ans = ans + (ctime - customer[0]);
            } else {
                ctime = ctime + customer[1];
                ans = ans + (ctime - customer[0]);
            }
        }
        return Math.round(ans / customers.length * 100000d) / 100000d;
    }
}