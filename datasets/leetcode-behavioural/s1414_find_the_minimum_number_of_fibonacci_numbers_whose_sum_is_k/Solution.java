package g1401_1500.s1414_find_the_minimum_number_of_fibonacci_numbers_whose_sum_is_k;

// #Medium #Greedy #2022_03_26_Time_2_ms_(67.22%)_Space_41.9_MB_(28.81%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input `k` is an integer.*);
//@ ensures(*`k` is greater than or equal to 1 and less than or equal to 10^9.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the minimum number of Fibonacci numbers whose sum is equal to `k`.*);
    public int findMinFibonacciNumbers(int k) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);
        int prev = 1;
        int curr = 1;
        while (prev <= k) {
            int n = prev + curr;
            prev = curr;
            curr = n;
            list.add(n);
        }
        int count = 0;
        int num = k;
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i) <= num) {
                count++;
                num = num - list.get(i);
            }
        }
        return count;
    }
}