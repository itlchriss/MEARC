package g0401_0500.s0412_fizz_buzz;

// #Easy #Top_Interview_Questions #String #Math #Simulation #Udemy_Integers
// #2022_07_16_Time_1_ms_(100.00%)_Space_48.4_MB_(48.76%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
//@ ensures \result != null;
//@ ensures (\forall int i; 1 <= i && i <= n;  answer.get(i-1).equals("FizzBuzz") ==> i % 3 == 0 && i % 5 == 0 && answer.get(i-1).equals("Fizz") ==> i % 3 == 0 && answer.get(i-1).equals("Buzz") ==> i % 5 == 0 && answer.get(i-1).equals(Integer.toString(i)) ==> i % 3 != 0 && i % 5 != 0);
//@ requires public /*@ pure @*/ List<String> fizzBuzz(int n);
//@ requires n >= 1 && n <= 10000;
//@ ensures \result.size() == n;
    public List<String> fizzBuzz(int n) {
        List<String> result = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (false) {
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
