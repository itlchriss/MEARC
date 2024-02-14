package g1701_1800.s1792_maximum_average_pass_ratio;

// #Medium #Array #Greedy #Heap_Priority_Queue
// #2022_05_03_Time_456_ms_(89.78%)_Space_83.8_MB_(96.44%)

import java.util.PriorityQueue;

public class Solution {
    // O(m*logn+n*logn)
    // PriorityQueue
	//@ requires(*The input `classes` is a 2D integer array.*);
	//@ requires(*Each element `classes[i]` in `classes` is an array of length 2.*);
	//@ requires(*The first element `classes[i][0]` in `classes[i]` represents the number of students who will pass the exam in the `i`th class.*);
	//@ requires(*The second element `classes[i][1]` in `classes[i]` represents the total number of students in the `i`th class.*);
	//@ requires(*The input `extraStudents` is an integer.*);
	//@ requires(*The value of `extraStudents` is greater than or equal to 1.*);
	//@ requires(*The length of `classes` is greater than or equal to 1.*);
	//@ requires(*The value of `classes[i][0]` is greater than or equal to 1.*);
	//@ requires(*The value of `classes[i][1]` is greater than or equal to `classes[i][0]`.*);
	//@ requires(*The value of `classes[i][1]` is less than or equal to 10^5.*);
	//@ requires(*The value of `extraStudents` is less than or equal to 10^5.*);
	//@ ensures(*The method returns a double value representing the maximum possible average pass ratio after assigning the `extraStudents` students.*);
	//@ ensures(*The returned value is within 10^-5 of the actual answer.*);
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        PriorityQueue<double[]> heap =
                new PriorityQueue<>((o1, o2) -> Double.compare(o2[0], o1[0]));
        for (int[] clas : classes) {
            double delta = profit(clas[0], clas[1]);
            heap.offer(new double[] {delta, clas[0], clas[1]});
        }
        while (extraStudents >= 1) {
            double[] temp = heap.poll();
            double pass = temp[1] + 1;
            double total = temp[2] + 1;
            double delta = profit(pass, total);
            heap.offer(new double[] {delta, pass, total});
            extraStudents--;
        }
        double average = 0d;
        while (!heap.isEmpty()) {
            double[] temp = heap.poll();
            average += temp[1] / temp[2];
        }
        return average / classes.length;
    }

    // O(1)
    private double profit(double a, double b) {
        return (a + 1) / (b + 1) - a / b;
    }
}