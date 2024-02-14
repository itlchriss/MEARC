package g2401_2500.s2432_the_employee_that_worked_on_the_longest_task;

// #Easy #Array #2022_12_07_Time_2_ms_(74.60%)_Space_53.7_MB_(69.30%)

public class Solution {
	//@ requires(*The input array `logs` is not null.*);
	//@ requires(*The length of `logs` is greater than or equal to 1.*);
	//@ requires(*The length of each subarray in `logs` is 2.*);
	//@ requires(*The values in `logs` are valid employee ids (between 0 and n-1).*);
	//@ requires(*The values in `logs` are sorted in ascending order based on the leave time.*);
	//@ ensures(*The method returns an integer representing the id of the employee that worked on the task with the longest time.*);
	//@ ensures(*If there is a tie between two or more employees, the method returns the smallest id among them.*);
    public int hardestWorker(int[][] logs) {
        int i;
        int max;
        int tid;
        int temp;
        max = 0;
        tid = Integer.MAX_VALUE;
        for (i = 0; i < logs.length; i++) {
            temp = logs[i][1];
            if (i > 0) {
                temp -= logs[i - 1][1];
            }
            if (temp > max) {
                max = temp;
                tid = logs[i][0];
            } else if (temp == max && tid > logs[i][0]) {
                tid = logs[i][0];
            }
        }
        return tid;
    }
}