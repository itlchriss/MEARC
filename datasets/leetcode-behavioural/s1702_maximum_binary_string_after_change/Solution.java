package g1701_1800.s1702_maximum_binary_string_after_change;

// #Medium #String #Greedy #2022_04_24_Time_42_ms_(82.86%)_Space_80.4_MB_(50.00%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input `binary` is a non-empty binary string consisting of only `0`'s and `1`'s.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The returned string is a binary string.*);
//@ ensures(*The returned string is the maximum binary string that can be obtained after any number of operations.*);
//@ ensures(*The decimal representation of the returned string is greater than or equal to the decimal representation of the input `binary` string.*);
    public String maximumBinaryString(String binary) {
        char[] bs = binary.toCharArray();
        int zcount = 0;
        int pos = -1;
        for (int i = bs.length - 1; i >= 0; i--) {
            if (bs[i] == '0') {
                bs[i] = '1';
                zcount++;
                pos = i;
            }
        }
        if (pos >= 0) {
            bs[pos + zcount - 1] = '0';
        }
        return new String(bs);
    }
}