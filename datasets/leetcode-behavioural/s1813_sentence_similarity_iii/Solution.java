package g1801_1900.s1813_sentence_similarity_iii;

// #Medium #Array #String #Two_Pointers #2022_05_04_Time_3_ms_(41.38%)_Space_42.8_MB_(10.34%)

@SuppressWarnings("java:S2234")
public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input sentences `sentence1` and `sentence2` are not null.*);
//@ ensures(*The lengths of `sentence1` and `sentence2` are between 1 and 100 (inclusive).*);
//@ ensures(*The words in `sentence1` and `sentence2` consist of only uppercase and lowercase English letters.*);
//@ ensures(*The words in `sentence1` and `sentence2` are separated by a single space.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns `true` if it is possible to insert an arbitrary sentence (possibly empty) inside one of the input sentences such that the two sentences become equal.*);
//@ ensures(*The method returns `false` if no single sentence can be inserted inside one of the input sentences to make it equal to the other.*);
    public boolean areSentencesSimilar(String sentence1, String sentence2) {
        String[] words1 = sentence1.split(" ");
        String[] words2 = sentence2.split(" ");
        int i = 0;
        int n1 = words1.length;
        int n2 = words2.length;
        if (n1 > n2) {
            return areSentencesSimilar(sentence2, sentence1);
        }
        while (i < n1 && words1[i].equals(words2[i])) {
            ++i;
        }
        while (i < n1 && words1[i].equals(words2[n2 - n1 + i])) {
            ++i;
        }
        return i == n1;
    }
}