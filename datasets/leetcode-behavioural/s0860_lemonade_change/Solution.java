package g0801_0900.s0860_lemonade_change;

// #Easy #Array #Greedy #Programming_Skills_II_Day_17
// #2022_03_27_Time_2_ms_(90.84%)_Space_75.8_MB_(55.09%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `bills` is not null.*);
//@ ensures(*The length of the input array `bills` is greater than or equal to 1.*);
//@ ensures(*Each element in the input array `bills` is either 5, 10, or 20.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns true if it is possible to provide every customer with the correct change, and false otherwise.*);
    public boolean lemonadeChange(int[] bills) {
        int countFive = 0;
        int countTen = 0;
        for (int bill : bills) {
            if (bill == 5) {
                countFive++;
            } else if (bill == 10) {
                if (countFive == 0) {
                    return false;
                }
                countFive--;
                countTen++;
            } else if (bill == 20) {
                if (countFive > 0 && countTen > 0) {
                    countFive--;
                    countTen--;
                } else if (countFive >= 3) {
                    countFive -= 3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}