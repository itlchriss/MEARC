package g1501_1600.s1553_minimum_number_of_days_to_eat_n_oranges;

// #Hard #Dynamic_Programming #Memoization #2022_04_11_Time_5_ms_(91.90%)_Space_43.5_MB_(31.90%)

import java.util.HashMap;
import java.util.Map;

public class Solution {
	//@ requires(*The input `n` is a positive integer greater than or equal to 1.*);
	//@ ensures(*The output is an integer representing the minimum number of days required to eat all the oranges.*);
	//@ ensures(*The number of remaining oranges after each day is updated according to the given rules.*);
	//@ ensures(*The number of remaining oranges after the last day is 0.*);

    public int minDays(int n) {
        return eat(n, new HashMap<>());
    }

    private int eat(int n, Map<Integer, Integer> cache) {
        if (n <= 1) {
            return n;
        }
        Integer cached = cache.get(n);
        if (cached != null) {
            return cached;
        }
        int result = Math.min(n % 2 + eat(n / 2, cache), n % 3 + eat(n / 3, cache)) + 1;
        cache.put(n, result);
        return result;
    }
}