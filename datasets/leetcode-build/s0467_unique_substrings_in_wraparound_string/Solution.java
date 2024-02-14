package g0401_0500.s0467_unique_substrings_in_wraparound_string;

// #Medium #String #Dynamic_Programming #2022_07_19_Time_4_ms_(98.79%)_Space_44_MB_(5.26%)

public class Solution {
	//@ requires(*The input string `p` is not null.*);
	//@ requires(*The length of `p` is greater than or equal to 1 and less than or equal to 10^`p` consists of lowercase English letters.*);
	//@ ensures(*The method returns an integer representing the number of unique non-empty substrings of `p` that are present in the infinite wraparound string `s`.*);
	//@ ensures(*The method correctly counts the number of unique non-empty substrings of `p` that are present in `s`.*);
	//@ ensures(*The method handles the case when `p` contains only one character and returns 1 if that character is present in `s`.*);
	//@ ensures(*The method handles the case when `p` contains multiple characters and returns the correct count of unique non-empty substrings present in `s`.*);
	//@ ensures(*The method handles the case when `p` contains repeated characters and counts each unique non-empty substring only once.*);
	//@ ensures(*The method handles the case when `p` contains characters that are not consecutive in the alphabet and counts each unique non-empty substring correctly.*);
	//@ ensures(*The method handles the case when `p` contains characters that are consecutive in the alphabet and counts each unique non-empty substring correctly.*);
	//@ ensures(*The method handles the case when `p` contains characters that are consecutive in the alphabet but not in the correct order and counts each unique non-empty substring correctly.*);
	//@ ensures(*The method handles the case when `p` contains characters that are consecutive in the alphabet but not in the correct order and counts each unique non-empty substring correctly.*);
	//@ ensures(*The method handles the case when `p` contains characters that are consecutive in the alphabet but not in the correct order and counts each unique non-empty substring correctly.*);
	//@ ensures(*The method handles the case when `p` contains characters that are consecutive in the alphabet but not in the correct order and counts each unique non-empty substring correctly.*);
	//@ ensures(*The method handles the case when `p` contains characters that are consecutive in the alphabet but not in the correct order and counts each unique non-empty substring correctly.*);
	//@ ensures(*The method handles the case when `p` contains characters that are consecutive in the alphabet but not in the correct order and counts each unique non-empty substring correctly.*);
	//@ ensures(*The method handles the case when `p` contains characters that are consecutive in the alphabet but not in the correct order and counts each unique non-empty substring correctly.*);
	//@ ensures(*The method handles the case when `p` contains characters that are consecutive in the alphabet but not in the correct order and counts each unique non-empty substring correctly.*);
	//@ ensures(*The method handles the case when `p` contains characters that are consecutive in the alphabet but not in the correct order and counts each unique non-empty substring correctly.*);
	//@ ensures(*The method handles the case when `p` contains characters that are consecutive in the alphabet but not in the correct order and counts each unique non-empty substring correctly.*);
	//@ ensures(*The method handles the case when `p` contains characters that are consecutive in the alphabet but not in the correct order and counts each unique non-empty substring correctly.*);
	//@ ensures(*The method handles the case when `p` contains characters that are consecutive in the alphabet but not in the correct order and counts each unique non-empty substring correctly.*);
	//@ ensures(*The method handles the case when `p` contains characters that are consecutive in the alphabet but not in the correct order and counts each unique non-empty substring correctly.*);
    public int findSubstringInWraproundString(String p) {
        char[] str = p.toCharArray();
        int n = str.length;
        int[] map = new int[26];
        int len = 0;
        for (int i = 0; i < n; i++) {
            if (i > 0 && ((str[i - 1] + 1 == str[i]) || (str[i - 1] == 'z' && str[i] == 'a'))) {
                len += 1;
            } else {
                len = 1;
            }
            // we are storing the max len of string for each letter and then we will count all these
            // length.
            map[str[i] - 'a'] = Math.max(map[str[i] - 'a'], len);
        }
        int answer = 0;
        for (int num : map) {
            answer += num;
        }
        return answer;
    }
}