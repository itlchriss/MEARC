package g2401_2500.s2416_sum_of_prefix_scores_of_strings;

// #Hard #Array #String #Counting #Trie #2022_11_15_Time_179_ms_(94.98%)_Space_374.1_MB_(10.98%)

public class Solution {
    private Solution[] child;
    private int ct;
	//@ requires(*The input array `words` is not null.*);
	//@ requires(*The length of the input array `words` is greater than or equal to 1.*);
	//@ requires(*Each string in the input array `words` is not null and consists of lowercase English letters.*);
	//@ ensures(*The output array `answer` is not null.*);
	//@ ensures(*The length of the output array `answer` is equal to the length of the input array `words`.*);
	//@ ensures(*Each element in the output array `answer` is an integer.*);
	//@ ensures(*The sum of all elements in the output array `answer` is equal to the sum of the scores of every non-empty prefix of each string in the input array `words`.*);

    public Solution() {
        child = new Solution[26];
        ct = 0;
    }

    public int[] sumPrefixScores(String[] words) {
        for (String s : words) {
            insert(s);
        }
        int[] res = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            res[i] = countPre(word);
        }
        return res;
    }

    private void insert(String word) {
        Solution cur = this;
        for (int i = 0; i < word.length(); i++) {
            int id = word.charAt(i) - 'a';
            if (cur.child[id] == null) {
                cur.child[id] = new Solution();
            }
            cur.child[id].ct++;
            cur = cur.child[id];
        }
    }

    private int countPre(String word) {
        Solution cur = this;
        int localCt = 0;
        for (int i = 0; i < word.length(); i++) {
            int id = word.charAt(i) - 'a';
            if (cur.child[id] == null) {
                return localCt;
            }
            localCt += cur.ct;
            cur = cur.child[id];
        }
        localCt += cur.ct;
        return localCt;
    }
}