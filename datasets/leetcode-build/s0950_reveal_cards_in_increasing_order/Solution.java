package g0901_1000.s0950_reveal_cards_in_increasing_order;

// #Medium #Array #Sorting #Simulation #Queue #2022_12_26_Time_1_ms_(100.00%)_Space_42.5_MB_(85.25%)

import java.util.Arrays;

public class Solution {
	//@ requires(*The input array `deck` is not null.*);
	//@ requires(*The length of `deck` is at least 1 and at most 1000.*);
	//@ requires(*Each element in `deck` is an integer between 1 and 10^6 (inclusive).*);
	//@ requires(*All the values in `deck` are unique.*);
	//@ ensures(*The output is an array of integers.*);
	//@ ensures(*The length of the output array is the same as the length of the input array.*);
	//@ ensures(*The output array contains all the elements from the input array.*);
	//@ ensures(*The elements in the output array are in increasing order.*);
	//@ ensures(*The first element in the output array is the smallest element from the input array.*);
	//@ ensures(*The last element in the output array is the largest element from the input array.*);
	//@ ensures(*The order of the elements in the output array is such that it follows the steps described in the problem statement:*);
	//@ ensures(*1. Take the top card of the deck, reveal it, and take it out of the deck.*);
	//@ ensures(*2. If there are still cards in the deck then put the next top card of the deck at the bottom of the deck.*);
	//@ ensures(*3. If there are still unrevealed cards, go back to step 1. Otherwise, stop.*);
    public int[] deckRevealedIncreasing(int[] deck) {
        Arrays.sort(deck);
        int n = deck.length;
        int[] result = new int[n * 2];
        int idx = result.length - 1;
        int lastIdx = result.length - 1;
        int i = n - 1;
        while (idx >= 0 && i >= 0) {
            if (i != (n - 1)) {
                result[idx--] = result[lastIdx--];
            }
            result[idx--] = deck[i--];
        }
        return Arrays.copyOfRange(result, idx + 1, lastIdx + 1);
    }
}