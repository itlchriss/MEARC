package g0701_0800.s0763_partition_labels;

// #Medium #Top_100_Liked_Questions #String #Hash_Table #Greedy #Two_Pointers
// #Data_Structure_II_Day_7_String #Big_O_Time_O(n)_Space_O(1)
// #2022_03_26_Time_1_ms_(100.00%)_Space_40.3_MB_(98.19%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
	//@ requires(*The input string `s` is not null.*);
	//@ requires(*The length of the input string `s` is between 1 and 500.*);
	//@ requires(*The input string `s` consists of lowercase English letters.*);
	//@ ensures(*The output is a list of integers representing the size of the partitions.*);
	//@ ensures(*Each letter in the input string `s` appears in at most one partition.*);
	//@ ensures(*The sum of the sizes of all partitions is equal to the length of the input string `s`.*);
    public List<Integer> partitionLabels(String s) {
        char[] letters = s.toCharArray();
        List<Integer> result = new ArrayList<>();
        int[] position = new int[26];
        for (int i = 0; i < letters.length; i++) {
            position[letters[i] - 'a'] = i;
        }
        int i = 0;
        int prev = -1;
        int max = 0;
        while (i < letters.length) {
            if (position[letters[i] - 'a'] > max) {
                max = position[letters[i] - 'a'];
            }
            if (i == max) {
                result.add(i - prev);
                prev = i;
            }
            i++;
        }
        return result;
    }
}