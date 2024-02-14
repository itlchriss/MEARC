package g2301_2400.s2347_best_poker_hand;

// #Easy #Array #Hash_Table #Counting #2022_07_26_Time_1_ms_(76.92%)_Space_41.6_MB_(30.77%)

import java.util.HashMap;

public class Solution {
	//@ requires(*The length of the `ranks` array is equal to the length of the `suits` array.*);
	//@ requires(*The length of the `ranks` array is equal to 5.*);
	//@ requires(*Each element in the `ranks` array is an integer between 1 and 13 (inclusive).*);
	//@ requires(*Each element in the `suits` array is a character between 'a' and 'd' (inclusive).*);
	//@ requires(*No two cards have the same rank and suit.*);
	//@ ensures(*The method returns a string representing the best type of poker hand that can be made with the given cards.*);
	//@ ensures(*The return value is case-sensitive.*);
    public String bestHand(int[] ranks, char[] suits) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char suit : suits) {
            if (map.containsKey(suit)) {
                map.put(suit, map.get(suit) + 1);
                if (map.get(suit) == 5) {
                    return "Flush";
                }
            } else {
                map.put(suit, 1);
            }
        }
        String s = "";
        HashMap<Integer, Integer> map2 = new HashMap<>();
        for (int rank : ranks) {
            if (map2.containsKey(rank)) {
                map2.put(rank, map2.get(rank) + 1);
                if (map2.get(rank) == 2) {
                    s = "Pair";
                } else if (map2.get(rank) == 3) {
                    s = "Three of a Kind";
                    return s;
                }
            } else {
                map2.put(rank, 1);
            }
        }
        return s.isEmpty() ? "High Card" : s;
    }
}