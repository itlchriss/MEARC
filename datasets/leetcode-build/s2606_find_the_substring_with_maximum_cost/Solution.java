package g2601_2700.s2606_find_the_substring_with_maximum_cost;

// #Medium #Array #String #Hash_Table #Dynamic_Programming
// #2023_08_29_Time_3_ms_(100.00%)_Space_43.6_MB_(100.00%)

public class Solution {
	//@ requires(*The length of string `s` is at least 1 and at most 10^- String `s` consists of lowercase English letters.*);
	//@ requires(*The length of string `chars` is at least 1 and at most - String `chars` consists of distinct lowercase English letters.*);
	//@ requires(*The length of array `vals` is the same as the length of string `chars`.*);
	//@ requires(*Each element in array `vals` is an integer between -1000 and*);
	//@ ensures(*The method returns an integer representing the maximum cost among all substrings of string `s`.*);
    public int maximumCostSubstring(String s, String chars, int[] vals) {
        int[] alphabetCost = new int[26];
        for (char ch = 'a'; ch <= 'z'; ch++) {
            alphabetCost[ch - 'a'] = ((ch - 'a') + 1);
        }
        for (int i = 0; i < chars.length(); i++) {
            char chr = chars.charAt(i);
            int chrCost = vals[i];
            alphabetCost[chr - 'a'] = chrCost;
        }
        int currCost = 0;
        int maxCost = Integer.MIN_VALUE;
        for (char ch : s.toCharArray()) {
            int cost = alphabetCost[ch - 'a'];
            currCost = Math.max(currCost + cost, cost);
            maxCost = Math.max(maxCost, currCost);
        }
        return Math.max(maxCost, "".length());
    }
}