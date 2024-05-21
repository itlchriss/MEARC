package g0801_0900.s0860_lemonade_change;

// #Easy #Array #Greedy #Programming_Skills_II_Day_17
// #2022_03_27_Time_2_ms_(90.84%)_Space_75.8_MB_(55.09%)

public class Solution {
//@ requires(*At a lemonade stand, each lemonade costs `$5`.*);
//@ requires(*Customers are standing in a queue to buy from you and order one at a time (in the order specified by bills).*);
//@ requires(*Each customer will only buy one lemonade and pay with either a `$5`, `$10`, or `$20` bill.*);
//@ requires(*You must provide the correct change to each customer so that the net transaction is that the customer pays `$5`.*);
//@ requires(*Note that you do not have any change in hand at first.*);
//@ requires(*Example 1:*);
//@ requires(*Input: bills = [5,5,5,10,20]*);
//@ requires(*Output: true*);
//@ requires(*Explanation:*);
//@ requires(*From the first 3 customers, we collect three $5 bills in order.*);
//@ requires(*From the fourth customer, we collect a $10 bill and give back a $5.*);
//@ requires(*From the fifth customer, we give a $10 bill and a $5 bill.*);
//@ requires(*Since all customers got correct change, we output true.*);
//@ requires(*Example 2:*);
//@ requires(*Input: bills = [5,5,10,10,20]*);
//@ requires(*Output: false*);
//@ requires(*Explanation:*);
//@ requires(*From the first two customers in order, we collect two $5 bills.*);
//@ requires(*For the next two customers in order, we collect a $10 bill and give back a $5 bill.*);
//@ requires(*For the last customer, we can not give the change of $15 back because we only have two $10 bills.*);
//@ requires(*Since not every customer received the correct change, the answer is false.*);
//@ requires(*Constraints:*);
//@ requires(*<code>1 <= bills.length <= 10<sup>5</sup></code>*);
//@ requires(*`bills[i]` is either `5`, `10`, or `20`.*);
//@ ensures(*Given an integer array param_bills where `bills[i]` is the bill the <code>i<sup>th</sup></code> customer pays, the result is `true` if you can provide every customer with the correct change, or `false` otherwise.*);
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