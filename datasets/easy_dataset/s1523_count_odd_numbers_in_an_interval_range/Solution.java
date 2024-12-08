package g1501_1600.s1523_count_odd_numbers_in_an_interval_range;

// #Easy #Math #Programming_Skills_I_Day_1_Basic_Data_Type
// #2022_04_09_Time_0_ms_(100.00%)_Space_41.5_MB_(10.62%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The method takes two non-negative integers `low` and `high` as input.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the count of odd numbers between `low` and `high` (inclusive).*);
    public int countOdds(int low, int high) {
        if (low % 2 != 0 || high % 2 != 0) {
            return (high - low) / 2 + 1;
        }
        return (high - low) / 2;
    }
}