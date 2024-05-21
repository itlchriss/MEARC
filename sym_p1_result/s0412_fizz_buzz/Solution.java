package g0401_0500.s0412_fizz_buzz;

// #Easy #Top_Interview_Questions #String #Math #Simulation #Udemy_Integers
// #2022_07_16_Time_1_ms_(100.00%)_Space_48.4_MB_(48.76%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
//@ requires(*`answer[i] == "FizzBuzz"` if `i` is divisible by `3` and `5`.*);
//@ requires(*`answer[i] == "Fizz"` if `i` is divisible by `3`.*);
//@ requires(*`answer[i] == "Buzz"` if `i` is divisible by `5`.*);
//@ requires(*`answer[i] == i` (as a string) if none of the above conditions are true.*);
//@ requires(*Example 1:*);
//@ requires(*Input: n = 3*);
//@ requires(*Output: ["1","2","Fizz"]*);
//@ requires(*Example 2:*);
//@ requires(*Input: n = 5*);
//@ requires(*Output: ["1","2","Fizz","4","Buzz"]*);
//@ requires(*Example 3:*);
//@ requires(*Input: n = 15*);
//@ requires(*Output: ["1","2","Fizz","4","Buzz","Fizz","7","8","Fizz","Buzz","11","Fizz","13","14","FizzBuzz"]*);
//@ requires(*Constraints:*);
//@ requires(*<code>1 <= n <= 10<sup>4</sup></code>*);
//@ ensures(*Given an integer param_n, the result is a string array `answer` (1-indexed) where:*);
    public List<String> fizzBuzz(int n) {
        List<String> result = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                result.add("FizzBuzz");
            } else if (i % 3 == 0) {
                result.add("Fizz");
            } else if (i % 5 == 0) {
                result.add("Buzz");
            } else {
                result.add(Integer.toString(i));
            }
        }
        return result;
    }
}