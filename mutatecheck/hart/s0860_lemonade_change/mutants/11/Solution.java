package g0801_0900.s0860_lemonade_change;

// #Easy #Array #Greedy #Programming_Skills_II_Day_17
// #2022_03_27_Time_2_ms_(90.84%)_Space_75.8_MB_(55.09%)

import java.util.Arrays;

import java.util.Collections;

public class Solution {
//@ requires((bills.length <= 100000) && (bills.length >= 1));
//@ ensures((Arrays.equals(bills, new int[] {5 , 5 , 10 , 10 , 20})) ==> (\result == false));
//@ ensures((Arrays.equals(bills, new int[] {5 , 5 , 5 , 10 , 20})) ==> (\result == true));
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
            } else if (false) {
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
