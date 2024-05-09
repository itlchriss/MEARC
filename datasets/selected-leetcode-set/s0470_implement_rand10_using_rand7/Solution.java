package g0401_0500.s0470_implement_rand10_using_rand7;

// #Medium #Math #Randomized #Probability_and_Statistics #Rejection_Sampling
// #2022_07_19_Time_8_ms_(82.15%)_Space_50.7_MB_(31.69%)

import java.util.Random;

@SuppressWarnings("java:S2245")
public class Solution {
    private final Random random = new Random();
//@ requires(*The method `rand10()` can only call the `rand7()` API and should not call any other API.*);
//@ requires(*The number of times the implemented function `rand10()` will be called while testing is provided as an internal argument `n`.*);
//@ requires(*The method `rand10()` should return a list of integers representing the random numbers generated within the range `[1, 10]` for each call.*);
//@ requires(*The constraints for the internal argument `n` are `1 <= n <= 10^5`.*);
//@ ensures(*The integer result after executing the method `rand10()` is within the range `[1, 10]`.*);

    public int rand10() {
        int x = rand7();
        int y = rand7();
        int value = (x - 1) * 7 + y;
        if (value >= 41) {
            return rand10();
        }
        return value % 10 + 1;
    }

    private int rand7() {
        return random.nextInt(7) + 1;
    }
}