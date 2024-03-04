package g1701_1800.s1716_calculate_money_in_leetcode_bank;

// #Easy #Math #2022_04_24_Time_1_ms_(65.33%)_Space_41.7_MB_(7.74%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input `n` must be a positive integer.*);
//@ ensures(*The input `n` must be less than or equal to 1000.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the total amount of money Hercy will have in the Leetcode bank at the end of the `n`th day.*);
    public int totalMoney(int n) {
        int mondayMoney = 1;
        int total = 0;
        while (n > 0) {
            int weekDays = 0;
            int base = mondayMoney;
            while (weekDays < 7 && n > 0) {
                total += base;
                base++;
                weekDays++;
                n--;
            }
            mondayMoney++;
        }
        return total;
    }
}