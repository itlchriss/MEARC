package g1301_1400.s1376_time_needed_to_inform_all_employees;

// #Medium #Depth_First_Search #Breadth_First_Search #Tree #Programming_Skills_II_Day_11
// #Graph_Theory_I_Day_9_Standard_Traversal #2023_09_03_Time_8_ms_(99.85%)_Space_58_MB_(89.47%)

@SuppressWarnings("java:S1172")
public class Solution {
    private int numMinsDFS(int index, int[] manager, int[] informTime) {
        if (manager[index] != -1) {
            informTime[index] += numMinsDFS(manager[index], manager, informTime);
            manager[index] = -1;
        }
        return informTime[index];
    }
	//@ requires(*The input `n` is a positive integer representing the number of employees.*);
	//@ requires(*The input `headID` is a non-negative integer representing the ID of the head of the company.*);
	//@ requires(*The input `manager` is an array of length `n` where each element represents the direct manager of the corresponding employee.*);
	//@ requires(*The input `informTime` is an array of length `n` where each element represents the time needed for the corresponding employee to inform their direct subordinates.*);
	//@ requires(*The subordination relationships represented by the `manager` array form a tree structure.*);
	//@ requires(*The head of the company is the employee with ID `headID` and their direct manager is represented by `manager[headID] = -1`.*);
	//@ requires(*The `informTime` array contains non-negative integers representing the time needed for each employee to inform their direct subordinates.*);
	//@ requires(*If an employee has no subordinates, their `informTime` is 0.*);
	//@ ensures(*The method returns an integer representing the number of minutes needed to inform all the employees about the urgent news.*);
	//@ ensures(*All employees are informed about the urgent news.*);
	//@ ensures(*The time needed for an employee to inform their direct subordinates is equal to the maximum `informTime` among their direct subordinates.*);

    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        int time = informTime[headID];
        for (int i = 0; i < n; i++) {
            if (informTime[i] == 0) {
                continue;
            }
            int timei = numMinsDFS(i, manager, informTime);
            if (timei > time) {
                time = timei;
            }
        }
        return time;
    }
}