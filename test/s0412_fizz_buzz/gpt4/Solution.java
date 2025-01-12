package g0401_0500.s0412_fizz_buzz;

// #Easy #Top_Interview_Questions #String #Math #Simulation #Udemy_Integers
// #2022_07_16_Time_1_ms_(100.00%)_Space_48.4_MB_(48.76%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
//@ requires(*The integer parameter `n` is less than or equal to 10000 and is greater than or equal to 1.*);
//@ ensures(*The length of the list result is equal to the integer parameter `n`.*);
//@ ensures(*If the list result contains the string "FizzBuzz", the index of "FizzBuzz" plus 1 is divisible by both 3 and 5.*);
//@ ensures(*If the list result contains the string "Fizz", the index of "Fizz" plus 1 is divisible by 3 and is not divisible by 5.*);
//@ ensures(*If the list result contains the string "Buzz", the index of "Buzz" plus 1 is divisible by 5 and is not divisible by 3.*);
//@ ensures(*If the list result contains a string that is a numeric representation of an integer, the index of that string plus 1 is not divisible by 3 and is not divisible by 5.*);
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