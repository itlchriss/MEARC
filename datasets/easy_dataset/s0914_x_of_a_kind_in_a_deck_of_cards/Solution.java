package g0901_1000.s0914_x_of_a_kind_in_a_deck_of_cards;

// #Easy #Array #Hash_Table #Math #Counting #Number_Theory
// #2022_07_14_Time_11_ms_(51.18%)_Space_52.7_MB_(22.45%)

import java.util.HashMap;
import java.util.Map;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `deck` is not null.*);
//@ ensures(*The length of the input array `deck` is greater than or equal to 1.*);
//@ ensures(*The elements in the input array `deck` are non-negative integers.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns a boolean value indicating whether it is possible to split the entire deck into groups of cards.*);
//@ ensures(*If the method returns true, it means that there exists a value `X` (greater than or equal to 2) such that the deck can be split into groups of size `X` where all the cards in each group have the same integer.*);
//@ ensures(*If the method returns false, it means that it is not possible to split the deck into groups satisfying the given conditions.*);
    public boolean hasGroupsSizeX(int[] deck) {
        HashMap<Integer, Integer> hmap = new HashMap<>();
        for (int j : deck) {
            if (hmap.containsKey(j)) {
                hmap.put(j, hmap.get(j) + 1);
            } else {
                hmap.put(j, 1);
            }
        }
        int x = hmap.get(deck[0]);
        for (Map.Entry<Integer, Integer> entry : hmap.entrySet()) {
            x = gcd(x, entry.getValue());
        }
        return x >= 2;
    }

    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}