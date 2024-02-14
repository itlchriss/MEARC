package g1301_1400.s1366_rank_teams_by_votes;

// #Medium #Array #String #Hash_Table #Sorting #Counting
// #2022_03_21_Time_5_ms_(95.68%)_Space_44.1_MB_(64.58%)

import java.util.Arrays;

public class Solution {
    static class Node {
        int[] count = new int[26];
        char c;
	//@ requires(*The input array `votes` is not null.*);
	//@ requires(*The length of the input array `votes` is greater than or equal to 1.*);
	//@ requires(*The length of each string in the input array `votes` is greater than or equal to 1.*);
	//@ requires(*The length of each string in the input array `votes` is less than or equal to 26.*);
	//@ requires(*The characters in each string of the input array `votes` are English uppercase letters.*);
	//@ requires(*All characters in the first string of the input array `votes` also occur in all other strings of the input array `votes`.*);
	//@ ensures(*The output string is not null.*);
	//@ ensures(*The length of the output string is equal to the length of each string in the input array `votes`.*);
	//@ ensures(*The characters in the output string are sorted according to the ranking system described in the requirements.*);
	//@ ensures(*If two or more teams have the same number of votes for a particular position, they are ranked alphabetically based on their team letter.*);

        public Node(char c) {
            this.c = c;
        }
    }

    public String rankTeams(String[] votes) {
        Node[] nodes = new Node[26];
        for (int i = 0; i < 26; i++) {
            nodes[i] = new Node((char) (i + 'A'));
        }
        for (String vote : votes) {
            for (int i = 0; i < vote.length(); i++) {
                nodes[vote.charAt(i) - 'A'].count[i]++;
            }
        }
        Arrays.sort(
                nodes,
                (o1, o2) -> {
                    for (int i = 0; i < 26; i++) {
                        if (o1.count[i] != o2.count[i]) {
                            return o2.count[i] - o1.count[i];
                        }
                    }
                    return o1.c - o2.c;
                });
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < votes[0].length(); i++) {
            sb.append(nodes[i].c);
        }
        return sb.toString();
    }
}