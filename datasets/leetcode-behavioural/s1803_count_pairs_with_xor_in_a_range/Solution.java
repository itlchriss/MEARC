package g1801_1900.s1803_count_pairs_with_xor_in_a_range;

// #Hard #Array #Bit_Manipulation #Trie #2022_05_02_Time_77_ms_(100.00%)_Space_70.3_MB_(61.54%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is greater than or equal to 2.*);
//@ ensures(*The values in the input array `nums` are between 1 and 2 * 10^4 (inclusive).*);
//@ ensures(*The value of `low` is between 1 and 2 * 10^4 (inclusive).*);
//@ ensures(*The value of `high` is between 1 and 2 * 10^4 (inclusive).*);
//@ ensures(*The value of `low` is less than or equal to `high`.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the number of nice pairs.*);
//@ ensures(*The returned value is greater than or equal to 0.*);
    public int countPairs(int[] nums, int low, int high) {
        Trie root = new Trie();
        int pairsCount = 0;
        for (int num : nums) {
            int pairsCountHigh = countPairsWhoseXorLessThanX(num, root, high + 1);
            int pairsCountLow = countPairsWhoseXorLessThanX(num, root, low);
            pairsCount += (pairsCountHigh - pairsCountLow);
            root.insertNumber(num);
        }
        return pairsCount;
    }

    private int countPairsWhoseXorLessThanX(int num, Trie root, int x) {
        int pairs = 0;
        Trie curr = root;
        for (int i = 14; i >= 0 && curr != null; i--) {
            int numIthBit = (num >> i) & 1;
            int xIthBit = (x >> i) & 1;
            if (xIthBit == 1) {
                if (curr.child[numIthBit] != null) {
                    pairs += curr.child[numIthBit].count;
                }
                curr = curr.child[1 - numIthBit];
            } else {
                curr = curr.child[numIthBit];
            }
        }
        return pairs;
    }

    private static class Trie {
        Trie[] child;
        int count;

        public Trie() {
            child = new Trie[2];
            count = 0;
        }

        public void insertNumber(int num) {
            Trie curr = this;
            for (int i = 14; i >= 0; i--) {
                int ithBit = (num >> i) & 1;
                if (curr.child[ithBit] == null) {
                    curr.child[ithBit] = new Trie();
                }
                curr.child[ithBit].count++;
                curr = curr.child[ithBit];
            }
        }
    }
}