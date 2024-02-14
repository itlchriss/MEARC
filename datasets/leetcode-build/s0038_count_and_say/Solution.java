package g0001_0100.s0038_count_and_say;

// #Medium #Top_Interview_Questions #String #2023_08_09_Time_2_ms_(97.68%)_Space_39.9_MB_(90.19%)

public class Solution {
	//@ requires(*The input `n` is a positive integer.*);
	//@ requires(*The value of `n` is between 1 and 30 (inclusive).*);
	//@ ensures(*The method returns a string representing the `nth` term of the count-and-say sequence.*);
	//@ ensures(*The returned string follows the rules of the count-and-say sequence:*);
	//@ ensures(*- The first term is always "1".*);
	//@ ensures(*- Each subsequent term is obtained by "saying" the previous term.*);
	//@ ensures(*- To "say" a term, it is split into groups of contiguous characters that are the same.*);
	//@ ensures(*- For each group, the number of characters is said, followed by the character itself.*);
	//@ ensures(*- The saying is then converted into a digit string by replacing the counts with numbers and concatenating every saying.*);
    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        StringBuilder res = new StringBuilder();
        String prev = countAndSay(n - 1);
        int count = 1;
        for (int i = 1; i < prev.length(); i++) {
            if (prev.charAt(i) == prev.charAt(i - 1)) {
                count++;
            } else {
                res.append(count).append(prev.charAt(i - 1));
                count = 1;
            }
        }
        res.append(count).append(prev.charAt(prev.length() - 1));
        return res.toString();
    }
}