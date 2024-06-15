package g0801_0900.s0860_lemonade_change;

// #Easy #Array #Greedy #Programming_Skills_II_Day_17
// #2022_03_27_Time_2_ms_(90.84%)_Space_75.8_MB_(55.09%)

public class Solution {
// requires public boolean lemonadeChange(int[] bills)
//@ ensures \result == true || \result == false;
// ensures \result == true ==> // can provide every customer with correct change
// ensures // detailed explanation of how the correct change is provided based on the input array bills
//@ requires bills != null && bills.length >= 1;
// requires constraints: 1 <= bills.length <= 10^5, bills[i] is either 5, 10, or 20.
// ensures \result == false ==> // cannot provide every customer with correct change
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
                if (countFive > 0 && countTen >= 0) {
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
