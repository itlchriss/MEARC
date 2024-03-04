package g0601_0700.s0650_2_keys_keyboard;

// #Medium #Dynamic_Programming #Math #2022_03_21_Time_0_ms_(100.00%)_Space_38.9_MB_(80.65%)

public class Solution {
//@ ensures(*Method behavioural requirements:*);
//@ ensures(*The integer parameter `n` must be greater than or equal to 1.*);
//@ ensures(*If the integer parameter `n` is equal to 1, the integer result is 0.*);
//@ ensures(*If the integer parameter `n` is greater than 1, the integer result is the minimum number of operations to get the character 'A' exactly `n` times on the screen.*);
    public int minSteps(int n) {
        int count = 1;
        int cost = 0;
        int addValue = 1;
        while (count < n) {
            cost++;
            count += addValue;
            if (n % count == 0) {
                cost++;
                addValue = count;
            }
        }
        return cost;
    }
}