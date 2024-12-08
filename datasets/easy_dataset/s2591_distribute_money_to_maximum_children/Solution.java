package g2501_2600.s2591_distribute_money_to_maximum_children;

// #Easy #Math #Greedy #2023_08_23_Time_1_ms_(100.00%)_Space_40.7_MB_(71.47%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input `money` must be an integer between 1 and 200 (inclusive).*);
//@ ensures(*The input `children` must be an integer between 2 and 30 (inclusive).*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the maximum number of children who may receive exactly 8 dollars.*);
//@ ensures(*If there is no way to distribute the money according to the rules, the method returns -1.*);
    public int distMoney(int money, int children) {
        if (money < children) {
            return -1;
        }
        if (money < children + 7) {
            return 0;
        }
        int numEighters = 0;
        money -= children;
        int possibleEighters = children;
        while (money >= 7 && possibleEighters > 0) {
            money -= 7;
            ++numEighters;
            --possibleEighters;
        }
        if ((money > 0 && possibleEighters == 0) || (money == 3 && possibleEighters == 1)) {
            --numEighters;
        }
        return numEighters;
    }
}