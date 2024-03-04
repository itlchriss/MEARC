package g0401_0500.s0412_fizz_buzz;

// #Easy #Top_Interview_Questions #String #Math #Simulation #Udemy_Integers
// #2022_07_16_Time_1_ms_(100.00%)_Space_48.4_MB_(48.76%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
//@ ensures(*The integer parameter `n` must be greater than or equal to 1.*);
//@ ensures(*The size of the string collection result is equal to the integer parameter `n`.*);
//@ ensures(*If the index `i` is divisible by both 3 and 5, the string at index `i` in the collection result is "FizzBuzz".*);
//@ ensures(*If the index `i` is divisible by 3, the string at index `i` in the collection result is "Fizz".*);
//@ ensures(*If the index `i` is divisible by 5, the string at index `i` in the collection result is "Buzz".*);
//@ ensures(*If none of the above conditions are true, the string at index `i` in the collection result is the string representation of `i`.*);
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