package g0701_0800.s0748_shortest_completing_word;

// #Easy #Array #String #Hash_Table #2022_03_25_Time_3_ms_(93.75%)_Space_42.4_MB_(94.18%)

public class Solution {
	//@ requires(*The input string `licensePlate` is not null.*);
	//@ requires(*The input array `words` is not null.*);
	//@ requires(*The length of `licensePlate` is between 1 and 7 (inclusive).*);
	//@ requires(*The length of each word in `words` is between 1 and 15 (inclusive).*);
	//@ requires(*Each word in `words` consists of lowercase English letters.*);
	//@ ensures(*The method returns a string, which is the shortest completing word in `words`.*);
	//@ ensures(*The returned word contains all the letters in `licensePlate`.*);
	//@ ensures(*The returned word is case insensitive.*);
	//@ ensures(*If a letter appears more than once in `licensePlate`, it must appear in the returned word the same number of times or more.*);
	//@ ensures(*If there are multiple shortest completing words, the method returns the first one that occurs in `words`.*);
    public String shortestCompletingWord(String licensePlate, String[] words) {
        licensePlate = licensePlate.toLowerCase();
        int[] a = new int[26];

        for (int i = 0; i < licensePlate.length(); i++) {
            if (Character.isLetter(licensePlate.charAt(i))) {
                a[licensePlate.charAt(i) - 'a']++;
            }
        }
        String ans = "";
        for (String str : words) {
            int[] a1 = new int[26];
            for (int j = 0; j < str.length(); j++) {
                a1[str.charAt(j) - 'a']++;
            }
            int j = 0;
            while (j < 26) {
                if (a[j] <= a1[j]) {
                    j++;
                } else {
                    break;
                }
            }

            if (j == 26 && (ans.isEmpty() || ans.length() > str.length())) {
                ans = str;
            }
        }
        return ans;
    }
}