package g1801_1900.s1894_find_the_student_that_will_replace_the_chalk;

// #Medium #Array #Binary_Search #Simulation #Prefix_Sum #Binary_Search_II_Day_2
// #2022_05_09_Time_2_ms_(76.67%)_Space_55.6_MB_(67.06%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `chalk` is not null.*);
//@ ensures(*The length of the input array `chalk` is equal to `n`.*);
//@ ensures(*The input integer `k` is greater than or equal to 0.*);
//@ ensures(*The input integer `n` is greater than or equal to 1.*);
//@ ensures(*Each element in the input array `chalk` is greater than or equal to 1.*);
//@ ensures(*The sum of all elements in the input array `chalk` is less than or equal to `k`.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is an integer representing the index of the student that will replace the chalk.*);
//@ ensures(*The output is between 0 and `n-1`, inclusive.*);
    public int chalkReplacer(int[] chalk, int k) {
        long localSum = sum(chalk);
        int currentIndex = 0;
        if (localSum != 0) {
            int localK = (int) (k % localSum);
            while (chalk[currentIndex] <= localK) {
                localK -= chalk[currentIndex++];
            }
        }
        return currentIndex;
    }

    private long sum(int[] chalk) {
        long sum = 0;
        for (int i : chalk) {
            sum += i;
        }
        return sum;
    }
}