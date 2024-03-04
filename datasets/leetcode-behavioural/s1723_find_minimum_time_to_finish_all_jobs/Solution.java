package g1701_1800.s1723_find_minimum_time_to_finish_all_jobs;

// #Hard #Array #Dynamic_Programming #Bit_Manipulation #Backtracking #Bitmask
// #2022_04_25_Time_3_ms_(76.73%)_Space_41.4_MB_(72.04%)

public class Solution {
    private int min = Integer.MAX_VALUE;
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `jobs` is not null.*);
//@ ensures(*The length of the input array `jobs` is greater than or equal to `k`.*);
//@ ensures(*The input integer `k` is greater than or equal to 1.*);
//@ ensures(*Each element in the input array `jobs` is a positive integer.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is an integer representing the minimum possible maximum working time of any assignment.*);
//@ ensures(*The output is greater than or equal to 0.*);
//@ ensures(*The output is less than or equal to the sum of all elements in the input array `jobs`.*);
//@ ensures(*The output is the minimum possible maximum working time achieved by assigning jobs to workers.*);

    public int minimumTimeRequired(int[] jobs, int k) {
        backtraking(jobs, jobs.length - 1, new int[k]);
        return min;
    }

    private void backtraking(int[] jobs, int j, int[] sum) {
        int max = getMax(sum);
        if (max >= min) {
            return;
        }
        if (j < 0) {
            min = max;
            return;
        }
        for (int i = 0; i < sum.length; i++) {
            if (i > 0 && sum[i] == sum[i - 1]) {
                continue;
            }
            sum[i] += jobs[j];
            backtraking(jobs, j - 1, sum);
            sum[i] -= jobs[j];
        }
    }

    private int getMax(int[] sum) {
        int max = Integer.MIN_VALUE;
        for (int j : sum) {
            max = Math.max(max, j);
        }
        return max;
    }
}