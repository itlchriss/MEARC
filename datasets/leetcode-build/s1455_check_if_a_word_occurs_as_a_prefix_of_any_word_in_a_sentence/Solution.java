package g1401_1500.s1455_check_if_a_word_occurs_as_a_prefix_of_any_word_in_a_sentence;

// #Easy #String #String_Matching #2022_03_28_Time_1_ms_(32.08%)_Space_41.9_MB_(43.38%)

public class Solution {
	//@ requires(*The `sentence` string is not null.*);
	//@ requires(*The `searchWord` string is not null.*);
	//@ requires(*The `sentence` string consists of lowercase English letters and spaces.*);
	//@ requires(*The `searchWord` string consists of lowercase English letters.*);
	//@ requires(*The length of the `sentence` string is between 1 and 100.*);
	//@ requires(*The length of the `searchWord` string is between 1 and 10.*);
	//@ ensures(*If `searchWord` is a prefix of any word in `sentence`, return the index of the first occurrence of the word where `searchWord` is a prefix (1-indexed).*);
	//@ ensures(*If `searchWord` is not a prefix of any word in `sentence`, return -1.*);
    public int isPrefixOfWord(String sentence, String searchWord) {
        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length; i++) {
            if (words[i].startsWith(searchWord)) {
                return i + 1;
            }
        }
        return -1;
    }
}