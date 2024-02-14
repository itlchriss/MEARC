package g0601_0700.s0649_dota2_senate;

// #Medium #String #Greedy #Queue #2022_03_21_Time_4_ms_(95.00%)_Space_41.8_MB_(93.75%)

public class Solution {
	//@ requires(*The input `senate` is a non-empty string representing the parties of the senators.*);
	//@ requires(*The length of `senate` is equal to the number of senators.*);
	//@ requires(*Each character in `senate` is either 'R' or 'D', representing the Radiant party or the Dire party respectively.*);
	//@ ensures(*The output is a string that represents the party that announces the victory and changes the Dota2 game.*);
	//@ ensures(*The output is either "Radiant" or "Dire".*);
	//@ ensures(*Additional behavioral requirements:*);
	//@ ensures(*The round-based procedure starts from the first senator to the last senator in the given order.*);
	//@ ensures(*Each senator can exercise one of the two rights: ban one senator's right or announce the victory.*);
	//@ ensures(*If a senator chooses to ban another senator's right, the senator loses all their rights in the current round and all the following rounds.*);
	//@ ensures(*If a senator chooses to announce the victory, they can only do so if all the senators who still have rights to vote are from the same party.*);
	//@ ensures(*If a senator's right has been banned, they cannot exercise any rights anymore.*);
	//@ ensures(*The procedure will last until the end of voting.*);
	//@ ensures(*All the senators who have lost their rights will be skipped during the procedure.*);
	//@ ensures(*Each senator will play the best strategy for their own party.*);
    public String predictPartyVictory(String senate) {
        int[] blocks = new int[2];
        boolean[] status = new boolean[senate.length()];
        boolean changes = true;
        while (changes) {
            changes = false;
            for (int i = 0; i < senate.length(); i++) {
                if (status[i]) {
                    continue;
                }
                char curr = senate.charAt(i);
                int block;
                if (curr == 'R') {
                    block = 0;
                } else {
                    block = 1;
                }

                if (blocks[1 - block] > 0) {
                    status[i] = true;
                    blocks[1 - block]--;
                    changes = true;
                } else {
                    blocks[block]++;
                }
            }
        }
        for (int i = 0; i < senate.length(); i++) {
            if (!status[i]) {
                if (senate.charAt(i) == 'R') {
                    return "Radiant";
                } else {
                    return "Dire";
                }
            }
        }
        return "";
    }
}