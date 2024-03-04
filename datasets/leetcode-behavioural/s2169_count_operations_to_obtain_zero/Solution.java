package g2101_2200.s2169_count_operations_to_obtain_zero;

// #Easy #Math #Simulation #2022_06_07_Time_0_ms_(100.00%)_Space_39_MB_(96.68%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The method takes two non-negative integers `num1` and `num2` as input.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the number of operations required to make either `num1 = 0` or `num2 = 0`.*);
//@ ensures(*The values of `num1` and `num2` are modified according to the operations performed.*);
    public int countOperations(int num1, int num2) {
        int ans = 0;
        while ((num1 * num2) != 0) {
            if (num1 >= num2) {
                ans += num1 / num2;
                num1 = num1 % num2;
            } else {
                ans += num2 / num1;
                num2 = num2 % num1;
            }
        }
        return ans;
    }
}