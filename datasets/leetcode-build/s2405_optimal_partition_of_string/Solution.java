package g2401_2500.s2405_optimal_partition_of_string;

// #Medium #String #Hash_Table #Greedy #2022_10_23_Time_7_ms_(99.40%)_Space_43.3_MB_(91.63%)

public class Solution {
	//@ requires(*The input string `s` is not null.*);
	//@ requires(*The input string `s` is not empty.*);
	//@ requires(*The input string `s` consists of only English lowercase letters.*);
	//@ ensures(*The output is an integer representing the minimum number of substrings needed to partition the input string.*);
	//@ ensures(*Each substring in the partition has unique characters.*);
	//@ ensures(*No letter appears in a single substring more than once.*);
	//@ ensures(*Each character in the input string belongs to exactly one substring in the partition.*);
    public int partitionString(String s) {
        int count = 1;
        boolean[] arr = new boolean[26];
        for (char c : s.toCharArray()) {
            if (arr[c - 'a']) {
                count++;
                arr = new boolean[26];
            }
            arr[c - 'a'] = true;
        }
        return count;
    }
}